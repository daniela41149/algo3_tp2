package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aleatorio {


    public Aleatorio(){

    }

    public List<List<Pais>> repartirPaisesAleatoriamente (int cantidadDeJugadores,List<Pais> paisesSinRepartir) {
        Random rand = new Random();

        int cantidadPorJugador = (paisesSinRepartir.size()/cantidadDeJugadores);

        List<List<Pais>> listasPaisesRepartidos = new ArrayList<>();

        for(int i = 0; i < cantidadDeJugadores; i++) {
            List<Pais>  listaPaises = new ArrayList<>();
            for (int j = 0; j < cantidadPorJugador; j++) {
                int posicionAleatoria = rand.nextInt(paisesSinRepartir.size());
                Pais paisAleatorio = paisesSinRepartir.get(posicionAleatoria);
                listaPaises.add(paisAleatorio);
                paisesSinRepartir.remove(paisAleatorio);
            }
            listasPaisesRepartidos.add(listaPaises);
        }

        return listasPaisesRepartidos;
    }

    public int elegirPosicionDelJugadorQueEmpieza(int numeroDeJugadores){
        Random rand = new Random();

        return (rand.nextInt(numeroDeJugadores));
    }
}
