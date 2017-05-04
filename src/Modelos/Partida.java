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
 * Esta clase crea el objeto partida y contine los metodos que lo administran.
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

    /**
     * Crea el objeto.
     * @param g 
     */
    public Partida(Grupo g) {
        preguntas = new PreguntaGrupo[10];
        fecha = Calendar.getInstance();
        this.g = g;
        this.respuestas_correctas = 0;
    }

    /**
     * Este metodo carga las preguntas que deberan contestar los grupos.
     * @return
     * @throws SQLException 
     */
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

    /**
     * Con este metodo obtenemos el tiempo que cada grupo tarde en constestar todas las preguntas.
     * @param fecha
     * @param fin
     * @return 
     */
    public String DevolverDuracion(Calendar fecha, Calendar fin) {
        fin = Calendar.getInstance();
        int h =  fin.get(Calendar.HOUR_OF_DAY)-fecha.get(Calendar.HOUR_OF_DAY);
        int m =  fin .get(Calendar.MINUTE)-fecha.get(Calendar.MINUTE);
        int s = fin.get(Calendar.SECOND)-fecha.get(Calendar.SECOND);
        String hora = Integer.toString(h);
        String min = Integer.toString(m);
        String sec = Integer.toString(s);
        String d = hora + ":" + min + ":" + sec;

        return d;
    }
        public void incrementarRespuestasCorrectas(){
        this.respuestas_correctas++;
    }
        
    /**
     * Registrar los resultados de la partida.
     */
    public void finalizar() {
        duracion = DevolverDuracion(this.fecha, this.fin);
        PartidaDAO.save(this);
    }
    
    /**
     * Se comprueba si la respuesta de la pregunta es correcta.
     * @param preg
     * @param codigo
     * @return 
     */
    public Respuesta findRespuesta(Pregunta preg, int codigo){
        Respuesta resp = null;
        for(Respuesta r : preg.getRespuestas()){
            if(r.getCodigo() == codigo)
                resp=r;
        }
        return resp;
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
