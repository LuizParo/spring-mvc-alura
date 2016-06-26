package br.com.caelum.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Preco implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private BigDecimal valor;
    private TipoPreco tipoPreco;
    
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }
    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }
    
    @Override
    public String toString() {
        return this.tipoPreco.name() + " - " + this.valor;
    }
}