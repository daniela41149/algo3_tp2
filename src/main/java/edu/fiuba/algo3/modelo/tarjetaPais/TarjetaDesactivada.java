package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

public class TarjetaDesactivada implements EstadoTarjetaPais{
    static final int EJERCITOS = 2;

    public void activar(TarjetaPais tarjetaPais,Jugador jugador) throws JugadaInvalidaException{
        String nombrePais = tarjetaPais.getNombre();
        if(jugador.esDueñoDelPais(nombrePais)){
            jugador.colocarEjercito(nombrePais,EJERCITOS);
            tarjetaPais.activar();
        }



    }




}
