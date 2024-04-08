package tela;
import javax.swing.*;

import Criaturas.Jogador;
import Itens.Arma;
import projeto.*;

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
	int atual_imagem=0;
	int espaco=300;
	private Jogo jogo;
	private boolean imagem=false;
    private boolean catando=false;
    private boolean bau=false;
    private int resultado=0;
    private Image map;
    String texto="Você chegou em uma nova e inexplorada ilha, onde lhe é prometido inestimaveis tesouro. Pressione a tecla espaço para andar entre os pontos da ilha.";
    Image[] imagems = new Image[6];

    public Tela() throws FileNotFoundException {
        setTitle("Meu Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x_size,y_size);
        setLocationRelativeTo(null);
        this.jogo=new Jogo("projeto/Dados.csv");
        this.jogo.inicial();
        ImageIcon icon = new ImageIcon("imagens/ilha.jpg");
        map=icon.getImage();
        y_size=icon.getIconHeight()+borda+topo_borda;
        x_size=icon.getIconWidth()+2*borda+espaco;
        setSize(x_size, y_size);
        for(int i=0;i<4;i++) {
        	icon=new ImageIcon("imagens/monstro"+i+".jpg");
        	imagems[i]=icon.getImage();
        }
        icon=new ImageIcon("imagens/Baú.jpg");
    	imagems[4]=icon.getImage();
    	icon=new ImageIcon("imagens/final.jpg");
    	imagems[5]=icon.getImage();
        this.addKeyListener(this);
 
        this.setVisible(true);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(map,borda,topo_borda,this);
        if(imagem) {
        	g.drawImage(imagems[atual_imagem], borda + map.getWidth(this), topo_borda, this);
        }
        String[] words = texto.split(" ");
        String currentLine = "";
        int x = borda + map.getWidth(this);
        int y = 300;
        if(imagem)
            y = topo_borda + imagems[atual_imagem].getHeight(this) + 20;
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
    	ArrayList<Arma> lista=jogo.getArmas();
    	texto="";
    	int aux=jogo.getCriatura();
    	switch (tecla) {
            case ' ':
                if(catando||(atual_imagem>-1&&atual_imagem<4)){
                    break;
                }else{
                    texto+=jogo.turno();
                    break;
                }
    		case 'f':
    		case 'F':
    			if(imagem)
                	jogo.prepararBatalha(true);
    			break;
            case 'l':
            case 'L':
            	if(imagem) {
            		texto=texto+jogo.prepararBatalha(false);
            		if(aux!=jogo.getCriatura())
            			texto+="Você derrotou a criatura.";
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
            case 'b':
            case 'B':
            	if(bau) {
            		if(jogo.itemBau())
            			texto+=" Você encontrou e equipou um colar.";
            		else 
            			texto+=" Ao abrir o baú uma arma caiu no chão.";
            		bau=false;
            	}
            	break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            	if(catando){
            		Jogador jogador=jogo.getJogador();
            		Arma arma_velha=jogador.getArma();
            		jogador.setArma(lista.get(tecla - '0'));
                    System.out.println("Armas" + lista.size());
            		lista.remove(tecla - '0');
                    if(arma_velha != null)
            		    lista.add(arma_velha);
            		catando=false;
            	}
            	break;
    		default:
             //nada
     }
    	
    	lista=jogo.getArmas();
    	this.atual_imagem=jogo.getCriatura();
        if (atual_imagem==-1) {
            imagem=false;
            
            if(jogo.hasBau()) {
            	bau=true;
            	texto+=" Você achou um bau, pressione B para abri-lo.";
            }
            if(!catando) {
            	texto= texto+" Pressione a tecla espaço para andar entre os pontos da ilha.";
            	if(lista.size()>0) {
            		texto+=" Ha itens no chão, pressione C para vê-los.";
            	}
        	}
            else {
            	int n=lista.size();
        		texto="Pressione o numero da arma para equipar ela \n";
        		for(int i=0; i<n; i++) {
        			texto=texto+i+":"+lista.get(i).getNome()+"Durabilidade:"+lista.get(i).getDurabilidade()+" ";
        		}
        		texto=texto+" Pressione S para sair.";
            }
        }else{
            imagem=true;
            texto=texto+" Pressione L para lutar ou F para fugir.";
        }
        resultado=jogo.verificaResultado();
        if(jogo.isTesouro()) {
        	if(imagem)
        		texto+=" Você encontra um bau de tesouros, e furtivamente começa coletado, mas quando está terminando um imagem aparece.";
        	else
        		texto+=" Você encontrou um bau de tesouros e coletou tudo o que podia carregar, hora de voltar pra praia.";
        }
        texto+=" - VIDA:"+jogo.getVidaJogador();
        texto+=" - Tesouro:"+jogo.getTesouro();
        texto+=" - Posição atual:"+jogo.getJogador().getPosição();
        texto+=" - Tempo:"+jogo.getTempo();
        if(resultado==-1) {
        	texto="Você perdeu!!!";
        }else if(resultado==1){
        	texto="Você finalmente chega ao barco, perplexo com as criaturas que acabara de ver, quando o alívio começa a tomar conta de seu ser. Tremores gigantescos agitam as ondas do mar e um barulho estranho alcança "
        			+ "seus ouvidos. Estacas começam a emergir da ilha e, por algum motivo desconhecido, a própria ilha começa a se erguer, revelando-se como um monstro gigante.\r\n"
        			+ "\r\n"
        			+ "Ela olha para você, dá meia volta e se afasta. Você percebe que é insignificante para aquela criatura colossal. Desconcertado, "
        			+ "você verifica o tesouro que coletou e encontra uma foto peculiar: um pequeno ornitorrinco ao lado de um homem calvo com óculos de fundo de garrafa,"
        			+ " acompanhado por uma bela moça com uma maquiagem engraçada.\r\n"
        			+ "\r\n"
        			+ "Sem entender nada, você retorna para casa com a certeza de que ninguém acreditará em sua história, e aquela foto estranha certamente não ajudará."
        			+ "  Você ganhou!!! Ouro ganho:"+jogo.getTesouro();
        	atual_imagem=5;
        	imagem=true;
        }
        if(!imagem&&jogo.isTesouro()) {
        	atual_imagem=4;
        	imagem=true;
        }
    	this.repaint();
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
    }
}

