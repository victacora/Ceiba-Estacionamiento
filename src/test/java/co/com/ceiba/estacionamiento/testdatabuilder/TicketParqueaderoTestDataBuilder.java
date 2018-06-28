package co.com.ceiba.estacionamiento.testdatabuilder;

import java.util.Date;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class TicketParqueaderoTestDataBuilder {

	private Date fechaIngreso;
	private Date fechaSalida;
	private double valor;
	private Vehiculo vehiculo;
	
	public TicketParqueaderoTestDataBuilder withFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso=fechaIngreso;
		return this;
	}
	
	public TicketParqueaderoTestDataBuilder withFechaSalida(Date fechaSalida) {
		this.fechaSalida=fechaSalida;
		return this;
	}
	
	public TicketParqueaderoTestDataBuilder withValor(double valor) {
		this.valor=valor;
		return this;
	}
	
	public TicketParqueaderoTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo=vehiculo;
		return this;
	}
	
	public TicketParqueadero build()
	{
		return new TicketParqueadero(this.fechaIngreso,this.fechaSalida,this.valor,this.vehiculo);
	}
	
	
}
