package JuegoWordle;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {

        setTitle("Wordle - Inicio");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel titulo = new JLabel("Seleccionar idioma");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton español = new JButton("Español");
        JButton ingles = new JButton("English");

        add(titulo);
        add(español);
        add(ingles);

        español.addActionListener(e -> {
            ConfiguracionJuego.setIdioma("es");
            mostrarDificultad();
        });

        ingles.addActionListener(e -> {
            ConfiguracionJuego.setIdioma("en");
            mostrarDificultad();
        });

        setVisible(true);
    }

    // pantalla de dificultad después del idioma
    private void mostrarDificultad() {

        getContentPane().removeAll();
        setLayout(new GridLayout(4, 1));

        JLabel titulo;

        if (ConfiguracionJuego.getIdioma().equals("en")) {
            titulo = new JLabel("Select difficulty");
        } else {
            titulo = new JLabel("Seleccionar dificultad");
        }

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton facil;
        JButton normal;
        JButton dificil;

        if (ConfiguracionJuego.getIdioma().equals("en")) {
            facil = new JButton("Easy");
            normal = new JButton("Normal");
            dificil = new JButton("Hard");
        } else {
            facil = new JButton("Fácil");
            normal = new JButton("Normal");
            dificil = new JButton("Difícil");
        }


        add(titulo);
        add(facil);
        add(normal);
        add(dificil);

        facil.addActionListener(e -> {
            ConfiguracionJuego.Setdificultad("facil");
            iniciarJuego();
        });

        normal.addActionListener(e -> {
            ConfiguracionJuego.Setdificultad("medio");
            iniciarJuego();
        });

        dificil.addActionListener(e -> {
            ConfiguracionJuego.Setdificultad("dificil");
            iniciarJuego();
        });

        revalidate();
        repaint();
    }

    private void iniciarJuego() {
        new VentanaJuego();
        dispose();
    }
}
