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
public class Pregunta {
    private int codigo;
    private String enunciado;
    private String[] respuestas;
    private int respuesta_correcta;
    private int veces;
    
    public Pregunta(int codigo, String enunciado, String[] respuestas, int respuesta_correcta, int veces){
        this.codigo=codigo;
        this.enunciado=enunciado;
        this.respuestas=respuestas;
        this.respuesta_correcta=respuesta_correcta;
        this.veces=veces;
    }
    
    public int getCodigo(){
        return codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public int getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public int getVeces() {
        return veces;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setRespuesta_correcta(int respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }
}
