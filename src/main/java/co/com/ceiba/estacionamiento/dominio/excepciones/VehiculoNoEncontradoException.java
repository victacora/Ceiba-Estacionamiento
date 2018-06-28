package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoNoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 3L;
	
	public VehiculoNoEncontradoException(String message) {
		super(message);
	}
}
