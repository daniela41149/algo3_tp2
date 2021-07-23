package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Batalla;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BatallaTest {
    private Pais mockPaisAtacante;
    private Pais mockPaisDefensor;

    @BeforeEach
    public void setup() {
        mockPaisAtacante = mock(Pais.class);
        mockPaisDefensor = mock(Pais.class);
    }

    @Test
    public void test01NoSePuedeHacerBatallarAPaisesQueNoSonLimitrofes() throws JugadaInvalidaException {
        setup();
        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);

        when(mockPaisAtacante.atacar(mockPaisDefensor,2)).thenThrow(new JugadaInvalidaException());

        assertThrows(JugadaInvalidaException.class, () -> batalla.atacar(2));
    }
/*
    @Test
    public void test02LosDadosDeLosPaisesABatallarSeComparanDeFormaDescendente() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(3);
        dadosAtacante.add(1);
        dadosAtacante.add(6);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(4);
        dadosDefensor.add(5);
        dadosDefensor.add(2);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 4)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(4);
        assertEquals(dadosAtacante.get(0), 6);
        assertEquals(dadosAtacante.get(1), 3);
        assertEquals(dadosAtacante.get(2), 1);

        assertEquals(dadosDefensor.get(0), 5);
        assertEquals(dadosDefensor.get(1), 4);
        assertEquals(dadosDefensor.get(2), 2);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }
*/
    @Test
    public void test03SiElAtacanteGanaUnaComparacionDeDadosEntoncesElDefensorSacaUnEjercito() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(5);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(2);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 2)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(2);
        verify(mockPaisAtacante, never()).sacarFichas(1);
        verify(mockPaisDefensor).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }

    @Test
    public void test04SiElDefensorGanaUnaComparacionDeDadosEntoncesElAtacanteSacaUnEjercito() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(2);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(4);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 2)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(2);
        verify(mockPaisDefensor, never()).sacarFichas(1);
        verify(mockPaisAtacante).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }

    @Test
    public void test05SiUnaComparacionDeDadosResultaEnEmpateElAtacanteSacaUnEjercito() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(4);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(4);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 2)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(2);
        verify(mockPaisDefensor, never()).sacarFichas(1);
        verify(mockPaisAtacante).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }

    @Test
    public void test06LosDadosDeMasQuePuedaTenerElAtacantePorEncimaDelDefensorNoSeTienenEnCuentaEnLaBatalla() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(5);
        dadosAtacante.add(1);
        dadosAtacante.add(4);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(3);
        dadosDefensor.add(6);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 4)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(4);
        verify(mockPaisDefensor).sacarFichas(1);
        verify(mockPaisAtacante).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }

    @Test
    public void test07ElAtacanteGanaTodasLasComparacionesDeDados() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(3);
        dadosAtacante.add(5);
        dadosAtacante.add(4);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(2);
        dadosDefensor.add(1);
        dadosDefensor.add(3);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 4)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(4);
        verify(mockPaisAtacante, never()).sacarFichas(1);
        verify(mockPaisDefensor, times(3)).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }
/*
    @Test
    public void test08ElDefensorGanaTodasLasComparacionesDeDados() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(3);
        dadosAtacante.add(2);
        dadosAtacante.add(1);
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(2);
        dadosDefensor.add(3);
        dadosDefensor.add(4);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 4)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(4);
        verify(mockPaisDefensor, never()).sacarFicha(1);
        verify(mockPaisAtacante, times(3)).sacarFicha(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }
*/
    @Test
    public void test09UnPaisAtacanteConUnSoloEjercitoNoPuedeBatallar() throws JugadaInvalidaException {
        setup();
        List<Integer> dadosAtacante = new ArrayList<>();
        List<Integer> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(2);

        Batalla batalla = new Batalla(mockPaisAtacante, mockPaisDefensor);
        when(mockPaisAtacante.atacar(mockPaisDefensor, 1)).thenReturn(dadosAtacante);
        when(mockPaisDefensor.defender()).thenReturn(dadosDefensor);

        batalla.atacar(1);
        verify(mockPaisDefensor, never()).sacarFichas(1);
        verify(mockPaisAtacante, never()).sacarFichas(1);
        verify(mockPaisDefensor).establecerDueño(mockPaisAtacante);
    }
}
