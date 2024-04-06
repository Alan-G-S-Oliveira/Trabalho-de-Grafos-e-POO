package Itens;

import Criaturas.Criatura;

public abstract class Arma {
	private int dano;
	private String nome;
	private String descricao;
	private int durabilidade;
	
	abstract public Arma copy();
	public void atacar(Criatura a, int força) {
		a.receberDano(força+this.getDano());
		durabilidade--;
	}
	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
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
	public int getDurabilidade() {
		return durabilidade;
	}
	public void setDurabilidade(int durabilidade) {
		this.durabilidade = durabilidade;
	}

}
