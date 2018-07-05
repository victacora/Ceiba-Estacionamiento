package co.com.ceiba.estacionamiento.servicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.excepciones.PaginaNoEncontradaException;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;
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
	public List<TicketParqueaderoDTO> listarTicketsParqueadero(int pagina, int tamano, String dirOrdenamiento,
			String campoOrdenamiento) {

		PageRequest pageReq = PageRequest.of(pagina, tamano, Sort.Direction.fromString(dirOrdenamiento),
				campoOrdenamiento);
		Page<TicketParqueaderoEntity> ticketParqueaderoPage = ticketParqueaderoRepositorio.findAll(pageReq);

		if (pagina > ticketParqueaderoPage.getTotalPages()) {
			throw new PaginaNoEncontradaException("El numero de pagina consultado es erroneo.");
		}

		return ticketParqueaderoPage.getContent().stream().map(TicketParqueaderoDTOBuilder::convertirADTO).collect(Collectors.toList());
	}

	
}
