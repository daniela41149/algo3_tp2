package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Continente;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ContinenteTest {
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_SUR = 3;
    private List<Pais> listaPaises;
    private Continente continente;
    private Pais pais1;
    private List<String> limitrofes1;
    private Pais pais2;
    private List<String> limitrofes2;
    private Pais pais3;
    private List<String> limitrofes3;
    private Pais pais4;
    private List<String> limitrofes4;
    private Pais pais5;
    private List<String> limitrofes5;
    private Pais pais6;
    private List<String> limitrofes6;




    @BeforeEach
    public void setup() {
        limitrofes1 = new ArrayList<>();
        limitrofes1.add("Argentina");
        limitrofes1.add("Peru");
        limitrofes1.add("Australia");
        pais1 = new Pais("Chile", limitrofes1);

        limitrofes2 = new ArrayList<>();
        limitrofes2.add("Chile");
        limitrofes2.add("Peru");
        limitrofes2.add("Brasil");
        limitrofes2.add("Uruguay");
        pais2 = new Pais("Argentina", limitrofes2);

        limitrofes3 = new ArrayList<>();
        limitrofes3.add("Argentina");
        limitrofes3.add("Brasil");
        pais3 = new Pais("Uruguay", limitrofes3);

        limitrofes4 = new ArrayList<>();
        limitrofes4.add("Colombia");
        limitrofes4.add("Peru");
        limitrofes4.add("Argentina");
        limitrofes4.add("Uruguay");
        limitrofes4.add("Sahara");
        pais4 = new Pais("Brasil", limitrofes4);

        limitrofes5 = new ArrayList<>();
        limitrofes5.add("Mexico");
        limitrofes5.add("Peru");
        limitrofes5.add("Brasil");
        pais5 = new Pais("Colombia", limitrofes5);

        limitrofes6 = new ArrayList<>();
        limitrofes6.add("Colombia");
        limitrofes6.add("Brasil");
        limitrofes6.add("Chile");
        limitrofes6.add("Argentina");

        pais6 = new Pais("Peru", limitrofes6);

        listaPaises = new ArrayList<>();

        listaPaises.add(pais1);
        listaPaises.add(pais2);
        listaPaises.add(pais3);
        listaPaises.add(pais4);
        listaPaises.add(pais5);
        listaPaises.add(pais6);

        continente = new Continente ("America del Sur",listaPaises,EJERCITOS_ADICIONALES_AMERICA_DEL_SUR);

    }

/*
    @Test
    public void test001JugadorControlaTodosLosPaisesDeUnContinente() {
        setup();
        Jugador mockedJugador = mock(Jugador.class);


        pais1.colocarEjercito(mockedJugador,1);
        pais2.colocarEjercito(mockedJugador,1);
        pais3.colocarEjercito(mockedJugador,1);
        pais4.colocarEjercito(mockedJugador,1);
        pais5.colocarEjercito(mockedJugador,1);
        pais6.colocarEjercito(mockedJugador,1);

        assertTrue(continente.jugadorControlaContinente(mockedJugador));

    }

    @Test
    public void test002JugadorNoControlaTodosLosPaisesDeUnContinente() {
        setup();
        Jugador mockedJugador = mock(Jugador.class);
        Jugador mockedJugador2 = mock(Jugador.class);

        pais1.colocarEjercito(mockedJugador,1);
        pais2.colocarEjercito(mockedJugador,1);
        pais3.colocarEjercito(mockedJugador2,3);
        pais4.colocarEjercito(mockedJugador,1);
        pais5.colocarEjercito(mockedJugador,1);
        pais6.colocarEjercito(mockedJugador,1);
        Mockito.doThrow(new JugadaInvalidaException()).when(mockedJugador2).esElMismo(mockedJugador);


        assertFalse(continente.jugadorControlaContinente(mockedJugador));


    }

*/

}
