package web.sistemagerenciamentohotel.model;

public enum StatusPago {
	PAGO("Pago"),
	NAO_PAGO("Nao pago");
	
	private String descricao;
	
	private StatusPago(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}