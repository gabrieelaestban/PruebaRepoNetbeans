/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

import matrix.utils.Color;

/**
 *
 * @author arkano
 */
public class Smith  extends Personaje{

private final int probInfectar;

    public Smith(Localizacion localizacion, int probInfectar) {
        super("Smith", localizacion);
        this.probInfectar = probInfectar;
        setNombre(nombre + "-" + id);
    }

    public int getProbInfectar() {
        return probInfectar;
    }

    @Override
    public String toString() {
        return Color.rojo + "S" + Color.normal;
    }
   

    @Override
    public String mostrar() {
        return "Smith " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", localizacion: " + localizacion +
                ", createdAt: " + creacion +
                ", probInfectar: " + probInfectar;
    }
}