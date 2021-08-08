package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
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

public class PruebasDeIntegracionTest {

    static final int EJERCITOS_ADICIONALES_ASIA = 7;
    static final int EJERCITOS_ADICIONALES_EUROPA = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_SUR = 3;
    static final int EJERCITOS_ADICIONALES_AFRICA = 3;
    static final int EJERCITOS_ADICIONALES_OCEANIA = 2;

    private Pais argentina;
    private Pais uruguay;
    private Pais brasil;
    private Pais chile;
    private Pais oregon;
    private Pais nuevaYork;
    private Pais terranova;
    private Pais alaska;
    private Pais australia;
    private Pais sumatra;
    private Pais borneo;
    private Pais francia;
    private Pais españa;
    private Pais polonia;
    private Pais rusia;
    private Pais turquia;
    private Pais india;
    private Pais iran;
    private Pais arabia;
    private Pais sahara;
    private Pais zaire;
    private Pais etiopia;
    private Pais egipto;


    private Continente americaDelSur;
    private Continente americaDelNorte;
    private Continente oceania;
    private Continente europa;
    private Continente asia;
    private Continente africa;

    private List<Pais> listaPaises;
    private List<Continente> listaContinentes;
    private List<TarjetaObjetivo> mazoDeTarjetasObjetivo;
    private List<TarjetaPais> mazoDeTarjetasPais;
    private Moderador moderador;
    private Aleatorio aleatorio;

    private Juego mockedJuego;
    private Tablero tablero;
    private Jugador jugadorPedro;
    private Jugador jugadorMartina;


    @BeforeEach
    public void setup() throws IOException {
        moderador = new Moderador();
        tablero = new Tablero(moderador.pedirPaises(), moderador.pedirContinentes());
        List<TarjetaObjetivo> tarjetasObjetivo = moderador.pedirTarjetasObjetivo();
        List<TarjetaPais> tarjetasPais = moderador.pedirTarjetasPais();
        mockedJuego = mock(Juego.class);

        jugadorPedro = new Jugador("Pedro","Azul",mockedJuego);
        jugadorMartina = new Jugador("Martina","Rojo",mockedJuego);

        jugadorPedro.agregarPais(tablero.buscarPais("Argentina"));
        jugadorPedro.agregarPais(tablero.buscarPais("Uruguay"));
        jugadorMartina.agregarPais(tablero.buscarPais("Brasil"));
        jugadorMartina.agregarPais(tablero.buscarPais("Chile"));

    }

