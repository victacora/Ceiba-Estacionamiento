package co.com.ceiba.estacionamiento.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.dto.TicketParqueaderoDTO;
import co.com.ceiba.estacionamiento.dominio.dto.VehiculoDTO;
import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoEncontradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.servicios.ParqueaderoServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.dominio.servicios.VehiculoServicio;

@Controller
@RequestMapping("/parqueadero")
@CrossOrigin(origins = "http://localhost:4200")

public class ParqueaderoController {

	private final Log logger = LogFactory.getLog(getClass());
	private static final long COD_OPERACION_EXITOSA = 1001l;
	private static final long COD_OPERACION_ERRONEA = 1002l;
	@Autowired
	private ParqueaderoServicio parqueaderoSevicio;

	@Autowired
	private VehiculoServicio vehiculoServicio;

	@Autowired
	private TarifaServicio tarifaServicio;

	@GetMapping(value = "/listadovehiculos")
	@ResponseBody
	public Map<String, Object> listarTodosLosTicketsParqueadero(@RequestParam int pagina, @RequestParam int tamano,
			@RequestParam String dirOrdenamiento, @RequestParam String campoOrdenamiento) {
		return parqueaderoSevicio.listarVehiculosParqueadero(pagina, tamano, dirOrdenamiento, campoOrdenamiento);
	}

	@PostMapping(value = "/ingresarvehiculo")
	@ResponseStatus(value = HttpStatus.OK)
	public void registrarIngreso(@RequestBody VehiculoDTO vehiculoDTO) {
		parqueaderoSevicio.registraringreso(vehiculoDTO, vehiculoServicio, tarifaServicio);
	}

	@PostMapping(value = "/retirarvehiculo")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody TicketParqueaderoDTO retirarvehiculo(@RequestBody String placa) {
		return parqueaderoSevicio.retirarVehiculo(placa, vehiculoServicio, tarifaServicio);
	}
}
