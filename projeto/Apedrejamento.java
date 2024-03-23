package projeto;

public class Apedrejamento extends Efeito_de_terreno implements Efeito{
	public Apedrejamento() {
		setNome("Ataque de nativos");
		setDescriptor("quando voce se aproxima de um território de nativos da area eles aremasam pedras em você."
				+ " Eles não paresem querer avançar, mas e melhor se apressar.");
		setDano(10);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano);
	}
}

