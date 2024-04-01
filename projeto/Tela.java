package projeto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tela extends JFrame implements KeyListener {
	int x_size=1600,y_size=800;
	int borda=8,topo_borda=32;
	int atual_monstro=0;
	int espaco=300;
	private Jogo jogo;
	private boolean monstro=false;
    private boolean emBatalha=false;
    private boolean catando=false;
    private Image map;
    ArrayList<Arma> chao[];
    int quantidade_chao;
    String texto="Você chegou em uma nova e inexplorada ilha, onde lhe é prometido inestimaveis tesouro. Pressione a tecla espaço para andar entre os pontos da ilha.";
    Image[] monstros = new Image[4];

    public Tela() throws FileNotFoundException {
        setTitle("Meu Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x_size,y_size);
        setLocationRelativeTo(null);
        this.jogo=new Jogo("\\Dados.csv");
        this.jogo.inicial();
        ImageIcon icon = new ImageIcon("C:\\Users\\doppa\\OneDrive\\Área de Trabalho\\map.jpg");
        map=icon.getImage();
        y_size=icon.getIconHeight()+borda+topo_borda;
        x_size=icon.getIconWidth()+2*borda+espaco;
        setSize(x_size, y_size);
        for(int i=0;i<4;i++) {
        	icon=new ImageIcon("C:\\Users\\doppa\\OneDrive\\Área de Trabalho\\monstro"+i+".jpg");
        	monstros[i]=icon.getImage();
        }
        
 
        this.setVisible(true);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(map,borda,topo_borda,this);
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
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Este método é chamado quando uma tecla é digitada (pressionada e solta)
    	char tecla=e.getKeyChar();
    	switch (tecla) {
            case ' ':
                if(catando){
                    break;
                }
                if(emBatalha)
                    break;
                if(monstro){
                    break;
                }else{
                    jogo.turno();
                    break;
                }
    		case 'f':
    		case 'F':
                //VAI PRO PAU
    			break;
            case 'l':
            case 'L':
                //ACORVADA
                break;
    		default:
             //nada
     }
    	
    	
    	this.atual_monstro=jogo.getCriatura();
        if (atual_monstro==-1) {
            monstro=false;
        }else{
            monstro=true;
            texto=texto+"Pressione L para lutar ou F para fugir.";
        }
    	this.repaint();
    }
    
    public void jogar() {
    	  	
    }

   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tela jogo = new Tela();
            jogo.setVisible(true);
            jogo.repaint(); // Certifique-se de que a tela seja atualizada
        });
    }*/
}

