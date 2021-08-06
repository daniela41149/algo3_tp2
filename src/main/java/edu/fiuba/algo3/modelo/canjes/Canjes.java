package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.util.ArrayList;
import java.util.List;

public class Canjes {
    static final int CONST1 = 1;
    private List<Canje> listaDeCanjes;
    private int posicion;

    public Canjes () {
        listaDeCanjes = new ArrayList<>();
        posicion = 0;

    }

    private TarjetaPais buscarTarjetaPais (String nombreTarjetaPais, List<TarjetaPais> tarjetasPais){
        for (TarjetaPais unaTarjetaPais: tarjetasPais){
            if (unaTarjetaPais.getNombre().equals(nombreTarjetaPais)){
                return unaTarjetaPais;
            }
        }
        return null;
    }

    private void agregarNuevoCanje () {
        int numeroDeCanje = listaDeCanjes.size()+CONST1;
        Canje nuevoCanje = new Canje(numeroDeCanje);
        listaDeCanjes.add(nuevoCanje);
    }



    public void solicitarUnCanje(List<String> nombresTarjetasPaisParaCanjear, Jugador jugador, List<TarjetaPais> tarjetasPais) {
        this.agregarNuevoCanje();
        for (String nombreTarjetaPais: nombresTarjetasPaisParaCanjear) {
            TarjetaPais tarjetaPaisEncontrada = buscarTarjetaPais(nombreTarjetaPais, tarjetasPais);
            listaDeCanjes.get(listaDeCanjes.size()-CONST1).agregarTarjetaPaisParaCanjear(tarjetaPaisEncontrada);
        }
        if (!listaDeCanjes.get(listaDeCanjes.size()-CONST1).solicitarUnCanje(jugador)) {
            listaDeCanjes.remove(listaDeCanjes.get(listaDeCanjes.size()-CONST1));
        }



    }








}
