/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thematrix;

import thematrixcontroller.TheMatrixController;

/**
 *
 * @author arkano
 */
public class TheMatrix {

    static final int DIMENSION = 10;

    public static void main(String[] args) {
        TheMatrixController matrix = new TheMatrixController(DIMENSION);
        matrix.iniciar();
        matrix.simular();
        matrix.resumen();
    }
}
