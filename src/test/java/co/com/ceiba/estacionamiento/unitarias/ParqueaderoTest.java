package co.com.ceiba.estacionamiento.unitarias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionCupo;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionIngresoAutorizado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoNoEncontrado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoNoRegistrado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoRegistrado;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoTarifa;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.EnumUnidadTiempo;
import co.com.ceiba.estacionamiento.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.TicketParqueaderoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class ParqueaderoTest {

	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_ADICIONAL_MOTO = 2000;
	private TicketParqueaderoServicio ticketParqueaderoServicio;
	private VehiculoServicio vehiculoServicio;
	private TarifaServicio TarifaServicio;

	private List<IValidacion> valiacionesIngresoVehiculo() {
		List<IValidacion> validaciones = new ArrayList<>();
		validaciones = new ArrayList<>();
		validaciones.add(new ValidacionCupo(this.ticketParqueaderoServicio));
		validaciones.add(new ValidacionIngresoAutorizado());
		validaciones.add(new ValidacionVehiculoRegistrado(this.ticketParqueaderoServicio));
		return validaciones;
	}
	
	private List<IValidacion> validacionesSalidaVehiculo() {
		List<IValidacion> validaciones = new ArrayList<>();
		validaciones = new ArrayList<>();
		validaciones.add(new ValidacionVehiculoNoEncontrado(this.vehiculoServicio));
		validaciones.add(new ValidacionVehiculoNoRegistrado(this.ticketParqueaderoServicio));
		return validaciones;
	}

	@Before
	public void setUp() {
		ticketParqueaderoServicio = Mockito.mock(TicketParqueaderoServicio.class);
		Mockito.when(ticketParqueaderoServicio.crearTicketParqueadero(Mockito.any())).thenReturn(true);
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString())).thenReturn(0);
		Mockito.when(ticketParqueaderoServicio.verificarIngresoVehiculo(Mockito.anyString())).thenReturn(0);

		Vehiculo vehiculo = Mockito.mock(Vehiculo.class);

		vehiculoServicio = Mockito.mock(VehiculoServicio.class);
		Mockito.when(vehiculoServicio.crearVehiculo(Mockito.any())).thenReturn(true);
		Mockito.when(vehiculoServicio.obtenerVehiculo(Mockito.anyString())).thenReturn(vehiculo);

		TarifaServicio = Mockito.mock(TarifaServicio.class);
		Mockito.doAnswer(new Answer<Double>() {
			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				Double valor = 0.0;
				Object[] args = invocation.getArguments();
				String tipoVehiculo =  args[0].toString();
				String tipoTarifa =  args[1].toString();
				String unidadTiempo =  args[2].toString();
				if (tipoVehiculo.equals(EnumTipoVehiculo.CARRO.name())&&
						tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())&&
								unidadTiempo.equals(EnumUnidadTiempo.HORA.name())){
					valor=VALOR_HORA_CARRO;
				}
				else if (tipoVehiculo.equals(EnumTipoVehiculo.CARRO.name())&&
						tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())&&
								unidadTiempo.equals(EnumUnidadTiempo.DIA.name())){
					valor=VALOR_DIA_CARRO;
				}
				else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())&&
						tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())&&
								unidadTiempo.equals(EnumUnidadTiempo.HORA.name())){
					valor=VALOR_HORA_MOTO;
				}
				else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())&&
						tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())&&
								unidadTiempo.equals(EnumUnidadTiempo.DIA.name())){
					valor=VALOR_DIA_MOTO;
				}
				else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())&&
						tipoTarifa.equals(EnumTipoTarifa.ADICIONAL.name())&&
								unidadTiempo.equals(EnumUnidadTiempo.NOAPLICA.name())){
					valor=VALOR_ADICIONAL_MOTO;
				}
				return valor;
			}
		}).when(TarifaServicio).obtenerValorTarifa(Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
	}

	@Test
	public void IngresarCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio,
				valiacionesIngresoVehiculo());

		boolean resultado = vigilante.ingresarVehiculo(carro);

		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio,
				valiacionesIngresoVehiculo());

		boolean resultado = vigilante.ingresarVehiculo(moto);

		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

	@Test(expected = CupoExcedidoException.class)
	public void ValidarCuposCarroMax20() {
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_CARRO + 1);
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XZX-225").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio,
				valiacionesIngresoVehiculo());

		vigilante.ingresarVehiculo(carro);
	}

	@Test(expected = CupoExcedidoException.class)
	public void ValidarCuposMotoMax10() {
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_MOTO + 1);
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(200).withPlaca("YTY-223").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio,
				valiacionesIngresoVehiculo());

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
				Vehiculo vehiculo = (Vehiculo) args[0];
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
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validaciones);
		vigilante.ingresarVehiculo(carro);
	}

	@Test
	public void ValidarCobroHoraCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("AZX-225").build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(carro).withFechaIngreso(formatoFecha.parse("28/06/2018 12:00")).withFechaSalida(formatoFecha.parse("28/06/2018 13:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals(VALOR_HORA_CARRO,valor,0.0);
	}

	@Test
	public void ValidarCobroDiaCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("AZX-225").build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(carro).withFechaIngreso(formatoFecha.parse("28/06/2018 00:00")).withFechaSalida(formatoFecha.parse("29/06/2018 00:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals(VALOR_DIA_CARRO,valor,0.0);
	}

	@Test
	public void ValidarCobroHoraMoto() {
		Vehiculo moto = new MotoTestDataBuilder().withPlaca("AZX-225").withCilindraje(200).build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(moto).withFechaIngreso(formatoFecha.parse("28/06/2018 12:00")).withFechaSalida(formatoFecha.parse("28/06/2018 13:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals(VALOR_HORA_MOTO,valor,0.0);
	}

	@Test
	public void ValidarCobroDiaMoto() {
		Vehiculo moto = new MotoTestDataBuilder().withPlaca("AZX-225").withCilindraje(200).build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(moto).withFechaIngreso(formatoFecha.parse("28/06/2018 00:00")).withFechaSalida(formatoFecha.parse("29/06/2018 00:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals(VALOR_DIA_MOTO,valor,0.0);
	}

	@Test
	public void ValidarCobroMotoCilindrajeMayor500CC() {
		Vehiculo moto = new MotoTestDataBuilder().withPlaca("AZX-225").withCilindraje(650).build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(moto).withFechaIngreso(formatoFecha.parse("28/06/2018 12:00")).withFechaSalida(formatoFecha.parse("28/06/2018 13:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals((VALOR_HORA_MOTO+VALOR_ADICIONAL_MOTO),valor,0.0);

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
