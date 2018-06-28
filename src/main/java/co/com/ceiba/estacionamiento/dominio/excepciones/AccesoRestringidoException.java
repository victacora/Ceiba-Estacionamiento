package co.com.ceiba.estacionamiento.dominio.excepciones;

public class AccesoRestringidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AccesoRestringidoException(String message) {
		super(message);
	}
}