    @BeforeEach
    public void setup2() throws IOException {

        // Continente America del Sur
        List<String> limitrofes = new ArrayList<>();
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        argentina = new Pais("Argentina", limitrofes);

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");
        uruguay = new Pais("Uruguay", limitrofes2);

        List<String> limitrofes3 = new ArrayList<>();
        limitrofes3.add("Argentina");
        limitrofes3.add("Uruguay");
        limitrofes3.add("Sahara");
        brasil = new Pais("Brasil", limitrofes3);

        List<String> limitrofes4 = new ArrayList<>();
        limitrofes4.add("Argentina");
        limitrofes4.add("Australia");
        chile = new Pais("Chile", limitrofes4);

        //Continente America del Norte
        List<String> limitrofes5 = new ArrayList<>();
        limitrofes5.add("Alaska");
        limitrofes5.add("Nueva York");
        oregon = new Pais("Oregon", limitrofes5);

        List<String> limitrofes6 = new ArrayList<>();
        limitrofes6.add("Oregon");
        limitrofes6.add("Terranova");
        nuevaYork = new Pais("Nueva York", limitrofes6);

        List<String> limitrofes7 = new ArrayList<>();
        limitrofes7.add("Nueva York");
        terranova = new Pais("Terranova", limitrofes7);

        List<String> limitrofes8 = new ArrayList<>();
        limitrofes8.add("Oregon");
        alaska = new Pais("Alaska", limitrofes8);

        //Continente Oceania
        List<String> limitrofes9 = new ArrayList<>();
        limitrofes9.add("Chile");
        limitrofes9.add("Sumatra");
        limitrofes9.add("Borneo");
        australia = new Pais("Australia", limitrofes9);

        List<String> limitrofes10 = new ArrayList<>();
        limitrofes10.add("Australia");
        limitrofes10.add("India");
        sumatra = new Pais("Sumatra", limitrofes10);

        List<String> limitrofes11 = new ArrayList<>();
        limitrofes11.add("Australia");
        borneo = new Pais("Borneo", limitrofes11);

        //Continente Europa
        List<String> limitrofes12 = new ArrayList<>();
        limitrofes12.add("España");
        francia = new Pais("Francia", limitrofes12);

        List<String> limitrofes13 = new ArrayList<>();
        limitrofes13.add("Sahara");
        limitrofes13.add("Francia");
        españa = new Pais("España", limitrofes13);

        List<String> limitrofes14 = new ArrayList<>();
        limitrofes14.add("Rusia");
        limitrofes14.add("Turquia");
        limitrofes14.add("Egipto");
        polonia = new Pais("Polonia", limitrofes14);

        List<String> limitrofes15 = new ArrayList<>();
        limitrofes15.add("Iran");
        limitrofes15.add("Turquia");
        limitrofes15.add("Polonia");
        rusia = new Pais("Rusia", limitrofes15);

        //Continente Asia
        List<String> limitrofes16 = new ArrayList<>();
        limitrofes16.add("Egipto");
        limitrofes16.add("Polonia");
        limitrofes16.add("Rusia");
        limitrofes16.add("Iran");
        limitrofes16.add("Arabia");
        turquia = new Pais("Turquia", limitrofes16);

        List<String> limitrofes17 = new ArrayList<>();
        limitrofes17.add("Iran");
        india = new Pais("India", limitrofes17);

        List<String> limitrofes18 = new ArrayList<>();
        limitrofes18.add("India");
        limitrofes18.add("Turquia");
        limitrofes18.add("Rusia");
        iran = new Pais("Iran", limitrofes18);

        List<String> limitrofes19 = new ArrayList<>();
        limitrofes19.add("Turquia");
        limitrofes19.add("Israel");
        arabia = new Pais("Arabia", limitrofes19);

        //Continente Africa
        List<String> limitrofes20 = new ArrayList<>();
        limitrofes20.add("Brasil");
        limitrofes20.add("España");
        limitrofes20.add("Egipto");
        limitrofes20.add("Etiopia");
        limitrofes20.add("Zaire");
        sahara = new Pais("Sahara", limitrofes20);

        List<String> limitrofes21 = new ArrayList<>();
        limitrofes21.add("Etiopia");
        limitrofes21.add("Sahara");
        zaire = new Pais("Zaire", limitrofes21);

        List<String> limitrofes22 = new ArrayList<>();
        limitrofes22.add("Sahra");
        limitrofes22.add("Egipto");
        limitrofes22.add("Zaire");
        etiopia = new Pais("Etiopia", limitrofes22);

        List<String> limitrofes23 = new ArrayList<>();
        limitrofes23.add("Polonia");
        limitrofes23.add("Turquia");
        limitrofes23.add("Etiopia");
        limitrofes23.add("Sahara");
        egipto = new Pais("Egipto", limitrofes23);

        listaPaises = new ArrayList<>();
        listaPaises.add(argentina);
        listaPaises.add(uruguay);
        listaPaises.add(brasil);
        listaPaises.add(chile);
        listaPaises.add(oregon);
        listaPaises.add(nuevaYork);
        listaPaises.add(terranova);
        listaPaises.add(alaska);
        listaPaises.add(australia);
        listaPaises.add(sumatra);
        listaPaises.add(borneo);
        listaPaises.add(francia);
        listaPaises.add(españa);
        listaPaises.add(polonia);
        listaPaises.add(rusia);
        listaPaises.add(turquia);
        listaPaises.add(india);
        listaPaises.add(iran);
        listaPaises.add(arabia);
        listaPaises.add(sahara);
        listaPaises.add(zaire);
        listaPaises.add(etiopia);
        listaPaises.add(egipto);

        List<Pais> listaPaises1 = new ArrayList<>();
        listaPaises1.add(argentina);
        listaPaises1.add(uruguay);
        listaPaises1.add(brasil);
        listaPaises1.add(chile);
        americaDelSur = new Continente("America del Sur",listaPaises1,EJERCITOS_ADICIONALES_AMERICA_DEL_SUR);

        List<Pais> listaPaises2 = new ArrayList<>();
        listaPaises2.add(oregon);
        listaPaises2.add(nuevaYork);
        listaPaises2.add(terranova);
        listaPaises2.add(alaska);
        americaDelNorte = new Continente("America del Norte",listaPaises2,EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE);

        List<Pais> listaPaises3 = new ArrayList<>();
        listaPaises3.add(australia);
        listaPaises3.add(sumatra);
        listaPaises3.add(borneo);
        oceania = new Continente("Oceania",listaPaises3,EJERCITOS_ADICIONALES_OCEANIA);

        List<Pais> listaPaises4 = new ArrayList<>();
        listaPaises4.add(francia);
        listaPaises4.add(españa);
        listaPaises4.add(polonia);
        listaPaises4.add(rusia);
        europa = new Continente("Europa",listaPaises4,EJERCITOS_ADICIONALES_EUROPA);

        List<Pais> listaPaises5 = new ArrayList<>();
        listaPaises5.add(turquia);
        listaPaises5.add(india);
        listaPaises5.add(iran);
        listaPaises5.add(arabia);
        asia = new Continente("Asia",listaPaises5,EJERCITOS_ADICIONALES_ASIA);

        List<Pais> listaPaises6 = new ArrayList<>();
        listaPaises6.add(sahara);
        listaPaises6.add(zaire);
        listaPaises6.add(etiopia);
        listaPaises6.add(egipto);
        africa = new Continente("Africa",listaPaises6,EJERCITOS_ADICIONALES_AFRICA);

        listaContinentes = new ArrayList<>();
        listaContinentes.add(americaDelSur);
        listaContinentes.add(americaDelNorte);
        listaContinentes.add(oceania);
        listaContinentes.add(europa);
        listaContinentes.add(asia);
        listaContinentes.add(africa);

        moderador = new Moderador();
        aleatorio = new Aleatorio();
        mazoDeTarjetasObjetivo = moderador.pedirTarjetasObjetivo();
        mazoDeTarjetasPais = moderador.pedirTarjetasPais();
    }

