package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dados {
    

    static final int MIN_VALOR_DADO = 1;
    static final int MAX_VALOR_DADO = 6;



    List<Integer> listaDados;

    public Dados(){
    }


    public List<Integer> tirarDados( int cantidadDeDados ){

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

}
