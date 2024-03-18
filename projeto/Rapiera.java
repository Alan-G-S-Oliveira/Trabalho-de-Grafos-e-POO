package projeto;

public class Rapiera extends Arma {
	
	public Rapiera(){
		this.setDano(25);
		this.setDurabilidade(3);
		this.setNome("Rapiera");
	}
	
	@Override
	public void atacar(Criatura a, int força) {
		a.receberDano(força+this.getDano());
	}
	@Override
	public Arma copy() {
		return new Rapiera();
	}
}
