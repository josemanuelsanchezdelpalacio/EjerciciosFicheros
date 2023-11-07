package code;

import libs.Leer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EjBackup {
    public static void buckUpTotal() {
        File rutaOrigen = new File(Leer.pedirCadena("Introduce ruta archivo original: "));
        File rutaDestino = new File(Leer.pedirCadena("Introduce ruta archivo destino: "));
        copiaCompleta(rutaOrigen, rutaDestino);
    }

    public static void buckUpIncremental() {
        SimpleDateFormat formatoDate = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
        String date = formatoDate.format(new Date());

        File rutaOrigen = new File(Leer.pedirCadena("Introduce ruta carpeta original: "));
        File rutaDestino = new File(Leer.pedirCadena("Introduce ruta carpeta de backup incremental: ") + "/" + date);

        copiaIncremental(rutaOrigen, rutaDestino);
    }

    public static void copiaCompleta(File po, File pd) {
        try {
            //creo la carpeta de destino si no existe
            if (!pd.exists()) {
                if (pd.mkdir()) {
                    //itero sobre los archivos en la carpeta de origen y hago una copia total
                    File[] archivos = po.listFiles();
                    if (archivos != null) {
                        for (File archivo : archivos) {
                            if (archivo.isFile()) {
                                File destinoFile = new File(pd, archivo.getName());
                                copiarArchivo(archivo, destinoFile);
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

        public static void copiaIncremental(File po, File pd) {
        try {
            //creo la carpeta de destino si no existe
            if (!pd.exists()) {
                if (pd.mkdir()) {
                    //itero sobre los archivos en la carpeta de origen
                    File[] archivos = po.listFiles();
                    if (archivos != null) {
                        for (File archivo : archivos) {
                            //hago la copia desde la ultima modificacion
                            File destinoFile = new File(pd, archivo.getName());
                            if (!destinoFile.exists() || archivo.lastModified() > destinoFile.lastModified()) {
                                copiarArchivo(archivo, destinoFile);
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

    //metodo para copiar los archivos
    public static void copiarArchivo(File origen, File destino) throws IOException {
        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
