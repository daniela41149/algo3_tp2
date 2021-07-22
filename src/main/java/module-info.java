module edu.fiuba.algo3 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    exports edu.fiuba.algo3.modelo;
    exports vista;
    exports controladores;
    opens controladores;
}