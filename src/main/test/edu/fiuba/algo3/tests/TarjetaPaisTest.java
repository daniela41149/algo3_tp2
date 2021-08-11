package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TarjetaPaisTest {

    private Juego juego;
    private Aleatorio mockedAleatorio;
    private List<TarjetaPais> tarjetasPais;
    private List<TarjetaObjetivo> tarjetasObjetivo;
    private List<Continente> listaContinentes;



    @BeforeEach
    public void setup() throws IOException {
        List<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");

        Moderador moderador = new Moderador();
        listaContinentes = moderador.pedirContinentes();
        tarjetasObjetivo = moderador.pedirTarjetasObjetivo();

        Pais pais = new Pais("Argentina", new ArrayList<>());
        Pais pais2 = new Pais("Uruguay", new ArrayList<>());
        Pais pais3 = new Pais("Brasil", new ArrayList<>());
        Pais pais4 = new Pais("Chile", new ArrayList<>());

        List<Pais> listaPaises = new ArrayList<>();
        listaPaises.add(pais);
        listaPaises.add(pais2);
        listaPaises.add(pais3);
        listaPaises.add(pais4);

        juego = new Juego (listaPaises,listaContinentes,nombresJugadores);

        tarjetasPais = new ArrayList<>();
        tarjetasPais.add(new TarjetaPais("Argentina","Comodin"));
        tarjetasPais.add(new TarjetaPais("Uruguay","Globo"));
        tarjetasPais.add(new TarjetaPais("Brasil","Barco"));
        tarjetasPais.add(new TarjetaPais("Chile","Globo"));

        juego.guardarMazoDeTarjetasPais(tarjetasPais);
        juego.guardarMazoDeTarjetasObjetivo(tarjetasObjetivo);

        List<Pais> listaPaisesParaPedro = new ArrayList<>();
        listaPaisesParaPedro.add(pais);
        listaPaisesParaPedro.add(pais2);

        List<Pais> listaPaisesParaMartina = new ArrayList<>();
        listaPaisesParaMartina.add(pais3);
        listaPaisesParaMartina.add(pais4);

        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(listaPaisesParaPedro);
        listaPaisesRepartidos.add(listaPaisesParaMartina);


        Aleatorio aleatorio = new Aleatorio();
        mockedAleatorio = mock(Aleatorio.class);
        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(tarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(tarjetasObjetivo));
    }

    @Test
    public void test001JugadorActiva1TarjetaParaUnPaisQueLePertenece() throws IOException {
        setup();

        boolean lanzaExcepcion = false;
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        try {
            juego.comenzarFaseInicial(mockedAleatorio);

            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();

            jugador.activarTarjetaPais("Argentina");
        } catch (JugadaInvalidaException e) {
            lanzaExcepcion = true;
        }
        assertFalse(lanzaExcepcion);
        assertEquals(jugador.pedirPaises().get(0).cantidadDeFichas(),3);
    }

    @Test
    public void test002JugadorActiva2TarjetasParaDosPaisesQueLePertenecen() throws IOException {
        setup();

        boolean lanzaExcepcion = false;
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        try {
            juego.comenzarFaseInicial(mockedAleatorio);

            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();

            jugador.activarTarjetaPais("Argentina");
            jugador.activarTarjetaPais("Uruguay");
        } catch (JugadaInvalidaException e) {
            lanzaExcepcion = true;
        }
        assertFalse(lanzaExcepcion);
        assertEquals(jugador.pedirPaises().get(0).cantidadDeFichas(),3);
        assertEquals(jugador.pedirPaises().get(1).cantidadDeFichas(),3);
    }

    @Test
    public void test003JugadorNoPuedeActivarUnaTarjetaParaUnPaisQueNoLePertenece() throws IOException {
        setup();

        boolean lanzaExcepcion = false;
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        try {
            juego.comenzarFaseInicial(mockedAleatorio);

            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();

            jugador.activarTarjetaPais("Brasil");

        } catch (JugadaInvalidaException e) {
            lanzaExcepcion = true;
        }
        assertTrue(lanzaExcepcion);
        assertEquals(jugador.pedirPaises().get(0).cantidadDeFichas(),1);
        assertEquals(jugador.pedirPaises().get(1).cantidadDeFichas(),1);
        assertEquals(juego.devolverJugadores().get(1).pedirPaises().get(0).cantidadDeFichas(),1);
        assertEquals(juego.devolverJugadores().get(1).pedirPaises().get(1).cantidadDeFichas(),1);
    }

    @Test
    public void test004JugadorNoPuedeVolverAActivarUnaTarjetaQueYaEstaActivada() throws IOException {
        setup();

        boolean lanzaExcepcion = false;
        List<Jugador> listaJugadores = juego.devolverJugadores();
        Jugador jugador = listaJugadores.get(0);

        try {
            juego.comenzarFaseInicial(mockedAleatorio);

            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();
            jugador.pedirTarjetaPais();

            jugador.activarTarjetaPais("Argentina");
            jugador.activarTarjetaPais("Argentina");
        } catch (JugadaInvalidaException e) {
            lanzaExcepcion = true;
        }
        assertFalse(lanzaExcepcion);
        assertEquals(jugador.pedirPaises().get(0).cantidadDeFichas(),3);
    }

}
