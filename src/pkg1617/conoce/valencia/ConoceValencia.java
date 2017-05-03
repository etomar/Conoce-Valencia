
package pkg1617.conoce.valencia;

import java.util.Scanner;
import Modelos.*;
import ConexionDB.*;

/*@autor iguisado*/
public class ConoceValencia {
    public static Scanner sc=new Scanner(System.in);
    
    public static void clear(){
        for(int i=0;i<=300;i++){
            System.out.println("\b");
        }
    }
    
    public static int menu(){
        
        int eleccion=0;
        System.out.println("               CONOCE VALÈNCIA                ");
        System.out.println("                    MENÚ                      ");
        System.out.println("   Elija una opción:                          ");
        System.out.println("   1: Generar un nuevo test                   ");
        System.out.println("   2: Añadir preguntas                        ");
        System.out.println("   3: Eliminar preguntas                      ");
        System.out.println("   4: Mostrar preguntas del almacén           ");
        System.out.println("   5: Importar preguntas de fichero           ");
        System.out.println("   0: Salir del programa                      ");
        System.out.println("______________________________________________");
        eleccion=sc.nextInt();
        return eleccion;
    }

    public static void main(String[] args) {
        
        int eleccion;
        do {
            clear();
            eleccion=menu();
            switch (eleccion){
                case 1:
                    System.out.println("¡Empezamos a jugar!");
                    System.out.println("______________________________________________");
                    System.out.println("Indica el nombre de tu grupo: ");
                    String nombre;
                    nombre=sc.next();
                    Grupo g= new Grupo(nombre);
                    Partida p=new Partida(g);
                    break;

                case 2:
                    
                    
                    break;

                case 3:
                    int codigo;
                    
                    break;

                case 4:
                    
                    break;

                case 5:
                    
                    break;

                case 0:
                    
                    break;
                default:
                    System.out.println("¡Ops! No has introducido una opción del menú.. vuelve a intentarlo :) ");
                    break;
            }
         }while(eleccion!=0);
                
        
        
        }
    
}
