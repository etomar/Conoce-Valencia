/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Modelos.Grupo;

/**
 *
 * @author sx
 */
public class GrupoDAO {
    public static boolean exist(String nombre){
        return true;
    }
    
    public static boolean save(Grupo grupo){
        if(exist(grupo.getNombre())){
            //Crear nuevo en la BD
        }else{
            //Actualizar o nada
        }
        return true;
    }
}
