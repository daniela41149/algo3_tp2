package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.Ejercito;
import edu.fiuba.algo3.Pais;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaisTest {
    @Test
    public void test001SeCreaUnPaisConNombrePaisNombreContienteYPaisesLimitrofes() {
        String nombrePais = "Argentina";
        String nombreContinente = "America del Sur";

        List<String> limitrofes = new ArrayList<>();
        limitrofes.add("Chile");
        limitrofes.add("Peru");
        limitrofes.add("Brasil");
        limitrofes.add("Uruguay");

        Pais pais = new Pais(nombrePais, nombreContinente, limitrofes);
        assertEquals(pais.getNombre(),nombrePais);
        assertEquals(pais.getNombreContinente(),nombreContinente);
        System.out.println("Pais: " + pais.getNombre());
        System.out.println("Continente: " + pais.getNombreContinente());
        System.out.println("Limitrofes: "+limitrofes);
    }

    @Test
    public void test002DosPaisesSonLimitrofes() {
        String nombrePais1 = "Argentina";
        String nombreContinente1 = "America del Sur";

        List<String> limitrofes1 = new ArrayList<>();
        limitrofes1.add("Chile");
        limitrofes1.add("Peru");
        limitrofes1.add("Brasil");
        limitrofes1.add("Uruguay");

        String nombrePais2 = "Uruguay";
        String nombreContinente2 = "America del Sur";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Argentina");
        limitrofes2.add("Brasil");

        Pais pais1 = new Pais(nombrePais1, nombreContinente1, limitrofes1);
        Pais pais2 = new Pais(nombrePais2, nombreContinente2, limitrofes2);

        assertTrue(pais1.esLimitrofe(pais2));
        assertTrue(pais2.esLimitrofe(pais1));
        System.out.println("Los paises " + pais1.getNombre() +" y "+ pais2.getNombre() +" son limítrofes.");
    }

    @Test
    public void test003DosPaisesNoSonLimitrofes() {
        String nombrePais1 = "Argentina";
        String nombreContinente1 = "America del Sur";

        List<String> limitrofes1 = new ArrayList<>();
        limitrofes1.add("Chile");
        limitrofes1.add("Peru");
        limitrofes1.add("Brasil");
        limitrofes1.add("Uruguay");

        String nombrePais2 = "Italia";
        String nombreContinente2 = "Europa";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Alemania");
        limitrofes2.add("Francia");

        Pais pais1 = new Pais(nombrePais1, nombreContinente1, limitrofes1);
        Pais pais2 = new Pais(nombrePais2, nombreContinente2, limitrofes2);

        assertFalse(pais1.esLimitrofe(pais2));
        assertFalse(pais2.esLimitrofe(pais1));
        System.out.println("Los paises " + pais1.getNombre() +" e "+ pais2.getNombre() +" no son limítrofes.");
    }

    @Test
    public void test005NoPuedeAtacarAUnPaisQueNoEsLimitrofe() {
        String nombrePais1 = "Argentina";
        String nombreContinente1 = "America del Sur";

        List<String> limitrofes1 = new ArrayList<>();
        limitrofes1.add("Chile");
        limitrofes1.add("Peru");
        limitrofes1.add("Brasil");
        limitrofes1.add("Uruguay");

        String nombrePais2 = "Java";
        String nombreContinente2 = "Oceania";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Australia");

        Pais pais1 = new Pais(nombrePais1, nombreContinente1, limitrofes1);
        Pais pais2 = new Pais(nombrePais2, nombreContinente2, limitrofes2);

        assertNull(pais1.atacar(pais2, 1));
        System.out.println(pais1.getNombre() +" no puede atacar a "+ pais2.getNombre() +" porque no son limítrofes.");
    }

    @Test
    public void test006PuedeAtacarAUnPaisQueSiEsLimitrofe() {
        String nombrePais1 = "Argentina";
        String nombreContinente1 = "America del Sur";

        List<String> limitrofes1 = new ArrayList<>();
        limitrofes1.add("Chile");
        limitrofes1.add("Peru");
        limitrofes1.add("Brasil");
        limitrofes1.add("Uruguay");

        String nombrePais2 = "Peru";
        String nombreContinente2 = "America del Sur";

        List<String> limitrofes2 = new ArrayList<>();
        limitrofes2.add("Colombia");
        limitrofes2.add("Brasil");
        limitrofes2.add("Chile");
        limitrofes2.add("Argentina");

        Pais pais1 = new Pais(nombrePais1, nombreContinente1, limitrofes1);
        Pais pais2 = new Pais(nombrePais2, nombreContinente2, limitrofes2);

        List<Integer> dados = new ArrayList<>();
        dados.add(1);
        dados.add(3);
        dados.add(4);

        Ejercito mockedEjercito1 = mock(Ejercito.class);
        Ejercito mockedEjercito2 = mock(Ejercito.class);
        when(mockedEjercito1.atacar(anyInt())).thenReturn(dados);
        when(mockedEjercito2.atacar(anyInt())).thenReturn(dados);
        pais1.setEjercito(mockedEjercito1);
        pais2.setEjercito(mockedEjercito2);

        assertNotNull(pais1.atacar(pais2,1));
        assertNotNull(pais1.atacar(pais2,2));
        assertNotNull(pais1.atacar(pais2,3));
        assertNotNull(pais2.atacar(pais1,1));
        assertNotNull(pais2.atacar(pais1,2));
        assertNotNull(pais2.atacar(pais1,3));

        assertEquals(pais1.atacar(pais2,1),dados);
        assertEquals(pais1.atacar(pais2,2),dados);
        assertEquals(pais1.atacar(pais2,3),dados);
        assertEquals(pais2.atacar(pais1,1),dados);
        assertEquals(pais2.atacar(pais1,2),dados);
        assertEquals(pais2.atacar(pais1,3),dados);

        System.out.println("Cuando los paises son limitrofes puede atacar y devuelve sus dados "+ pais1.atacar(pais2,3));
    }




}
