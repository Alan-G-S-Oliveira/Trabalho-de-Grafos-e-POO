package projeto;

public class EspadaFerro extends Arma {

	public EspadaFerro(){
		this.setDano(25);
		this.setDurabilidade(3);
		this.setNome("Espada de ferro");
		this.setDescricao("Uma espada feita de ferro, parece afiada mas nada fora do normal.");
	}
	@Override
	public Arma copy() {
		 EspadaFerro copia= new EspadaFerro();
		return copia;
	}

}
