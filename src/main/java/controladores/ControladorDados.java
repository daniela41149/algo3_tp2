package controladores;

import java.io.IOException;
import java.util.List;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControladorDados{

    private Juego juego;
    private Jugador jugador;
    private Pais paisAtacante;
    private Pais paisDefensor;
    private List<Pais> paisesEnTablero;

    private List<Integer>[] dadosEnBatalla;
    ListView<String> listaLimitrofes;
    List<Integer> dadosAtacante;
    List<Integer> dadosDefensor;

    private int numeroDeDadosElegidos;

    @FXML
    private Button botonColocarEjercitos;

    @FXML
    private Button botonLanzar;

    @FXML
    private Button botonTarjetas;

    @FXML
    private Label labelNombrePaisAtacante;

    @FXML
    private Label labelNombrePaisDefensor;

    @FXML
    private Label labelEjercitosPaisAtacante;

    @FXML
    private Label labelEjercitosPaisDefensor;

    @FXML
    private Label labelCantidadDeDados;

    @FXML
    private Label labelEjercitosDisponibles;


    public void atacar(Juego juego, List<Pais> paisesEnTablero, Pais paisAtacante, Pais paisDefensor, ListView<String> listaLimitrofes,Button botonTarjetas, Label ejercitosDisponibles, Button botonColocarEjercitos) throws JugadaInvalidaException{
        this.listaLimitrofes = listaLimitrofes;
        this.juego = juego;
        this.paisesEnTablero = paisesEnTablero;
        this.jugador = juego.jugadorEnTurno();
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
        this.numeroDeDadosElegidos = 1;
        this.labelEjercitosDisponibles = ejercitosDisponibles;
        this.botonColocarEjercitos = botonColocarEjercitos;
        this.botonTarjetas = botonTarjetas;

        labelNombrePaisAtacante.setText(paisAtacante.getNombre());
        labelNombrePaisDefensor.setText(paisDefensor.getNombre());
        labelEjercitosPaisAtacante.setText(String.valueOf(paisAtacante.cantidadDeFichas()));
        labelEjercitosPaisDefensor.setText(String.valueOf(paisDefensor.cantidadDeFichas()));
        labelCantidadDeDados.setText(String.valueOf(numeroDeDadosElegidos));
    }

    @FXML
    private void sumarUno() {
        if (numeroDeDadosElegidos < 3)
            numeroDeDadosElegidos++;
        labelCantidadDeDados.setText(String.valueOf(numeroDeDadosElegidos));
    }

    @FXML
    private void restarUno() {
        if (numeroDeDadosElegidos > 1)
            numeroDeDadosElegidos--;
        labelCantidadDeDados.setText(String.valueOf(numeroDeDadosElegidos));
    }

    @FXML
    private void lanzar() throws IOException {

        try {
            dadosEnBatalla = juego.atacar(paisAtacante.getNombre(), paisDefensor.getNombre(), numeroDeDadosElegidos);

            levantarVentana("/vista/ventanaResultados.fxml", botonLanzar, "Resultados de Batalla");

            this.numeroDeDadosElegidos = 0;
            dadosAtacante = dadosEnBatalla[0];
            dadosDefensor = dadosEnBatalla[1];
            ControladorResultadosDeBatalla controladorResultadosDeBatalla = obtenerControladorResultadosDeBatalla();
            controladorResultadosDeBatalla.mostrarResultados(dadosAtacante, dadosDefensor);

            controladorResultadosDeBatalla.mostrarDatos(paisAtacante.getNombre(),paisAtacante.cantidadDeFichas(),paisDefensor.getNombre(),paisDefensor.cantidadDeFichas(),listaLimitrofes, paisesEnTablero);
            controladorResultadosDeBatalla.mostrarJugadorQueConquistoPais(jugador,paisDefensor.getNombre());

            labelEjercitosDisponibles.setText(Integer.toString(juego.devolverEjercitosRestantesDeJugadorActual()));
            if (juego.devolverEjercitosRestantesDeJugadorActual() > 0)
                botonColocarEjercitos.setDisable(false);
            if (jugador.esDueñoDelPais(paisDefensor.getNombre())) {
                botonTarjetas.setDisable(false);
            }
            botonLanzar.setVisible(false);

        } catch (CantidadInvalidaDeEjercitosException e1) {
            levantarVentana("/vista/ventanaNoHayEjercitosSuficientes.fxml", botonLanzar,"No hay ejercitos suficientes para atacar.");
        } catch (JugadaInvalidaException e2) {
            // no se necesita atrapar la excepcion
        }

        if (juego.cumplioObjetivo(jugador)) {
            levantarVentana("/vista/ventanaGanaste.fxml", botonLanzar,"Ganaste el juego");
        }

    }

    Stage escenarioSeleccion = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;

    public void levantarVentana(String path, Button boton,String titulo) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioSeleccion = (Stage) boton.getScene().getWindow();
        escenarioSeleccion.setTitle(titulo);
        escenarioSeleccion.setScene(scene);
        escenarioSeleccion.show();
    }

    private ControladorResultadosDeBatalla obtenerControladorResultadosDeBatalla() {
        ControladorResultadosDeBatalla controladorResultadosDeBatalla = (ControladorResultadosDeBatalla) loader.getController();
        return controladorResultadosDeBatalla;
    }

}
