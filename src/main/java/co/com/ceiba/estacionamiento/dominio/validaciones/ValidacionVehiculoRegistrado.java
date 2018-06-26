package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.persistencia.repositorio.RepositorioTicketParqueadero;

public class ValidacionVehiculoRegistrado implements Validacion {

	private RepositorioTicketParqueadero repositorioTicketParqueadero;
	
	public ValidacionVehiculoRegistrado(RepositorioTicketParqueadero repositorioTicketParqueadero) {
		this.repositorioTicketParqueadero = repositorioTicketParqueadero;
	}
	
	@Override
	public void validar(Vehiculo vehiculo) {
		if (repositorioTicketParqueadero.verificarIngresoVehiculo(vehiculo.getPlaca())) {
			throw new VehiculoRegistradoException("El vehiculo ya se encuentra registrado");
		}
	}

}
