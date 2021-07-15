package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombreJugador;
    private String color;
    private List<Pais> paises = new ArrayList<>();

    public Jugador(String nombre, String colorJugador, List<Pais> paisesDelJugador){
        this.nombreJugador = nombre;
        this. color = colorJugador;
        this. paises = paisesDelJugador;
    }


    public void jugarTurno(){
        Scanner paisAtacante = new Scanner(System.in);
        String paisAtacanteNombre = paisAtacante.nextLine();
        Scanner paisDefensor = new Scanner(System.in);
        String paisDefensorNombre = paisDefensor.nextLine();
        Scanner cantEjercito = new Scanner(System.in);
        int cantEjercitoAtaca = cantEjercito.nextInt();


        //atacar(paisAtacanteNombre,paisDefensorNombre,cantEjercitoAtaca);

    }
    public boolean esElMismo(Jugador jugador){
        return (jugador.nombreJugador.equals(this.nombreJugador);
    }
    public void desocupar(String nombrePais){
        boolean paisEncontrado = false;
        int i = 0;
        Pais paisBuscado;
        while( (!paisEncontrado) && (i < paises.size()) ){
            paisBuscado = paises[i];

            if(paisBuscado.coincideNombre(nombrePais)){
                paisEncontrado = true;
                paises.remove(i);
            }
            i++;
        }

    }
}
