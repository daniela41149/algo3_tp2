package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorSeleccionJugadores {

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private Button boton5;

    @FXML
    private Button boton6;

    @FXML
    void jugar_con_cinco(ActionEvent event) throws IOException {
        crearListaJugadores(5);
        cambiarDeVentana();
    }

    @FXML
    void jugar_con_cuatro(ActionEvent event) throws IOException {
        crearListaJugadores(4);
        cambiarDeVentana();
    }

    @FXML
    void jugar_con_dos(ActionEvent event) throws IOException {
        crearListaJugadores(2);
        cambiarDeVentana();
    }

    @FXML
    void jugar_con_seis(ActionEvent event) throws IOException {
        crearListaJugadores(6);
        cambiarDeVentana();
    }

    @FXML
    void jugar_con_tres(ActionEvent event) throws IOException {
        crearListaJugadores(3);
        cambiarDeVentana();
    }

    Stage escenarioSeleccion = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    ArrayList<String> jugadores = new ArrayList<>();

    public void levantarVentanaMenuJugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaMenu.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton2.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public ControladorMenuJugador obtenerControladorMenuJugador() {
        ControladorMenuJugador controladorMenuJugadores = (ControladorMenuJugador)loader.getController();
        return controladorMenuJugadores;
    }

    private void cambiarDeVentana() throws IOException{
        levantarVentanaMenuJugadores();
        ControladorMenuJugador controladorMenuJugadores = obtenerControladorMenuJugador();
        controladorMenuJugadores.asignarJugadores(jugadores);
    }

    private void crearListaJugadores(Integer cantidadJugadores){
        String nombreJugador;
        for(int i=0; i < cantidadJugadores; i++){
            nombreJugador = "Jugador "+ String.valueOf(i+1);
            jugadores.add(nombreJugador);
        }
    }

}
