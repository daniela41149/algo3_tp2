package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.tarjetaPais.EstadoTarjetaPais;

public class TarjetaPais {

    private String nombre;
    private String simbolo;
    private EstadoTarjetaPais estadoTarjeta;

    public TarjetaPais (String nombre, String simbolo) {

        this.nombre = nombre;
        this.simbolo = simbolo;

    }

    public String getNombre(){
        return this.nombre;
    }
    public String getSimbolo(){
        return this.simbolo;
    }


    public void activar(Jugador jugador) throws JugadaInvalidaException {
        this.estadoTarjeta.activar(this,jugador);
    }

    public void activar(){
        this.estadoTarjeta = new TarjetaActivada();
    }




}
