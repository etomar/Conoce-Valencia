/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 * Clase que contine información y metodos para cada objeto de tipo Area.
 * @author Vicente Cabedo
 * @version 2
 */
public class Area {
    
    private int codigo;
    private String nombre;
    
    /**
     * Constructor con 2 parametros. Construye un objeto de la clase area.
     * @param codigo, codigo del area. De tipo entero
     * @param nombre, nombre del area. De tipo String.
     */
    public Area(int codigo, String nombre){
        
        this.codigo=codigo;
        this.nombre=nombre;
    }
    
    /**
     * Metodo que devuelve el codigo del area
     * @return devuelve el codigo del area. De tipo entero.
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Metodo con un parametro. Establece el codigo del area.
     * @param codigo, codigo del area. De tipo entero.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Metodo que devuelve el nombre del area
     * @return devuelve el nombre del area. De tipo String
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo con un parametro. Establece el nombre del area
     * @param nombre, nombre del area. De tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
