package br.com.bagarote.model.entity;

public enum MetodoPagamento {
	PENDENTE("PENDENTE"),
	DINHEIRO("DINHEIRO"),
	CREDITO("CREDITO"),
	DEBITO("DEBITO"),
	PIX("PIX");

	private String metodoPagamento;

	MetodoPagamento(String metodoPagamento){
		this.metodoPagamento=metodoPagamento;
	}

	public String getMetodoPagamento(){
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento){
		this.metodoPagamento = metodoPagamento;
	}


}
