/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import ConexionDB.PreguntaDAO;
import java.sql.SQLException;

/**
 *
 * @author Lliurex
 */
public class PreguntaGrupo extends Pregunta {
    private int respuesta_contestada;

    public PreguntaGrupo(int codigo, String enunciado, Respuesta[] respuestas, int respuesta_correcta, Area area, int dificultad, int veces, int respuesta_contestada) throws SQLException {
        super(codigo, enunciado, respuestas, respuesta_correcta, area, dificultad, veces);
        this.respuesta_contestada = respuesta_contestada;
        incrementarVeces();
    }
    
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
    
    public boolean validar(){
        //Contar puntuacion
        return (PreguntaGrupo.super.getRespuesta_correcta()==respuesta_contestada);
    }
    
    
    
}
