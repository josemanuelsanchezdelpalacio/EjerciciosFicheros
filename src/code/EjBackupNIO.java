package code;

import libs.FicheroEscribible;
import libs.Leer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static libs.FicheroEscribible.ficheroEscribible;
import static libs.FicheroEscribible.ficheroLegible;

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
        try {
            if (ficheroEscribible(pd)) {
                //DirectoryStream<Path> es un objeto para iterar sobre un directorio y asi poder usar un for:each
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
                    // itero sobre los archivos en la carpeta de origen y hago una copia total
                    for (Path archivo : stream) {
                        if(ficheroLegible(po)) {
                            Path destinoArchivo = pd.resolve(archivo.getFileName());
                            Files.copy(archivo, destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error al copiar el backup: " + e.getMessage());
                }
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de lectura: " + e.getMessage());
        }
    }

    public static void copiaIncremental(Path po, Path pc, Path pd) {
        //creo la carpeta de destino si no existe
        try {
            if (ficheroEscribible(pd)) {
                //listo los archivos de la carpeta original
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(po)) {
                    for (Path pOriginal : stream) {

                        if(ficheroLegible(po)) {
                            Path archivoOriginal = po.relativize(pOriginal);
                            Path archivoCompleto = pc.resolve(archivoOriginal);
                            Path archivoIncremental = pd.resolve(archivoOriginal);

                            //comparo el archivo original y el completo para comprobar si existe un nuevo archivo. Si no existe el archivo solo saca un mensaje de que no hay archivos nuevos
                            if (Files.exists(archivoCompleto) && Files.getLastModifiedTime(pOriginal).compareTo(Files.getLastModifiedTime(archivoCompleto)) <= 0) {
                                System.out.println("El archivo '" + archivoOriginal.getFileName() + "' ya existe o no ha sido modificado desde la última copia");
                            }else {
                                //copio el archivo nuevo desde la carpeta original a la carpeta incremental
                                Files.copy(pOriginal, archivoIncremental, StandardCopyOption.REPLACE_EXISTING);
                                System.out.println("Se ha copiado: " + archivoOriginal.getFileName());
                            }
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error al realizar la copia incremental: " + e.getMessage());
                }
            }
        } catch (SecurityException e) {
            System.err.println("No tiene permiso de lectura: " + e.getMessage());
        }
    }
}
