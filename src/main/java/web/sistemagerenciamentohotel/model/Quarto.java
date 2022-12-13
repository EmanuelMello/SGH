package web.sistemagerenciamentohotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "quarto")
@DynamicUpdate
public class Quarto implements Serializable {

	private static final long serialVersionUID = 7562368353372595992L;

	@Id
	@SequenceGenerator(name = "gerador2", sequenceName = "quarto_codigo_seq", allocationSize = 1)
	@GeneratedValue(generator = "gerador2", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	private BigDecimal valor;
	private String descricao;
	private String numero;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	@Enumerated(EnumType.STRING)
	private StatusQuarto statusQuarto = StatusQuarto.DISPONIVEL;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusQuarto getStatusQuarto() {
		return statusQuarto;
	}

	public void setStatusQuarto(StatusQuarto statusQuarto) {
		this.statusQuarto = statusQuarto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Quarto [codigo=" + codigo + ", valor=" + valor + ", descricao=" + descricao + ", numero=" + numero
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descricao, numero, status, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quarto other = (Quarto) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(numero, other.numero) && status == other.status && Objects.equals(valor, other.valor);
	}



	

}
