package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
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
    private List<Pais> paisesEnTablero;
    private Button botonTarjetas;

    Stage escenarioReagrupar = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;

    @FXML
    private Label labelPais1;

    @FXML
    private Label labelPais2;

    @FXML
    private Label ejercitosDiponibles;

    @FXML
    private Button botonColocarEjercito;

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

    public void seleccionarPais(String nombrePais, int cantEjercito, Juego juego, List<Pais> paisesEnTablero, HashMap<String, Integer> limitrofesConEjercitos,ListView<String> listaPaises, Button botonTarjetas, Label labelEjercitosDisponibles, Button botonColocarEjercito) {
        this.juego = juego;
        this.paisesEnTablero = paisesEnTablero;
        this.nombrePais = nombrePais;
        this.ejercito = cantEjercito;
        this.limitrofesConEjercitos = limitrofesConEjercitos;
        this.listaPaises = listaPaises;
        this.botonTarjetas = botonTarjetas;
        this.ejercitosDiponibles = labelEjercitosDisponibles;
        this.botonColocarEjercito = botonColocarEjercito;

        labelPais1.setText(nombrePais);
    }

    @FXML
    void seleccionarLimitrofe(MouseEvent event) throws IOException{
        limitrofeSeleccionado = listaLimitrofes.getSelectionModel().getSelectedItem();
        if (limitrofeSeleccionado == null) {
            levantarVentana("/vista/ventanaNoEligioPais.fxml", "No Eligio Pais");
            return;
        }
        labelPais2.setText(nombre(limitrofeSeleccionado));
    }

    @FXML
    void reagrupar(ActionEvent event) throws IOException{
        if (limitrofeSeleccionado == null){
            levantarVentana("/vista/ventanaNoEligioPais.fxml", "No Eligio Pais");
            return;
        }
        levantarVentana("/vista/ventanaReagruparEjercitos.fxml","Reagrupar Ejercitos");
        ControladorReagruparEjercitos controladorReagruparEjercitos = obtenerControladorReagruparEjercitos();
        int ejercitoDesde = buscarPais(nombrePais).cantidadDeFichas();
        int ejercitoHasta = buscarPais(nombre(limitrofeSeleccionado)).cantidadDeFichas();
        controladorReagruparEjercitos.reagrupar(juego,paisesEnTablero, nombrePais,ejercitoDesde,nombre(limitrofeSeleccionado),ejercitoHasta,listaLimitrofes,limitrofesConEjercitos);
        limitrofeSeleccionado = null;
        labelPais2.setText("");
    }

    @FXML
    void atacar(ActionEvent event) throws IOException, JugadaInvalidaException{
        if (limitrofeSeleccionado == null){
            levantarVentana("/vista/ventanaNoEligioPais.fxml", "No Eligio Pais");
            return;
        }
        levantarVentana("/vista/ventanaDados.fxml","Batalla");
        ControladorDados controladorDados = obtenerControladorDados();

        Pais paisAtacante = buscarPais(nombrePais);
        Pais paisDefensor = buscarPais(nombre(limitrofeSeleccionado));

        controladorDados.atacar(juego, paisesEnTablero,paisAtacante, paisDefensor,listaLimitrofes, botonTarjetas, ejercitosDiponibles, botonColocarEjercito);
        limitrofeSeleccionado = null;
        labelPais2.setText("");
    }

    @FXML
    void volver(ActionEvent event) {
        listaPaises.getItems().clear();
        refrescarDatosEnPantalla();
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.close();
    }

    private String nombre(String cadena){
        cadena = cadena.replaceAll("[0-9]","");
        return cadena.substring(0, cadena.length() - 2);
    }

    public void levantarVentana(String path, String titulo) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioReagrupar.setTitle(titulo);
        escenarioReagrupar.setScene(scene);
        escenarioReagrupar.show();
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
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paisesEnTablero) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paisesEnTablero) {
            if (unPais.esLimitrofe(paisBuscado) && !juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }



    private Pais buscarPais (String nombrePaisBuscado) {
        for (Pais unPais: paisesEnTablero) {
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
