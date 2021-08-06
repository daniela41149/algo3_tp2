package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaDestruccion;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaOcupacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TarjetaObjetivoTest {

    private TarjetaOcupacion tarjetaDeOcupacion;
    private TarjetaDestruccion tarjetaDeDestruccion;

    private TarjetaObjetivo ocupacion1;
    private TarjetaObjetivo ocupacion2;
    private TarjetaObjetivo ocupacion3;
    private TarjetaObjetivo ocupacion4;
    private TarjetaObjetivo ocupacion5;
    private TarjetaObjetivo ocupacion6;
    private TarjetaObjetivo ocupacion7;
    private TarjetaObjetivo ocupacion8;
    private TarjetaObjetivo destruccion1;

    private Moderador moderador;
    private Jugador dueñoDeTarjeta;
    private Tablero tablero;
    private Juego juegoMockeado;

    @BeforeEach
    public void setUp() throws IOException {

        moderador = new Moderador();
        tablero = new Tablero(moderador.pedirPaises(), moderador.pedirContinentes());
        List<TarjetaObjetivo> tarjetas = moderador.pedirTarjetasObjetivo();
        juegoMockeado = mock(Juego.class);

        ocupacion1 = tarjetas.get(0);
        ocupacion2 = tarjetas.get(1);
        ocupacion3 = tarjetas.get(2);
        ocupacion4 = tarjetas.get(3);
        ocupacion5 = tarjetas.get(4);
        ocupacion6 = tarjetas.get(5);
        ocupacion7 = tarjetas.get(6);
        ocupacion8 = tarjetas.get(7);
        destruccion1 = tarjetas.get(8);
    }

    @Test
    public void test01UnaTarjetaConDueñoPeroSinUnTableroOJuegoValidoNoPuedeCumplirSuObjetivo() {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        String enunciado1 = "Ocupar África, 5 países de América del Norte y 4 países de Europa.";
        List<String> continentesAOcupar = new ArrayList<>();
        continentesAOcupar.add("Africa");
        HashMap<String, Integer> cantPaisesPorContinente = new HashMap<>();
        cantPaisesPorContinente.put("America del Norte", 5);
        cantPaisesPorContinente.put("Europa", 4);
        tarjetaDeOcupacion = new TarjetaOcupacion(enunciado1, continentesAOcupar, cantPaisesPorContinente);

        String enunciado2 = "Destruir el ejército azul de ser imposible al jugador de la derecha.";
        String colorADestruir = "Azul";
        tarjetaDeDestruccion = new TarjetaDestruccion(enunciado2, colorADestruir);

        tarjetaDeOcupacion.establecerDueño(dueñoDeTarjeta);
        assertFalse(tarjetaDeOcupacion.cumplioObjetivo(null, juegoMockeado));
        assertFalse(tarjetaDeOcupacion.cumplioObjetivo(tablero, null));

        tarjetaDeDestruccion.establecerDueño(dueñoDeTarjeta);
        assertFalse(tarjetaDeDestruccion.cumplioObjetivo(null, juegoMockeado));
        assertFalse(tarjetaDeDestruccion.cumplioObjetivo(tablero, null));
    }

    @Test
    public void test02SiLaTarjetaNoTieneDueñoEntoncesSuObjetivoNoEstaCumplido() {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        String enunciado1 = "Ocupar África, 5 países de América del Norte y 4 países de Europa.";
        List<String> continentesAOcupar = new ArrayList<>();
        continentesAOcupar.add("Africa");
        HashMap<String, Integer> cantPaisesPorContinente = new HashMap<>();
        cantPaisesPorContinente.put("America del Norte", 5);
        cantPaisesPorContinente.put("Europa", 4);
        tarjetaDeOcupacion = new TarjetaOcupacion(enunciado1, continentesAOcupar, cantPaisesPorContinente);

        String enunciado2 = "Destruir el ejército azul de ser imposible al jugador de la derecha.";
        String colorADestruir = "Azul";
        tarjetaDeDestruccion = new TarjetaDestruccion(enunciado2, colorADestruir);

        assertFalse(tarjetaDeOcupacion.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(tarjetaDeDestruccion.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test03LaTarjetaTieneDueñoQueNoControlaNingunPaisEntoncesSuObjetivoNoEstaCumplido() {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        String enunciado1 = "Ocupar África, 5 países de América del Norte y 4 países de Europa.";
        List<String> continentesAOcupar = new ArrayList<>();
        continentesAOcupar.add("Africa");
        HashMap<String, Integer> cantPaisesPorContinente = new HashMap<>();
        cantPaisesPorContinente.put("America del Norte", 5);
        cantPaisesPorContinente.put("Europa", 4);
        tarjetaDeOcupacion = new TarjetaOcupacion(enunciado1, continentesAOcupar, cantPaisesPorContinente);

        tarjetaDeOcupacion.establecerDueño(dueñoDeTarjeta);
        assertFalse(tarjetaDeOcupacion.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test04LaTarjetaTieneDueñoQueNoDestruyoAlJugadorADestruirEntoncesSuObjetivoNoEstaCumplido() {
        Jugador jugadorADestruir = new Jugador("Pablo", "Azul", juegoMockeado);
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        String enunciado2 = "Destruir el ejército azul de ser imposible al jugador de la derecha.";
        String colorADestruir = "Azul";
        tarjetaDeDestruccion = new TarjetaDestruccion(enunciado2, colorADestruir);

        when(juegoMockeado.jugadorDeColorOSiguiente("Azul", dueñoDeTarjeta)).thenReturn(jugadorADestruir);

        tarjetaDeDestruccion.establecerDueño(dueñoDeTarjeta);
        assertFalse(tarjetaDeDestruccion.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test05SiElDueñoDeLaTarjetaPosee30PaisesCumplioElObjetivoGeneral() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        String enunciado1 = "Ocupar África, 5 países de América del Norte y 4 países de Europa.";
        List<String> continentesAOcupar = new ArrayList<>();
        continentesAOcupar.add("Africa");
        HashMap<String, Integer> cantPaisesPorContinente = new HashMap<>();
        cantPaisesPorContinente.put("America del Norte", 5);
        cantPaisesPorContinente.put("Europa", 4);
        tarjetaDeOcupacion = new TarjetaOcupacion(enunciado1, continentesAOcupar, cantPaisesPorContinente);

        String enunciado2 = "Destruir el ejército azul de ser imposible al jugador de la derecha.";
        String colorADestruir = "Azul";
        tarjetaDeDestruccion = new TarjetaDestruccion(enunciado2, colorADestruir);

        try {
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Zaire"));
            dueñoDeTarjeta.colocarEjercito("Zaire", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Etiopia"));
            dueñoDeTarjeta.colocarEjercito("Etiopia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Egipto"));
            dueñoDeTarjeta.colocarEjercito("Egipto", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Madagascar"));
            dueñoDeTarjeta.colocarEjercito("Madagascar", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sahara"));
            dueñoDeTarjeta.colocarEjercito("Sahara", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sudafrica"));
            dueñoDeTarjeta.colocarEjercito("Sudafrica", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
            dueñoDeTarjeta.colocarEjercito("Oregon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
            dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
            dueñoDeTarjeta.colocarEjercito("Terranova", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
            dueñoDeTarjeta.colocarEjercito("California", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Canada"));
            dueñoDeTarjeta.colocarEjercito("Canada", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Francia"));
            dueñoDeTarjeta.colocarEjercito("Francia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gran Bretaña"));
            dueñoDeTarjeta.colocarEjercito("Gran Bretaña", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Rusia"));
            dueñoDeTarjeta.colocarEjercito("Rusia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("España"));
            dueñoDeTarjeta.colocarEjercito("España", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Polonia"));
            dueñoDeTarjeta.colocarEjercito("Polonia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Italia"));
            dueñoDeTarjeta.colocarEjercito("Italia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alemania"));
            dueñoDeTarjeta.colocarEjercito("Alemania", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Suecia"));
            dueñoDeTarjeta.colocarEjercito("Suecia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Islandia"));
            dueñoDeTarjeta.colocarEjercito("Islandia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Tartaria"));
            dueñoDeTarjeta.colocarEjercito("Tartaria", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Mongolia"));
            dueñoDeTarjeta.colocarEjercito("Mongolia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Kamtchatka"));
            dueñoDeTarjeta.colocarEjercito("Kamtchatka", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Turquia"));
            dueñoDeTarjeta.colocarEjercito("Turquia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Iran"));
            dueñoDeTarjeta.colocarEjercito("Iran", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Israel"));
            dueñoDeTarjeta.colocarEjercito("Israel", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Taymir"));
            dueñoDeTarjeta.colocarEjercito("Taymir", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Aral"));
            dueñoDeTarjeta.colocarEjercito("Aral", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Siberia"));
            dueñoDeTarjeta.colocarEjercito("Siberia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Japon"));
            dueñoDeTarjeta.colocarEjercito("Japon", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(30, dueñoDeTarjeta.cantidadPaises());

        tarjetaDeOcupacion.establecerDueño(dueñoDeTarjeta);
        assertTrue(tarjetaDeOcupacion.cumplioObjetivo(tablero, juegoMockeado));

        tarjetaDeDestruccion.establecerDueño(dueñoDeTarjeta);
        assertTrue(tarjetaDeDestruccion.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test06ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion1() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de Africa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Zaire"));
            dueñoDeTarjeta.colocarEjercito("Zaire", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Etiopia"));
            dueñoDeTarjeta.colocarEjercito("Etiopia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Egipto"));
            dueñoDeTarjeta.colocarEjercito("Egipto", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Madagascar"));
            dueñoDeTarjeta.colocarEjercito("Madagascar", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sahara"));
            dueñoDeTarjeta.colocarEjercito("Sahara", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sudafrica"));
            dueñoDeTarjeta.colocarEjercito("Sudafrica", 1);

            //Ocupacion de 5 paises de America del Norte
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
            dueñoDeTarjeta.colocarEjercito("Oregon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
            dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
            dueñoDeTarjeta.colocarEjercito("Terranova", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
            dueñoDeTarjeta.colocarEjercito("California", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Canada"));
            dueñoDeTarjeta.colocarEjercito("Canada", 1);

            //Ocupacion de 4 paises de Europa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Francia"));
            dueñoDeTarjeta.colocarEjercito("Francia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gran Bretaña"));
            dueñoDeTarjeta.colocarEjercito("Gran Bretaña", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Rusia"));
            dueñoDeTarjeta.colocarEjercito("Rusia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("España"));
            dueñoDeTarjeta.colocarEjercito("España", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(15, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion1);
        assertTrue(ocupacion1.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test07ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion2() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de America del Sur
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Chile"));
            dueñoDeTarjeta.colocarEjercito("Chile", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Argentina"));
            dueñoDeTarjeta.colocarEjercito("Argentina", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Uruguay"));
            dueñoDeTarjeta.colocarEjercito("Uruguay", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Brasil"));
            dueñoDeTarjeta.colocarEjercito("Brasil", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Colombia"));
            dueñoDeTarjeta.colocarEjercito("Colombia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Peru"));
            dueñoDeTarjeta.colocarEjercito("Peru", 1);

            //Ocupacion de 7 paises de Europa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Francia"));
            dueñoDeTarjeta.colocarEjercito("Francia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gran Bretaña"));
            dueñoDeTarjeta.colocarEjercito("Gran Bretaña", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Rusia"));
            dueñoDeTarjeta.colocarEjercito("Rusia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("España"));
            dueñoDeTarjeta.colocarEjercito("España", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Italia"));
            dueñoDeTarjeta.colocarEjercito("Italia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alemania"));
            dueñoDeTarjeta.colocarEjercito("Alemania", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Suecia"));
            dueñoDeTarjeta.colocarEjercito("Suecia", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(13, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion2);
        assertTrue(ocupacion2.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test08ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion3() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de Asia
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Tartaria"));
            dueñoDeTarjeta.colocarEjercito("Tartaria", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Mongolia"));
            dueñoDeTarjeta.colocarEjercito("Mongolia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Kamtchatka"));
            dueñoDeTarjeta.colocarEjercito("Kamtchatka", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Turquia"));
            dueñoDeTarjeta.colocarEjercito("Turquia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Iran"));
            dueñoDeTarjeta.colocarEjercito("Iran", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Israel"));
            dueñoDeTarjeta.colocarEjercito("Israel", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Taymir"));
            dueñoDeTarjeta.colocarEjercito("Taymir", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Aral"));
            dueñoDeTarjeta.colocarEjercito("Aral", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Siberia"));
            dueñoDeTarjeta.colocarEjercito("Siberia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Japon"));
            dueñoDeTarjeta.colocarEjercito("Japon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gobi"));
            dueñoDeTarjeta.colocarEjercito("Gobi", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Arabia"));
            dueñoDeTarjeta.colocarEjercito("Arabia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("India"));
            dueñoDeTarjeta.colocarEjercito("India", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("China"));
            dueñoDeTarjeta.colocarEjercito("China", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Malasia"));
            dueñoDeTarjeta.colocarEjercito("Malasia", 1);

            //Ocupacion de 2 paises de America del Sur
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Argentina"));
            dueñoDeTarjeta.colocarEjercito("Argentina", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Uruguay"));
            dueñoDeTarjeta.colocarEjercito("Uruguay", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(17, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion3);
        assertTrue(ocupacion3.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test09ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion4() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de Europa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Francia"));
            dueñoDeTarjeta.colocarEjercito("Francia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gran Bretaña"));
            dueñoDeTarjeta.colocarEjercito("Gran Bretaña", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Rusia"));
            dueñoDeTarjeta.colocarEjercito("Rusia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("España"));
            dueñoDeTarjeta.colocarEjercito("España", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Polonia"));
            dueñoDeTarjeta.colocarEjercito("Polonia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Italia"));
            dueñoDeTarjeta.colocarEjercito("Italia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alemania"));
            dueñoDeTarjeta.colocarEjercito("Alemania", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Suecia"));
            dueñoDeTarjeta.colocarEjercito("Suecia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Islandia"));
            dueñoDeTarjeta.colocarEjercito("Islandia", 1);

            //Ocupacion de 4 paises de Asia
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Arabia"));
            dueñoDeTarjeta.colocarEjercito("Arabia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("India"));
            dueñoDeTarjeta.colocarEjercito("India", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("China"));
            dueñoDeTarjeta.colocarEjercito("China", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Malasia"));
            dueñoDeTarjeta.colocarEjercito("Malasia", 1);

            //Ocupacion de 2 paises de America del Sur
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Argentina"));
            dueñoDeTarjeta.colocarEjercito("Argentina", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Uruguay"));
            dueñoDeTarjeta.colocarEjercito("Uruguay", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(15, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion4);
        assertTrue(ocupacion4.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test10ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion5() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de America del Norte
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
            dueñoDeTarjeta.colocarEjercito("Oregon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
            dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
            dueñoDeTarjeta.colocarEjercito("Terranova", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
            dueñoDeTarjeta.colocarEjercito("California", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Canada"));
            dueñoDeTarjeta.colocarEjercito("Canada", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Yukon"));
            dueñoDeTarjeta.colocarEjercito("Yukon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Groenlandia"));
            dueñoDeTarjeta.colocarEjercito("Groenlandia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alaska"));
            dueñoDeTarjeta.colocarEjercito("Alaska", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Mexico"));
            dueñoDeTarjeta.colocarEjercito("Mexico", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Labrador"));
            dueñoDeTarjeta.colocarEjercito("Labrador", 1);

            //Ocupacion de 4 paises de Asia
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Arabia"));
            dueñoDeTarjeta.colocarEjercito("Arabia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("India"));
            dueñoDeTarjeta.colocarEjercito("India", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("China"));
            dueñoDeTarjeta.colocarEjercito("China", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Malasia"));
            dueñoDeTarjeta.colocarEjercito("Malasia", 1);

            //Ocupacion de 2 paises de Oceania
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Australia"));
            dueñoDeTarjeta.colocarEjercito("Australia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Borneo"));
            dueñoDeTarjeta.colocarEjercito("Borneo", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(16, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion5);
        assertTrue(ocupacion5.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test11ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion6() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de 2 paises de Oceania
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Australia"));
            dueñoDeTarjeta.colocarEjercito("Australia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Borneo"));
            dueñoDeTarjeta.colocarEjercito("Borneo", 1);

            //Ocupacion de 2 paises de Africa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Zaire"));
            dueñoDeTarjeta.colocarEjercito("Zaire", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Etiopia"));
            dueñoDeTarjeta.colocarEjercito("Etiopia", 1);

            //Ocupacion de 2 paises de America del Sur
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Argentina"));
            dueñoDeTarjeta.colocarEjercito("Argentina", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Uruguay"));
            dueñoDeTarjeta.colocarEjercito("Uruguay", 1);

            //Ocupacion de 3 paises de Europa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alemania"));
            dueñoDeTarjeta.colocarEjercito("Alemania", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Suecia"));
            dueñoDeTarjeta.colocarEjercito("Suecia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Islandia"));
            dueñoDeTarjeta.colocarEjercito("Islandia", 1);

            //Ocupacion de 4 paises de America del Norte
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
            dueñoDeTarjeta.colocarEjercito("Oregon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
            dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
            dueñoDeTarjeta.colocarEjercito("Terranova", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
            dueñoDeTarjeta.colocarEjercito("California", 1);

            //Ocupacion de 3 paises de Asia
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Arabia"));
            dueñoDeTarjeta.colocarEjercito("Arabia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("India"));
            dueñoDeTarjeta.colocarEjercito("India", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("China"));
            dueñoDeTarjeta.colocarEjercito("China", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(16, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion6);
        assertTrue(ocupacion6.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test12ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion7() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de Oceania
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Australia"));
            dueñoDeTarjeta.colocarEjercito("Australia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Borneo"));
            dueñoDeTarjeta.colocarEjercito("Borneo", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sumatra"));
            dueñoDeTarjeta.colocarEjercito("Sumatra", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Java"));
            dueñoDeTarjeta.colocarEjercito("Java", 1);

            //Ocupacion de America del Norte
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
            dueñoDeTarjeta.colocarEjercito("Oregon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
            dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
            dueñoDeTarjeta.colocarEjercito("Terranova", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
            dueñoDeTarjeta.colocarEjercito("California", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Canada"));
            dueñoDeTarjeta.colocarEjercito("Canada", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Yukon"));
            dueñoDeTarjeta.colocarEjercito("Yukon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Groenlandia"));
            dueñoDeTarjeta.colocarEjercito("Groenlandia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alaska"));
            dueñoDeTarjeta.colocarEjercito("Alaska", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Mexico"));
            dueñoDeTarjeta.colocarEjercito("Mexico", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Labrador"));
            dueñoDeTarjeta.colocarEjercito("Labrador", 1);

            //Ocupacion de 2 paises de Europa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alemania"));
            dueñoDeTarjeta.colocarEjercito("Alemania", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Suecia"));
            dueñoDeTarjeta.colocarEjercito("Suecia", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(16, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion7);
        assertTrue(ocupacion7.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test13ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion8() {
        boolean hayJugadaInvalida = false;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        try {
            //Ocupacion de Africa
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Zaire"));
            dueñoDeTarjeta.colocarEjercito("Zaire", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Etiopia"));
            dueñoDeTarjeta.colocarEjercito("Etiopia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Egipto"));
            dueñoDeTarjeta.colocarEjercito("Egipto", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Madagascar"));
            dueñoDeTarjeta.colocarEjercito("Madagascar", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sahara"));
            dueñoDeTarjeta.colocarEjercito("Sahara", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sudafrica"));
            dueñoDeTarjeta.colocarEjercito("Sudafrica", 1);

            //Ocupacion de America del Sur
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Chile"));
            dueñoDeTarjeta.colocarEjercito("Chile", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Argentina"));
            dueñoDeTarjeta.colocarEjercito("Argentina", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Uruguay"));
            dueñoDeTarjeta.colocarEjercito("Uruguay", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Brasil"));
            dueñoDeTarjeta.colocarEjercito("Brasil", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Colombia"));
            dueñoDeTarjeta.colocarEjercito("Colombia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Peru"));
            dueñoDeTarjeta.colocarEjercito("Peru", 1);

            //Ocupacion de 5 paises de America del Norte
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Yukon"));
            dueñoDeTarjeta.colocarEjercito("Yukon", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Groenlandia"));
            dueñoDeTarjeta.colocarEjercito("Groenlandia", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Alaska"));
            dueñoDeTarjeta.colocarEjercito("Alaska", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Mexico"));
            dueñoDeTarjeta.colocarEjercito("Mexico", 1);
            dueñoDeTarjeta.agregarPais(tablero.buscarPais("Labrador"));
            dueñoDeTarjeta.colocarEjercito("Labrador", 1);
        } catch (JugadaInvalidaException e){
            hayJugadaInvalida = true;
        }

        assertFalse(hayJugadaInvalida);
        assertEquals(17, dueñoDeTarjeta.cantidadPaises());
        dueñoDeTarjeta.establecerObjetivo(ocupacion8);
        assertTrue(ocupacion8.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test14ElDueñoDeLaTarjetaCumpleElObjetivoDeDestruirAlJugadorAzul() {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);
        Jugador jugadorAzul = new Jugador("Pedro", "Azul", juegoMockeado);

        dueñoDeTarjeta.establecerObjetivo(destruccion1);
        jugadorAzul.establecerPosibleDestructor(dueñoDeTarjeta);

        when(juegoMockeado.jugadorDeColorOSiguiente("Azul", dueñoDeTarjeta)).thenReturn(jugadorAzul);

        assertTrue(dueñoDeTarjeta.cumplioObjetivo(tablero));
    }

    @Test
    public void test15ElDueñoDeLaTarjetaPoseeLosContinentesDelEnunciadoPeroNoLosPaisesPorContinentesEntoncesNoCumpleObjetivo() throws JugadaInvalidaException {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);
        dueñoDeTarjeta.establecerObjetivo(ocupacion1);

        //Controla Africa
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Zaire"));
        dueñoDeTarjeta.colocarEjercito("Zaire", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Etiopia"));
        dueñoDeTarjeta.colocarEjercito("Etiopia", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Egipto"));
        dueñoDeTarjeta.colocarEjercito("Egipto", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Madagascar"));
        dueñoDeTarjeta.colocarEjercito("Madagascar", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sahara"));
        dueñoDeTarjeta.colocarEjercito("Sahara", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Sudafrica"));
        dueñoDeTarjeta.colocarEjercito("Sudafrica", 1);

        assertEquals(6, dueñoDeTarjeta.cantidadPaises());
        assertFalse(dueñoDeTarjeta.cumplioObjetivo(tablero));
    }

    @Test
    public void test16ElJugadorAzulNoPoseeLosContinentesDelEnunciadoPeroSiLosPaisesPorContinentesEntoncesNoCumpleObjetivo() throws JugadaInvalidaException {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);
        dueñoDeTarjeta.establecerObjetivo(ocupacion1);

        //Ocupacion de 5 paises de America del Norte
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Oregon"));
        dueñoDeTarjeta.colocarEjercito("Oregon", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Nueva York"));
        dueñoDeTarjeta.colocarEjercito("Nueva York", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Terranova"));
        dueñoDeTarjeta.colocarEjercito("Terranova", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("California"));
        dueñoDeTarjeta.colocarEjercito("California", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Canada"));
        dueñoDeTarjeta.colocarEjercito("Canada", 1);
        //Ocupacion de 4 paises de Europa
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Francia"));
        dueñoDeTarjeta.colocarEjercito("Francia", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Gran Bretaña"));
        dueñoDeTarjeta.colocarEjercito("Gran Bretaña", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("Rusia"));
        dueñoDeTarjeta.colocarEjercito("Rusia", 1);
        dueñoDeTarjeta.agregarPais(tablero.buscarPais("España"));
        dueñoDeTarjeta.colocarEjercito("España", 1);

        assertEquals(9, dueñoDeTarjeta.cantidadPaises());
        assertFalse(dueñoDeTarjeta.cumplioObjetivo(tablero));
    }
}