package code;
import libs.Leer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
public class Ej2NIO {
    static Scanner sc = new Scanner(System.in);
    public static void mostrarFicherosNio() {
        System.out.print(Leer.pedirCadena("Introduce una ruta: "));
        Path path1 = Path.of(sc.nextLine());
        mostrarContNIO(path1);
    }
    // otra forma de mostrar el contenido
    // Files.newDirectoryStream(p).forEach(System.out::println);
    static void mostrarContNIO(Path p) {
        try {
            if (Files.exists(p)) {
                if (Files.isDirectory(p)) {
                    for (Path contenido : Files.newDirectoryStream(p)) {
                        String nombreFichero = String.valueOf(contenido.getFileName());
                        // para buscar segun el formato
                        if (nombreFichero.endsWith(".txt")) {
                            System.out.println(contenido.getFileName());
                        }
                    }
                }
            } else {
                System.out.println(p.getFileName() + " no existe");
            }
        } catch (SecurityException e) {
            System.err.println("No se tiene permiso para acceder a " + p.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error al procesar " + p.toAbsolutePath() + ": " + e.getMessage());
        }
    }
}
