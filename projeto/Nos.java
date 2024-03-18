package projeto;

import java.util.ArrayList;

public class Nos {

    private ArrayList<Criatura> criaturas;
    private Efeito efeito;
    private Riqueza riqueza;
    private Arma arma;

    private boolean tesouro;
    private boolean checkpoint;

    //Construtor da classe
    public Nos(Efeito efeito, Riqueza riqueza, boolean tesouro) {

        this.efeito = efeito;
        this.tesouro = tesouro;
        this.riqueza = riqueza;
        this.arma = null;
        this.checkpoint = false;
        this.criaturas = new ArrayList<>();

    }

    public Nos() {

        this.efeito = null;
        this.riqueza = null;
        this.arma = null;
        this.tesouro = false;
        this.checkpoint = false;
        this.criaturas = new ArrayList<>();

    }
    
    //Conjunto de gets
    public Efeito getEfeito() {

        return this.efeito;

    }

    public Riqueza getRiqueza() {

        return this.riqueza;

    }

    public Arma getArma() {

        return this.arma;

    }
        
    public boolean isCheckPoint() {

        return this.checkpoint;

    }

    public boolean isTesouro() {

        return this.tesouro;

    }

    public ArrayList<Criatura> getCriaturas() {

        return this.criaturas;

    }

    //Conjunto de sets
    public void setEfeito(Efeito efeito) {

        this.efeito = efeito;

    }

    public void setRiquesa(Riqueza riqueza) {

        this.riqueza = riqueza;

    }

    public void setArma(Arma arma) {

        this.arma = arma;

    }

    public void updateCheckPoint(boolean checkpoint) {

        this.checkpoint = checkpoint;

    }
    
    public void setTesouro(boolean tesouro) {

        this.tesouro = tesouro;

    }
    
    public void addCriaturas(Criatura criatura) {

        this.criaturas.add(criatura);

    }

    public boolean removeCriatura(Criatura mob) {

        return criaturas.remove(mob);

    }

}
