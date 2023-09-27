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
            System.out.println("6. Ej4: Escribir archivo borrando anterior");
            System.out.println("7. Ej5: Escribir archivo hasta que se escriba fin");
            System.out.println("8. Ej6: Backup fichero");
            System.out.println("9. Ej10: Leer contenido ASCII");
            System.out.println("10. EjBackup archivo total");
            System.out.println("11. EjBackup archivo incremental");
            System.out.println("12. EjBackup archivo total NIO");
            System.out.println("13. EjBackup archivo incremental NIO");

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
                case 8 -> {Ej6.buckUpEj5();}
                case 9 -> {Ej10.leerASCII();}
                case 10 -> {EjBackup.buckUpTotal();}
                case 11 -> {EjBackup.buckUpIncremental();}
                case 12 -> {EjBackupNIO.buckUpTotalNio();}
                case 13 -> {EjBackupNIO.buckUpIncrementalNio();}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
        } while (!salir);
    }
}