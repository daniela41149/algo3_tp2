package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ControladorTarjetas {

    private Juego juego;
    private List<TarjetaPais> tarjetasPais;
    private List<TarjetaPais> tarjetasPaisParaCanjear = new ArrayList<>();;
    List<TarjetaPais> mazoCompletoDeTarjetasPais = new ArrayList<>();
    ListIterator<TarjetaPais> iterador;
    ListIterator<TarjetaPais> iteradorCanje;
    TarjetaPais tarjetaPaisSeleccionada;
    private int numero = 0;
    static final String[] SIMBOLOS = {"Globo", "Barco", "Cañon", "Comodin"};
    List<String> nombreTarjetasSeleccionadasParaCanjear = new ArrayList<>();


    Image globo = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/globo.png") ) ;
    Image barco = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/barco.png") ) ;
    Image cañon = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/cañon.png") ) ;
    Image comodin = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/comodin.png") ) ;



    @FXML
    private ImageView imagenTarjetaPrincipal;

    @FXML
    private Label labelPais;

    @FXML
    private ImageView imagenSimbolo;

    @FXML
    private Button botonAnterior;

    @FXML
    private Button botonSiguiente;

    @FXML
    private Button botonActivar;

    @FXML
    private Button botonSeleccionarCanje;

    @FXML
    private Button botonCanjear;

    @FXML
    private Button botonOkey;

    @FXML
    private Button botonTarjetas;

    @FXML
    private ImageView imagenPrimerTarjeta;

    @FXML
    private ImageView imagenSegundaTarjeta;

    @FXML
    private ImageView imagenTercerTarjeta;

    @FXML
    private ImageView imagenPrimerSimboloSeleccionado;

    @FXML
    private ImageView imagenSegundoSimboloSeleccionado;

    @FXML
    private ImageView imagenTercerSimboloSeleccionado;

    @FXML
    private Label labelPrimerPaisSeleccionado;

    @FXML
    private Label labelSegundoPaisSeleccionado;

    @FXML
    private Label labelTercerPaisSeleccionado;

    @FXML
    private Label ejercitosDisponibles;

    Stage escenario = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;


    public void mostrarTarjetas(Juego juego,List<TarjetaPais> mazoCompletoDeTarjetasPais,Button botonTarjetas,Label ejercitosDisponibles) {
        this.juego = juego;
        this.tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        this.mazoCompletoDeTarjetasPais = mazoCompletoDeTarjetasPais;
        this.botonTarjetas = botonTarjetas;
        this.ejercitosDisponibles = ejercitosDisponibles;

        imagenPrimerTarjeta.setVisible(false);
        imagenSegundaTarjeta.setVisible(false);
        imagenTercerTarjeta.setVisible(false);
        imagenTarjetaPrincipal.setVisible(false);
        botonCanjear.setDisable(true);


        this.iterador = tarjetasPais.listIterator();
        this.tarjetaPaisSeleccionada = iterador.next();
        mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);

        this.iteradorCanje = tarjetasPaisParaCanjear.listIterator();

        verificacionesSegunNumeroDeTarjetas();
        verificacionesBotonSiguienteYAnterior();
    }





    @FXML
    void activar(ActionEvent event) throws IOException {

        try {
            juego.jugadorEnTurno().activarTarjetaPais(tarjetasPais.get(numero).getNombre());

        } catch (JugadaInvalidaException e) {
            levantarVentana("/vista/ventanaNoSePuedeActivarTarjetaPais.fxml","No Se Puede Activar la Tarjeta");
        }
        tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        mostrarSiguienteTarjeta();
        verificacionesSegunNumeroDeTarjetas();
        verificacionesBotonSiguienteYAnterior();
        actualizarNumeroDeEjercitosDeMenu();
    }



    @FXML
    void seleccionarParaCanje(ActionEvent event) {
        verificacionesSegunNumeroDeTarjetas();
        verificacionesBotonSiguienteYAnterior();

        if (tarjetasPaisParaCanjear.size() < 3){
            tarjetasPaisParaCanjear.add(tarjetaPaisSeleccionada);
            iterador.remove();
            mostrarTarjetasDeCanje();
            guardarObjetoDeIterador();
            mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);

        }
        if (tarjetasPaisParaCanjear.size() == 3){
            botonCanjear.setDisable(false);
        }
        verificacionesSegunNumeroDeTarjetas();
        verificacionesBotonSiguienteYAnterior();
    }

    @FXML
    void cerrar(ActionEvent event) {
        Stage stage = (Stage) botonOkey.getScene().getWindow();
        stage.close();
    }


    @FXML
    void canjear(ActionEvent event) {
        List<String> nombresDeLasTarjetasSeleccionadas = buscarNombresDeTarjetasParaCanje();
        juego.solicitarUnCanje(nombresDeLasTarjetasSeleccionadas);

        noMostrarTarjeta(labelPrimerPaisSeleccionado,imagenPrimerSimboloSeleccionado,imagenPrimerTarjeta);
        noMostrarTarjeta(labelSegundoPaisSeleccionado,imagenSegundoSimboloSeleccionado,imagenSegundaTarjeta);
        noMostrarTarjeta(labelTercerPaisSeleccionado,imagenTercerSimboloSeleccionado,imagenTercerTarjeta);

        this.tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        tarjetasPaisParaCanjear = new ArrayList<>();
        nombreTarjetasSeleccionadasParaCanjear = new ArrayList<>();
        mostrarSiguienteTarjeta();

        verificacionesSegunNumeroDeTarjetas();
        verificacionesBotonSiguienteYAnterior();
        actualizarNumeroDeEjercitosDeMenu();

    }

    @FXML
    void anterior(ActionEvent event){
        verificacionesBotonSiguienteYAnterior();
        mostrarAnteriorTarjeta();
    }

    @FXML
    void siguiente(ActionEvent event) {
        verificacionesBotonSiguienteYAnterior();
        mostrarSiguienteTarjeta();
    }



    private void mostrarTarjeta(TarjetaPais tarjetaPais, Label nombre, ImageView simbolo, ImageView imagenTarjeta) {
        nombre.setText(tarjetaPais.getNombre());
        simbolo.setImage(buscarImagen(tarjetaPais));
        imagenTarjeta.setVisible(true);
    }

    private void noMostrarTarjeta(Label nombre, ImageView simbolo, ImageView imagenTarjeta) {
        nombre.setText("");
        simbolo.setImage(null);
        imagenTarjeta.setVisible(false);

    }


    private void mostrarPrimerTarjetaSeleccionada(TarjetaPais tarjetaActual) {
        mostrarTarjeta(tarjetaActual, labelPrimerPaisSeleccionado,imagenPrimerSimboloSeleccionado,imagenPrimerTarjeta);
    }

    private void mostrarSegundaTarjetaSeleccionada(TarjetaPais tarjetaActual) {
        mostrarTarjeta(tarjetaActual, labelSegundoPaisSeleccionado,imagenSegundoSimboloSeleccionado,imagenSegundaTarjeta);
    }

    private void mostrarTercerTarjetaSeleccionada(TarjetaPais tarjetaActual) {
        mostrarTarjeta(tarjetaActual, labelTercerPaisSeleccionado,imagenTercerSimboloSeleccionado,imagenTercerTarjeta);
    }

    private void mostrarSiguienteTarjeta(){
        if (iterador.hasNext()) {
            tarjetaPaisSeleccionada = iterador.next();
            mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);

        }
        else {
            noMostrarTarjeta(labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
    }

    private void guardarObjetoDeIterador() {
        if (iterador.hasNext()) {
            tarjetaPaisSeleccionada = iterador.next();
        }
        else if (iterador.hasPrevious()) {
            tarjetaPaisSeleccionada = iterador.previous();
        }
    }

    private List<String> buscarNombresDeTarjetasParaCanje() {
        List<String> listaNombres = new ArrayList<>();
        for (TarjetaPais unaTarjeta: tarjetasPaisParaCanjear) {
            listaNombres.add(unaTarjeta.getNombre());
        }
        return listaNombres;
    }



    private void mostrarAnteriorTarjeta(){
        if (iterador.hasPrevious()) {
            this.tarjetaPaisSeleccionada = iterador.previous();
            mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
        else {
            noMostrarTarjeta(labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
    }

    private void mostrarTarjetasDeCanje() {
        if (tarjetasPaisParaCanjear.size() >= 1) {
            mostrarPrimerTarjetaSeleccionada(tarjetasPaisParaCanjear.get(0));
            if (tarjetasPaisParaCanjear.size() >= 2) {
                mostrarSegundaTarjetaSeleccionada(tarjetasPaisParaCanjear.get(1));
                if (tarjetasPaisParaCanjear.size() == 3) {
                    mostrarTercerTarjetaSeleccionada(tarjetasPaisParaCanjear.get(2));
                }
            }
        }
    }


    private void actualizarNumeroDeEjercitosDeMenu() {
        ejercitosDisponibles.setText(String.valueOf(juego.devolverEjercitosRestantesDeJugadorActual()));
    }




    private void verificacionesSegunNumeroDeTarjetas () {
        if (tarjetasPaisParaCanjear.size() == 3) {
            botonCanjear.setDisable(false);
        }
        if (this.tarjetasPaisParaCanjear.size() <3) {
            botonCanjear.setDisable(true);
        }
        if (this.tarjetasPais.size() == 0) {
            botonTarjetas.setDisable(true);
            botonActivar.setDisable(true);
            botonSeleccionarCanje.setDisable(true);
            noMostrarTarjeta(labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
    }

    private void verificacionesBotonSiguienteYAnterior () {
        if (iterador.hasNext()) {
            botonSiguiente.setDisable(false);
        }
        else {
            botonSiguiente.setDisable(true);
        }
        if (iterador.hasPrevious()) {
            botonAnterior.setDisable(false);
        }  else {
            botonAnterior.setDisable(true);
        }
    }


    private Image buscarImagen (TarjetaPais tarjetaPais) {
        if (tarjetaPais.esElMismoSimbolo(SIMBOLOS[0])) {
            return globo;
        } else if (tarjetaPais.esElMismoSimbolo(SIMBOLOS[1])) {
            return barco;
        } else if (tarjetaPais.esElMismoSimbolo(SIMBOLOS[2])) {
            return cañon;
        } else {
            return comodin;
        }
    }

    private List<TarjetaPais> buscarTarjetasDelJugador(List<TarjetaPais> mazoCompletoDeTarjetasPais) {
        List<TarjetaPais> listaTarjetas = new ArrayList<>();
        for (TarjetaPais unaTarjeta: mazoCompletoDeTarjetasPais) {
            if (juego.jugadorEnTurno().tieneLaTarjetaPais(unaTarjeta)){
                listaTarjetas.add(unaTarjeta);
            }
        }
        return listaTarjetas;
    }


    public void levantarVentana(String path, String titulo) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = (Parent) loader.load();
        scene = new Scene(root);
        escenario.setTitle(titulo);
        escenario.setScene(scene);
        escenario.show();
    }




}
