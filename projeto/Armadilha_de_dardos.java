package projeto;

public class Armadilha_de_dardos extends Efeito_de_terreno implements Efeito{
	public Armadilha_de_dardos() {
		setNome("Armadilha de dardos");
		setComment("Dardos são lançados em direção pelas arvores não da para saber sé são nativos ou uma armadilha automatica."+
					"O veneno entrou em sua corrente sanguinea e causara um pequeno");
		setDano(2);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
		p.setStatus(new Envenenado());
	}
}