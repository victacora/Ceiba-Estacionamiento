package co.com.ceiba.estacionamiento.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.servicios.TicketParqueaderoServicio;

@Controller
@RequestMapping("/parqueadero")
public class ParqueaderoController {

	@Autowired
	TicketParqueaderoServicio ticketParqueaderoSevicio;

	//@Autowired
	//private ModelMapper modelMapper;

	@GetMapping(value = "/ticketsparqueadero", params = { "pagina", "size", "tamano", "dirOrdenamiento",
			"campoOrdaniento" })
	@ResponseBody
	public List<TicketParqueadero> listarTodosLosTicketsParqueadero(@RequestParam("pagina") int pagina,
			@RequestParam("tamano") int tamano, @RequestParam("dirOrdenamiento") String dirOrdenamiento,
			@RequestParam("campoOrdaniento") String campoOrdaniento) {
		return ticketParqueaderoSevicio.listarTodosLosTicketsParqueadero(pagina, tamano, dirOrdenamiento,
				campoOrdaniento);
	}
}
