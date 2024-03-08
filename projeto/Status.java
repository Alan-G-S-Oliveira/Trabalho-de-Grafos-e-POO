package projeto;

public abstract class Status implements Efeito {
	private String nome;
	private String descricao;
	
	@Override
	public void aplicarEfeito(Criatura a) {

	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
