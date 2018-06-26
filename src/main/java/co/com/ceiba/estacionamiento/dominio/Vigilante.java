package co.com.ceiba.estacionamiento.dominio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.estacionamiento.dominio.validaciones.Validacion;

public class Vigilante {

	 public static final int NUMERO_MAXIMO_CUPOS_CARRO=20;
	 public static final int NUMERO_MAXIMO_CUPOS_MOTO=10;
	 
	public TicketParqueadero ingresarVehiculo(Vehiculo vehiculo, List<Validacion> validaciones) {
		
		return new TicketParqueadero(new Date(), vehiculo);
	}
}
