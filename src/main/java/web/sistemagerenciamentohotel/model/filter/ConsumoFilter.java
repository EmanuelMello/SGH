package web.sistemagerenciamentohotel.model.filter;

import java.math.BigDecimal;
import java.util.Objects;

public class ConsumoFilter {
	
	private Long codigo;
	private String descricao;
	private Integer quantidade;
	private BigDecimal valorUnitario;

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descricao, quantidade, valorUnitario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumoFilter other = (ConsumoFilter) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(valorUnitario, other.valorUnitario);
	}

	@Override
	public String toString() {
		return "ConsumoFilter [codigo=" + codigo + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", valorUnitario=" + valorUnitario + "]";
	}

}
