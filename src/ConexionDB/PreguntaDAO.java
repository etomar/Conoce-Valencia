/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Modelos.Pregunta;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lliurex
 */
public class PreguntaDAO {
    public static void delete(int codigo) throws SQLException{
        Statement stmt = null;
            try{
            stmt=BDConnect.connect().createStatement();
            stmt.executeUpdate("DELETE FROM Pregunta WHERE codigo="+codigo);
            }catch(SQLException e){
               BDConnect.showMYSQLerrors(e);
            }finally{
                stmt.close();
            }
    
    }
    
    public static Pregunta load(int codigo) throws SQLException{
       return null;
    }
    
    public static boolean save(Pregunta pregunta){
        return true;
    }
    
    public static ArrayList<Pregunta> loadAll(){
        return null;
    }
}
