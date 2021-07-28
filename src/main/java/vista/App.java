package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;  
import javafx.stage.Stage;
import javafx.scene.Parent;


public class App extends Application {

    @Override
    public void start(Stage escenarioInicio) throws Exception {
        
        Parent root = FXMLLoader.load( getClass().getResource("ventanaInicio.fxml") );
        Scene scene = new Scene(root);
        escenarioInicio.setTitle("ALTEGO");
        escenarioInicio.setScene(scene);
        escenarioInicio.show();
    }

    public static void main(String[] args) {
        launch();
    }

}