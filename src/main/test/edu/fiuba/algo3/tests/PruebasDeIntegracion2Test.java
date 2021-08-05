package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PruebasDeIntegracion2Test {

    static final int EJERCITOS_ADICIONALES_ASIA = 7;
    static final int EJERCITOS_ADICIONALES_EUROPA = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_SUR = 3;
    static final int EJERCITOS_ADICIONALES_AFRICA = 3;
    static final int EJERCITOS_ADICIONALES_OCEANIA = 2;

    private Pais pais;
    private Pais pais2;
    private Pais pais3;
    private Pais pais4;
    private Pais pais5;
    private Pais pais6;
    private Pais pais7;
    private Pais pais8;
    private Pais pais9;
    private Pais pais10;
    private Pais pais11;
    private Pais pais12;
    private Pais pais13;
    private Pais pais14;
    private Pais pais15;
    private Pais pais16;
    private Pais pais17;
    private Pais pais18;
    private Pais pais19;
    private Pais pais20;
    private Pais pais21;
    private Pais pais22;
    private Pais pais23;


    private Continente continente1;
    private Continente continente2;
    private Continente continente3;
    private Continente continente4;
    private Continente continente5;
    private Continente continente6;

    private List<Pais> listaPaises;
    private List<Continente> listaContinentes;
    private List<TarjetaObjetivo> mazoDeTarjetasObjetivo;
    private Moderador moderador;
    private Aleatorio aleatorio;


    @BeforeEach
    public void setup() {

        // Continente America del Sur

        String nombrePais = "Argentina";

        List<String> limitrofes = new ArrayList<>();
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        pais = new Pais(nombrePais, limitrofes);


        String nombrePais2 = "Uruguay";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");
        pais2 = new Pais(nombrePais2, limitrofes2);


        String nombrePais3 = "Brasil";

        List<String> limitrofes3 = new ArrayList<>();
        limitrofes3.add("Argentina");
        limitrofes3.add("Uruguay");
        limitrofes3.add("Sahara");
        pais3 = new Pais(nombrePais3, limitrofes3);


        String nombrePais4 = "Chile";

        List<String> limitrofes4 = new ArrayList<>();
        limitrofes4.add("Argentina");
        limitrofes4.add("Australia");
        pais4 = new Pais(nombrePais4, limitrofes4);

        //Continente America del Norte

        String nombrePais5 = "Oregon";

        List<String> limitrofes5 = new ArrayList<>();
        limitrofes5.add("Alaska");
        limitrofes5.add("Nueva York");
        pais5 = new Pais(nombrePais5, limitrofes5);

        String  nombrePais6 = "Nueva York";

        List<String> limitrofes6 = new ArrayList<>();
        limitrofes6.add("Oregon");
        limitrofes6.add("Terranova");
        pais6 = new Pais(nombrePais6, limitrofes6);

        String nombrePais7 = "Terranova";

        List<String> limitrofes7 = new ArrayList<>();
        limitrofes7.add("Nueva York");
        pais7 = new Pais(nombrePais7, limitrofes7);

        String nombrePais8 = "Alaska";

        List<String> limitrofes8 = new ArrayList<>();
        limitrofes8.add("Oregon");
        pais8 = new Pais(nombrePais8, limitrofes8);

        //Continente Oceania

        String nombrePais9 = "Australia";

        List<String> limitrofes9 = new ArrayList<>();
        limitrofes9.add("Chile");
        limitrofes9.add("Sumatra");
        limitrofes9.add("Borneo");
        pais9 = new Pais(nombrePais9, limitrofes9);


        String nombrePais10 = "Sumatra";

        List<String> limitrofes10 = new ArrayList<>();
        limitrofes10.add("Australia");
        limitrofes10.add("India");
        pais10 = new Pais(nombrePais10, limitrofes10);


        String nombrePais11 = "Borneo";

        List<String> limitrofes11 = new ArrayList<>();
        limitrofes11.add("Australia");
        pais11 = new Pais(nombrePais11, limitrofes11);


        //Continente Europa

        String nombrePais12 = "Francia";

        List<String> limitrofes12 = new ArrayList<>();
        limitrofes12.add("España");
        pais12 = new Pais(nombrePais12, limitrofes12);


        String nombrePais13 = "España";

        List<String> limitrofes13 = new ArrayList<>();
        limitrofes13.add("Sahara");
        limitrofes13.add("Francia");
        pais13 = new Pais(nombrePais13, limitrofes13);


        String nombrePais14 = "Polonia";

        List<String> limitrofes14 = new ArrayList<>();
        limitrofes14.add("Rusia");
        limitrofes14.add("Turquia");
        limitrofes14.add("Egipto");
        pais14 = new Pais(nombrePais14, limitrofes14);

        String nombrePais15 = "Rusia";

        List<String> limitrofes15 = new ArrayList<>();
        limitrofes15.add("Iran");
        limitrofes15.add("Turquia");
        limitrofes15.add("Polonia");
        pais15 = new Pais(nombrePais15, limitrofes15);

        //Continente Asia

        String nombrePais16 = "Turquia";

        List<String> limitrofes16 = new ArrayList<>();
        limitrofes16.add("Egipto");
        limitrofes16.add("Polonia");
        limitrofes16.add("Rusia");
        limitrofes16.add("Iran");
        limitrofes16.add("Arabia");
        pais16 = new Pais(nombrePais16, limitrofes16);


        String nombrePais17 = "India";

        List<String> limitrofes17 = new ArrayList<>();
        limitrofes17.add("Iran");
        pais17 = new Pais(nombrePais17, limitrofes17);


        String nombrePais18 = "Iran";

        List<String> limitrofes18 = new ArrayList<>();
        limitrofes18.add("India");
        limitrofes18.add("Turquia");
        limitrofes18.add("Rusia");
        pais18 = new Pais(nombrePais18, limitrofes18);

        String nombrePais19 = "Arabia";

        List<String> limitrofes19 = new ArrayList<>();
        limitrofes19.add("Turquia");
        limitrofes19.add("Israel");
        pais19 = new Pais(nombrePais19, limitrofes19);


        //Continente Africa

        String nombrePais20 = "Sahara";

        List<String> limitrofes20 = new ArrayList<>();
        limitrofes20.add("Brasil");
        limitrofes20.add("España");
        limitrofes20.add("Egipto");
        limitrofes20.add("Etiopia");
        limitrofes20.add("Zaire");
        pais20 = new Pais(nombrePais20, limitrofes20);


        String nombrePais21 = "Zaire";

        List<String> limitrofes21 = new ArrayList<>();
        limitrofes21.add("Etiopia");
        limitrofes21.add("Sahara");
        pais21 = new Pais(nombrePais21, limitrofes21);

        String nombrePais22 = "Etiopia";

        List<String> limitrofes22 = new ArrayList<>();
        limitrofes22.add("Sahra");
        limitrofes22.add("Egipto");
        limitrofes22.add("Zaire");
        pais22 = new Pais(nombrePais22, limitrofes22);

        String nombrePais23 = "Egipto";

        List<String> limitrofes23 = new ArrayList<>();
        limitrofes23.add("Polonia");
        limitrofes23.add("Turquia");
        limitrofes23.add("Etiopia");
        limitrofes23.add("Sahara");
        pais23 = new Pais(nombrePais23, limitrofes23);

        listaPaises = new ArrayList<>();
        listaPaises.add(pais);
        listaPaises.add(pais2);
        listaPaises.add(pais3);
        listaPaises.add(pais4);
        listaPaises.add(pais5);
        listaPaises.add(pais6);
        listaPaises.add(pais7);
        listaPaises.add(pais8);
        listaPaises.add(pais9);
        listaPaises.add(pais10);
        listaPaises.add(pais11);
        listaPaises.add(pais12);
        listaPaises.add(pais13);
        listaPaises.add(pais14);
        listaPaises.add(pais15);
        listaPaises.add(pais16);
        listaPaises.add(pais17);
        listaPaises.add(pais18);
        listaPaises.add(pais19);
        listaPaises.add(pais20);
        listaPaises.add(pais21);
        listaPaises.add(pais22);
        listaPaises.add(pais23);

        List<Pais> listaPaises1 = new ArrayList<>();
        listaPaises1.add(pais);
        listaPaises1.add(pais2);
        listaPaises1.add(pais3);
        listaPaises1.add(pais4);
        continente1 = new Continente("America del Sur",listaPaises1,EJERCITOS_ADICIONALES_AMERICA_DEL_SUR);

        List<Pais> listaPaises2 = new ArrayList<>();
        listaPaises2.add(pais5);
        listaPaises2.add(pais6);
        listaPaises2.add(pais7);
        listaPaises2.add(pais8);
        continente2 = new Continente("America del Norte",listaPaises2,EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE);

        List<Pais> listaPaises3 = new ArrayList<>();
        listaPaises3.add(pais9);
        listaPaises3.add(pais10);
        listaPaises3.add(pais11);
        continente3 = new Continente("Oceania",listaPaises3,EJERCITOS_ADICIONALES_OCEANIA);

        List<Pais> listaPaises4 = new ArrayList<>();
        listaPaises4.add(pais12);
        listaPaises4.add(pais13);
        listaPaises4.add(pais14);
        listaPaises4.add(pais15);
        continente4 = new Continente("Europa",listaPaises4,EJERCITOS_ADICIONALES_EUROPA);

        List<Pais> listaPaises5 = new ArrayList<>();
        listaPaises5.add(pais16);
        listaPaises5.add(pais17);
        listaPaises5.add(pais18);
        listaPaises5.add(pais19);
        continente5 = new Continente("Asia",listaPaises5,EJERCITOS_ADICIONALES_ASIA);

        List<Pais> listaPaises6 = new ArrayList<>();
        listaPaises6.add(pais20);
        listaPaises6.add(pais21);
        listaPaises6.add(pais22);
        listaPaises6.add(pais23);
        continente6 = new Continente("Africa",listaPaises6,EJERCITOS_ADICIONALES_AFRICA);

        listaContinentes = new ArrayList<>();
        listaContinentes.add(continente1);
        listaContinentes.add(continente2);
        listaContinentes.add(continente3);
        listaContinentes.add(continente4);
        listaContinentes.add(continente5);
        listaContinentes.add(continente6);

        moderador = new Moderador();
        aleatorio = new Aleatorio();
        mazoDeTarjetasObjetivo = moderador.pedirTarjetasObjetivo();
    }

    @Test
    public void test004JuegoDeUnaRondaConDosJugadoresCadaJugadorColocaEjercitosDeLaFaseInicial() {
        setup();

        List <String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");

        List<Pais> paisesJugador1 = new ArrayList<>();
        List<Pais> paisesJugador2 = new ArrayList<>();

        paisesJugador1.add(pais);
        paisesJugador2.add(pais2);
        paisesJugador1.add(pais3);
        paisesJugador2.add(pais4);
        paisesJugador1.add(pais5);
        paisesJugador2.add(pais6);
        paisesJugador1.add(pais7);
        paisesJugador2.add(pais8);
        paisesJugador1.add(pais9);
        paisesJugador2.add(pais10);
        paisesJugador1.add(pais11);
        paisesJugador2.add(pais12);
        paisesJugador1.add(pais13);
        paisesJugador2.add(pais14);
        paisesJugador1.add(pais15);
        paisesJugador2.add(pais16);
        paisesJugador1.add(pais17);
        paisesJugador2.add(pais18);
        paisesJugador1.add(pais19);
        paisesJugador2.add(pais20);
        paisesJugador1.add(pais21);
        paisesJugador2.add(pais22);
        paisesJugador1.add(pais23);


        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(paisesJugador1);
        listaPaisesRepartidos.add(paisesJugador2);

        Aleatorio mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo));


        boolean lanzaUnaExcepcion = false;

        try {
            // fase inicial
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
            juego.comenzarFaseInicial(mockedAleatorio);

            // cada jugador coloca 5 ejercitos en los paises que quiere.

            List<String> listaPaisesElegidosPorJugador1 = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorJugador1 = new ArrayList<>();

            listaPaisesElegidosPorJugador1.add("Argentina");
            listaEjercitosElegidosPorJugador1.add(2);
            listaPaisesElegidosPorJugador1.add("Australia");
            listaEjercitosElegidosPorJugador1.add(1);
            listaPaisesElegidosPorJugador1.add("Borneo");
            listaEjercitosElegidosPorJugador1.add(1);
            listaPaisesElegidosPorJugador1.add("España");
            listaEjercitosElegidosPorJugador1.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesElegidosPorJugador1,listaEjercitosElegidosPorJugador1);

            List<String> listaPaisesElegidosPorJugador2 = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorJugador2 = new ArrayList<>();

            listaPaisesElegidosPorJugador2.add("Chile");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Nueva York");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Turquia");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Iran");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Etiopia");
            listaEjercitosElegidosPorJugador2.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesElegidosPorJugador2,listaEjercitosElegidosPorJugador2);

            //cada jugador coloca 3 ejercitos en los paises que quiere.

            listaPaisesElegidosPorJugador1 = new ArrayList<>();
            listaEjercitosElegidosPorJugador1 = new ArrayList<>();

            listaPaisesElegidosPorJugador1.add("Terranova");
            listaEjercitosElegidosPorJugador1.add(2);
            listaPaisesElegidosPorJugador1.add("Australia");
            listaEjercitosElegidosPorJugador1.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesElegidosPorJugador1,listaEjercitosElegidosPorJugador1);

            listaPaisesElegidosPorJugador2 = new ArrayList<>();
            listaEjercitosElegidosPorJugador2 = new ArrayList<>();

            listaPaisesElegidosPorJugador2.add("Iran");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Sahara");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Etiopia");
            listaEjercitosElegidosPorJugador2.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesElegidosPorJugador2,listaEjercitosElegidosPorJugador2);

            assertEquals(paisesJugador1.get(0).cantidadDeFichas(),3);
            assertEquals(paisesJugador1.get(1).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(2).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(3).cantidadDeFichas(),3);
            assertEquals(paisesJugador1.get(4).cantidadDeFichas(),3);
            assertEquals(paisesJugador1.get(5).cantidadDeFichas(),2);
            assertEquals(paisesJugador1.get(6).cantidadDeFichas(),2);
            assertEquals(paisesJugador1.get(7).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(8).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(9).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(10).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(11).cantidadDeFichas(),1);

            assertEquals(paisesJugador2.get(0).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(1).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(2).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(3).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(4).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(5).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(6).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(7).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(8).cantidadDeFichas(),3);
            assertEquals(paisesJugador2.get(9).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(10).cantidadDeFichas(),3);


        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeJugadoresException e2){
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeEjercitosException e3){
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);


    }

    @Test
    public void test005JuegoDeUnaRondaConTresJugadoresCadaJugadorColocaEjercitosDeLaFaseInicialJugadorControlaAsia() {
        setup();

        Aleatorio randomPaises = new Aleatorio();

        List <String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");
        nombresJugadores.add("Felipe");

        List<Pais> paisesJugador1 = new ArrayList<>();
        List<Pais> paisesJugador2 = new ArrayList<>();
        List<Pais> paisesJugador3 = new ArrayList<>();

        paisesJugador1.add(pais);
        paisesJugador1.add(pais2);
        paisesJugador3.add(pais3);
        paisesJugador3.add(pais4);
        paisesJugador1.add(pais5);
        paisesJugador3.add(pais6);
        paisesJugador1.add(pais7);
        paisesJugador2.add(pais8);
        paisesJugador3.add(pais9);
        paisesJugador1.add(pais10);
        paisesJugador3.add(pais11);
        paisesJugador3.add(pais12);
        paisesJugador1.add(pais13);
        paisesJugador2.add(pais14);
        paisesJugador3.add(pais15);
        paisesJugador2.add(pais16);
        paisesJugador2.add(pais17);
        paisesJugador2.add(pais18);
        paisesJugador2.add(pais19);
        paisesJugador2.add(pais20);
        paisesJugador3.add(pais21);
        paisesJugador1.add(pais22);
        paisesJugador2.add(pais23);

        List<List<Pais>> listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(paisesJugador1);
        listaPaisesRepartidos.add(paisesJugador2);
        listaPaisesRepartidos.add(paisesJugador3);

        Aleatorio mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);
        when(mockedAleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo)).thenReturn(aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoDeTarjetasObjetivo));


        boolean lanzaUnaExcepcion = false;

        try {
            // fase inicial
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
            juego.comenzarFaseInicial(mockedAleatorio);


            // cada jugador coloca 5 ejercitos en los paises que quiere.

            List<String> listaPaisesElegidosPorJugador1 = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorJugador1 = new ArrayList<>();

            listaPaisesElegidosPorJugador1.add("Argentina");
            listaEjercitosElegidosPorJugador1.add(2);
            listaPaisesElegidosPorJugador1.add("Uruguay");
            listaEjercitosElegidosPorJugador1.add(1);
            listaPaisesElegidosPorJugador1.add("Terranova");
            listaEjercitosElegidosPorJugador1.add(1);
            listaPaisesElegidosPorJugador1.add("Sumatra");
            listaEjercitosElegidosPorJugador1.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesElegidosPorJugador1,listaEjercitosElegidosPorJugador1);

            List<String> listaPaisesElegidosPorJugador2 = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorJugador2 = new ArrayList<>();

            listaPaisesElegidosPorJugador2.add("Polonia");
            listaEjercitosElegidosPorJugador2.add(2);
            listaPaisesElegidosPorJugador2.add("Iran");
            listaEjercitosElegidosPorJugador2.add(2);
            listaPaisesElegidosPorJugador2.add("India");
            listaEjercitosElegidosPorJugador2.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesElegidosPorJugador2,listaEjercitosElegidosPorJugador2);

            List<String> listaPaisesElegidosPorJugador3 = new ArrayList<>();
            List<Integer> listaEjercitosElegidosPorJugador3 = new ArrayList<>();

            listaPaisesElegidosPorJugador3.add("Brasil");
            listaEjercitosElegidosPorJugador3.add(1);
            listaPaisesElegidosPorJugador3.add("Borneo");
            listaEjercitosElegidosPorJugador3.add(1);
            listaPaisesElegidosPorJugador3.add("Nueva York");
            listaEjercitosElegidosPorJugador3.add(1);
            listaPaisesElegidosPorJugador3.add("Rusia");
            listaEjercitosElegidosPorJugador3.add(1);
            listaPaisesElegidosPorJugador3.add("Zaire");
            listaEjercitosElegidosPorJugador3.add(1);

            juego.colocarEjercitoPrimeraVuelta(listaPaisesElegidosPorJugador3,listaEjercitosElegidosPorJugador3);

            // cada jugador coloca 3 ejercitos en los paises que quiere.

            listaPaisesElegidosPorJugador1 = new ArrayList<>();
            listaEjercitosElegidosPorJugador1 = new ArrayList<>();

            listaPaisesElegidosPorJugador1.add("Uruguay");
            listaEjercitosElegidosPorJugador1.add(3);

            juego.colocarEjercitoSegundaVuelta(listaPaisesElegidosPorJugador1,listaEjercitosElegidosPorJugador1);

            listaPaisesElegidosPorJugador2 = new ArrayList<>();
            listaEjercitosElegidosPorJugador2 = new ArrayList<>();

            listaPaisesElegidosPorJugador2.add("Alaska");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Turquia");
            listaEjercitosElegidosPorJugador2.add(1);
            listaPaisesElegidosPorJugador2.add("Arabia");
            listaEjercitosElegidosPorJugador2.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesElegidosPorJugador2,listaEjercitosElegidosPorJugador2);

            listaPaisesElegidosPorJugador3 = new ArrayList<>();
            listaEjercitosElegidosPorJugador3 = new ArrayList<>();

            listaPaisesElegidosPorJugador3.add("Chile");
            listaEjercitosElegidosPorJugador3.add(2);
            listaPaisesElegidosPorJugador3.add("Australia");
            listaEjercitosElegidosPorJugador3.add(1);

            juego.colocarEjercitoSegundaVuelta(listaPaisesElegidosPorJugador3,listaEjercitosElegidosPorJugador3);

            assertEquals(paisesJugador1.get(0).cantidadDeFichas(),3);
            assertEquals(paisesJugador1.get(1).cantidadDeFichas(),5);
            assertEquals(paisesJugador1.get(2).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(3).cantidadDeFichas(),2);
            assertEquals(paisesJugador1.get(4).cantidadDeFichas(),2);
            assertEquals(paisesJugador1.get(5).cantidadDeFichas(),1);
            assertEquals(paisesJugador1.get(6).cantidadDeFichas(),1);


            assertEquals(paisesJugador2.get(0).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(1).cantidadDeFichas(),3);
            assertEquals(paisesJugador2.get(2).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(3).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(4).cantidadDeFichas(),3);
            assertEquals(paisesJugador2.get(5).cantidadDeFichas(),2);
            assertEquals(paisesJugador2.get(6).cantidadDeFichas(),1);
            assertEquals(paisesJugador2.get(7).cantidadDeFichas(),1);

            assertEquals(paisesJugador3.get(0).cantidadDeFichas(),2);
            assertEquals(paisesJugador3.get(1).cantidadDeFichas(),3);
            assertEquals(paisesJugador3.get(2).cantidadDeFichas(),2);
            assertEquals(paisesJugador3.get(3).cantidadDeFichas(),2);
            assertEquals(paisesJugador3.get(4).cantidadDeFichas(),2);
            assertEquals(paisesJugador3.get(5).cantidadDeFichas(),1);
            assertEquals(paisesJugador3.get(6).cantidadDeFichas(),2);
            assertEquals(paisesJugador3.get(7).cantidadDeFichas(),2);

            assertTrue(juego.controlaContiente("Martina", "Asia"));


        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeJugadoresException e2){
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeEjercitosException e3){
            lanzaUnaExcepcion = true;
        }
        assertFalse(lanzaUnaExcepcion);


    }














}
