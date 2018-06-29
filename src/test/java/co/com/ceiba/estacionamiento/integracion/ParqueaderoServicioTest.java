package co.com.ceiba.estacionamiento.integracion;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.servicios.TarifaServicio;
import co.com.ceiba.estacionamiento.servicios.TicketParqueaderoServicio;
import co.com.ceiba.estacionamiento.servicios.VehiculoServicio;
import co.com.ceiba.estacionamiento.testdatabuilder.CarroTestDataBuilder;
import co.com.ceiba.estacionamiento.testdatabuilder.MotoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParqueaderoServicioTest {

	
	@Autowired
	TicketParqueaderoServicio ticketParqueaderoServicio;

	@Autowired
	VehiculoServicio vehiculoServicio;
	
	@Autowired
	TarifaServicio tarifaServicio;
	
	@Test
	public void IngresarCarro() {
		Vehiculo carro = new CarroTestDataBuilder().withPlaca("XXX-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio,tarifaServicio);

		boolean resultado = vigilante.ingresarVehiculo(carro);

		Assert.assertTrue("El carro no pudo ser ingresado.", resultado);
	}

	@Test
	public void IngresarMoto() {

		Vehiculo moto = new MotoTestDataBuilder().withCilindraje(10).withPlaca("XXY-220").build();
		Vigilante vigilante = new Vigilante(ticketParqueaderoServicio, vehiculoServicio,tarifaServicio);

		boolean resultado = vigilante.ingresarVehiculo(moto);

		Assert.assertTrue("La moto no pudo ser ingresada.", resultado);
	}


}
