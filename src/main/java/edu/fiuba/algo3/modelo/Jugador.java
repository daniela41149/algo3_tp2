package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombreJugador;
    private String color;
    private List<Pais> paises ;
    private Jugador jugadorQueLoDestruyo;
    private TarjetaObjetivo tarjetaDeObjetivo;
    private Juego juego;
    private List<TarjetaPais> tarjetasPais;


    public Jugador(String nombre, String colorJugador, Juego juego){
        this.nombreJugador = nombre;
        this. color = colorJugador;
        this.paises = new ArrayList<>();
        this.jugadorQueLoDestruyo = null;
        this.juego = juego;
        this.tarjetasPais = new ArrayList<>();
    }

    public void agregarPais(Pais nuevoPais){
        nuevoPais.elegirPais(this);
        paises.add(nuevoPais);
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

    public void colocarEjercito(String nombrePais,int cantidadEjercito)throws JugadaInvalidaException {
        Pais pais = this.buscarPais(nombrePais);
        if(pais == null){
            throw new JugadaInvalidaException();
        }
        pais.colocarEjercito (this, cantidadEjercito);

    }

    public List<Pais> pedirPaises(){
        List<Pais> listaPaises = new ArrayList<>();
        for(Pais pais: paises){
            listaPaises.add(pais);
        }
        return listaPaises;
    }

    public String getNombre () {
        return this.nombreJugador;
    }

    public String getColor () {
        return this.color;
    }

    public boolean esElMismo(Jugador jugador)  {
        if( (jugador.getNombre().equals(this.nombreJugador) ) && (jugador.getColor().equals(this.color))){
            return true;
        }
        return false;
    }

    public void desocupar(String nombrePais){
        Pais paisBuscado = buscarPais(nombrePais);
        paises.remove(paisBuscado);
    }

    public int cantidadPaises(){
        return (this.paises.size());
    }

    public void establecerObjetivo(TarjetaObjetivo objetivo) {
        objetivo.establecerDueño(this);
        this.tarjetaDeObjetivo = objetivo;
    }

    public boolean cumplioObjetivo(Tablero tablero) {
        if (tarjetaDeObjetivo == null)
            return false;

        return (tarjetaDeObjetivo.cumplioObjetivo(tablero, juego));
    }

    public void establecerPosibleDestructor(Jugador jugador) {
        if (cantidadPaises() == 0)
            this.jugadorQueLoDestruyo = jugador;
    }

    public boolean fueDestruidoPor(Jugador jugador) {
        if (jugadorQueLoDestruyo == null)
            return false;

        return (jugadorQueLoDestruyo.esElMismo(jugador));
    }

    public void pedirTarjetaPais(){
        TarjetaPais nuevaTarjetaPais = juego.entregaTarjetaPais();
        tarjetasPais.add(nuevaTarjetaPais);
    }

    public boolean esDueñoDelPais(String nombrePais) {
        for (Pais pais : paises) {
            if (pais.coincideNombre(nombrePais)) {
                return true;
            }
        }
        return false;
    }



        public void activarTarjetaPais (String nombreTarjetaPais) throws JugadaInvalidaException {
        for (TarjetaPais unaTarjetaPais: tarjetasPais){
            if (unaTarjetaPais.getNombre().equals(nombreTarjetaPais)){
                unaTarjetaPais.activarTarjeta(this);
            }
        }
    }



}

