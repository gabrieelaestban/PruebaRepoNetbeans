/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package thematrixcontroller;

import comparators.PersonajeFechaComparator;
import factories.GenericoFactory;
import factories.SmithFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import matrix.utils.Inputs;
import models.Generico;
import models.Neo;
import models.Personaje;
import models.Smith;



/**
 * Clase que simula Matrix con las condiciones pedidas
 * 
 **/

public class TheMatrixController {
    final int TIEMPO_MAX = 60;   // 300 segundos = 5 min.
    LinkedList<Generico> disponibles = new LinkedList<>();
    ArrayList<Personaje> usados = new ArrayList<>();
    ArrayList<Personaje> infectados = new ArrayList<>();
    Personaje[][] matrix;
    Personaje[][] matrixAux;
    int dimension;
    int numPersonajes = 0;
    ArrayList<String> log = new ArrayList();
    Neo neo;
    final int INC_PROB_MORIR = 10;   // Cantidad en la que desciende la probabilidad de morir Genéricos 
    
    public TheMatrixController(int dimension){
        matrix =  new Personaje[dimension][dimension];
        this.dimension = dimension;
        
    }
    
    /**
     *  Método que incicializa Matrix
     */
    public void iniciar(){
        iniciaDisponibles();
        inicializaMatrix();
        pintaMatrix();        // Estado inicial de matrix
    }
    
    private void inicializaMatrix(){
        ponNeo();
        ponSmith();
        ponGenericos();
    }    
    
    /** 
     *  Crea y pone en Matrix a Neo
     */
    private void ponNeo(){
            int fil = (int) (Math.random() * dimension);
            int col = (int) (Math.random() * dimension);
            neo = new Neo();
            actualiazLocalicacion(neo, fil, col);
            matrix[fil][col] = neo;
            usados.add(neo);
            numPersonajes++;
    }
    
    /** 
     *  Crea y pone en Matrix el primer Smith
     */
    private void ponSmith(){
        int fil,col;
        do {
            fil = (int) (Math.random() * dimension);
            col = (int) (Math.random() * dimension);
        }while (matrix[fil][col] != null );
        Smith smith = SmithFactory.createRandom();
        actualiazLocalicacion(smith, fil, col);
        matrix[fil][col] = smith;
        usados.add(smith);
        numPersonajes++;
    }
    
    /** 
     *  Colola lo genéricos en los lugaras libres de Matrix
     */
    private void ponGenericos(){
        for (int f = 0; f < dimension; f++){
            for (int c = 0; c < dimension; c++){
                if (matrix[f][c] == null){
                    Generico generico = disponibles.poll();
                    actualiazLocalicacion(generico, f, c);
                    matrix[f][c] = generico;
                    usados.add(generico);
                    numPersonajes++;
                }
            }
        }
    }
    
