package co.com.ceiba.estacionamiento.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
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
	public @ResponseBody Map<Long, String> registrarIngreso(@RequestBody VehiculoDTO vehiculoDTO) {
		Map<Long, String> resultado = new HashMap<>();
		try {
			boolean vehiculoRegistradoCorrectamente = parqueaderoSevicio.registraringreso(vehiculoDTO, vehiculoServicio,
					tarifaServicio);
			if (vehiculoRegistradoCorrectamente) {
				resultado.put(COD_OPERACION_EXITOSA,
						"Entrada registrada, para vehiculo con placa " + vehiculoDTO.getPlaca() + ".");
			} else {

				resultado.put(COD_OPERACION_ERRONEA, "No se pudo registrar el vehiculo.");
			}
		} catch (CupoExcedidoException e) {
			resultado.put(e.getCodigoError(), e.getMessage());
			logger.error(e);
		} catch (AccesoRestringidoException e) {
			resultado.put(e.getCodigoError(), e.getMessage());
			logger.error(e);
		} catch (VehiculoRegistradoException e) {
			resultado.put(e.getCodigoError(), e.getMessage());
			logger.error(e);
		}
		return resultado;
	}

	@PostMapping(value = "/retirarvehiculo")
	public @ResponseBody Map<Long, String> retirarvehiculo(@RequestBody String placa) {
		Map<Long, String> resultado = new HashMap<>();
		try {
			TicketParqueadero ticketParqueadero = parqueaderoSevicio.retirarVehiculo(placa, vehiculoServicio,
					tarifaServicio);
			if (ticketParqueadero != null) {
				resultado.put(COD_OPERACION_EXITOSA, "Salida registrada, para vehiculo con placa " + placa + ".");
			} else {
				resultado.put(COD_OPERACION_ERRONEA, "No se pudo retirar el vehiculo.");
			}
		} catch (VehiculoNoEncontradoException e) {
			resultado.put(e.getCodigoError(), e.getMessage());
			logger.error(e);
		} catch (VehiculoNoRegistradoException e) {
			resultado.put(e.getCodigoError(), e.getMessage());
			logger.error(e);
		}
		return resultado;
	}
}
