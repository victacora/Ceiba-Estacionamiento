package co.com.ceiba.estacionamiento.dominio;

import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;

public class Vehiculo {

	private String placa;
	private double cilindraje;
	private TipoVehiculo tipoVehiculo;

	public Vehiculo(String placa, double cilindraje, TipoVehiculo tipoVehiculo) {
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
