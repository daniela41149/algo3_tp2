package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

public interface EstadoTarjetaPais {

    public void activar(TarjetaPais tarjeta, Jugador jugador) throws JugadaInvalidaException;


}
