/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

/**
 *
 * @author arkano
 */
public class Localizacion {
    protected int latitud;
    protected int longitud;
    protected String ciudad;

    public Localizacion(int latitud, int longitud, String ciudad) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "Localizacion: [" + (latitud+1) +"," + (longitud +1) + "], ciudad:" + ciudad + '}';
    }
    

}
