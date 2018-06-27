package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;

public class ValidacionVehiculoRegistrado implements IValidacion {

	private ITicketParqueaderoServicio ticketParqueaderoServicio;

	public ValidacionVehiculoRegistrado(ITicketParqueaderoServicio repositorioTicketParqueadero) {
		this.ticketParqueaderoServicio = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (ticketParqueaderoServicio
				.verificarIngresoVehiculo(vehiculo.getPlaca()) != Vigilante.VEHICULO_NO_REGISTRADO) {
			throw new VehiculoRegistradoException("El vehiculo ya se encuentra registrado");
		}
	}

}
