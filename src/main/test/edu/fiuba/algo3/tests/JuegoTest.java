package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    Moderador moderador;

    @BeforeEach
    public void setup() throws IOException {
        moderador = new Moderador();
    }

    @Test
    public void test01NoSePuedeCrearUnJuegoConMasDeSieteJugadores() {
        ArrayList<String> nombreJugadores = new ArrayList<>();
        nombreJugadores.add("jugador1");
        nombreJugadores.add("jugador2");
        nombreJugadores.add("jugador3");
        nombreJugadores.add("jugador4");
        nombreJugadores.add("jugador5");
        nombreJugadores.add("jugador6");
        nombreJugadores.add("jugador7");

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombreJugadores));
    }

    @Test
    public void test02NoSePuedeCrearUnJuegoMenosDeDosJugadores() {
        ArrayList<String> nombreJugadores = new ArrayList<>();
        nombreJugadores.add("jugador1");

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombreJugadores));
    }

}
