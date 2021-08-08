package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

public class TarjetaPais {

    private String nombre;
    private String simbolo;
    private EstadoTarjetaPais estadoTarjeta;

    public TarjetaPais(String nombre, String simbolo) {

        this.nombre = nombre;
        this.simbolo = simbolo;
        this.estadoTarjeta = new Desactivada();

    }

    public String getNombre() {
        return this.nombre;
    }

    public String getSimbolo() {
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


    public boolean esElMismoSimbolo(TarjetaPais tarjetaPais1, TarjetaPais tarjetaPais2) {
        return this.simbolo.equals(tarjetaPais1.getSimbolo()) && this.simbolo.equals(tarjetaPais2.getSimbolo());
    }

    public boolean sonSimbolosDiferentes(TarjetaPais tarjetaPais1, TarjetaPais tarjetaPais2) {
        return !this.simbolo.equals(tarjetaPais1.getSimbolo()) && !this.simbolo.equals(tarjetaPais2.getSimbolo()) && !tarjetaPais1.getSimbolo().equals(tarjetaPais2.getSimbolo());
    }
/*
    private void guardarSimboloDeTarjetaPais (String simbolo) {
        if (simbolo.equals("Comodin")) {
            this.simbolo = new Comodin();
        }
        else {
            this.simbolo = new Figura(simbolo);
        }
    }
*/

}



