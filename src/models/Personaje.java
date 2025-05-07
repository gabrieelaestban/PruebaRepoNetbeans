/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

import java.time.LocalDateTime;

/**
 *
 * @author arkano
 */
public abstract class Personaje {
    private static int contaPersonaje = 0;
    protected int id;
    protected String nombre;
    protected Localizacion localizacion;
    LocalDateTime creacion;

    public Personaje( String nombre, Localizacion localizacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        creacion = LocalDateTime.now();
        id = ++contaPersonaje;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getLongitud() {
        return localizacion.getLongitud();
    }
    
    public int getLatitud() {
        return localizacion.getLatitud();
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }
    
     public void setLocalizacion(int latitud, int logitud) {
        this.localizacion.setLatitud(latitud);
        this.localizacion.setLongitud(logitud);
    }
    
    
    public abstract String mostrar();

}
