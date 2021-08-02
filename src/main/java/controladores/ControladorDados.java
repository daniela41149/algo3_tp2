package controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.modelo.Dados;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorDados{

    Integer fichasAtaque = 0;
    Integer fichasDefensa = 0;
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
    private Button boton;

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

    private void iniciarListasDeImagenes() {
        imagenes.addAll(  Arrays.asList( imagenDado1, imagenDado2, imagenDado3, imagenDado4, imagenDado5, imagenDado6 )  );
        imagenesDadosAtacantes.add(dadoAtacanteUno);
        imagenesDadosAtacantes.add(dadoAtacanteDos);
        imagenesDadosAtacantes.add(dadoAtacanteTres);
        imagenesDadosDefensores.add(dadoDefensorUno);
        imagenesDadosDefensores.add(dadoDefensorDos);
        imagenesDadosDefensores.add(dadoDefensorTres);
    }

    @FXML
    private void lanzar() {
        Dados dados = new Dados();
        List<Integer> dadosAtacante = dados.dadosAtaque(fichasAtaque);
        List<Integer> dadosDefensor = dados.dadosDefensa(fichasDefensa);
        iniciarListasDeImagenes();
        mostrarDados(dadosAtacante, imagenesDadosAtacantes);
        mostrarDados(dadosDefensor, imagenesDadosDefensores);
    }

    private void mostrarDados(List<Integer> dados, List<ImageView> imagenesVacias) {
        for ( int i = 0; i < dados.size(); i++ )
            asignarImagen( dados.get(i), i , imagenesVacias );
    }

    private void asignarImagen( int numeroDado, int posicion, List<ImageView> imagenesVacias ) {
        ( imagenesVacias.get(posicion) ).setImage( imagenes.get( numeroDado-1 ) );
    }

    public void asignarFichas(Integer fichasAtaque, Integer fichasDefensa){
        this.fichasAtaque = fichasAtaque;
        this.fichasDefensa = fichasDefensa;
    }
}
