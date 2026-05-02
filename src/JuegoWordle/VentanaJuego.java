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

    private JPanel panelGrilla;
    private JLabel[][] casillas;
    private int filaActual = 0;

    public VentanaJuego() {

        controlador = new ControladorJuego();

        setTitle("Wordle");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new PanelBotones();

        // Texto según idioma
        if (ConfiguracionJuego.getIdioma().equals("en")) {
            mensaje = new JLabel("Guess the word");
            tiempoLabel = new JLabel("Time: 0 s");
        } else {
            mensaje = new JLabel("Adivina la palabra");
            tiempoLabel = new JLabel("Tiempo: 0 s");
        }

        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.BOLD, 18));
        tiempoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        panelSuperior.add(mensaje, BorderLayout.NORTH);
        panelSuperior.add(panel, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);
        add(tiempoLabel, BorderLayout.SOUTH);

        
        int filas = ConfiguracionJuego.getIntentos();

        panelGrilla = new JPanel();
        panelGrilla.setLayout(new GridLayout(filas, 5, 5, 5));

        casillas = new JLabel[filas][5];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 5; j++) {

                JLabel casilla = new JLabel("");
                casilla.setOpaque(true);
                casilla.setBackground(Color.LIGHT_GRAY);
                casilla.setHorizontalAlignment(SwingConstants.CENTER);
                casilla.setFont(new Font("Arial", Font.BOLD, 18));

                casillas[i][j] = casilla;
                panelGrilla.add(casilla);
            }
        }

        add(panelGrilla, BorderLayout.CENTER);

        // BOTÓN REINTENTAR
        if (ConfiguracionJuego.getIdioma().equals("en")) {
            botonReintentar = new JButton("Retry");
        } else {
            botonReintentar = new JButton("Reintentar");
        }

        botonReintentar.setVisible(false);
        add(botonReintentar, BorderLayout.EAST);

        botonReintentar.addActionListener(e -> {
            new VentanaJuego();
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

        // BOTÓN INTENTAR
        panel.getBoton().addActionListener(e -> {

            if (filaActual >= ConfiguracionJuego.getIntentos()) return;

            String palabra = panel.getTexto().toUpperCase();

            if (palabra.length() != 5) {
                mensaje.setText("Debe tener 5 letras");
                return;
            }

            EstadoLetra[] resultado = controlador.evaluar(palabra);

            // pintar en la fila
            for (int i = 0; i < palabra.length(); i++) {

                JLabel casilla = casillas[filaActual][i];

                casilla.setText(String.valueOf(palabra.charAt(i)));
                casilla.setForeground(Color.WHITE);

                if (resultado[i] == EstadoLetra.VERDE) {
                    casilla.setBackground(Color.GREEN);
                } 
                else if (resultado[i] == EstadoLetra.AMARILLO) {
                    casilla.setBackground(Color.ORANGE);
                } 
                else {
                    casilla.setBackground(Color.GRAY);
                }
            }

            filaActual++;

            EstadoJuego estado = controlador.intentar(palabra);

            if (estado == EstadoJuego.GANADO) {

                timer.stop();
                long tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000;

                mensaje.setText("¡Ganaste! Tiempo: " + tiempoFinal + "s");
                panel.getBoton().setEnabled(false);
                botonReintentar.setVisible(true);
            }
            else if (estado == EstadoJuego.PERDIDO) {

                timer.stop();

                mensaje.setText("Perdiste. Palabra: " + controlador.getPalabraSecreta());
                panel.getBoton().setEnabled(false);
                botonReintentar.setVisible(true);
            }
            else {
                mensaje.setText("Intentos: " + controlador.getIntentosRestantes());
            }

            panel.limpiar();
        });

        setVisible(true);
    }
}