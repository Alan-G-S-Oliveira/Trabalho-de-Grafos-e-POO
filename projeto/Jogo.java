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

    //Prepara as criaturas que vão batalhar e faz a batalha.
    public void prepararBatalha() {

        ArrayList<Criatura> aux;

        for(int i = 0; i < ilha.getTotalVertices(); i++) {

            aux = ilha.getNo(i).getCriaturas();
            if(aux.size() > 1) {

                removeMenores(aux);
                int[] batalha = buscaCriaturas(aux);
                batalhar(aux.get(batalha[0]), aux.get(batalha[1]));

            }

        }

    }

    //POSSIVELMENTE VOU FAZER ALGUMAS ALTERAÇÕES 
    public void batalhar(Criatura mob1, Criatura mob2) {

        int x = mob1.getPosição();

        for(int i = 0; i < 3; i++) {

            if(mob1 instanceof Jogador) {

                //O comando de atacar ou fugir vai vir do usuário, logo
                //tô em dúvida se getAtacar() vai tá em jogador, ou em uma classe do front.
                if(!mob1.getAtacar()) {

                    int v = pilha.getLast();
                    fugir(mob1);
                    mob2.atacar(mob1);
                    break;

                }

            }
            
            mob1.atacar(mob2);
            if(mob2.getVida_atual() <= 0) {

                //ACHO QUE TÁ FUNCIONANDO.
                ilha.getNo(x).removeCriatura(mob2);
                mob2.reviver(ilha.getTotalVertices());
                ilha.getNo(mob2.getPosição()).addCriaturas(mob2);
                break;

            }

            if(mob2 instanceof Jogador) {

                //O comando de atacar ou fugir vai vir do usuário, logo
                //tô em dúvida se getAtacar() vai tá em jogador, ou em uma classe do front.
                if(!mob2.getAtacar()) {

                    int v = pilha.getLast();
                    fugir(mob2);
                    mob1.atacar(mob2);
                    break;

                }

            }

            mob2.atacar(mob2);
            if(mob1.getVida_atual() <= 0) {

                //ACHO QUE TÁ FUNCIONANDO.
                ilha.getNo(x).removeCriatura(mob1);
                mob1.reviver(ilha.getTotalVertices());
                ilha.getNo(mob1.getPosição()).addCriaturas(mob2);
                break;

            }

            this.tempo--;

        }

        mob1.setBatalhou(true);
        mob2.setBatalhou(true);

    }

    //AINDA VOU FAZER.
    //VAI TER 3 VALORES DE RETONO. -1 PARA DERROTA, 1 PARA VIÓRIA E 0 QUANDO O JOGO AINDA NÃO ACABOU.
    public int verificaFim() {

        return 98348;   //VALOR FODA-SE, SÓ PRA NÃO FICAR IMPLICANDO MAIS ERRO DO QUE JÁ TEM.

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

        int saida =  criaturas.get(0).getVida_atual();

        for(int i = 1; i < criaturas.size(); i++) {

            if(saida > criaturas.get(i).getVida_atual() && !criaturas.get(i).getBatalhou())
                saida = criaturas.get(i).getVida_atual();

        }

        return saida;

    }

    //Move as criaturas para outro nó.
    private void fugir(Criatura mob) {

        int v = -1;     //Posição padrão definida como -1.

        if(mob instanceof Jogador) {

            v = pilha.getLast();    //v recebe a próxima posição para se mover. 
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
