package JuegoWordle;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private PanelBotones panel;
    private JLabel mensaje;
    private JLabel tiempoLabel;
    private ControladorJuego controlador;

    private long tiempoInicio;
    private Timer timer;

    private JButton botonReintentar; 

    public VentanaJuego() {

        controlador = new ControladorJuego();

        setTitle("Wordle");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new PanelBotones();

        //  Texto según idioma
        if (ConfiguracionJuego.getIdioma().equals("en")) {
            mensaje = new JLabel("Guess the word");
            tiempoLabel = new JLabel("Time: 0 s");
        } else {
            mensaje = new JLabel("Adivina la palabra");
            tiempoLabel = new JLabel("Tiempo: 0 s");
        }

        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        tiempoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(mensaje, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(tiempoLabel, BorderLayout.SOUTH);

        // BOTÓN REINTENTAR
        if (ConfiguracionJuego.getIdioma().equals("en")) {
            botonReintentar = new JButton("Retry");
        } else {
            botonReintentar = new JButton("Reintentar");
        }

        botonReintentar.setVisible(false); // oculto al inicio
        add(botonReintentar, BorderLayout.EAST);

        botonReintentar.addActionListener(e -> {
            new VentanaJuego(); // reinicia
            dispose(); 
        });

        // TIMER
        tiempoInicio = System.currentTimeMillis();

        timer = new Timer(1000, e -> {
            long tiempoActual = System.currentTimeMillis();
            long segundos = (tiempoActual - tiempoInicio) / 1000;

            if (ConfiguracionJuego.getIdioma().equals("en")) {
                tiempoLabel.setText("Time: " + segundos + " s");
            } else {
                tiempoLabel.setText("Tiempo: " + segundos + " s");
            }
        });

        timer.start();

        panel.getBoton().addActionListener(e -> {

            String palabra = panel.getTexto();

            EstadoJuego estado = controlador.intentar(palabra);

            if (estado == EstadoJuego.GANADO) {

                timer.stop();
                long tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000;

                if (ConfiguracionJuego.getIdioma().equals("en")) {
                    mensaje.setText("You win in " + tiempoFinal + " seconds");
                } else {
                    mensaje.setText("Ganaste en " + tiempoFinal + " segundos");
                }

                panel.getBoton().setEnabled(false); // desactiva intentar
                botonReintentar.setVisible(true); //  muestra botón
            }

            else if (estado == EstadoJuego.PERDIDO) {

                timer.stop();
                long tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000;

                if (ConfiguracionJuego.getIdioma().equals("en")) {
                    mensaje.setText("You lose in " + tiempoFinal + " seconds");
                } else {
                    mensaje.setText("Perdiste en " + tiempoFinal + " segundos");
                }

                panel.getBoton().setEnabled(false); 
                botonReintentar.setVisible(true); 
            }

            else {

                if (ConfiguracionJuego.getIdioma().equals("en")) {
                    mensaje.setText("Attempts left: " + controlador.getIntentosRestantes());
                } else {
                    mensaje.setText("Intentos restantes: " + controlador.getIntentosRestantes());
                }
            }

            panel.limpiar();
        });

        setVisible(true);
    }
}

