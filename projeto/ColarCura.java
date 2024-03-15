package projeto;

public class ColarCura extends Colar {
	private int cura;
	
	public ColarCura() {
		this.setNome("Benção de Rafael");
		this.setDescricao("Um misterioso colar lindamente confeccionado com uma beleza"
				+ " simples mas ou mesmo tempo angelical, ao segura-lo você sente energia"
				+ " restauradora partindo dele.");
	}

	@Override
	public void aplicarEfeito(Criatura a) {
		a.curar(this.getCura()); //implemento curar depois pra n dar conflito de merge.
	}

	@Override
	public Colar copy() {
		Colar copia=new ColarCura();
		return copia;
	}

	public int getCura() {
		return cura;
	}

}
