package code;
import libs.Leer;

import java.io.File;
import java.util.Scanner;
public class Ej2 {
    public static void mostrarFicheros() {
        File f1 = new File(Leer.pedirCadena("Introduce una ruta: "));
        comprobarContFile(f1);
    }
    //otra forma de mostrar el contenido
    //for (String ficheros : f.list()) { System.out.println(ficheros); }
    public static void comprobarContFile(File f) {
        try {
            if (f.exists()) {
                if (f.isDirectory()) {
                    String[] files = f.list();
                    if (files != null) {
                        for (String fileName : files) {
                            System.out.println(fileName);
                        }
                    } else {
                        System.out.println("No hay ficheros en el directorio");
                    }
                }
            } else {
                System.out.println(f.getName() + " no existe");
            }
        } catch (SecurityException e) {
            System.err.println("No se tiene permiso para acceder a " + f.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error al procesar " + f.getAbsolutePath() + ": " + e.getMessage());
        }
    }
}