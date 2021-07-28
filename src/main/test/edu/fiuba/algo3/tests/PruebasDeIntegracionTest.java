package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeJugadoresException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PruebasDeIntegracionTest {

    private String nombrePais;
    private List<String> limitrofes;
    private Pais pais;
    private String nombrePais2;
    private List<String> limitrofes2;
    private Pais pais2;
    private String nombrePais3;
    private List<String> limitrofes3;
    private Pais pais3;
    private String nombrePais4;
    private List<String> limitrofes4;
    private Pais pais4;

    private Continente continente;
    private List<Pais> listaPaises;
    private List<Continente> listaContinentes;
    private List<String> nombresJugadores;

    private List<List<Pais>> listaPaisesRepartidos;
    private List<Pais> listaPaisesParaJugador1;
    private List<Pais> listaPaisesParaJugador2;
    private Aleatorio mockedAleatorio;



    @BeforeEach
    public void setup() {

        nombrePais = "Argentina";

        limitrofes = new ArrayList<>();
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        pais = new Pais(nombrePais, limitrofes);


        nombrePais2 = "Uruguay";

        limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");
        limitrofes2.add("Chile");
        pais2 = new Pais(nombrePais2, limitrofes2);

        nombrePais3 = "Brasil";


        limitrofes3 = new ArrayList<>();
        limitrofes3.add("Chile");
        limitrofes3.add("Argentina");
        limitrofes3.add("Uruguay");
        pais3 = new Pais(nombrePais3, limitrofes3);


        nombrePais4 = "Chile";

        limitrofes4 = new ArrayList<>();
        limitrofes4.add("Argentina");
        limitrofes4.add("Brasil");
        limitrofes4.add("Uruguay");
        pais4 = new Pais(nombrePais4, limitrofes4);


        listaPaises = new ArrayList<>();
        listaPaises.add(pais);
        listaPaises.add(pais2);
        listaPaises.add(pais3);
        listaPaises.add(pais4);

        continente = new Continente("America del Sur",listaPaises);

        listaContinentes = new ArrayList<>();
        listaContinentes.add(continente);

        nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Pedro");
        nombresJugadores.add("Martina");


        listaPaisesParaJugador1 = new ArrayList<>();

        listaPaisesParaJugador1.add(pais);
        listaPaisesParaJugador1.add(pais2);

        listaPaisesParaJugador2 = new ArrayList<>();
        listaPaisesParaJugador2.add(pais3);
        listaPaisesParaJugador2.add(pais4);

        listaPaisesRepartidos = new ArrayList<>();
        listaPaisesRepartidos.add(listaPaisesParaJugador1);
        listaPaisesRepartidos.add(listaPaisesParaJugador2);

        mockedAleatorio = mock(Aleatorio.class);

        when(mockedAleatorio.repartirPaisesAleatoriamente(anyInt(), any())).thenReturn(listaPaisesRepartidos);
        when(mockedAleatorio.elegirPosicionDelJugadorQueEmpieza(anyInt())).thenReturn(0);

    }




    @Test
    public void test001ColocacionDeEjercitosEnLosPaises() {
        setup();
        //Pedro: Argentina y Uruguay
        //Martina: Brasil y Chile

        boolean lanzaUnaExcepcion = false;

        try {
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.comenzarFaseInicial(mockedAleatorio);

            HashMap<String,List<Pais>>  diccionario = juego.mostrarPaisesDeCadaJugador(); //diccionario jugador pais

            juego.colocarEjercito("Argentina",3);
            juego.colocarEjercito("Brasil",2);
            juego.colocarEjercito("Uruguay",1);
            juego.colocarEjercito("Chile",3);


            assertEquals((diccionario.get("Pedro")).get(0).cantidadDeFichas(),4);
            assertEquals((diccionario.get("Pedro")).get(1).cantidadDeFichas(),2);
            assertEquals((diccionario.get("Martina")).get(0).cantidadDeFichas(),3);
            assertEquals((diccionario.get("Martina")).get(1).cantidadDeFichas(),4);


        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeJugadoresException e2){
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);

    }

    @Test
    public void test002AtaqueEntrePaisesConElPaisDefensorComoGanador() {
        setup();

        //Pedro: Argentina y Uruguay
        //Martina: Brasil y Chile

        boolean lanzaUnaExcepcion = false;

        try {
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.comenzarFaseInicial(mockedAleatorio);

            HashMap<String,List<Pais>>  diccionario = juego.mostrarPaisesDeCadaJugador(); //diccionario jugador pais
            assertEquals((diccionario.get("Pedro")).size(),2);
            assertEquals((diccionario.get("Martina")).size(),2);

            juego.colocarEjercito("Argentina",3);
            juego.colocarEjercito("Brasil",2);

            List<Integer> dadosAtaque = new ArrayList<>();
            dadosAtaque.add(1);
            dadosAtaque.add(3);

            List<Integer> dadosDefensa = new ArrayList<>();
            dadosDefensa .add(4);
            dadosDefensa .add(5);

            Dados mockedDadosAtaque = mock(Dados.class);
            when(mockedDadosAtaque.dadosAtaque(anyInt())).thenReturn(dadosAtaque);

            Dados mockedDadosDefensa = mock(Dados.class);
            when(mockedDadosDefensa.dadosDefensa(anyInt())).thenReturn(dadosDefensa);

            Ejercito ejercitoPaisAtacante = pais.getEjercito();
            Ejercito ejercitoPaisDefensor = pais3.getEjercito();

            ejercitoPaisAtacante.setDados(mockedDadosAtaque);
            ejercitoPaisDefensor.setDados(mockedDadosDefensa);

            //Argentina (Pedro) ataca a Brasil (Martina).

            juego.atacar("Argentina","Brasil",2);

            //Argentina (Pedro) pierde y Brasil (Martina) gana.

            diccionario = juego.mostrarPaisesDeCadaJugador();

            assertEquals((diccionario.get("Pedro")).size(),2);
            assertEquals((diccionario.get("Martina")).size(),2);


        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeJugadoresException e2){
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);

    }



    @Test
    public void test003AtaqueEntrePaisesConElPaisAtacanteComoGanador() {
        setup();

        //Pedro: Argentina y Uruguay
        //Martina: Brasil y Chile

        boolean lanzaUnaExcepcion = false;

        try {
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.comenzarFaseInicial(mockedAleatorio);
            HashMap<String,List<Pais>>  diccionario = juego.mostrarPaisesDeCadaJugador();

            assertEquals((diccionario.get("Pedro")).size(),2);
            assertEquals((diccionario.get("Martina")).size(),2);

            juego.colocarEjercito("Argentina",2);
            juego.colocarEjercito("Brasil",1);
            assertEquals(pais.cantidadDeFichas(),3);
            assertEquals(pais3.cantidadDeFichas(),2);

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

            Ejercito ejercitoPaisAtacante = pais.getEjercito();
            Ejercito ejercitoPaisDefensor = pais3.getEjercito();

            ejercitoPaisAtacante.setDados(mockedDadosAtaque);
            ejercitoPaisDefensor.setDados(mockedDadosDefensa);

            //Argentina (Pedro) ataca a Brasil (Martina).

            juego.atacar("Argentina","Brasil",2);

            //Pedro gana y Martina pierde Brasil.

            diccionario = juego.mostrarPaisesDeCadaJugador();

            assertEquals((diccionario.get("Pedro")).size(),3);
            assertEquals((diccionario.get("Martina")).size(),1);
            assertEquals(pais.cantidadDeFichas(),2);
            assertEquals(pais3.cantidadDeFichas(),1);

            } catch (JugadaInvalidaException e1) {
                lanzaUnaExcepcion = true;
            } catch (CantidadInvalidaDeJugadoresException e2){
                lanzaUnaExcepcion = true;
            }

            assertFalse(lanzaUnaExcepcion);

    }
/*
    @Test
    public void test004JuegoDeUnaRondaConDosJugadores() {
        Moderador moderador = new Moderador("resources/Fronteras.csv","resources/TarjetasPais.json");
        Aleatorio randomPaises = new Aleatorio();

        List<Pais> listaPaises = moderador.pedirPaises();
        List<Continente> listaContinentes = moderador.pedirContinentes();

        nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Felipe");
        nombresJugadores.add("Tomás");
        nombresJugadores.add("Pepe");

        boolean lanzaUnaExcepcion = false;

        try {
            Juego juego = new Juego(listaPaises,listaContinentes,nombresJugadores);
            juego.comenzarFaseInicial(randomPaises);

            HashMap<String,List<Pais>>  diccionario = juego.mostrarPaisesDeCadaJugador();
            diccionario.entrySet().forEach(entry -> {
                System.out.println(entry.getKey());
                List<Pais> paises = entry.getValue();
                for (Pais pais: paises) {
                    System.out.println(pais.getNombre());
                }
            });

        } catch (JugadaInvalidaException e1) {
            lanzaUnaExcepcion = true;
        } catch (CantidadInvalidaDeJugadoresException e2){
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);





    }

*/

}
