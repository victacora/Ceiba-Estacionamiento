package co.com.ceiba.estacionamiento.dominio.servicios;

import java.util.List;
import java.util.Map;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.dto.VehiculoDTO;

public interface ParqueaderoServicio {

	public Integer verificarCupoVehiculo(String tipoVehiculo);

	public Integer verificarIngresoVehiculo(String placa);

	public boolean crearTicketParqueadero(TicketParqueadero ticketParqueadero);

	public TicketParqueadero actualizarTicketParqueadero(TicketParqueadero ticketParqueadero);

	public TicketParqueadero obtenerTicketParquedero(String placa);

	public Map<String, Object> listarVehiculosParqueadero(int pagina, int tamano, String dirOrdenamiento,
			String campoOrdenamiento);

	public boolean registraringreso(VehiculoDTO vehiculo, VehiculoServicio vehiculoServicio,
			TarifaServicio tarifaServicio);

	public TicketParqueadero retirarVehiculo(String placa, VehiculoServicio vehiculoServicio,
			TarifaServicio tarifaServicio);

}
