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

    public void turno() {
        
        int v;
        while(resultado == 0) {

            if(ilha.getJogador().getTesouro() != 0) {

                v = pilha.getLast();
                ilha.movimentoNormal(v, marca, pilha);
                ilha.movimentarCriaturas(v);

                if(ilha.getNo(v).isCheckPoint())
                    pilhaCheckPoint = pilha;
                
            } else {

                v = pilha.removeLast();
                ilha.movimentarCriaturas(v);

            }

            if(ilha.getNo(v).isCheckPoint()) {

                ilha.getNo(v).updateCheckPoint(false);
                ilha.getJogador().salvar_check();

            }

            this.tempo--;

        }

    }

    public void prepararBatalha() {

        ArrayList<Criatura> aux;

        for(int i = 0; i < ilha.getTotalVertices(); i++) {

            aux = ilha.getNo(i).getCriaturas();
            if(aux.size() > 1) {

                removeMenores(aux);
                batalhar(aux.get(1), aux.get(0));

            }

        }

    }

    //FAZER ALTERAÇÕES
    public void batalhar(Criatura mob1, Criatura mob2) {

        for(int i = 0; i < 3; i++) {

            if(mob1 instanceof Jogador) {

                if(!mob1.getAtacar()) {

                    int v = pilha.getLast();
                    fugir(mob1);
                    mob2.atacar(mob1);
                    break;

                }

            }
            
            mob1.atacar(mob2);
            if(mob2.getVida_atual() <= 0) {

                mob2.morrer();
                break;

            }

            if(mob2 instanceof Jogador) {

                if(!mob2.getAtacar()) {

                    int v = pilha.getLast();
                    fugir(mob2);
                    mob1.atacar(mob2);
                    break;

                }

            }

            mob2.atacar(mob2);
            if(mob1.getVida_atual() <= 0) {

                mob1.morrer();
                break;

            } 

        }

    }
 
    private void removeMenores(ArrayList<Criatura> criaturas) {

        while(naoBatalhou(criaturas) > 2) {

            int removerIndice = menor(criaturas);
            fugir(criaturas.get(removerIndice));

        }

    }

    //Retorna a posição da criatura que ainda não batalhou com a menor vida na ArrayList.
    private int menor(ArrayList<Criatura> criaturas) {

        int saida =  criaturas.get(0).getVida_atual();

        for(int i = 1; i < criaturas.size(); i++) {

            if(saida > criaturas.get(i).getVida_atual() && !criaturas.get(i).getBatalhou())
                saida = criaturas.get(i).getVida_atual();

        }

        return saida;

    }

    private void fugir(Criatura mob) {

        int v = -1;     //Posição padrão definida como -1.

        if(mob instanceof Jogador) {

            v = pilha.getLast();    //v recebe a próxima posição para se mover. 
            ilha.movimentoNormal(v, marca, pilha);

        }

        ilha.moveCriatura(mob, v);  //Move, caso mob seja um monstro o valor v será ingnorado dentro do método. Caso contrário ele é utilizado.
        mob.setBatalhou(true);      //Confirma que o jogador batalhour/fugiu nesse turno.

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

}
