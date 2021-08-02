package controladores;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorInicio {

    @FXML
    private Button botonJugar;

    @FXML
    private Button botonSalir;

    @FXML
    void cerrarJuego(ActionEvent event) {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    void lanzarJuego(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load( getClass().getResource("/vista/ventanaMenu.fxml") );
        Stage ventana = (Stage) botonJugar.getScene().getWindow();
        ventana.setScene( new Scene(root) );
    }

}
