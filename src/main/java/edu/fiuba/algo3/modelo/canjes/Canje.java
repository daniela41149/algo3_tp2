package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.util.ArrayList;
import java.util.List;

public class Canje {
    static final int CONST1 = 1;
    static final int PRIMER_CANJE = 1;
    static final int SEGUNDO_CANJE = 2;
    static final int PRIMER_CANJE_EJERCITOS = 4;
    static final int SEGUNDO_CANJE_EJERCITOS = 7;
    static final int EJERCITOS = 5;

    private int ejercitos;
    private int numeroDeCanje;
    private List<TarjetaPais> tarjetasPaisParaCanjear = new ArrayList<>();
    private Canjes canjes;

    public Canje (int numeroDeCanje, Canjes canjes) {
        this.numeroDeCanje = numeroDeCanje;
        this.canjes = canjes;
        calcularEjercitosSegunNumeroDeCanje(numeroDeCanje);
    }

    public void agregarTarjetaPaisParaCanjear(TarjetaPais tarjetaPais) {
        tarjetasPaisParaCanjear.add(tarjetaPais);
    }

    private void desactivarTarjetas (List<TarjetaPais> tarjetasPais) {
        for (TarjetaPais unaTarjeta: tarjetasPais) {
            unaTarjeta.desactivar();
        }

    }

    private void calcularEjercitosSegunNumeroDeCanje (int numeroDeCanje) {
        if (numeroDeCanje == PRIMER_CANJE) {
            this.ejercitos = PRIMER_CANJE_EJERCITOS;
        }
        if (numeroDeCanje == SEGUNDO_CANJE) {
            this.ejercitos = SEGUNDO_CANJE_EJERCITOS;
        }
        if (numeroDeCanje > SEGUNDO_CANJE) {
            this.ejercitos = (numeroDeCanje-CONST1)*EJERCITOS;
        }
    }

    public boolean solicitarUnCanje(Jugador jugador, List<TarjetaPais> tarjetasPais) {
        if(tarjetasPaisParaCanjear.get(0).esElMismoSimbolo(tarjetasPaisParaCanjear.get(1), tarjetasPaisParaCanjear.get(2)) || tarjetasPaisParaCanjear.get(0).sonSimbolosDiferentes(tarjetasPaisParaCanjear.get(1), tarjetasPaisParaCanjear.get(2) ))  {
            this.desactivarTarjetas(tarjetasPaisParaCanjear);
            canjes.devolverTarjetasAlMazo(jugador,tarjetasPaisParaCanjear,tarjetasPais);
            jugador.entregarEjercitosDeCanje(ejercitos);
            return true;
        }
        else {
            return false;
        }

    }




}
