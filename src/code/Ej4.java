package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
EJERCICIO 4
Escribe un programa Java que pida una serie de frases por teclado hasta que se inserte como
frase la palabra “fin”. Dichas frases deberán guardarse en un fichero de texto. A continuación,
el programa visualizará el contenido del fichero, frase por frase. Cada vez que se ejecute el
programa, se tienen que descartar las frases que ya estaban escritas en el fichero. Realiza este
ejercicio sin usar la clase BufferedReader.
*/

public class Ej4 {

    public static void escribirFicheroBorrando() {
        String rutaString = Leer.pedirCadena("Introduce ruta archivo: ");
        Path ruta = Path.of(rutaString);
        pedirFrases(ruta);
    }

    public static void pedirFrases(Path p) {
        String mensaje;
        try {
            Scanner sc = new Scanner(System.in);
            if (Files.exists(p)) {
                if (p.toString().endsWith(".txt")) {
                    do {
                        System.out.print("Introduce una frase ('fin' para terminar): ");
                        mensaje = sc.nextLine() + "\n";
                        // Forma simplificada de escribir en un fichero
                        Files.write(p, mensaje.getBytes(), StandardOpenOption.APPEND);
                    } while (!Objects.equals(mensaje, "fin\n"));

                    // leemos el archivo
                    Files.readAllLines(p).forEach(System.out::println);
                } else {
                    System.out.println("No es un archivo de texto");
                }
            } else {
                // Si el archivo no existe, lo creamos
                try {
                    Files.createFile(p);
                    System.out.println("Archivo creado en la ruta: " + p);
                } catch (IOException e) {
                    System.err.println("Error al crear el archivo: " + e.getMessage());
                } catch (SecurityException e) {
                    System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
                }
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de escritura: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}