package projeto;

public class Armadilha_de_dardos extends Efeito_de_terreno implements Efeito{
	public Armadilha_de_dardos() {
		setNome("Armadilha de dardos");
		setComment("Dardos são lançados em direção pelas arvores não da para saber sé são nativos ou uma armadilha altomatica.");
		setDano(2);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
		p.setStatus(new Envenenado());;
	}
}