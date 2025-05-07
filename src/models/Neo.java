/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

import matrix.utils.Color;
import matrix.utils.Probabilidad;

/**
 *
 * @author arkano
 */
public class Neo extends Personaje{
    
    public Neo(String nombre, Localizacion localizacion) {
        super(nombre, localizacion);
    }

    public Neo() {
        super("Neo", new Localizacion(0, 0, "Punta Umbría"));
    }

    /**
     * Método que devuelve una probabilidad de que el personaje indique si es el Elegido.
     *
     * @return
     */
    public boolean isElegido() {
        return Probabilidad.getRandom(0, 100) < 50;
    }

    @Override
    public String toString() {
        return Color.verde + "N" + Color.normal;
    }

    @Override
    public String mostrar() {
        return "Neo " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", localizacion: " + localizacion +
                ", createdAt: " + creacion;
    }

    
   

}
