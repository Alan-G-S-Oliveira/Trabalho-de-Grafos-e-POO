package POO;
import java.util.Random;

public class Monstro extends Criatura {
	String nome; //Variavel responsavel por guardar o nome atual do Monstro.
	Random random = new Random();
	
	Monstro(int vida_total,int força,Arma arma,int posição,Status status,String nome){
		super(vida_total,força,arma,posição,status);
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome_novo) {
		nome = nome_novo;
	}
	
	public void movimentar(int adijacencias[]) { //Movimentação do monstro.
		int ficar = random.nextInt(10); //Gera a chance de um decimo do monstro ficar parado.
		if(ficar>0) { //Se ele se mover ele pegar uma posição aleatoria do array list adijasentes para o mostro se movimentar.
			int tamanho = adijacencias.length;
			ficar = random.nextInt(tamanho);
			setPosição(ficar);
		}
	}
}
