package br.com.edm.entities;

import java.math.BigDecimal;
import java.math.MathContext;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoRiscoEnum {

	A("A", new BigDecimal(0.0, new MathContext(2))),
	B("B", new BigDecimal(0.1, new MathContext(2))),
	C("C", new BigDecimal(0.2, new MathContext(2)));
	
	@JsonProperty("tipo")
	private final String tipo;
	@JsonProperty("risco")
	private final BigDecimal risco;

	private TipoRiscoEnum(String tipo, BigDecimal risco) {
		this.tipo = tipo;
		this.risco = risco;
	}
	
	public static TipoRiscoEnum getByTipo(String tipo) {
		for (TipoRiscoEnum e: TipoRiscoEnum.values()) {
			if (e.name().equals(tipo))
				return e;
		}
		return null;
	}
	
	public static BigDecimal getRiscoByTipo(String tipo) {
		for (TipoRiscoEnum e: TipoRiscoEnum.values()) {
			if (e.name().equals(tipo))
				return e.risco;
		}
		return null;
	}
	
	
}

