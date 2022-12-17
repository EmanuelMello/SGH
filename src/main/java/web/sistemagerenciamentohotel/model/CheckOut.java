package web.sistemagerenciamentohotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "checkOut")
public class CheckOut implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@SequenceGenerator(name = "gerador5", sequenceName = "checkOut_codigo_seq", allocationSize = 1)
	@GeneratedValue(generator = "gerador5", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	@NotNull
	private BigDecimal valorTotal;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_checkin")
	private CheckIn checkIn;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		return "CheckOut [codigo=" + codigo + ", valorTotal=" + valorTotal + ", checkIn=" + checkIn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckOut other = (CheckOut) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
