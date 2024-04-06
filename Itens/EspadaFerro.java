package Itens;

public class EspadaFerro extends Arma {

	public EspadaFerro(){
		this.setDano(25);
		this.setDurabilidade(3);
		this.setNome("Espada de ferro");
		this.setDescricao("Uma espada feita de ferro, parece afiada mas nada fora do normal.");
	}
	@Override
	public Arma copy() {
		Arma copia= new EspadaFerro();
		copia.setDurabilidade(this.getDurabilidade());
		return copia;
	}

}
