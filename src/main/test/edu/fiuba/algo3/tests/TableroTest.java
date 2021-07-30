package edu.fiuba.algo3.tests;

import edu.fiuba.algo3.modelo.Continente;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.pais.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


        continente = new Continente("America",listaPaises,EJERCITOS_ADICIONALES);
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
    public void test03LaCantidaDeContientesEsUno() {
        Tablero tablero = new Tablero(listaPaises,listaContinentes);

        assertEquals( 1,tablero.cantidadContinentes() );

    }



}
