package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoRegistradoException extends RuntimeException {
	private static final long serialVersionUID = 3L;
	
	public VehiculoRegistradoException(String message) {
		super(message);
	}
}