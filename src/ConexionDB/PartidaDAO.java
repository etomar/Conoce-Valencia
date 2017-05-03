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
 * @author Ruben y Bujeda
 */
public class PartidaDAO {
    
    /**
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
    
    public static void registrarRespuestaPregunta(int codigo_partida, int codigo_respuesta){
    }
}
