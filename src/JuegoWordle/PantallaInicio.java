package JuegoWordle;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {

        setTitle("Wordle - Inicio");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel titulo = new JLabel("Seleccionar dificultad");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton facil = new JButton("Fácil");
        JButton normal = new JButton("Normal");
        JButton dificil = new JButton("Difícil");

        add(titulo);
        add(facil);
        add(normal);
        add(dificil);

        
        facil.addActionListener(e -> iniciarJuego());
        normal.addActionListener(e -> iniciarJuego());
        dificil.addActionListener(e -> iniciarJuego());

        setVisible(true);
    }

    private void iniciarJuego() {
        new VentanaJuego(); 
        dispose(); 
    }
}