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

    public int ejercitosAdicionalesPorContinentesControlados(Jugador jugador,int ejercitosAdicionales) {
        if (this.jugadorControlaContinente(jugador)){
            return ejercitosAdicionales += ejercitos;
        }
        return ejercitosAdicionales;

    }


}
