package edu.fiuba.algo3.modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaDestruccion;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaOcupacion;
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

    static final String RUTA_FRONTERAS = "resources/Fronteras.csv";
    static final String RUTA_TARJETAS_PAIS = "resources/TarjetasPais.json";
    static final String RUTA_OBJETIVOS_OCUPACION = "resources/ObjetivosDeOcupacion.json";
    static final String RUTA_OBJETIVOS_DESTRUCCION = "resources/ObjetivosDeDestruccion.json";

    private List<Pais> paises;
    private List<Continente> continentes;
    private List<TarjetaPais> tarjetasPais;
    private List<TarjetaObjetivo> tarjetasObjetivo;

    public Moderador() throws IOException {
        List<Pais> paisesLeidos =  new ArrayList<>();
        List<Continente> continentesLeidos = new ArrayList<>();
        List<TarjetaPais> tarjetasPaisLeidas = new ArrayList<>();
        List<TarjetaObjetivo> tarjetasObjetivoLeidas = new ArrayList<>();

        cargarArchivoFronteras(paisesLeidos, continentesLeidos);
        cargarArchivoTarjetasPais(tarjetasPaisLeidas);
        cargarArchivoTarjetasObjetivo(tarjetasObjetivoLeidas);

        this.paises = paisesLeidos;
        this.continentes = continentesLeidos;
        this.tarjetasPais = tarjetasPaisLeidas;
        this.tarjetasObjetivo = tarjetasObjetivoLeidas;
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

    private void cargarArchivoFronteras(List<Pais> paises, List<Continente> continentes) throws IOException {
        String renglon;
        HashMap<String,List<Pais>> diccionarioContinentes = new HashMap<>();
        HashMap<String, Integer> ejercitosSegunContinente = establecerEjercitosAdicionalesSegunContinentes();

            File archivo = new File (RUTA_FRONTERAS);
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

        Set<String> listaContinentes = diccionarioContinentes.keySet();
        for( String nombreContinente : listaContinentes){
            List<Pais> listaPaisesPorContinente = diccionarioContinentes.get(nombreContinente);
            Continente nuevoContinente = new Continente(nombreContinente,listaPaisesPorContinente, ejercitosSegunContinente.get(nombreContinente));
            continentes.add(nuevoContinente);
        }
    }

    private String leerJson(String rutaArchivo) {
        String jsonLeido = "";

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            while ((linea = buffer.readLine()) != null) {
                jsonLeido += linea;
            }

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonLeido;
    }

    private void cargarArchivoTarjetasPais(List<TarjetaPais> tarjetas) {
        Gson gson = new Gson();

        String jsonLeido = leerJson(RUTA_TARJETAS_PAIS);
        final Type tipoListaTarjetas = new TypeToken<List<TarjetaPais>>(){}.getType();
        tarjetas.addAll(gson.fromJson(jsonLeido, tipoListaTarjetas));
    }

    private void cargarArchivoTarjetasObjetivo(List<TarjetaObjetivo> tarjetas){
        Gson gson = new Gson();

        String jsonLeidoDeOcupacion = leerJson(RUTA_OBJETIVOS_OCUPACION);
        String jsonLeidoDeDestruccion = leerJson(RUTA_OBJETIVOS_DESTRUCCION);
        final Type tipoListaObjetivosOcupacion = new TypeToken<List<TarjetaOcupacion>>(){}.getType();
        final Type tipoListaObjetivosDestruccion = new TypeToken<List<TarjetaDestruccion>>(){}.getType();
        tarjetas.addAll(gson.fromJson(jsonLeidoDeOcupacion, tipoListaObjetivosOcupacion));
        tarjetas.addAll(gson.fromJson(jsonLeidoDeDestruccion, tipoListaObjetivosDestruccion));
    }


    public List<Pais> pedirPaises(){
        return (this.paises);
    }

    public List<Continente> pedirContinentes(){
        return (this.continentes);
    }

    public List<TarjetaPais> pedirTarjetasPais(){
        return (this.tarjetasPais);
    }

    public List<TarjetaObjetivo> pedirTarjetasObjetivo() { return (this.tarjetasObjetivo); }
}
