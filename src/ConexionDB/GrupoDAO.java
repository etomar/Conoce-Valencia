/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import Modelos.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sx
 * @author Vicente Cabedo
 * @author Javier Galindo
 */
public class GrupoDAO {

    /**
     * Comprueba si el grupo existe en la BDD.
     * @param nombre Nombre del grupo a comprobar.
     * @return true: Si el grupo existe, false: El grupo no existe
     * @throws SQLException 
     */
    public static boolean exist(String nombre) throws SQLException {
        boolean existe = false;
        Statement stat = BDConnect.connect().createStatement();
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM conocevalencia.GRUPO WHERE=" + nombre + ";");
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stat.close();
        }
        return existe;
    }

    /**
     * Almacena en la base de datos un grupo.
     * @param grupo Objeto grupo cargado.
     * @return true: Si el grupo se ha guardado correctamente.
     * @throws SQLException 
     */
    public static boolean save(Grupo grupo) throws SQLException {
        boolean guardado = false;
        if (!exist(grupo.getNombre())) {
            Statement stat = BDConnect.connect().createStatement();
            try {
                stat.executeUpdate("INSERT INTO conocevalencia.GRUPO(nombre) VALUES(" + grupo.getNombre() + ");");
                guardado = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stat.close();
            }
        }
        return guardado;
    }
}
