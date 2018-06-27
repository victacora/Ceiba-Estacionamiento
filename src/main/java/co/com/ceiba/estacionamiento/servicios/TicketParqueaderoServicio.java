package co.com.ceiba.estacionamiento.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.persistencia.builders.TicketParqueaderoBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;
import co.com.ceiba.estacionamiento.persistencia.repositorio.jpa.ITicketParqueaderoRepositorio;

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

}
