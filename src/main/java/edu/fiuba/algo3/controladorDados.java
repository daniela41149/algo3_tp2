package edu.fiuba.algo3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class controladorDados implements Initializable {


    @FXML
    private ImageView dadoAtacanteUno;
    private Image caraUno = new Image( getClass().getResourceAsStream( "dice_1.png" ) );
    
    @FXML
    public void desplegarImagenesDeDados(){
        dadoAtacanteUno.setImage(caraUno);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}
    