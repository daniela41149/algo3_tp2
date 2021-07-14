package edu.fiuba.algo3;


import java.util.ArrayList;
import java.lang.String;
import java.util.List;
import java.io.*;

public class Tablero {
    //private private List<Continente> continentes;
    private List<Pais> paises;


    /*atacar(string string cant ejercito)*/

    private List<Integer> leerArchivo (String rutaArchivo){
        List<Integer> listaPaises = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(rutaArchivo));
            String unRenglon = br.readLine();
            while(unRenglon != null)
            {
                String[] parts = unRenglon.split(",");
                String pais = parts[0];
                String continente = parts[1];
                List<String> nombrePaisesLimitrofes = parts[2];
                Pais nuevoPais = new Pais(pais, continente, nombrePaisesLimitrofes);
                listaPaises.add (nuevoPais);
                unRenglon = br.readLine();
            }
        }
        finally {
                if (br != null)
                    br.close();
        }
        return listaPaises;
    }

    public Tablero(String rutaArchivo){
        //this.continentes = ;
        this.paises = leerArchivo(rutaArchivo) ;
    }

}
