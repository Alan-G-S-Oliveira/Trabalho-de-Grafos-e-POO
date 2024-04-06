package projeto;

import javax.swing.SwingUtilities;

import tela.Tela;

public class Inicializador {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            Tela jogo = new Tela();
            } catch (Exception exc) {

                System.out.println(exc);
                exc.printStackTrace();

            }
        });
    }
}