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
public class Generico extends Personaje {
    private double probabilidadMorir;

    public Generico( String nombre, Localizacion localizacion) {
        super(nombre, localizacion);
        probabilidadMorir = Probabilidad.getRandom(0, 100);
    }

    public double getProbabilidadMorir() {
        return probabilidadMorir;
    }

    public void setProbabilidadMorir(double probabilidadMorir) {
        if (probabilidadMorir < 0)
            this.probabilidadMorir = 0;
        else 
            this.probabilidadMorir = probabilidadMorir;
    }
    
    @Override
    public String toString() {
        return Color.cian + "O" + Color.normal ;
    }

    @Override
    public String mostrar() {
        return "Generico " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", localizacion: " + localizacion +
                ", createdAt: " + creacion +
                ", probMorir: " + String.format("%.2f", probabilidadMorir);
                
    }

    
}
