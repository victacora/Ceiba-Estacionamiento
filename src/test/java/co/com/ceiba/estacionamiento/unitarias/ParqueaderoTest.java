package co.com.ceiba.estacionamiento.unitarias;

import static org.junit.Assert.fail;


import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.AccesoRestringidoException;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoTarifa;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.EnumUnidadTiempo;
import co.com.ceiba.estacionamiento.servicios.TarifaServicioImpl;
import co.com.ceiba.estacionamiento.servicios.ParqueaderoServicioImpl;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicioImpl;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.TicketParqueaderoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;
import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class ParqueaderoTest {

	private static final int UN_DIA_EN_MILISEG = 3600 * 1000 * 24;
	private static final int UNA_HORA_EN_MILISEG = 3600 * 1000;
	private static final int VEHICULO_REGISTRADO = 1;
	private static final int VEHICULO_NO_REGISTRADO = 0;
	private static final int CUPOS_USADOS = 0;
	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_ADICIONAL_MOTO = 2000;
	private static final double VALOR_UN_DIA_UNA_HORA_CARRO = 11000;
	private static final double VALOR_10_HORA_MOTO_650CC = 6000;

	private ParqueaderoServicioImpl ticketParqueaderoServicio;
	private VehiculoServicioImpl vehiculoServicio;
	private TarifaServicioImpl TarifaServicio;
	private Vigilante vigilante;
	
	private CalendarioVigilante calendarioVigilante;

	@Before
	public void setUp() {

		ticketParqueaderoServicio = Mockito.mock(ParqueaderoServicioImpl.class);
		Mockito.when(ticketParqueaderoServicio.crearTicketParqueadero(Mockito.any())).thenReturn(true);
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString())).thenReturn(CUPOS_USADOS);
		Mockito.when(ticketParqueaderoServicio.verificarIngresoVehiculo(Mockito.anyString()))
				.thenReturn(VEHICULO_NO_REGISTRADO);

		Vehiculo vehiculo = Mockito.mock(Vehiculo.class);

		vehiculoServicio = Mockito.mock(VehiculoServicioImpl.class);
		Mockito.when(vehiculoServicio.guardarVehiculo(Mockito.any())).thenReturn(true);
		Mockito.when(vehiculoServicio.obtenerVehiculo(Mockito.anyString())).thenReturn(vehiculo);

		TarifaServicio = Mockito.mock(TarifaServicioImpl.class);
		Mockito.doAnswer(new Answer<Double>() {
			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				Double valor = 0.0;
				Object[] args = invocation.getArguments();
				String tipoVehiculo = args[0].toString();
				String tipoTarifa = args[1].toString();
				String unidadTiempo = args[2].toString();
				if (tipoVehiculo.equals(EnumTipoVehiculo.CARRO.name())
						&& tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())
						&& unidadTiempo.equals(EnumUnidadTiempo.HORA.name())) {
					valor = VALOR_HORA_CARRO;
				} else if (tipoVehiculo.equals(EnumTipoVehiculo.CARRO.name())
						&& tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())
						&& unidadTiempo.equals(EnumUnidadTiempo.DIA.name())) {
					valor = VALOR_DIA_CARRO;
				} else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())
						&& tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())
						&& unidadTiempo.equals(EnumUnidadTiempo.HORA.name())) {
					valor = VALOR_HORA_MOTO;
				} else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())
						&& tipoTarifa.equals(EnumTipoTarifa.TIEMPO.name())
						&& unidadTiempo.equals(EnumUnidadTiempo.DIA.name())) {
					valor = VALOR_DIA_MOTO;
				} else if (tipoVehiculo.equals(EnumTipoVehiculo.MOTO.name())
						&& tipoTarifa.equals(EnumTipoTarifa.ADICIONAL.name())
						&& unidadTiempo.equals(EnumUnidadTiempo.NOAPLICA.name())) {
					valor = VALOR_ADICIONAL_MOTO;
				}
				return valor;
			}
		}).when(TarifaServicio).obtenerValorTarifa(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

		calendarioVigilante = Mockito.mock(CalendarioVigilante.class);
		Mockito.when(calendarioVigilante.esDiaHabil()).thenReturn(true);
		
		vigilante = new Vigilante(ticketParqueaderoServicio,vehiculoServicio,TarifaServicio,calendarioVigilante);
	}

	@Test
	public void ingresarCarro() {
		// arrange
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("CXX-001").build();
		// act
		boolean resultado = vigilante.ingresarVehiculo(carro);
		// assert
		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void ingresarMoto() {
		// arrange
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("MXX-001").build();
		// act
		boolean resultado = vigilante.ingresarVehiculo(moto);
		// assert
		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

	@Test
	public void validarCuposCarroMax20() {
		// arrange
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_CARRO + 1);
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("CMM-002").build();
		// act
		try {
			vigilante.ingresarVehiculo(carro);
			fail();
		} catch (CupoExcedidoException e) {
			// assert
			Assert.assertEquals(Vigilante.MSJ_NO_HAY_CUPOS_DISPONIBLES, e.getMessage());
		}
	}

	@Test
	public void validarCuposMotoMax10() {
		// arrange
		Mockito.when(ticketParqueaderoServicio.verificarCupoVehiculo(Mockito.anyString()))
				.thenReturn(Vigilante.NUMERO_MAXIMO_CUPOS_MOTO + 1);
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(200).withPlaca("CXX-002").build();
		// act
		try {
			vigilante.ingresarVehiculo(moto);
			fail();
		} catch (CupoExcedidoException e) {
			// assert
			Assert.assertEquals(Vigilante.MSJ_NO_HAY_CUPOS_DISPONIBLES, e.getMessage());
		}
	}

	@Test
	public void validarIngresoVehiculoDiaHabilTest() {
		// arrange
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("AXX-001").build();
		
		CalendarioVigilante calendarioVigilante = Mockito.mock(CalendarioVigilante.class);
		Mockito.when(calendarioVigilante.esDiaHabil()).thenReturn(false);
		vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, TarifaServicio, calendarioVigilante);
		try {
		
			vigilante.ingresarVehiculo(carro);
			fail();
		} catch (AccesoRestringidoException e) {
			// assert
			Assert.assertEquals(Vigilante.MSJ_NO_ESTA_AUTORIZADO_PARA_INGRESAR, e.getMessage());
		}
	}

	@Test
	public void validarCobroHoraCarro() {
		// arrange
		Vehiculo carro = inicializarCobroParqueaderoVehiculo(UNA_HORA_EN_MILISEG, EnumTipoVehiculo.CARRO, 0);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(carro.getPlaca());
		// assert
		Assert.assertEquals(VALOR_HORA_CARRO, resultado.getValor(), 0.0);
	}

	@Test
	public void validarCobroDiaCarro() {
		// arrange
		Vehiculo carro = inicializarCobroParqueaderoVehiculo(UN_DIA_EN_MILISEG, EnumTipoVehiculo.CARRO, 0);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(carro.getPlaca());
		// assert
		Assert.assertEquals(VALOR_DIA_CARRO, resultado.getValor(), 0.0);
	}

	@Test
	public void validarCobroHoraMoto() {
		// arrange
		Vehiculo moto = inicializarCobroParqueaderoVehiculo(UNA_HORA_EN_MILISEG, EnumTipoVehiculo.MOTO, 0);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(moto.getPlaca());
		// assert
		Assert.assertEquals(VALOR_HORA_MOTO, resultado.getValor(), 0.0);

	}

	@Test
	public void validarCobroDiaMoto() {
		// arrange
		Vehiculo moto = inicializarCobroParqueaderoVehiculo(UN_DIA_EN_MILISEG, EnumTipoVehiculo.MOTO, 0);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(moto.getPlaca());
		// assert
		Assert.assertEquals(VALOR_DIA_MOTO, resultado.getValor(), 0.0);
	}

	@Test
	public void validarCobroMotoCilindrajeMayor500CC() {
		// arrange
		Vehiculo moto = inicializarCobroParqueaderoVehiculo(0, EnumTipoVehiculo.MOTO, 650);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(moto.getPlaca());
		// assert
		Assert.assertEquals(VALOR_ADICIONAL_MOTO, resultado.getValor(), 0.0);

	}

	@Test
	public void validarCobroCarroUnDiaY3horas() {
		// arrange
		Vehiculo carro = inicializarCobroParqueaderoVehiculo((UN_DIA_EN_MILISEG + 3 * UNA_HORA_EN_MILISEG),
				EnumTipoVehiculo.CARRO, 0);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(carro.getPlaca());
		// assert
		Assert.assertEquals(VALOR_UN_DIA_UNA_HORA_CARRO, resultado.getValor(), 0.0);

	}

	@Test
	public void validarCobroMotoPara10Hora650CC() {
		// arrange
		Vehiculo moto = inicializarCobroParqueaderoVehiculo(10 * UNA_HORA_EN_MILISEG, EnumTipoVehiculo.MOTO, 650);
		// act
		TicketParqueadero resultado = vigilante.retirarVehiculo(moto.getPlaca());
		// assert
		Assert.assertEquals(VALOR_10_HORA_MOTO_650CC, resultado.getValor(), 0.0);
	}

	private Vehiculo inicializarCobroParqueaderoVehiculo(long tiempo, EnumTipoVehiculo tipoVehiculo,
			double cilindraje) {
		Vehiculo carro = tipoVehiculo == EnumTipoVehiculo.CARRO
				? new CarroTestDataBuilder().withPlaca("CXX-003").build()
				: new MotoTestDataBuilder().withPlaca("MXX-003").withCilindraje(cilindraje).build();

		TicketParqueadero ticketParqueadero = new TicketParqueaderoTestDataBuilder().withVehiculo(carro)
				.withFechaIngreso(new Date(System.currentTimeMillis() - tiempo)).build();

		Mockito.when(ticketParqueaderoServicio.obtenerTicketParquedero(Mockito.anyString()))
				.thenReturn(ticketParqueadero);
		
		Mockito.when(ticketParqueaderoServicio.actualizarTicketParqueadero(Mockito.any()))
				.thenAnswer(new Answer<TicketParqueadero>() {
					@Override
					public TicketParqueadero answer(InvocationOnMock invocation) throws Throwable {
						Object[] args = invocation.getArguments();
						return (TicketParqueadero) args[0];
					}
				});
		Mockito.when(ticketParqueaderoServicio.verificarIngresoVehiculo(Mockito.anyString()))
				.thenReturn(VEHICULO_REGISTRADO);

		return carro;
	}

}
