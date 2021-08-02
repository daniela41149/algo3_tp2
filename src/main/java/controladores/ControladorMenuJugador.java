package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
import edu.fiuba.algo3.modelo.Juego;


public class ControladorMenuJugador implements Initializable {

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

    private HashMap<String, Integer> paisesConEjercitos;

    private Juego juego;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        /*juego = new Juego(paises,continentes,jugadores);
        mostrarJugadorActual();
        mostrarPaisesLimitrofesActuales();*/
    }

    private void mostrarJugadorActual(){
        nombreJugador.setText( juego.nombreJugadorActual() );
    }

    private void mostrarPaisesLimitrofesActuales(){
        paisesConEjercitos = juego.nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaPaises.getItems().add( nombrePais + cantidadEjercito.toString() ) );
    }

    @FXML
    void pasarTurno(ActionEvent event) {
        /*juego.pasarTurno();
        mostrarJugadorActual();
        mostrarPaisesLimitrofesActuales();*/
    }

    @FXML
    void atacar(ActionEvent event) throws Exception {
        Stage escenarioDados = new Stage();
        Scene scene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ventanaDados.fxml"));
        Parent root = (Parent)loader.load();
        ControladorDados controladorDados = (ControladorDados)loader.getController();
        controladorDados.asignarFichas(4, 4);
        scene = new Scene(root);
        escenarioDados.setTitle("Batalla");
        escenarioDados.setScene(scene);
        escenarioDados.show();

    }
}
