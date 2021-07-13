package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.Juego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void test01NoSePuedeCrearUnJuegoConMenosDeDosJugadores() {

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(1));
    }

    @Test
    public void test02NoSePuedeCrearUnJuegoConMasDeSeisJugadores() {

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(7));
    }
}
