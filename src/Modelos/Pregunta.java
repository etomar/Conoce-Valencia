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
    private Respuesta[] respuestas;
    private int respuesta_correcta;
    private Area area;
    private int veces;
    private int dificultad;
    
    public Pregunta(int codigo, String enunciado, Respuesta[] respuestas, int respuesta_correcta,Area area, int dificultad, int veces){
        this.codigo=codigo;
        this.enunciado=enunciado;
        this.respuestas=respuestas;
        this.respuesta_correcta=respuesta_correcta;
        this.area=area;
        this.dificultad=dificultad;
        this.veces=veces;
    }
    
    public Pregunta(){
        
    }
    
    public int getCodigo(){
        return codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public Respuesta[] getRespuestas() {
        return respuestas;
    }

    public int getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public int getVeces() {
        return veces;
    }

    public Area getArea() {
        return area;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setRespuestas(Respuesta[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setRespuesta_correcta(int respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public void setVeces(int veces) {
        this.veces = veces;
    }
    
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}