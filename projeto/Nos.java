package projeto;

import java.util.ArrayList;

public class Nos {

    private ArrayList<Criatura> criaturas;
    private Efeito efeito;
    private Riqueza riqueza;
    private ArrayList<Arma> armas;

    private boolean tesouro;
    private boolean checkpoint;

    //Construtor da classe
    public Nos(Efeito efeito, Riqueza riqueza, boolean tesouro) {

        this.efeito = efeito;
        this.tesouro = tesouro;
        this.riqueza = riqueza;
        this.checkpoint = false;
        this.criaturas = new ArrayList<>();
        this.armas = new ArrayList<>();

    }

    public Nos() {

        this.efeito = null;
        this.riqueza = null;
        this.tesouro = false;
        this.checkpoint = false;
        this.criaturas = new ArrayList<>();
        this.armas = new ArrayList<>();

    }
    
    //Conjunto de gets
    public Efeito getEfeito() {

        return this.efeito;

    }

    public Riqueza getRiqueza() {

        return this.riqueza;

    }

    public ArrayList<Arma> getArmas() {

        return this.armas;

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

    public void addArma(Arma arma) {

        this.armas.add(arma);

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

        return this.criaturas.remove(mob);

    }

    public boolean removeArma(Arma arma) {

        return  this.armas.remove(arma);

    }

}
