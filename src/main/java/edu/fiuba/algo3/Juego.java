package edu.fiuba.algo3;

public class Juego {

    static final int MIN_JUGADORES = 2;
    static final int MAX_JUGADORES = 6;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};

    private Tablero tablero;
//    private List<Jugador> jugadores;
//    private List<TarjetaPais> tarjetasPais;

    public Juego(int cantidadDeJugadores) throws CantidadInvalidaDeJugadoresException {
        if (cantidadDeJugadores < MIN_JUGADORES || cantidadDeJugadores > MAX_JUGADORES) {
            throw new CantidadInvalidaDeJugadoresException();
        }

        this.tablero = new Tablero();
/*
        for (int i = 0; i < cantidadDeJugadores ; i++) {
            this.jugadores.add(new Jugador(COLORES[i]));
        }
*/

    }
/*
    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor, int cantidadDeEjercitoAtacante){
        tablero.atacar(nombrePaisAtacante, nombrePaisDefensor, cantidadDeEjercitoAtacante);
    }
 */

}
