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

    static final int NUM_RESPUESTAS = 4;

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
     * @param codigo
     * @return Pregunta p Busca una pregunta en la BD y crea un objeto Pregunta.
     * Tambien se crean un vector de respuestas de las cuales se realiza una
     * busqueda. Consulta para sacar el area y se crea un objeto Area que hace
     * falta para Pregunta.
     */
    public static Pregunta load(int codigo) {

        Pregunta p = null;
        int contador = 0;//aumenta la posicion del vector
        Respuesta[] respuesta = new Respuesta[NUM_RESPUESTAS];
        Statement stmt = null;//seleccionar la pregunta
        Statement stat = null;//seleccionar las respuesta de la pregunta
        Statement stm = null;//seleccionar el area
        try {

            stmt = BDConnect.connect().createStatement();
            stat = BDConnect.connect().createStatement();
            stm = BDConnect.connect().createStatement();

            ResultSet res = stat.executeQuery("SELECT (*) FROM Respuesta WHERE cod_pregunta= " + codigo);

            while (res.next()) {
                int cod = res.getInt("codigo");
                String contenido = res.getString("contenido");
                respuesta[contador] = new Respuesta(cod, contenido);
                contador++;
            }

            ResultSet rs = stmt.executeQuery("SELECT (*) FROM Pregunta WHERE codigo=" + codigo);
            rs.next();

            int cod = rs.getInt("codigo");
            String contenido = rs.getString("contenido");
            int veces = rs.getInt("veces");
            int dificultad = rs.getInt("dificultad");
            int cod_tematica = rs.getInt("codigo_tematica");
            ResultSet result = stm.executeQuery("SELECT nombre FROM Tematica WHERE codigo_tematica = " + cod_tematica);
            result.next();
            String nombre = result.getString("nombre");

            Area area = new Area(cod_tematica, nombre);

            int cod_respuesta = rs.getInt("codigo_respuesta");

            p = new Pregunta(cod, contenido, respuesta, cod_respuesta, area, dificultad, veces);
            stmt.close();
            stat.close();

        } catch (SQLException e) {
            BDConnect.showMYSQLerrors(e);
        }
        return p;
    }

    /**
     * Guarda las preguntas y sus correspondientes repuestas.
     * @param pregunta
     * @return guardado Guarda un objeto Pregunta en la BD y sus respuestas
     *
     */
    public static boolean save(Pregunta pregunta, int pos) {
        Connection con = BDConnect.connect();
        Statement stmt = null;//guarda pregunta 
        // = null;//guarda respuesta
        Statement stt = null;//saca el codigo de la pregunta creada
        Boolean guardado = false;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Pregunta(contenido,dificultad,"
                    + "codigo_tematica) VALUES ('" + pregunta.getEnunciado()
                    + "'," + pregunta.getDificultad() + "," + pregunta.getArea().getCodigo() + ")");

            stt = con.createStatement();
            ResultSet rs = stt.executeQuery("SELECT MAX(codigo) FROM Pregunta");
            rs.next();
            pregunta.setCodigo(rs.getInt(1));
            Respuesta[] resp = pregunta.getRespuestas();
            
            for (int i = 0; i < NUM_RESPUESTAS; i++) {
                Statement stat = con.createStatement();
                stat.executeUpdate("INSERT INTO Respuesta(contenido,codigo_pregunta) "
                        + "VALUES('" + resp[i].getContenido() + "'," + pregunta.getCodigo() + ")");
                Statement stt1 = con.createStatement();
                ResultSet rs1 = stt1.executeQuery("SELECT MAX(codigo) FROM Respuesta");
                rs1.next();
                resp[i].setCodigo(rs1.getInt(1));
                stt1.close();
                stat.close();
            }
            Statement stt2 = con.createStatement();
            stt2.executeUpdate("UPDATE Pregunta SET codigo_respuesta=" + resp[pos].getCodigo() + " WHERE codigo=" + pregunta.getCodigo());
            stmt.close();
            
            stt.close();
            con.close();
            guardado = true;
        } catch (SQLException e) {
            BDConnect.showMYSQLerrors(e);
            guardado = false;
        }

        return guardado;
    }
    /**
     * Incrementa el numero de veces que ha salido una pregunta.
     * @param p
     * @throws SQLException 
     */
    public static void incrementarVeces(Pregunta p) throws SQLException{
        Statement stat = ConexionDB.BDConnect.connect().createStatement();
        try{
            stat.executeUpdate("UPDATE Pregunta SET veces="+p.getVeces()+1+" WHERE codigo="+p.getCodigo());
            p.setVeces(p.getVeces()+1);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stat.close();
        }
    }

    /**
     * Carga todas las preguntas en un ArrayList
     * @return preguntas 
     */
    public static ArrayList<PreguntaGrupo> loadAll() {

        ArrayList<PreguntaGrupo> preguntas = new ArrayList<PreguntaGrupo>();
        Statement stmt = null;//seleccionar la pregunta
        Statement stat = null;//seleccionar las respuesta de la pregunta
        Statement stm = null;//seleccionar el area
        try {
            int contador = 0;//aumenta la posicion del vector
            

            stmt = BDConnect.connect().createStatement();
            stat = BDConnect.connect().createStatement();
            stm = BDConnect.connect().createStatement();

            ResultSet res = stat.executeQuery("SELECT * FROM Pregunta");

            while (res.next()) {
                contador=0;
                Respuesta[] respuesta = new Respuesta[NUM_RESPUESTAS];
                int cod = res.getInt("codigo");
                String contenido = res.getString("contenido");
                int veces = res.getInt("veces");
                int dificultad = res.getInt("dificultad");
                int cod_tematica = res.getInt("codigo_tematica");
                int cod_respuesta = res.getInt("codigo_respuesta");
                
                ResultSet result = stm.executeQuery("SELECT nombre FROM Tematica WHERE codigo_tematica = " + cod_tematica);
                result.next();
                String nombre = result.getString("nombre");

                Area area = new Area(cod_tematica, nombre);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Respuesta WHERE codigo_pregunta=" + cod);
                while(rs.next()){
                    
                    int codigo = rs.getInt("codigo");
                String cont = rs.getString("contenido");
                
                respuesta[contador] = new Respuesta(codigo, cont);
                    
                contador++;
                }

                preguntas.add(new PreguntaGrupo(cod, contenido, respuesta, cod_respuesta, area, dificultad, veces));
            }
            stmt.close();
            stat.close();
            stm.close();

        } catch (SQLException e) {
            BDConnect.showMYSQLerrors(e);
        }
        return preguntas;
    }
}
