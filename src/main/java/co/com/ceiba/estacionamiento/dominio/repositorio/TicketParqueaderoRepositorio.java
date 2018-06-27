package co.com.ceiba.estacionamiento.dominio.repositorio;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;

public interface TicketParqueaderoRepositorio {

		public Integer verificarCupoVehiculo(String tipoVehiculo);
	
		public Integer verificarIngresoVehiculo(String placa);
		
		public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero);
		
}
