package code;

import libs.Leer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Ej10 {

    public static void leerASCII() {
        String rutaOrigenString = Leer.pedirCadena("Introduce ruta fichero: ");
        Path rutaOrigen = Path.of(rutaOrigenString);
        leer(rutaOrigen);
    }
    public static void leer(Path p){

        if (Files.exists(p)) {
            if (p.toString().endsWith(".txt")) {
                try {

                    String linea = "";
                    char caracter = 0;
                    //creo un hashmap con una clave como char y un valor como integer
                    HashMap<Character, Integer> mapLineas = new HashMap<>();
                    Files.readAllLines(p).forEach(linea.charAt(caracter));

                    // comprobamos si los caracteres estan en el hashmap
                    if (mapLineas.containsKey(caracter)) {
                        // si aparece el caracter le sumamos 1 y lo añadimos
                        mapLineas.put(caracter, mapLineas.get(caracter) + 1);
                    } else {
                        mapLineas.put(caracter, 1);
                    }

                    System.out.println("letra | numero repeticiones");
                    // recorremos el hashmap
                    for (char letra : mapLineas.keySet()) {
                        char clave = letra;
                        int cont_letra = mapLineas.get(letra);
                        System.out.print(clave + "     | " + cont_letra + "\n");
                    }
                } catch (SecurityException e) {
                    System.err.println("No tiene permiso de lectura: " + e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("No es un archivo de texto");
            }
        } else {
            // Si el archivo no existe, lo creamos
            try {
                Files.createFile(p);
                System.out.println("Archivo creado en la ruta: " + p);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
    }

}
