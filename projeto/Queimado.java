package projeto;

public class Queimado extends Status {
	
	public Queimado() {
		this.setNome("Queimado");
		this.setDescricao("O calor criou uma queimadura que causarar dano até ela estabilizar");
		this.setDano(12);
		this.setDuracao(2);
	}
	
	@Override
	public Status copy() {
		Queimado copia= new Queimado();
		copia.setDuracao(this.getDuracao());
		return copia;
	}

	@Override
	public void aplicarEfeito(Criatura a) {
		a.receberDano(this.getDano());
		this.setDuracao(this.getDuracao()-1);
		if (this.getDuracao()==0) {
			a.setStatus(null);
		}
	}

}
