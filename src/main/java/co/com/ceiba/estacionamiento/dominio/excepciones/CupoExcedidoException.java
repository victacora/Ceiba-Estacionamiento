package co.com.ceiba.estacionamiento.dominio.excepciones;

public class CupoExcedidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CupoExcedidoException(String message) {
		super(message);
	}
}
