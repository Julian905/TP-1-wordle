package JuegoWordle;

public class ControladorJuego {
	
	 private Juego juego;

	    public ControladorJuego() {

	        juego = new Juego();

	    }

	    public EstadoJuego intentar(
	            String palabra) {

	        return juego.intentar(
	                palabra
	        );

	    }

	    public int getIntentosRestantes() {

	        return juego.getIntentosRestantes();

	    }

	}





