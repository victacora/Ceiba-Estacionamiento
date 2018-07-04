package co.com.ceiba.estacionamiento.integracion;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.excepciones.CupoExcedidoException;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoTarifa;
import co.com.ceiba.estacionamiento.enumeraciones.EnumTipoVehiculo;
import co.com.ceiba.estacionamiento.enumeraciones.EnumUnidadTiempo;
import co.com.ceiba.estacionamiento.servicios.TarifaServicioImpl;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicioImpl;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicioImpl;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.configuracion.H2JpaConfig;
import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EstacionamientoApplication.class, H2JpaConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoTest {

	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_ADICIONAL_MOTO = 2000;

	@Autowired
	TicketParqueaderoServicioImpl ticketParqueaderoServicio;

	@Autowired
	VehiculoServicioImpl vehiculoServicio;

	@Autowired
	TarifaServicioImpl tarifaServicio;

	@Test
	public void ingresar20Carros() {
		// Assert
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		int MAX_NUM_CARROS = 20;
		for (int numeroCarro = 0; numeroCarro < MAX_NUM_CARROS; numeroCarro++) {

			Vehiculo carro = new CarroTestDataBuilder().withPlaca("CXX-" + numeroCarro).build();

			vigilante.ingresarVehiculo(carro);
		}
		// Act
		int cantidadCarros = ticketParqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.CARRO.name());
		// Assert
		Assert.assertEquals("La cantidad de carros ingresados no coincide con los registrados.", MAX_NUM_CARROS,
				cantidadCarros);
	}

	@Test
	public void ingresar10Motos() {
		// Assert
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		int MAX_NUM_MOTOS = 10;
		for (int numeroMoto = 0; numeroMoto < MAX_NUM_MOTOS; numeroMoto++) {

			Vehiculo moto = new MotoTestDataBuilder().withPlaca("MXX-" + numeroMoto).withCilindraje(100).build();

			vigilante.ingresarVehiculo(moto);
		}
		// Act
		int cantidadMotos = ticketParqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.MOTO.name());
		// Assert
		Assert.assertEquals("La cantidad de motos ingresadas no coincide con los registrados.", MAX_NUM_MOTOS,
				cantidadMotos);
	}

	@Test
	public void ingresarCarroCupoMax20() {
		// arrange
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("MXX-20").build();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
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
	public void ingresarMotoCupoMax10() {
		// arrange
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(200).withPlaca("MXX-010").build();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
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
	public void validarCobroHoraCarro() {
		// arrange
		// act
		double valor = tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.CARRO.name(), EnumTipoTarifa.TIEMPO.name(),
				EnumUnidadTiempo.HORA.name());
		// assert
		Assert.assertEquals("El valor de la hora carro no coinciden",VALOR_HORA_CARRO, valor, 0.0);
	}

	@Test
	public void validarCobroDiaCarro() {
		// arrange
		// act
		double valor = tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.CARRO.name(), EnumTipoTarifa.TIEMPO.name(),
				EnumUnidadTiempo.DIA.name());
		// assert
		Assert.assertEquals("El valor del dia carro no coinciden",VALOR_DIA_CARRO, valor, 0.0);
	}

	@Test
	public void ValidarCobroHoraMoto() {
		// arrange
		// act
		double valor = tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(), EnumTipoTarifa.TIEMPO.name(),
				EnumUnidadTiempo.HORA.name());
		// assert
		Assert.assertEquals("El valor de la hora moto no coinciden",VALOR_HORA_MOTO, valor, 0.0);

	}

	@Test
	public void validarCobroDiaMoto() {
		// arrange
		// act
		double valor = tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(), EnumTipoTarifa.TIEMPO.name(),
				EnumUnidadTiempo.DIA.name());
		// assert
		Assert.assertEquals("El valor del dia carro no coinciden",VALOR_DIA_MOTO, valor, 0.0);
	}

	@Test
	public void validarCobroMotoCilindrajeMayor500CC() {
		// arrange
		// act
		double valor = tarifaServicio.obtenerValorTarifa(EnumTipoVehiculo.MOTO.name(), EnumTipoTarifa.ADICIONAL.name(),
				EnumUnidadTiempo.NOAPLICA.name());
		// assert
		Assert.assertEquals("El valor del costo adicional moto cilindraje mayor a 500 no coinciden",VALOR_ADICIONAL_MOTO, valor, 0.0);
	}

	@Test
	public void retirar20Carros() {
		// Assert
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		int MAX_NUM_CARROS = 20;
		for (int numeroCarro = 0; numeroCarro < MAX_NUM_CARROS; numeroCarro++) {

			vigilante.retirarVehiculo("CXX-" + numeroCarro);
		}
		// Act
		int cantidadCarros = ticketParqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.CARRO.name());
		// Assert
		Assert.assertEquals("La cantidad de carros ingresados no coincide con los registrados.", 0,
				cantidadCarros);
	}

	@Test
	public void retirar10Motos() {
		// Assert
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		int MAX_NUM_MOTOS = 10;
		for (int numeroMoto = 0; numeroMoto < MAX_NUM_MOTOS; numeroMoto++) {
			vigilante.retirarVehiculo("MXX-" + numeroMoto);
		}
		// Act
		int cantidadMotos = ticketParqueaderoServicio.verificarCupoVehiculo(EnumTipoVehiculo.MOTO.name());
		// Assert
		Assert.assertEquals("La cantidad de motos pendientes por retirar no coincide con los registrados.", 0,
				cantidadMotos);
	}
}
