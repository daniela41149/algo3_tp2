package controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Moderador;
import edu.fiuba.algo3.modelo.pais.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;


public class ControladorReagruparEjercitos {




    @FXML
    private Label labelPaisDesde;

    @FXML
    private Label labelPaisHasta;

    @FXML
    private Button botonReagrupar;

    @FXML
    private Button botonPasarUno;

    @FXML
    private Button botonRestarUno;

    @FXML
    private Label labelEjercitoPaisDesde;

    @FXML
    private Label labelEjercitoPaisHasta;




    private Juego juego;
    private Moderador moderador;
    private int suma = 0;
    private String nombrePaisDesde;
    private String nombrePaisHasta;
    private int ejercitosDesde;
    private int ejercitosHasta;
    private HashMap<String, Integer> limitrofesConEjercitos;
    private ListView<String> listaLimitrofes;





    public void reagrupar(Juego juego, Moderador moderador,String nombrePaisDesde, int ejercitosDesde, String nombrePaisHasta, int ejercitosHasta, ListView<String> listaLimitrofes, HashMap<String, Integer> limitrofesConEjercitos) {
        this.juego = juego;
        this.moderador = moderador;
        this.nombrePaisDesde = nombrePaisDesde;
        this.nombrePaisHasta = nombrePaisHasta;
        this.ejercitosDesde = ejercitosDesde;
        this.ejercitosHasta = ejercitosHasta;
        this.listaLimitrofes = listaLimitrofes;
        this.limitrofesConEjercitos = limitrofesConEjercitos;
        labelPaisDesde.setText(nombrePaisDesde);
        labelPaisHasta.setText(nombrePaisHasta);
        labelEjercitoPaisDesde.setText(String.valueOf(ejercitosDesde));
        labelEjercitoPaisHasta.setText(String.valueOf(ejercitosHasta));

    }


    @FXML
    public void pasarUno(ActionEvent event){
        if (ejercitosDesde-suma > 1) {
            suma ++;
        }
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
        String numDesde= String.valueOf(ejercitosDesde-suma);
        String numHasta= String.valueOf(ejercitosHasta+suma);
        labelEjercitoPaisDesde.setText(numDesde);
        labelEjercitoPaisHasta.setText(numHasta);
    }

    @FXML
    public void reagrupar(ActionEvent event){
        juego.reagrupar(nombrePaisDesde,nombrePaisHasta,suma);
        listaLimitrofes.getItems().clear();
        mostrarLimitrofesActualesParaReagrupar(nombrePaisDesde);

        Stage stage = (Stage) botonReagrupar.getScene().getWindow();
        stage.close();
    }


    private void mostrarLimitrofesActualesParaReagrupar(String nombre){
        limitrofesConEjercitos = nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(nombre);
        limitrofesConEjercitos.forEach( (nombrePais,cantidadEjercito) -> listaLimitrofes.getItems().add( nombrePais+ "  "+cantidadEjercito.toString() ) );
    }


    private HashMap<String, Integer> nombrePaisYEjercitosDePaisesLimitrofesParaReagrupar(String nombrePais) {
        HashMap<String,Integer> paisesLimitrofes = new HashMap<>();
        List<Pais> paises = moderador.pedirPaises();
        Pais paisBuscado = buscarPais(nombrePais);
        for (Pais unPais: paises) {
            if (unPais.esLimitrofe(paisBuscado) && juego.jugadorEnTurno().esDueñoDelPais(unPais.getNombre())) {
                paisesLimitrofes.put(unPais.getNombre(),unPais.cantidadDeFichas());
            }
        }
        return paisesLimitrofes;
    }


    private Pais buscarPais(String nombrePaisBuscado) {
        List<Pais> paises = moderador.pedirPaises();
        for (Pais unPais : paises) {
            if (unPais.getNombre().equals((nombrePaisBuscado)))
                return unPais;
        }
        return null;
    }

}