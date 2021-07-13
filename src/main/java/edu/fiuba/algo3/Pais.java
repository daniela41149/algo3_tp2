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

    public Boolean esLimitrofe(Pais paisAtacante) {
        for (String limitrofe: nombrePaisesLimitrofes) {
            if (paisAtacante.getNombre().equals(limitrofe)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> atacar(Pais paisDefensa, int cantidadEjercito) {
        if(paisDefensa.esLimitrofe(this)){
            return (ejercito.atacar(cantidadEjercito));
        } else { return null; }
    }

    public List<Integer> defender() {
        return (ejercito.defender());
    }

    /*
        public void sacarFicha(int cantidad) {
            ejercito.sacarFicha(cantidad);
        }

        public void colocarEjercito (Jugador jugador, int cantidadEjercito) {
        }

        public Jugador nuevoDueño () {
            return this.dueño;
        }

        public void establecerDueño (Pais paisAtacante) {
            if (ejercito.quedoSinEjercito()) {
                this.dueño.desocupar();
                this.dueño = paisAtacante.nuevoDueño();
        }
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
