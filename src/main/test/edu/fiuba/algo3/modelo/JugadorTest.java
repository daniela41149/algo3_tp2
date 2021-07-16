package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Jugador;
import edu.fiuba.algo3.NoEsElMismoJugadorException;
import edu.fiuba.algo3.Pais;
import edu.fiuba.algo3.Juego;
import edu.fiuba.algo3.PaisNoLimitrofeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    private String nombrePais;
    private  String nombreOtroPais;
    private List<String> limitrofes = new ArrayList<>();
    private Pais paisUno;
    private Pais paisDos;
    private Juego juego;

    @BeforeEach
    public void setup() {
        juego = new Juego();
        nombrePais = "Argentina";
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        paisUno = new Pais(nombrePais, limitrofes);


        nombreOtroPais = "Brasil";
        limitrofes.add("Uruguay");
        limitrofes.add("Argentina");
        paisDos = new Pais(nombreOtroPais, limitrofes);

    }

    @Test
    public void test01EsElMismoJugador() {

        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        assertDoesNotThrow( () -> jugador1.esElMismo(jugador1) );
    }
    @Test
    public void test02NoEsElMismoJugador() {

        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        Jugador jugador2 = new Jugador("Sebastian", "Rojo",juego);
        jugador2.agregarPais(paisUno);
        assertThrows(NoEsElMismoJugadorException.class, ()-> jugador1.esElMismo(jugador2));
    }


    @Test
    public void test03DesocupoUnPaisYQuedaConUnosolo() {

        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);


        assertEquals( 2 ,jugador1.cantidadPaises());
        jugador1.desocupar("Brasil");
        assertEquals( jugador1.cantidadPaises() , 1);
    }


}
