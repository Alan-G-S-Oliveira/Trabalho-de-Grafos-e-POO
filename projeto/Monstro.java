package POO;
import java.util.Random;

public class Monstro extends Criatura {
	String nome; //Variavel responsavel por guardar o nome atual do Monstro.
	Random random = new Random();
	
	Monstro(int vida_total,int forsa,Arma arma,No posisao,Status status,String nome){
		super(vida_total,forsa,arma,posisao,status);
		this.nome = nome;
	}
	
	public No movimentar() { //Movimentação do monstro.
		int ficar = random.nextInt(10); //Gera a chance de um decimo do monstro ficar parado.
		if(ficar>0) { //Se ele se mover ele pegar uma posição aleatoria do array list adijasentes para o mostro se movimentar.
			int tamanho = posisao.adjancencias.size();
			ficar = random.nextInt(tamanho);
			posisao = posisao.adjancencias.get(ficar);
		}
		return posisao;
	}
}
