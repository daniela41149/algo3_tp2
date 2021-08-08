package edu.fiuba.algo3.modelo.tarjetaPais;

public class Figura implements Simbolo{
    private String simbolo;

    public Figura (String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean esElMismoSimbolo(Simbolo simbolo) {
        return simbolo.esElMismo(this.simbolo);
    }
    public boolean sonSimbolosDiferentes(Simbolo simbolo){
        return simbolo.sonDiferentes(this.simbolo);
    }

    public boolean esElMismo(String nombreSimbolo) {
        return this.simbolo.equals(nombreSimbolo);
    }
    public boolean sonDiferentes(String nombreSimbolo) {
        return !this.simbolo.equals(nombreSimbolo);
    }




}