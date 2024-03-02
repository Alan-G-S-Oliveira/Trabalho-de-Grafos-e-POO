package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grafo {
    
    private Nos[] nos;
    private ArrayList<Integer>[] adjacencias;

    private int totalCriaturas;

    public Grafo(String caminhoArquivo, int totalVertices) throws FileNotFoundException {

        Random num = new Random();

        Jogador player = new Jogador();
        Monstro monstro;

        nos = new Nos[totalVertices];

        int qtdCriaturas = 1;
        nos[0] = new Nos(player, null, false);
        for(int i = 1; i < totalVertices; i++) {

            if(num.nextInt(10) < 3) {

                monstro = new Monstro();
                nos[i] = new Nos(monstro, null);
                qtdCriaturas++;

            }

        }

        totalCriaturas = qtdCriaturas;

        listarAdjacencias(caminhoArquivo, totalVertices);
        
    }

    public Nos[] getNos() {

        return nos;

    }

    public ArrayList<Integer>[] getAdjacencias() {

        return adjacencias;

    }

    private void listarAdjacencias(String caminhoArquivo, int totalVertices) throws FileNotFoundException {

        String aux;
        String linha;
        int cont = 0;

        adjacencias = new ArrayList[totalVertices];

        try (Scanner lerArquivo = new Scanner(new File(caminhoArquivo))) {

            while(lerArquivo.hasNextLine()) {

                adjacencias[cont] = new ArrayList<Integer>();
                linha = lerArquivo.nextLine();
                aux = "";

                for(int i = 0; i < linha.length(); i++) {

                    if(linha.charAt(i) != ',')
                        aux += linha.charAt(i);
                    else {

                        try {

                            adjacencias[cont].add(Integer.parseInt(aux));
        
                        } catch(NumberFormatException exc) {

                        }

                    }

                }

                try {

                    adjacencias[cont].add(Integer.parseInt(aux));

                } catch(NumberFormatException exc) {

                }

                cont++;

            }

        }

    }

}
