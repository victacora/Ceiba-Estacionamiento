package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.persistencia.repositorio.RepositorioTicketParqueadero;

public class ValidacionCupo implements Validacion {

	private RepositorioTicketParqueadero repositorioTicketParqueadero;

	public ValidacionCupo(RepositorioTicketParqueadero repositorioTicketParqueadero) {
		this.repositorioTicketParqueadero = repositorioTicketParqueadero;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (repositorioTicketParqueadero.verificarCupoVehiculo(vehiculo.getTipoVehiculo())) {
			throw new CupoExcedidoException("No hay cupos disponibles");
		}
	}

}
