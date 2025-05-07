/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package matrix.utils;

import java.util.Scanner;

/**
 *
 * @author arkano
 */
public class Inputs {
    /**
     * Lee una cadena de caracteres.
     *
     * @param message Mensaje a mostrar en el terminal.
     * @return Cadena de caracteres leída desde el terminal
     */
    public static String getString(String message) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(message);
        
        
        return scanner.nextLine();
    }

}
