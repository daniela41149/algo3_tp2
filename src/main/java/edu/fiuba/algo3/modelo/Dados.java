package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dados {
    

    static final int MIN_VALOR_DADO = 1;
    static final int MAX_VALOR_DADO = 6;
    List<Integer> listaDados;

    public Dados(){
    }

    private List<Integer> lanzarDados( int cantidadDeDados ){

        List<Integer> listaDados = new ArrayList<>();
        Random numeroDado= new Random();
        for( int i = 1; i <= cantidadDeDados; i++)
            listaDados.add( MIN_VALOR_DADO + numeroDado.nextInt( MAX_VALOR_DADO ) );
        return listaDados;
    }

    public List<Integer> dadosAtaque(int cantidadDeDados){

        if ( (cantidadDeDados > 1) && (cantidadDeDados <= 4) )
            return lanzarDados(cantidadDeDados-1);
        if (cantidadDeDados > 4)
            return lanzarDados(3);
        return lanzarDados(0);
        
    }

    public List<Integer> dadosDefensa(int cantidadDeDados){
        
        if ( (cantidadDeDados >= 1) && (cantidadDeDados < 3) )
            return lanzarDados(cantidadDeDados+1);
        if ( cantidadDeDados >= 3 )
            return lanzarDados(3);
        return lanzarDados(0);
    }
}
