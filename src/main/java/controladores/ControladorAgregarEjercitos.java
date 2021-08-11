package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ControladorAgregarEjercitos {

    @FXML
    private Label labelPais;

    @FXML
    private Label labelEjercitos;

    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonSumarUno;

    @FXML
    private Button botonRestarUno;

    @FXML
    private Button botonOkey;

    @FXML
    private Label nombreJugador;

    @FXML
    private Label colorJugador;

    @FXML
    private ListView<String> listaPaises;

    @FXML
    private Button botonAtacar;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonPasar;


    static final int CANT_EJERCITOS_EN_PRIMERA_VUELTA = 5;
    static final int CANT_EJERCITOS_EN_SEGUNDA_VUELTA = 3;
    static final int NUMERO_PAISES = 50;
    private Juego juego;
    private int suma = 0;
    private String nombrePais;
    private HashMap<String, Integer> paisesConEjercitos;
    private int cantidadDeJugadores;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};




    public void asignarEjercitos(String pais,int cantidad,Juego juego, Label nombreJugador, Label colorJugador,ListView<String> listaPaises, Button botonAtacar, Button botonReagrupar, Button botonPasar){
        this.botonAtacar = botonAtacar;
        this.botonReagrupar = botonReagrupar;
        this.botonPasar = botonPasar;
        this.juego = juego;
        this.nombrePais = pais;
        this.nombreJugador = nombreJugador;
        this.colorJugador = colorJugador;
        this.listaPaises = listaPaises;
        this.cantidadDeJugadores = juego.devolverJugadores().size();
        labelPais.setText( pais);
        labelEjercitos.setText( "0" );
    }


    @FXML
    public void agregarEjercitos(ActionEvent event) throws IOException{

        int ejercitosTotales = sumarEjercitosTotales();
        int maximoPrimeraVuelta = cantidadDeJugadores*CANT_EJERCITOS_EN_PRIMERA_VUELTA+NUMERO_PAISES;
        int maximoSegundaVuelta = cantidadDeJugadores*CANT_EJERCITOS_EN_SEGUNDA_VUELTA+ cantidadDeJugadores*CANT_EJERCITOS_EN_PRIMERA_VUELTA+NUMERO_PAISES;

        try {

            if ((maximoSegundaVuelta > ejercitosTotales) && (maximoPrimeraVuelta <= ejercitosTotales)) {
                juego.colocarEjercitoSegundaVuelta(nombrePais, suma);

                ejercitosTotales = sumarEjercitosTotales();
                if (ejercitosTotales == maximoSegundaVuelta) {
                    botonAtacar.setDisable(false);
                    botonReagrupar.setDisable(false);
                    botonPasar.setDisable(false);
                }
            }
            else if (maximoPrimeraVuelta > ejercitosTotales) {
                juego.colocarEjercitoPrimeraVuelta(nombrePais, suma);
            }
            else {
                juego.colocarEjercito(nombrePais,suma);
            }

            listaPaises.getItems().clear();
            refrescarDatosEnPantalla();


        } catch (CantidadInvalidaDeEjercitosException e1) {
            levantarVentanaNoHayEjercitosSuficientes();

        } catch (JugadaInvalidaException e2) {
        }

        Stage stage = (Stage) botonAgregar.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void sumarUno(ActionEvent event){
        suma ++;
        mostrarEjercitos();
    }

    @FXML
    public void restarUno(ActionEvent event){
        if (suma > 0) {
            suma --;
        }
        mostrarEjercitos();
    }

    private void mostrarEjercitos(){
        String ejercitos= String.valueOf(suma);
        labelEjercitos.setText( ejercitos);
    }


    @FXML
    public void cerrar(ActionEvent event){
        Stage stage = (Stage) botonOkey.getScene().getWindow();
        stage.close();
    }



    Stage  escenarioNoHayEjercitos = new Stage();
    Scene scene;
    Parent root;
    FXMLLoader loader;



    public void levantarVentanaNoHayEjercitosSuficientes() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/vista/ventanaNoHayEjercitosSuficientes.fxml"));
        root = (Parent)loader.load();
        scene = new Scene(root);
        escenarioNoHayEjercitos.setScene(scene);
        escenarioNoHayEjercitos.show();
    }


    private void mostrarPaisesActuales(){
        paisesConEjercitos = nombrePaisesYEjercitosDeJugadorActual();
        paisesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaPaises.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }

    private int sumarEjercitosTotales() {
        paisesConEjercitos = nombrePaisesYEjercitosDeJugadorActual();
        int ejercitosTotales = 0;
        for (Pais pais : juego.devolverPaises()) {
            ejercitosTotales += pais.cantidadDeFichas();
        }
        return ejercitosTotales;
    }


    private void refrescarDatosEnPantalla(){
        mostrarJugadorActual();
        mostrarPaisesActuales();
    }

    private void mostrarJugadorActual(){
        nombreJugador.setText( nombreJugadorActual() );
        cambiarElColor();
        colorJugador.setText("Ejército "+colorJugadorActual());
    }

    private void cambiarElColor() {
        String colorDeJugadorActual = juego.jugadorEnTurno().getColor();
        System.out.println(colorDeJugadorActual);

        if (colorDeJugadorActual.equals(COLORES[0])) {
            colorJugador.setTextFill(Color.color(0.0,0.1,1.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[1])) {
            colorJugador.setTextFill(Color.color(0.85,0.0,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[2])) {
            colorJugador.setTextFill(Color.color(0.8,0.7,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[3])) {
            colorJugador.setTextFill(Color.color(0.3,0.70,0.0));
        }
        else if (colorDeJugadorActual.equals(COLORES[4])) {
        colorJugador.setTextFill(Color.color(0.8,0.1,1.0));
        }
        else {
            colorJugador.setTextFill(Color.color(0.0,0.0,0.0));
        }
    }


    private HashMap<String, Integer> nombrePaisesYEjercitosDeJugadorActual (){
        List<Pais> listaPaises = juego.jugadorEnTurno().pedirPaises();
        HashMap<String,Integer> diccionario = new HashMap<>();
        for(Pais unPais: listaPaises){
            diccionario.put(unPais.getNombre(),unPais.cantidadDeFichas());
        }
        return diccionario;
    }



    private String nombreJugadorActual() {
        return juego.jugadorEnTurno().getNombre();
    }
    private String colorJugadorActual() {
        return juego.jugadorEnTurno().getColor();
    }



}