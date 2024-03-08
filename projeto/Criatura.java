package POO;

public abstract class Criatura {
	private int vida_total; //Variavel responsavel por guardar a vida total da Criatura.
	private int vida_atual; //Variavel responsavel por guardar a vida atual da Criatura.
	private int força; //Variavel responsavel por guardar a força da Criatura.
	private Arma arma; //Variavel responsavel por guardar a arma atual da Criatura.
	private int posição; //Variavel responsavel por guardar a posição atual da Criatura.
	private Status status; //Variavel responsavel por guardar a status atual da Criatura.
	Criatura(int vida_total,int força,Arma arma,int posição,Status status){
		this.vida_total = vida_total;
		this.vida_atual = vida_total;
		this.força = força;
		this.arma = arma;
		this.posição = posição;
		this.status = status;
	}
	
	public int getVida_total() {
		return vida_total;
	}
	public void setVida_total(int vida) {
		vida_total = vida;
	}
	
	public int getVida_atual() {
		return vida_atual;
	}
	public void setVida_atual(int vida) {
		vida_atual = vida;
	}
	
	public int getForça() {
		return força;
	}
	public void setForça(int força_e) {
		força = força_e;
	}
	
	public int getPosição() {
		return posição;
	}
	public void setPosição(int posição_e) {
		posição = posição_e;
	}
	
	public Arma getArma() {
		return arma;
	}
	public void setArma(Arma arma_nova) {
		arma = arma_nova;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status_novo) {
		status = status_novo;
	}
	
	public void receberDano(int dano) { //Função para dar dano imediato de armadilhas.
		vida_atual -= dano;
	}
	
	public void atacar(Criatura alvo) { //Função para infligir dano em uma criatura inimiga.
		if(arma != null) {
			arma.atacar(alvo);
		}else {
			alvo.receberDano(força); //Quem usa arma é por que não se garante no soco.
		}
	}
	
	public void movimentar(int nova_posisao) { //Função para movimentar uma criatura mais comumente usado na classe Jogador.
		posição = nova_posisao;
	}
	
	public float renaser(int local_nacimento) { //Função de renascimento mais usada na classe monstro.
		vida_atual = vida_total;
		posição = local_nacimento;
		return local_nacimento;
	}

}
