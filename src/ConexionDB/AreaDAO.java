package ConexionDB;

import Modelos.Area;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Clase AreaDAO
 * @author Vicente Cabedo
 * @author Javier Galindo
 * @since 03/05/2017
 * @version 1.0
 */
public class AreaDAO {
    
    /**
     * Carga un ArrayList con objetos de tipo Area ordenados por codigo de manera ascendente
     * desde la tabla TEMATICA de la base de datos.
     * @return ArrayList de objetos de tipo Area.
     * @throws SQLException 
     */
    public static ArrayList<Area> loadAll() throws SQLException {
        ArrayList<Area> al = new ArrayList();
        Statement stat =BDConnect.connect().createStatement();
        try{
            ResultSet rs = stat.executeQuery("SELECT * FROM Tematica ORDER BY codigo_tematica");
            if (rs.next()){
            do {
                int codigo=rs.getInt("codigo_tematica");
                String nombre=rs.getString("nombre");
                al.add(new Area(codigo,nombre));
            }while(rs.next());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            stat.close();
        }
        return al;
    }

}
