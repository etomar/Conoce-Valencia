/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Calendar;
import ConexionDB.*;
import java.sql.SQLException;
import java.util.*;

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
    private Calendar fin;
    private String duracion;

    public Partida(Grupo g) {
        preguntas = new PreguntaGrupo[10];
        fecha = Calendar.getInstance();
        this.g = g;
        this.respuestas_correctas = 0;
    }

    public PreguntaGrupo[] cargarPreguntas() throws SQLException {
        ArrayList<PreguntaGrupo> al = PreguntaDAO.loadAll();
        int vp[] = new int [10];
        
        for (int i = 0; i < 10; i++) {
            
            int ran = (int)new Random().nextInt(al.size());
            boolean correcto = true;
           
            for(int j = 0; j<i && correcto == true; j++){
                              
                if(ran == vp[j]){

                    i--;
                    correcto=false;
                 }
                
            }

             if(correcto){
                 vp[i] =  ran;
            
                 
                Pregunta p = al.get(ran);
                preguntas[i]=(PreguntaGrupo)p;
                PreguntaDAO.incrementarVeces(p);
            
             }

            
            
        }

        return preguntas;
    }

    public String DevolverDuracion(Calendar fecha, Calendar fin) {
        fin = Calendar.getInstance();
        int h = fecha.get(Calendar.HOUR_OF_DAY) - fin.get(Calendar.HOUR_OF_DAY);
        int m = fecha.get(Calendar.MINUTE) - fin.get(Calendar.MINUTE);
        int s = fecha.get(Calendar.SECOND) - fin.get(Calendar.SECOND);
        String hora = Integer.toString(h);
        String min = Integer.toString(m);
        String sec = Integer.toString(s);
        String d = hora + ":" + min + ":" + sec;

        return d;
    }
        public void incrementarRespuestasCorrectas(){
        this.respuestas_correctas++;
    }
    /*
        Registrar los resultados de la partida
     */
    public void finalizar() {
        duracion = DevolverDuracion(this.fecha, this.fin);
        PartidaDAO.save(this);
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

}
