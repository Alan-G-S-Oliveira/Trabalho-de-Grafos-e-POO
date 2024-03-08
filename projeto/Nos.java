package projeto;

import java.util.ArrayList;

public class Nos {

    private ArrayList<Criatura> criaturas = new ArrayList<Criatura>();
    private Efeito efeito;

    private Riqueza tesouro;

    public Nos(Criatura criaturas, Efeito efeito, boolean tesouro) {

        this.criaturas.add(criaturas);
        this.efeito = efeito;
        this.tesouro = tesouro;

    }

    public ArrayList<Criatura> getCriaturas() {

        return this.criaturas;

    }

    public Efeito getEfeito() {

        return this.efeito;

    }
    
    public void batalhar() {

        

    }

}
