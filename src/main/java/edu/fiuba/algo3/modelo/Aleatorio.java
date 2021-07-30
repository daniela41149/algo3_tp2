package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.pais.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aleatorio {


    public Aleatorio(){

    }

    public List<List<Pais>> repartirPaisesAleatoriamente (int cantidadDeJugadores, List<Pais> paisesSinRepartir) {
        Random rand = new Random();

        List<List<Pais>> listasPaisesRepartidos = new ArrayList<>();

        for (int i = 0; i < cantidadDeJugadores; i++) {
            List<Pais> listaPaises = new ArrayList<>();
            listasPaisesRepartidos.add(listaPaises);
        }


        while (!paisesSinRepartir.isEmpty()){
            for(int i = 0; i < cantidadDeJugadores; i++) {
                if (!paisesSinRepartir.isEmpty()){
                    int posicionAleatoria = rand.nextInt(paisesSinRepartir.size());
                    Pais paisAleatorio = paisesSinRepartir.get(posicionAleatoria);
                    List<Pais> listaPaisesJugador = listasPaisesRepartidos.get(i);
                    listaPaisesJugador.add(paisAleatorio);
                    paisesSinRepartir.remove(paisAleatorio);
                }
            }
        }

        return listasPaisesRepartidos;
    }

    public int elegirPosicionDelJugadorQueEmpieza(int numeroDeJugadores){
        Random rand = new Random();

        return (rand.nextInt(numeroDeJugadores));
    }

    public TarjetaPais agarrarTajetaPaisAleatoriaDelMazo(List<TarjetaPais> mazoDeTarjetas) {
        Random rand = new Random();

        int posicionDeTarjetaRandom = rand.nextInt(mazoDeTarjetas.size());
        TarjetaPais tarjetaRandom = mazoDeTarjetas.get(posicionDeTarjetaRandom);
        mazoDeTarjetas.remove(posicionDeTarjetaRandom);

        return tarjetaRandom;
    }
}
