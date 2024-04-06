package projeto;

public class Lagartas_de_fogo extends Efeito_de_terreno implements Efeito{
	public Lagartas_de_fogo() {
		setNome("Lagartas de fogo");
		setComment("Você olha ao redor, para cima e para baixo, as larvas de fogo estão por toda parte."
					+" Desesperadamente, você as afasta, mas a sensação de queimação é insuportável."
					+"Você se mantém em silêncio, temendo chamar a atenção dos monstros."
					+" O calor intenso deixou uma queimadura que continuará a causar danos até que ela se estabilize.");
		setDano(0);
	}
	public void aplicarEfeito(Criatura p){
		p.receberDano(getDano());
		p.setStatus(new Queimado());
	}
}

