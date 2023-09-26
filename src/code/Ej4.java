package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Scanner;

public class Ej4 {

    public static void escribirFicheroBorrando() {
        String rutaString = Leer.pedirCadena("Introduce ruta archivo: ");
        Path ruta = Path.of(rutaString);
        pedirFrases(ruta);
    }

    public static void pedirFrases(Path p) {
        String mensaje;
        try (Scanner sc = new Scanner(System.in)) {
            if (Files.exists(p)) {
                if (p.toString().endsWith(".txt")) {
                    do {
                        System.out.print("Introduce una frase ('fin' para terminar): ");
                        mensaje = sc.nextLine();

                        // Forma simplificada de escribir en un fichero
                        Files.write(p, (mensaje + "\n").getBytes(), StandardOpenOption.APPEND);

                    } while (!mensaje.equalsIgnoreCase("fin"));
                } else {
                    System.out.println("No es un archivo de texto");
                }
            } else {
                System.out.println("No existe el archivo");
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de escritura: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}