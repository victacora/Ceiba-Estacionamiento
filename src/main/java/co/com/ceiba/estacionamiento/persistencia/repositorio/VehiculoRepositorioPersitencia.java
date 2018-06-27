package co.com.ceiba.estacionamiento.persistencia.repositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.dominio.repositorio.VehiculoRepositorio;
import co.com.ceiba.estacionamiento.persistencia.builders.VehiculoBuilder;
import co.com.ceiba.estacionamiento.persistencia.entidades.VehiculoEntity;
import co.com.ceiba.estacionamiento.persistencia.repositorio.jpa.VehiculoRepositorioJPA;

@Repository
public class VehiculoRepositorioPersitencia implements VehiculoRepositorio {

	@Autowired
	private VehiculoRepositorioJPA vehiculoRepositorioJPA;

	@Override
	public boolean crearVehiculo(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		return vehiculoRepositorioJPA.save(vehiculoEntity) != null;
	}

	@Override
	public Vehiculo obtenerVehiculo(String placa) {
		Optional<VehiculoEntity> vehiculoEntity = vehiculoRepositorioJPA.findById(placa);
		return (vehiculoEntity.isPresent() ? VehiculoBuilder.convertirADominio(vehiculoEntity.get()) : null);
	}

}
