package projeto;
import javax.swing.*;
import java.awt.*;

public class Tela extends JFrame {
	int x_size=1600,y_size=800;
	int borda=8,topo_borda=32;
	int atual_monstro=0;
	int espaco=300;
	private boolean monstro=true;
    private Image sprite;
    private Image map;
    String texto="dkjvgç sdohjgdfgsfdd ddddddddddddddd ddddddddd dddddddd ddddddddd ddddd dddddd ddddddddd dddddddddd ddl~gk";
    Image[] monstros = new Image[4];
    
    
    public Tela() {
        setTitle("Meu Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x_size,y_size);
        setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\doppa\\OneDrive\\Área de Trabalho\\Nova pasta\\img\\nave_azul.png");
        sprite = icon.getImage();
        icon = new ImageIcon("C:\\Users\\doppa\\OneDrive\\Área de Trabalho\\map.jpg");
        map=icon.getImage();
        y_size=icon.getIconHeight()+borda+topo_borda;
        x_size=icon.getIconWidth()+2*borda+espaco;
        setSize(x_size, y_size);
        for(int i=0;i<4;i++) {
        	icon=new ImageIcon("C:\\Users\\doppa\\OneDrive\\Área de Trabalho\\monstro"+i+".jpg");
        	monstros[i]=icon.getImage();
        }
        
 
        monstros[0] = sprite;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(map,borda,topo_borda,this);
        g.drawImage(sprite, 550, 350, this);
        if(monstro) {
        	g.drawImage(monstros[atual_monstro], borda + map.getWidth(this), topo_borda, this);
        }
        String[] words = texto.split(" ");
        String currentLine = "";
        int x = borda + map.getWidth(this);
        int y = topo_borda + monstros[atual_monstro].getHeight(this) + 20;
        int maxWidth = getWidth() - x - borda;
        for (String word : words) {
            if (g.getFontMetrics().stringWidth(currentLine + " " + word) < maxWidth) {
                currentLine += (currentLine.isEmpty() ? "" : " ") + word;
            } else {
                g.drawString(currentLine, x, y);
                y += g.getFontMetrics().getHeight();
                currentLine = word;
            }
        }
        // Desenha a última linha
        g.drawString(currentLine, x, y);
    }
    
    public void jogar() {
    	Jogo jogo=new Jogo("o arquivo la do grafo");
    	
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tela jogo = new Tela();
            jogo.setVisible(true);
            jogo.repaint(); // Certifique-se de que a tela seja atualizada
        });
    }
}

