package edu.fiuba.algo3.tests;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Dados;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadosTest {
    @Test
    public void test01DevuelveListaDeDadosVacia() {
        Dados dados = new Dados();
        assertEquals(dados.tirarDados(0).size(), 0);
    }

    @Test
    public void test02DevuelveListaDeDadosNoVacia() {
        Dados dados = new Dados();
        assertEquals(1,dados.tirarDados(1).size());
        assertEquals(2,dados.tirarDados(2).size());
        assertEquals(3,dados.tirarDados(3).size());
        assertEquals(4,dados.tirarDados(4).size());
        assertEquals(5,dados.tirarDados(5).size());
        assertEquals(6,dados.tirarDados(6).size());


    }



    @Test
    public void test03DevuelveResultadosEntreValoresEsperadosDeUnDado() {
        Dados dados = new Dados();
        assertTrue( ( 1 <= dados.tirarDados(3).get(0) ) && ( dados.tirarDados(3).get(0) <= 6 ) );
        assertTrue( ( 1 <= dados.tirarDados(3).get(1) ) && ( dados.tirarDados(3).get(1) <= 6 ) );
        assertTrue( ( 1 <= dados.tirarDados(3).get(2) ) && ( dados.tirarDados(3).get(2) <= 6 ) );
        assertTrue( ( 1 <= dados.tirarDados(4).get(0) ) && ( dados.tirarDados(4).get(0) <= 6 ) );
        assertTrue( ( 1 <= dados.tirarDados(4).get(1) ) && ( dados.tirarDados(4).get(1) <= 6 ) );
        assertTrue( ( 1 <= dados.tirarDados(4).get(2) ) && ( dados.tirarDados(4).get(2) <= 6 ) );
    }
}