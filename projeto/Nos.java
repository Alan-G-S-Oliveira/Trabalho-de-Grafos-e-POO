package projeto;

public class Nos {

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

    }

    public Nos() {

        this.efeito = null;
        this.riqueza = null;
        this.arma = null;
        this.tesouro = false;
        this.checkpoint = false;

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

}
