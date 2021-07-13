package edu.fiuba.algo3.modelo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.Ejercito;

public class EjercitoTest {

    @Test
    public void test01SeCreanEjercitoConCantidadDeFichasCorrectas() {
        Ejercito ejercitoUno = new Ejercito();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 0);
        Ejercito ejercitoDos = new Ejercito(3);
        assertEquals(ejercitoDos.devolverCantidadDeFichas(), 3);
    }

    @Test
    public void test02SeSacanFichasDelEjercito() {
        Ejercito ejercitoUno = new Ejercito(3);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 3);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 2);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 1);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 0);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 0);
    }

    @Test
    public void test03SeAgreganFichasDelEjercito() {
        Ejercito ejercitoUno = new Ejercito(0);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 0);
        ejercitoUno.agregarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 1);
        ejercitoUno.agregarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 2);
        ejercitoUno.agregarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 3);
        ejercitoUno.agregarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 4);
    }

    @Test
    public void test04SacoFichasDeAUna() {
        Ejercito ejercitoUno = new Ejercito(2);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 2);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.quedoSinEjercito(), false);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.quedoSinEjercito(), true);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.quedoSinEjercito(), true);
    }

    @Test
    public void test05SacaFichasPorCantidad() {
        Ejercito ejercitoUno = new Ejercito(40);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 40);
        ejercitoUno.sacarFicha(10);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 30);
        ejercitoUno.sacarFicha();
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 29);
        ejercitoUno.sacarFicha(5);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 24);
        ejercitoUno.sacarFicha(50);
        assertEquals(ejercitoUno.devolverCantidadDeFichas(), 0);
    }
}