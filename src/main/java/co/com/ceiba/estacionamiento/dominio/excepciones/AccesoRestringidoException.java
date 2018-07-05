package co.com.ceiba.estacionamiento.dominio.excepciones;

public class AccesoRestringidoException extends RuntimeException {
	private static final long serialVersionUID = 100L;

	public AccesoRestringidoException(String message) {
		super(message);
	}

	public long getCodigoError() {
		return serialVersionUID;
	}
}
