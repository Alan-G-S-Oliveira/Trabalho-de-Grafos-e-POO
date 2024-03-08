package POO;

public class Jogador extends Criatura {
	int tesouro; //Variavel responsavel por guardar a quantidade de tesouro.
	Arma arma_check; //Variavel responsavel por guardar a arma que o jogador esta usando no checkpoint.
	int vida_atual_check; //Variavel responsavel por guardar a vida atual do jogador no checkpoint.
	int posisao_check; //Variavel responsavel por guardar a posição do jogador no checkpoint.
	Colar colar; //Variavel responsavel por guardar o colar que o jogador esta utilisando.
	Colar colar_check; ////Variavel responsavel por guardar a colar que o jogador esta usando no checkpoint.
	int checkPoint; //Variavel responsavel por guardar o ultimo checkpoint que o jogador pegou.
	Status status_check;
	
	Jogador(int vida_total,int forsa,Arma arma,int posisao,Status status,Colar colar,int checkPoint){
		super(vida_total,forsa,arma,posisao,status);
		this.checkPoint = checkPoint;
		this.vida_atual_check = vida_total;
		this.arma_check = arma;
		this.posisao_check = posisao;
		this.tesouro = 0;
		this.colar = colar;
		this.colar_check = colar;
	}
	
	public int gettesouro() {
		return tesouro;
	}
	public void settesouro(int tesouro) {
		this.tesouro = tesouro;
	}
	
	public Colar getColar() {
		return colar;
	}
	public void setVida_total(int colar) {
		this.colar = colar;
	}
	
	public int getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(int checkPoint) {
		this.checkPoint = checkPoint;
	}
	
	public void salvar_check() { //salva o estado do jogador ao passar pelo checkpoint.
		posisao_check = getPosição();
		arma_check = this.getArma().copy();
		vida_atual_check = getVida_atual();
		colar_check = colar;
		status_check=this.getStatus().copy();
	}
	
	public void renacer() { //Função responsavel por reviver o jogador.
		setPosição(posisao_check);
		setArma(arma_check);
		setVida_atual(vida_atual_check);
		colar = colar_check;
	}
}
