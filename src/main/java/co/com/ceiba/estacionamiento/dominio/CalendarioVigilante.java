package co.com.ceiba.estacionamiento.dominio;

import java.util.Calendar;

public class CalendarioVigilante {

	private int diaActual;
	private int mesActual;
	private int anioActual;
	
	public CalendarioVigilante(Integer diaActual,Integer mesActual, Integer anioActual)
	{
		Calendar cal=Calendar.getInstance();
		this.diaActual = diaActual==null?cal.get(Calendar.DAY_OF_WEEK):diaActual;
		this.mesActual = mesActual==null?cal.get(Calendar.DAY_OF_WEEK):mesActual;
		this.anioActual = anioActual==null?cal.get(Calendar.DAY_OF_WEEK):anioActual;
	}
		
	public boolean esDiaHabil() {
		return diaActual == Calendar.SUNDAY || diaActual == Calendar.MONDAY;
	}

	public int getDiaActual() {
		return diaActual;
	}

	public int getMesActual() {
		return mesActual;
	}

	public int getAnioActual() {
		return anioActual;
	}
	
	

}
