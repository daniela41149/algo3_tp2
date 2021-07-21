package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.excepciones.NoSeSuperaMinimoDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.SuperaMaximoDeJugadoresException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void test03SeCreaUnJuegoConDosJugadoresYseRepartenLosPaises() throws SuperaMaximoDeJugadoresException, NoSeSuperaMinimoDeJugadoresException{
        Juego juego = new Juego();

        juego.agregarJugador("jugador1");
        juego.agregarJugador("jugador2");
        assertEquals(2, juego.jugadores.size());

        juego.comenzarFaseInicial();
        Jugador jugador1 = juego.jugadores.get(0);
        Jugador jugador2 = juego.jugadores.get(1);
        assertEquals(25, jugador1.cantidadPaises());
        assertEquals(25, jugador2.cantidadPaises());
    }
}
