package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.*;
import java.lang.String;
import java.io.*;

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

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor,int cantEjercito)throws JugadaInvalidaException {
        Pais paisAtacante = buscarPais(nombrePaisAtacante) ;
        Pais paisDefensor = buscarPais(nombrePaisDefensor) ;
        if((paisAtacante == null) || (paisDefensor == null)  ){
            throw new JugadaInvalidaException();
        }
        this.batalla = new Batalla(paisAtacante,paisDefensor);
        batalla.atacar(cantEjercito);
    }

    public int cantidadPaises(){
        return (this.paises.size());
    }
    public int cantidadContinentes(){
        return (this.continentes.size());
    }



}


