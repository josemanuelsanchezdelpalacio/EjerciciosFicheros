package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class EjBackupNIO {

    public static void buckUpTotalNio() {
        String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
        Path rutaOrigen = Path.of(rutaOrigenString);
        String rutaDestinoString = Leer.pedirCadena("Introduce ruta archivo destino: ");
        Path rutaDestino = Path.of(rutaDestinoString);
        copiaCompleta(rutaOrigen, rutaDestino);
    }

    public static void buckUpIncrementalNio() {
        SimpleDateFormat formatoDate = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
        String date = formatoDate.format(new Date());

        Path rutaOrigen = Path.of(Leer.pedirCadena("Introduce ruta carpeta original: "));
        Path rutaDestino = Path.of(Leer.pedirCadena("Introduce ruta carpeta de backup incremental: ") + "/" + date);

        copiaIncremental(rutaOrigen, rutaDestino);
    }

    public static void copiaCompleta(Path po, Path pd) {
        //creo la carpeta de destino si no existe
        if (Files.notExists(pd)) {
            try {
                Files.createDirectories(pd);
            } catch (IOException e) {
                System.err.println("Error al crear el directorio de destino");
            }
        }
        //DirectoryStream<Path> es un objeto para iterar sobre un directorio y asi poder usar un for:each
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
            // itero sobre los archivos en la carpeta de origen y hago una copia total
            for (Path archivo : stream) {
                Path destinoArchivo = pd.resolve(archivo.getFileName());
                Files.copy(archivo, destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println("Error al copiar el backup: " + e.getMessage());
        }
    }

    public static void copiaIncremental(Path po, Path pd) {
        //creo la carpeta de destino si no existe
        if (Files.notExists(pd)) {
            try {
                Files.createDirectories(pd);
            } catch (IOException e) {
                System.err.println("Error al crear el directorio de destino");
            }
        }
        //DirectoryStream<Path> es un objeto para iterar sobre un directorio y asi poder usar un for:each
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
            // itero sobre los archivos en la carpeta de origen
            for (Path archivo : stream) {
                Path archivoOriginal = po.resolve(archivo.getFileName());
                Path destinoArchivo = pd.resolve(archivo.getFileName());

                // compruebo si el archivo es nuevo desde la última copia total
                if (Files.notExists(destinoArchivo) || Files.getLastModifiedTime(archivoOriginal).compareTo(Files.getLastModifiedTime(destinoArchivo)) > 0) {
                    Files.copy(archivoOriginal, destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("El archivo '" + archivo.getFileName() + "' ya existe o no ha sido modificado en backups anteriores");
                }
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de lectura: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al copiar el backup: " + e.getMessage());
        }
    }
}