    @Test
    public void test001ColocacionDeEjercitosEnLosPaises() throws IOException {
        setup();

        boolean lanzaUnaExcepcion = false;

        try {
            jugadorPedro.colocarEjercito("Argentina", 1);
            jugadorMartina.colocarEjercito("Brasil", 1);
            jugadorPedro.colocarEjercito("Argentina", 3);
            jugadorMartina.colocarEjercito("Chile", 1);
            jugadorPedro.colocarEjercito("Uruguay", 1);
            jugadorMartina.colocarEjercito("Brasil", 3);
            jugadorPedro.colocarEjercito("Argentina", 1);
            jugadorMartina.colocarEjercito("Chile", 3);
            jugadorPedro.colocarEjercito("Uruguay", 4);
            jugadorMartina.colocarEjercito("Brasil", 2);

        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        }
        assertEquals(jugadorPedro.pedirPaises().size(),2);
        assertEquals(jugadorMartina.pedirPaises().size(),2);
        assertEquals(jugadorPedro.pedirPaises().get(0).cantidadDeFichas(),5);
        assertEquals(jugadorPedro.pedirPaises().get(1).cantidadDeFichas(),5);
        assertEquals(jugadorMartina.pedirPaises().get(0).cantidadDeFichas(),6);
        assertEquals(jugadorMartina.pedirPaises().get(1).cantidadDeFichas(),4);

        assertFalse(lanzaUnaExcepcion);

    }

