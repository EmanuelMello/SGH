package web.sistemagerenciamentohotel.model;
//package web.controlevacinacao.model;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicUpdate;
//
//@Entity
//@Table(name = "lote")
//@DynamicUpdate
//public class Lote implements Serializable {
//
//	private static final long serialVersionUID = -3935828642122652510L;
//	
//	@Id
//	@SequenceGenerator(name="gerador2", sequenceName="lote_codigo_seq", allocationSize=1)
//	@GeneratedValue(generator="gerador2", strategy = GenerationType.SEQUENCE)
//	private Long codigo;
//	private LocalDate validade;
//	@Column(name = "nro_doses_do_lote")
//	private int nroDosesDoLote;
//	@Column(name = "nro_doses_atual")
//	private int nroDosesAtual;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "codigo_vacina")
//	private Quarto vacina;
//	@Enumerated(EnumType.STRING)
//	private Status status = Status.ATIVO;
//
//	public Long getCodigo() {
//		return codigo;
//	}
//
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//
//	public LocalDate getValidade() {
//		return validade;
//	}
//
//	public void setValidade(LocalDate validade) {
//		this.validade = validade;
//	}
//
//	public int getNroDosesDoLote() {
//		return nroDosesDoLote;
//	}
//
//	public void setNroDosesDoLote(int nroDosesDoLote) {
//		this.nroDosesDoLote = nroDosesDoLote;
//	}
//
//	public int getNroDosesAtual() {
//		return nroDosesAtual;
//	}
//
//	public void setNroDosesAtual(int nroDosesAtual) {
//		this.nroDosesAtual = nroDosesAtual;
//	}
//
//	public Quarto getVacina() {
//		return vacina;
//	}
//
//	public void setVacina(Quarto vacina) {
//		this.vacina = vacina;
//	}
//
//	public Status getStatus() {
//		return status;
//	}
//
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Lote other = (Lote) obj;
//		if (codigo == null) {
//			if (other.codigo != null)
//				return false;
//		} else if (!codigo.equals(other.codigo))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "codigo: " + codigo + "\nvalidade: " + validade + "\nnroDosesDoLote: " + nroDosesDoLote
//				+ "\nnroDosesAtual: " + nroDosesAtual;
//	}
//
//}
