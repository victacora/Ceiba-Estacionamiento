package co.com.ceiba.estacionamiento.dominio.servicios;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public interface IVehiculoServicio {

	public boolean crearVehiculo(Vehiculo vehiculo);
	
	public Vehiculo obtenerVehiculo(String placa);
}
