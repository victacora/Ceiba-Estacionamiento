package co.com.ceiba.estacionamiento.dominio;

public class Moto extends Vehiculo {
	private double cilindraje;
	
	public Moto(String placa, double cilindraje) {
		super(placa);
		this.cilindraje=cilindraje;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}


	
}
