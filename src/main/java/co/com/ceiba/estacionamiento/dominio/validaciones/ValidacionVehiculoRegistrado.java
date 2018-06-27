package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.repositorio.TicketParqueaderoRepositorio;

public class ValidacionVehiculoRegistrado implements Validacion {

	private TicketParqueaderoRepositorio repositorioTicketParqueadero;

	public ValidacionVehiculoRegistrado(TicketParqueaderoRepositorio repositorioTicketParqueadero) {
		this.repositorioTicketParqueadero = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (repositorioTicketParqueadero
				.verificarIngresoVehiculo(vehiculo.getPlaca()) != Vigilante.VEHICULO_NO_REGISTRADO) {
			throw new VehiculoRegistradoException("El vehiculo ya se encuentra registrado");
		}
	}

}
