package co.com.ceiba.estacionamiento.unitarias;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;

public class CalendarioTest {

	@Test
	public void validarEsDiaHabil() {
		// arrange
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(1, 6, 2018);
		// act
		boolean resultado = calendarioVigilante.esDiaHabil();
		// assert
		Assert.assertTrue("Dia no habil.", resultado);
	}

	@Test
	public void validarDiaNoHabil() {
		// arrange
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(3, 6, 2018);
		// act
		boolean resultado = calendarioVigilante.esDiaHabil();
		// assert
		Assert.assertFalse("Dia habil.", resultado);
	}
}
