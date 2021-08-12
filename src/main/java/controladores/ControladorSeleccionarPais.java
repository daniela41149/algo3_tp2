package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ControladorSeleccionarPais {

    private Juego juego;
    private HashMap<String, Integer> limitrofesConEjercitos;
    private HashMap<String, Integer> paisesConEjercitos;
    private int ejercito;
    private String limitrofeSeleccionado;
    private String nombrePais;
    private Moderador moderador;



    @FXML
    private Label labelPais1;

    @FXML
    private Label labelPais2;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonVolver;

    @FXML
    private ListView<String> listaLimitrofes;

    @FXML
    private ListView<String> listaPaises;



    Stage escenarioReagrupar = new Stage();
    Stage escenarioDados = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;



    public void seleccionarPais(String nombrePais, int cantEjercito, Juego juego, Moderador moderador, HashMap<String, Integer> limitrofesConEjercitos,ListView<String> listaPaises) {
        this.juego = juego;
        this.moderador = moderador;
        this.nombrePais = nombrePais;
        this.ejercito = cantEjercito;
        this.limitrofesConEjercitos = limitrofesConEjercitos;
        this.listaPaises = listaPaises;
        labelPais1.setText(nombrePais);
    }


    @FXML
    void seleccionarLimitrofe(MouseEvent event) throws IOException{
        limitrofeSeleccionado = listaLimitrofes.getSelectionModel().getSelectedItem();
        labelPais2.setText(nombre(limitrofeSeleccionado));
    }

    @FXML
    void reagrupar(ActionEvent event) throws IOException{
        levantarVentanaReagruparEjercitos();
        ControladorReagruparEjercitos controladorReagruparEjercitos = obtenerControladorReagruparEjercitos();
        int ejercitoDesde = buscarPais(nombrePais).cantidadDeFichas();
        int ejercitoHasta = buscarPais(nombre(limitrofeSeleccionado)).cantidadDeFichas();
        controladorReagruparEjercitos.reagrupar(juego,moderador, nombrePais,ejercitoDesde,nombre(limitrofeSeleccionado),ejercitoHasta,listaLimitrofes,limitrofesConEjercitos);
    }

    @FXML
    void atacar(ActionEvent event) throws IOException{
        Integer fichasAtaque = buscarPais(nombrePais).cantidadDeFichas();
        Integer fichasDefensa = buscarPais(nombre(limitrofeSeleccionado)).cantidadDeFichas();
        levantarVentanaDados();
        ControladorDados controladorDados = obtenerControladorDados();
        controladorDados.asignarFichas(fichasAtaque, fichasDefensa);

    }

    @FXML
    void volver(ActionEvent event) throws IOException{
        listaPaises.getItems().clear();
        refrescarDatosEnPantalla();
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.close();
    }


    private String nombre(String cadena){
        cadena = cadena.replaceAll("[0-9]","");
        return cadena.substring(0, cadena.length() - 2);
    }





    public void levantarVentanaReagruparEjercitos() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaReagruparEjercitos.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioReagrupar.setTitle("Reagrupar Ejercitos");
        escenarioReagrupar.setScene(scene);
        escenarioReagrupar.show();
    }

    public void levantarVentanaDados() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaDados.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioDados.setTitle("Batalla");
        escenarioDados.setScene(scene);
        escenarioDados.show();
    }

    public ControladorDados obtenerControladorDados() {
        ControladorDados controladorDados = (ControladorDados)loader.getController();
        return controladorDados;
    }

    public ControladorReagruparEjercitos obtenerControladorReagruparEjercitos() {
        ControladorReagruparEjercitos controladorReagruparEjercitos = (ControladorReagruparEjercitos) loader.getController();
        return controladorReagruparEjercitos;
    }

    private void refrescarDatosEnPantalla(){
        mostrarPaisesActuales();
    }

    private void mostrarPaisesActuales(){
        paisesConEjercitos = nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaPaises.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    public void mostrarLimitrofesParaAtacar() {
        mostrarLimitrofesActualesParaAtacar(nombrePais);
    }
    public void mostrarLimitrofesParaReagrupar() {
        mostrarLimitrofesActualesParaReagrupar(nombrePais);
    }

    private void mostrarLimitrofesActualesParaReagrupar(String nombre){
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(nombre);
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    private void mostrarLimitrofesActualesParaAtacar(String nombre) {
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(nombre);
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = moderador.pedirPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paises) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = moderador.pedirPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paises) {
            if (unPais.esLimitrofe(paisBuscado) && !juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }



    private Pais buscarPais (String nombrePaisBuscado) {
        List<Pais> paises = moderador.pedirPaises();
        for (Pais unPais: paises) {
            if (unPais.getNombre().equals((nombrePaisBuscado)))
                return unPais;
        }
        return null;
    }

    private HashMap<String, Integer> nombrePaisesYEjercitosDeJugadorActual (){
        List<Pais> listaPaises = juego.jugadorEnTurno().pedirPaises();
        HashMap<String,Integer> diccionario = new HashMap<>();
        for(Pais unPais: listaPaises){
            diccionario.put(unPais.getNombre(),unPais.cantidadDeFichas());
        }
        return diccionario;
    }
}
