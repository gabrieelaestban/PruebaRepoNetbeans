/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package comparators;

import java.util.Comparator;
import models.Personaje;

/**
 *
 * @author arkano
 * 
 * Comprador de personas por fecha de creación en el sistema
 */
public class PersonajeFechaComparator implements Comparator<Personaje> {
    public int compare(Personaje o1, Personaje o2) {
        return o2.getCreacion().compareTo(o1.getCreacion());
    }

    @Override
    public Comparator<Personaje> reversed() {
        return Comparator.super.reversed();
    }
}