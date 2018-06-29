package co.com.ceiba.estacionamiento.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.persistencia.builders.TicketParqueaderoBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;
import co.com.ceiba.estacionamiento.persistencia.repositorio.ITicketParqueaderoRepositorio;

@Service
public class TicketParqueaderoServicio implements ITicketParqueaderoServicio {

	@Autowired
	private ITicketParqueaderoRepositorio ticketParqueaderoRepositorio;

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
			resultado=TicketParqueaderoBuilder.convertirADominio(actualizarTicketParqueadero);
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

}
