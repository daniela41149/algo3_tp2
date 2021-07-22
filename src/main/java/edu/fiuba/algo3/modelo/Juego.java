package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.*;

public class Juego {

    static final int MIN_JUGADORES = 2;
    static final int MAX_JUGADORES = 6;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};

    public Tablero tablero;
    public List<Jugador> jugadores;

    public Juego(List<Pais> paises, List<Continente> continentes, List<String> nombresDeJugadores) throws CantidadInvalidaDeJugadoresException {
        if (nombresDeJugadores.size() < MIN_JUGADORES || nombresDeJugadores.size() > MAX_JUGADORES)
            throw new CantidadInvalidaDeJugadoresException();

        this.tablero = new Tablero(paises, continentes);
        this.jugadores = new LinkedList<>();
        for (int i = 0; i < nombresDeJugadores.size(); i++)
            jugadores.add(new Jugador(nombresDeJugadores.get(i), COLORES[i], this));

    }

    private void repartirPaisesAleatoriamente(int cantidadPorJugador) throws JugadaInvalidaException {
        Random rand = new Random();
        ArrayList<Pais> paisesSinRepartir = new ArrayList<>(tablero.pasarPiasesAJuego());

        for (Jugador jugadorActual : jugadores) {
            for (int j = 0; j < cantidadPorJugador; j++) {
                int posicionAleatoria = rand.nextInt(paisesSinRepartir.size());
                Pais paisAleatorio = paisesSinRepartir.get(posicionAleatoria);

                paisAleatorio.colocarEjercito(jugadorActual,1);
                jugadorActual.agregarPais(paisAleatorio);
                paisesSinRepartir.remove(paisAleatorio);
            }
        }
    }

    public void comenzarFaseInicial() throws JugadaInvalidaException {
        int paisesPorJugador = (tablero.cantidadPaises()/jugadores.size());
        repartirPaisesAleatoriamente(paisesPorJugador);
    }
/*
    public void comenzarFaseDeJuego() {
        for (Jugador jugador: jugadores) {
            jugador.jugarTurno();
        }
    }
*/
    public void colocarEjercito(String nombreJugador, String nombrePais, int cantidadEjercito) throws JugadaInvalidaException {
        for (Jugador unJugador : jugadores) {
            if (unJugador.getNombre().equals(nombreJugador)) {
                unJugador.colocarEjercito(nombrePais, cantidadEjercito);
            }
        }
    }

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor, int cantidadDeEjercitoAtacante) throws JugadaInvalidaException {
        tablero.atacar(nombrePaisAtacante, nombrePaisDefensor, cantidadDeEjercitoAtacante);
    }

    public HashMap<String,List<Pais>> mostrarPaisesDeCadaJugador(){
        HashMap<String,List<Pais>> diccionario = null;
        return  diccionario;
    }

}
