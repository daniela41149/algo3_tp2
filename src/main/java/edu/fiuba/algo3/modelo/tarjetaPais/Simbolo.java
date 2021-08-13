package edu.fiuba.algo3.modelo.tarjetaPais;

public interface Simbolo {

    public boolean esElMismoSimbolo(Simbolo simbolo);
    public boolean sonSimbolosDiferentes(Simbolo simbolo);

    public boolean esElMismoSimbolo(String nombreSimbolo);
    public boolean sonSimbolosDiferentes(String nombreSimbolo);
}