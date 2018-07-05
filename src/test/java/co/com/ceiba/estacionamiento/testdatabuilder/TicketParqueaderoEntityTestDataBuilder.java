package co.com.ceiba.estacionamiento.testdatabuilder;

import java.util.Date;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;

public class TicketParqueaderoEntityTestDataBuilder {
	private long id;
	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private VehiculoEntity vehiculo;

	public TicketParqueaderoEntityTestDataBuilder withId(long id) {
		this.id = id;
		return this;
	}
	
	public TicketParqueaderoEntityTestDataBuilder withFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public TicketParqueaderoEntityTestDataBuilder withFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public TicketParqueaderoEntityTestDataBuilder withValor(double valor) {
		this.valor = valor;
		return this;
	}

	public TicketParqueaderoEntityTestDataBuilder withVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public TicketParqueaderoEntity build() {
		TicketParqueaderoEntity ticketParqueaderoEntity=new TicketParqueaderoEntity();
		ticketParqueaderoEntity.setId(this.id);
		ticketParqueaderoEntity.setFechaIngreso(this.fechaIngreso);
		ticketParqueaderoEntity.setFechaSalida(this.fechaSalida);
		ticketParqueaderoEntity.setValor(this.valor);
		ticketParqueaderoEntity.setVehiculo(this.vehiculo);
		return ticketParqueaderoEntity;
	}

	
}
