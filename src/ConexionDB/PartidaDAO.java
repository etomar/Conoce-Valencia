/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Modelos.Partida;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Esta clase contiene un metodo utlizado para guardar las puntuaciones de las partidas 
 * y otro que se encarga de registrar las repuestas
 * @author Ruben y J.Bujeda
 */
public class PartidaDAO {
    
    /**
     * @author Ruben y J.Bujeda
     * Metodo save que guarda la partida en la base de datos
     * @param partida
     */
    public static void save(Partida partida){
       
        try {
            /*int respuestas_correctas=0;
            
            for(int i=0; i<partida.getPreguntas().length;i++)
                    if(partida.getPreguntas()[i].validar())
                        respuestas_correctas++;*/
            
            String query = "INSERT INTO Sesion (aciertos, grupo, duracion) VALUES("
		+ partida.getRespuestas_correctas() + ", '" + partida.getG().getNombre() + "','"
		+ partida.DevolverDuracion(partida.getFecha(), partida.getFin()) +"')";
            
		Statement stat;
                
                stat = BDConnect.connect().createStatement();
		stat.executeUpdate(query);
		
                Statement stt=BDConnect.connect().createStatement();
                ResultSet rs=stt.executeQuery("SELECT MAX(codigo) FROM Sesion");
                rs.next();
                partida.setCodigo(rs.getInt(1));
                
                for(int i=0; i<partida.getPreguntas().length;i++)
                    PartidaDAO.registrarRespuestaPregunta(partida.getCodigo(), partida.getPreguntas()[i].getRespuesta_contestada());
        
                
		System.out.println("Partida Guardada");
			 
	        } catch (SQLException ex) {
	        	BDConnect.showMYSQLerrors(ex);
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
			 
		
			 
	        } catch (SQLException ex) {
	        	
	            System.out.println("Error al guardar respuesta");
	            
	        }
        
    }
}
