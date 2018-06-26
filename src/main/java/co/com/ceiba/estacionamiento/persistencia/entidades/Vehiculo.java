package co.com.ceiba.estacionamiento.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehiculo {

	@Id
	private String placa;

	@Column
	private double cilindraje;

	@Column(name="tipo_vehiculo")
	private String tipoVehiculo;
	
}
