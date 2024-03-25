package projeto;

public class Lagartas_de_fogo extends Efeito_de_terreno implements Efeito{
	public Lagartas_de_fogo() {
		setNome("Lagartas de fogo");
		setComment("você olha para os lados para cima e para baixo as largatas de fogo estão por todo lugar você joga elas para longe,"
				+ " mas a sensação de queimação e insuportável, mas você se mantém em silêncio para não chamar atenção dos mostros.");
		setDano(0);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
		p.setStatus(new Queimado());
	}
}

