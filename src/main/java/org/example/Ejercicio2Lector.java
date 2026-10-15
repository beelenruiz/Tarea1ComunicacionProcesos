package org.example;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.io.*;

public class Ejercicio2Lector {
    static void main(String[] args) {
        System.out.print("Introduce un número:");
        int num = Entrada.entero();

        // creamos el proceso escritor
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), "org.example.Ejercicio2Escritor");

        try {
            // inicio el proceso
            Process p = pb.start();

            // buffer para escribir en la entrada el numero introducido
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            bw.write(String.valueOf(num));

            bw.flush();
            bw.close();

            // gestionamos posibles errores
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String linea = null;
            while ((linea = brError.readLine()) != null) {
                System.out.println("ERROR: " + linea);
            }
            brError.close();

            // leemos salida estandar del escritor
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String lector;
            while ((lector = br.readLine()) != null){
                System.out.println(lector);
            }

            p.waitFor();

        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Se interrumpió la espera.");
        }
    }
}
