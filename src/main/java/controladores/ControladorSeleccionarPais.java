package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ControladorSeleccionarPais {

    Juego juego;
    HashMap<String, Integer> limitrofesConEjercitos;
    int ejercito;
    String limitrofeSeleccionado;
    String nombrePais;



    @FXML
    private Label labelPais1;

    @FXML
    private Label labelPais2;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonVolver;

    @FXML
    private ListView<String> listaLimitrofes;


    Stage escenarioReagrupar = new Stage();
    Stage escenarioDados = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;



    public void seleccionarPais(String nombrePais, int cantEjercito, Juego juego, HashMap<String, Integer> limitrofesConEjercitos) {
        this.juego = juego;
        this.nombrePais = nombrePais;
        this.ejercito = cantEjercito;
        this.limitrofesConEjercitos = limitrofesConEjercitos;
        labelPais1.setText(nombrePais);


    }

    public void mostrarLimitrofesParaAtacar() {
        mostrarLimitrofesActualesParaAtacar(nombrePais);
    }
    public void mostrarLimitrofesParaReagrupar() {
        mostrarLimitrofesActualesParaReagrupar(nombrePais);
    }

    @FXML
    void seleccionarLimitrofe(MouseEvent event) throws IOException{
        limitrofeSeleccionado = listaLimitrofes.getSelectionModel().getSelectedItem();
        labelPais2.setText(nombre(limitrofeSeleccionado));
    }

    @FXML
    void elegir(ActionEvent event) throws IOException{
        levantarVentanaReagruparEjercitos();
    }

    @FXML
    void atacar(ActionEvent event) throws IOException{
        levantarVentanaDados();
        ControladorDados controladorDados = obtenerControladorDados();
        controladorDados.asignarFichas(2, 4);

    }

    @FXML
    void volver(ActionEvent event) throws IOException{
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.close();
    }



    private String nombre (String cadena) {
        String[] partes = cadena.split(" ");
        return partes[0];
    }



    private void mostrarLimitrofesActualesParaReagrupar(String nombre){
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(nombre);
        if (limitrofesConEjercitos.isEmpty()) {
            labelPais2.setText("no tiene limitrofes para reagrupar");
        }
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    private void mostrarLimitrofesActualesParaAtacar(String nombre) {
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(nombre);
        if (limitrofesConEjercitos.isEmpty()) {
            labelPais2.setText("no tiene limitrofes para atacar");
        }
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }


    public void levantarVentanaReagruparEjercitos() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaReagruparEjercitos.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioReagrupar.setTitle("Reagrupar Ejercitos");
        escenarioReagrupar.setScene(scene);
        escenarioReagrupar.show();
    }

    public void levantarVentanaDados() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaDados.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioDados.setTitle("Batalla");
        escenarioDados.setScene(scene);
        escenarioDados.show();
    }

    public ControladorDados obtenerControladorDados() {
        ControladorDados controladorDados = (ControladorDados)loader.getController();
        return controladorDados;
    }




    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = juego.devolverPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paises) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = juego.devolverPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paises) {
            if (unPais.esLimitrofe(paisBuscado) && !juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }



    private Pais buscarPais (String nombrePaisBuscado) {
        List<Pais> paises = juego.devolverPaises();
        for (Pais unPais: paises) {
            if (unPais.getNombre().equals((nombrePaisBuscado)))
                return unPais;
        }
        return null;
    }


    private HashMap<String, Integer> nombrePaisesYEjercitosDeJugadorActual (){
        List<Pais> listaPaises = juego.jugadorEnTurno().pedirPaises();
        HashMap<String,Integer> diccionario = new HashMap<>();
        for(Pais unPais: listaPaises){
            diccionario.put(unPais.getNombre(),unPais.cantidadDeFichas());
        }
        return diccionario;
    }


}
