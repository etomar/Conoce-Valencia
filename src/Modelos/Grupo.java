/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Lliurex
 */
public class Grupo {
    
    private String nombre;
    private int respuestas_correctas;
    
    public Grupo(String nombre){
        
        this.nombre = nombre;
        respuestas_correctas = 0;
        
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRespuestas_correctas() {
        return respuestas_correctas;
    }

    public void setRespuestas_correctas(int respuestas_correctas) {
        this.respuestas_correctas = respuestas_correctas;
    }
    
        public void addRespuestaCorrecta(){
        
        respuestas_correctas ++;
    }
        
}
