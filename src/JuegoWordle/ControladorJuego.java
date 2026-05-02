package JuegoWordle;

public class ControladorJuego {

    private Juego juego;

    public ControladorJuego() {
        juego = new Juego();
    }

    
    public EstadoLetra[] evaluar(String palabra) {
        return juego.evaluarIntento(palabra);
    }

    public EstadoJuego intentar(String palabra) {
        return juego.intentar(palabra);
    }

    public int getIntentosRestantes() {
        return juego.getIntentosRestantes();
    }

    public String getPalabraSecreta() {
        return juego.getPalabraSecreta();
    }
}
