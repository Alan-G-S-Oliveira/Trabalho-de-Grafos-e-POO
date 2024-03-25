package projeto;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Jogo {
    
    private Grafo ilha;
    private int tempo;
    private int resultado;

    private String[] marca;
    private ArrayList<Integer> pilhaCheckPoint;
    private ArrayList<Integer> pilha;

    public Jogo(String caminhoArquivo) throws FileNotFoundException {

        ilha = new Grafo(caminhoArquivo);
        tempo = 3 * ilha.getTotalVertices();
        resultado = 0;

    }

    public Grafo getIlha() {

        return this.ilha;

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

    public ArrayList<Arma> getArmas(int no) {

        return this.ilha.getNo(no).getArmas();

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

        marca = new String[ilha.getTotalVertices()];
        pilhaCheckPoint =  new ArrayList<>();
        pilha = new ArrayList<>();

        Random num = new Random();       

        for(int i = 0; i < marca.length; i++)
            marca[i] = "branco";
        
        int v = num.nextInt(ilha.getAdjacencias(0).size());

        pilha.add(0);
        pilha.add(ilha.getAdjacencias(0).get(v));
        
    }

    //Escolhe o próximo movimento do jogador e move todas as crituras.
    public void turno() {
        
        int v;

        if(ilha.getJogador().getTesouro() != 0) {

            v = pilha.remove(pilha.size()-1);
            ilha.movimentoNormal(v, marca, pilha);
            ilha.movimentarCriaturas(v);

            if(ilha.getNo(v).isCheckPoint())
                pilhaCheckPoint = pilha;
                
        } else {

            v = pilha.remove(pilha.size()-1);
            ilha.movimentarCriaturas(v);

        }

        if(ilha.getNo(v).isCheckPoint()) {

            ilha.getNo(v).updateCheckPoint(false);
            ilha.getJogador().salvar_check();

        }

        this.tempo--;

    }

    //Prepara as criaturas que vão batalhar e faz a batalha.
    public void prepararBatalha() {

        ArrayList<Criatura> aux;

        for(int i = 0; i < ilha.getTotalVertices(); i++) {

            aux = ilha.getNo(i).getCriaturas();
            if(naoBatalhou(aux) > 1) { 

                removeMenores(aux);     //Remove as criaturas com menor vida que ainda não batalharam.
                int[] batalha = buscaCriaturas(aux);
                batalhar(aux.get(batalha[0]), aux.get(batalha[1]));

            }

        }

    }

    //VOU DÁ UMA MUDADA NISSO.
    public void batalhar(Criatura mob1, Criatura mob2) {

        int x = mob1.getPosição();
        boolean renasceu=false;

        for(int i = 0; i < 3; i++) {

            //MODIFICAR PRA VERIFICAR SE O JOGADOR TÁ BATALHANDO ANTES DA RODADA.

            if(mob1 instanceof Jogador) {

                //ACHO QUE USA UM MÉTODO STATIC DA CLASSE FACHADA PRA TER A INFORMAÇÃO SE O JOGADOR FUGIU O VAI LUTAR.
                if(!mob1.getBatalhou()) {

                    int v = pilha.remove(pilha.size()-1);
                    fugir(mob1);
                    mob2.atacar(mob1);
                    break;

                }

            }
            
            mob1.atacar(mob2);
            if(mob2.getVida_atual() <= 0) {

                //agora sim ta fundand0
                ilha.getNo(x).removeCriatura(mob2);     //Remove a criatura do vértice atual. 
                mob2.reviver(ilha.getTotalVertices());  //PROBLEMA da tua cabecinha
                renasceu =true;
                ilha.getNo(mob2.getPosição()).addCriaturas(mob2);   //Coloca a criatura no novo vértice.

                //VERIFICAR SE FUNCIONA.
                if(mob2 instanceof Jogador)
                    pilha = pilhaCheckPoint;

                break;

            }

            if(mob2 instanceof Jogador) {

                //ACHO QUE USA UM MÉTODO STATIC DA CLASSE FACHADA PRA TER A INFORMAÇÃO SE O JOGADOR FUGIU O VAI LUTAR.
                if(!mob2.getBatalhou()) {

                    int v = pilha.remove(pilha.size()-1);
                    fugir(mob2);
                    mob1.atacar(mob2);

                    if(mob1 instanceof Jogador)
                        pilha = pilhaCheckPoint;

                    break;

                }

            }

            mob2.atacar(mob2);
            if(mob1.getVida_atual() <= 0) {

                //agora sim ta funfanfo
                ilha.getNo(x).removeCriatura(mob1);
                mob1.reviver(ilha.getTotalVertices());
                renasceu = true;   ///PROBLEMA da tua cabeça isso aqui
                ilha.getNo(mob1.getPosição()).addCriaturas(mob2);
                break;

            }

            this.tempo--;

        }

        resultado = verificaFim(renasceu);
        mob1.setBatalhou(true);
        mob2.setBatalhou(true);

    }
    
    public int verificaFim(boolean renasceu) {

        if(tempo <= 0 || !renasceu)     //Vefifica se o jogador não pode mais renascer ou se o tempo acabou.
            return -1;

        Jogador aux = ilha.getJogador();

        if(aux.getPosição() == 0 && aux.getTesouro() != 0)  //Verifica se o jogador voultou com o tesouro para a praia.
            return 1;

        return 0;   //Segue o fluxo normal do jogo.

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
        
        if(criaturas.get(0) instanceof Monstro)
            saida = criaturas.get(0).getVida_atual();
        else
            saida = criaturas.get(1).getVida_atual();

        for(int i = 0; i < criaturas.size(); i++) {

            //Verifica o monstro com a vida menor que o atual e que ainda não batalhou. 
            if(saida > criaturas.get(i).getVida_atual() && !criaturas.get(i).getBatalhou() && criaturas.get(i) instanceof Monstro)
                saida = criaturas.get(i).getVida_atual();

        }

        return saida;

    }

    //Move as criaturas para outro nó.
    private void fugir(Criatura mob) {

        int v = -1;     //Posição padrão definida como -1.

        if(mob instanceof Jogador) {

            v = pilha.remove(pilha.size()-1);    //v recebe a próxima posição para se mover. 
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

}
