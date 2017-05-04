/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase que se encarga  de hacer las conexiones con la base de datos
 * @author Jaume Segarra
 */
public class BDConnect {
    
    public static String conData = "jdbc:mysql://127.0.0.1:3306/conocevalenciadb?" +
                                           "user=root&password=";
    
    /**
     * Metodo utilizado para conectar con la base de datos
     * @author Jaume Segarra
     * @return 
     */
    public static Connection connect(){
        
        try {
            Connection conn = DriverManager.getConnection(conData);
            
            return conn;
            
        } catch (SQLException ex) {
                showMYSQLerrors(ex);
        }
        
        return null;
    }

    /**
     * Metodo que utilizamos para mostrar errores
     * @author Jaume Segarra
     * @param ex 
     */
    public static void showMYSQLerrors(SQLException ex){        
        ex.printStackTrace();
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
}
