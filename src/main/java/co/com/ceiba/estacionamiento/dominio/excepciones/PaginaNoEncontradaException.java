package co.com.ceiba.estacionamiento.dominio.excepciones;

public class PaginaNoEncontradaException extends RuntimeException {
private static final long serialVersionUID = 300L;
	
	public PaginaNoEncontradaException(String message) {
		super(message);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
