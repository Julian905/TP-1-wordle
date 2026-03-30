package JuegoWordle;

import java.util.Random; 

public class Juego {
	
	private String palabraSecreta;
	    private int intentosRestantes;

	    private String[] palabras = {

	        "CASAS",
	        "PERRO",
	        "GATOS",
	        "ARBOL",
	        "GENTE",
	        "SILLA",
	        "PLATO",
	        "LLAVE",
	        "CAMPO",
	        "AVIÓN"

	    };

	    public Juego() {

	        Random r = new Random();

	        palabraSecreta =
	                palabras[r.nextInt(
	                        palabras.length
	                )];

	        intentosRestantes = 6;

	    }

	    public EstadoJuego intentar(
	            String palabra) {

	        if (palabra == null)
	            return EstadoJuego.CONTINUAR;

	        palabra = palabra.toUpperCase();

	        intentosRestantes--;

	        if (palabra.equals(
	                palabraSecreta)) {

	            return EstadoJuego.GANADO;

	        }

	        if (intentosRestantes <= 0) {

	            return EstadoJuego.PERDIDO;

	        }

	        return EstadoJuego.CONTINUAR;

	    }

	    public int getIntentosRestantes() {

	        return intentosRestantes;

	    }

	}