    /** 
     *  Carga la lista de dispobnibles con Genéricos
     */
    private void iniciaDisponibles(){
        for (int i = 0; i < 200; i++){
            disponibles.add(GenericoFactory.createRandom());
        }
    }

    
    /**
     *  Pinta en pantalla el estado actual de Matrix
     */
    private void pintaMatrix(){
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < dimension; f++) {
            for (int c = 0; c < dimension; c++) {
                if (c == 0){
                    sb.append("|");
                }
                if (matrix[f][c]!= null) {
                    sb.append(matrix[f][c]).append("|");
                } else {
                   sb.append(" |");
                }
            }
            sb.append("\n");
        }
        limpiaPantalla();
        System.out.println(sb);
    }
    /**
     *  Limpia la pantalla
     */
    private void limpiaPantalla(){
        for (int i = 0; i < 30; i++)
            System.out.println();
    }
    
    /** Método que simula la acción en Matrix
     * 
     */
    public void simular(){
        System.out.println("¡¡¡Welcome to The Matrix!!!");
        Inputs.getString("Comenzar la simulación ....");
        boolean salir = false;
        int tiempo = 0;
        do{
            try{
                Thread.sleep(1000);
                tiempo++;
            } catch (InterruptedException e ){
                System.out.println("Error en el sistema, matrix está corrupta!!!");
            }
            accionGenericos();
            if (tiempo % 2 == 0)
                accionSmith();
            if (tiempo % 5 == 0){
                accionNeo();
                
            }
            if (tiempo % 10 == 0)
                añadePersonajes();
            
            pintaMatrix();
            salir = tiempo > TIEMPO_MAX ;//|| disponibles.isEmpty();
        } while (!salir);
    }
    
    /** 
     *  Método que localiza los Genéricos de Matrix y los trata 
     */
    private void accionGenericos(){
        for (int f = 0; f < dimension; f++){
            for (int c = 0; c < dimension; c++){
                if (matrix[f][c] instanceof Generico){
                    trataGenerico(f,c);
                }
            }
        }
        
    }
    
    /**
     * Método que trata el Genérico de la fila y columna recibida 
     * @param f
     * @param c 
     */
    private void trataGenerico( int f, int c){
        Generico g = (Generico)matrix[f][c];
        double probabilidad = g.getProbabilidadMorir();
        if (probabilidad < 30){
            Generico nG = disponibles.poll();
            String cadenaLog = "Muere " + g.getNombre() + " en:[" + (g.getLatitud()-1) + "," + (g.getLongitud()-1) + "]";
            if (nG != null){
                cadenaLog += ". Es sustituido por " + nG.getNombre();
                actualiazLocalicacion(nG, f, c);
                matrix[f][c] = nG;
            } else {
               cadenaLog += ".La lista de disponibles está vacía!!!.";
                matrix[f][c] = null;
            }
            log.add(cadenaLog); 
        } else {
            g.setProbabilidadMorir(probabilidad-INC_PROB_MORIR);
        }
    }

    /** 
     *  Método que busca los virus Smith de la tabla y los trata
     */    
    private void accionSmith(){
        clonaMatrix();
        for (int f = 0; f < dimension; f++){
            for (int c = 0; c < dimension; c++){
                if (matrix[f][c] instanceof Smith){
                    trataSmith(f,c); 
                }
            }
        }
        matrix = matrixAux;
    }
    
    /** Trata el virus Smith en fila y columna recibida
     * 
     * @param f Fila del Smith a tratar
     * @param c Columna del Smith a tratar
     */
    private void trataSmith(int f, int c){
        Smith s = (Smith)matrix[f][c];
        int infecciones = (int) (Math.random() * (s.getProbInfectar() + 1));
        String cadenaLog = "Agente Smith " + s.getNombre() + ". Intentos: " + infecciones +
                           " desde posición [" + (f+1) + "," + (c+1) +"]";
        log.add(cadenaLog);
        int veces = 0;
        do {
            int iF = ((int) (Math.random() * 3))-1;
            int iC = ((int) (Math.random() * 3))-1;
            if (f + iF >= 0 && f + iF < dimension && c + iC >= 0 && c + iC < dimension){
                if (matrix[f+iF][c+iC] instanceof Generico){
                    cadenaLog = "Agente Smith " + s.getNombre() + " infecta individuo en " +
                                "[" + (f + iF + 1) + "," + (c + iC + 1) +"]";
                    log.add(cadenaLog);
                    Smith smith = SmithFactory.createRandom();
                    matrixAux[f+iF][c+iC] = smith;
                    usados.add(smith);
                    infectados.add(smith);
                    actualiazLocalicacion(smith, f + iF, c + iC);
                    numPersonajes++;
                }
            }
            
            if ( !(iF == 0 && iC == 0)) veces++;
            
        } while (veces < infecciones);
        
        
    }
    
    private void accionNeo(){
        String cadenaLog = "Neo decide ";
        if (neo.isElegido()){
            cadenaLog += "actuar.";
            log.add(cadenaLog);
            actuaNeo();
        } else{
            cadenaLog += "no actuar.";
            log.add(cadenaLog);
        }
        mueveNeo();
    }

    private void actuaNeo(){
        String cadenaLog;
        int f = neo.getLatitud()-1;
        int c = neo.getLongitud()-1;
        for (int iF = -1; iF < 2; iF++){
            for (int iC = -1; iC < 2; iC++){
                if (f + iF >= 0 && f + iF < dimension && c + iC >= 0 && c + iC < dimension)
                    if (matrix[f+iF][c+iC] instanceof Smith){
                        matrix[f+iF][c+iC] = null;
                        cadenaLog = "Neo mata Smith en [" + (f + iF + 1) + "," +(c + iC + 1) + "].";
                        log.add(cadenaLog);
                    }
            }
        }
    }
    
    private void mueveNeo(){
        String cadenaLog;
        int fil;
        int fAnt = neo.getLatitud()-1;
        int col;
        int cAnt = neo.getLongitud()-1;
        do {
            fil = (int) (Math.random() * dimension);
            col = (int) (Math.random() * dimension); 
        }while (fil == fAnt && col == cAnt);
        cadenaLog = "Neo salta a posición [" + (fil +1) + "," + (col+1) + "] desde posición [" + (fAnt + 1)
                    + "," + (cAnt+1) + "]";
        log.add(cadenaLog);
        if (matrix[fil][col] instanceof Generico){
            actualiazLocalicacion((Generico)matrix[fil][col], fAnt, cAnt);
            matrix[fAnt][cAnt] = matrix[fil][col];
            
        } else{
            matrix[fAnt][cAnt] = null;
        } 
        actualiazLocalicacion(neo, fil, col);
        matrix[fil][col] = neo;
        
    }
    
    private void clonaMatrix(){
        matrixAux = new Personaje[dimension][dimension];
        for (int f = 0; f < dimension; f++){
            for (int c = 0; c < dimension; c++){
                matrixAux[f][c] = matrix[f][c];
            }
        }
    }
    
    
    private void añadePersonajes(){
        String cadenaLog;
        int contador = 0;
        boolean seguir = true;
        for (int f = 0; f < dimension && seguir; f++){
            for (int c = 0; c < dimension; c++){
                if (matrix [f][c] == null){
                    if (!disponibles.isEmpty() && contador < 5){
                        Generico nG = disponibles.poll();
                        cadenaLog = "Aparece un nuevo generico llamado " + nG.getNombre() + 
                                " en la posición [" + (f+1) + "," + (c+1) + "]";
                        log.add(cadenaLog);
                        actualiazLocalicacion(nG, f, c);
                        matrix[f][c] = nG;
                        usados.add(nG);
                        contador++;
                    } else {
                        seguir = false;
                    }
                }
            }
            
        }
    }
    
    /** Método que muetra los log de acción en Matrix así 
     *  como los listados de personajes usado y de virus Smith
     */
    public void resumen(){
        Inputs.getString("Mostrar resumen de la simulación ....");
        log.forEach(System.out::println);
        Inputs.getString("Mostrar Personajes usados ....");
        usados.forEach(p -> System.out.println(p.mostrar()));
        Inputs.getString("Mostrar lista de infectados ....");
        infectados.sort(new PersonajeFechaComparator());         
        infectados.forEach(p -> System.out.println(p.mostrar()));
        System.out.println("Número de personjes creados: " + numPersonajes);
    }
    
    /**
     * Actualiza la localización de un personaje en la matriz
     *
     * @param persona personaje a actualizar
     * @param fila    fila donde se encuentra es la latitud
     * @param columna columna donde se encuentra es la longitud
     */
    private void actualiazLocalicacion(Personaje persona, int fila, int columna) {
        if (persona != null) {
            persona.setLocalizacion(fila + 1, columna + 1);
        }
    }
}
