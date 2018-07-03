package co.com.ceiba.estacionamiento.integracion;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.servicios.TarifaServicioImpl;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicioImpl;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicioImpl;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.EstacionamientoApplication;
import co.com.ceiba.estacionamiento.dominio.CalendarioVigilante;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EstacionamientoApplication.class)
public class ParqueaderoTest {

	@Autowired
	TicketParqueaderoServicioImpl ticketParqueaderoServicio;

	@Autowired
	VehiculoServicioImpl vehiculoServicio;

	@Autowired
	TarifaServicioImpl tarifaServicio;

	@Test
	public void IngresarCarro() {
		// Assert
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		// Act
		boolean resultado = vigilante.ingresarVehiculo(carro);
		// Assert
		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {
		// Assert
		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();
		CalendarioVigilante calendarioVigilante = new CalendarioVigilante(Calendar.TUESDAY, 6, 2018);
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio, tarifaServicio,
				calendarioVigilante);
		// Act
		boolean resultado = vigilante.ingresarVehiculo(moto);
		// Assert
		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}

}
