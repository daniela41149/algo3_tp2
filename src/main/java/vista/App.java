package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;  
import javafx.stage.Stage;
import javafx.scene.Parent;


public class App extends Application {

    @Override
    public void start(Stage escenarioDados) throws Exception {
        
        Parent root = FXMLLoader.load( getClass().getResource("ventanaMenu.fxml") );
        Scene scene = new Scene(root);
        escenarioDados.setTitle("ALTEGO");
        escenarioDados.setScene(scene);
        escenarioDados.show();
    }

    public static void main(String[] args) {
        launch();
    }

}