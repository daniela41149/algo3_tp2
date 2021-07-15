package edu.fiuba.algo3.modelo;

import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombreJugador;
    private String color;


    public void jugarTurno(){
        Scanner paisAtacante = new Scanner(System.in);
        String paisAtacanteNombre = paisAtacante.nextLine();
        Scanner paisDefensor = new Scanner(System.in);
        String paisDefensorNombre = paisDefensor.nextLine();
        Scanner cantEjercito = new Scanner(System.in);
        int cantEjercitoAtaca = cantEjercito.nextInt();

        atacar(paisAtacanteNombre,paisDefensorNombre,cantEjercitoAtaca);
    }
    public void desocupar(){

    }
}
