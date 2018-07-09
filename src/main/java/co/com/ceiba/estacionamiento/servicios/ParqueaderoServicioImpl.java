package co.com.ceiba.estacionamiento.servicios;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;
import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.dto.VehiculoDTO;
import co.com.ceiba.estacionamiento.dominio.excepciones.PaginaNoEncontradaException;
import co.com.ceiba.estacionamiento.dominio.fabrica.VehiculoFactory;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.persistencia.builders.TicketParqueaderoBuilder;
import co.com.ceiba.estacionamiento.persistencia.builders.TicketParqueaderoDTOBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;
import co.com.ceiba.estacionamiento.persistencia.repositorio.TicketParqueaderoRepositorio;

@Service
public class ParqueaderoServicioImpl implements ParqueaderoServicio {

	@Autowired
	public TicketParqueaderoRepositorio ticketParqueaderoRepositorio;

	@Override
	public Integer verificarCupoVehiculo(String tipoVehiculo) {
		return ticketParqueaderoRepositorio.verificarCupoVehiculo(tipoVehiculo);
	}

	@Override
	public Integer verificarIngresoVehiculo(String placa) {
		return ticketParqueaderoRepositorio.verificarIngresoVehiculo(placa);
	}

	@Override
	public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero) {
		TicketParqueaderoEntity ticketParqueaderoEntity = TicketParqueaderoBuilder.convertirAEntity(ticketParqueadero);
		return ticketParqueaderoRepositorio.save(ticketParqueaderoEntity) != null;
	}

	@Override
	public TicketParqueadero actualizarTicketParqueadero(TicketParqueadero ticketParqueadero) {
		TicketParqueadero resultado = null;
		Optional<TicketParqueaderoEntity> ticketParqueaderoEntity = ticketParqueaderoRepositorio
				.obtenerTicketParqueaderoByPlaca(ticketParqueadero.getVehiculo().getPlaca());
		if (ticketParqueaderoEntity.isPresent()) {
			TicketParqueaderoEntity actualizarTicketParqueadero = ticketParqueaderoEntity.get();
			actualizarTicketParqueadero.setFechaSalida(ticketParqueadero.getFechaSalida());
			actualizarTicketParqueadero.setValor(ticketParqueadero.getValor());
			actualizarTicketParqueadero = ticketParqueaderoRepositorio.save(actualizarTicketParqueadero);
			resultado = TicketParqueaderoBuilder.convertirADominio(actualizarTicketParqueadero);
		}
		return resultado;
	}

	@Override
	public TicketParqueadero obtenerTicketParquedero(String placa) {
		TicketParqueadero ticketParqueadero = null;
		Optional<TicketParqueaderoEntity> ticketParqueaderoEntity = ticketParqueaderoRepositorio
				.obtenerTicketParqueaderoByPlaca(placa);
		if (ticketParqueaderoEntity.isPresent()) {
			ticketParqueadero = TicketParqueaderoBuilder.convertirADominio(ticketParqueaderoEntity.get());
		}
		return ticketParqueadero;
	}

	@Override
	public Map<String, Object> listarVehiculosParqueadero(int pagina, int tamano, String dirOrdenamiento,
			String campoOrdenamiento) {

		PageRequest pageReq = PageRequest.of(pagina, tamano, Sort.Direction.fromString(dirOrdenamiento),
				campoOrdenamiento);
		Page<TicketParqueaderoEntity> ticketParqueaderoPage = ticketParqueaderoRepositorio
				.listarVehiculosParqueadero(pageReq);

		if (pagina > ticketParqueaderoPage.getTotalPages()) {
			throw new PaginaNoEncontradaException("El numero de pagina consultado es erroneo.");
		}
		List<TicketParqueaderoEntity> ticketParqueaderoEntities = ticketParqueaderoPage.getContent();
		int totalRegistros = ticketParqueaderoRepositorio.contarVehiculosParqueadero();
		TicketParqueaderoDTOBuilder ticketParqueaderoDTOBuilder = new TicketParqueaderoDTOBuilder();
		List<TicketParqueaderoDTO> ticketParqueaderoDTOs = ticketParqueaderoEntities.stream()
				.map(ticketParqueaderoDTOBuilder::convertirADTO).collect(Collectors.toList());
		Map<String, Object> resultado = new LinkedHashMap<>();
		resultado.put("total", totalRegistros);
		resultado.put("elementos", ticketParqueaderoDTOs);
		return resultado;
	}

	@Override
	public boolean registraringreso(VehiculoDTO vehiculoDTO, VehiculoServicio vehiculoServicio,
			TarifaServicio tarifaServicio) {
		VehiculoFactory vehiculoFactory = new VehiculoFactory();
		Vehiculo vehiculo = vehiculoFactory.crearVehiculo(vehiculoDTO.getTipoVehiculo(), vehiculoDTO.getPlaca(),
				vehiculoDTO.getCilindraje());
		Calendar cal = Calendar.getInstance();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

		Vigilante vigilante = new Vigilante(this, vehiculoServicio, tarifaServicio, calendarioVigilante);

		return vigilante.ingresarVehiculo(vehiculo);
	}

	@Override
	public TicketParqueadero retirarVehiculo(String placa, VehiculoServicio vehiculoServicio,
			TarifaServicio tarifaServicio) {
		Calendar cal = Calendar.getInstance();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

		Vigilante vigilante = new Vigilante(this, vehiculoServicio, tarifaServicio, calendarioVigilante);

		return vigilante.retirarVehiculo(placa);
	}

}
