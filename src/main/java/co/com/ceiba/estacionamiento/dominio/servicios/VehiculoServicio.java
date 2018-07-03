package co.com.ceiba.estacionamiento.dominio.servicios;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public interface VehiculoServicio {

	public boolean guardarVehiculo(Vehiculo vehiculo);
	
	public Vehiculo obtenerVehiculo(String placa);
	
}
