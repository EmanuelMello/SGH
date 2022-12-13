package web.sistemagerenciamentohotel.model.filter;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaFilter {
	private Long codigo;
	private BigDecimal valorTotal;
	private Long codigoConsumo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getCodigoConsumo() {
		return codigoConsumo;
	}

	public void setCodigoConsumo(Long codigoConsumo) {
		this.codigoConsumo = codigoConsumo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, codigoConsumo, valorTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaFilter other = (ContaFilter) obj;
		return codigo == other.codigo && codigoConsumo == other.codigoConsumo
				&& Objects.equals(valorTotal, other.valorTotal);
	}

	@Override
	public String toString() {
		return "ContaFilter [codigo=" + codigo + ", valorTotal=" + valorTotal + ", codigoConsumo=" + codigoConsumo
				+ "]";
	}

}
