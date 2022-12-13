package web.sistemagerenciamentohotel.model.filter;

import java.math.BigDecimal;

public class QuartoFilter {

	private Long codigo;
	private BigDecimal valor;
	private String descricao;
	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "QuartoFilter [codigo=" + codigo + ", valor=" + valor + ", descricao=" + descricao + ", numero=" + numero
				+ "]";
	}
	
}
