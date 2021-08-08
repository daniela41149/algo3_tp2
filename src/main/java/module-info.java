module edu.fiuba.algo3 {
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    exports edu.fiuba.algo3.modelo;
    exports vista;
    exports controladores;
    opens controladores;
    exports edu.fiuba.algo3.modelo.pais;
    exports edu.fiuba.algo3.modelo.tarjetaPais;
    exports edu.fiuba.algo3.modelo.tarjetaObjetivo;
    exports edu.fiuba.algo3.modelo.canjes;
    opens edu.fiuba.algo3.modelo.tarjetaPais;
    opens edu.fiuba.algo3.modelo.tarjetaObjetivo;
    opens edu.fiuba.algo3.modelo;
    opens edu.fiuba.algo3.modelo.pais;
    opens edu.fiuba.algo3.modelo.canjes;
}