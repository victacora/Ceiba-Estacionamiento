package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.dominio.Moto;

public class MotoTestDataBuilder {

	private String placa;
	private double cilindraje;
	

	public MotoTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder withCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	
	public Moto build()
	{
		return new Moto(this.placa,this.cilindraje);
	}
	
	
}
