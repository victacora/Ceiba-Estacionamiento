package co.com.ceiba.estacionamiento.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoEncontradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoNoRegistradoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.VehiculoRegistradoException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleCupoExcedidoException(CupoExcedidoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleAccesoRestringidoException(AccesoRestringidoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleVehiculoRegistradoException(VehiculoRegistradoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleVehiculoNoEncontradoException(VehiculoNoEncontradoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleVehiculoNoRegistradoException(VehiculoNoRegistradoException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
