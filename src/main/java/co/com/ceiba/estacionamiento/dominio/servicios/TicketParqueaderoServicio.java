package co.com.ceiba.estacionamiento.dominio.servicios;


import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;

public interface TicketParqueaderoServicio {

		public Integer verificarCupoVehiculo(String tipoVehiculo);
	
		public Integer verificarIngresoVehiculo(String placa);
		
		public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero);
		
		public TicketParqueadero actualizarTicketParqueadero(TicketParqueadero ticketParqueadero);

		public TicketParqueadero obtenerTicketParquedero(String placa);

}
