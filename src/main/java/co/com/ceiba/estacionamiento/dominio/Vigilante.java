package co.com.ceiba.estacionamiento.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.validaciones.Validacion;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionCupo;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionPlacaAccesoRestringido;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoRegistrado;
import co.com.ceiba.estacionamiento.persistencia.repositorio.RepositorioTicketParqueadero;

public class Vigilante {

	public static final int NUMERO_MAXIMO_CUPOS_CARRO = 20;
	public static final int NUMERO_MAXIMO_CUPOS_MOTO = 10;
	List<Validacion> validaciones;

	private RepositorioTicketParqueadero repositorioTicketParqueadero;

	public Vigilante(RepositorioTicketParqueadero repositorioTicketParqueadero) {
		this.repositorioTicketParqueadero = repositorioTicketParqueadero;
		validaciones = new ArrayList<>();
		validaciones.add(new ValidacionCupo(this.repositorioTicketParqueadero));
		validaciones.add(new ValidacionPlacaAccesoRestringido());
		validaciones.add(new ValidacionVehiculoRegistrado(this.repositorioTicketParqueadero));
	}

	public TicketParqueadero ingresarVehiculo(Vehiculo vehiculo) {
		for (Validacion validacion : validaciones) {
			validacion.validar(vehiculo);
		}
		return new TicketParqueadero(new Date(), vehiculo);
	}
}
