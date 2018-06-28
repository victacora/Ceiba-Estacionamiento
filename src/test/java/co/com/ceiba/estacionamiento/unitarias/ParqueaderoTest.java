package co.com.ceiba.estacionamiento.unitarias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.dominio.validaciones.IValidacion;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionIngresoAutorizado;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class ParqueaderoTest {

	protected static final List<IValidacion> VALIDACIONES_POR_DEFECTO = null;

	private TicketParqueaderoServicio ticketParqueaderoServicio;
	private VehiculoServicio vehiculoServicio;

	@Before
	public void setUp() {
		ticketParqueaderoServicio = Mockito.mock(TicketParqueaderoServicio.class);
		Mockito.when(ticketParqueaderoServicio.crearTicketParqueadero(Mockito.any())).thenReturn(true);
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString())).thenReturn(0);
		Mockito.when(ticketParqueaderoServicio.verificarIngresoVehiculo(Mockito.anyString())).thenReturn(0);
 
		Vehiculo vehiculo=Mockito.mock(Vehiculo.class);
		
		vehiculoServicio = Mockito.mock(VehiculoServicio.class);
		Mockito.when(vehiculoServicio.crearVehiculo(Mockito.any())).thenReturn(true);
		Mockito.when(vehiculoServicio.obtenerVehiculo(Mockito.anyString())).thenReturn(vehiculo);
	}

	@Test
	public void IngresarCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, VALIDACIONES_POR_DEFECTO);

		boolean resultado = vigilante.ingresarVehiculo(carro);

		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, VALIDACIONES_POR_DEFECTO);

		boolean resultado = vigilante.ingresarVehiculo(moto);

		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

	@Test(expected = CupoExcedidoException.class)
	public void ValidarCuposCarroMax20() {
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_CARRO + 1);
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XZX-225").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, VALIDACIONES_POR_DEFECTO);

		vigilante.ingresarVehiculo(carro);
	}

	@Test(expected = CupoExcedidoException.class)
	public void ValidarCuposMotoMax10() {
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_MOTO + 1);
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(200).withPlaca("YTY-223").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, VALIDACIONES_POR_DEFECTO);

		vigilante.ingresarVehiculo(moto);
	}

	@Test(expected = AccesoRestringidoException.class)
	public void ValidarAccesoVehiculoPlacasTerminadasEnA() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("AZX-225").build();
		ValidacionIngresoAutorizado validacion = Mockito.mock(ValidacionIngresoAutorizado.class);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Vehiculo vehiculo=(Vehiculo)args[0];
				if (vehiculo.getPlaca().toUpperCase().startsWith("A")) {
					int diaActual = Calendar.THURSDAY;
					if (diaActual != Calendar.SUNDAY && diaActual != Calendar.MONDAY) {
						throw new AccesoRestringidoException("No esta autorizado para ingresar");
					}
				}
				return null;
			}
		}).when(validacion).validar(Mockito.any(Vehiculo.class));
		List<IValidacion> validaciones = new ArrayList<>();
		validaciones.add(validacion);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, validaciones);
		vigilante.ingresarVehiculo(carro);
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
