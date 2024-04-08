package projeto;

import java.util.ArrayList;

import Criaturas.Criatura;
import Itens.Arma;
import efeitos.Efeito_de_terreno;
import recompensas.Riqueza;

public class Nos {

    private ArrayList<Criatura> criaturas;
    private Efeito_de_terreno efeito;
    private Riqueza riqueza;
    private ArrayList<Arma> armas;

    private boolean tesouro;
    private boolean checkpoint;

    public Nos() {

        this.efeito = null;
        this.riqueza = null;
        this.tesouro = false;
        this.checkpoint = false;
        this.criaturas = new ArrayList<>();
        this.armas = new ArrayList<>();

    }
    
    //Conjunto de gets
    public Efeito_de_terreno getEfeito() {

        return this.efeito;

    }

    public Riqueza getRiqueza() {

        return this.riqueza;

    }

    public ArrayList<Arma> getArmas() {

        return this.armas;

    }

    public ArrayList<Criatura> getCriaturas() {

        return this.criaturas;

    }
     
    //Conjunto de is
    public boolean isCheckPoint() {

        return this.checkpoint;

    }

    public boolean isTesouro() {

        return this.tesouro;

    }

    //Conjunto de sets
    public void setEfeito(Efeito_de_terreno efeito) {

        this.efeito = efeito;

    }

    public void setRiqueza(Riqueza riqueza) {

        this.riqueza = riqueza;

    }

    public void setTesouro(boolean tesouro) {

        this.tesouro = tesouro;

    }

    //adds e updates
    public void addArma(Arma arma) {

        this.armas.add(arma);

    }

    public void addCriaturas(Criatura criatura) {

        this.criaturas.add(criatura);

    }

    public void updateCheckPoint(boolean checkpoint) {

        this.checkpoint = checkpoint;

    }

    //Conjunto de removes
    public boolean removeCriatura(Criatura mob) {

        return this.criaturas.remove(mob);

    }

    public boolean removeArma(Arma arma) {

        return  this.armas.remove(arma);

    }

}
