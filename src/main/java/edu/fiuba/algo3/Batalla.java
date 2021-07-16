package edu.fiuba.algo3;

import java.util.Collections;
import java.util.List;

public class Batalla {

    private Pais paisAtacante;
    private Pais paisDefensor;

    public Batalla(Pais paisAtacante, Pais paisDefensor) {
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
    }

    private void ordenarDadosDeFormaDescendente(List<Integer> dadosDelAtacante, List<Integer> dadosDelDefensor) {
        dadosDelAtacante.sort(Collections.reverseOrder());
        dadosDelDefensor.sort(Collections.reverseOrder());
    }

    private void establecerResultadosDeBatalla(List<Integer> dadosDelAtacante, List<Integer> dadosDelDefensor) {
        int comparacionesMaximas = Math.min(dadosDelAtacante.size(), dadosDelDefensor.size());

        for (int i = 0; i < comparacionesMaximas; i++) {
            if (dadosDelAtacante.get(i) > dadosDelDefensor.get(i)) {
                paisDefensor.sacarFicha(1);
            } else {
                paisAtacante.sacarFicha(1);
            }
        }
    }

    public void atacar(int cantidadEjercito) throws PaisNoLimitrofeException {
        List<Integer> dadosDelAtacante;
        List<Integer> dadosDelDefensor;

        dadosDelAtacante = paisAtacante.atacar(paisDefensor, cantidadEjercito);
        dadosDelDefensor = paisDefensor.defender();

        ordenarDadosDeFormaDescendente(dadosDelAtacante, dadosDelDefensor);
        establecerResultadosDeBatalla(dadosDelAtacante, dadosDelDefensor);
        paisDefensor.establecerDueño(paisAtacante);
    }

}
