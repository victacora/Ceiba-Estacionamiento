package co.com.ceiba.estacionamiento.dominio.excepciones;

public class VehiculoNoEncontradoException extends IllegalArgumentException {
	private static final long serialVersionUID = 400L;
	
	public VehiculoNoEncontradoException(String message) {
		super(message);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
