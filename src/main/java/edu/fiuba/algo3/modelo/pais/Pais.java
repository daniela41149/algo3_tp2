package edu.fiuba.algo3.modelo.pais;

import edu.fiuba.algo3.modelo.Ejercito;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.List;

public class Pais {
    private String nombrePais;
    private List<String> nombrePaisesLimitrofes;
    private Jugador dueño;
    private Ejercito ejercito;
    private EstadoPais estadoPais;


    public Pais(String nombrePais, List<String> nombrePaisesLimitrofes) {
        this.nombrePais = nombrePais;
        this.nombrePaisesLimitrofes = nombrePaisesLimitrofes;
        this.ejercito = new Ejercito();
        this.estadoPais = new Vacante();
    }

    public boolean esLimitrofe(Pais unPais) {
        for (String limitrofe: nombrePaisesLimitrofes) {
            if (unPais.getNombre().equals(limitrofe)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> atacar(Pais paisDefensa, int cantidadEjercito) throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        if(paisDefensa.esLimitrofe(this))
            return (ejercito.atacar(cantidadEjercito));
        else  {
            throw new JugadaInvalidaException();
        }
    }

    public List<Integer> defender() {
        return (ejercito.defender());
    }

    public void sacarFichas(int cantidad) {
        ejercito.sacarFichas(cantidad);
    }

    public Boolean coincideNombre(String nombre) {
        return nombre.equals(this.nombrePais);
    }

    public boolean esElDueño(Jugador jugador) {
        if(this.dueño == null){
            return false;
        }
        return this.dueño.esElMismo(jugador);
    }

    public Jugador nuevoDueño() {
        return this.dueño;
    }

    public void elegirPais (Jugador jugador) {
        this.dueño = jugador;
    }

    public void cambiarEstadoAOcupado () {
        this.estadoPais = new Ocupado();
    }


    public void establecerDueño (Pais paisAtacante) {
        if (ejercito.quedoSinEjercito()) {
            this.dueño.desocupar(this.nombrePais);
            this.dueño.establecerPosibleDestructor(paisAtacante.nuevoDueño());
            this.dueño = paisAtacante.nuevoDueño();
            this.dueño.agregarPais(this);
            paisAtacante.ejercito.sacarFicha();
            this.ejercito.agregarFicha();
            this.dueño.pedirTarjetaPais();

        }
    }

    public void colocarEjercito (Jugador jugador, int cantidadEjercito) {
        this.estadoPais.ocuparPais(jugador,this);
        if (this.dueño.esElMismo(jugador)){
            ejercito.agregarFichas(cantidadEjercito);
        }
    }

    public int cantidadDeFichas() {return ejercito.devolverCantidadDeFichas();}


    public String getNombre () {
        return this.nombrePais;
    }


    public Ejercito getEjercito() {
        return this.ejercito;
    }



}
