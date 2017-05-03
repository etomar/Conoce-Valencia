/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Modelos.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jcfong
 * @since 02-05-2017
 * @version 1.0
 */
public class PreguntaDAO {
/**
 * Borra una pregunta de la BD
 * @param codigo 
 */
    public static void delete(int codigo) {
        Statement stmt = null;
        try {
            stmt = BDConnect.connect().createStatement();
            stmt.executeUpdate("DELETE FROM Pregunta WHERE codigo=" + codigo);
            stmt.close();
        } catch (SQLException e) {
            BDConnect.showMYSQLerrors(e);
        }
    }
    /**
     * 
     * @param codigo 
     * @return Pregunta p
     * Busca una pregunta en la BD y crea un objeto Pregunta.
     * Tambien se crean un vector de respuestas de las cuales se realiza una busqueda.
     * Consulta para sacar el area y se crea un objeto Area que hace falta para Pregunta.
     */
    public static Pregunta load(int codigo) {
        
         Pregunta p=null;
         int contador=0;//aumenta la posicion del vector
         Respuesta[]respuesta=new Respuesta[4];
         Statement stmt= null;//seleccionar la pregunta
         Statement stat= null;//seleccionar las respuesta de la pregunta
         Statement stm=null;//seleccionar el area
        try {
            
            stmt = BDConnect.connect().createStatement();
            stat=BDConnect.connect().createStatement();
            stm=BDConnect.connect().createStatement();
            
            ResultSet res=stat.executeQuery("SELECT (*) FROM Respuesta WHERE cod_pregunta= "+codigo);
            
            while(res.next()){
                int cod=res.getInt("codigo");
                String contenido=res.getString("contenido");
                respuesta[contador]=new Respuesta(cod,contenido);
                contador++;
            }
            
            ResultSet rs=   stmt.executeQuery("SELECT (*) FROM Pregunta WHERE codigo="+codigo);
            rs.next();
            
            
            int cod = rs.getInt("codigo");
            String contenido = rs.getString("contenido");
            int veces = rs.getInt("veces");
            int dificultad = rs.getInt("difcultad");
            int cod_tematica=rs.getInt("codigo_tematica");
           ResultSet result=stm.executeQuery("SELECT nombre FROM Tematica WHERE codigo_tematica = "+cod_tematica);
           result.next();
           String nombre = result.getString("nombre");
           
            Area area =new Area(cod_tematica,nombre);
            
            int cod_respuesta = rs.getInt("codigo_respuesta");
            
            p=new Pregunta(cod,contenido,respuesta,cod_respuesta,area,dificultad,veces);
            stmt.close();
            stat.close();
            
        } catch (SQLException e) {
            BDConnect.showMYSQLerrors(e);
        } 
        return p;
    }
    /**
     * 
     * @param pregunta
     * @return guardado
     * Guarda un objeto Pregunta en la BD y sus respuestas
     * 
     */
    public static boolean save(Pregunta pregunta) {
        Statement stmt=null;//guarda pregunta 
        Statement stat=null;//guarda respuesta
        Statement stt=null;//saca el codigo de la pregunta creada
        Boolean guardado=false;
        try{
            stmt=BDConnect.connect().createStatement();
            stmt.executeUpdate("INSERT INTO Pregunta(contenido,dificultad,"
                + "codigo_tematica,codigo_respuesta) VALUES ('"+pregunta.getEnunciado()
                +"',"+pregunta.getDificultad()+","+pregunta.getArea().getCodigo()+","+","+pregunta.getRespuesta_correcta()+")");
            
          stt=BDConnect.connect().createStatement();
          ResultSet rs=stt.executeQuery("SELECT MAX(codigo) FROM Pregunta");
          rs.next();
          pregunta.setCodigo(rs.getInt(1))
                  ;
           Respuesta[]resp=pregunta.getRespuestas();
           for(int i=0;i<4;i++){
                stat=BDConnect.connect().createStatement();
                stat.executeUpdate("INSERT INTO Respuesta(codigo,contenido,contenido_pregunta) "
                        + "VALUES("+resp[i].getCodigo()+",'"+resp[i].getContenido()+"',"+pregunta.getCodigo()+")");
           }
            guardado=true;
        }catch(SQLException e){
            BDConnect.showMYSQLerrors(e);
            guardado=false;
        }
        
        return guardado;
    }

    public static ArrayList<Pregunta> loadAll() {
        return null;
    }

}
