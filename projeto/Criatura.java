package POO;

public class Criatura {
	private int vida_total; //Variavel responsavel por guardar a vida total da Criatura.
	protected int vida_atual; //Variavel responsavel por guardar a vida atual da Criatura.
	private int forca; //Variavel responsavel por guardar a força da Criatura.
	protected Arma arma; //Variavel responsavel por guardar a arma atual da Criatura.
	protected No posisao; //Variavel responsavel por guardar a posição atual da Criatura.
	private Status status; //Variavel responsavel por guardar a status atual da Criatura.
	Criatura(int vida_total,int forsa,Arma arma,No posisao,Status status){
		this.vida_total = vida_total;
		this.vida_atual = vida_total;
		this.forca = forsa;
		this.arma = arma;
		this.posisao = posisao;
		this.status = status;
	}
	
	public void receberDano(int dano) { //Função para dar dano imediato de armadilhas.
		vida_atual -= dano;
	}
	
	public void atacar(Criatura alvo) { //Função para infligir dano em uma criatura inimiga.
		if(arma != null) {
			arma.atacar(alvo);
		}else {
			alvo.vida_atual -= forca; //Quem usa arma é por que não se garante no soco.
		}
	}
	
	public No movimentar(No nova_posisao) { //Função para movimentar uma criatura mais comumente usado na classe Jogador.
		posisao = nova_posisao;
		return nova_posisao;
	}
	
	public No renaser(No local_nacimento) { //Função de renascimento mais usada na classe monstro.
		vida_atual = vida_total;
		posisao = local_nacimento;
		return local_nacimento;
	}

}
