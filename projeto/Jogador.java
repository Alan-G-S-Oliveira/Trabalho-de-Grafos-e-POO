package POO;

public class Jogador extends Criatura {
	int tesouro; //Variavel responsavel por guardar a quantidade de tesouro.
	Arma arma_check; //Variavel responsavel por guardar a arma que o jogador esta usando no checkpoint.
	int vida_atual_check; //Variavel responsavel por guardar a vida atual do jogador no checkpoint.
	No posisao_check; //Variavel responsavel por guardar a posição do jogador no checkpoint.
	Colar colar; //Variavel responsavel por guardar o colar que o jogador esta utilisando.
	Colar colar_check; ////Variavel responsavel por guardar a colar que o jogador esta usando no checkpoint.
	No checkPoint; //Variavel responsavel por guardar o ultimo checkpoint que o jogador pegou.
	
	Jogador(int vida_total,int forsa,Arma arma,No posisao,Status status,Colar colar,No checkPoint){
		super(vida_total,forsa,arma,posisao,status);
		this.chekPoint = checkPoint;
		this.vida_atual_check = vida_total;
		this.arma_check = arma;
		this.posisao_check = posisao;
		this.tesouro = 0;
		this.colar = colar;
		this.colar_check = colar;
	}
	
	public void salvar_check() { //salva o estado do jogador ao passar pelo checkpoint.
		posisao_check = posisao;
		arma_check = arma;
		vida_atual_check = vida_atual;
		colar_check = colar;
	}
	
	public No renacer() { //Função responsavel por reviver o jogador.
		posisao = posisao_check;
		arma = arma_check;
		vida_atual = vida_atual_check;
		return posisao;
	}
}
