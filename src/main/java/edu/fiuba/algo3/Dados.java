package edu.fiuba.algo3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dados {
    
    List<Integer> listaDados;

    public Dados(){
    }

    private List<Integer> lanzarDados( int cantidadDeDados ){

        List<Integer> listaDados = new ArrayList<>();
        Random numeroDado= new Random();
        for( int i = 1; i <= cantidadDeDados; i++){
            listaDados.add( 1+numeroDado.nextInt(6) );
        }
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
