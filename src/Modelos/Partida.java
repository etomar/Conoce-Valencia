/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Calendar;
import ConexionDB.*;

/**
 *
 * @author jcfong
 * @author fjgalindo
 * @since 02-05-2017
 * @version 1.0
 */
public class Partida {

    private Calendar fecha;
    private PreguntaGrupo[] preguntas;
    private Grupo g;

    public Partida() {
    }

    public Partida(Grupo g) {
        preguntas = new PreguntaGrupo[10];
        fecha = Calendar.getInstance();
        this.g = g;
    }

    public PreguntaGrupo[] cargarPreguntas() {
        ArrayList<Pregunta> al = PreguntaDAO.loadAll();
        for (int i = 0; i < 10; i++) {
            Pregunta p = al.get(Math.random()*al.size +1);
            preguntas[i]=(PreguntaGrupo)p;
        }
        
        return preguntas;
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

    public void setPreguntas(PreguntaGrupo[] preguntas) {
        this.preguntas = preguntas;
    }

    public Grupo getG() {
        return g;
    }

    public void setG(Grupo g) {
        this.g = g;
    }

}
