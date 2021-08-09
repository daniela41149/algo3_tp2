package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Aleatorio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    Aleatorio aleatorio;
    Moderador moderador;
    List<String> nombresDeCincoJugadores;

    @BeforeEach
    public void setup() throws IOException {
        aleatorio = new Aleatorio();
        moderador = new Moderador();
        nombresDeCincoJugadores = new ArrayList<>();
        nombresDeCincoJugadores.add("jugador1");
        nombresDeCincoJugadores.add("jugador2");
        nombresDeCincoJugadores.add("jugador3");
        nombresDeCincoJugadores.add("jugador4");
        nombresDeCincoJugadores.add("jugador5");
    }

    @Test
    public void test01SePuedeCrearUnJuegoDeDosJugadores() {
        ArrayList<String> dosJugadores = new ArrayList<>();
        dosJugadores.add("jugador1");
        dosJugadores.add("jugador2");
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), dosJugadores);

        List<Jugador> jugadores = juego.devolverJugadores();

        assertEquals(2, jugadores.size());
        assertEquals(jugadores.get(0).getNombre(), dosJugadores.get(0));
        assertEquals(jugadores.get(1).getNombre(), dosJugadores.get(1));
    }

    @Test
    public void test02SePuedeCrearUnJuegoDeSeisJugadores() {
        ArrayList<String> seisJugadores = new ArrayList<>();
        seisJugadores.add("jugador1");
        seisJugadores.add("jugador2");
        seisJugadores.add("jugador3");
        seisJugadores.add("jugador4");
        seisJugadores.add("jugador5");
        seisJugadores.add("jugador6");
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), seisJugadores);

        List<Jugador> jugadores = juego.devolverJugadores();

        assertEquals(6, jugadores.size());
        assertEquals(jugadores.get(0).getNombre(), seisJugadores.get(0));
        assertEquals(jugadores.get(1).getNombre(), seisJugadores.get(1));
        assertEquals(jugadores.get(2).getNombre(), seisJugadores.get(2));
        assertEquals(jugadores.get(3).getNombre(), seisJugadores.get(3));
        assertEquals(jugadores.get(4).getNombre(), seisJugadores.get(4));
        assertEquals(jugadores.get(5).getNombre(), seisJugadores.get(5));
    }

    @Test
    public void test03AlCrearUnJuegoSinComenzarLaFaseInicialElJugadorEnTurnoComienzaPorElPrimero() {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        Jugador jugadorEnTurno = juego.jugadorEnTurno();
        List<Jugador> jugadores = juego.devolverJugadores();

        assertEquals(jugadores.get(0), jugadorEnTurno);
    }

    @Test
    public void test04SeRespetanLosTurnosAscendenteHataCumplirUnaRondaYDespuesVuelveAEmpezar() {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        List<Jugador> jugadores = juego.devolverJugadores();

        assertEquals(jugadores.get(0), juego.jugadorEnTurno());
        juego.pasarTurno();
        assertEquals(jugadores.get(1), juego.jugadorEnTurno());
        juego.pasarTurno();
        assertEquals(jugadores.get(2), juego.jugadorEnTurno());
        juego.pasarTurno();
        assertEquals(jugadores.get(3), juego.jugadorEnTurno());
        juego.pasarTurno();
        assertEquals(jugadores.get(4), juego.jugadorEnTurno());
        juego.pasarTurno();
        assertEquals(jugadores.get(0), juego.jugadorEnTurno());
    }

    @Test
    public void test05AlComenzarLaFaseInicialSeRepartenLosPaisesParaCadaJugadorDeFormaAleatoria() throws JugadaInvalidaException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        List<Jugador> jugadores = juego.devolverJugadores();

        assertEquals( 10, jugadores.get(0).cantidadPaises());
        assertEquals( 10, jugadores.get(1).cantidadPaises());
        assertEquals( 10, jugadores.get(2).cantidadPaises());
        assertEquals( 10, jugadores.get(3).cantidadPaises());
        assertEquals( 10, jugadores.get(4).cantidadPaises());

        List<Pais> paisesDelJugador1 = jugadores.get(0).pedirPaises();
        List<Pais> paisesDelJugador2 = jugadores.get(1).pedirPaises();
        List<Pais> paisesDelJugador3 = jugadores.get(2).pedirPaises();
        List<Pais> paisesDelJugador4 = jugadores.get(3).pedirPaises();
        List<Pais> paisesDelJugador5 = jugadores.get(4).pedirPaises();

        assertNotEquals(paisesDelJugador1, paisesDelJugador2);
        assertNotEquals(paisesDelJugador1, paisesDelJugador3);
        assertNotEquals(paisesDelJugador1, paisesDelJugador4);
        assertNotEquals(paisesDelJugador1, paisesDelJugador5);
        assertNotEquals(paisesDelJugador2, paisesDelJugador3);
        assertNotEquals(paisesDelJugador2, paisesDelJugador4);
        assertNotEquals(paisesDelJugador2, paisesDelJugador5);
        assertNotEquals(paisesDelJugador3, paisesDelJugador4);
        assertNotEquals(paisesDelJugador3, paisesDelJugador5);
        assertNotEquals(paisesDelJugador4, paisesDelJugador5);
    }

    @Test
    public void test06UnJugadorIvalidoNoPuedeCumplirObjetivo() {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);
        assertFalse(juego.cumplioObjetivo(null));
    }

    @Test
    public void test07AlComenzarLaFaseInicialNingunJugadorCumplioSuObjetivo() throws JugadaInvalidaException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        Jugador jugador1 = juego.jugadorEnTurno();
        assertFalse(juego.cumplioObjetivo(jugador1));
        juego.pasarTurno();
        Jugador jugador2 = juego.jugadorEnTurno();
        assertFalse(juego.cumplioObjetivo(jugador2));
        juego.pasarTurno();
        Jugador jugador3 = juego.jugadorEnTurno();
        assertFalse(juego.cumplioObjetivo(jugador3));
        juego.pasarTurno();
        Jugador jugador4 = juego.jugadorEnTurno();
        assertFalse(juego.cumplioObjetivo(jugador4));
        juego.pasarTurno();
        Jugador jugador5 = juego.jugadorEnTurno();
        assertFalse(juego.cumplioObjetivo(jugador5));
    }

    @Test
    public void test08NoSePuedeColocarMasDe5EjercitosEnLaPrimeraVuelta() throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        List<Pais> paisesJugadorEnTurno = juego.jugadorEnTurno().pedirPaises();
        juego.colocarEjercitoPrimeraVuelta(paisesJugadorEnTurno.get(0).getNombre(), 1);
        juego.colocarEjercitoPrimeraVuelta(paisesJugadorEnTurno.get(1).getNombre(), 1);
        juego.colocarEjercitoPrimeraVuelta(paisesJugadorEnTurno.get(2).getNombre(), 1);
        juego.colocarEjercitoPrimeraVuelta(paisesJugadorEnTurno.get(3).getNombre(), 1);

        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> juego.colocarEjercitoPrimeraVuelta(paisesJugadorEnTurno.get(5).getNombre(), 2));
    }

    @Test
    public void test09NoSePuedeColocarMasDe3EjercitosEnLaSegundaVuelta() throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        List<Pais> paisesJugadorEnTurno = juego.jugadorEnTurno().pedirPaises();
        juego.colocarEjercitoSegundaVuelta(paisesJugadorEnTurno.get(0).getNombre(), 1);
        juego.colocarEjercitoSegundaVuelta(paisesJugadorEnTurno.get(1).getNombre(), 1);

        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> juego.colocarEjercitoSegundaVuelta(paisesJugadorEnTurno.get(3).getNombre(), 2));
    }

    @Test
    public void test10UnJugadorColocaSusEjercitosDePrimeraVuelta() throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        Jugador jugador = juego.jugadorEnTurno();
        List<Pais> paisesJugador = jugador.pedirPaises();

        juego.colocarEjercitoPrimeraVuelta(paisesJugador.get(0).getNombre(), 3);
        juego.colocarEjercitoPrimeraVuelta(paisesJugador.get(1).getNombre(), 2);

        assertTrue(paisesJugador.get(0).esElDueño(jugador));
        assertEquals( 4, paisesJugador.get(0).cantidadDeFichas());
        assertTrue(paisesJugador.get(1).esElDueño(jugador));
        assertEquals( 3, paisesJugador.get(1).cantidadDeFichas());
        assertNotEquals(jugador, juego.jugadorEnTurno());
    }

    @Test
    public void test11UnJugadorColocaSusEjercitosDeSegundaVuelta() throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        Juego juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), nombresDeCincoJugadores);

        juego.guardarMazoDeTarjetasPais(moderador.pedirTarjetasPais());
        juego.guardarMazoDeTarjetasObjetivo(moderador.pedirTarjetasObjetivo());
        juego.comenzarFaseInicial(aleatorio);

        Jugador jugador = juego.jugadorEnTurno();
        List<Pais> paisesJugador = jugador.pedirPaises();

        juego.colocarEjercitoSegundaVuelta(paisesJugador.get(0).getNombre(), 1);
        juego.colocarEjercitoSegundaVuelta(paisesJugador.get(1).getNombre(), 2);

        assertTrue(paisesJugador.get(0).esElDueño(jugador));
        assertEquals( 2, paisesJugador.get(0).cantidadDeFichas());
        assertTrue(paisesJugador.get(1).esElDueño(jugador));
        assertEquals( 3, paisesJugador.get(1).cantidadDeFichas());
        assertNotEquals(jugador, juego.jugadorEnTurno());
    }

}
