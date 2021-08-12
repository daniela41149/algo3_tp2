package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;

import java.util.ArrayList;
import java.util.List;

public class Batalla {

    private Pais paisAtacante;
    private Pais paisDefensor;

    public Batalla(Pais paisAtacante, Pais paisDefensor) {
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
    }

    private void establecerResultadosDeBatalla(List<Integer> dadosDelAtacante, List<Integer> dadosDelDefensor) {
        int comparacionesMaximas = Math.min(dadosDelAtacante.size(), dadosDelDefensor.size());

        for (int i = 0; i < comparacionesMaximas; i++) {
            if (dadosDelAtacante.get(i) > dadosDelDefensor.get(i)) {
                paisDefensor.sacarFichas(1);
            } else {
                paisAtacante.sacarFichas(1);
            }
        }
    }

    public List<Integer>[] atacar(int cantidadEjercito) throws JugadaInvalidaException {
        List<Integer> dadosDelAtacante;
        List<Integer> dadosDelDefensor;
        List<Integer> [ ] dadosEnBatalla = new List [2];

        dadosDelAtacante = paisAtacante.atacar(paisDefensor, cantidadEjercito);
        dadosDelDefensor = paisDefensor.defender();

        dadosEnBatalla[0] = dadosDelAtacante;
        dadosEnBatalla[1] = dadosDelDefensor;

        establecerResultadosDeBatalla(dadosDelAtacante, dadosDelDefensor);
        paisDefensor.establecerDueño(paisAtacante);
        return dadosEnBatalla;
    }

}
