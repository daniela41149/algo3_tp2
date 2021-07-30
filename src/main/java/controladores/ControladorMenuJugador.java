package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    private ListView<?> listaPaises;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        mostrarJugadorActual();
        mostrarPaisesLimitrofesActuales();
    }

    private void mostrarJugadorActual(){

    }

    private void mostrarPaisesLimitrofesActuales(){

    }

    @FXML
    void pasarTurno(ActionEvent event) {
        // cambia de jugador
        mostrarJugadorActual();
        mostrarPaisesLimitrofesActuales();
    }

}
