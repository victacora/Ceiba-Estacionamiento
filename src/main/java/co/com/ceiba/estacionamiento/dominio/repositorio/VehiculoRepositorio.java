package co.com.ceiba.estacionamiento.dominio.repositorio;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public interface VehiculoRepositorio {

	public boolean crearVehiculo(Vehiculo vehiculo);
	
	public Vehiculo obtenerVehiculo(String placa);
}
