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
	
	public String getcomment() {
		return this.comment;
	}
	public void setcomment(String comment) {
		this.comment = comment;
	}
	
	public int getdano() {
		return this.dano;
	}
	public void setdano(int dano) {
		this.dano = dano;
	}
}