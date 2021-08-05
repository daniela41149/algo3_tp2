package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Continente;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
    static final int EJERCITOS_ADICIONALES = 3;
    private String nombrePais;
    private  String nombreOtroPais;
    private List<String> limitrofes = new ArrayList<>();
    private Pais paisUno;
    private Pais paisDos;
    private List<Pais> listaPaises = new ArrayList<>();
    private Continente continente;
    private List<Continente> listaContinentes = new ArrayList<>();
    private Juego juego;

    @BeforeEach
    public void setup() {

        nombrePais = "Argentina";
        limitrofes.add("Chile");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");
        paisUno = new Pais(nombrePais, limitrofes);
        listaPaises.add(paisUno);

        nombreOtroPais = "Brasil";
        limitrofes.add("Uruguay");
        limitrofes.add("Argentina");
        paisDos = new Pais(nombreOtroPais, limitrofes);
        listaPaises.add(paisDos);


        continente = new Continente("America del Sur",listaPaises,EJERCITOS_ADICIONALES);
        listaContinentes.add(continente);
    }

    @Test
    public void test01LaListaDePaisesCoincide() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        assertEquals( listaPaises,tablero.pasarPaisesAJuego());

    }

    @Test
    public void test02EncuentraElPaisBuscado() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        assertEquals( paisDos, tablero.buscarPais("Brasil"));

    }

    @Test
    public void test03LaCantidaDePaisesEsDos() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);

        assertEquals( 2,tablero.cantidadPaises() );

    }
    @Test
    public void test04LaCantidaDeContientesEsUno() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);

        assertEquals( 1,tablero.cantidadContinentes() );

    }
    @Test
    public void test05NoSeEncuentraElPaisBuscado() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        assertEquals( null, tablero.buscarPais("Rusia"));

    }
    @Test
    public void test06JugadorNoPoseeDosPaisesDelContinente() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        Jugador jugador = new Jugador("Adrian", "Rojo",juego);
        jugador.agregarPais(paisUno);
        jugador.agregarPais(paisDos);

        assertFalse(tablero.poseeUnaCantidadDePaisesEnContinente(jugador,2,"America"));


    }
    @Test
    public void test07JugadorPoseeDosPaisesDelContinente() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        Jugador jugador = new Jugador("Adrian", "Rojo",juego);
        jugador.agregarPais(paisUno);
        jugador.agregarPais(paisDos);

        assertTrue(tablero.poseeUnaCantidadDePaisesEnContinente(jugador,2,"America del Sur"));

    }
    @Test
    public void test08JugadorContolaElContinente() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        Jugador jugador = new Jugador("Adrian", "Rojo",juego);
        jugador.agregarPais(paisUno);
        jugador.agregarPais(paisDos);

        assertTrue(tablero.controlaContinente(jugador,"America del Sur"));

    }
    @Test
    public void test09JugadorNoContolaElContinente() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);
        Jugador jugador = new Jugador("Adrian", "Rojo",juego);
        jugador.agregarPais(paisUno);

        assertFalse(tablero.controlaContinente(jugador,"America del Sur"));

    }
}
