package co.com.ceiba.estacionamiento.dominio.excepciones;

public class PaginaNoEncontradaException extends IllegalArgumentException {
	private static final long serialVersionUID = 6L;

	public PaginaNoEncontradaException(String message) {
		super(message);
	}
}
