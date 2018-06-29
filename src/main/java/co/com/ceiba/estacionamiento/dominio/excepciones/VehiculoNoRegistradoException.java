package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoNoRegistradoException extends IllegalArgumentException {
	private static final long serialVersionUID = 4L;
	
	public VehiculoNoRegistradoException(String message) {
		super(message);
	}
}
