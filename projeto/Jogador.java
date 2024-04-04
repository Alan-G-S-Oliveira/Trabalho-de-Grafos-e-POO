package projeto;

public class Jogador extends Criatura {
	int tesouro; //Variavel responsavel por guardar a quantidade de tesouro.
	Arma arma_check; //Variavel responsavel por guardar a arma que o jogador esta usando no checkpoint.
	int vida_atual_check; //Variavel responsavel por guardar a vida atual do jogador no checkpoint.
	int posisao_check; //Variavel responsavel por guardar a posição do jogador no checkpoint.
	Colar colar; //Variavel responsavel por guardar o colar que o jogador esta utilisando.
	Colar colar_check; ////Variavel responsavel por guardar a colar que o jogador esta usando no checkpoint.
	Status status_check;
	
	Jogador(int vida_total,int forca,Arma arma,int posisao,Status status,Colar colar){
		super(vida_total,forca,arma,posisao,status);
		this.vida_atual_check = vida_total;
		this.arma_check = arma;
		this.posisao_check = -1;
		this.tesouro = 0;
		this.colar = colar;
		this.colar_check = colar;
	}
	
	public int getTesouro() {
		return tesouro;
	}
	public void setTesouro(int tesouro) {
		this.tesouro = tesouro;
	}
	
	public Colar getColar() {
		return colar;
	}
	public void setColar(Colar colar) {
		this.colar=colar;
	}
	public int getCheckPoint() {
		return posisao_check;
	}
	public void setCheckPoint(int checkPoint) {
		this.posisao_check = checkPoint;
	}
	
	public void salvar_check() { //salva o estado do jogador ao passar pelo checkpoint.
		posisao_check = getPosição();
		if(this.getArma() != null)
			arma_check = this.getArma().copy();
		vida_atual_check = getVida_atual();
		if(this.getColar() != null)
			colar_check = this.getColar().copy();
		if(this.getStatus() != null)
			 status_check=this.getStatus().copy();
	}
	
	public boolean reviver() { //Função responsavel por reviver o jogador.
		if(this.posisao_check>=0) {
			setPosição(posisao_check);
			setArma(arma_check);
			setVida_atual(vida_atual_check);
			setTesouro(0);
			colar = colar_check;
			this.posisao_check = -1;
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void receberDano(int a) {
		super.receberDano(a);
		if(tesouro>getVida_atual()) {
			tesouro=getVida_atual();
		}
	}
}
