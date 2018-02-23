package com.example.firebaseprueba_23_02;

/**
 * Created by Vicente FN on 23/02/2018.
 */

public class CJugador {

    String nombre;
    int dorsal;
    String posicion;
    double sueldo;


    public CJugador(){
        //Es obligatorio incluir constructor vacío por defecto
    }

    public CJugador(String nombre, int dorsal, String posicion, double sueldo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
