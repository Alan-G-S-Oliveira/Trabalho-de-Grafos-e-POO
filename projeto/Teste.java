package projeto;
    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Teste extends JFrame implements KeyListener {
    JTextField textField;

    public Teste() {
        setTitle("Key Typed Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField(20);
        textField.addKeyListener(this);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(textField);

        pack();
        setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        System.out.println("Key typed: " + keyChar);
    }

    // Implementações vazias necessárias para KeyListener
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Teste();
            }
        });
    }
}
