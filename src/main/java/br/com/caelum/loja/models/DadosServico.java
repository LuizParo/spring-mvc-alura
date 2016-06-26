package br.com.caelum.loja.models;

import java.math.BigDecimal;

public class DadosServico {

    private BigDecimal value;

    public DadosServico(BigDecimal value) {
	this.value = value;
    }

    public BigDecimal getValue() {
	return value;
    }
}