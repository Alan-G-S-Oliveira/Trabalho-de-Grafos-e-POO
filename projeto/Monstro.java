package projeto;
import java.util.Random;
import java.util.ArrayList;

public class Monstro extends Criatura {
	String nome; //Variavel responsavel por guardar o nome atual do Monstro.
	String description;
	Random random = new Random();
	
	Monstro(){ //refazer
		int monstro = random.nextInt(4);
		switch (monstro) {
		case 0: {
			this.nome = "Captain P.";
			this.description = ("Um ornitorrinco comum, mascote de uma família peculiar, porém discreta, secretamente trabalha"
					+"para uma organização secreta de animais de estimação piratas do mal. Eles eventualmente se reúnem"
					+" em uma ilha, mas o ornitorrinco, que tem dificuldade com datas, chegou um mês antes"
					+" do previsto e agora teme você descubra o local da reunião.");
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(75+variação_vida);
			setVida_atual(75+variação_vida);
			setForça(10+variação_força);
			setArma(new Rapiera());
			setStatus(null);
		}
		case 1:{
			this.nome = "Ornitohulk";
			this.description = ("Um ornitorrinco capturado pelo Dr. Bruce Banner para testar um composto que supostamente removeria seus poderes."
					+ ". Infelizmente, o soro apenas reduziu significativamente os efeitos da radiação, deixando o ornitorrinco mais fraco, porém"
					+ "muito mais irritado. Com medo de que o ornitorrinco destruísse cidades,"
					+ " ele foi levado para uma ilha inabitada por seu amigo Stark.");
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(100+variação_vida);
			setVida_atual(100+variação_vida);
			setForça(20+variação_força);
			setArma(new Garra());
			setStatus(null);
		}
		case 2:{
			this.nome = "Ornitorrinco super saiyajin";
			this.description = ("Um ornitorrinco extremamente incomum, criado em laboratório pelo Dr. Hedo, com o objetivo de desenvolver"
					+"um super-herói, tão poderoso quanto um sayajin e com habilidades de fogo, simplesmente por achar isso legal. No"
					+" entanto, após a conclusão do experimento, o ornitorrinco não estava interessadoem uma vida agitada de super-herói"
					+ " para uma ilha isolada em busca de paz e tranquilidade. Lá, ele desfruta de uma vida pacífica, mas sua herança"
					+ " genética sayajin faz com que ele não tolere qualquer intruso em seu caminho.");
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(175+variação_vida);
			setVida_atual(175+variação_vida);
			setForça(12+variação_força);
			setArma(new Ki());
			setStatus(null);
		}
		case 3:{
			this.nome = "Ornitorrinco otorrinolaringologista";
			this.description = ("Já se perguntou por que os monstros se curam com o tempo? A culpa é deste ornitorrinco que veio para"
					+ " esta ilha unicamente para ajudar os outros, independentemente da espécie. No entanto, infelizmente, quando você"
					+ " encontrou essa majestosa criatura, cometeu um erro muito grave ao chamá-la de ornitorrinco, o que fez com que"
					+ " ele jurasse te matar por não ter se referido a ele como doutor.");
			int variação_vida = random.nextInt(25);
			int variação_força = random.nextInt(10);
			setVida_total(25+variação_vida);
			setVida_atual(25+variação_vida);
			setForça(8+variação_força);
			setArma(new Maleta());
			setStatus(null);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + 4);
		}
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void movimentar(ArrayList<Integer> adijacencias) { //Movimentação do monstro.
		int ficar = random.nextInt(10); //Gera a chance de um decimo do monstro ficar parado.
		if(ficar>0) { //Se ele se mover ele pegar uma posição aleatoria do array list adijasentes para o mostro se movimentar.
			int tamanho = adijacencias.size();
			ficar = random.nextInt(tamanho);
			setPosição(adijacencias.get(ficar));
			
		}
	}
}