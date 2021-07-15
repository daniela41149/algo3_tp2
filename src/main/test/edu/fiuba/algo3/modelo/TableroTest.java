package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void test01LaCantidadDePaisesEs50() {
       Tablero tablero = new Tablero("Fronteras.csv");
        assertEquals(tablero.cantidadPaises(), 50);

    }

}
