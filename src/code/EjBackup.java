package code;
import libs.Leer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class EjBackup {
    public static void buckUpTotal() {
        try {
            String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
            File rutaOrigen = new File(rutaOrigenString);
            String rutaDestinoString = Leer.pedirCadena("Introduce ruta archivo destino: ");
            File rutaDestino = new File(rutaDestinoString);

            copiaBuckUp(rutaOrigen, rutaDestino);
        } catch (IOException e) {
            System.err.println("Error al realizar el backup: " + e.getMessage());
        }
    }

    public static void buckUpIncremental() {
        try {
            SimpleDateFormat formatoDate = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
            String date = formatoDate.format(new Date());

            String rutaOrigenString = Leer.pedirCadena("Introduce ruta archivo original: ");
            File rutaOrigen = new File(rutaOrigenString);
            String rutaDestinoString = Leer.pedirCadena("Introduce ruta carpeta de backups incrementales: ");
            File rutaDestino = new File(rutaDestinoString + "/" + date);

            copiaBuckUp(rutaOrigen, rutaDestino);
        } catch (IOException e) {
            System.err.println("Error al realizar el backup incremental: " + e.getMessage());
        }
    }

    public static void copiaBuckUp(File po, File pd) throws IOException {
        try {
            // creo la carpeta de destino si no existe
            if (!pd.exists()) {
                if (pd.mkdir()) {
                    // itero sobre los archivos en la carpeta de origen
                    File[] archivos = po.listFiles();
                    if (archivos != null) {
                        for (File archivo : archivos) {
                            if (archivo.isFile()) {
                                File destinoFile = new File(pd, archivo.getName());
                                Files.copy(archivo.toPath(), destinoFile.toPath());
                            }
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de lectura: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al copiar el backup: " + e.getMessage());
        }
    }
}