package edu.fiuba.algo3.tests;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Dados;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.Ejercito;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
