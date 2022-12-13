package web.sistemagerenciamentohotel.model.filter;

import java.util.Objects;

public class HospedeFilter {

	private Long codigo;
	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
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
	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf, nome, rg, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HospedeFilter other = (HospedeFilter) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg)
				&& Objects.equals(telefone, other.telefone);
	}
	@Override
	public String toString() {
		return "HospedeFilter [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", telefone="
				+ telefone + "]";
	}
	
	
	
}