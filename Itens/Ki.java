package Itens;

import java.util.Random;

import Criaturas.Criatura;
import efeitos.Queimado;

public class Ki extends Arma {
	public Ki(){
		this.setDano(12);
		this.setDurabilidade(3);
		this.setNome("Ki flamejante");
	}
		
	@Override
	public void atacar(Criatura a, int força) {
		a.receberDano(força+this.getDano());
		Random random = new Random();
		int aux=random.nextInt(10);
		if(aux>2) {
			a.setStatus(new Queimado());
		}
	}
	@Override
	public Arma copy() {
		return new Ki();
	}
}

