package edu.fiuba.algo3.modelo.tarjetaObjetivo;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;

public abstract class TarjetaObjetivo {
    static final int OBJETIVO_GENERAL = 30;

    public Jugador dueño;

    public void establecerDueño(Jugador jugador) {
        this.dueño = jugador;
    }

    public boolean cumplioObjetivoGeneral() {
        return (dueño.cantidadPaises() >= OBJETIVO_GENERAL);
    }

    public abstract boolean cumplioObjetivo(Tablero tablero, Juego juego);

    public abstract String devolverEnunciado();
}
