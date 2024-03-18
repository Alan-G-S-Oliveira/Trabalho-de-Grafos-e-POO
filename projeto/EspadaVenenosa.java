package projeto;

public class EspadaVenenosa extends Arma implements Efeito {
	
	public EspadaVenenosa() {
		this.setDano(23);
		this.setDurabilidade(3);
		this.setNome("Espada traiçoeira");
		this.setDescricao("Uma espada aparentemente feita de ferro com o desenho de uma cobra"
				+ " indo do protetor da espada até a ponto, além de estranhas ranhuras "
				+ "diagonais ao fio da lamina de onde você pode ver um misterioso liquido"
				+ "escorrer.");
	}

	@Override
	public void atacar(Criatura a, int força) {
		super.atacar(a, força);
		this.aplicarEfeito(a);
	}
	@Override
	public void aplicarEfeito(Criatura a) {
		a.setStatus(new Envenenado());
	}

	@Override
	public Arma copy() {
		Arma copia=new EspadaVenenosa();
		copia.setDurabilidade(this.getDurabilidade());
		return copia;
	}

}
