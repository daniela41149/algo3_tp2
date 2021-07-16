package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Juego;
import edu.fiuba.algo3.NoSeSuperaMinimoDeJugadoresException;
import edu.fiuba.algo3.SuperaMaximoDeJugadoresException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void test01NoSePuedeAgregarAUnJuegoMasDeSieteJugadores() throws SuperaMaximoDeJugadoresException {
        Juego juego = new Juego();

        juego.agregarJugador("jugador1");
        juego.agregarJugador("jugador2");
        juego.agregarJugador("jugador3");
        juego.agregarJugador("jugador4");
        juego.agregarJugador("jugador5");
        juego.agregarJugador("jugador6");

        assertThrows(SuperaMaximoDeJugadoresException.class, () -> juego.agregarJugador("jugador7"));
    }

    @Test
    public void test02NoSePuedeComenzarLaFaseInicialDeUnJuegoConMenosDeDosJugadores() throws SuperaMaximoDeJugadoresException {
        Juego juego = new Juego();

        juego.agregarJugador("jugador1");
        assertThrows(NoSeSuperaMinimoDeJugadoresException.class, juego::comenzarFaseInicial);
    }

}
