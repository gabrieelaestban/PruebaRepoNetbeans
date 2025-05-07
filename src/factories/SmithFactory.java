/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package factories;

import java.util.List;
import matrix.utils.Probabilidad;
import models.Localizacion;
import models.Smith;

/**
 *
 * @author arkano
 */
public class SmithFactory {
    /**
     * Crea un Smith aleatorio.
     *
     * @return Smith aleatorio.
     */
    static final int INFECCION_MAX = 9;
    
    public static Smith createRandom() {
        var ciudad = List.of("Barcelona", "Madrid", "Pekin", "Leganés", "Londres", "Paris");
        var localizacion = new Localizacion(
                -1,
                -1,
                ciudad.get((int) (Math.random() * ciudad.size()))
        );
        return new Smith(
                localizacion,
                // Solo infecta en las 8 direcciones: N, NE, E, SE, S, SW, W, NW
                (int) Probabilidad.getRandom(0, INFECCION_MAX)
        );
    }
}
