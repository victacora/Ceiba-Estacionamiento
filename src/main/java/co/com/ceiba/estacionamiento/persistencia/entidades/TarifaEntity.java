package co.com.ceiba.estacionamiento.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Tarifa")
@Table(name = "tarifa")
public class TarifaEntity {

	@EmbeddedId
	private TarifaId id;

	@Column(name = "valor", nullable = true, precision = 12, scale = 2)
	private double valor;

	public TarifaId getId() {
		return id;
	}

	public void setId(TarifaId id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
}
