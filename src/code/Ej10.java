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

    public static void leer(Path p) {

        if (Files.exists(p)) {
            if (p.toString().endsWith(".txt")) {
                try {
                    // leo todas las lineas del fichero
                    List<String> lineas = Files.readAllLines(p);
                    // creo un map para guardar los caracteres
                    HashMap<Character, Integer> mapCaracteres = new HashMap<>();
                    // itero sobre las lineas del fichero
                    for (String linea : lineas) {
                        // itero sobre cada caracter
                        for (char caracter : linea.toCharArray()) {
                            // compruebo que el caracter esta entre 0 y 127
                            if (caracter <= 127) {
                                if (mapCaracteres.containsKey(caracter)) {
                                    // si ya existe el caracter aumenta el contador en 1
                                    mapCaracteres.put(caracter, mapCaracteres.get(caracter) + 1);
                                } else {
                                    // si no existe lo añado comenzando en 1
                                    mapCaracteres.put(caracter, 1);
                                }
                            }
                        }
                    }

                    System.out.println("Carácter | Número de repeticiones");
                    for (char caracter : mapCaracteres.keySet()) {
                        int repeticiones = mapCaracteres.get(caracter);
                        System.out.println(caracter + "       | " + repeticiones);
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                }
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

