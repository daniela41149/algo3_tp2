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

    public Simbolo getSimbolo() {
        return this.simbolo;
    }


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

    public boolean puedeCanjear (TarjetaPais tarjetaPais1, TarjetaPais tarjetaPais2) {
        return this.esElMismoSimbolo(tarjetaPais1.getSimbolo(),tarjetaPais2.getSimbolo()) || this.sonSimbolosDiferentes(tarjetaPais1.getSimbolo(),tarjetaPais2.getSimbolo());
    }

    private void guardarSimboloDeTarjetaPais (String simbolo) {
        if (simbolo.equals("Comodin")) {
            this.simbolo = new Comodin();
        } else {
            this.simbolo = new Figura(simbolo);
        }
    }


}



