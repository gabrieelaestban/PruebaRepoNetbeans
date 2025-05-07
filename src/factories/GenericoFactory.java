/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package factories;

import java.util.List;
import matrix.utils.Probabilidad;
import models.Generico;
import models.Localizacion;

/**
 *
 * @author arkano
 */
public class GenericoFactory {

 /**
     * Crea un objeto de tipo Generico con datos aleatorios.
     *
     * @return Objeto de tipo Generico.
     */
    public static Generico createRandom() {
        var nombre = List.of("Pepe", "Juan", "Maria", "Jose", "Ana", "Sonia");
        var ciudad = List.of("Madrid", "New York", "Pekin", "Leganés", "Londres", "Paris");
        var localizacion = new Localizacion(
                -1,
                -1,
                ciudad.get((int) (Math.random() * ciudad.size()))
        );
        return new Generico(
                nombre.get((int) (Math.random() * nombre.size())),
                localizacion
        );
    }
}