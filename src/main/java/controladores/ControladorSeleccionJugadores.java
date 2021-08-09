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
        levantarVentanaNombresDe5Jugadores();
    }

    @FXML
    void jugar_con_cuatro(ActionEvent event) throws IOException {
        levantarVentanaNombresDe4Jugadores();
    }

    @FXML
    void jugar_con_dos(ActionEvent event) throws IOException {
        levantarVentanaNombresDe2Jugadores();
    }

    @FXML
    void jugar_con_seis(ActionEvent event) throws IOException {
        levantarVentanaNombresDe6Jugadores();
    }

    @FXML
    void jugar_con_tres(ActionEvent event) throws IOException {
        levantarVentanaNombresDe3Jugadores();
    }

    Stage escenarioSeleccion = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;



    public void levantarVentanaNombresDe2Jugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNombresDe2Jugadores.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton2.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaNombresDe3Jugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNombresDe3Jugadores.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton3.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaNombresDe4Jugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNombresDe4Jugadores.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton4.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaNombresDe5Jugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNombresDe5Jugadores.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton5.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaNombresDe6Jugadores() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNombresDe6Jugadores.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton6.getScene().getWindow();
        escenarioSeleccion.setTitle("ALTEGO");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

}
