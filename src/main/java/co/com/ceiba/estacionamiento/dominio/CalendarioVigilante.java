package co.com.ceiba.estacionamiento.dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarioVigilante {

	private int diaActual;
	private int mesActual;
	private int anioActual;
	private Calendar calendario;

	public CalendarioVigilante(int diaActual, int mesActual, int anioActual) {
		this.diaActual = diaActual;
		this.mesActual = mesActual;
		this.anioActual = anioActual;
		calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, anioActual);
		calendario.set(Calendar.DAY_OF_MONTH, diaActual);
		calendario.set(Calendar.MONTH, mesActual);
	}

	public boolean esDiaHabil() {
		return calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| calendario.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
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

	public Date getFechaActual() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder fecha = new StringBuilder();
		fecha.append(this.diaActual);
		fecha.append("/");
		fecha.append(this.mesActual);
		fecha.append("/");
		fecha.append(this.anioActual);
		try {
			return formatoFecha.parse(fecha.toString());
		} catch (ParseException e) {
			return null;
		}
	}

}
