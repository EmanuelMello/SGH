package web.sistemagerenciamentohotel.model.filter;

import java.math.BigDecimal;

import web.sistemagerenciamentohotel.model.CheckIn;

public class CheckOutFilter {
	private Long codigo;
	private BigDecimal valorTotal;
	private CheckIn checkIn;
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
	public CheckIn getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}
	@Override
	public String toString() {
		return "CheckOutFilter [codigo=" + codigo + ", valorTotal=" + valorTotal + ", checkIn=" + checkIn + "]";
	}
}
