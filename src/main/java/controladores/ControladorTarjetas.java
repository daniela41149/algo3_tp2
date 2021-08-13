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
    String nombrePrimeraTarjeta;
    String nombreSegundaTarjeta;
    String nombreTerceraTarjeta;


    Image globo = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/globo.png") ) ;
    Image barco = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/barco.png") ) ;
    Image cañon = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/cañon.png") ) ;
    Image comodin = new Image( getClass().getResourceAsStream("/vista/imagenesTarjetasPais/comodin.png") ) ;
    Image dibujoTarjeta = new Image( getClass().getResourceAsStream("/vista/imagenes_objetivos/objetivoSecreto fondo.png") ) ;


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


    public void mostrarTarjetas(Juego juego,List<TarjetaPais> mazoCompletoDeTarjetasPais) {
        this.juego = juego;
        this.tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        this.mazoCompletoDeTarjetasPais = mazoCompletoDeTarjetasPais;

        imagenPrimerTarjeta.setVisible(false);
        imagenSegundaTarjeta.setVisible(false);
        imagenTercerTarjeta.setVisible(false);
        imagenTarjetaPrincipal.setVisible(false);
        botonCanjear.setDisable(true);

        this.iterador = tarjetasPais.listIterator();
        guardarObjetoDeIterador();
        this.iteradorCanje = tarjetasPaisParaCanjear.listIterator();
    }





    @FXML
    void activar(ActionEvent event) throws IOException, JugadaInvalidaException {
        juego.jugadorEnTurno().activarTarjetaPais(tarjetasPais.get(numero).getNombre());
        tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        mostrarSiguienteTarjeta();
    }

    @FXML
    void seleccionarParaCanje(ActionEvent event) throws IOException, JugadaInvalidaException {

        if (nombreTarjetasSeleccionadasParaCanjear.size() < 3){

            tarjetasPaisParaCanjear.add(tarjetaPaisSeleccionada);
            iterador.remove();
            mostrarTarjetasDeCanje();
            guardarObjetoDeIterador();
            mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);

        }
        if (nombreTarjetasSeleccionadasParaCanjear.size() == 3){
            botonCanjear.setDisable(false);
        }



    }

    @FXML
    void canjear(ActionEvent event) throws IOException, JugadaInvalidaException {
        juego.solicitarUnCanje(nombreTarjetasSeleccionadasParaCanjear);
        noMostrarTarjeta(labelPrimerPaisSeleccionado,imagenPrimerSimboloSeleccionado,imagenPrimerTarjeta);
        noMostrarTarjeta(labelSegundoPaisSeleccionado,imagenSegundoSimboloSeleccionado,imagenSegundaTarjeta);
        noMostrarTarjeta(labelTercerPaisSeleccionado,imagenTercerSimboloSeleccionado,imagenTercerTarjeta);
        botonCanjear.setDisable(true);
        this.tarjetasPais = buscarTarjetasDelJugador(mazoCompletoDeTarjetasPais);
        tarjetasPaisParaCanjear = new ArrayList<>();
        nombreTarjetasSeleccionadasParaCanjear = new ArrayList<>();
        mostrarSiguienteTarjeta();
    }

    @FXML
    void anterior(ActionEvent event){
        mostrarAnteriorTarjeta();
    }

    @FXML
    void siguiente(ActionEvent event) {
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

    private void pasarTarjetas(TarjetaPais tarjeta, List<TarjetaPais> primerMazo, List<TarjetaPais> segundoMazo) {
        for (TarjetaPais unaTarjeta: primerMazo) {
            if (tarjeta.getNombre().equals(unaTarjeta.getNombre())) {
                primerMazo.remove(tarjeta);
                segundoMazo.add(tarjeta);
            }
        }
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
            iterador.next();
            guardarObjetoDeIterador();
            mostrarTarjeta(tarjetaPaisSeleccionada,labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
        else {
            noMostrarTarjeta(labelPais,imagenSimbolo,imagenTarjetaPrincipal);
        }
    }

    private void guardarObjetoDeIterador() {
        if (iterador.hasNext()) {
            tarjetaPaisSeleccionada = iterador.next();
            iterador.previous();
        }
        else {
            tarjetaPaisSeleccionada = iterador.previous();
            iterador.next();
        }
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
        if (iteradorCanje.hasNext()) {
            mostrarPrimerTarjetaSeleccionada(tarjetaPaisSeleccionada);
            if (iteradorCanje.hasNext()) {
                mostrarSegundaTarjetaSeleccionada(tarjetaPaisSeleccionada);
                if (iteradorCanje.hasNext()) {
                    mostrarTercerTarjetaSeleccionada(tarjetaPaisSeleccionada);
                }
            }
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


}
