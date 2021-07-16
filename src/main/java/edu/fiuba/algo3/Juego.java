package edu.fiuba.algo3;

import java.util.LinkedList;
import java.util.List;

public class Juego {

    static final int MIN_JUGADORES = 2;
    static final int MAX_JUGADORES = 6;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};

    private Tablero tablero;
    private List<Jugador> jugadores;

    public Juego() {

        this.tablero = new Tablero("src/main/java/edu/fiuba/algo3/Fronteras.csv");
        this.jugadores = new LinkedList<>();
    }

    public void agregarJugador(String nombreDeJugador) throws SuperaMaximoDeJugadoresException {
        if (jugadores.size() == MAX_JUGADORES)
            throw new SuperaMaximoDeJugadoresException();
        String colorAsignado = COLORES[jugadores.size()];
        jugadores.add(new Jugador(nombreDeJugador, colorAsignado, this));
    }

    public void comenzarFaseInicial() throws NoSeSuperaMinimoDeJugadoresException {
        if (jugadores.size() < MIN_JUGADORES)
            throw new NoSeSuperaMinimoDeJugadoresException();

        // Aca se piden los paises a tablero y se dividen dependiendo la cantidad de jugadores
    }

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor, int cantidadDeEjercitoAtacante) throws PaisNoLimitrofeException {
        tablero.atacar(nombrePaisAtacante, nombrePaisDefensor, cantidadDeEjercitoAtacante);
    }


}
