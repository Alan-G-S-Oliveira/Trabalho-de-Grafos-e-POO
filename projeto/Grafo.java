package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
    
    private Nos[] nos;
    private ArrayList<Integer>[] adjacencias;
    private ArrayList<Criatura> criaturas;
    private Jogador jogador;
    private int totalVertices;
    
    @SuppressWarnings("unchecked")
    public Grafo(String caminhoArquivo) throws FileNotFoundException {

        String[][] efeitos;
        Monstro monstro;
        LerArquivo ler;
        Random num;

        int[] riquezas;
        int[] checkpoints;
        int totalEfeitos;
        int totalRiquezas;

        ler = new LerArquivo(caminhoArquivo);
        num = new Random();

        totalVertices = ler.lerInteiro();   //Total de vértices
        totalEfeitos = ler.lerInteiro();    //Total de efeitos
        totalRiquezas = ler.lerInteiro();   //Total de riquesas
        riquezas = ler.lerListaInteiro(totalRiquezas);    //Posição das riquesas
        checkpoints = ler.lerListaInteiro(3);     //Posição dos checkpoints

        //Lista de efeitos e suas respectivas posições
        efeitos = new String[totalEfeitos][2];
        for(int i = 0; i < totalEfeitos; i++)
            efeitos[i] = ler.lerEfeitos();

        //Lista as adjacencias
        adjacencias = new ArrayList[totalVertices];
        for(int i = 0; i < totalVertices; i++)
            adjacencias[i] = ler.lerListaInteiro();

        //Cria o vetor de nós, o inicializa com algumas criaturas e posiciona os tesouros.
        nos = new Nos[totalVertices];
        criaturas = new ArrayList<>();
        jogador = new Jogador(100, 25, null, 0, null, null, -1);
        criaturas.add(jogador);

        nos[0] = new Nos();
        nos[0].addCriaturas(jogador);
        for(int i = 1; i < totalVertices; i++) {

            nos[i] = new Nos();
            if(procuraVetor(checkpoints, i))
                this.nos[i].updateCheckPoint(true);
            if(procuraVetor(riquezas, i))
                this.nos[i].setRiquesa(new Bau());

            if(num.nextInt(10) < 3){

                monstro = new Monstro();
                monstro.setPosição(i);
                nos[i].addCriaturas(monstro);
                criaturas.add(monstro);
                
            }
            //PEDIR PARA PEDRO MUDAR A PARTE DE MOVER CRIATURAS.

        }

        //Em tese essa parte sorteia aonde o tesouro vai estar.
        //Ele pode está na última posição ou em uma de suas adjacências.
        int x = num.nextInt(adjacencias[totalVertices - 1].size() + 1);
        if(x == adjacencias[totalVertices - 1].size())
            nos[totalVertices - 1].setTesouro(true);
        else
            nos[adjacencias[totalVertices - 1].get(x)].setTesouro(true);

    }

    public Nos getNo(int posicao) {

        return nos[posicao];

    }

    public ArrayList<Integer> getAdjacencias(int posicao) {

        return adjacencias[posicao];

    }

    public void updateNo(Nos no, int posicao) {

        this.nos[posicao] = no;

    }

	public int getTotalVertices() {

		return totalVertices;

	}

    public Jogador getJogador() {

        return this.jogador;

    }

    public ArrayList<Criatura> getCriaturas() {

        return this.criaturas;

    }

	public void setTotalVertices(int totalVertices) {

		this.totalVertices = totalVertices;

	}

    //Calcula o próximo vértice para o jogador ir usando busca em profundidade.
    public void movimentoNormal(int v, String[] marca, ArrayList<Integer> pilha) {

        int i;
        for(i = 0; i < adjacencias[v].size(); i++) {

            if(marca[i].equals("branco"))
                break;

        }

        if(i != adjacencias[v].size()) {

            int u = adjacencias[v].get(i);
            marca[u] = "cinza";
            pilha.add(u);

        } else
            pilha.removeLast();

    }

    public void movimentarCriaturas(int v) {

        Criatura mob;

        //Percorre a lista de criaturas.
        for(int i = 0; i < criaturas.size(); i++) {

            mob = criaturas.get(i);                 //Pega uma critura específica.
            moveCriatura(mob, v);

        }      

    }

    public void moveCriatura(Criatura mob, int v) {

        int posicaoAtual, novaPosicao;

        posicaoAtual = mob.getPosição();        //Pega sua posição atual.
        nos[posicaoAtual].removeCriatura(mob);  //Remove a criatura do seu nó atual.

        if(mob instanceof Monstro)
            novaPosicao = ((Monstro) (mob)).movimentar();   //Movimenta caso for monstro.  
        else
            novaPosicao = mob.movimentar(v);    //Movimenta caso for o jogador.

        nos[novaPosicao].addCriaturas(mob);
        mob.setBatalhou(false);

    }

    private boolean procuraVetor(int[] vetor, int x) {

        for(int i = 0; i < vetor.length; i++) {

            if(x == vetor[i]) 
                return true;
        }

        return false;

    }

}
