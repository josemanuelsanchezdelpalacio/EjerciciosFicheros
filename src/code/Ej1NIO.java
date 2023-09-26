package code;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
public class Ej1NIO {
    public static void comprobarFicherosNio() {
        Path f1 = Path.of("src/src/resources/ficheroEj1.txt");
        Path f2 = Path.of("src/src/resources/ficheroEj2.txt");
        Path p1 = Path.of("src/src/resources/ej1");
        Path p2 = Path.of("src/src/resources/ej2");
        comprobar(f1); comprobar(f2); comprobar(p1); comprobar(p2);
    }
    static void comprobar(Path p) {
        try {
            if (Files.exists(p)) {
                System.out.println(p.getFileName() + " existe. Ruta: " + p.toAbsolutePath());
                if (Files.isRegularFile(p)) {System.out.println(p.getFileName() + " es un fichero");}
                if (Files.isDirectory(p)) {
                    System.out.println(p.getFileName() + " es un directorio\n");
                    BasicFileAttributes bfa = null;
                    try {
                        bfa = Files.readAttributes(p, BasicFileAttributes.class);
                        System.out.println(bfa.lastAccessTime());
                        System.out.println(bfa.lastModifiedTime());
                        System.out.println(bfa.size());
                        System.out.println(bfa.isSymbolicLink());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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

