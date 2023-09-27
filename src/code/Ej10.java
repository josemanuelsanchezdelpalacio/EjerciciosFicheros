package code;

import libs.Leer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                    List<String> contenido = Files.readAllLines(p);
                    String linea;
                    HashMap<Character, Integer> mapCaracteres = new HashMap<>();

                    for (String cont : contenido) {
                        while ((linea = String.valueOf(cont)) != null) {
                            for (char caracter : linea.toCharArray()) {
                                // verifico si el caracter es un caracter ASCII mirando si su valor numerico esta entero
                                if (caracter <= 127) {
                                    //si el map contiene un caracter entre 0 y 127
                                    if (mapCaracteres.containsKey(caracter)) {
                                        //guardo en el map el caracter como clave y el siguiente caracter como valor.
                                        mapCaracteres.put(caracter, mapCaracteres.get(caracter) + 1);
                                    } else {
                                        mapCaracteres.put(caracter, 1);
                                    }
                                }
                            }
                        }
                    }

                    // itero el mapa para sacar el numero de repeticiones de cada caracter
                    System.out.println("Caracter | Numero de repeticiones");
                    for (char caracter : mapCaracteres.keySet()) {
                        int repeticiones = mapCaracteres.get(caracter);
                        System.out.println(caracter + "       | " + repeticiones);
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
