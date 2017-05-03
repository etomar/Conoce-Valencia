
package pkg1617.conoce.valencia;

import java.util.Scanner;
import Modelos.*;
import ConexionDB.*;
import java.util.ArrayList;

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
            int dificultad=sc.nextInt();
            switch (eleccion){
                
                case 1:
                    System.out.println("¡Empezamos a jugar!");
                    System.out.println("______________________________________________");
                    System.out.println("Indica el nombre de tu grupo: ");
                    String nombre;
                    nombre=sc.next();
                    Grupo g= new Grupo(nombre);
                    System.out.println("Elige la dificultad del juego");
                    
                    
                    Partida p=new Partida(g);
                    break;

                case 2:
                    System.out.println("Añadir pregunta");
                    System.out.println("______________________________________________");
                    int codigo=0;
                    String enunciado="";
                    Respuesta[] respuestas= new Respuesta[4];
                    int respuesta_correcta=0;
                    Area area = null;
                    int veces=0;
                    Pregunta pre= new Pregunta(codigo,enunciado,respuestas, respuesta_correcta,area, dificultad, veces);
                    
                    System.out.println("Añadir enunciado pregunta");
                    enunciado=sc.next();
                    System.out.println("Respuestas porsibles");
                    char[] letras = new char[]{'A', 'B', 'C', 'D'};
                    
                    for(int i=0;i<=respuestas.length;i++){
                        System.out.println("Añade la respuesta "+letras[i]);
                        respuestas[i] = new Respuesta(0, sc.next());
                    }
                    System.out.println("¿Cúal es la respuesta correcta?");
                    respuesta_correcta=sc.nextInt();
                    /********************FALTA AÑADIR LA AREA********************************************/

                    System.out.println("Area de la pregunta");
                    /********************FALTA AÑADIR LA AREA********************************************/
                    System.out.println("¿Cúal es la dificultad?");
                    dificultad=sc.nextInt();
                    
                    
                    break;

                case 3:
                    int codigoPreguntaEliminada=0;
                    System.out.println("¿Cúal es el código de la pregunta a eliminar?");
                    codigoPreguntaEliminada= sc.nextInt();
                    PreguntaDAO.delete(codigoPreguntaEliminada);
                    break;

                case 4:
                    ArrayList<Pregunta> all=PreguntaDAO.loadAll();
                    System.out.println();
                    String pasaPagina;
                    do{
                        
                        for(int i=0;i<all.size();i++){
                            
                            System.out.println("codigo:"+all.get(i).getCodigo());
                            System.out.println("Enunciado:"+all.get(i).getEnunciado());
                            System.out.println("Respuestas:"+all.get(i).getRespuestas());
                            System.out.println("Respuesta correcta:"+all.get(i).getRespuesta_correcta());
                            System.out.println("Area :"+all.get(i).getArea());
                            System.out.println("Dificultad :"+all.get(i).getDificultad());
                            System.out.println("Veces :"+all.get(i).getVeces());
                        }
                        System.out.println("Presiona espacio y enter para mostrar las 20 siguientes");
                    pasaPagina=sc.next();
                    }while(pasaPagina.equals(" "));
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