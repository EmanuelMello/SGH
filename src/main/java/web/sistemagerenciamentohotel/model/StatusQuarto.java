package web.sistemagerenciamentohotel.model;

public enum StatusQuarto {
	DISPONIVEL("disponivel"),
	OCUPADO("ocupado");
	
	private String descricaoQuarto;
	
	private StatusQuarto(String descricao) {
		this.descricaoQuarto = descricao;
	}
	
	public String getDescricao() {
		return descricaoQuarto;
	}
}

