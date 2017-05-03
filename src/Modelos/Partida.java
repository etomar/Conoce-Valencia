/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Calendar;
import ConexionDB.*;
import java.util.ArrayList;

/**
 *
 * @author jcfong
 * @author fjgalindo
 * @since 02-05-2017
 * @version 1.0
 */
public class Partida {
    private int codigo;
    private int respuestas_correctas;
    private Calendar fecha;
    private PreguntaGrupo[] preguntas;
    private Grupo g;

    public Partida(Grupo g) {
        preguntas = new PreguntaGrupo[10];
        fecha = Calendar.getInstance();
        this.g = g;
        this.respuestas_correctas = 0;
    }

    public PreguntaGrupo[] cargarPreguntas() {
        ArrayList<Pregunta> al = PreguntaDAO.loadAll();
        for (int i = 0; i < 10; i++) {
            Pregunta p = al.get((int)Math.random()*al.size()+1);
            preguntas[i]=(PreguntaGrupo)p;
        }
        
        return preguntas;
    }
    
    /*
        Registrar los resultados de la partida
    */
    public void finalizar(){
        PartidaDAO.save(this);
        
        for(int i=0; i<preguntas.length;i++){
            if(preguntas[i].validar())
                this.respuestas_correctas++;
            
            PartidaDAO.registrarRespuestaPregunta(this.codigo, preguntas[i].getRespuesta_contestada());
        }
        
        PartidaDAO.save(this);//Actualizar respuestas correctas
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public PreguntaGrupo[] getPreguntas() {
        return preguntas;
    }

    public int getRespuestas_correctas() {
        return respuestas_correctas;
    }
    
    public void setPreguntas(PreguntaGrupo[] preguntas) {
        this.preguntas = preguntas;
    }

    public Grupo getG() {
        return g;
    }

    public void setG(Grupo g) {
        this.g = g;
    }

    public void setRespuestas_correctas(int respuestas_correctas) {
        this.respuestas_correctas = respuestas_correctas;
    }
    
}
