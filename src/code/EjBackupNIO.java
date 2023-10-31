package code;

import libs.Leer;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class EjBackupNIO {

    public static void buckUpTotalNio() {
        Path rutaOrigen = Path.of(Leer.pedirCadena("Introduce ruta archivo original: "));
        Path rutaDestino = Path.of(Leer.pedirCadena("Introduce ruta archivo destino: "));
        copiaCompleta(rutaOrigen, rutaDestino);
    }

    public static void buckUpIncrementalNio() {
        SimpleDateFormat formatoDate = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
        String date = formatoDate.format(new Date());

        Path rutaOrigen = Path.of(Leer.pedirCadena("Introduce ruta carpeta original: "));
        Path rutaTotal = Path.of(Leer.pedirCadena("Introduce ruta carpeta del total: "));
        Path rutaDestino = Path.of("src/resources/incre" + "/" + date);

        copiaIncremental(rutaOrigen, rutaTotal, rutaDestino);
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

    public static void copiaIncremental(Path po, Path pc, Path pd) {
        // Creo la carpeta de destino si no existe
        if (Files.notExists(pd)) {
            try {
                Files.createDirectories(pd);
            } catch (IOException e) {
                System.err.println("Error al crear el directorio de destino");
            }
        }

        //listo los archivos completos
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(pc)) {
            for (Path pCompleto : stream) {
                Path archivoCompleto = pc.relativize(pCompleto);
                Path archivoIncremental = pd.resolve(archivoCompleto);
                Path archivoOriginal = po.resolve(archivoCompleto);

                //comparo el archivo original y el completo para comprobar si existe un nuevo archivo. Si existe lo copia, si no solo saca mensaje de los archivos que no han sido modificados
                if (Files.getLastModifiedTime(archivoOriginal).compareTo(Files.getLastModifiedTime(pCompleto)) > 0) {
                    // Copio el archivo desde la carpeta completa a la carpeta incremental
                    Files.copy(pCompleto, archivoIncremental, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("El archivo '" + archivoCompleto.getFileName() + "' ya existe o no ha sido modificado desde la última copia");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al realizar la copia incremental: " + e.getMessage());
        }
    }
}