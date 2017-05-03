/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;


//import static ConexionDB.BDConnect.*;
import Modelos.Partida;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ruben y J.Bujeda
 */
public class PartidaDAO {
    
    /**
     * @author Ruben y J.Bujeda
     * Metodo save que guarda la partida en la base de datos
     * @param partida
     * @param codigo
     * @param aciertos
     * @param fecha
     * @param grupo 
     */
    public static void save(Partida partida, int codigo, int aciertos, Date fecha, String grupo){
       
        try {
            String query = "INSERT INTO Sesion VALUES("
		+ "\"" + codigo + "\", "
		+ "\"" + aciertos + "\", "
		+ "\"" + fecha + "\", "
		+ "\"" + grupo +"\")";
            
		Statement stat;
                
                stat = BDConnect.connect().createStatement();
		stat.executeUpdate(query);
			 
		System.out.println("Partida Guardada");
			 
	        } catch (SQLException ex) {
	        	
	            System.out.println("Error al guardar partida");
	            
	        }
    }
    
    /**
     * @author Ruben y J.Bujeda
     * Metodo para guardar las respuestas seleccionadas en la partida
     * @param codigo_sesion
     * @param codigo_respuesta 
     */
    public static void registrarRespuestaPregunta(int codigo_sesion, int codigo_respuesta){
        
        try {
            String query = "INSERT INTO Responde VALUES("
		+ "\"" + codigo_sesion + "\", "
		+ "\"" + codigo_respuesta +"\")";
            
		Statement stat;
                
                stat = BDConnect.connect().createStatement();
		stat.executeUpdate(query);
			 
		System.out.println("Respuesta Guardada");
			 
	        } catch (SQLException ex) {
	        	
	            System.out.println("Error al guardar respuesta");
	            
	        }
        
    }
}
