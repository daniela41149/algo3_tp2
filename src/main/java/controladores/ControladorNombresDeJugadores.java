package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorNombresDeJugadores {


    @FXML
    private TextField nombreJugador1;

    @FXML
    private TextField nombreJugador2;

    @FXML
    private TextField nombreJugador3;

    @FXML
    private TextField nombreJugador4;

    @FXML
    private TextField nombreJugador5;

    @FXML
    private TextField nombreJugador6;

    @FXML
    private Button jugar;


    @FXML
    void guardar2Nombres(ActionEvent event) throws IOException {
        jugadores.add(nombreJugador1.getText());
        jugadores.add(nombreJugador2.getText());
        cambiarDeVentana();
    }

    @FXML
    void guardar3Nombres(ActionEvent event) throws IOException {
        jugadores.add(nombreJugador1.getText());
        jugadores.add(nombreJugador2.getText());
        jugadores.add(nombreJugador3.getText());
        cambiarDeVentana();
    }

    @FXML
    void guardar4Nombres(ActionEvent event) throws IOException {
        jugadores.add(nombreJugador1.getText());
        jugadores.add(nombreJugador2.getText());
        jugadores.add(nombreJugador3.getText());
        jugadores.add(nombreJugador4.getText());
        cambiarDeVentana();
    }

    @FXML
    void guardar5Nombres(ActionEvent event) throws IOException {
        jugadores.add(nombreJugador1.getText());
        jugadores.add(nombreJugador2.getText());
        jugadores.add(nombreJugador3.getText());
        jugadores.add(nombreJugador4.getText());
        jugadores.add(nombreJugador5.getText());
        cambiarDeVentana();
    }

    @FXML
    void guardar6Nombres(ActionEvent event) throws IOException {
        jugadores.add(nombreJugador1.getText());
        jugadores.add(nombreJugador2.getText());
        jugadores.add(nombreJugador3.getText());
        jugadores.add(nombreJugador4.getText());
        jugadores.add(nombreJugador5.getText());
        jugadores.add(nombreJugador6.getText());
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
        escenarioSeleccion = (Stage) jugar.getScene().getWindow();
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
}
