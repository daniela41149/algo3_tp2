package edu.fiuba.algo3.modelo.pais;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pais.EstadoPais;
import edu.fiuba.algo3.modelo.pais.Pais;

public class Vacante implements EstadoPais {

    public Vacante() {
    }
    public void ocuparPais(Jugador jugador, Pais pais) {
        pais.elegirPais(jugador);
        pais.cambiarEstadoAOcupado();
    }


}
