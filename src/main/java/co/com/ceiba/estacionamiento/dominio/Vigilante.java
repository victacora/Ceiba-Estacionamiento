package co.com.ceiba.estacionamiento.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.servicios.ITicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.IVehiculoServicio;
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
	private IVehiculoServicio vehiculoServicio;

	public Vigilante(ITicketParqueaderoServicio ticketParqueaderoServicio, IVehiculoServicio vehiculoServicio,
			List<IValidacion> validaciones) {
		this.ticketParqueaderoServicio = ticketParqueaderoServicio;
		this.vehiculoServicio = vehiculoServicio;
		if (validaciones == null) {
			this.validaciones = new ArrayList<>();
			this.validaciones.add(new ValidacionCupo(this.ticketParqueaderoServicio));
			this.validaciones.add(new ValidacionIngresoAutorizado());
			this.validaciones.add(new ValidacionVehiculoRegistrado(this.ticketParqueaderoServicio));
		}
		else{
			this.validaciones=validaciones;
		}
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {
		for (IValidacion validacion : this.validaciones) {
			validacion.validar(vehiculo);
		}

		if (vehiculoServicio.obtenerVehiculo(vehiculo.getPlaca()) == null) {
			vehiculoServicio.crearVehiculo(vehiculo);
		}

		return ticketParqueaderoServicio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}
}
