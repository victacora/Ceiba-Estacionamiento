package co.com.ceiba.estacionamiento.dominio;

import java.util.Date;

public class TicketParqueadero {
	private Date fechaIngreso;
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
