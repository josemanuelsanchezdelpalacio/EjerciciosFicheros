package code;
import java.io.File;
public class EJ1 {
    public static void comprobarFicheros() {
        File f1 = new File("src/resources/ficheroEj1.txt");
        File f2 = new File("src/resources/ficheroEj2.txt");
        File p1 = new File("src/resources/ej1");
        File p2 = new File("src/resources/ej2");
        comprobar(f1); comprobar(f2); comprobar(p1); comprobar(p2);
    }
    static void comprobar(File f) {
        try {
            if (f.exists()) {
                System.out.println(f.getName() + " existe. Ruta: " + f.getAbsolutePath());
                if (f.isFile()) {System.out.println(f.getName() + " es un fichero");}
                if (f.isDirectory()) {System.out.println(f.getName() + " es un directorio");}
            } else {
                System.out.println(f.getName() + " no existe");
            }
        } catch (SecurityException e) {
            System.err.println("No se tiene permiso para acceder a " + f.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error al procesar " + f.getAbsolutePath() + ": " + e.getMessage());
        }
    }
}