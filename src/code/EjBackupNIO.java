package code;
import libs.Leer;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class EjBackupNIO {
    static Scanner sc = new Scanner(System.in);
    public static void buckUpTotalNio() {
        String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
        Path rutaOrigen = Path.of(rutaOrigenString);
        String rutaDestinoString = Leer.pedirCadena("Introduce ruta archivo destino: ");
        Path rutaDestino = Path.of(rutaDestinoString);
        copiaBuckUp(rutaOrigen, rutaDestino);
    }
    public static void buckUpIncrementalNio() {
        SimpleDateFormat formatoDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = formatoDate.format(new Date());

        String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
        Path rutaOrigen = Path.of(rutaOrigenString);
        String rutaDestinoString = Leer.pedirCadena("Introduce ruta archivo destino: ");
        Path rutaDestino = Path.of(rutaDestinoString + date);
        copiaBuckUp(rutaOrigen, rutaDestino);
    }
    public static void copiaBuckUp(Path po, Path pd){

        // Crear la carpeta de destino si no existe
        if (pd.toFile().mkdir()) {
            //DirectoryStream<Path> es un objeto apra iterar sobre un directorio y asi poder usar un for:each
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
                // Iterar sobre los archivos en la carpeta de origen
                for (Path archivo : stream) {
                    //si el archivo de origen existe y su nombre no es igual al nombre de destino
                    if (Files.exists(po) && !po.getFileName().equals(pd.getFileName())) {
                        //entonces hace la copia
                        Files.copy(archivo, pd.resolve(archivo.getFileName()));
                    }
                }
            } catch (SecurityException e) {
                System.err.println("No tiene permiso de lectura: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al copiar el backup: " + e.getMessage());
            }
        }
    }
}