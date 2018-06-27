package co.com.ceiba.estacionamiento.persistencia.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="Vehiculo")
@Table(name="vehiculo")
public class VehiculoEntity {

	@Id
	@Column(length=8)
	private String placa;

	@Column(name = "valor", nullable = true, precision = 12, scale = 2)
	private double cilindraje;

	@Column(name = "tipo_vehiculo")
	private String tipoVehiculo;

	@OneToMany(mappedBy = "vehiculo", targetEntity = TicketParqueaderoEntity.class, fetch = FetchType.LAZY)
	private List<TicketParqueaderoEntity> ticketParqueaderos;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public List<TicketParqueaderoEntity> getTicketParqueaderos() {
		return ticketParqueaderos;
	}

	public void setTicketParqueaderos(List<TicketParqueaderoEntity> ticketParqueaderos) {
		this.ticketParqueaderos = ticketParqueaderos;
	}

	
}
