package edu.fiuba.algo3.tests;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Dados;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosTest {
    @Test
    public void test01DevuelveListaDeDadosVacia() {
        Dados dados = new Dados();
        assertEquals(dados.dadosAtaque(0).size(), 0);
        assertEquals(dados.dadosDefensa(0).size(), 0);
    }

    @Test
    public void test02DevuelveListaDeDadosDeAtaqueNoVacia() {
        Dados dados = new Dados();
        assertEquals(dados.dadosAtaque(1).size(), 0);
        assertEquals(dados.dadosAtaque(2).size(), 1);
        assertEquals(dados.dadosAtaque(3).size(), 2);
        assertEquals(dados.dadosAtaque(4).size(), 3);
        assertEquals(dados.dadosAtaque(5).size(), 3);
        assertEquals(dados.dadosAtaque(6).size(), 3);
        assertEquals(dados.dadosAtaque(0).size(), 0);
    }

    @Test
    public void test02DevuelveListaDeDadosDeDefensaNoVacia() {
        Dados dados = new Dados();
        assertEquals(dados.dadosDefensa(1).size(), 2);
        assertEquals(dados.dadosDefensa(2).size(), 3);
        assertEquals(dados.dadosDefensa(3).size(), 3);
        assertEquals(dados.dadosDefensa(4).size(), 3);
        assertEquals(dados.dadosDefensa(5).size(), 3);
        assertEquals(dados.dadosDefensa(0).size(), 0);
    }

    @Test
    public void test03DevuelveResultadosEntreValoresEsperadosDeUnDado() {
        Dados dados = new Dados();
        assertTrue( ( 1 <= dados.dadosDefensa(3).get(0) ) && ( dados.dadosDefensa(3).get(0) <= 6 ) );
        assertTrue( ( 1 <= dados.dadosDefensa(3).get(1) ) && ( dados.dadosDefensa(3).get(1) <= 6 ) );
        assertTrue( ( 1 <= dados.dadosDefensa(3).get(2) ) && ( dados.dadosDefensa(3).get(2) <= 6 ) );
        assertTrue( ( 1 <= dados.dadosAtaque(4).get(0) ) && ( dados.dadosAtaque(4).get(0) <= 6 ) );
        assertTrue( ( 1 <= dados.dadosAtaque(4).get(1) ) && ( dados.dadosAtaque(4).get(1) <= 6 ) );
        assertTrue( ( 1 <= dados.dadosAtaque(4).get(2) ) && ( dados.dadosAtaque(4).get(2) <= 6 ) );
    }
}