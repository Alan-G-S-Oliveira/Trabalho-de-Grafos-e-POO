package projeto;

public abstract class Colar implements Efeito{
	private String nome;
	private String descricao;
	
	abstract public Colar copy();
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
