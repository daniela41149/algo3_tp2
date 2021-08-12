package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;

import java.util.*;
import java.lang.String;

public class Tablero {
    private List<Pais> paises ;
    private List<Continente> continentes ;
    private Batalla batalla;

    public Tablero (List<Pais> paises, List<Continente> continentes ){
        this.paises = paises ;
        this.continentes = continentes;
    }

    public List<Pais> pasarPaisesAJuego(){
        return( this.paises);
    }

    public int cantidadPaises(){
        return (this.paises.size());
    }

    public int cantidadContinentes(){
        return (this.continentes.size());
    }

    public Pais buscarPais(String nombrePais){
        Pais paisBuscado = null;
        for(Pais pais: paises){
            if(pais.coincideNombre(nombrePais)){
                paisBuscado = pais;
                return paisBuscado;
            }
        }
        return paisBuscado;
    }

    public List<Integer>[] atacar(String nombrePaisAtacante, String nombrePaisDefensor,int cantEjercito)throws JugadaInvalidaException {
        Pais paisAtacante = buscarPais(nombrePaisAtacante) ;
        Pais paisDefensor = buscarPais(nombrePaisDefensor) ;
        if( (paisAtacante == null) || (paisDefensor == null) ){
            throw new JugadaInvalidaException();
        }
        this.batalla = new Batalla(paisAtacante,paisDefensor);
        return batalla.atacar(cantEjercito);
    }

    private Continente buscarContinente(String nombreContinente) {
        for (Continente unContinente : continentes) {
            if (unContinente.getNombre().equals(nombreContinente))
                return unContinente;
        }
        return null;
    }

    public boolean poseeUnaCantidadDePaisesEnContinente(Jugador unJugador, int cantidadDePaises, String nombreContinente) {
        Continente continente = this.buscarContinente(nombreContinente);
        if (continente == null)
            return false;

        int cantidadControlada = continente.cantidadDePaisesControlados(unJugador);

        return (cantidadControlada == cantidadDePaises);
    }

    public boolean controlaContinente(Jugador unJugador, String nombreContinente) {
        for (Continente unContinente: this.continentes) {
            if (unContinente.getNombre().equals(nombreContinente)) {
                return unContinente.jugadorControlaContinente(unJugador);
            }
        }
        return false;
    }

    public int ejercitosAdicionalesPorContinentesControlados(Jugador jugador) {
        int ejercitosAdicionales = 0;
        for (Continente unContinente: this.continentes) {
            ejercitosAdicionales = unContinente.ejercitosAdicionalesPorContinentesControlados(jugador,ejercitosAdicionales);
        }
        return ejercitosAdicionales;

    }

}




