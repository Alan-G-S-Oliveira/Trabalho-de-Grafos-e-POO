package projeto;

public class Apedrejamento extends Efeito_de_terreno implements Efeito{
	public Apedrejamento() {
		setNome("Ataque de nativos");
		setComment("Quando você se aproxima de um território de nativos da área eles arremessam pedras em você."
				+ " Eles não parecem querer avançar, mas é melhor se apressar.");
		setDano(10);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
	}
}

