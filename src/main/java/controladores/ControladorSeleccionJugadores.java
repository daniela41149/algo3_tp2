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
import javafx.scene.control.TextField;
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
        levantarVentana("/vista/ventanaNombresDe5Jugadores.fxml",boton5,"ALTEGO");
    }

    @FXML
    void jugar_con_cuatro(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaNombresDe4Jugadores.fxml",boton4,"ALTEGO");
    }

    @FXML
    void jugar_con_dos(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaNombresDe2Jugadores.fxml",boton2,"ALTEGO");
    }

    @FXML
    void jugar_con_seis(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaNombresDe6Jugadores.fxml",boton6,"ALTEGO");
    }

    @FXML
    void jugar_con_tres(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaNombresDe3Jugadores.fxml",boton3,"ALTEGO");
    }

    Stage escenarioSeleccion = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;


    public void levantarVentana(String path, Button boton, String titulo) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton.getScene().getWindow();
        escenarioSeleccion.setTitle(titulo);
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }



}
