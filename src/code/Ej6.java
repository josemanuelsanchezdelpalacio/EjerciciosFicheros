package code;

import libs.Leer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Ej6 {

    public static void buckUpEj5() {
        String rutaOrigenString = Leer.pedirCadena("Introduce ruta fichero original: ");
        Path rutaOrigen = Path.of(rutaOrigenString);
        String rutaDestinoString = Leer.pedirCadena("Introduce ruta fichero destino: ");
        Path rutaDestino = Path.of(rutaDestinoString);
        copiaBuckUp(rutaOrigen, rutaDestino);
    }
    public static void copiaBuckUp(Path po, Path pd){

        if (Files.exists(po)) {
            if (po.toString().endsWith(".txt")) {
                try {
                    Files.copy(po, pd, StandardCopyOption.REPLACE_EXISTING);
                } catch (FileNotFoundException e) {
                    System.out.print("No se ha encontrado el archivo");
                } catch (SecurityException e) {
                    System.err.println("No tiene permiso de lectura: " + e.getMessage());
                } catch (IOException e){
                    System.err.println("Error al leer: " + e.getMessage());
                }
            } else {
                System.out.println("No es un archivo de texto");
            }
        } else {
            // Si el archivo no existe, lo creamos
            try {
                Files.createFile(po);
                System.out.println("Archivo creado en la ruta: " + po);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
    }
}