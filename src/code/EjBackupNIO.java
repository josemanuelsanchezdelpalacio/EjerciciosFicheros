package code;
import libs.Leer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class EjBackupNIO {

    public static void buckUpTotalNio() {
        try {
            String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
            Path rutaOrigen = Path.of(rutaOrigenString);
            String rutaDestinoString = Leer.pedirCadena("Introduce ruta archivo destino: ");
            Path rutaDestino = Path.of(rutaDestinoString);
            copiaBuckUp(rutaOrigen, rutaDestino);
        } catch (IOException e) {
            System.err.println("Error al realizar el backup: " + e.getMessage());
        }
    }

    public static void buckUpIncrementalNio() {
        try {
            SimpleDateFormat formatoDate = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
            String date = formatoDate.format(new Date());

            String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
            Path rutaOrigen = Path.of(rutaOrigenString);
            String rutaDestinoString = Leer.pedirCadena("Introduce ruta carpeta de backups incrementales: ");
            Path rutaDestino = Path.of(rutaDestinoString + "/" + date);
            copiaBuckUp(rutaOrigen, rutaDestino);
        } catch (IOException e) {
            System.err.println("Error al realizar el backup incremental: " + e.getMessage());
        }
    }

    public static void copiaBuckUp(Path po, Path pd) throws IOException {
        //creo la carpeta de destino si no existe
        if (Files.notExists(pd)) {
            Files.createDirectories(pd);
            //DirectoryStream<Path> es un objeto para iterar sobre un directorio y asi poder usar un for:each
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
                // itero sobre los archivos en la carpeta de origen
                for (Path archivo : stream) {
                    Path destinoArchivo = pd.resolve(archivo.getFileName());

                    // compruebo si el archivo ya existe en la anterior copia o ha sido modificado
                    if (Files.notExists(destinoArchivo) || Files.getLastModifiedTime(archivo).compareTo(Files.getLastModifiedTime(destinoArchivo)) > 0) {
                        Files.copy(archivo, destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                    }else{
                        System.out.println("El archivo '" + archivo.getFileName() + "' ya existe o no ha sido modificado en backups anteriores");
                    }
                }
            }catch (SecurityException e) {
                System.err.println("No tiene permiso de lectura: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al copiar el backup: " + e.getMessage());
            }
        }
    }
}