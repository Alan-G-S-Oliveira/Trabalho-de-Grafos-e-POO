package projeto;

public abstract class Efeito_de_terreno {
	String nome;
	String comment;
	int dano;
	public Efeito_de_terreno() {
		
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getDano() {
		return this.dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}

	public abstract void aplicarEfeito(Criatura p);
}