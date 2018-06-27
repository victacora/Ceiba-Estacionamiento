package co.com.ceiba.estacionamiento.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.repositorio.TicketParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.dominio.validaciones.Validacion;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionCupo;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionIngresoAutorizado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoRegistrado;

public class Vigilante {

	public static final int NUMERO_MAXIMO_CUPOS_CARRO = 20;
	public static final int NUMERO_MAXIMO_CUPOS_MOTO = 10;
	public static final int VEHICULO_NO_REGISTRADO = 0;
	List<Validacion> validaciones;

	private TicketParqueaderoRepositorio ticketParqueaderoRepositorio;

	public Vigilante(TicketParqueaderoRepositorio repositorioTicketParqueadero) {
		this.ticketParqueaderoRepositorio = repositorioTicketParqueadero;
		validaciones = new ArrayList<>();
		validaciones.add(new ValidacionCupo(this.ticketParqueaderoRepositorio));
		validaciones.add(new ValidacionIngresoAutorizado());
		validaciones.add(new ValidacionVehiculoRegistrado(this.ticketParqueaderoRepositorio));
	}

	public boolean ingresarVehiculo(Vehiculo vehiculo) {
		for (Validacion validacion : validaciones) {
			validacion.validar(vehiculo);
		}
		return ticketParqueaderoRepositorio.crearTicketParqueadero(new TicketParqueadero(new Date(), vehiculo));
	}
}
