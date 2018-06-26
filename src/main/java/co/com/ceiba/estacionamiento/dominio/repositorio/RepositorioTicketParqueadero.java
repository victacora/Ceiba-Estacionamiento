package co.com.ceiba.estacionamiento.dominio.repositorio;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;

public interface RepositorioTicketParqueadero {

	public boolean verificarCupoVehiculo(TipoVehiculo tipoVehiculo);
	public boolean verificarIngresoVehiculo(String placa);
	public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero);
	public boolean actualizarTicketParqueadero(TicketParqueadero ticketParqueadero);
	
}
