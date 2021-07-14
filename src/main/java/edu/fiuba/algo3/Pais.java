package edu.fiuba.algo3;

import java.util.List;

public class Pais {
    private String nombrePais;
    private String nombreContinente;
    private List<String> nombrePaisesLimitrofes;
    //private Jugador dueño;
    private Ejercito ejercito;


    public Pais(String nombrePais, String nombreContinente, List<String> nombrePaisesLimitrofes) {
        this.nombrePais = nombrePais;
        this.nombreContinente = nombreContinente;
        this.nombrePaisesLimitrofes = nombrePaisesLimitrofes;
        this.ejercito = new Ejercito();
    }

    public void esLimitrofe(Pais paisAtacante) throws PaisNoLimitrofeException {
        for (String limitrofe: nombrePaisesLimitrofes) {
            if (paisAtacante.getNombre().equals(limitrofe)) {
                return;
            }
        }
        throw new PaisNoLimitrofeException();
    }

    public List<Integer> atacar(Pais paisDefensa, int cantidadEjercito) throws PaisNoLimitrofeException {
        paisDefensa.esLimitrofe(this);
        return (ejercito.atacar(cantidadEjercito));
    }

    public List<Integer> defender() {
        return (ejercito.defender());
    }

    public void sacarFicha(int cantidad) {
        ejercito.sacarFicha(cantidad);
    }

    /*
    public Jugador nuevoDueño() {
        return this.dueño;
    }

    private void elegirPais (Jugador jugador) {
        this.dueño = jugador;
    }


    public void establecerDueño (Pais paisAtacante) {
        if (ejercito.quedoSinEjercito()) {
            this.dueño.desocupar();
            this.dueño = paisAtacante.nuevoDueño();
        }
    }

    public void colocarEjercito (Jugador jugador, int cantidadEjercito) throws NoEsElMismoJugadorException {
        if (this.dueño == null) {
            this.elegirPais(jugador);
        }
        this.dueño.esElMismo(jugador);
        ejercito.agregarFichas(cantidadEjercito);
    }

    */



    public String getNombre () {
        return this.nombrePais;
    }

    public String getNombreContinente () {
        return this.nombreContinente;
    }

    public void setEjercito(Ejercito nuevoEjercito) {
        this.ejercito = nuevoEjercito;
    }



}
