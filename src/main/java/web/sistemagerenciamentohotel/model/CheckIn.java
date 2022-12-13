package web.sistemagerenciamentohotel.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "checkIn")
public class CheckIn implements Serializable {

	private static final long serialVersionUID = 2L;
	
	@Id
	@SequenceGenerator(name="gerador5", sequenceName="checkIn_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador5", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	private LocalDate dataCheckIn;
	@NotNull(message ="O checkOut deve ser preenchido")
	private LocalDate dataCheckOut;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_quarto")
	private Quarto quarto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_hospede")
	private Hospede hospede;
	@Enumerated(EnumType.STRING)
	private StatusPago statusPago = StatusPago.NAO_PAGO;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getCheckIn() {
		return dataCheckIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.dataCheckIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return dataCheckOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.dataCheckOut = checkOut;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
//
//	public Conta getConta() {
//		return conta;
//	}
//
//	public void setConta(Conta conta) {
//		this.conta = conta;
//	}

	@Override
	public String toString() {
		return "CheckIn [codigo=" + codigo + ", checkIn=" + dataCheckIn + ", checkOut=" + dataCheckOut + ", quarto=" + quarto
				+ ", hospede=" + hospede + "]";
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
		CheckIn other = (CheckIn) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
