package Itens;

import Criaturas.Criatura;

public class Maleta extends Arma {

	public Maleta(){
		this.setDano(5);
		this.setDurabilidade(3);
		this.setNome("Maleta");
	}
	
	@Override
	public void atacar(Criatura a, int força) {
		a.receberDano(força+this.getDano());
	}
	@Override
	public Arma copy() {
		return new Maleta();
	}
}
