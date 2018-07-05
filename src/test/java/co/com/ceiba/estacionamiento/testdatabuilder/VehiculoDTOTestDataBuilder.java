package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.dominio.dto.VehiculoDTO;

public class VehiculoDTOTestDataBuilder {

	private String placa;
	private String tipoVehiculo;
	private double cilindraje;

	public VehiculoDTOTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoDTOTestDataBuilder withTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public VehiculoDTOTestDataBuilder withCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoDTO build()
	{
		VehiculoDTO vehiculoDTO=new VehiculoDTO();
		vehiculoDTO.setPlaca(this.placa);
		vehiculoDTO.setCilindraje(this.cilindraje);
		vehiculoDTO.setTipoVehiculo(this.tipoVehiculo);
		return vehiculoDTO;
	}
	
	
}
