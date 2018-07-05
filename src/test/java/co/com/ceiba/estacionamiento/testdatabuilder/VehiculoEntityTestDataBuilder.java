package co.com.ceiba.estacionamiento.testdatabuilder;

import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

public class VehiculoEntityTestDataBuilder {

	private String placa;
	private String tipoVehiculo;
	private double cilindraje;

	public VehiculoEntityTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoEntityTestDataBuilder withTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public VehiculoEntityTestDataBuilder withCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoEntity build() {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(this.placa);
		vehiculoEntity.setTipoVehiculo(this.tipoVehiculo);
		vehiculoEntity.setCilindraje(this.cilindraje);
		return vehiculoEntity;
	}

}
