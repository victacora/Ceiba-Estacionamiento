package co.com.ceiba.estacionamiento.dominio.excepciones;

public class PlacaAccesoRestringidoException extends RuntimeException {
	private static final long serialVersionUID = 2L;
	
	public PlacaAccesoRestringidoException(String message) {
		super(message);
	}
}
