package edu.fiuba.algo3.modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Moderador {

    static final int EJERCITOS_ADICIONALES_ASIA = 7;
    static final int EJERCITOS_ADICIONALES_EUROPA = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE = 5;
    static final int EJERCITOS_ADICIONALES_AMERICA_DEL_SUR = 3;
    static final int EJERCITOS_ADICIONALES_AFRICA = 3;
    static final int EJERCITOS_ADICIONALES_OCEANIA = 2;

    private List<Pais> paises;
    private List<Continente> continentes;
    private List<TarjetaPais> tarjetas;

    public Moderador(String rutaArchivoFronteras, String rutaArchivoTarjetas){
        List<Pais> paisesLeidos =  new ArrayList<>();
        List<Continente> continentesLeidos = new ArrayList<>();
        List<TarjetaPais> tarjetasLeidas = new ArrayList<>();

        cargarArchivoFronteras(rutaArchivoFronteras, paisesLeidos, continentesLeidos);
        cargarArchivoTarjetas(rutaArchivoTarjetas, tarjetasLeidas);

        this.tarjetas = tarjetasLeidas;
        this.paises = paisesLeidos;
        this.continentes = continentesLeidos;
    }

    private HashMap<String, Integer> establecerEjercitosAdicionalesSegunContinentes() {
        HashMap<String, Integer> ejercitosSegunContinente = new HashMap<>();
        ejercitosSegunContinente.put("Asia", EJERCITOS_ADICIONALES_ASIA);
        ejercitosSegunContinente.put("Europa", EJERCITOS_ADICIONALES_EUROPA);
        ejercitosSegunContinente.put("America del Norte", EJERCITOS_ADICIONALES_AMERICA_DEL_NORTE);
        ejercitosSegunContinente.put("America del Sur", EJERCITOS_ADICIONALES_AMERICA_DEL_SUR);
        ejercitosSegunContinente.put("Africa", EJERCITOS_ADICIONALES_AFRICA);
        ejercitosSegunContinente.put("Oceania", EJERCITOS_ADICIONALES_OCEANIA);
        return ejercitosSegunContinente;
    }

    private void cargarArchivoFronteras(String direccionArchivo, List<Pais> paises, List<Continente> continentes) {
        String renglon;
        HashMap<String,List<Pais>> diccionarioContinentes = new HashMap<>();
        HashMap<String, Integer> ejercitosSegunContinente = establecerEjercitosAdicionalesSegunContinentes();
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
        } catch (IOException e) {//SACARLOS
            e.printStackTrace();
        }
        Set<String> listaContinentes = diccionarioContinentes.keySet();
        for( String nombreContinente : listaContinentes){
            List<Pais> listaPaisesPorContinente = diccionarioContinentes.get(nombreContinente);
            Continente nuevoContinente = new Continente(nombreContinente,listaPaisesPorContinente, ejercitosSegunContinente.get(nombreContinente));
            continentes.add(nuevoContinente);
        }
    }

    private void cargarArchivoTarjetas(String direccionArchivo, List<TarjetaPais> tarjetas) {
        String json = "";
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(direccionArchivo));

            String linea;
            while ((linea = buffer.readLine()) != null) {
                json += linea;
            }

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();

        final Type tipoListaTarjetas = new TypeToken<List<TarjetaPais>>(){}.getType();
        tarjetas.addAll(gson.fromJson(json, tipoListaTarjetas));
    }

    //para que juego pueda recibir el listado de paises del archivo
    public List<Pais> pedirPaises(){
        return (this.paises);
    }
    //para que juego pueda recibir el listado de continentes del archivo
    public List<Continente> pedirContinentes(){
        return (this.continentes);
    }

    public List<TarjetaPais> pedirTarjetasPais(){
        return (this.tarjetas);
    }

}