    @Test
    public void test002AtaqueEntrePaisesConElPaisDefensorComoGanador() throws IOException{
        setup();
        List<Integer> dadosAtaque = new ArrayList<>();
        dadosAtaque.add(1);
        dadosAtaque.add(3);

        List<Integer> dadosDefensa = new ArrayList<>();
        dadosDefensa.add(4);
        dadosDefensa.add(5);

        Dados mockedDadosAtaque = mock(Dados.class);
        when(mockedDadosAtaque.dadosAtaque(anyInt())).thenReturn(dadosAtaque);

        Dados mockedDadosDefensa = mock(Dados.class);
        when(mockedDadosDefensa.dadosDefensa(anyInt())).thenReturn(dadosDefensa);

        Ejercito ejercitoPaisAtacante = tablero.buscarPais("Argentina").getEjercito();
        Ejercito ejercitoPaisDefensor = tablero.buscarPais("Brasil").getEjercito();

        ejercitoPaisAtacante.setDados(mockedDadosAtaque);
        ejercitoPaisDefensor.setDados(mockedDadosDefensa);

        boolean lanzaUnaExcepcion = false;

        try {
            jugadorPedro.colocarEjercito("Argentina", 4);
            jugadorPedro.colocarEjercito("Uruguay", 1);
            jugadorMartina.colocarEjercito("Brasil", 3);
            jugadorMartina.colocarEjercito("Chile", 1);

            assertEquals(jugadorPedro.pedirPaises().size(),2);
            assertEquals(jugadorMartina.pedirPaises().size(),2);

            //Argentina (Pedro) ataca a Brasil (Martina).
            tablero.atacar("Argentina","Brasil",2);
            //Argentina (Pedro) pierde y Brasil (Martina) gana.
            assertEquals(jugadorPedro.pedirPaises().size(),2);
            assertEquals(jugadorMartina.pedirPaises().size(),2);
            assertEquals(jugadorPedro.pedirPaises().get(0).cantidadDeFichas(),2);
            assertEquals(jugadorMartina.pedirPaises().get(0).cantidadDeFichas(),3);


        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        }
        assertFalse(lanzaUnaExcepcion);

    }

    @Test
    public void test003AtaqueEntrePaisesConElPaisAtacanteComoGanador() throws IOException {
        setup();

        List<Integer> dadosAtaque = new ArrayList<>();
        dadosAtaque.add(5);
        dadosAtaque.add(5);

        List<Integer> dadosDefensa = new ArrayList<>();
        dadosDefensa.add(1);
        dadosDefensa.add(2);

        Dados mockedDadosAtaque = mock(Dados.class);
        when(mockedDadosAtaque.dadosAtaque(anyInt())).thenReturn(dadosAtaque);

        Dados mockedDadosDefensa = mock(Dados.class);
        when(mockedDadosDefensa.dadosDefensa(anyInt())).thenReturn(dadosDefensa);

        Ejercito ejercitoPaisAtacante = tablero.buscarPais("Argentina").getEjercito();
        Ejercito ejercitoPaisDefensor = tablero.buscarPais("Brasil").getEjercito();

        ejercitoPaisAtacante.setDados(mockedDadosAtaque);
        ejercitoPaisDefensor.setDados(mockedDadosDefensa);

        boolean lanzaUnaExcepcion = false;

        try {
            jugadorPedro.colocarEjercito("Argentina", 3);
            jugadorPedro.colocarEjercito("Uruguay", 1);
            jugadorMartina.colocarEjercito("Brasil", 2);
            jugadorMartina.colocarEjercito("Chile", 1);

            assertEquals(jugadorPedro.pedirPaises().size(),2);
            assertEquals(jugadorMartina.pedirPaises().size(),2);

            //Argentina (Pedro) ataca a Brasil (Martina).
            tablero.atacar("Argentina","Brasil",2);
            //Pedro gana y Martina pierde Brasil.
            assertEquals(jugadorPedro.pedirPaises().size(),3);
            assertEquals(jugadorMartina.pedirPaises().size(),1);
            assertEquals(jugadorPedro.pedirPaises().get(0).cantidadDeFichas(),2);
            assertEquals(jugadorPedro.pedirPaises().get(1).cantidadDeFichas(),1);
            assertEquals(jugadorPedro.pedirPaises().get(2).cantidadDeFichas(),1);
            assertEquals(jugadorMartina.pedirPaises().get(0).cantidadDeFichas(),1);

        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);

    }

