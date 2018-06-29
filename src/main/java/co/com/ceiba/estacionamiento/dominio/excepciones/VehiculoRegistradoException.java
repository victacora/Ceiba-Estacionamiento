package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoRegistradoException extends IllegalArgumentException {
	private static final long serialVersionUID = 5L;
	
	public VehiculoRegistradoException(String message) {
		super(message);
	}
}
