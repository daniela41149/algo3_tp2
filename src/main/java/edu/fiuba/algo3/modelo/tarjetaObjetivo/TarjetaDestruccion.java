package edu.fiuba.algo3.modelo.tarjetaObjetivo;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;

public class TarjetaDestruccion extends TarjetaObjetivo {

    private final String enunciado;
    private final String colorADestruir;

    public TarjetaDestruccion(String enunciado, String colorADestruir) {

        this.enunciado = enunciado;
        this.colorADestruir = colorADestruir;
    }

    private boolean destruyoAJugador(Juego juego) {
        Jugador jugadorADestruir = juego.jugadorDeColorOSiguiente(colorADestruir, dueño);
        return (jugadorADestruir.fueDestruidoPor(dueño));
    }

    public boolean cumplioObjetivo(Tablero tablero, Juego juego) {
        if (dueño == null || tablero == null || juego == null)
            return false;

        if (cumplioObjetivoGeneral())
            return true;

        return (destruyoAJugador(juego));
    }
    public String devolverEnunciado() {
        return enunciado;
    }

}
