package recompensas;

import Criaturas.Jogador;

public class Tesouro implements Riqueza {

	@Override
	public Object receberRiqueza(Jogador a) {
		int valor=a.getVida_atual()-a.getArma().getDano();
		if (valor<=0) {
			a.setTesouro(valor);
		}
		return null;
	}

}
