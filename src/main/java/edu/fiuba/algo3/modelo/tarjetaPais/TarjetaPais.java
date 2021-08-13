package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

public class TarjetaPais {

    private String nombre;
    private Simbolo simbolo;
    private EstadoTarjetaPais estadoTarjeta;

    public TarjetaPais(String nombre, String tipoDeSimbolo) {

        this.nombre = nombre;
        this.estadoTarjeta = new Desactivada();
        guardarSimboloDeTarjetaPais(tipoDeSimbolo);
    }

    public String getNombre() {
        return this.nombre;
    }

    // public Simbolo getSimbolo() {
    //    return this.simbolo;
    // }


    public void activarTarjeta(Jugador jugador) throws JugadaInvalidaException {
        this.estadoTarjeta.activar(this, jugador);
    }

    public void activar() {
        this.estadoTarjeta = new Activada();
    }

    public void desactivar () {
        this.estadoTarjeta = new Desactivada();
    }


    private boolean esElMismoSimbolo(Simbolo simbolo1, Simbolo simbolo2) {
        return this.simbolo.esElMismoSimbolo(simbolo1) && this.simbolo.esElMismoSimbolo(simbolo2);
    }

    private boolean sonSimbolosDiferentes(Simbolo simbolo1, Simbolo simbolo2) {
        return this.simbolo.sonSimbolosDiferentes(simbolo1) && this.simbolo.sonSimbolosDiferentes(simbolo2) && simbolo1.sonSimbolosDiferentes(simbolo2);
    }

    private boolean esElMismoSimbolo(TarjetaPais tarjetaPais, Simbolo simbolo) {
        return tarjetaPais.esElMismoSimbolo(this.simbolo,simbolo);
    }

    public boolean esElMismoSimbolo(String nombreSimbolo) {
        return this.simbolo.esElMismoSimbolo(nombreSimbolo);
    }

    private boolean sonSimbolosDiferentes(TarjetaPais tarjetaPais, Simbolo simbolo) {
        return tarjetaPais.sonSimbolosDiferentes(this.simbolo,simbolo);
    }


    public boolean puedeCanjear (TarjetaPais tarjetaPais1, TarjetaPais tarjetaPais2) {
        return tarjetaPais1.esElMismoSimbolo(tarjetaPais2,this.simbolo) || tarjetaPais1.sonSimbolosDiferentes(tarjetaPais2,this.simbolo);
    }


    private void guardarSimboloDeTarjetaPais (String simbolo) {
        if (simbolo.equals("Comodin")) {
            this.simbolo = new Comodin();
        } else {
            this.simbolo = new Figura(simbolo);
        }
    }
}


