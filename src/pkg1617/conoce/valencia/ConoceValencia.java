package pkg1617.conoce.valencia;

import java.util.Scanner;
import Modelos.*;
import ConexionDB.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


/**
 * Clase principal del programa Conoce Valencia.
 * @author Ivan Guisado
 */
public class ConoceValencia {

    public static Scanner sc = new Scanner(System.in);

    public static void clear() {
        for (int i = 0; i <= 300; i++) {
            System.out.println("\b");
        }
    }

    /**
     * Función para cargar las preguntas a la BD desde un fichero.
     *
     * @param fichero Cada pregunta en una línea, cada parámetro separado por ;
     * @param areas ArrayList con las áreas
     * @author Lluís Navarro
     */
    public static void importarPreguntas(String fichero, ArrayList<Area> areas) {
        Pregunta p = new Pregunta();
        File fe = new File(fichero);
        try {
            FileReader fr = new FileReader(fe);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            String[] linea, area, lineaDificultad;
            Respuesta[] respuestas = new Respuesta[4];
            while ((cadena = br.readLine()) != null) {
                linea = cadena.split(";");
                p.setEnunciado(linea[0]);
                for (int i = 0, j = 1; i < respuestas.length; i++, j++) {
                    respuestas[i] = new Respuesta(linea[j]);
                }
                p.setRespuestas(respuestas);
                int pos=0;
                switch (linea[5]) {
                    case "A":
                        pos=0;
                        break;
                    case "B":
                        pos=1;
                        break;
                    case "C":
                        pos=2;
                        break;
                    case "D":
                        pos=3;
                        break;
                }
                area = linea[6].split(" ");
                p.setArea(areas.get((Integer.parseInt(area[0]) - 1)));
                lineaDificultad = linea[7].split(" ");
                p.setDificultad(Integer.parseInt(lineaDificultad[0]));
                PreguntaDAO.save(p, pos);
                

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menu con las opciones del juego.
     * @return 
     */
    public static int menu() {

        int eleccion = 0;
        System.out.println("");
        System.out.println("************************************************");
        System.out.println("*               CONOCE VALÈNCIA                *");
        System.out.println("************************************************");
        System.out.println("                                                ");
        System.out.println("   1: Generar un nuevo test                     ");
        System.out.println("   2: Añadir preguntas                          ");
        System.out.println("   3: Eliminar preguntas                        ");
        System.out.println("   4: Mostrar preguntas del almacén             ");
        System.out.println("   5: Importar preguntas de fichero             ");
        System.out.println("   6: Estadísticas                              ");
        System.out.println("   0: Salir del programa                        ");
        System.out.println("________________________________________________");
        System.out.print("   Elija una opción: ");
        eleccion = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        return eleccion;
    }

    /**
     * Clase principal del programa.
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {

        int eleccion;
        do {
            //clear();
            eleccion = menu();
            ArrayList<Area> areas = AreaDAO.loadAll();
            switch (eleccion) {

                case 1:
                    System.out.println("*************************");
                    System.out.println("*  ¡Empezamos a jugar!  *");
                    System.out.println("*************************");
                    System.out.println("");
                    System.out.print("Indica el nombre de tu grupo: ");
                    String nombre;
                    nombre = sc.nextLine();
                    Grupo g = new Grupo(nombre);
                    System.out.print("Elige la dificultad del juego: ");
                    int dificultad = sc.nextInt();
                    System.out.println("");
                    //Cargamos las preguntas de la partida.
                    Partida p = new Partida(g);
                    PreguntaGrupo[] pgaux = p.cargarPreguntas();
                    char []opciones={'A','B','C','D'};
                    
                    
                    //Bucle de preguntas.
                    int con =1;
                    for(PreguntaGrupo pg: pgaux){
                        System.out.println("");
                        
                        System.out.println("-"+pg.getArea().getNombre()+"-");
                        
                        System.out.println(con+"- "+pg.getEnunciado());
                        for(int j=0;j<pg.getRespuestas().length;j++){
                            System.out.println(opciones[j]+". "+pg.getRespuestas()[j].getContenido());
                        }
                        
                        char a = '0';
                        do{
                            System.out.println("");
                            System.out.print("Seleccionar respuesta: ");
                            a = sc.next().toUpperCase().charAt(0);
                        }while(a!='A' && a!='B' && a!='C' && a!='D'); 
                        
                        switch(a){
                            case 'A':
                                pg.setRespuesta_contestada(pg.getRespuestas()[0].getCodigo());
                                break;
                            case 'B':
                                pg.setRespuesta_contestada(pg.getRespuestas()[1].getCodigo());
                                break;
                            case 'C':
                                pg.setRespuesta_contestada(pg.getRespuestas()[2].getCodigo());
                                break;
                            case 'D':
                                pg.setRespuesta_contestada(pg.getRespuestas()[3].getCodigo());
                                break;
                        }
                        if(pg.validar()){
                            p.incrementarRespuestasCorrectas();
                        } 
                        con++;
                    }
                    
                    //Resultados del test
                    System.out.println("RESULTADOS\n------------------");
                    for(PreguntaGrupo p_ : p.getPreguntas()){
                        System.out.println(p_.getEnunciado());
                        System.out.println("Respuesta correcta: "+ p.findRespuesta(p_, p_.getRespuesta_correcta()).getContenido());
                        System.out.println("Respuesta escogida: "+ p.findRespuesta(p_, p_.getRespuesta_contestada()).getContenido());
                        
                        if(p_.validar())
                            System.out.println("Respuesta Correcta!");
                            System.out.println("");
                    }
                    
                    System.out.println("Número de respuestas correctas: "+p.getRespuestas_correctas());
                    p.finalizar();
                    break;

                case 2:
                    System.out.println("Añadir pregunta");
                    System.out.println("________________________________________________");
                    int codigo = 0;
                    String enunciado = "";
                    Respuesta[] respuestas = new Respuesta[4];
                    int respuesta_correcta = 0;
                    Area area = null;
                    
                    int veces = 0;
                    
                    System.out.println("Añadir enunciado pregunta");
                    enunciado = sc.nextLine();
                    System.out.println("Respuestas porsibles");
                    char[] letras = new char[]{'A', 'B', 'C', 'D'};

                    for (int i = 0; i <= respuestas.length; i++) {
                        System.out.println("Añade la respuesta " + letras[i]);
                        respuestas[i] = new Respuesta(0, sc.next());
                    }
                    System.out.println("¿Cúal es la respuesta correcta?");
                    respuesta_correcta = sc.nextInt();
                    
                    System.out.println("Area de la pregunta");
                    for (Area a : areas) {
                        System.out.println(a.getCodigo()+". "+a.getNombre());
                    }
                    System.out.print("Elige un area: ");
                    int pos = (sc.nextInt())-1;
                    area=areas.get(pos);
                    
                    System.out.println("¿Cúal es la dificultad?");
                    dificultad = sc.nextInt();
                    Pregunta pre = new Pregunta(codigo, enunciado, respuestas, respuesta_correcta, area, dificultad, veces);
                    break;

                case 3:
                    int codigoPreguntaEliminada = 0;
                    System.out.println("¿Cúal es el código de la pregunta a eliminar?");
                    codigoPreguntaEliminada = sc.nextInt();
                    PreguntaDAO.delete(codigoPreguntaEliminada);
                    break;

                case 4:
                    ArrayList<PreguntaGrupo> all = PreguntaDAO.loadAll();
                    System.out.println();
                    String pasaPagina;
                    do {

                        for (int i = 0; i < all.size(); i++) {

                            System.out.println("codigo:" + all.get(i).getCodigo());
                            System.out.println("Enunciado:" + all.get(i).getEnunciado());
                            System.out.println("Respuestas:" + all.get(i).getRespuestas());
                            System.out.println("Respuesta correcta:" + all.get(i).getRespuesta_correcta());
                            System.out.println("Area :" + all.get(i).getArea());
                            System.out.println("Dificultad :" + all.get(i).getDificultad());
                            System.out.println("Veces :" + all.get(i).getVeces());
                        }
                        System.out.println("Presiona espacio y enter para mostrar las 20 siguientes");
                        pasaPagina = sc.nextLine();
                    } while (pasaPagina.equals(" "));
                    break;

                case 5:
                    System.out.println("Introduce el nombre del fichero:");
                    String nombreFichero=sc.nextLine();
                    importarPreguntas(nombreFichero,areas);
                    break;
                    
                case 6:
                    System.out.println("   1: Puntuaciones                            ");
                    System.out.println("   2: Consulte la media de dificultad         ");
                    System.out.println("   3: Número de preguntas no usadas en ningún test ");
                    System.out.println("   4: Número de preguntas usadas en los test ");
                    System.out.println("   5: Mostrar preguntas por dificultad        ");
                    System.out.println("   6: Mostrar preguntas por área        ");

                    int eleccion2=0;
                    System.out.print("Elija una opción: ");
                    eleccion2=sc.nextInt();
                    System.out.println("");
                    switch (eleccion2){
                        
                        case 1:
                            Statement stmt=BDConnect.connect().createStatement();
                            ResultSet rs=stmt.executeQuery("SELECT grupo,aciertos as puntuacion,duracion from Sesion order by aciertos,duracion");
                            
                            System.out.println("");
                            while(rs.next()){
                                System.out.println("Grupo: "+rs.getString(1));
                                System.out.println("Puntuación: "+rs.getInt(2));
                                System.out.println("Duración: "+rs.getString(3));
                                System.out.println("");
                            }
                            stmt.close();
                            break;
                        case 2: 
                            Statement stmt1=BDConnect.connect().createStatement();
                            ResultSet res=stmt1.executeQuery("SELECT AVG(dificultad) as Media from Pregunta");
                            res.next();
                                System.out.println("Media de dificultad "+res.getFloat(1));
                            stmt1.close();

                        break;
                        case 3:
                            Statement stmt2=BDConnect.connect().createStatement();
                            ResultSet res2=stmt2.executeQuery("SELECT COUNT(codigo) from Pregunta WHERE codigo_respuesta IN(SELECT codigo_respuesta from Pregunta WHERE codigo_respuesta NOT IN(SELECT codigo_respuesta from Responde)) ");
                            res2.next();
                            System.out.println("Preguntas no usadas "+res2.getInt(1));

                            stmt2.close();  

                            break;
                        case 4:
                            Statement stmt3=BDConnect.connect().createStatement();
                            ResultSet res3=stmt3.executeQuery("SELECT COUNT(codigo) from Pregunta WHERE codigo_respuesta IN(SELECT codigo_respuesta from Pregunta WHERE codigo_respuesta IN(SELECT codigo_respuesta from Responde)) ");
                            res3.next();
                            System.out.println("Preguntas usadas "+res3.getInt(1));

                            stmt3.close(); 
                            break;
                        case 5:
                            System.out.println("¿Qué nivel quieres ver?");
                            System.out.println("1 Muy fácil");
                            System.out.println("2 Fácil");
                            System.out.println("3 Normal");
                            System.out.println("4 Difícil");
                            System.out.println("5 Muy difícil");
                            
                            int nvldificultad=sc.nextInt();
                            
                            
                            
                            Statement stmt4=BDConnect.connect().createStatement();
                            ResultSet res4=stmt4.executeQuery("SELECT dificultad, contenido FROM Pregunta WHERE dificultad="+nvldificultad);
                            while(res4.next()){
                                System.out.println("Nivel: "+res4.getInt(1)+", Pregunta: "+res4.getString(2));
                            }
                            stmt4.close();
                            
                            break;
                            
                        case 6:
                            System.out.println("¿Qué área quieres ver?");
                            System.out.println("1 Valencia Romana");
                            System.out.println("2 Valencia Medieval");
                            System.out.println("3 Valencia Moderna");
                            System.out.println("4 Valencia Contemporánea");
                            System.out.println("5 Valencia desde la guerra civil hasta el presente");
                            
                            int conarea=sc.nextInt();
                            
                            
                            
                            Statement stmt5=BDConnect.connect().createStatement();
                            ResultSet res5=stmt5.executeQuery("SELECT codigo_tematica, contenido FROM Pregunta WHERE codigo_tematica="+conarea);
                            while(res5.next()){
                                System.out.println("Cod.Área: "+res5.getInt(1)+", Pregunta: "+res5.getString(2));
                            }
                            stmt5.close();
                            break;
                    
                        default:
                            System.out.println("¡Ops! No has introducido una opción del menú.. vuelve a intentarlo :) ");
                            break;
                        
                        
                    }
                    break;    

                case 0:

                    break;
                default:
                    System.out.println("¡Ops! No has introducido una opción del menú.. vuelve a intentarlo :) ");
                    break;
            }
        } while (eleccion != 0);

    }

}
