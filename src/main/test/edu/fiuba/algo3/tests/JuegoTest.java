package edu.fiuba.algo3.tests;

public class JuegoTest {
    /*
    @Test
    public void test01NoSePuedeCrearUnJuegoConMasDeSieteJugadores() throws CantidadInvalidaDeJugadoresException {
        Tablero mockTablero = mock(Tablero.class);
        ArrayList<String> nombreJugadores = new ArrayList<>();
        nombreJugadores.add("jugador1");
        nombreJugadores.add("jugador2");
        nombreJugadores.add("jugador3");
        nombreJugadores.add("jugador4");
        nombreJugadores.add("jugador5");
        nombreJugadores.add("jugador6");
        nombreJugadores.add("jugador7");

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(mockTablero, nombreJugadores));
    }

    @Test
    public void test02NoSePuedeCrearUnJuegoMenosDeDosJugadores() throws CantidadInvalidaDeJugadoresException {
        Tablero mockTablero = mock(Tablero.class);
        ArrayList<String> nombreJugadores = new ArrayList<>();
        nombreJugadores.add("jugador1");

        assertThrows(CantidadInvalidaDeJugadoresException.class, () -> new Juego(mockTablero, nombreJugadores));
    }

    @Test
    public void test03SeCreaUnJuegoConDosJugadoresYseRepartenLosPaises() throws SuperaMaximoDeJugadoresException, NoSeSuperaMinimoDeJugadoresException, JugadaInvalidaException {
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
    */
}
