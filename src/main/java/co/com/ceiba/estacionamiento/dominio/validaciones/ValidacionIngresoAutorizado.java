package co.com.ceiba.estacionamiento.dominio.validaciones;

import java.util.Calendar;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;

public class ValidacionIngresoAutorizado implements Validacion {

	@Override
	public void validar(Vehiculo vehiculo) {

		if (vehiculo.getPlaca().toUpperCase().endsWith("A")) {
			Calendar cal = Calendar.getInstance();
			int diaActual = cal.get(Calendar.DAY_OF_WEEK);
			if (diaActual != Calendar.SUNDAY && diaActual != Calendar.MONDAY) {
				throw new AccesoRestringidoException("No esta autorizado para ingresar");
			}
		}
	}

}