package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorObjetivoSecreto {

    @FXML
    private Label labelEnunciado;



    public void mostrarObjetivo(String enunciadoDeObjetivo) {
        labelEnunciado.setText(enunciadoDeObjetivo);
    }
}
