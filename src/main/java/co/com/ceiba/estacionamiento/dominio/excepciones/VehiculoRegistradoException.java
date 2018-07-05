package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoRegistradoException extends IllegalArgumentException {
	private static final long serialVersionUID = 600L;
	
	public VehiculoRegistradoException(String message) {
		super(message);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
