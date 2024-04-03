package projeto;

import java.util.Random;

public class Garra extends Arma {
	public Garra(){
		this.setDano(10);
		this.setDurabilidade(3);
		this.setNome("Garra");
	}
	
	@Override
	public void atacar(Criatura a, int força) {
		a.receberDano(força+this.getDano());
		Random random = new Random();
		int aux=random.nextInt(10);
		if(aux>6) {
			a.setStatus(new Envenenado());
		}
	}
	@Override
	public Arma copy() {
		return new Rapiera();
	}
}
