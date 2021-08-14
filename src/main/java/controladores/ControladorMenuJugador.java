package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.Aleatorio;
import edu.fiuba.algo3.modelo.pais.Pais;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ControladorMenuJugador {

    private HashMap<String, Integer> paisesConEjercitos;
    private HashMap<String, Integer> limitrofesConEjercitos;
    private Juego juego;
    Stage escenario = new Stage();
    Stage escenarioNoEligioPais = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    ArrayList<String> jugadores = new ArrayList<>();
    Aleatorio aleatorio = new Aleatorio();
    List<Pais> paisesEnTablero;
    List<TarjetaObjetivo> mazoDeTarjetasObjetivo;
    List<TarjetaPais> mazoCompletoDeTarjetasPais = new ArrayList<>();
    String paisSeleccionado;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};


    @FXML
    private Label nombreJugador;

    @FXML
    private Label colorJugador;

    @FXML
    private Label ejercitosDisponibles;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonPasar;

    @FXML
    private ListView<String> listaPaises;

    @FXML
    private Button botonArranque;

    @FXML
    private Button botonColocarEjercito;

    @FXML
    private Button botonTarjetas;

    @FXML
    private Button botonObjetivo;



    @FXML
    void cargarJuego(ActionEvent event) throws IOException, JugadaInvalidaException {
        iniciarJuego();
        botonArranque.setVisible(false);
        botonColocarEjercito.setDisable(false);
        botonObjetivo.setDisable(false);
        botonTarjetas.setDisable(false);

    }

    @FXML
    void pasarTurno(ActionEvent event) {
        listaPaises.getItems().clear();
        juego.pasarTurno();
        refrescarDatosEnPantalla();
    }

    @FXML
    void atacar(ActionEvent event) throws IOException {
        if (paisSeleccionado == null) {
            levantarVentanaNoEligioPais();
            return;
        }

        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(nombre(paisSeleccionado));
        levantarVentana("/vista/ventanaSeleccionarPaisParaAtacar.fxml","Seleccionar Pais");
        ControladorSeleccionarPais controladorSeleccionarPais = obtenerControladorSeleccionarPais();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorSeleccionarPais.seleccionarPais((nombre(paisSeleccionado)), fichas, juego, paisesEnTablero, limitrofesConEjercitos,listaPaises,botonTarjetas);
        controladorSeleccionarPais.mostrarLimitrofesParaAtacar();
        paisSeleccionado = null;
    }

    @FXML
    void colocarEjercito(ActionEvent event) throws IOException {
        if (paisSeleccionado == null) {
            levantarVentanaNoEligioPais();
            return;
        }

        levantarVentana("/vista/ventanaAgregarEjercitos.fxml","Agregar Ejercitos");
        ControladorAgregarEjercitos controladorAgregarEjercitos = obtenerControladorAgregarEjercitos();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorAgregarEjercitos.asignarEjercitos(nombre(paisSeleccionado), fichas, juego, paisesEnTablero, nombreJugador, colorJugador, ejercitosDisponibles, listaPaises, botonAtacar, botonReagrupar, botonPasar);
        paisSeleccionado = null;
    }

    @FXML
    void reagruparEjercito(ActionEvent event) throws IOException {
        if (paisSeleccionado == null) {
            levantarVentanaNoEligioPais();
            return;
        }

        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(nombre(paisSeleccionado));
        levantarVentana("/vista/ventanaSeleccionarPaisParaReagrupar.fxml","Seleccionar Pais");
        ControladorSeleccionarPais controladorSeleccionarPais = obtenerControladorSeleccionarPais();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorSeleccionarPais.seleccionarPais((nombre(paisSeleccionado)), fichas, juego, paisesEnTablero, limitrofesConEjercitos,listaPaises,botonTarjetas);
        controladorSeleccionarPais.mostrarLimitrofesParaReagrupar();
        paisSeleccionado = null;
    }

    @FXML
    void verObjetivo(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaObjetivoSecreto.fxml","Objetivo Secreto");
        ControladorObjetivoSecreto controladorObjetivoSecreto = obtenerControladorObjetivoSecreto();
        controladorObjetivoSecreto.mostrarObjetivo(juego.jugadorEnTurno().devolverEnunciadoDeObjetivo());
    }

    @FXML
    void verTarjetas(ActionEvent event) throws IOException {
        levantarVentana("/vista/ventanaTarjetasPais.fxml","Tarjetas");
        ControladorTarjetas controladorTarjetas = obtenerControladorTarjetas();
        controladorTarjetas.mostrarTarjetas(juego,mazoCompletoDeTarjetasPais);
    }


    private String nombre(String cadena){
        cadena = cadena.replaceAll("[0-9]","");
        return cadena.substring(0, cadena.length() - 2);
    }

    @FXML
    void seleccionarPais(MouseEvent event) throws IOException {
        paisSeleccionado = listaPaises.getSelectionModel().getSelectedItem();
    }

    public void levantarVentanaNoEligioPais() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNoEligioPais.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioNoEligioPais.setTitle("No Eligio Pais");
        escenarioNoEligioPais.setScene(scene);
        escenarioNoEligioPais.show();
    }

    public void levantarVentana(String path, String titulo) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenario.setTitle(titulo);
        escenario.setScene(scene);
        escenario.show();
    }

    public ControladorAgregarEjercitos obtenerControladorAgregarEjercitos() {
        ControladorAgregarEjercitos controladorAgregarEjercitos = (ControladorAgregarEjercitos) loader.getController();
        return controladorAgregarEjercitos;
    }
    public ControladorTarjetas obtenerControladorTarjetas() {
        ControladorTarjetas controladorTarjetas = (ControladorTarjetas) loader.getController();
        return controladorTarjetas;
    }
    public ControladorSeleccionarPais obtenerControladorSeleccionarPais() {
        ControladorSeleccionarPais controladorSeleccionarPais = (ControladorSeleccionarPais) loader.getController();
        return controladorSeleccionarPais;
    }
    public ControladorObjetivoSecreto obtenerControladorObjetivoSecreto() {
        ControladorObjetivoSecreto controladorObjetivoSecreto = (ControladorObjetivoSecreto) loader.getController();
        return controladorObjetivoSecreto;
    }


    public void asignarJugadores(ArrayList<String> nombresJugadores) {
        jugadores = nombresJugadores;
    }

    public void iniciarJuego() throws IOException, JugadaInvalidaException {
        Moderador moderador = new Moderador();
        paisesEnTablero = moderador.pedirPaises();
        juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), jugadores);
        mazoDeTarjetasObjetivo = moderador.pedirTarjetasObjetivo();
        List <TarjetaPais> mazoDeTarjetasPais = moderador.pedirTarjetasPais();
        for (TarjetaPais unaTarjeta: mazoDeTarjetasPais) {
            mazoCompletoDeTarjetasPais.add(unaTarjeta);
        }
        juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
        juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
        juego.comenzarFaseInicial(aleatorio);
        refrescarDatosEnPantalla();
    }



    public void refrescarDatosEnPantalla() {
        mostrarJugadorActual();
        mostrarPaisesActuales();
        mostrarEjercitosDisponibles();
    }
    private void mostrarJugadorActual(){
        nombreJugador.setText( nombreJugadorActual() );
        cambiarElColor();
        colorJugador.setText("Ejército "+colorJugadorActual());
    }
    private void mostrarPaisesActuales() {
        paisesConEjercitos = nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach((nombrePais, cantidadEjercito) -> listaPaises.getItems().add(nombrePais + "  " + cantidadEjercito.toString()));
    }
    private void mostrarEjercitosDisponibles() {
        ejercitosDisponibles.setText(Integer.toString(ejercitoDisponibleActual()));
    }



    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String, Integer> paisesLimitrofes = new HashMap<>();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais : paisesEnTablero) {
            if (unPais.esLimitrofe(paisBuscado) && !juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(), unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(String nombrePais) {
        HashMap<String, Integer> paisesLimitrofes = new HashMap<>();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais : paisesEnTablero) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(), unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private Pais buscarPais(String nombrePaisBuscado) {
        for (Pais unPais : paisesEnTablero) {
            if (unPais.getNombre().equals((nombrePaisBuscado)))
                return unPais;
        }
        return null;
    }


    private HashMap<String, Integer> nombrePaisesYEjercitosDeJugadorActual() {
        List<Pais> listaPaises = juego.jugadorEnTurno().pedirPaises();
        HashMap<String, Integer> diccionario = new HashMap<>();
        for (Pais unPais : listaPaises) {
            diccionario.put(unPais.getNombre(), unPais.cantidadDeFichas());
        }
        return diccionario;
    }

    private void cambiarElColor() {
        String colorDeJugadorActual = colorJugadorActual();
        if (colorDeJugadorActual.equals(COLORES[0])) {
            colorJugador.setTextFill(Color.color(0.0,0.1,1.0));
            ejercitosDisponibles.setTextFill(Color.color(0.0,0.1,1.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[1])) {
            colorJugador.setTextFill(Color.color(0.85,0.0,0.0));
            ejercitosDisponibles.setTextFill(Color.color(0.85,0.0,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[2])) {
            colorJugador.setTextFill(Color.color(0.8,0.7,0.0));
            ejercitosDisponibles.setTextFill(Color.color(0.8,0.7,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[3])) {
            colorJugador.setTextFill(Color.color(0.3,0.70,0.0));
            ejercitosDisponibles.setTextFill(Color.color(0.3,0.70,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[4])) {
            colorJugador.setTextFill(Color.color(0.8,0.1,1.0));
            ejercitosDisponibles.setTextFill(Color.color(0.8,0.1,1.0));
        }
        else {
            colorJugador.setTextFill(Color.color(0.0,0.0,0.0));
            ejercitosDisponibles.setTextFill(Color.color(0.0,0.0,0.0));
        }
    }

    private String nombreJugadorActual() {
        return juego.jugadorEnTurno().getNombre();
    }
    private String colorJugadorActual() {
        return juego.jugadorEnTurno().getColor();
    }
    private int ejercitoDisponibleActual() {
        return juego.devolverEjercitosRestantesDeJugadorActual();
    }


}