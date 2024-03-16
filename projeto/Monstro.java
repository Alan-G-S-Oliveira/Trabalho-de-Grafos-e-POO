package projeto;
import java.util.Random;
import java.util.ArrayList;

public class Monstro extends Criatura {
	String nome; //Variavel responsavel por guardar o nome atual do Monstro.
	Random random = new Random();
	
	Monstro(){ //refazer
		int mostro = random.nextInt(4);
		switch (4) {
		case 0: {
			this.nome = "depois eu penso em uns nomes legais";
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(100+variação_vida);
			setVida_atual(100+variação_vida);
			setForça(20+variação_força);
			setArma(new Arma());
			setStatus(null);
		}
		case 2:{
			this.nome = "depois eu penso em uns nomes legais";
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(100+variação_vida);
			setVida_atual(100+variação_vida);
			setForça(20+variação_força);
			setArma(new Arma());
			setStatus(null);
		}
		case 3:{
			this.nome = "depois eu penso em uns nomes legais";
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(100+variação_vida);
			setVida_atual(100+variação_vida);
			setForça(20+variação_força);
			setArma(new Arma());
			setStatus(null);
		}
		case 4:{
			this.nome = "depois eu penso em uns nomes legais";
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(100+variação_vida);
			setVida_atual(100+variação_vida);
			setForça(20+variação_força);
			setArma(new Arma());
			setStatus(null);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + 4);
		}
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void movimentar() { //Movimentação do monstro.
		ArrayList<Integer> adijacencias = new ArrayList<>();
		int ficar = random.nextInt(10); //Gera a chance de um decimo do monstro ficar parado.
		if(ficar>0) { //Se ele se mover ele pegar uma posição aleatoria do array list adijasentes para o mostro se movimentar.
			adijacencias = getAdjacencias(getPosição());
			int tamanho = adijacencias.size();
			ficar = random.nextInt(tamanho);
			setPosição(adijacencias.get(ficar));
			
		}
	}
}