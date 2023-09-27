package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/*
EJERCICIO 5
Modifica el programa Java anterior, de tal forma que cada vez que se ejecute el programa,
vaya añadiendo las nuevas frases introducidas al final del fichero de texto.
*/
public class Ej5 {

    public static void escribirFichero() {
        String rutaString = Leer.pedirCadena("Introduce ruta archivo: ");
        Path ruta = Path.of(rutaString);
        pedirFrases(ruta);
    }

    public static void pedirFrases(Path p) {
        String mensaje;
        try {
            // Verificamos si el archivo ya existe
            boolean archivoExiste = Files.exists(p);
            Scanner sc = new Scanner(System.in);

            if (archivoExiste) {
                // Si el archivo ya existe, obtenemos su tamaño
                long fileSize = Files.size(p);

                // Si el archivo no está vacio, añadimos una linea en blanco
                if (fileSize > 0) {
                    Files.write(p, "\n".getBytes(), StandardOpenOption.APPEND);
                }
            }

            do {
                System.out.print("Introduce una frase ('fin' para terminar): ");
                mensaje = sc.nextLine();
                Files.write(p, (mensaje + "\n").getBytes(), StandardOpenOption.APPEND);

            } while (!mensaje.equalsIgnoreCase("fin"));

            System.out.println("Frases guardadas en el archivo.");

        } catch (SecurityException e) {
            System.err.println("No tiene permiso de escritura: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}