    @Test
    public void test004JuegoDeUnaRondaConDosJugadoresCadaJugadorColocaEjercitosDeLaFaseInicial() throws IOException{
        setup2();

        List <String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");

        List<Pais> paisesDePedro = new ArrayList<>();
        List<Pais> paisesDeMartina = new ArrayList<>();

        paisesDePedro.add(argentina);
        paisesDeMartina.add(uruguay);
        paisesDePedro.add(brasil);
        paisesDeMartina.add(chile);
        paisesDePedro.add(oregon);
        paisesDeMartina.add(nuevaYork);
        paisesDePedro.add(terranova);
        paisesDeMartina.add(alaska);
        paisesDePedro.add(australia);
        paisesDeMartina.add(sumatra);
        paisesDePedro.add(borneo);
        paisesDeMartina.add(francia);
        paisesDePedro.add(españa);
        paisesDeMartina.add(polonia);
        paisesDePedro.add(rusia);
        paisesDeMartina.add(turquia);
        paisesDePedro.add(india);
        paisesDeMartina.add(iran);
        paisesDePedro.add(arabia);
        paisesDeMartina.add(sahara);
        paisesDePedro.add(zaire);
        paisesDeMartina.add(etiopia);
        paisesDePedro.add(egipto);


        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(paisesDePedro);
        listaPaisesRepartidos.add(paisesDeMartina);

        Aleatorio mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo));


        boolean lanzaExcepcionJugadaInvalida = false;
        boolean lanzaExcepcionCantidadInvalidaDeEjercitos = false;

        try {
            // fase inicial
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
            juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
            juego.comenzarFaseInicial(mockedAleatorio);

            // cada jugador coloca 5 ejercitos en los paises que quiere.

            List<String> listaPaisesParaColocarEjercitosElegidosPorPedro = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Argentina");
            listaEjercitosElegidosPorPedro.add(2);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Australia");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Borneo");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("España");
            listaEjercitosElegidosPorPedro.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro,listaEjercitosElegidosPorPedro);

            List<String> listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Chile");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Nueva York");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Turquia");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Iran");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Etiopia");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina,listaEjercitosElegidosPorMartina);

            //cada jugador coloca 3 ejercitos en los paises que quiere.

            listaPaisesParaColocarEjercitosElegidosPorPedro = new ArrayList<>();
            listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Terranova");
            listaEjercitosElegidosPorPedro.add(2);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Australia");
            listaEjercitosElegidosPorPedro.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro,listaEjercitosElegidosPorPedro);

            listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Iran");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Sahara");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Etiopia");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina,listaEjercitosElegidosPorMartina);

            assertEquals(argentina.cantidadDeFichas(),3);
            assertEquals(brasil.cantidadDeFichas(),1);
            assertEquals(oregon.cantidadDeFichas(),1);
            assertEquals(terranova.cantidadDeFichas(),3);
            assertEquals(australia.cantidadDeFichas(),3);
            assertEquals(borneo.cantidadDeFichas(),2);
            assertEquals(españa.cantidadDeFichas(),2);
            assertEquals(rusia.cantidadDeFichas(),1);
            assertEquals(india.cantidadDeFichas(),1);
            assertEquals(arabia.cantidadDeFichas(),1);
            assertEquals(zaire.cantidadDeFichas(),1);
            assertEquals(egipto.cantidadDeFichas(),1);

            assertEquals(uruguay.cantidadDeFichas(),1);
            assertEquals(chile.cantidadDeFichas(),2);
            assertEquals(nuevaYork.cantidadDeFichas(),2);
            assertEquals(alaska.cantidadDeFichas(),1);
            assertEquals(sumatra.cantidadDeFichas(),1);
            assertEquals(francia.cantidadDeFichas(),1);
            assertEquals(polonia.cantidadDeFichas(),1);
            assertEquals(turquia.cantidadDeFichas(),2);
            assertEquals(iran.cantidadDeFichas(),3);
            assertEquals(sahara.cantidadDeFichas(),2);
            assertEquals(etiopia.cantidadDeFichas(),3);


        } catch (JugadaInvalidaException e1) {
            lanzaExcepcionJugadaInvalida = true;
        } catch (CantidadInvalidaDeEjercitosException e2){
            lanzaExcepcionCantidadInvalidaDeEjercitos = true;
        }
        assertFalse(lanzaExcepcionJugadaInvalida);
        assertFalse(lanzaExcepcionCantidadInvalidaDeEjercitos);
    }

    @Test
    public void test005JuegoDeUnaRondaConTresJugadoresCadaJugadorColocaEjercitosDeLaFaseInicialJugadorControlaAsia() throws IOException{
        setup2();

        Aleatorio randomPaises = new Aleatorio();

        List <String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");
        nombresJugadores.add("Felipe");

        List<Pais> paisesDePedro = new ArrayList<>();
        List<Pais> paisesDeMartina = new ArrayList<>();
        List<Pais> paisesDeFelipe = new ArrayList<>();

        paisesDePedro.add(argentina);
        paisesDePedro.add(uruguay);
        paisesDeFelipe.add(brasil);
        paisesDeFelipe.add(chile);
        paisesDePedro.add(oregon);
        paisesDeFelipe.add(nuevaYork);
        paisesDePedro.add(terranova);
        paisesDeMartina.add(alaska);
        paisesDeFelipe.add(australia);
        paisesDePedro.add(sumatra);
        paisesDeFelipe.add(borneo);
        paisesDeFelipe.add(francia);
        paisesDePedro.add(españa);
        paisesDeMartina.add(polonia);
        paisesDeFelipe.add(rusia);
        paisesDeMartina.add(turquia);
        paisesDeMartina.add(india);
        paisesDeMartina.add(iran);
        paisesDeMartina.add(arabia);
        paisesDeMartina.add(sahara);
        paisesDeFelipe.add(zaire);
        paisesDePedro.add(etiopia);
        paisesDeMartina.add(egipto);

        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(paisesDePedro);
        listaPaisesRepartidos.add(paisesDeMartina);
        listaPaisesRepartidos.add(paisesDeFelipe);

        Aleatorio mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo));


        boolean lanzaExcepcionJugadaInvalida = false;
        boolean lanzaExcepcionCantidadInvalidaDeEjercitos = false;

        try {
            // fase inicial
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
            juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
            juego.comenzarFaseInicial(mockedAleatorio);

            // cada jugador coloca 5 ejercitos en los paises que quiere.

            List<String> listaPaisesParaColocarEjercitosElegidosPorPedro  = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro .add("Argentina");
            listaEjercitosElegidosPorPedro.add(2);
            listaPaisesParaColocarEjercitosElegidosPorPedro .add("Uruguay");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro .add("Terranova");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro .add("Sumatra");
            listaEjercitosElegidosPorPedro.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro ,listaEjercitosElegidosPorPedro);

            List<String> listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Polonia");
            listaEjercitosElegidosPorMartina.add(2);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Iran");
            listaEjercitosElegidosPorMartina.add(2);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("India");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina,listaEjercitosElegidosPorMartina);

            List<String> listaPaisesParaColocarEjercitosElegidosPorFelipe = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorFelipe = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Brasil");
            listaEjercitosElegidosPorFelipe.add(1);
            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Borneo");
            listaEjercitosElegidosPorFelipe.add(1);
            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Nueva York");
            listaEjercitosElegidosPorFelipe.add(1);
            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Rusia");
            listaEjercitosElegidosPorFelipe.add(1);
            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Zaire");
            listaEjercitosElegidosPorFelipe.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorFelipe,listaEjercitosElegidosPorFelipe);

            // cada jugador coloca 3 ejercitos en los paises que quiere.

            listaPaisesParaColocarEjercitosElegidosPorPedro  = new ArrayList<>();
            listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro .add("Uruguay");
            listaEjercitosElegidosPorPedro.add(3);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro,listaEjercitosElegidosPorPedro);

            listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Alaska");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Turquia");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Arabia");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina,listaEjercitosElegidosPorMartina);

            listaPaisesParaColocarEjercitosElegidosPorFelipe = new ArrayList<>();
            listaEjercitosElegidosPorFelipe = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Chile");
            listaEjercitosElegidosPorFelipe.add(2);
            listaPaisesParaColocarEjercitosElegidosPorFelipe.add("Australia");
            listaEjercitosElegidosPorFelipe.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorFelipe,listaEjercitosElegidosPorFelipe);

            assertTrue(juego.controlaContiente("Martina", "Asia"));

            juego.colocarEjercito("Oregon",3);
            juego.colocarEjercito("Iran",11);
            juego.colocarEjercito("Rusia",4);

            juego.colocarEjercito("Terranova",1);
            juego.colocarEjercito("España",2);

            juego.colocarEjercito("Arabia",3);
            juego.colocarEjercito("Sahara",3);
            juego.colocarEjercito("Egipto",3);
            juego.pasarTurno();

            juego.colocarEjercito("Nueva York",2);
            juego.colocarEjercito("Australia",2);

            juego.pasarTurno();
            juego.reagrupar("Iran","India",6);
            juego.pasarTurno();

            assertEquals(argentina.cantidadDeFichas(),3);
            assertEquals(uruguay.cantidadDeFichas(),5);
            assertEquals(oregon.cantidadDeFichas(),4);
            assertEquals(terranova.cantidadDeFichas(),3);
            assertEquals(sumatra.cantidadDeFichas(),2);
            assertEquals(españa.cantidadDeFichas(),3);
            assertEquals(etiopia.cantidadDeFichas(),1);

            assertEquals(alaska.cantidadDeFichas(),2);
            assertEquals(polonia.cantidadDeFichas(),3);
            assertEquals(turquia.cantidadDeFichas(),2);
            assertEquals(india.cantidadDeFichas(),8);
            assertEquals(iran.cantidadDeFichas(),8);
            assertEquals(arabia.cantidadDeFichas(),5);
            assertEquals(sahara.cantidadDeFichas(),4);
            assertEquals(egipto.cantidadDeFichas(),4);

            assertEquals(brasil.cantidadDeFichas(),2);
            assertEquals(chile.cantidadDeFichas(),3);
            assertEquals(nuevaYork.cantidadDeFichas(),4);
            assertEquals(australia.cantidadDeFichas(),4);
            assertEquals(borneo.cantidadDeFichas(),2);
            assertEquals(francia.cantidadDeFichas(),1);
            assertEquals(rusia.cantidadDeFichas(),6);
            assertEquals(zaire.cantidadDeFichas(),2);


        } catch (JugadaInvalidaException e1) {
            lanzaExcepcionJugadaInvalida = true;
        } catch (CantidadInvalidaDeEjercitosException e2){
            lanzaExcepcionCantidadInvalidaDeEjercitos = true;
        }
        assertFalse(lanzaExcepcionJugadaInvalida);
        assertFalse(lanzaExcepcionCantidadInvalidaDeEjercitos);


    }

    @Test
    public void test006JuegoDeUnaRondaConDosJugadoresElJugador1AtacaYConquistaDosPaisesDelJugador2() throws IOException {
        setup2();

        List<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");

        List<Pais> paisesDePedro = new ArrayList<>();
        List<Pais> paisesDeMartina = new ArrayList<>();

        paisesDePedro.add(argentina);
        paisesDeMartina.add(uruguay);
        paisesDePedro.add(brasil);
        paisesDeMartina.add(chile);
        paisesDePedro.add(oregon);
        paisesDeMartina.add(nuevaYork);
        paisesDePedro.add(terranova);
        paisesDeMartina.add(alaska);
        paisesDePedro.add(australia);
        paisesDeMartina.add(sumatra);
        paisesDePedro.add(borneo);
        paisesDeMartina.add(francia);
        paisesDePedro.add(españa);
        paisesDeMartina.add(polonia);
        paisesDePedro.add(rusia);
        paisesDeMartina.add(turquia);
        paisesDePedro.add(india);
        paisesDeMartina.add(iran);
        paisesDePedro.add(arabia);
        paisesDeMartina.add(sahara);
        paisesDePedro.add(zaire);
        paisesDeMartina.add(etiopia);
        paisesDePedro.add(egipto);


        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(paisesDePedro);
        listaPaisesRepartidos.add(paisesDeMartina);

        Aleatorio mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo));


        boolean lanzaExcepcionJugadaInvalida = false;
        boolean lanzaExcepcionCantidadInvalidaDeEjercitos = false;

        try {
            // fase inicial
            Juego juego = new Juego(listaPaises, listaContinentes, nombresJugadores);
            juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
            juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
            juego.comenzarFaseInicial(mockedAleatorio);

            // cada jugador coloca 5 ejercitos en los paises que quiere.

            List<String> listaPaisesParaColocarEjercitosElegidosPorPedro = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Argentina");
            listaEjercitosElegidosPorPedro.add(2);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Australia");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Borneo");
            listaEjercitosElegidosPorPedro.add(1);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("España");
            listaEjercitosElegidosPorPedro.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro, listaEjercitosElegidosPorPedro);

            List<String> listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Chile");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Nueva York");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Turquia");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Iran");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Etiopia");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina, listaEjercitosElegidosPorMartina);

            //cada jugador coloca 3 ejercitos en los paises que quiere.

            listaPaisesParaColocarEjercitosElegidosPorPedro = new ArrayList<>();
            listaEjercitosElegidosPorPedro = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Terranova");
            listaEjercitosElegidosPorPedro.add(2);
            listaPaisesParaColocarEjercitosElegidosPorPedro.add("Australia");
            listaEjercitosElegidosPorPedro.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorPedro, listaEjercitosElegidosPorPedro);

            listaPaisesParaColocarEjercitosElegidosPorMartina = new ArrayList<>();
            listaEjercitosElegidosPorMartina = new ArrayList<>();

            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Iran");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Sahara");
            listaEjercitosElegidosPorMartina.add(1);
            listaPaisesParaColocarEjercitosElegidosPorMartina.add("Etiopia");
            listaEjercitosElegidosPorMartina.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesParaColocarEjercitosElegidosPorMartina, listaEjercitosElegidosPorMartina);

            // JUGADOR 1 ATACA Y CONQUISTA 1 PAIS DE JUGADOR 2

            List<Integer> dadosAtaque = new ArrayList<>();
            dadosAtaque.add(5);
            dadosAtaque.add(5);

            List<Integer> dadosDefensa = new ArrayList<>();
            dadosDefensa.add(1);
            dadosDefensa.add(2);

            Dados mockedDadosAtaque = mock(Dados.class);
            when(mockedDadosAtaque.dadosAtaque(anyInt())).thenReturn(dadosAtaque);

            Dados mockedDadosDefensa = mock(Dados.class);
            when(mockedDadosDefensa.dadosDefensa(anyInt())).thenReturn(dadosDefensa);

            Ejercito ejercitoPaisAtacante = argentina.getEjercito();
            Ejercito ejercitoPaisDefensor = chile.getEjercito();

            ejercitoPaisAtacante.setDados(mockedDadosAtaque);
            ejercitoPaisDefensor.setDados(mockedDadosDefensa);

            assertEquals(juego.devolverJugadores().get(0).pedirPaises().size(), 12);
            assertEquals(juego.devolverJugadores().get(1).pedirPaises().size(), 11);

            juego.atacar("Argentina", "Chile", 2);

            assertEquals(juego.devolverJugadores().get(0).pedirPaises().size(), 13);
            assertEquals(juego.devolverJugadores().get(1).pedirPaises().size(), 10);


            //JUGADOR 1 ATACA Y CONQUISTA UN PAIS DE JUGADOR 2

            ejercitoPaisAtacante = terranova.getEjercito();
            ejercitoPaisDefensor = nuevaYork.getEjercito();

            ejercitoPaisAtacante.setDados(mockedDadosAtaque);
            ejercitoPaisDefensor.setDados(mockedDadosDefensa);

            juego.atacar("Terranova", "Nueva York", 2);

            assertEquals(juego.devolverJugadores().get(0).pedirPaises().size(), 14);
            assertEquals(juego.devolverJugadores().get(1).pedirPaises().size(), 9);


        } catch (JugadaInvalidaException e1) {
            lanzaExcepcionJugadaInvalida = true;
        } catch (CantidadInvalidaDeEjercitosException e2) {
            lanzaExcepcionCantidadInvalidaDeEjercitos = true;
        }
        assertFalse(lanzaExcepcionJugadaInvalida);
        assertFalse(lanzaExcepcionCantidadInvalidaDeEjercitos);

    }

}
