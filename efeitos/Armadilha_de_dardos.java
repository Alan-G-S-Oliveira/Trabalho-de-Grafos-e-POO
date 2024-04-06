package efeitos;

import Criaturas.Criatura;

public class Armadilha_de_dardos extends Efeito_de_terreno implements Efeito{
	public Armadilha_de_dardos() {
		setNome("Armadilha de dardos");
		setComment("Dardos são lançados em direção pelas árvores não dá para saber se são nativos ou uma armadilha automática."+
					"O veneno entrou em sua corrente sanguínea e causará um pequeno dano.");
		setDano(2);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
		p.setStatus(new Envenenado());
	}
}