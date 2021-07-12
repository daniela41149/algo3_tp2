package edu.fiuba.algo3.modelo;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.Dados;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void test03DevuelveResultadosDeUnDadoReal() {
        Dados dados = new Dados();
        System.out.println(dados.dadosAtaque(4));
        System.out.println(dados.dadosAtaque(2));
        System.out.println(dados.dadosAtaque(3));
        System.out.println(dados.dadosAtaque(1));

        System.out.println(dados.dadosDefensa(4));
        System.out.println(dados.dadosDefensa(5));
        System.out.println(dados.dadosDefensa(1));
        System.out.println(dados.dadosDefensa(2));
    }
}