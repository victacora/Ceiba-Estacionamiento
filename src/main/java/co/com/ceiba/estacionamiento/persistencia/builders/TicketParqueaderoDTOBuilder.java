package co.com.ceiba.estacionamiento.persistencia.builders;

import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.persistencia.entidades.TicketParqueaderoEntity;

public final class TicketParqueaderoDTOBuilder {

	private static ModelMapper modelMapper = new ModelMapper();

	private TicketParqueaderoDTOBuilder() {
		throw new IllegalStateException("Clase utilidad");
	}

	public static TicketParqueaderoDTO convertirADTO(TicketParqueaderoEntity ticketParqueadero) {

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

		return modelMapper.map(ticketParqueadero, TicketParqueaderoDTO.class);
	}
}