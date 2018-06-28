package co.com.ceiba.estacionamiento.persistencia.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Vehiculo")
@Table(name = "vehiculo")
public class VehiculoEntity {

	@Id
	@Column(length = 8)
	private String placa;

	@Column(name = "cilindraje", nullable = true, precision = 12, scale = 2)
	private double cilindraje;

	@Column(name = "tipo_vehiculo",length = 5)
	private String tipoVehiculo;

	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private Set<TicketParqueaderoEntity> ticketParqueaderos;

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

	public Set<TicketParqueaderoEntity> getTicketParqueaderos() {
		return ticketParqueaderos;
	}

	public void setTicketParqueaderos(Set<TicketParqueaderoEntity> ticketParqueaderos) {
		this.ticketParqueaderos = ticketParqueaderos;
	}

	public void agregarTicket(TicketParqueaderoEntity ticketParqueaderoEntity) {
		if (this.ticketParqueaderos == null)
			this.ticketParqueaderos = new HashSet<>();
		this.ticketParqueaderos.add(ticketParqueaderoEntity);
		ticketParqueaderoEntity.setVehiculo(this);
	}

}
