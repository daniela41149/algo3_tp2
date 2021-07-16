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
        System.out.println("Ingrese el Pais Atacante: ");
        Scanner paisAtacante = new Scanner(System.in);
        String paisAtacanteNombre = paisAtacante.nextLine();
        System.out.println("Ingrese el Pais Defensor: ");
        Scanner paisDefensor = new Scanner(System.in);
        String paisDefensorNombre = paisDefensor.nextLine();
        System.out.println("Ingrese la cantidad de ejercito para batallar: ");
        Scanner cantEjercito = new Scanner(System.in);
        int cantEjercitoAtaca = cantEjercito.nextInt();


        try {
            juego.atacar(paisAtacanteNombre,paisDefensorNombre,cantEjercitoAtaca);
        }catch(PaisNoLimitrofeException e) {
        }
        /*
        boolean repetir = true;

        while(repetir){
            try {
                paisDefensor = new Scanner(System.in);
                paisDefensorNombre = paisDefensor.nextLine();
                juego.atacar(paisAtacanteNombre,paisDefensorNombre,cantEjercitoAtaca);
                repetir = false;
            }catch(PaisNoLimitrofeException e) {
                System.out.println("El pais Defensor no es Limitrofe al atacante, ingrese otro: ");
            }
        }

         */

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
