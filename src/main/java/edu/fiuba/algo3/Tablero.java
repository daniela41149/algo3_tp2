package edu.fiuba.algo3;


import java.util.ArrayList;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Set;

public class Tablero {
    private List<Pais> paises = new ArrayList<>();
    private List<Continente> continentes = new ArrayList<>();
    private Batalla batalla;


    private void cargarArchivo(String direccionArchivo) {
        String renglon;
        HashMap<String,List<Pais>> diccionarioContinentes = new HashMap<>();


        try {
            File archivo = new File (direccionArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            renglon = br.readLine();

            while(renglon != null){

                String[] parts = renglon.split(",", 3);
                String nombrePais = parts[0];
                String continente = parts[1];
                List<String> nombrePaisesLimitrofes = parts[2];
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
            System.out.println(e);
            e.printStackTrace();
        }
        Set<String> listaContinentes = diccionarioContinentes.keySet();
        for( String nombreContinente : listaContinentes){
            List<Pais> listaPaisesPorContinente = diccionarioContinentes.get(nombreContinente);
            Continente nuevoContinente = new Continente(nombreContinente,listaPaisesPorContinente);
            continentes.add(nuevoContinente);

        }

    }

    public Tablero(String rutaArchivo){
        cargarArchivo(rutaArchivo);
    }

    private Pais buscarPais(String nombrePais){
        boolean paisEncontrado = false;
        int i = 0;
        Pais paisBuscado;
        while( (!paisEncontrado) && (i < paises.size()) ){
            paisBuscado = paises[i];

            if(paisBuscado.coincideNombre(nombrePais)){
                paisEncontrado = true;
            }
            i++;
        }

        return paisBuscado;

    }

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor,int cantEjercito){
        Pais paisAtacante = buscarPais(nombrePaisAtacante) ;
        Pais paisDefensor = buscarPais(nombrePaisDefensor) ;
        Batalla batalla = new Batalla(paisAtacante,paisDefensor);
        batalla.atacar(cantEjercito);
        //nose si esta bien definida batalla

    }

}
