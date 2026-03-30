package JuegoWordle;

import javax.swing.*;

import JuegoWordle.PanelBotones;

import java.awt.*;

public class VentanaJuego
    extends JFrame {

private PanelBotones panel;

private JLabel mensaje;

private ControladorJuego controlador;

public VentanaJuego() {

    controlador =
            new ControladorJuego();

    setTitle("Wordle");

    setSize(400, 200);

    setDefaultCloseOperation(
            EXIT_ON_CLOSE
    );

    setLayout(
            new BorderLayout()
    );

    panel =
            new PanelBotones();

    mensaje =
            new JLabel(
                    "Adivina la palabra"
            );

    mensaje.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    add(
            mensaje,
            BorderLayout.NORTH
    );

    add(
            panel,
            BorderLayout.CENTER
    );

    panel.getBoton()
            .addActionListener(e -> {

                String palabra =
                        panel.getTexto();

                EstadoJuego estado =
                        controlador
                                .intentar(
                                        palabra
                                );

                if (estado ==
                        EstadoJuego.GANADO) {

                    mensaje.setText(
                            "Ganaste"
                    );

                }

                else if (estado ==
                        EstadoJuego.PERDIDO) {

                    mensaje.setText(
                            "Perdiste"
                    );

                }

                else {

                    mensaje.setText(
                            "Intentos restantes: "
                            +
                            controlador
                                    .getIntentosRestantes()
                    );

                }

                panel.limpiar();

            });

    setVisible(true);

}

}



