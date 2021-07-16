package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.*;
import edu.fiuba.algo3.Tablero;
import edu.fiuba.algo3.Pais;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    Pais pais;

    @Test
    public void test01LaCantidadDePaisesEs50() {
       Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/Fronteras.csv");
        assertEquals( 50,tablero.cantidadPaises());

    }

    @Test
    public void test02LaCantidadDeContinentesEs6() {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/Fronteras.csv");
        assertEquals( 6, tablero.cantidadContinentes());

    }
    @Test
    public void test03SeEncuentraElPaisBuscado() {
        Tablero tablero = new Tablero("src/main/java/edu/fiuba/algo3/Fronteras.csv");
        pais = tablero.buscarPais("Argentina");
        assertEquals( "Argentina", pais.getNombre() );

    }

}
