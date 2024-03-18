package projeto;

public class ColarCura extends Colar {
	private int cura=10;
	
	public ColarCura() {
		this.setNome("Benção de Rafael");
		this.setDescricao("Um misterioso colar lindamente confeccionado com uma beleza"
				+ " simples mas ou mesmo tempo angelical, ao segura-lo você sente energia"
				+ " restauradora partindo dele.");
	}

	@Override
	public void aplicarEfeito(Criatura a) {
		a.receberCura(this.getCura());
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
