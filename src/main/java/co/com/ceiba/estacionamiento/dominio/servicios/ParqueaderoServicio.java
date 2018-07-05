package co.com.ceiba.estacionamiento.dominio.servicios;


import java.util.List;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;

public interface ParqueaderoServicio {

		public Integer verificarCupoVehiculo(String tipoVehiculo);
	
		public Integer verificarIngresoVehiculo(String placa);
		
		public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero);
		
		public TicketParqueadero actualizarTicketParqueadero(TicketParqueadero ticketParqueadero);

		public TicketParqueadero obtenerTicketParquedero(String placa);

		public List<TicketParqueaderoDTO> listarTicketsParqueadero(int pagina, int tamano, String dirOrdenamiento, String campoOrdenamiento);

}
