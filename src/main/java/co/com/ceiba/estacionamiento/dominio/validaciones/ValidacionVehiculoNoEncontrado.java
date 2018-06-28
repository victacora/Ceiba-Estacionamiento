package co.com.ceiba.estacionamiento.dominio.validaciones;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.servicios.IVehiculoServicio;

public class ValidacionVehiculoNoEncontrado implements IValidacion {

	private IVehiculoServicio vehiculoServicio;

	public ValidacionVehiculoNoEncontrado(IVehiculoServicio vehiculoServicio) {
		this.vehiculoServicio = vehiculoServicio;
	}

	@Override
	public void validar(Vehiculo vehiculo) {
		if (vehiculoServicio.obtenerVehiculo(vehiculo.getPlaca()) == null) {
			throw new VehiculoRegistradoException("El vehiculo no existe.");
		}
	}

}
