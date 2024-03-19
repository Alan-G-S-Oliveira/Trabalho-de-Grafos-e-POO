package projeto;

import java.util.Random;

public abstract class Criatura {
	private int vida_total; //Variavel responsavel por guardar a vida total da Criatura.
	private int vida_atual; //Variavel responsavel por guardar a vida atual da Criatura.
	private int força; //Variavel responsavel por guardar a força da Criatura.
	private Arma arma; //Variavel responsavel por guardar a arma atual da Criatura.
	private int posição; //Variavel responsavel por guardar a posição atual da Criatura.
	private Status status; //Variavel responsavel por guardar a status atual da Criatura.
	private boolean batalhou;
	Criatura(){
		
	}
	Criatura(int vida_total,int força,Arma arma,int posição,Status status){
		this.vida_total = vida_total;
		this.vida_atual = vida_total;
		this.força = força;
		this.arma = arma;
		this.posição = posição;
		this.status = status;
		this.batalhou = false;
	}
	
	public boolean getBatalhou() {
		return this.batalhou;
	}
	public void setBatalhou(boolean batalhou) {
		this.batalhou = batalhou;
	}
	
	public int getVida_total() {
		return vida_total;
	}
	public void setVida_total(int vida_total) {
		this.vida_total = vida_total;
	}
	
	public int getVida_atual() {
		return vida_atual;
	}
	public void setVida_atual(int vida_atual) {
		this.vida_atual = vida_atual;
	}
	
	public int getForça() {
		return força;
	}
	public void setForça(int força) {
		this.força = força;
	}
	
	public int getPosição() {
		return posição;
	}
	public void setPosição(int posição) {
		this.posição = posição;
	}
	
	public Arma getArma() {
		return arma;
	}
	public void setArma(Arma arma) {
		this.arma = arma;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void receberDano(int dano) { //Função para dar dano imediato de armadilhas.
		vida_atual -= dano;
	}
	
	public void receberCura(int cura) {
		if((this.vida_atual + cura)<this.vida_total) {
			this.vida_atual += cura;
		}else {
			this.setVida_atual(this.getVida_total());
		}
	}
	
	public void atacar(Criatura alvo) { //Função para infligir dano em uma criatura inimiga.
		if(arma != null&&this.arma.getDurabilidade()>0) {
			arma.atacar(alvo,this.getForça());
		}else {
			alvo.receberDano(força); //Quem usa arma é por que não se garante no soco.
		}
	}
	
	public void movimentar(int nova_posisao) { //Função para movimentar uma criatura mais comumente usado na classe Jogador.
		posição = nova_posisao;
	}
	
	public void reviver(int totalVertices) {
		vida_atual = vida_total;
		Random random=new Random();
		int variação_posição = random.nextInt(totalVertices);
		posição = variação_posição;
	}

}
