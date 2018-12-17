package br.com.edm.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cliente {

	@Id
	private String nome;
	private BigDecimal limite;
	
	@ManyToOne
	@JoinColumn
	private TipoRisco risco;
	
	public Cliente() {}

	public Cliente(String nome, BigDecimal limite, TipoRisco risco) {
		this.nome = nome;
		this.limite = limite;
		this.risco = risco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public TipoRisco getRisco() {
		return risco;
	}

	public void setRisco(TipoRisco risco) {
		this.risco = risco;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", limite=" + limite + ", risco=" + risco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((limite == null) ? 0 : limite.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((risco == null) ? 0 : risco.hashCode());
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
		Cliente other = (Cliente) obj;
		if (limite == null) {
			if (other.limite != null)
				return false;
		} else if (!limite.equals(other.limite))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (risco == null) {
			if (other.risco != null)
				return false;
		} else if (!risco.equals(other.risco))
			return false;
		return true;
	}

}
