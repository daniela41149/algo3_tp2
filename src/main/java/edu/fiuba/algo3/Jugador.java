package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.NullPointerException;

public class Jugador {
    private String nombreJugador;
    private String color;
    private List<Pais> paises ;
    private Juego juego;


    public Jugador(String nombre, String colorJugador, Juego juego){
        this.nombreJugador = nombre;
        this. color = colorJugador;
        this.juego = juego;
    }

    public void agragarPaises(List<Pais> paisesDelJugador){
        this. paises = paisesDelJugador;
    }


    public void jugarTurno(){
        Scanner paisAtacante = new Scanner(System.in);
        String paisAtacanteNombre = paisAtacante.nextLine();
        Scanner paisDefensor = new Scanner(System.in);
        String paisDefensorNombre = paisDefensor.nextLine();
        Scanner cantEjercito = new Scanner(System.in);
        int cantEjercitoAtaca = cantEjercito.nextInt();


       //juego.atacar(paisAtacanteNombre,paisDefensorNombre,cantEjercitoAtaca);

    }

    public String getNombre () {
        return this.nombreJugador;
    }

    public String getColor () {
        return this.color;
    }

    public boolean esElMismo(Jugador jugador) throws NoEsElMismoJugadorException{
        if( (jugador.getNombre().equals(this.nombreJugador) ) && (jugador.getColor().equals(this.color))){
            return true;
        }
        throw new NoEsElMismoJugadorException();
    }

    public void desocupar(String nombrePais){
        boolean paisEncontrado = false;
        int i = 0;
        Pais paisBuscado;
        while( (!paisEncontrado) && (i < paises.size()) ){
            paisBuscado = paises.get(i);
            if(paisBuscado.coincideNombre(nombrePais)){
                paisEncontrado = true;
                paises.remove(i);
            }
            i++;
        }

    }

    public int cantidadPaises(){
        return (this.paises.size());
    }
}
