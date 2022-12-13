package web.sistemagerenciamentohotel.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "hospede")
@DynamicUpdate
public class Hospede implements Serializable {

	private static final long serialVersionUID = 7562368353372595992L;

	@Id
	@SequenceGenerator(name = "gerador", sequenceName = "hospede_codigo_seq", allocationSize = 1)
	@GeneratedValue(generator = "gerador", strategy = GenerationType.SEQUENCE)
	private Long codigo;
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	@NotBlank(message = "O cpf é obrigatório")
	private String cpf;
	@NotBlank(message = "O rg é obrigatório")
	private String rg;
	@PositiveOrZero(message = "Numero inserido é inválido")
	@NotBlank(message = "O numero é obrigatório")
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public int hashCode() {
		return Objects.hash(codigo, cpf, nome, rg, status, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospede other = (Hospede) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg) && status == other.status
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "Hospede [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", telefone=" + telefone
				+ ", status=" + status + "]";
	}

}