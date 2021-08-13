package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ControladorResultadosDeBatalla {


    private Jugador jugador;
    private List<Pais> paisesEnTablero;
    private String nombrePaisAtacante;


    ListView<String> listaLimitrofes;
    List<ImageView> imagenesDadosAtacantes = new ArrayList<ImageView>();
    List<ImageView> imagenesDadosDefensores = new ArrayList<ImageView>();
    List<Image> imagenes = new ArrayList<Image>();// ../ para ir hacia atras
    Image imagenDado1 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_1.png") ) ;
    Image imagenDado2 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_2.png") ) ;
    Image imagenDado3 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_3.png") ) ;
    Image imagenDado4 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_4.png") ) ;
    Image imagenDado5 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_5.png") ) ;
    Image imagenDado6 = new Image( getClass().getResourceAsStream("/vista/imagenes_dado/dice_6.png") ) ;


    @FXML
    private ImageView dadoAtacanteUno;

    @FXML
    private ImageView dadoAtacanteDos;

    @FXML
    private ImageView dadoAtacanteTres;

    @FXML
    private ImageView dadoDefensorUno;

    @FXML
    private ImageView dadoDefensorDos;

    @FXML
    private ImageView dadoDefensorTres;

    @FXML
    private Label labelGanador;

    @FXML
    private Label labelNombrePaisAtacante;

    @FXML
    private Label labelNombrePaisDefensor;

    @FXML
    private Label labelEjercitosPaisAtacante;

    @FXML
    private Label labelEjercitosPaisDefensor;

    @FXML
    private Button botonCerrar;











    @FXML
    public void cerrar(ActionEvent event){
        listaLimitrofes.getItems().clear();
        mostrarLimitrofesActualesParaAtacar(nombrePaisAtacante);
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.close();
    }


    public void mostrarResultados(List<Integer> dadosAtacante, List<Integer> dadosDefensor) {
        iniciarListasDeImagenes();
        mostrarDados(dadosAtacante, imagenesDadosAtacantes);
        mostrarDados(dadosDefensor, imagenesDadosDefensores);
    }

    public void mostrarDatos(String paisAtacante, int ejercitosAtacante, String paisDefensor, int ejercitosDefensor, ListView listaLimitrofes, List<Pais> paisesEnTablero) {
        this.listaLimitrofes = listaLimitrofes;
        this.nombrePaisAtacante = paisAtacante;
        this.paisesEnTablero = paisesEnTablero;
        labelNombrePaisAtacante.setText(paisAtacante);
        labelNombrePaisDefensor.setText(paisDefensor);
        labelEjercitosPaisAtacante.setText(String.valueOf(ejercitosAtacante));
        labelEjercitosPaisDefensor.setText(String.valueOf(ejercitosDefensor));
    }

    public void mostrarJugadorQueConquistoPais(Jugador jugador, String nombrePaisDefensor) {
        this.jugador = jugador;
        if(jugador.esDueñoDelPais(nombrePaisDefensor)) {
            String frase = jugador.getNombre()+" conquistó "+ nombrePaisDefensor;
            labelGanador.setText(frase);
        }
    }



    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paisesEnTablero) {
            if (unPais.esLimitrofe(paisBuscado) && !jugador.esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
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

    private void mostrarLimitrofesActualesParaAtacar(String nombre) {
        HashMap<String, Integer> limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaAtacar(nombre);
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }


    private void mostrarDados(List<Integer> dados, List<ImageView> imagenesVacias) {
        for ( int i = 0; i < dados.size(); i++ )
            asignarImagen( dados.get(i), i , imagenesVacias );
    }

    private void asignarImagen( int numeroDado, int posicion, List<ImageView> imagenesVacias ) {
        ( imagenesVacias.get(posicion) ).setImage( imagenes.get( numeroDado-1 ) );
    }

    private void iniciarListasDeImagenes() {
        imagenes.addAll(  Arrays.asList( imagenDado1, imagenDado2, imagenDado3, imagenDado4, imagenDado5, imagenDado6 )  );
        imagenesDadosAtacantes.add(dadoAtacanteUno);
        imagenesDadosAtacantes.add(dadoAtacanteDos);
        imagenesDadosAtacantes.add(dadoAtacanteTres);
        imagenesDadosDefensores.add(dadoDefensorUno);
        imagenesDadosDefensores.add(dadoDefensorDos);
        imagenesDadosDefensores.add(dadoDefensorTres);
    }






}
