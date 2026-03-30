package JuegoWordle;

import javax.swing.*;
import java.awt.*;
	
	public class PanelBotones extends JPanel {

	    private JTextField campo;
	    private JButton boton;

	    public PanelBotones() {

	        setLayout(
	                new FlowLayout()
	        );

	        campo = new JTextField(10);

	        boton =
	                new JButton("Intentar");

	        add(campo);
	        add(boton);

	    }

	    public JButton getBoton() {

	        return boton;

	    }

	    public String getTexto() {

	        return campo.getText();

	    }

	    public void limpiar() {

	        campo.setText("");

	    }

	}



