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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ControladorMenuJugador {

    private HashMap<String, Integer> paisesConEjercitos;
    private HashMap<String, Integer> limitrofesConEjercitos;
    private Juego juego;
    Stage escenarioDados = new Stage();
    Stage escenarioSeleccion = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;
    ArrayList<String> jugadores = new ArrayList<>();
    Moderador moderador;
    Aleatorio aleatorio = new Aleatorio();
    List<TarjetaObjetivo> mazoDeTarjetasObjetivo;
    List<TarjetaPais> mazoDeTarjetasPais;
    String paisSeleccionado;


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
    private Button botonArranque;

    @FXML
    private Button botonColocarEjercito;



    @FXML
    void cargarJuego(ActionEvent event) throws IOException, JugadaInvalidaException {
        iniciarJuego();
        botonArranque.setVisible(false);
    }

    @FXML
    void pasarTurno(ActionEvent event) {
        listaPaises.getItems().clear();
        juego.pasarTurno();
        refrescarDatosEnPantalla();
    }

    @FXML
    void atacar(ActionEvent event) throws IOException {
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(nombre(paisSeleccionado));
        levantarVentanaSeleccionarPaisParaAtacar();
        ControladorSeleccionarPais controladorSeleccionarPais = obtenerControladorSeleccionarPais();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorSeleccionarPais.seleccionarPais((nombre(paisSeleccionado)), fichas, juego, limitrofesConEjercitos,listaPaises);
        controladorSeleccionarPais.mostrarLimitrofesParaAtacar();
    }

    @FXML
    void colocarEjercito(ActionEvent event) throws IOException {
        levantarVentanaAgregarEjercitos();
        ControladorAgregarEjercitos controladorAgregarEjercitos = obtenerControladorAgregarEjercitos();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorAgregarEjercitos.asignarEjercitos(nombre(paisSeleccionado), fichas, juego, nombreJugador, listaPaises, botonAtacar, botonReagrupar, botonPasar);
    }

    @FXML
    void reagruparEjercito(ActionEvent event) throws IOException {
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(nombre(paisSeleccionado));
        levantarVentanaSeleccionarPaisParaReagrupar();
        ControladorSeleccionarPais controladorSeleccionarPais = obtenerControladorSeleccionarPais();
        int fichas = buscarPais(nombre(paisSeleccionado)).cantidadDeFichas();
        controladorSeleccionarPais.seleccionarPais((nombre(paisSeleccionado)), fichas, juego, limitrofesConEjercitos,listaPaises);
        controladorSeleccionarPais.mostrarLimitrofesParaReagrupar();
    }

    @FXML
    void verObjetivo(ActionEvent event) throws IOException {
        levantarVentanaObjetivoSecreto();
        ControladorObjetivoSecreto controladorObjetivoSecreto = obtenerControladorObjetivoSecreto();
        controladorObjetivoSecreto.mostrarObjetivo(juego.jugadorEnTurno().devolverEnunciadoDeObjetivo());
    }


    private String nombre(String cadena){
        cadena = cadena.replaceAll("[0-9]","");
        return cadena.substring(0, cadena.length() - 2);
    }

    @FXML
    void seleccionarPais(MouseEvent event) throws IOException {
        paisSeleccionado = listaPaises.getSelectionModel().getSelectedItem();
    }


    public void levantarVentanaAgregarEjercitos() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaAgregarEjercitos.fxml"));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenarioDados.setTitle("Agregar Ejercitos");
        escenarioDados.setScene(scene);
        escenarioDados.show();
    }

    public void levantarVentanaSeleccionarPaisParaReagrupar() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaSeleccionarPaisParaReagrupar.fxml"));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenarioSeleccion.setTitle("Seleccionar Pais");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaSeleccionarPaisParaAtacar() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaSeleccionarPaisParaAtacar.fxml"));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenarioSeleccion.setTitle("Seleccionar Pais");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    public void levantarVentanaObjetivoSecreto() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaObjetivoSecreto.fxml"));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenarioSeleccion.setTitle("Objetivo Secreto");
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }


    public ControladorAgregarEjercitos obtenerControladorAgregarEjercitos() {
        ControladorAgregarEjercitos controladorAgregarEjercitos = (ControladorAgregarEjercitos) loader.getController();
        return controladorAgregarEjercitos;
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
        moderador = new Moderador();
        juego = new Juego(moderador.pedirPaises(), moderador.pedirContinentes(), jugadores);
        mazoDeTarjetasObjetivo = moderador.pedirTarjetasObjetivo();
        mazoDeTarjetasPais = moderador.pedirTarjetasPais();
        juego.guardarMazoDeTarjetasObjetivo(mazoDeTarjetasObjetivo);
        juego.guardarMazoDeTarjetasPais(mazoDeTarjetasPais);
        juego.comenzarFaseInicial(aleatorio);
        refrescarDatosEnPantalla();

    }

    public void refrescarDatosEnPantalla() {
        mostrarJugadorActual();
        mostrarPaisesActuales();
    }

    private void mostrarJugadorActual() {
        nombreJugador.setText(nombreJugadorActual());
    }


    private void mostrarPaisesActuales() {
        paisesConEjercitos = nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach((nombrePais, cantidadEjercito) -> listaPaises.getItems().add(nombrePais + "  " + cantidadEjercito.toString()));
    }


    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String, Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = juego.devolverPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais : paises) {
            if (unPais.esLimitrofe(paisBuscado) && !juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(), unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(String nombrePais) {
        HashMap<String, Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = juego.devolverPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais : paises) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(), unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }

    private Pais buscarPais(String nombrePaisBuscado) {
        List<Pais> paises = juego.devolverPaises();
        for (Pais unPais : paises) {
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

    private String nombreJugadorActual() {
        return juego.jugadorEnTurno().getNombre();
    }


}