package co.com.ceiba.estacionamiento.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.validaciones.IValidacion;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionCupo;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionIngresoAutorizado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoRegistrado;

public class Vigilante {

	public static final int NUMERO_MAXIMO_CUPOS_CARRO = 20;
	public static final int NUMERO_MAXIMO_CUPOS_MOTO = 10;
	public static final int VEHICULO_NO_REGISTRADO = 0;
	List<IValidacion> validaciones;

	private ITicketParqueaderoServicio ticketParqueaderoServicio;

	public Vigilante(ITicketParqueaderoServicio ticketParqueaderoServicio) {
		this.ticketParqueaderoServicio = ticketParqueaderoServicio;
		validaciones = new ArrayList<>();
		validaciones.add(new ValidacionCupo(this.ticketParqueaderoServicio));
		validaciones.add(new ValidacionIngresoAutorizado());
		validaciones.add(new ValidacionVehiculoRegistrado(this.ticketParqueaderoServicio));
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {
		for (IValidacion validacion : validaciones) {
			validacion.validar(vehiculo);
		}
		return ticketParqueaderoServicio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}
}
