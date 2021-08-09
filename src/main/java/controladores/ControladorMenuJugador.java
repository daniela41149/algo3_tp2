package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.Aleatorio;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ControladorMenuJugador{

    private HashMap<String, Integer> paisesConEjercitos;
    private Juego juego;
    Stage escenarioDados = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    ArrayList<String> jugadores = new ArrayList<>();
    Moderador moderador;
    Aleatorio aleatorio = new Aleatorio();
    List<TarjetaObjetivo> mazoDeTarjetasObjetivo;
    List<TarjetaPais> mazoDeTarjetasPais;

    @FXML
    private Label nombreJugador;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonPasar;

    @FXML
    private ListView<String> listaPaises;

    @FXML
    private ListView<String> listaLimitrofes;

    @FXML
    private Button botonArranque;

    @FXML
    private Button botonColocarEjercito;

    @FXML
    void pasarTurno(ActionEvent event) {
        listaPaises.getItems().clear();
        juego.pasarTurno();
        refrescarDatosEnPantalla();
    }

    @FXML
    void atacar(ActionEvent event) throws IOException {
        listaPaises.getSelectionModel().getSelectedItem();
        levantarVentanaDados();
        ControladorDados controladorDados = obtenerControladorDados();
        controladorDados.asignarFichas(2, 4);
        refrescarDatosEnPantalla();
    }

    @FXML
    void cargarJuego(ActionEvent event) throws IOException, JugadaInvalidaException {
        iniciarJuego();
        botonArranque.setVisible(false);
    }

    @FXML
    void colocarEjercito(ActionEvent event) {

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

    public void asignarJugadores(ArrayList<String> nombresJugadores) {
        jugadores = nombresJugadores;
    }

    public void iniciarJuego() throws IOException, JugadaInvalidaException{
        moderador = new Moderador();
        juego = new Juego(moderador.pedirPaises(),moderador.pedirContinentes(),jugadores);
        mazoDeTarjetasObjetivo = moderador.pedirTarjetasObjetivo();
        mazoDeTarjetasPais = moderador.pedirTarjetasPais();
        juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
        juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
        juego.comenzarFaseInicial(aleatorio);
        refrescarDatosEnPantalla();
    }

    public void refrescarDatosEnPantalla(){
        mostrarJugadorActual();
        mostrarPaisesActuales();
    }

    private void mostrarJugadorActual(){
        nombreJugador.setText( juego.nombreJugadorActual() );
    }

    private void mostrarPaisesActuales(){
        paisesConEjercitos = juego.nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaPaises.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    @FXML
    void mostrarLimitrofes(MouseEvent event) {
        listaLimitrofes.getItems().clear();
        listaLimitrofes.getItems().add( "sdfsdf");
    }

}
