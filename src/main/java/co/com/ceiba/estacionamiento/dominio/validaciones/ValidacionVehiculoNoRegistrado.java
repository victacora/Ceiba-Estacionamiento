package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;

public class ValidacionVehiculoNoRegistrado implements IValidacion {

	private ITicketParqueaderoServicio ticketParqueaderoServicio;

	public ValidacionVehiculoNoRegistrado(ITicketParqueaderoServicio repositorioTicketParqueadero) {
		this.ticketParqueaderoServicio = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (ticketParqueaderoServicio
				.verificarIngresoVehiculo(vehiculo.getPlaca()) == Vigilante.VEHICULO_NO_REGISTRADO) {
			throw new VehiculoNoRegistradoException("El vehiculo no se encuentra registrado.");
		}
	}

}
