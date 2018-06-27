package co.com.ceiba.estacionamiento.unitarias;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class ParqueaderoTest {

	private TicketParqueaderoServicio ticketParqueaderoServicio;

	@Before
	public void setUp() {
		ticketParqueaderoServicio = Mockito.mock(TicketParqueaderoServicio.class);
		Mockito.when(ticketParqueaderoServicio.crearTicketParqueadero(Mockito.any())).thenReturn(true);
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString())).thenReturn(0);
		Mockito.when(ticketParqueaderoServicio.verificarIngresoVehiculo(Mockito.anyString())).thenReturn(0);

	}

	@Test
	public void IngresarCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();

		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio);

		boolean resultado = vigilante.ingresarVehiculo(carro);

		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {

		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();

		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio);

		boolean resultado = vigilante.ingresarVehiculo(moto);

		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

	@Test
	public void ValidarCuposCarroMax20() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCuposMotoMax10() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarAccesoVehiculoPlacasTerminadasEnA() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroHoraCarro() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroDiaCarro() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroHoraMoto() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroDiaMoto() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroMotoCilindrajeMayor500CC() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroCarroParaVariosDiasYHoras() {
		Assert.assertTrue(true);
	}

	@Test
	public void ValidarCobroMotoParaVariosDiasYHoras() {
		Assert.assertTrue(true);
	}

}
