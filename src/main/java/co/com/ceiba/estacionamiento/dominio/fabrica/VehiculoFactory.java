package co.com.ceiba.estacionamiento.dominio.fabrica;

import co.com.ceiba.estacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;

public class VehiculoFactory {

	public Vehiculo crearVehiculo(String tipoVehiculo, String placa, double cilindraje) {
		Vehiculo vehiculo = null;
		if (tipoVehiculo == TipoVehiculo.CARRO.name()) {

			vehiculo = new Carro(placa);
		} else {
			vehiculo = new Moto(placa, cilindraje);
		}
		return vehiculo;
	}
}
