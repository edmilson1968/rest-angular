package br.com.edm.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoRisco {

	@Id
	private String tipo;
	private BigDecimal taxaJuros;
	
	public TipoRisco() {
	}

	public TipoRisco(String tipo) {
		this.tipo = tipo;
		this.taxaJuros = new BigDecimal(0.0);
	}
	
	public TipoRisco(String tipo, BigDecimal taxaJuros) {
		this.tipo = tipo;
		this.taxaJuros = taxaJuros;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal	 taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	@Override
	public String toString() {
		return "TipoRisco [tipo=" + tipo + ", taxaJuros=" + taxaJuros + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxaJuros == null) ? 0 : taxaJuros.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoRisco other = (TipoRisco) obj;
		if (taxaJuros == null) {
			if (other.taxaJuros != null)
				return false;
		} else if (!taxaJuros.equals(other.taxaJuros))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
