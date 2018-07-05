package co.com.ceiba.estacionamiento.dominio.excepciones;

public class CupoExcedidoException extends RuntimeException  {
	private static final long serialVersionUID = 200L;
	
	public CupoExcedidoException(String message) {
		super(message);
	}
	
	public long getCodigoError(){
		return serialVersionUID;
	}
}
