package edu.fiuba.algo3.modelo.tarjetaPais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

public class Desactivada implements EstadoTarjetaPais{
    static final int EJERCITOS = 2;

    public void activar(TarjetaPais tarjetaPais,Jugador jugador) throws JugadaInvalidaException{
        String nombrePais = tarjetaPais.getNombre();
        jugador.colocarEjercito(nombrePais,EJERCITOS);
        if(jugador.esDueñoDelPais(nombrePais)){
            tarjetaPais.activar();
        }

    }




}
