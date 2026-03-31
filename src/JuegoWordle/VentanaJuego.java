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

    public VentanaJuego() {

        controlador = new ControladorJuego();

        setTitle("Wordle");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new PanelBotones();

        mensaje = new JLabel("Adivina la palabra");
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);

        tiempoLabel = new JLabel("Tiempo: 0 s");
        tiempoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(mensaje, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(tiempoLabel, BorderLayout.SOUTH);

        //  INICIAR TIMER
        tiempoInicio = System.currentTimeMillis();

        timer = new Timer(1000, e -> {
            long tiempoActual = System.currentTimeMillis();
            long segundos = (tiempoActual - tiempoInicio) / 1000;
            tiempoLabel.setText("Tiempo: " + segundos + " s");
        });

        timer.start();

        
        panel.getBoton().addActionListener(e -> {

            String palabra = panel.getTexto();

            EstadoJuego estado = controlador.intentar(palabra);

            if (estado == EstadoJuego.GANADO) {

                timer.stop();

                long tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000;

                mensaje.setText("Ganaste en " + tiempoFinal + " segundos");

            }

            else if (estado == EstadoJuego.PERDIDO) {

                timer.stop();

                long tiempoFinal = (System.currentTimeMillis() - tiempoInicio) / 1000;

                mensaje.setText("Perdiste en " + tiempoFinal + " segundos");

            }

            else {

                mensaje.setText(
                        "Intentos restantes: " +
                        controlador.getIntentosRestantes()
                );
            }

            panel.limpiar();
        });

        setVisible(true);
    }
}

