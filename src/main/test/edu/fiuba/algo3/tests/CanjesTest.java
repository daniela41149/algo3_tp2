package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanjesTest {


    private Juego juego;
    private List<TarjetaPais> tarjetasPais;
    private List<TarjetaObjetivo> tarjetasObjetivos;
    private List<Pais> listaPaises;
    private List<Continente> listaContinentes;



    @BeforeEach
    public void setup() throws IOException {
        List<String> nombresJugadores = new ArrayList<>();
        Moderador moderador = new Moderador();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");

        List<TarjetaPais> tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Argentina","Comodin"));
        tarjetasPais.add(new TarjetaPais("Uruguay","Globo"));
        tarjetasPais.add(new TarjetaPais("Brasil","Barco"));
        tarjetasPais.add(new TarjetaPais("Chile","Globo"));
        tarjetasPais.add(new TarjetaPais("Gran Bretaña","Barco"));
        tarjetasPais.add(new TarjetaPais("Tartaria","Cañon"));
        tarjetasPais.add(new TarjetaPais("Mongolia","Barco"));
        tarjetasPais.add(new TarjetaPais("Zaire","Barco"));
        tarjetasPais.add(new TarjetaPais("Groenlandia","Globo"));
        tarjetasPais.add(new TarjetaPais("Gobi","Globo"));
        tarjetasPais.add(new TarjetaPais("Francia","Globo"));
        tarjetasPais.add(new TarjetaPais("Peru","Barco"));


        listaContinentes = moderador.pedirContinentes();
        tarjetasObjetivos = moderador.pedirTarjetasObjetivo();

        juego = new Juego (listaPaises,listaContinentes,nombresJugadores);
        juego.guardarMazoDeTarjetasPais(tarjetasPais);
        juego.guardarMazoDeTarjetasObjetivo(tarjetasObjetivos);
    }



    @Test
    public void test001JugadorTiene12TarjetasPaisYRealizaElPrimerCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        for (int i = 0; i < 12; i++){
            jugador.pedirTarjetaPais();
        }

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),4);
    }

    @Test
    public void test002JugadorTiene12TarjetasPaisYRealizaElSegundoCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        for (int i = 0; i < 12; i++){
            jugador.pedirTarjetaPais();
        }

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),4);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Gran Bretaña");
        nombresTarjetasPaisParaCanjear.add("Mongolia");
        nombresTarjetasPaisParaCanjear.add("Zaire");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),7);

    }

    @Test
    public void test003JugadorTiene12TarjetasPaisYRealizaElTercerCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        for (int i = 0; i < 12; i++){
            jugador.pedirTarjetaPais();
        }

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),4);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Gran Bretaña");
        nombresTarjetasPaisParaCanjear.add("Mongolia");
        nombresTarjetasPaisParaCanjear.add("Zaire");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),7);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Peru");
        nombresTarjetasPaisParaCanjear.add("Francia");
        nombresTarjetasPaisParaCanjear.add("Tartaria");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),10);

    }

    @Test
    public void test004JugadorTiene12TarjetasPaisYRealizaElCuartoCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        for (int i = 0; i < 12; i++){
            jugador.pedirTarjetaPais();
        }

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),4);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Gran Bretaña");
        nombresTarjetasPaisParaCanjear.add("Mongolia");
        nombresTarjetasPaisParaCanjear.add("Zaire");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),7);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Peru");
        nombresTarjetasPaisParaCanjear.add("Francia");
        nombresTarjetasPaisParaCanjear.add("Tartaria");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),10);

        nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Chile");
        nombresTarjetasPaisParaCanjear.add("Groenlandia");
        nombresTarjetasPaisParaCanjear.add("Gobi");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),15);

    }

    @Test
    public void test005DosJugadoresRealizanUnCanjeCadaUno() throws IOException  {
        List<String> nombresJugadores = new ArrayList<>();
        Moderador moderador = new Moderador();
        listaContinentes = moderador.pedirContinentes();
        tarjetasObjetivos = moderador.pedirTarjetasObjetivo();

        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");
        juego = new Juego (listaPaises,listaContinentes,nombresJugadores);
        juego.guardarMazoDeTarjetasObjetivo(tarjetasObjetivos);
        List<Jugador> listaJugadores = juego.devolverJugadores();

        List<TarjetaPais> tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Uruguay","Globo"));
        tarjetasPais.add(new TarjetaPais("Groenlandia","Globo"));
        tarjetasPais.add(new TarjetaPais("Chile","Globo"));
        juego.guardarMazoDeTarjetasPais(tarjetasPais);

        Jugador jugadorPedro = listaJugadores.get(0);

        jugadorPedro.pedirTarjetaPais();
        jugadorPedro.pedirTarjetaPais();
        jugadorPedro.pedirTarjetaPais();

        tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Gobi","Globo"));
        tarjetasPais.add(new TarjetaPais("Tartaria","Cañon"));
        tarjetasPais.add(new TarjetaPais("Peru","Barco"));
        juego.guardarMazoDeTarjetasPais(tarjetasPais);

        Jugador jugadorMartina = listaJugadores.get(1);

        jugadorMartina.pedirTarjetaPais();
        jugadorMartina.pedirTarjetaPais();
        jugadorMartina.pedirTarjetaPais();

        List<String> nombresTarjetasPaisParaCanjear1 = new ArrayList<>();
        nombresTarjetasPaisParaCanjear1.add("Uruguay");
        nombresTarjetasPaisParaCanjear1.add("Groenlandia");
        nombresTarjetasPaisParaCanjear1.add("Chile");

        List<String> nombresTarjetasPaisParaCanjear2 = new ArrayList<>();
        nombresTarjetasPaisParaCanjear2.add("Gobi");
        nombresTarjetasPaisParaCanjear2.add("Tartaria");
        nombresTarjetasPaisParaCanjear2.add("Peru");

        jugadorPedro.solicitarUnCanje(nombresTarjetasPaisParaCanjear1);
        jugadorMartina.solicitarUnCanje(nombresTarjetasPaisParaCanjear2);

        assertEquals(jugadorPedro.ejercitosDeCanje(),4);
        assertEquals(jugadorMartina.ejercitosDeCanje(),4);
    }

    @Test
    public void test006JugadorSinTarjetaPaisNoPuedeRealizarCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),0);
    }

    @Test
    public void test007JugadorCon1TarjetaPaisNoPuedeRealizarCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);
        jugador.pedirTarjetaPais();

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),0);
    }

    @Test
    public void test008JugadorCon2TarjetasPaisNoPuedeRealizarCanje() throws IOException {
        setup();
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);
        jugador.pedirTarjetaPais();
        jugador.pedirTarjetaPais();

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Brasil");

        assertEquals(jugador.ejercitosDeCanje(),0);
        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),0);

    }

    @Test
    public void test009JugadorCon3TarjetasPaisDosSimbolosIgualesYUnoDistintoNoPuedeRealizarCanje() throws IOException {
        List<String> nombresJugadores = new ArrayList<>();
        Moderador moderador = new Moderador();
        listaContinentes = moderador.pedirContinentes();
        tarjetasObjetivos = moderador.pedirTarjetasObjetivo();

        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");
        juego = new Juego (listaPaises,listaContinentes,nombresJugadores);
        juego.guardarMazoDeTarjetasObjetivo(tarjetasObjetivos);
        List<Jugador> listaJugadores = juego.devolverJugadores();

        List<TarjetaPais> tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Uruguay","Globo"));
        tarjetasPais.add(new TarjetaPais("Groenlandia","Globo"));
        tarjetasPais.add(new TarjetaPais("Tartaria","Cañon"));
        juego.guardarMazoDeTarjetasPais(tarjetasPais);

        Jugador jugador = listaJugadores.get(0);

        jugador.pedirTarjetaPais();
        jugador.pedirTarjetaPais();
        jugador.pedirTarjetaPais();

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Uruguay");
        nombresTarjetasPaisParaCanjear.add("Groenlandia");
        nombresTarjetasPaisParaCanjear.add("Tartaria");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),0);

    }

    @Test
    public void test010JugadorNoPuedeRealizarCanjeSiAlgunaTarjetaPaisNoLePertenece() throws IOException {
        List<String> nombresJugadores = new ArrayList<>();
        Moderador moderador = new Moderador();
        listaContinentes = moderador.pedirContinentes();
        tarjetasObjetivos = moderador.pedirTarjetasObjetivo();

        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");
        juego = new Juego (listaPaises,listaContinentes,nombresJugadores);
        juego.guardarMazoDeTarjetasObjetivo(tarjetasObjetivos);
        List<Jugador> listaJugadores = juego.devolverJugadores();

        List<TarjetaPais> tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Uruguay","Globo"));
        tarjetasPais.add(new TarjetaPais("Groenlandia","Globo"));
        tarjetasPais.add(new TarjetaPais("Francia","Globo"));
        juego.guardarMazoDeTarjetasPais(tarjetasPais);

        Jugador jugador = listaJugadores.get(0);

        jugador.pedirTarjetaPais();
        jugador.pedirTarjetaPais();
        jugador.pedirTarjetaPais();

        List<String> nombresTarjetasPaisParaCanjear = new ArrayList<>();
        nombresTarjetasPaisParaCanjear.add("Argentina");
        nombresTarjetasPaisParaCanjear.add("Groenlandia");
        nombresTarjetasPaisParaCanjear.add("Francia");

        jugador.solicitarUnCanje(nombresTarjetasPaisParaCanjear);
        assertEquals(jugador.ejercitosDeCanje(),0);

    }







}





