package projeto;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Jogo {
    
    private Grafo ilha;
    private int tempo;
    private int resultado;
    private boolean reviveu;

    private int[] marca;
    private int[] marcaCheckPoint;
    private ArrayList<Integer> pilhaCheckPoint;
    private ArrayList<Integer> pilha;

    public Jogo(String caminhoArquivo) throws FileNotFoundException {

        ilha = new Grafo(caminhoArquivo);
        tempo = 3 * ilha.getTotalVertices();
        resultado = 0;
        reviveu = true;

    }

    public Grafo getIlha() {

        return this.ilha;

    }
    public int getTesouro() {

        return this.getJogador().getTesouro();

    }

    public boolean hasBau() {

        boolean teste;
        Bau aux = ilha.getNo(getJogador().getPosição()).getRiqueza();

        if(aux != null) {
            if(aux.isAberto())
                teste = false;
            else
                teste = true;
        } else  
            teste = false;
        
        return teste;

    }

    public void itemBau() {

        No noAtual = ilha.getNo(getJogador().getPosição());
        Bau bau = noAtual.getRiqueza();
        Object item = bau.receberRiqueza();

        if(item instanceof Arma)
            noAtual.addArma((Arma) item);
        else
            getJogador().setColar((Colar) item);

    }

    public int getTempo() {

        return this.tempo;

    }

    public int getResultado() {

        return this.resultado;

    }

    public Jogador getJogador() {

        return this.ilha.getJogador();

    }

    public ArrayList<Arma> getArmas() {
    	int no=ilha.getJogador().getPosição();
        return this.ilha.getNo(no).getArmas();

    }

    public int getVidaJogador() {

        return this.getJogador().getVida_atual();

    }

    public int getCriatura() {

        Criatura b = null;
        ArrayList<Criatura> aux = ilha.getNo(ilha.getJogador().getPosição()).getCriaturas();

        for(int i = 0; i < aux.size(); i++) {

            if(aux.get(i) instanceof Monstro && !aux.get(i).getBatalhou()){
                b = aux.get(i);
                break;
            }

        }

        if(b == null)
            return -1;
        Monstro a= (Monstro)b;
        if (a.getNome()=="Captain P.")
            return 0;
        if (a.getNome()=="Ornitohulk")
            return 1;
        if (a.getNome()=="Ornitorrinco super saiyajin")
            return 2;
        if (a.getNome()=="Ornitorrinco otorrinolaringologista")
            return 3;
        return -1;
    }

    //ACHO QUE TÁ DE BOA ESSE MÉTODO. QUALQUER COISA MUDO DEPOIS.
    public ArrayList<Integer> copy() {

        ArrayList<Integer> saida = new ArrayList<>();
        
        for(int i = 0; i < pilha.size(); i++)
            saida.add(pilha.get(i));

        return saida;

    }

    //Escolhe de forma aleatória a primeira posição para qual o jogador vai se mover a partir da praia.
    public void inicial() {

        marca = new int[ilha.getTotalVertices()];
        pilhaCheckPoint =  new ArrayList<>();
        pilha = new ArrayList<>();

        Random num = new Random();       

        for(int i = 0; i < marca.length; i++)
            marca[i] = 0;
        
        int v = num.nextInt(ilha.getAdjacencias(0).size());

        pilha.add(0);
        marca[0] = 1;
        marca[ilha.getAdjacencias(0).get(v)] = 1;
        pilha.add(ilha.getAdjacencias(0).get(v));
        
    }

    //Escolhe o próximo movimento do jogador e move todas as crituras.
    public void turno() {
        
        if(verificaResultado() != 0)
            return;

        int v;

        System.out.println(ilha.getJogador().getTesouro() == 0);
        System.out.println(ilha.getJogador().getPosição());
        System.out.println(pilha);
        for(int i: marca)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Vida: " + getVidaJogador());

        if(ilha.getJogador().getTesouro() == 0) {

            v = pilha.get(pilha.size()-1);
            ilha.movimentoNormal(v, marca, pilha);
            ilha.movimentarCriaturas(v);
                
        } else {

            v = pilha.remove(pilha.size()-1);
            ilha.movimentarCriaturas(v);

        }

        if(ilha.getNo(v).isCheckPoint()) {

            ilha.getNo(v).updateCheckPoint(false);
            ilha.getJogador().salvar_check();
            pilhaCheckPoint = copy();
            marcaCheckPoint = marca.clone();

        }

        if(ilha.getNo(ilha.getJogador().getPosição()).isTesouro())
            ilha.getJogador().setTesouro(ilha.getJogador().getVida_atual());

        System.out.println("Criaturas " + ilha.getNo(v).getCriaturas().size());

        this.tempo--;

    }

    //Prepara as criaturas que vão batalhar e faz a batalha.
    public String prepararBatalha(boolean fuga) {

        ArrayList<Criatura> aux;
        String saida = "";

        for(int i = 0; i < ilha.getTotalVertices(); i++) {

            aux = ilha.getNo(i).getCriaturas();
            if(naoBatalhou(aux) > 1) { 

                removeMenores(aux);     //Remove as criaturas com menor vida que ainda não batalharam.
                int[] batalha = buscaCriaturas(aux);

                if((!(aux.get(batalha[0]) instanceof Jogador) && !(aux.get(batalha[1]) instanceof Jogador)) || !fuga)
                    saida += batalhar(aux.get(batalha[0]), aux.get(batalha[1]));
                else
                    turno();

            }

        }

        for(int i = 0; i < ilha.getCriaturas().size(); i++) 
            ilha.getCriaturas().get(i).setBatalhou(false);

        return saida;

    }

    public String batalhar(Criatura mob1, Criatura mob2) {

        int x = mob1.getPosição();
        String saida = "";

        for(int i = 0; i < 3; i++) {
            saida += infoBatalha(mob1, mob2);

            if(mob2.getVida_atual() <= 0) {

                ilha.getNo(x).removeCriatura(mob2);

                if(mob2 instanceof Jogador) {

                    reviveu = ((Jogador) mob2).reviver();
                    if(reviveu) {

                        pilha = pilhaCheckPoint;
                        marca = marcaCheckPoint;
                        pilhaCheckPoint = null;
                        marcaCheckPoint = null;

                    }

                    break;

                } else
                    reviveu = mob2.reviver(ilha.getTotalVertices());

                ilha.getNo(mob2.getPosição()).addCriaturas(mob2);
                
            }

            saida += infoBatalha(mob2, mob1);

            if(mob1.getVida_atual() <= 0) {

                ilha.getNo(x).removeCriatura(mob1);

                if(mob1 instanceof Jogador) {

                    reviveu = ((Jogador) mob1).reviver();
                    if(reviveu){

                        pilha = pilhaCheckPoint;
                        marca = marcaCheckPoint;
                        pilhaCheckPoint = null;
                        marcaCheckPoint = null;

                    }

                    break;

                } else
                    reviveu =  mob1.reviver(ilha.getTotalVertices());

                ilha.getNo(mob1.getPosição()).addCriaturas(mob1);
            
            }
        }

        return saida;

    }

    private String infoBatalha(Criatura mob1, Criatura mob2) {

        String saida = "";
        Status aux = mob2.getStatus();

        mob1.atacar(mob2);

        if(mob1 instanceof Jogador)
            saida += "O jogador ";
        else
            saida += ((Monstro) mob1).getNome() + " ";
            
        saida += "atacou ";

        if(mob2 instanceof Jogador)
            saida += "o jogador ";
        else
            saida += "o " + ((Monstro) mob2).getNome() + " ";

        saida += "causando " + mob1.getDano() + " de dano.";

        if(!(mob1.getArma() instanceof Rapiera) && !(mob1.getArma() instanceof EspadaFerro)) {

            if(mob2.getStatus() != aux)
                saida += mob2.getStatus().getDescricao();

        }

        if(mob1 instanceof Monstro && mob2 instanceof Monstro)
            saida = "";

        return saida;

    }

    public boolean aplicarEfeitos() {

        for(int i = 0; i < ilha.getCriaturas().size(); i++) {

            Criatura aux1 = ilha.getCriaturas().get(i);
            if(!(ilha.getCriaturas().get(i) instanceof Jogador)) {

                Efeito_de_terreno aux2 = ilha.getNo(aux1.getPosição()).getEfeito();
                if(aux2 != null)
                    aux2.aplicarEfeito(aux1);

            }

        }

        if(ilha.getNo(ilha.getJogador().getPosição()).getEfeito() != null) {

            ilha.getNo(ilha.getJogador().getPosição()).getEfeito().aplicarEfeito(ilha.getJogador());
            return true;          

        }
        
        return false;

    }
 
    //Faz as criaturas mais fracas fugirem do nó.
    private void removeMenores(ArrayList<Criatura> criaturas) {

        while(naoBatalhou(criaturas) > 2) {

            int removerIndice = menor(criaturas);
            fugir(criaturas.get(removerIndice));

        }

    }

    //Retorna a posição da criatura que ainda não batalhou com a menor vida na ArrayList.
    private int menor(ArrayList<Criatura> criaturas) {

        int saida;
        int indiceSaida;
        
        if(criaturas.get(0) instanceof Monstro) {
            saida = criaturas.get(0).getVida_atual();
            indiceSaida = 0;
        } else {
            saida = criaturas.get(1).getVida_atual();
            indiceSaida = 1;
        }

        for(int i = 0; i < criaturas.size(); i++) {

            //Verifica o monstro com a vida menor que o atual e que ainda não batalhou. 
            if(saida > criaturas.get(i).getVida_atual() && !criaturas.get(i).getBatalhou() && criaturas.get(i) instanceof Monstro) {
                saida = criaturas.get(i).getVida_atual();
                indiceSaida = i;
            }

        }

        return indiceSaida;

    }

    //Move as criaturas para outro nó.
    private void fugir(Criatura mob) {

        int v = -1;     //Posição padrão definida como -1.

        if(mob instanceof Jogador) {

            v = pilha.get(pilha.size()-1);    //v recebe a próxima posição para se mover. 
            ilha.movimentoNormal(v, marca, pilha);

        }

        ilha.moveCriatura(mob, v);  //Move, caso mob seja um monstro o valor v será ingnorado dentro do método. Caso contrário ele é utilizado.
        mob.setBatalhou(true);      //Confirma que o jogador batalhou/fugiu nesse turno.

    }

    //Conta a quantidades de criaturas em um nó que ainda não batalharam.
    private int naoBatalhou(ArrayList<Criatura> criaturas) {

        int saida = 0;
        for(int i = 0; i < criaturas.size(); i++) {

            if(!criaturas.get(i).getBatalhou())
                saida++;

        }

        return saida;

    }

    //Busca as duas criaturas que vão batalhar em um nó.
    private int[] buscaCriaturas(ArrayList<Criatura> criaturas) {

        int[] saida = new int[2];

        int cont = 0;
        for(int i = 0; i < criaturas.size(); i++) {

            if(!criaturas.get(i).getBatalhou()) {

                saida[cont] = i;
                cont++;

            }

        }

        return saida;

    }

    public int verificaResultado() {

        if(tempo <= 0 || !this.reviveu)
            return -1;
        else if(getJogador().getPosição() == 0 && getJogador().getTesouro() != 0)
            return 1;
        else
            return 0;

    }

}
