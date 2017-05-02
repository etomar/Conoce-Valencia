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
public class PreguntaGrupo extends Pregunta {
    private int respuesta_contestada;

    public PreguntaGrupo(int codigo, String enunciado, String[] respuestas, int respuesta_correcta, Area area, int dificultad, int veces, int respuesta_contestada) {
        super(codigo, enunciado, respuestas, respuesta_correcta, area, dificultad, veces);
        this.respuesta_contestada = respuesta_contestada;
    }

    public void setRespuesta_contestada(int respuesta_contestada) {
        this.respuesta_contestada = respuesta_contestada;
    }
    
    public int getRespuesta_contestada(){
        return respuesta_contestada;
    }
    
    public boolean validar(){
        return (PreguntaGrupo.super.getRespuesta_correcta()==respuesta_contestada);
    }
    
    
    
}
