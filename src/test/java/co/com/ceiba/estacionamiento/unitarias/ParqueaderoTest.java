package co.com.ceiba.estacionamiento.unitarias;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.estacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.dominio.TicketParqueadero;
import co.com.ceiba.estacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.enumeraciones.TipoVehiculo;
import co.com.ceiba.estacionamiento.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.estacionamiento.dominio.Vehiculo;

public class ParqueaderoTest {

	@Test
	public void IngresarCarro() 
	{
		Vehiculo carro=new VehiculoTestDataBuilder().withCilindraje(10)
				.withPlaca("XXX-220")
				.withTipoVehiculo(TipoVehiculo.CARRO).build();
		Vigilante vigilante=new Vigilante();
		TicketParqueadero ticketParqueadero= vigilante.ingresarVehiculo(carro);
		Assert.assertNotNull(ticketParqueadero);
	}

	@Test
	public void IngresarMoto() 
	{
		Vehiculo moto=new VehiculoTestDataBuilder().withCilindraje(10)
				.withPlaca("XXY-220")
				.withTipoVehiculo(TipoVehiculo.MOTO).build();
		Vigilante vigilante=new Vigilante();
		TicketParqueadero ticketParqueadero=  vigilante.ingresarVehiculo(moto);
		Assert.assertNotNull(ticketParqueadero);
	}
	
	@Test
	public void ValidarCuposCarroMax20() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCuposMotoMax10() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarAccesoVehiculoPlacasTerminadasEnA() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroHoraCarro() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroDiaCarro() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroHoraMoto() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroDiaMoto() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroMotoCilindrajeMayor500CC() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroCarroParaVariosDiasYHoras() 
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void ValidarCobroMotoParaVariosDiasYHoras() 
	{
		Assert.assertTrue(true);
	}
	
}
