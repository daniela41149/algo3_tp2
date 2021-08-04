package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.Juego;
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
    private Pais paisTres;
    private Juego juego;

    @BeforeEach
    public void setup() {

        nombrePais = "Argentina";
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        paisUno = new Pais(nombrePais, limitrofes);
        limitrofes.clear();

        nombreOtroPais = "Brasil";
        limitrofes.add("Uruguay");
        limitrofes.add("Argentina");
        paisDos = new Pais(nombreOtroPais, limitrofes);
        limitrofes.clear();

        nombreOtroPais = "Estados Unidos";
        limitrofes.add("Canada");
        limitrofes.add("Mexico");
        paisTres = new Pais(nombreOtroPais, limitrofes);
        limitrofes.clear();

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
        assertFalse(jugador1.esElMismo(jugador2));
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
    @Test
    public void test04LaListaDePaisesCoincide() {

        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        List<Pais> listaPaises = new ArrayList<>();
        listaPaises.add(paisUno);
        listaPaises.add(paisDos);


        assertEquals( listaPaises ,jugador1.pedirPaises());

    }
    
    @Test
    public void test05SeEncuentraPaisBuscado() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        assertEquals( paisUno,jugador1.buscarPais("Argentina"));
    }
    @Test
    public void test06NoSeEncuentraPaisBuscado() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        assertEquals( null,jugador1.buscarPais("Rusia"));
    }
    @Test
    public void test07LaCantidadDePaisesCoincide() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        assertEquals( 2,jugador1.cantidadPaises());
    }

    @Test
    public void test08CoincideElJugadorDestructor() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        Jugador jugador2 = new Jugador("Lucas", "Azul",juego);
        jugador2.agregarPais(paisTres);

        jugador1.establecerPosibleDestructor(jugador2);

        assertEquals( true,jugador1.fueDestruidoPor(jugador2));
    }
    @Test
    public void test09NoCoincideElJugadorDestructor() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        Jugador jugador2 = new Jugador("Lucas", "Azul",juego);
        jugador2.agregarPais(paisTres);

        assertEquals( false,jugador1.fueDestruidoPor(jugador2));
    }
    @Test
    public void test010Jugador1EsDueñoDeArgentina() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        assertEquals( true,jugador1.esDueñoDelPais("Argentina"));
    }
    @Test
    public void test011Jugador1NoEsDueñoDeRusia() {
        Jugador jugador1 = new Jugador("Adrian", "Rojo",juego);
        jugador1.agregarPais(paisUno);
        jugador1.agregarPais(paisDos);

        assertEquals( false,jugador1.esDueñoDelPais("Rusia"));
    }



}
