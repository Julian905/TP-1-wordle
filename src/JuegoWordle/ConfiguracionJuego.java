package JuegoWordle;

public class ConfiguracionJuego {

    private static String dificultad;
    private static int intentos;
    private static String idioma; 

    public static void Setdificultad(String dificultadE) {

        String dificultadEscogida = dificultadE.toLowerCase();

        switch (dificultadEscogida) {
            case "facil":
                dificultad = "facil";
                intentos = 8;
                break;

            case "medio":
                dificultad = "medio";
                intentos = 6;
                break;

            case "dificil":
                dificultad = "dificil";
                intentos = 4;
                break;
        }
    }

    public static int getIntentos() {
        return intentos;
    }

    public static String getDificultad() {
        return dificultad;
    }

    // idioma
    public static void setIdioma(String idiomaElegido) {
        idioma = idiomaElegido.toLowerCase();
    }

    public static String getIdioma() {
        return idioma;
    }
}
