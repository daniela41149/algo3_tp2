package edu.fiuba.algo3.tests;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Dados;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.Ejercito;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaisTest {
    private String nombrePais;
    private List<String> limitrofes;
    private Pais pais;


    @BeforeEach
    public void setup() {
        nombrePais = "Argentina";

        limitrofes = new ArrayList<>();
        limitrofes.add("Chile");
        limitrofes.add("Peru");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        pais = new Pais(nombrePais, limitrofes);
    }


    @Test
    public void test001DosPaisesSonLimitrofesEntoncesDevuelveTrue() {
        setup();

        String nombrePais2 = "Uruguay";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");

        Pais pais2 = new Pais(nombrePais2, limitrofes2);

        assertTrue(pais.esLimitrofe(pais2));
        assertTrue(pais2.esLimitrofe(pais));

    }

    @Test
    public void test002DosPaisesNoSonLimitrofesEntoncesDevuelveFalse() {
        setup();
        String nombrePais3 = "Italia";

        List<String> limitrofes3 = new ArrayList<>();
        limitrofes3.add("Alemania");
        limitrofes3.add("Francia");

        Pais pais3 = new Pais(nombrePais3, limitrofes3);

        assertFalse(pais.esLimitrofe(pais3));
        assertFalse(pais3.esLimitrofe(pais));

    }

    @Test
    public void test003NoPuedeAtacarAUnPaisQueNoEsLimitrofeEntoncesSeLanzaJugadaInvalidaException() {
        String nombrePais4 = "Java";

        List<String> limitrofes4 = new ArrayList<>();
        limitrofes4.add("Australia");

        Pais pais4 = new Pais(nombrePais4, limitrofes4);

        assertThrows(JugadaInvalidaException.class, () -> pais.atacar(pais4, 1));
        assertThrows(JugadaInvalidaException.class, () -> pais4.atacar(pais, 1));
    }

    @Test
    public void test004PuedeAtacarAUnPaisQueSiEsLimitrofe() {
        setup();
        String nombrePais5 = "Peru";

        List<String> limitrofes5 = new ArrayList<>();
        limitrofes5.add("Colombia");
        limitrofes5.add("Brasil");
        limitrofes5.add("Chile");
        limitrofes5.add("Argentina");

        Pais pais5 = new Pais(nombrePais5, limitrofes5);

        List<Integer> dadosAtaque = new ArrayList<>();
        dadosAtaque.add(3);

        List<Integer> dadosAtaque5 = new ArrayList<>();
        dadosAtaque5.add(6);

        Dados mockedDadosAtaque = mock(Dados.class);
        when(mockedDadosAtaque.dadosAtaque(anyInt())).thenReturn(dadosAtaque);

        Dados mockedDadosAtaque5 = mock(Dados.class);
        when(mockedDadosAtaque5.dadosAtaque(anyInt())).thenReturn(dadosAtaque5);


        Ejercito ejercito = pais.getEjercito();
        Ejercito ejercito5 = pais5.getEjercito();

        ejercito.setDados(mockedDadosAtaque);
        ejercito5.setDados(mockedDadosAtaque5);

        boolean lanzaUnaExcepcion = false;

        try {
            assertEquals(pais.atacar(pais5,1),dadosAtaque);
            assertEquals(pais5.atacar(pais,1),dadosAtaque5);
        } catch (JugadaInvalidaException e) {
            lanzaUnaExcepcion = true;
        }

        assertFalse(lanzaUnaExcepcion);
        assertDoesNotThrow( () -> pais.atacar(pais5, 1));  //ataca con 1 Ficha
        assertDoesNotThrow( () -> pais5.atacar(pais, 1));  //ataca con 1 Ficha
    }


/*
    @Test
    public void test005PaisLePideAEjercitoQueDefiendaDeUnAtaque() {
        setup();

        List<Integer> dados = new ArrayList<>();
        dados.add(4);
        dados.add(5);

        Ejercito mockedEjercito = mock(Ejercito.class);
        when(mockedEjercito.defender()).thenReturn(dados);
        pais.setEjercito(mockedEjercito);

        assertEquals(pais.defender(),dados);
    }


    @Test
    public void test006JugadorQuiereColocarEjercitoEnUnPaisQueNoTieneDueño() {
        setup();
        Jugador mockedJugador = mock(Jugador.class);
        Ejercito mockedEjercito = mock(Ejercito.class);
        pais.setEjercito(mockedEjercito);

        boolean lanzaUnaExcepcion = false;

        try {
            pais.colocarEjercito(mockedJugador,2);
        } catch (Exception e){
            lanzaUnaExcepcion = true;
        }
        assertFalse(lanzaUnaExcepcion);
    }



    @Test
    public void test008JugadorQuiereColocarEjercitoEnUnPaisQueNoLePertenece() {
        setup();
        Jugador mockedJugador = mock(Jugador.class);
        Jugador mockedJugador2 = mock(Jugador.class);

        Ejercito mockedEjercito = mock(Ejercito.class);
        pais.setEjercito(mockedEjercito);

        boolean lanzaUnaExcepcion = false;

        try {
            Mockito.doThrow(new JugadaInvalidaException()).when(mockedJugador).esElMismo(mockedJugador2);
            pais.colocarEjercito(mockedJugador,2);
            pais.colocarEjercito(mockedJugador2,1);
        } catch (JugadaInvalidaException e){
            lanzaUnaExcepcion = true;
        }

        assertTrue(lanzaUnaExcepcion);

    }

    @Test
    public void test009PaisQuePerdioEjercitoEstableceSuDueño() {
        setup();

        String nombrePais2 = "Uruguay";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");

        Pais pais2 = new Pais(nombrePais2, limitrofes2);

        Jugador mockedJugador = mock(Jugador.class);
        Jugador mockedJugador2 = mock(Jugador.class);

        Ejercito mockedEjercito = mock(Ejercito.class);
        Ejercito mockedEjercito2 = mock(Ejercito.class);

        when(mockedEjercito.quedoSinEjercito()).thenReturn(true);
        pais.setEjercito(mockedEjercito);
        pais2.setEjercito(mockedEjercito);

        boolean lanzaUnaExcepcion = false;

        try {
            Mockito.doThrow(new JugadaInvalidaException()).when(mockedJugador).esElMismo(mockedJugador2);
            pais.colocarEjercito(mockedJugador,2);
            pais2.colocarEjercito(mockedJugador2,2);
            pais.establecerDueño(pais2);

        } catch (JugadaInvalidaException e){
            lanzaUnaExcepcion = true;
        }
        assertFalse(lanzaUnaExcepcion);

    }

*/

}
