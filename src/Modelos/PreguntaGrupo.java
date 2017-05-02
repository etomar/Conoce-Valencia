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

    public PreguntaGrupo(int codigo, String enunciado, String[] respuesta, int respuesta_correcta, int dificultad, int respuesta_contestada) {
        super(codigo, enunciado, respuesta, respuesta_correcta, dificultad);
        this.respuesta_contestada = respuesta_contestada;
    }

    public void setRespuesta_contestada(int respuesta_contestada) {
        this.respuesta_contestada = respuesta_contestada;
    }
    
    public int getRespuesta_contestada(){
        return respuesta_contestada;
    }
    
    public boolean validar(){
        boolean correcta;
        
        if(PreguntaGrupo.super.getRespuesta_correcta()==respuesta_contestada)
            correcta=true;
        else
            correcta=false;
        
        return correcta;
    }
    
    
    
}
