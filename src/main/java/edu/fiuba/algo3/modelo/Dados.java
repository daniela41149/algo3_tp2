package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dados {
    

    static final int MIN_VALOR_DADO = 1;
    static final int MAX_VALOR_DADO = 6;
    static final int MAX_DADOS_DEFENSA = 3;
    static final int MAX_DADOS_ATAQUE = 3;
    static final int MIN_CANTIDAD_DADOS = 1;


    List<Integer> listaDados;

    public Dados(){
    }

    private List<Integer> lanzarDados( int cantidadDeDados ){

        List<Integer> listaDados = new ArrayList<>();
        Random numeroDado= new Random();
        for( int i = 1; i <= cantidadDeDados; i++)
            listaDados.add( MIN_VALOR_DADO + numeroDado.nextInt( MAX_VALOR_DADO ) );
        listaDados = ordenarDados(listaDados);
        return listaDados;
    }

    private List<Integer> ordenarDados(List<Integer> listaDados){
        listaDados.sort( Collections.reverseOrder() );
        return listaDados;
    }

    public List<Integer> dadosAtaque(int cantidadDeDados){

        if ( (cantidadDeDados >= MIN_CANTIDAD_DADOS) && (cantidadDeDados <= MAX_DADOS_ATAQUE) )
            return lanzarDados(cantidadDeDados);

        return lanzarDadosConCantidad(cantidadDeDados);
    }

    public List<Integer> dadosDefensa(int cantidadDeDados){

        if ( (cantidadDeDados >= MIN_CANTIDAD_DADOS) && (cantidadDeDados < MAX_DADOS_DEFENSA) )
            return lanzarDados(cantidadDeDados);

        return lanzarDadosConCantidad(cantidadDeDados);
    }

    private List<Integer> lanzarDadosConCantidad(int cantidadDeDados){

        if(cantidadDeDados >= MAX_DADOS_DEFENSA){
            return lanzarDados(3);
        }
        return lanzarDados(0);

    }
}
