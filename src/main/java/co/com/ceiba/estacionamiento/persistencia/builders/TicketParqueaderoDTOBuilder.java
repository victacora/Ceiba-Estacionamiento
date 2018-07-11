package co.com.ceiba.estacionamiento.persistencia.builders;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;

public final class TicketParqueaderoDTOBuilder {

	private ModelMapper modelMapper = new ModelMapper();
	
	public TicketParqueaderoDTOBuilder() {
		PropertyMap<TicketParqueaderoEntity, TicketParqueaderoDTO> ticketParqueaderoMap = new PropertyMap<TicketParqueaderoEntity, TicketParqueaderoDTO>() {

			protected void configure() {
				map().setFechaIngreso(source.getFechaIngreso());
				map().setFechaSalida(source.getFechaSalida());
				map().setPlaca(source.getVehiculo().getPlaca());
				map().setTipoVehiculo(source.getVehiculo().getTipoVehiculo());
				map().setCilindraje(source.getVehiculo().getCilindraje());
			}
		};

		modelMapper.addMappings(ticketParqueaderoMap);
	}

	public TicketParqueaderoDTO convertirADTO(TicketParqueaderoEntity ticketParqueadero) {
		return modelMapper.map(ticketParqueadero, TicketParqueaderoDTO.class);
	}

	public TicketParqueaderoDTO convertirADTO(TicketParqueadero ticketParqueadero) {
		return modelMapper.map(ticketParqueadero, TicketParqueaderoDTO.class);
	}
}