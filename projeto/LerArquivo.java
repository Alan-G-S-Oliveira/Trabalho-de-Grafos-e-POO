package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//PRECISO REMOVER ALGUMAS GAMBIARRAS.
public class LerArquivo {

    private Scanner ler;

    public LerArquivo(String caminhoArquivo) throws FileNotFoundException {

        ler = new Scanner(new File(caminhoArquivo));

    }

    public int lerInteiro() {

        String linha = ler.nextLine();
        linha  = linha.substring(0, linha.length() - 1);
        
        return Integer.parseInt(linha);

    }

    public String[] lerEfeitos() {

        String aux = "";
        String linha = ler.nextLine();
        String[] leitura = new String[2];

        for(int i = 0; i < linha.length(); i++) {

            if(linha.charAt(i) != ',') 
                aux += linha.charAt(i);
            else {

                try {

                    Integer.parseInt(aux);
                    leitura[1] = aux;
                    aux = "";

                } catch(NumberFormatException exc) {

                    leitura[0] = aux;
                    aux = "";

                }
                
            }

        }

        return leitura;

    }

    public ArrayList<Integer> lerListaInteiro() {

        String aux = "";
        String linha = ler.nextLine();

        ArrayList<Integer> leitura = new ArrayList<>();

        for(int i = 0; i < linha.length(); i++) {

            if(linha.charAt(i) != ',')
                aux += linha.charAt(i);
            else {

                leitura.add(Integer.parseInt(aux));
                aux = "";

            }

        }

        return leitura;

    }

    public int[] lerListaInteiro(int tamanho) {

        String aux = "";
        String linha = ler.nextLine();

        int[] leitura = new int[tamanho];
        int cont = 0;

        for(int i = 0; i < linha.length(); i++) {

            if(linha.charAt(i) != ',')
                aux += linha.charAt(i);
            else {

                leitura[cont] = Integer.parseInt(aux);
                aux = "";
                cont++;

            }

        }

        return leitura;

    }
    
}
