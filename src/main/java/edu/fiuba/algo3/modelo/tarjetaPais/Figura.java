package edu.fiuba.algo3.modelo.tarjetaPais;

public class Figura implements Simbolo{
    private String simbolo;

    public Figura (String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean esElMismoSimbolo(Simbolo simbolo) {
        return simbolo.esElMismoSimbolo(this.simbolo);
    }
    public boolean sonSimbolosDiferentes(Simbolo simbolo){
        return simbolo.sonSimbolosDiferentes(this.simbolo);
    }

    public boolean esElMismoSimbolo(String nombreSimbolo) {
        return this.simbolo.equals(nombreSimbolo);
    }
    public boolean sonSimbolosDiferentes(String nombreSimbolo) {
        return !this.simbolo.equals(nombreSimbolo);
    }
}