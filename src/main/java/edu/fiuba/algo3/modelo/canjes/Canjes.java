package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.util.ArrayList;
import java.util.List;

public class Canjes {
    static final int CONST1 = 1;
    static final int TARJETAS_POR_CANJE = 3;
    private List<Canje> listaDeCanjes;

    public Canjes () {
        listaDeCanjes = new ArrayList<>();
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
        Canje nuevoCanje = new Canje(numeroDeCanje,this);
        listaDeCanjes.add(nuevoCanje);
    }

    private boolean agregarACanjeLasTarjetasPaisVerificadas (List<String> nombresTarjetasPaisParaCanjear, List<TarjetaPais> tarjetasPais) {
        if (tarjetasPais.size() >= TARJETAS_POR_CANJE) {
            this.agregarNuevoCanje();
            for (String nombreTarjetaPais: nombresTarjetasPaisParaCanjear) {
                TarjetaPais tarjetaPaisEncontrada = buscarTarjetaPais(nombreTarjetaPais, tarjetasPais);
                if (tarjetaPaisEncontrada == null) {
                    listaDeCanjes.remove(listaDeCanjes.get(listaDeCanjes.size()-CONST1));
                    return false;
                }
                listaDeCanjes.get(listaDeCanjes.size()-CONST1).agregarTarjetaPaisParaCanjear(tarjetaPaisEncontrada);
            }
            return true;
        }
        return false;
    }



    public void solicitarUnCanje(List<String> nombresTarjetasPaisParaCanjear, Jugador jugador, List<TarjetaPais> tarjetasPais) {
        if (agregarACanjeLasTarjetasPaisVerificadas (nombresTarjetasPaisParaCanjear, tarjetasPais)) {
            boolean solicitarCanje = listaDeCanjes.get(listaDeCanjes.size()-CONST1).solicitarUnCanje(jugador, tarjetasPais);
            if (!solicitarCanje) {
                listaDeCanjes.remove(listaDeCanjes.get(listaDeCanjes.size()-CONST1));
            }
        }
        return;
    }

    private void borrarTarjeta (TarjetaPais tarjetaParaBorrar, List<TarjetaPais> tarjetasPais) {
        for (TarjetaPais unaTarjetaPais: tarjetasPais){
            if (tarjetaParaBorrar.getNombre().equals(unaTarjetaPais.getNombre())){
                tarjetasPais.remove(unaTarjetaPais);
                return;
            }
        }
    }

    public void devolverTarjetasAlMazo(Jugador jugador, List<TarjetaPais> tarjetasPaisParaDevolverAlMazo, List<TarjetaPais> tarjetasPais) {
        for (TarjetaPais unaTarjetaParaDevolver: tarjetasPaisParaDevolverAlMazo) {
            borrarTarjeta(unaTarjetaParaDevolver,tarjetasPais);
        }
        jugador.devolverTarjetasAlMazo(tarjetasPaisParaDevolverAlMazo);
    }








}
