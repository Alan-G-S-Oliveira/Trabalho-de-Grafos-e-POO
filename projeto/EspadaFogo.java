package projeto;

public class EspadaFogo extends Arma implements Efeito {
	
	public EspadaFogo() {
		this.setDano(18);
		this.setDurabilidade(3);
		this.setNome("Espada Carmesim");
		this.setDescricao("Uma espada feita de um material desconhecido de coloração carmesim,"
				+ "parece mais leve do que uma espada de ferro mas só de olhar para ela você "
				+ "consegue sentir que ela emana calor.");
	}

	@Override
	public void atacar(Criatura a, int força) {
		super.atacar(a, força);
		this.aplicarEfeito(a);
	}
	@Override
	public void aplicarEfeito(Criatura a) {
		a.setStatus(new Queimado());
	}

	@Override
	public Arma copy() {
		Arma copia=new EspadaFogo();
		copia.setDurabilidade(this.getDurabilidade());
		return copia;
	}

}
