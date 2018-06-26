package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;

public class VehiculoTestDataBuilder {

	private String placa;
	private double cilindraje;
	private TipoVehiculo tipoVehiculo;
	
	

	public VehiculoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder withCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoTestDataBuilder withTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public Vehiculo build()
	{
		return new Vehiculo(this.placa,this.cilindraje,this.tipoVehiculo);
	}
	
	
}
