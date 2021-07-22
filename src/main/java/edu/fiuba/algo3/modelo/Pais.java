package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.List;

public class Pais {
    private String nombrePais;
    private List<String> nombrePaisesLimitrofes;
    private Jugador dueño;
    private Ejercito ejercito;
    private Estado estado;


    public Pais(String nombrePais, List<String> nombrePaisesLimitrofes) {
        this.nombrePais = nombrePais;
        this.nombrePaisesLimitrofes = nombrePaisesLimitrofes;
        this.ejercito = new Ejercito();
        this.estado = new Vacante();
    }

    public boolean esLimitrofe(Pais paisAtacante) {
        for (String limitrofe: nombrePaisesLimitrofes) {
            if (paisAtacante.getNombre().equals(limitrofe)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> atacar(Pais paisDefensa, int cantidadEjercito) throws JugadaInvalidaException {
        if(paisDefensa.esLimitrofe(this))
            return (ejercito.atacar(cantidadEjercito));
        else  {
            throw new JugadaInvalidaException();
        }
    }

    public List<Integer> defender() {
        return (ejercito.defender());
    }

    public void sacarFicha(int cantidad) {
        ejercito.sacarFicha(cantidad);
    }


    public Boolean coincideNombre(String nombre) {
        return nombre.equals(this.nombrePais);
    }



    public boolean esElDueño(Jugador jugador) {
        return this.dueño.esElMismo(jugador);
    }


    public Jugador nuevoDueño() {
        return this.dueño;
    }

    private void elegirPais (Jugador jugador) {
        this.dueño = jugador;
    }


    public void establecerDueño (Pais paisAtacante) {
        if (ejercito.quedoSinEjercito()) {
            this.dueño.desocupar(this.nombrePais);
            this.dueño = paisAtacante.nuevoDueño();
            this.dueño.agregarPais(paisAtacante);
        }
    }

    public void colocarEjercito (Jugador jugador, int cantidadEjercito) {
        if (estado.puedeOcupar()) {
            this.elegirPais(jugador);
            estado = new Ocupado();
        }
        if (this.dueño.esElMismo(jugador)){
            ejercito.agregarFichas(cantidadEjercito);
        }
    }



    public String getNombre () {
        return this.nombrePais;
    }



    public Ejercito getEjercito() {
        return this.ejercito;
    }



}
