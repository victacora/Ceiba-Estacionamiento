package co.com.ceiba.estacionamiento.dominio.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketParqueaderoDTO {

	private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private long id;

	private String fechaIngreso;

	private String fechaSalida;

	private double valor;

	private String placa;

	private String tipoVehiculo;

	private double cilindraje;

	public TicketParqueaderoDTO() {

	}

	public TicketParqueaderoDTO(long id, String fechaIngreso, String fechaSalida, double valor, String placa,
			String tipoVehiculo, double cilindraje) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = formatoFecha.format(fechaIngreso);
	}

	public void setFechaSalida(Date fechaSalida) {
		if (fechaSalida != null) {
			this.fechaSalida = formatoFecha.format(fechaSalida);
		}
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
