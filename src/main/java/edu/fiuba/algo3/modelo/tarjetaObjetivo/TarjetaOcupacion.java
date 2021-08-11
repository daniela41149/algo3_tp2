package edu.fiuba.algo3.modelo.tarjetaObjetivo;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TarjetaOcupacion extends TarjetaObjetivo {

    private String enunciado;
    private List<String> continentesAOcupar;
    private HashMap<String, Integer> cantidadPaisesPorContinente;

    public TarjetaOcupacion(String enunciado, List<String> continentesAOcupar, HashMap<String, Integer> cantidadPaisesPorContinente) {

        this.enunciado = enunciado;
        this.continentesAOcupar = continentesAOcupar;
        this.cantidadPaisesPorContinente = cantidadPaisesPorContinente;
    }

    private boolean ocupoLosContinentesDeEnunciado(Tablero tablero) {
        int i = 0;
        boolean cumpleObjetivo = true;

        while ( (i < continentesAOcupar.size()) && cumpleObjetivo ) {
            cumpleObjetivo = tablero.controlaContinente(dueño, continentesAOcupar.get(i));
            i++;
        }

        return cumpleObjetivo;
    }

    private boolean ocupoLaCantidadDePaisesPorContinenteDeEnunciado(Tablero tablero) {
        for (Map.Entry<String, Integer> entry : cantidadPaisesPorContinente.entrySet()) {
            if ( !tablero.poseeUnaCantidadDePaisesEnContinente(dueño, entry.getValue(), entry.getKey()) )
                return false;
        }

        return true;
    }

    public boolean cumplioObjetivo(Tablero tablero, Juego juego) {
        if (dueño == null || tablero == null || juego == null)
            return false;

        if (cumplioObjetivoGeneral())
            return true;

        return (ocupoLosContinentesDeEnunciado(tablero) && ocupoLaCantidadDePaisesPorContinenteDeEnunciado(tablero));
    }
    public String devolverEnunciado() {
        return enunciado;
    }
}
