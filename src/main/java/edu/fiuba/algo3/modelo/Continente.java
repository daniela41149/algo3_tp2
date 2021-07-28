package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.pais.Pais;

import java.util.List;


public class Continente {
    private String nombre;
    private List<Pais> paises;


    public Continente(String nombreContinente, List<Pais> listaPaises){
        nombre = nombreContinente;
        paises = listaPaises;
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


}
