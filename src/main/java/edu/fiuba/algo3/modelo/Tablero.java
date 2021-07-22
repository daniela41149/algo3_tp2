package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;

import java.util.*;
import java.lang.String;
import java.io.*;

public class Tablero {
    private List<Pais> paises ;
    private List<Continente> continentes ;
    private Batalla batalla;


    public Tablero (List<Pais> paises, List<Continente> continentes ){
        this.paises = paises ;
        this.continentes = continentes;
    }

    public List<Pais> pasarPiasesAJuego(){
        return( this.paises);
    }

    public Pais buscarPais(String nombrePais){
        Pais paisBuscado = null;
        for(Pais pais: paises){
            if(pais.coincideNombre(nombrePais)){
                paisBuscado = pais;
                return paisBuscado;
            }
        }
        return paisBuscado;
    }

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor,int cantEjercito)throws JugadaInvalidaException {
        Pais paisAtacante = buscarPais(nombrePaisAtacante) ;
        Pais paisDefensor = buscarPais(nombrePaisDefensor) ;
        if((paisAtacante == null) || (paisDefensor == null)  ){
            throw new JugadaInvalidaException();
        }
        this.batalla = new Batalla(paisAtacante,paisDefensor);
        batalla.atacar(cantEjercito);
    }

    public int cantidadPaises(){
        return (this.paises.size());
    }
    public int cantidadContinentes(){
        return (this.continentes.size());
    }



}


 /*
    public Tablero(String rutaArchivo){
        List<Pais> paisesLeidos =  new ArrayList<>();
        List<Continente> continentesLeidos = new ArrayList<>();
        cargarArchivo(rutaArchivo,paisesLeidos ,continentesLeidos);
        this.paises = paisesLeidos;
        this.continentes = continentesLeidos;
    }


    private void cargarArchivo(String direccionArchivo, List<Pais> paises, List<Continente> continentes) {
        String renglon;
        HashMap<String,List<Pais>> diccionarioContinentes = new HashMap<>();


        try {
            File archivo = new File (direccionArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            renglon = br.readLine();

            while(renglon != null){

                String renglonSinComillas = renglon.replaceAll("\"", "");
                List<String> parts = Arrays.asList(renglonSinComillas.split(",", 3));
                String nombrePais = parts.get(0);
                String continente = parts.get(1);
                List<String> nombrePaisesLimitrofes = Arrays.asList(parts.get(2));

                Pais nuevoPais = new Pais(nombrePais, nombrePaisesLimitrofes);
                paises.add(nuevoPais);

                List<Pais> listaPaises = new ArrayList<>();
                if(diccionarioContinentes.containsKey(continente)){
                    listaPaises = diccionarioContinentes.get(continente);
                }
                listaPaises.add(nuevoPais);
                diccionarioContinentes.put(continente,listaPaises);

                renglon = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> listaContinentes = diccionarioContinentes.keySet();
        for( String nombreContinente : listaContinentes){
            List<Pais> listaPaisesPorContinente = diccionarioContinentes.get(nombreContinente);
            Continente nuevoContinente = new Continente(nombreContinente,listaPaisesPorContinente);
            continentes.add(nuevoContinente);

        }

    }

    iterador de buscar pais
    boolean paisEncontrado = false;
        int i = 0;
        Pais paisBuscado = null;
        while( (!paisEncontrado) && (i < paises.size()) ){
            paisBuscado = paises.get(i);
            if(paisBuscado.coincideNombre(nombrePais)){
                paisEncontrado = true;
            }
            i++;
        }
        return paisBuscado;

    */

