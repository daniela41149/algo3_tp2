package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;

import java.util.List;

public class Ejercito {

    int cantidadDeFichas;
    Dados dados;

    public Ejercito() {

        this.cantidadDeFichas = 0;
        dados = new Dados();
    }

    public Ejercito(int unaCantidadDeFichas) {

        this.dados = new Dados();
        this.cantidadDeFichas = unaCantidadDeFichas;
    }

    public void sacarFicha() {

        if (cantidadDeFichas > 0)
            this.cantidadDeFichas--;
    }

    public void sacarFichas(int unaCantidadDeFichas) {

        if (unaCantidadDeFichas <= this.cantidadDeFichas)
            this.cantidadDeFichas = this.cantidadDeFichas - unaCantidadDeFichas;
        else
            this.cantidadDeFichas = 0;
    }

    public void agregarFicha() {

        this.cantidadDeFichas++;
    }

    public void agregarFichas(int unaCantidadDeFichas) {

        this.cantidadDeFichas = this.cantidadDeFichas + unaCantidadDeFichas;
    }

    public boolean quedoSinEjercito() {

        return (this.cantidadDeFichas == 0);
    }

    public List<Integer> atacar(int unaCantidadDeFichas) throws CantidadInvalidaDeEjercitosException {
        if (this.cantidadDeFichas <= 1) {
            throw new CantidadInvalidaDeEjercitosException();
        }
        return dados.dadosAtaque(unaCantidadDeFichas);
    }

    public List<Integer> defender() {

        return dados.dadosDefensa(this.cantidadDeFichas);
    }

    public int devolverCantidadDeFichas() {
        return this.cantidadDeFichas;
    }


    public void setDados(Dados nuevosDados) {
        this.dados = nuevosDados;
    }
}