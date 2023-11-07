package libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FicheroEscribible {

    public static boolean ficheroEscribible(Path p){
        boolean ficheroOK = false;
        if(Files.exists(p)){
            if (Files.isWritable(p)) {
                ficheroOK = true;
            }
        }else {
            try {
                Files.createFile(p);
                ficheroOK = true;
                System.out.println("Archivo creado en la ruta: " + p);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
        return ficheroOK;
    }

    public static boolean ficheroLegible(Path p) {
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isReadable(p)) {
                ficheroOK = true;
            }
        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
                System.out.println("Archivo creado en la ruta: " + p);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
        return ficheroOK;
    }
}
