package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.*;


public class Juego {

    static final int MIN_JUGADORES = 2;
    static final int MAX_JUGADORES = 6;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};

    private Tablero tablero;
    private List<Jugador> jugadores;
    //private int posicionJugadorEnTurno;

    public Juego(List<Pais> paises, List<Continente> continentes, List<String> nombresDeJugadores) throws CantidadInvalidaDeJugadoresException {

        if (nombresDeJugadores.size() < MIN_JUGADORES || nombresDeJugadores.size() > MAX_JUGADORES)
            throw new CantidadInvalidaDeJugadoresException();

        this.tablero = new Tablero(paises, continentes);
        this.jugadores = new LinkedList<>();
        //this.posicionJugadorEnTurno = 0;
        for (int i = 0; i < nombresDeJugadores.size(); i++)
            jugadores.add(new Jugador(nombresDeJugadores.get(i), COLORES[i], this));
    }


    public void comenzarFaseInicial(Aleatorio aleatorio) throws JugadaInvalidaException {
        ArrayList<Pais> paisesSinRepartir = new ArrayList<>(tablero.pasarPaisesAJuego());

        //RandomPaises randomPaises = new RandomPaises();
        List<List<Pais>> paisesRepartidos = aleatorio.repartirPaisesAleatoriamente(jugadores.size(), paisesSinRepartir);

        for(int indice = 0;indice<jugadores.size();indice++) {
            List<Pais> listaPaisesParaJugador = paisesRepartidos.get(indice);
                for (Pais paisActual : listaPaisesParaJugador) {
                    jugadores.get(indice).agregarPais(paisActual);
                    paisActual.colocarEjercito(jugadores.get(indice),1);
                }
        }

        //posicionJugadorEnTurno = rand.nextInt(jugadores.size());
        //repartirPaisesAleatoriamente(paisesPorJugador);
    }

    /*
    public Jugador jugadorEnTurno() {
        Jugador jugadorEnTurno = jugadores.get(posicionJugadorEnTurno);

        posicionJugadorEnTurno++;
        if (posicionJugadorEnTurno >= jugadores.size())
            posicionJugadorEnTurno = 0;

        return jugadorEnTurno;
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
        HashMap<String,List<Pais>> diccionario = new HashMap<>();
        List<Pais> listaPaises = new ArrayList<>();
        for(Jugador jugador: jugadores){
            diccionario.put(jugador.getNombre(),jugador.pedirPaises());
        }
        return  diccionario;
    }

}
