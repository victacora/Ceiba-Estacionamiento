package co.com.ceiba.estacionamiento.dominio;

import java.util.Date;

public class TicketParqueadero {
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private Vehiculo vehiculo;

	public TicketParqueadero(Date fechaIngreso, Vehiculo vehiculo) {
		this.fechaIngreso = fechaIngreso;
		this.vehiculo = vehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
