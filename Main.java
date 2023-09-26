import code.*;
import libs.Leer;
public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            System.out.println("0. Salir");
            System.out.println("1. Ej1: Comprobar ficheros");
            System.out.println("2. Ej1b: Comprobar ficheros NIO");
            System.out.println("3. Ej2: Mostrar ficheros");
            System.out.println("4. Ej2b: Mostrar ficheros NIO");
            System.out.println("5. Ej3: Leer archivo");
            System.out.println("6. Ej6: Escribir archivo borrando lo anterior");
            System.out.println("7. Ej6: Escribir archivo hasta que se escriba fin");
            System.out.println("8. EjBackup total");
            System.out.println("9. EjBackup incremental");
            System.out.println("10. EjBackup total NIO");
            System.out.println("11. Ej6Backup incremental NIO");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0 -> {salir = true;}
                case 1 -> {EJ1.comprobarFicheros();}
                case 2 -> {Ej1NIO.comprobarFicherosNio();}
                case 3 -> {Ej2.mostrarFicheros();}
                case 4 -> {Ej2NIO.mostrarFicherosNio();}
                case 5 -> {Ej3.leerFichero();}
                case 6 -> {Ej4.escribirFicheroBorrando();}
                case 7 -> {Ej5.escribirFichero();}
                case 8 -> {EjBackup.buckUpTotal();}
                case 9 -> {EjBackup.buckUpIncremental();}
                case 10 -> {EjBackupNIO.buckUpTotalNio();}
                case 11 -> {EjBackupNIO.buckUpIncrementalNio();}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
        } while (!salir);
    }
}