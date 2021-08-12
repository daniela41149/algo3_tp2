package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadInvalidaDeEjercitosException;
import edu.fiuba.algo3.modelo.excepciones.JugadaInvalidaException;
import edu.fiuba.algo3.modelo.pais.Pais;
import edu.fiuba.algo3.modelo.tarjetaObjetivo.TarjetaObjetivo;
import edu.fiuba.algo3.modelo.tarjetaPais.TarjetaPais;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Juego {

    static final int CANT_EJERCITOS_EN_PRIMERA_VUELTA = 5;
    static final int CANT_EJERCITOS_EN_SEGUNDA_VUELTA = 3;
    static final int MIN_EJERCITOS_PERMITIDOS_PARA_COLOCAR = 3;
    static final String[] COLORES = {"Azul", "Rojo", "Amarillo", "Verde", "Rosa", "Negro"};

    private Tablero tablero;
    private List<Jugador> jugadores;
    private int posicionJugadorEnTurno;
    private int ejercitosColocadosPorJugadorEnTurno;
    private int ejercitosColocadosEnPrimeraVuelta;
    private int ejercitosColocadosEnSegundaVuelta;
    private List<TarjetaPais> mazoTarjetasPais;
    private List<TarjetaObjetivo> mazoTarjetasObjetivo;


    public Juego(List<Pais> paises, List<Continente> continentes, List<String> nombresDeJugadores) {

        this.posicionJugadorEnTurno = 0;
        this.ejercitosColocadosEnPrimeraVuelta = 0;
        this.ejercitosColocadosEnSegundaVuelta = 0;
        this.ejercitosColocadosPorJugadorEnTurno = 0;
        this.tablero = new Tablero(paises, continentes);
        this.jugadores = new LinkedList<>();
        for (int i = 0; i < nombresDeJugadores.size(); i++)
            jugadores.add(new Jugador(nombresDeJugadores.get(i), COLORES[i], this));
    }

    public void guardarMazoDeTarjetasPais(List<TarjetaPais> tarjetasPais){
        this.mazoTarjetasPais = tarjetasPais;
    }

    public void guardarMazoDeTarjetasObjetivo(List<TarjetaObjetivo> tarjetasObjetivo) { this.mazoTarjetasObjetivo = tarjetasObjetivo; }

    public void comenzarFaseInicial(Aleatorio aleatorio) throws JugadaInvalidaException {
        ArrayList<Pais> paisesSinRepartir = new ArrayList<>(tablero.pasarPaisesAJuego());

        posicionJugadorEnTurno = aleatorio.elegirPosicionDelJugadorQueEmpieza(jugadores.size());

        List<List<Pais>> paisesRepartidos = aleatorio.repartirPaisesAleatoriamente(jugadores.size(), paisesSinRepartir);
        for(int indice = 0;indice<jugadores.size();indice++) {
            List<Pais> listaPaisesParaJugador = paisesRepartidos.get(indice);
            for (Pais paisActual : listaPaisesParaJugador) {
                jugadores.get(indice).agregarPais(paisActual);
                paisActual.colocarEjercito(jugadores.get(indice),1);
            }
        }

        for (Jugador jugador : jugadores) {
            TarjetaObjetivo tarjetaObjetivoAleatoria = aleatorio.agarrarTajetaObjetivoAleatoriaDelMazo(mazoTarjetasObjetivo);
            jugador.establecerObjetivo(tarjetaObjetivoAleatoria);
        }
    }

    public Jugador jugadorEnTurno() {
        return jugadores.get(posicionJugadorEnTurno);
    }

    public void pasarTurno(){
        posicionJugadorEnTurno++;
        if (posicionJugadorEnTurno >= jugadores.size())
            posicionJugadorEnTurno = 0;
        ejercitosColocadosPorJugadorEnTurno = 0;
        ejercitosColocadosEnPrimeraVuelta = 0;
        ejercitosColocadosEnSegundaVuelta = 0;
    }

    private int establecerCantidadDeEjercitosPermitidosParaColocar(Jugador jugadorEnTurno) {
        int paisesDominados = jugadorEnTurno.cantidadPaises();

        int cantidadTotal = MIN_EJERCITOS_PERMITIDOS_PARA_COLOCAR;
        if (paisesDominados >= 6)
            cantidadTotal = (paisesDominados / 2);
        cantidadTotal += tablero.ejercitosAdicionalesPorContinentesControlados(jugadorEnTurno);

        if (jugadorEnTurno.ejercitosDeCanje() > 0) {
            ejercitosColocadosPorJugadorEnTurno -= jugadorEnTurno.ejercitosDeCanje();
            jugadorEnTurno.borrarEjercitosDeCanje();
        }

        return cantidadTotal;
    }

    public void colocarEjercitoPrimeraVuelta(String nombreDePais, int cantidadEjercitos) throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        int totalEjercitos = ejercitosColocadosEnPrimeraVuelta + cantidadEjercitos;
        if (totalEjercitos > CANT_EJERCITOS_EN_PRIMERA_VUELTA)
            throw new CantidadInvalidaDeEjercitosException();

        jugadorEnTurno().colocarEjercito(nombreDePais, cantidadEjercitos);
        ejercitosColocadosEnPrimeraVuelta += cantidadEjercitos;
        if (ejercitosColocadosEnPrimeraVuelta == CANT_EJERCITOS_EN_PRIMERA_VUELTA)
            pasarTurno();
    }

    public void colocarEjercitoSegundaVuelta(String nombreDePais, int cantidadEjercitos) throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        int totalEjercitos = ejercitosColocadosEnSegundaVuelta + cantidadEjercitos;
        if (totalEjercitos > CANT_EJERCITOS_EN_SEGUNDA_VUELTA)
            throw new CantidadInvalidaDeEjercitosException();

        jugadorEnTurno().colocarEjercito(nombreDePais, cantidadEjercitos);
        ejercitosColocadosEnSegundaVuelta += cantidadEjercitos;
        if (ejercitosColocadosEnSegundaVuelta == CANT_EJERCITOS_EN_SEGUNDA_VUELTA)
            pasarTurno();
    }

    public void colocarEjercito(String nombrePais, int cantidadEjercito) throws JugadaInvalidaException, CantidadInvalidaDeEjercitosException {
        Jugador jugadorEnTurno = jugadorEnTurno();
        int cantidadEjercitosPermitidosParaColocar = establecerCantidadDeEjercitosPermitidosParaColocar(jugadorEnTurno);

        if (cantidadEjercito > cantidadEjercitosPermitidosParaColocar-ejercitosColocadosPorJugadorEnTurno)
            throw new CantidadInvalidaDeEjercitosException();

        jugadorEnTurno.colocarEjercito(nombrePais, cantidadEjercito);
        ejercitosColocadosPorJugadorEnTurno += cantidadEjercito;
    }

    public void atacar(String nombrePaisAtacante, String nombrePaisDefensor, int cantidadDeEjercitoAtacante) throws JugadaInvalidaException {
        tablero.atacar(nombrePaisAtacante, nombrePaisDefensor, cantidadDeEjercitoAtacante);
    }

    public List<Jugador> devolverJugadores(){
        return this.jugadores;
    }

    public int devolverEjercitosRestantesDeJugadorActual() {
        int ejercitos = sumarEjercitosTotales();
        int ejercitosFinalPrimeraRonda = jugadores.size()*CANT_EJERCITOS_EN_PRIMERA_VUELTA+50;
        int ejercitosFinalSegundaRonda = jugadores.size()*CANT_EJERCITOS_EN_PRIMERA_VUELTA+jugadores.size()*CANT_EJERCITOS_EN_SEGUNDA_VUELTA+50;

        if ((ejercitos < ejercitosFinalSegundaRonda) && (ejercitos >= ejercitosFinalPrimeraRonda)) {
            return CANT_EJERCITOS_EN_SEGUNDA_VUELTA-ejercitosColocadosEnSegundaVuelta;
        }
        else if (ejercitos < ejercitosFinalPrimeraRonda) {
            return CANT_EJERCITOS_EN_PRIMERA_VUELTA-ejercitosColocadosEnPrimeraVuelta;
        }
        return establecerCantidadDeEjercitosPermitidosParaColocar(jugadorEnTurno())-ejercitosColocadosPorJugadorEnTurno;
    }

    private int sumarEjercitosTotales() {
        List<Pais> paises = tablero.pasarPaisesAJuego();
        int ejercitosTotales = 0;
        for (Pais pais : paises) {
            ejercitosTotales += pais.cantidadDeFichas();
        }
        return ejercitosTotales;
    }

    public boolean controlaContiente (String nombreJugador, String nombreContinente) {
        for (Jugador unJugador: this.jugadores) {
            if (unJugador.getNombre().equals(nombreJugador)) {
                return tablero.controlaContinente(unJugador,nombreContinente);
            }
        }
        return false;
    }

    private Jugador buscarJugadorPorColor(String colorBuscado) {
        int i = 0;
        boolean encontro = false;
        Jugador jugadorBuscado = null;

        while (!encontro && i < jugadores.size()){
            if (jugadores.get(i).getColor().equals(colorBuscado)){
                jugadorBuscado = jugadores.get(i);
                encontro = true;
            }
            i++;
        }
        return jugadorBuscado;
    }

    public Jugador jugadorDeColorOSiguiente(String colorBuscado, Jugador unJugador) {
        Jugador jugadorBuscado = buscarJugadorPorColor(colorBuscado);

        if (jugadorBuscado == null || jugadorBuscado.esElMismo(unJugador)) {
            int indiceDejugadorSiguiente = jugadores.indexOf(unJugador);
            if (indiceDejugadorSiguiente == jugadores.size())
                indiceDejugadorSiguiente = 0;
            jugadorBuscado = jugadores.get(indiceDejugadorSiguiente);
        }

        return jugadorBuscado;
    }

    public boolean cumplioObjetivo(Jugador jugador) {
        if (jugador == null)
            return false;
        return (jugador.cumplioObjetivo(tablero));
    }


    public void reagrupar(String nombrePaisDesde, String nombrePaisHasta, int cantidadDeEjercito) {
        List<Pais> paises = jugadorEnTurno().pedirPaises();
        Pais paisHasta = tablero.buscarPais(nombrePaisHasta);
        for (Pais unPaisDesde: paises){
            if (unPaisDesde.getNombre().equals(nombrePaisDesde) && unPaisDesde.esLimitrofe(paisHasta)){
                unPaisDesde.sacarFichas(cantidadDeEjercito);
                paisHasta.colocarEjercito(jugadorEnTurno(),cantidadDeEjercito);
            }
        }
    }

    public TarjetaPais entregaTarjetaPais() {
        Aleatorio aleatorio = new Aleatorio();

        return aleatorio.agarrarTajetaPaisAleatoriaDelMazo(mazoTarjetasPais);
    }

    public void devolverTarjetasAlMazo(List<TarjetaPais> tarjetasPais) {
        for (TarjetaPais unaTarjetaPais: tarjetasPais) {
            mazoTarjetasPais.add(unaTarjetaPais);
        }
    }

    public List<Pais> devolvePaises(){
        return tablero.pasarPaisesAJuego();
    }









}

