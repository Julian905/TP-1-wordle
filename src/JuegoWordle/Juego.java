package JuegoWordle;

import java.util.Random;

public class Juego {

    private String palabraSecreta;
    private int intentosRestantes;
    private String[] palabras;

    public Juego() {

        if (ConfiguracionJuego.getIdioma().equals("en")) {
            palabras = new String[]{
                    "HOUSE", "DOGGO", "CATS", "TREE", "PEOPLE",
                    "CHAIR", "PLATE", "KEYS", "FIELD", "PLANE"
            };
        } else {
            palabras = new String[]{
                    "CASAS", "PERRO", "GATOS", "ARBOL", "GENTE",
                    "SILLA", "PLATO", "LLAVE", "CAMPO", "AVION"
            };
        }

        Random r = new Random();
        palabraSecreta = palabras[r.nextInt(palabras.length)];
        intentosRestantes = ConfiguracionJuego.getIntentos();
    }

    
    public EstadoLetra[] evaluarIntento(String intento) {

        intento = intento.toUpperCase();
        EstadoLetra[] resultado = new EstadoLetra[5];
        boolean[] usado = new boolean[5];

        // VERDES
        for (int i = 0; i < 5; i++) {
            if (intento.charAt(i) == palabraSecreta.charAt(i)) {
                resultado[i] = EstadoLetra.VERDE;
                usado[i] = true;
            }
        }

        // AMARILLOS y GRISES
        for (int i = 0; i < 5; i++) {

            if (resultado[i] == EstadoLetra.VERDE) continue;

            boolean encontrado = false;

            for (int j = 0; j < 5; j++) {
                if (!usado[j] && intento.charAt(i) == palabraSecreta.charAt(j)) {
                    encontrado = true;
                    usado[j] = true;
                    break;
                }
            }

            if (encontrado) {
                resultado[i] = EstadoLetra.AMARILLO;
            } else {
                resultado[i] = EstadoLetra.GRIS;
            }
        }

        intentosRestantes--;
        return resultado;
    }

    
    public EstadoJuego intentar(String palabra) {

        palabra = palabra.toUpperCase();

        if (palabra.equals(palabraSecreta)) {
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

    public String getPalabraSecreta() {
        return palabraSecreta;
    }
}

