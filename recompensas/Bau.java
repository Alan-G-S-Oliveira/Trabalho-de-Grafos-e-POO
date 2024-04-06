package recompensas;

import java.util.Random;

import Criaturas.Jogador;
import Itens.ColarCura;
import Itens.EspadaFerro;
import Itens.EspadaFogo;
import Itens.EspadaVenenosa;

public class Bau implements Riqueza {
	private boolean aberto;
	
	private Object sortear() {
		Random random = new Random();
		int a=random.nextInt(4);
		switch(a) {
		case 0:
			return new EspadaFerro();
		case 1:
			return new EspadaFogo();
		case 2:
			return new EspadaVenenosa();
		case 3:
			return new ColarCura();
		default:
			return null;
		}
	}
	@Override
	public Object receberRiqueza(Jogador a) {
		if(this.aberto) {
			return null;
		}
		else {
			aberto=true;
			return this.sortear();
		}
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

}
