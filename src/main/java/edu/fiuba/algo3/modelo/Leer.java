package edu.fiuba.algo3.modelo;


public class Leer {
    /*
    private String archivo;
    private List<Pais> paises ;
    private List<Continente> continentes ;

    public Moderador(String rutaArchivo){
        this.archivo = rutaArchivo;
        List<Pais> paisesLeidos =  new ArrayList<>();
        List<Continente> continentesLeidos = new ArrayList<>();
        cargarArchivo(archivo,paisesLeidos,continentesLeidos);
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

    //para que juego pueda recibir el listado de paises del archivo
    public List<Pais> pedirPaises(){
        return (this.paises);
    }
    //para que juego pueda recibir el listado de continentes del archivo
    public List<Continente> pedirContinentes(){
        return (this.continentes);
    }
    */
}
