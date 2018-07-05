package co.com.ceiba.estacionamiento.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;

@Controller
@RequestMapping("/parqueadero")
public class ParqueaderoController {

	@Autowired
	ParqueaderoServicio parqueaderoSevicio;

	@GetMapping(value = "/ticketsparqueadero", params = { "pagina", "tamano", "dirOrdenamiento", "campoOrdenamiento" })
	@ResponseBody
	public List<TicketParqueaderoDTO> listarTodosLosTicketsParqueadero(@RequestParam("pagina") int pagina,
			@RequestParam("tamano") int tamano, @RequestParam("dirOrdenamiento") String dirOrdenamiento,
			@RequestParam("campoOrdenamiento") String campoOrdenamiento) {
		return parqueaderoSevicio.listarTicketsParqueadero(pagina,
				tamano, dirOrdenamiento, campoOrdenamiento);
	}

}
