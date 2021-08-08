package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class ControladorMenuJugador implements Initializable {

    private HashMap<String, Integer> paisesConEjercitos;
    private Juego juego;
    Stage escenarioDados = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    ArrayList<String> jugadores = new ArrayList<>();
    Moderador moderador;

    @FXML
    private Label nombreJugador;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonPasar;

    @FXML
    private ListView<String> listaPaises;

    @FXML
    private Button botonArranque;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void pasarTurno(ActionEvent event) {
        juego.pasarTurno();
        refrescarDatosEnPantalla();
    }

    @FXML
    void atacar(ActionEvent event) throws IOException {
       levantarVentanaDados();
       ControladorDados controladorDados = obtenerControladorDados();
       controladorDados.asignarFichas(2, 4);
    }

    @FXML
    void cargarJuego(ActionEvent event) throws IOException {
        iniciarJuego();
        botonArranque.setVisible(false);
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

    public void asignarJugadores(ArrayList<String> nombresJugadores) {
        jugadores = nombresJugadores;
    }

    public void iniciarJuego() throws IOException{
        moderador = new Moderador();
        juego = new Juego(moderador.pedirPaises(),moderador.pedirContinentes(),jugadores);
        refrescarDatosEnPantalla();
    }

    public void refrescarDatosEnPantalla(){
        mostrarJugadorActual();
        mostrarPaisesLimitrofesActuales();
    }

    private void mostrarJugadorActual(){
        nombreJugador.setText( juego.nombreJugadorActual() );
    }

    private void mostrarPaisesLimitrofesActuales(){
        paisesConEjercitos = juego.nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.put("a",20);
        paisesConEjercitos.put("b",10);
        paisesConEjercitos.put("c",40);
        paisesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaPaises.getItems().add( nombrePais+ cantidadEjercito.toString() ) );
        
    }

}
