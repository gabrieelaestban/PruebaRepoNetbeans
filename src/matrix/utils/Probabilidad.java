/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package matrix.utils;

/**
 *
 * @author arkano
 */
public class Probabilidad {
    /**
     * Obtiene un número aleatorio entre min y max.
     *
     * @param min Número mínimo.
     * @param max Número máximo.
     * @return Número aleatorio entre min y max.
     */
    public static float getRandom(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }
}
