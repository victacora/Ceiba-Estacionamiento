package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.dominio.Carro;

public class CarroTestDataBuilder {

	private String placa;
	
	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public Carro build()
	{
		return new Carro(this.placa);
	}
	
	
}
