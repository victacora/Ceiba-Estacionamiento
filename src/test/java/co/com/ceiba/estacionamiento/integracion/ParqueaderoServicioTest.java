package co.com.ceiba.estacionamiento.integracion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.dominio.validaciones.IValidacion;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionCupo;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionIngresoAutorizado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoNoEncontrado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoNoRegistrado;
import co.com.ceiba.estacionamiento.dominio.validaciones.ValidacionVehiculoRegistrado;
import co.com.ceiba.estacionamiento.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.TicketParqueaderoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParqueaderoServicioTest {

	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_ADICIONAL_MOTO = 2000;

	@Autowired
	TicketParqueaderoServicio ticketParqueaderoServicio;

	@Autowired
	VehiculoServicio vehiculoServicio;
	
	@Autowired
	TarifaServicio tarifaServicio;

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
	
	@Test
	public void IngresarCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio,tarifaServicio,valiacionesIngresoVehiculo());

		boolean resultado = vigilante.ingresarVehiculo(carro);

		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {

		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio,tarifaServicio,valiacionesIngresoVehiculo());

		boolean resultado = vigilante.ingresarVehiculo(moto);

		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

	@Test
	public void ValidarCobroHoraCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("AZX-225").build();
		SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		TicketParqueadero ticketParqueadero=null;
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio, validacionesSalidaVehiculo());
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
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio, validacionesSalidaVehiculo());
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
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio, validacionesSalidaVehiculo());
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
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio, validacionesSalidaVehiculo());
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
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio, validacionesSalidaVehiculo());
		try {
			 ticketParqueadero=new TicketParqueaderoTestDataBuilder().withVehiculo(moto).withFechaIngreso(formatoFecha.parse("28/06/2018 12:00")).withFechaSalida(formatoFecha.parse("28/06/2018 13:00")).build();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		double valor=vigilante.calcularValorAPagar(ticketParqueadero);
		Assert.assertEquals((VALOR_HORA_MOTO+VALOR_ADICIONAL_MOTO),valor,0.0);

	}
}
