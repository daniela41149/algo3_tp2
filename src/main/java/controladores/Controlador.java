package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controlador{

    @FXML
    private Button boton;

    @FXML
    private ImageView dadoAtacanteUno;

    @FXML
    private ImageView dadoAtacanteDos;

    @FXML
    private ImageView dadoAtacanteTres;

    @FXML
    private ImageView dadoDefensorUno;

    @FXML
    private ImageView dadoDefensorDos;

    @FXML
    private ImageView dadoDefensorTres;

    @FXML
    private void lanzar() {
        System.out.println("asda");
        Image image1 = new Image(getClass().getResourceAsStream("/vista/dice_6.png"));
        dadoAtacanteUno.setImage(image1);
    }


}
