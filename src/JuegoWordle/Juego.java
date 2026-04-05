package JuegoWordle;

import java.util.Random;

public class Juego {

    private String palabraSecreta;
    private int intentosRestantes;
    private String[] palabras; // ahora no está fija

    public Juego() {

        
        if (ConfiguracionJuego.getIdioma().equals("en")) {

            palabras = new String[]{
                "HOUSE",
                "DOGGO",
                "CATS",
                "TREE",
                "PEOPLE",
                "CHAIR",
                "PLATE",
                "KEYS",
                "FIELD",
                "PLANE"
            };

        } else {

            palabras = new String[]{
                "CASAS",
                "PERRO",
                "GATOS",
                "ARBOL",
                "GENTE",
                "SILLA",
                "PLATO",
                "LLAVE",
                "CAMPO",
                "AVION"
            };
        }

        Random r = new Random();

        palabraSecreta = palabras[r.nextInt(palabras.length)];

        intentosRestantes = ConfiguracionJuego.getIntentos();
    }

    public EstadoJuego intentar(String palabra) {

        if (palabra == null)
            return EstadoJuego.CONTINUAR;

        palabra = palabra.toUpperCase();

        intentosRestantes--;

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
}


