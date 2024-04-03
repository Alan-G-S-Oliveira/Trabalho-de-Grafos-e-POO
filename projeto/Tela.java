package projeto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tela extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 810561910264656891L;
	int x_size=1600,y_size=800;
	int borda=8,topo_borda=32;
	int atual_monstro=0;
	int espaco=300;
	private Jogo jogo;
	private boolean monstro=false;
    private boolean catando=false;
    private int resultado=0;
    private Image map;
    String texto="Você chegou em uma nova e inexplorada ilha, onde lhe é prometido inestimaveis tesouro. Pressione a tecla espaço para andar entre os pontos da ilha.";
    Image[] monstros = new Image[4];

    public Tela() throws FileNotFoundException {
        setTitle("Meu Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x_size,y_size);
        setLocationRelativeTo(null);
        this.jogo=new Jogo("\\Users\\alang\\OneDrive\\Área de Trabalho\\Programação\\Trabalho-de-Grafos-e-POO\\projeto\\Dados.csv");
        this.jogo.inicial();
        ImageIcon icon = new ImageIcon("\\Users\\alang\\OneDrive\\Área de Trabalho\\Programação\\Trabalho-de-Grafos-e-POO\\imagens\\ilha.jpg");
        map=icon.getImage();
        y_size=icon.getIconHeight()+borda+topo_borda;
        x_size=icon.getIconWidth()+2*borda+espaco;
        setSize(x_size, y_size);
        for(int i=0;i<4;i++) {
        	icon=new ImageIcon("\\Users\\alang\\OneDrive\\Área de Trabalho\\Programação\\Trabalho-de-Grafos-e-POO\\imagens\\monstro"+i+".jpg");
        	monstros[i]=icon.getImage();
        }
        
        this.addKeyListener(this);
 
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
        int y = 300;
        if(monstro)
            y = topo_borda + monstros[atual_monstro].getHeight(this) + 20;
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
        g.drawString("VIDA:"+jogo.getVidaJogador(), getWidth(),y=40);
        g.drawString("Tesouro:"+jogo.getTesouro(), getWidth(), 80);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Este método é chamado quando uma tecla é digitada (pressionada e solta)
    	char tecla=e.getKeyChar();
    	ArrayList<Arma> lista=jogo.getArmas();
    	texto="";
    	int aux=jogo.getCriatura();
    	int aux2=jogo.getTesouro();
    	switch (tecla) {
            case ' ':
                if(catando||monstro){
                    break;
                }else{
                    jogo.turno();
                    break;
                }
    		case 'f':
    		case 'F':
    			if(monstro)
                	jogo.prepararBatalha(true);
    			break;
            case 'l':
            case 'L':
            	if(monstro) {
            		texto=texto+jogo.prepararBatalha(false);
            		if(aux!=jogo.getCriatura())
            			texto+="Você derrotou a criatura";
            	}
                break;
            case 'c':
            case 'C':
            	catando=true;
            	break;
            case 's':
            case 'S':
            	if(catando) 
            		catando=false;
            	break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            	if(!catando)
            		break;
            	Arma aux3=jogo.getJogador().getArma();
            	jogo.getJogador().setArma(lista.get(tecla));
            	lista.remove(tecla);
            	lista.add(aux3);
            	catando=false;
            	break;
    		default:
             //nada
     }
    	
    	this.atual_monstro=jogo.getCriatura();
        if (atual_monstro==-1) {
            monstro=false;
            if(!catando)
            	texto= texto+"Pressione a tecla espaço para andar entre os pontos da ilha.";
            else {
            	int n=lista.size();
        		texto="Pressione o numero da arma para equipar ela \n";
        		for(int i=0; i<n; i++) {
        			texto=texto+i+":"+lista.get(i).getNome()+lista.get(i).getDurabilidade()+"\n";
        		}
        		texto=texto+"Pressione S para sair.\n";
            }
        }else{
            monstro=true;
            texto=texto+"Pressione L para lutar ou F para fugir.";
        }
        if(jogo.getArmas().size()>0 && !catando && !monstro)
        	texto=texto+"Pressione C para ver os itens no chão";
        resultado=jogo.getResultado();
        if(resultado==-1) {
        	texto="Você perdeu!!!";
        }else if(resultado==1){
        	texto="Você ganhou!!! Ouro ganho:"+jogo.getTesouro();
        }
        if(jogo.getTesouro()>aux2) {
        	if(monstro)
        		texto+="Você encontra um bau de tesouros, e furtivamente começa coletado, mas quando está  terminando um monstro aparece";
        	else
        		texto+="Você encontrou um bau de tesouros e coletou tudo o que podia carregar, hora de voltar pra praia";
        }
    	this.repaint();
    }
    
    public void jogar() {
    	  	
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            Tela jogo = new Tela();
            jogo.setVisible(true);
            jogo.repaint(); // Certifique-se de que a tela seja atualizada
            } catch (Exception exc) {

                System.out.println(exc);
                exc.printStackTrace();

            }

        });
    }
}

