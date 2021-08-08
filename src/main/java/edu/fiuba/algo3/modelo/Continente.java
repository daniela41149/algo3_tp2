package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.pais.Pais;

import java.util.List;

public class Continente {
    private String nombre;
    private List<Pais> paises;
    private int ejercitos;


    public Continente(String nombreContinente, List<Pais> listaPaises, int ejercitosAdicionales){
        nombre = nombreContinente;
        paises = listaPaises;
        ejercitos = ejercitosAdicionales;
    }

    public int cantidadDePaisesControlados(Jugador unJugador) {
        int cantidadControlada = 0;

        for (Pais pais : paises) {
            if (pais.esElDueño(unJugador))
                cantidadControlada++;
        }

        return cantidadControlada;
    }

    public boolean jugadorControlaContinente (Jugador jugador) {
        for (Pais cadaPais: paises) {
            if (!cadaPais.esElDueño(jugador)) {
                return false;
            }
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public int cantidadDePaises() {
        return paises.size();
    }

    public int ejercitosAdicionalesPorContinentesControlados(Jugador jugador,int sumaEjercitos) {
        if (this.jugadorControlaContinente(jugador)){
            sumaEjercitos += ejercitos;
        }
        return sumaEjercitos;

    }

}
