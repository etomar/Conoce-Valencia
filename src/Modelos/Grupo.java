/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import ConexionDB.GrupoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase crea el objeto grupo
 * @author Ruben, J.Bujeda, A.Sahuquillo, Manuel
 */
public class Grupo {
    
    private String nombre;
    
    public Grupo(String nombre){
        
        this.nombre = nombre;
        
        try {
            if(!GrupoDAO.exist(nombre))
                GrupoDAO.save(this);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }   
}
