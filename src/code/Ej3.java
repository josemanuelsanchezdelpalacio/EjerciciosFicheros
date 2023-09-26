package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Ej3 {

    public static void leerFichero() {
        String rutaString = Leer.pedirCadena("Introduce ruta archivo: ");
        Path ruta = Path.of(rutaString);
        leer(ruta);
    }

    public static void leer(Path p) {
        // Comprobamos si existe y si el archivo es un txt
        if (Files.exists(p)) {
            if (p.toString().endsWith(".txt")) {
                try {
                    // Recorremos el contenido del archivo
                    List<String> contenido = Files.readAllLines(p);
                    for (String cont : contenido) {
                        System.out.println(cont);
                    }

                    /*
                     * Forma simplificada para leer
                     * Files.readAllLines(p).forEach(System.out::println);
                     */
                } catch (IOException e) {
                    System.err.println("Error al leer: " + e.getMessage());
                } catch (SecurityException e) {
                    System.err.println("No tiene permiso de lectura: " + e.getMessage());
                }
            } else {
                System.out.println("No es un archivo de texto");
            }
        } else {
            // Si el archivo no existe, lo creamos
            try {
                Files.createFile(p);
                System.out.println("Archivo creado en la ruta: " + p.toString());
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
    }
}