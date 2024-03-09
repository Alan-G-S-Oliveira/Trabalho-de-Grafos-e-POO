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
    private int jogador;
    private int totalVertices;
    
    public Grafo(String caminhoArquivo) throws FileNotFoundException {

        String[][] efeitos;
        Monstro monstro;
        LerArquivo ler;
        Jogador player;
        Random num;

        int[] riquezas;
        int[] checkpoints;
        int totalVertices;
        int totalEfeitos;
        int totalRiquesas;

        ler = new LerArquivo(caminhoArquivo);
        num = new Random();

        totalVertices = ler.lerInteiro();   //Total de vértices
        totalRiquesas = ler.lerInteiro();   //Total de riquesas
        riquezas = ler.lerListaInteiro(totalRiquesas);    //Posição das riquesas
        checkpoints = ler.lerListaInteiro(3);     //Posição dos checkpoints
        totalEfeitos = ler.lerInteiro();    //Total de efeitos

        //Lista de efeitos e suas respectivas posições
        efeitos = new String[totalEfeitos][2];
        for(int i = 0; i < totalEfeitos; i++)
            efeitos[i] = ler.lerEfeitos();

        //Lista as adjacencias
        adjacencias = new ArrayList[totalVertices];
        for(int i = 0; i < totalVertices; i++)
            adjacencias[i] = ler.lerListaInteiro();

        //Cria o vetor de nós e o inicializa com algumas criaturas.
        nos = new Nos[totalVertices];
        player = new Jogador(100, 25, null, 0, null, null, -1);
        nos[0] = new Nos();
        jogador = 0;

        criaturas = new ArrayList<>();
        for(int i = 1; i < totalVertices; i++) {

            if(num.nextInt(10) < 3){

                monstro = new Monstro();
                monstro.setPosição(i);
                criaturas.add(monstro);
                nos[i] = new Nos();
            
            }

        }

        nos[riquezas[0]].setRiqueza(new Tesouro());     //O tesouro é colocado na posição dada.
        for(int i = 1; i < riquezas.length; i++)
            nos[riquezas[i]].setRiquesa(new Bau());     //Para cada posição de tesouros é adcionado um baú.

        for(int i = 0; i < 3; i++)
            nos[checkpoints[i]].updateCheckPoint(true);

        

    }

    public Nos getNo(int posicao) {

        return nos[posicao];

    }

    public ArrayList<Integer> getAdjacencias(int posicao) {

        return adjacencias[posicao];

    }

    public Criatura getCriatura(int posicao) {
        
        return criaturas.get(posicao);

    }

    public void updateNo(Nos no, int posicao) {

        this.nos[posicao] = no;

    }

    public void jogar() {

        ArrayList<Integer> pilha = new ArrayList<>();   //Pilha dos vértices percorridos.
        ArrayList<Integer> deque = new ArrayList<>();
        ArrayList<Integer> pilhaTesouro = new ArrayList<>();
        String[] marca = new String[nos.length];        //Vetor representando as marcas dos vértices.
        Boolean checkPointPreto = false;
        Boolean bifurcacao = false;

        //Marca todos os vértices como branco.
        for(int i = 0; i < nos.length; i++)
            marca[i] = "branco";

        //Marca o vértice inicial como cinza e o empilha.
        marca[0] = "cinza";
        pilha.add(0);
    
        //Executa enquanto tiver algum elemento na pilha.
        while(!pilha.isEmpty()) {
    
            int i, u;
            int v = pilha.getLast();    //Pega o topo da pilha.

            this.executaTurno(pilha, pilhaTesouro, deque);
            if(nos[v].isCheckPoint()) {

                deque.clear();
                checkPointPreto = false;
                bifurcacao = false;

            }

            if(nos[v].isTesouro()) {

                for(int j = 0; j < pilha.size(); j++)
                    pilhaTesouro.add(pilha.get(j));

            }
    
            //Procura na lista de adjacências de v um vértice marcado como branco.
            for(i = 0; i < adjacencias[v].size(); i++) {
    
                if(marca[adjacencias[v].get(i)].equals("branco"))
                    break;
    
            }
    
            //Se i != adjacencias[v].size(), ele saiu antes do fim do laço, logo encontrou um vértice branco.
            if(i != adjacencias[v].size()){
    
                u = adjacencias[v].get(i);
                marca[u] = "cinza";
                pilha.add(u);
                
                if(checkPointPreto) {

                    bifurcacao = true;

                    if(!deque.contains(v))
                        deque.add(v);

                }
    
            } else {

                int x = pilha.removeLast(); //Significa que não há mais vértices adjacentes brancos para remover, então o topo da pilha é removido.
                if(nos[x].isCheckPoint())
                    checkPointPreto = true;

                if(checkPointPreto && !bifurcacao)
                    deque.add(x);

                if(bifurcacao && deque.contains(x))
                    deque.remove(x);

            }

        }
    
    }

    private void executaTurno(ArrayList<Integer> pilha, ArrayList<Integer> pilhaTesouro,ArrayList<Integer> deque) {

        //Entra aqui se o jogador não morreu e ainda não encontrou o tesouro.
        //POSSÍVEL ERRO. REVISAR ESSE IF.
        if(pilha.getLast() == criaturas.get(jogador).getPosição() && ((Jogador) criaturas.get(jogador)).getTesouro() == 0) {

            int v = pilha.getLast();
            movimentarCriaturas(v);

            if(nos[v].isCheckPoint()) {

                ((Jogador)criaturas.get(jogador)).setCheckPoint(v);
                nos[v].updateCheckPoint(false);

            }
            
        } else if(((Jogador) criaturas.get(jogador)).getTesouro() != 0) {    //Entra aqui se o jogador encontrou o tesouro.

            //Entra aqui se ele ainda não morreu.
            while(pilha.getLast() == criaturas.get(jogador).getPosição()) {

                int v = pilha.removeLast();
                movimentarCriaturas(v);

            }

            if(!pilha.isEmpty()) {  //Entra aqui se o jogador morreu no meio do caminho de volta.

        

            } else      //Entra aqui se o jogador chegou a praia.  
                return;

        } else {    //Entra aqui se o jogador morreu e foi para no checkPoint.

            if(deque.isEmpty()) {   //Se o checkPoint era um vértice cinza.

                int x = pilha.indexOf(jogador);
                while(pilha.get(x) != pilha.getLast()) {

                    movimentarCriaturas(pilha.get(x));
                    x++;

                }


            } else {    //Se o checkpoint era um vértice preto.

                deque.removeFirst();
                int x = deque.getFirst();
                movimentarCriaturas(x);

            }

        }

    }

    private void movimentarCriaturas(int v) {

        for(int i = 0; i < criaturas.size(); i++) {

            if(criaturas.get(i) instanceof Monstro)
                ((Monstro) criaturas.get(i)).movimentar();
            else
                criaturas.get(i).movimentar(v);

        }

    }

	public int getTotalVertices() {
		return totalVertices;
	}

	public void setTotalVertices(int totalVertices) {
		this.totalVertices = totalVertices;
	}

}
