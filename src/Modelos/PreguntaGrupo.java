/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import ConexionDB.PreguntaDAO;
import java.sql.SQLException;

/**
 * Crea el objeto PreguntaGrupo que hereda de pregunta y contiene el metedo incremetarVeces() y validar().
 * @author Lluis Navarro
 */
public class PreguntaGrupo extends Pregunta {
    private int respuesta_contestada=0;

    /**
     * Creo el objeto PreguntaGrupo.
     * @param codigo
     * @param enunciado
     * @param respuestas
     * @param respuesta_correcta
     * @param area
     * @param dificultad
     * @param veces
     * @throws SQLException 
     */
    public PreguntaGrupo(int codigo, String enunciado, Respuesta[] respuestas, int respuesta_correcta, Area area, int dificultad, int veces) throws SQLException {
        super(codigo, enunciado, respuestas, respuesta_correcta, area, dificultad, veces);
        this.respuesta_contestada = 0;
        //incrementarVeces();
    }
    
    /**
     * Incrementa el numero de veces que ha salido la pregunta.
     * @throws SQLException 
     */
    public void incrementarVeces() throws SQLException{
        //this.setVeces(this.getVeces()+1);
        PreguntaDAO.incrementarVeces(this);
    }
    
    public void setRespuesta_contestada(int respuesta_contestada) {
        this.respuesta_contestada = respuesta_contestada;
    }
    
    public int getRespuesta_contestada(){
        return respuesta_contestada;
    }
    
    /**
     * Contar puntuacion.
     * @return 
     */
    public boolean validar(){
     
        return (PreguntaGrupo.super.getRespuesta_correcta()==respuesta_contestada);
    }
    
}
