package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarjetaObjetivoTest {

    private TarjetaObjetivo ocupacion1;
    private TarjetaObjetivo ocupacion2;
    private TarjetaObjetivo ocupacion3;
    private TarjetaObjetivo ocupacion4;
    private TarjetaObjetivo ocupacion5;
    private TarjetaObjetivo ocupacion6;
    private TarjetaObjetivo ocupacion7;
    private TarjetaObjetivo ocupacion8;
    private TarjetaObjetivo destruccion1;
    private TarjetaObjetivo destruccion2;
    private TarjetaObjetivo destruccion3;
    private TarjetaObjetivo destruccion4;
    private TarjetaObjetivo destruccion5;
    private TarjetaObjetivo destruccion6;

    private Moderador moderador;
    private Jugador dueñoDeTarjeta;
    private Juego juegoMockeado;
    private Tablero tablero;

    private List<Pais> paises;
    private List<Continente> continentes;
    private Pais argentina;
    private Pais brasil;
    List<Integer> dadosGanadores;
    List<Integer> dadosPerdedores;

    @BeforeEach
    public void setUp(){

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
        destruccion2 = tarjetas.get(9);
        destruccion3 = tarjetas.get(10);
        destruccion4 = tarjetas.get(11);
        destruccion5 = tarjetas.get(12);
        destruccion6 = tarjetas.get(13);

        List<String> limitrofesArg = new ArrayList<>();
        limitrofesArg.add("Chile");
        limitrofesArg.add("Brasil");
        limitrofesArg.add("Uruguay");
        argentina = new Pais("Argentina", limitrofesArg);

        List<String> limitrofesBr = new ArrayList<>();
        limitrofesBr.add("Chile");
        limitrofesBr.add("Argentina");
        limitrofesBr.add("Uruguay");
        brasil = new Pais("Brasil", limitrofesBr);

        paises = new ArrayList<>();
        paises.add(argentina);
        paises.add(brasil);

        continentes = new ArrayList<>();
        continentes.add(new Continente("America del Sur", paises, 3));

        dadosGanadores = new ArrayList<>();
        dadosGanadores.add(5);
        dadosGanadores.add(5);
        dadosPerdedores = new ArrayList<>();
        dadosPerdedores.add(1);
        dadosPerdedores.add(2);
    }

    @Test
    public void test01SiLaTarjetaNoTieneDueñoEntoncesSuObjetivoNoEstaCumplido() {
        assertFalse(ocupacion1.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion2.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion3.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion4.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion5.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion6.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion7.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion8.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion1.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion2.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion3.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion4.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion5.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(destruccion6.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test02LaTarjetaTieneDueñoQueNoControlaNingunPaisEntoncesSuObjetivoNoEstaCumplido() {
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        assertFalse(ocupacion1.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion2.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion3.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion4.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion5.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion6.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion7.cumplioObjetivo(tablero, juegoMockeado));
        assertFalse(ocupacion8.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test03LaTarjetaTieneDueñoQueNoDestruyoAlJugadorADestruirEntoncesSuObjetivoNoEstaCumplido() {
        Jugador jugadorADestruir;
        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);

        jugadorADestruir = new Jugador("Pablo", "Azul", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Azul", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion1.cumplioObjetivo(tablero, juegoMockeado));

        jugadorADestruir = new Jugador("Pablo", "Rojo", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Rojo", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion2.cumplioObjetivo(tablero, juegoMockeado));

        jugadorADestruir = new Jugador("Pablo", "Negro", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Negro", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion3.cumplioObjetivo(tablero, juegoMockeado));

        jugadorADestruir = new Jugador("Pablo", "Amarillo", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Amarillo", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion4.cumplioObjetivo(tablero, juegoMockeado));

        jugadorADestruir = new Jugador("Pablo", "Verde", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Verde", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion5.cumplioObjetivo(tablero, juegoMockeado));

        jugadorADestruir = new Jugador("Pablo", "Rosa", juegoMockeado);
        when(juegoMockeado.jugadorDeColorOSiguiente("Rosa", dueñoDeTarjeta)).thenReturn(jugadorADestruir);
        assertFalse(destruccion6.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test04SiElDueñoDeLaTarjetaPosee30PaisesCumplioElObjetivoGeneral() {
        boolean hayJugadaInvalida = false;

        dueñoDeTarjeta = new Jugador("Pedro", "Rojo", juegoMockeado);
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

        dueñoDeTarjeta.establecerObjetivo(ocupacion1);
        assertTrue(ocupacion1.cumplioObjetivo(tablero, juegoMockeado));

        dueñoDeTarjeta.establecerObjetivo(destruccion1);
        assertTrue(destruccion1.cumplioObjetivo(tablero, juegoMockeado));
    }

    @Test
    public void test05ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion1() {
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
    public void test06ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion2() {
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
    public void test07ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion3() {
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
    public void test08ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion4() {
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
    public void test09ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion5() {
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
    public void test10ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion6() {
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
    public void test11ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion7() {
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
    public void test12ElDueñoDeLaTarjetaCumpleObjetivoDeOcupacion8() {
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
    public void test13ElDueñoDeLaTarjetaCumpleElObjetivoDeDestruirAlJugadorAzul() throws CantidadInvalidaDeJugadoresException, JugadaInvalidaException {
        Jugador jugadorAzul;
        List<String> jugadores = new ArrayList<>();
        jugadores.add("jugadorAzul");
        jugadores.add("jugadorRojo");

        Juego juego = new Juego(paises, continentes, jugadores);

        jugadorAzul = juego.jugadorEnTurno();
        jugadorAzul.agregarPais(brasil);
        juego.colocarEjercito("Brasil", 1);
        juego.pasarTurno();

        dueñoDeTarjeta = juego.jugadorEnTurno();
        dueñoDeTarjeta.establecerObjetivo(destruccion1);
        dueñoDeTarjeta.agregarPais(argentina);
        juego.colocarEjercito("Argentina", 2);

        Dados mockDadosJugadorRojo = mock(Dados.class);
        when(mockDadosJugadorRojo.dadosAtaque(anyInt())).thenReturn(dadosGanadores);
        Dados mockDadosJugadorAzul = mock(Dados.class);
        when(mockDadosJugadorAzul.dadosDefensa(anyInt())).thenReturn(dadosPerdedores);

        Ejercito ejercitoPaisAtacante = argentina.getEjercito();
        Ejercito ejercitoPaisDefensor = brasil.getEjercito();
        ejercitoPaisAtacante.setDados(mockDadosJugadorRojo);
        ejercitoPaisDefensor.setDados(mockDadosJugadorAzul);

        juego.atacar("Argentina", "Brasil", 1);

        assertEquals(0, jugadorAzul.cantidadPaises());
        assertEquals(2, dueñoDeTarjeta.cantidadPaises());
        juego.cumplioObjetivo(dueñoDeTarjeta);
        assertTrue(juego.cumplioObjetivo(dueñoDeTarjeta));
    }
}
