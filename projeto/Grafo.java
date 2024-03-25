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
    
    //DEPOIS TENHO QUE MODULARIZAR ESSE CONTRUTOR MELHOR PARA FICAR MAIS LEGÍVEL.
    @SuppressWarnings("unchecked")
    public Grafo(String caminhoArquivo) throws FileNotFoundException {

        String[][] efeitos;
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

        posicionaElementos(checkpoints, riquezas, num);
        posicionaTesouro(num);

        //Percorre todos os efeitos lidos no arquivo e os coloca em suas respectivas posições.
        for(int i = 0; i < totalEfeitos; i++)
            adcionarEfeito(efeitos[i]);

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
            pilha.remove(pilha.size()-1);

    }

    //Percorre a lista de criaturas e as move.
    public void movimentarCriaturas(int v) {

        Criatura mob;

        //Percorre a lista de criaturas.
        for(int i = 0; i < criaturas.size(); i++) {

            mob = criaturas.get(i);                 //Pega uma critura específica.
            moveCriatura(mob, v);

        }      

    }

    //Move uma criatura. Independente se for monstro ou o jogador.
    public void moveCriatura(Criatura mob, int v) {

        int posicaoAtual, novaPosicao;

        posicaoAtual = mob.getPosição();        //Pega sua posição atual.
        nos[posicaoAtual].removeCriatura(mob);  //Remove a criatura do seu nó atual.

        //ARRUMA TUA PARTE PEDRO.
        if(mob instanceof Monstro)
            novaPosicao = ((Monstro) (mob)).movimentar(this.adjacencias[mob.getPosição()]);   //Movimenta caso for monstro.  
        else
            novaPosicao = mob.movimentar(v);    //Movimenta caso for o jogador.

        nos[novaPosicao].addCriaturas(mob);
        mob.setBatalhou(false);

    }

    //Verifica se um número específico está num vetor.
    private boolean procuraVetor(int[] vetor, int x) {

        for(int i = 0; i < vetor.length; i++) {

            if(x == vetor[i]) 
                return true;
        }

        return false;

    }

    //Adciona o efeito a sua respectiva posição.
    private void adcionarEfeito(String[] efeito) throws NumberFormatException{

        int i = Integer.parseInt(efeito[1]);

        switch (efeito[0]) {
            case "Envenenado":
                this.nos[i].setEfeito(new Envenenado());
                break;
            case "Queimado":
                this.nos[i].setEfeito(new Queimado());
            default:
                break;
        }

    }

    //Em tese essa parte sorteia aonde o tesouro vai estar.
    //Ele pode está na última posição ou em uma de suas adjacências.
    private void posicionaTesouro(Random num) {

        int x = num.nextInt(adjacencias[totalVertices - 1].size() + 1);
        if(x == adjacencias[totalVertices - 1].size())
            nos[totalVertices - 1].setTesouro(true);
        else
            nos[adjacencias[totalVertices - 1].get(x)].setTesouro(true);

    }

    //Função reponsável por criar o vetor de nos, e posicionar o jogador, os monstros e os checkpoints.
    private void posicionaElementos(int[] checkpoints, int[] riquezas, Random num) {

        nos = new Nos[totalVertices];
        criaturas = new ArrayList<>();
        jogador = new Jogador(100, 25, null, 0, null, null);
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

                Monstro monstro = new Monstro();
                monstro.setPosição(i);
                nos[i].addCriaturas(monstro);
                criaturas.add(monstro);
                
            }

        }

    }

}
