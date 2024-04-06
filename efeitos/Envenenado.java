package efeitos;

import Criaturas.Criatura;

public class Envenenado extends Status {

	public Envenenado() {
		this.setNome("Envenenado");
		this.setDescricao("O veneno entrou em sua corrente sanguinea e causara um pequeno"
				+ " dano por um longo tempo");
		this.setDano(6);
		this.setDuracao(6);
	}
	
	@Override
	public Status copy() {
		Status copia= new Envenenado();
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
