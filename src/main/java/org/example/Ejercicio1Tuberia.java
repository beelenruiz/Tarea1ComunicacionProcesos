package org.example;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Ejercicio1Tuberia {
    public static void main(String[] args) {
        // procesos
        ProcessBuilder pb1 = new ProcessBuilder("CMD", "/C", "DIR");
        ProcessBuilder pb2 = new ProcessBuilder("CMD", "/C", "SORT");

        try {
            // activamos los procesos
            Process p1 = pb1.start();
            Process p2 = pb2.start();

            // buffer para lectura de la salida del proceso 1
            BufferedReader br1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));

            // buffer para escritura en la entrada del proceso 2
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));

            // escribimos la salida del proceso1 (br) en la entrada del proceso2 (bw)
            String lector1;
            while ((lector1 = br1.readLine()) != null){
                bw.write(lector1);
                bw.newLine();
            }
            bw.flush(); // aseguramos que los datos viajen al proceso
            bw.close(); // cerramos el buffer de escritura para indicar que no hay mas datos
            br1.close();

            // gestionamos posibles errores
            BufferedReader brError = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
            String linea = null;
            while ((linea = brError.readLine()) != null) {
                System.out.println("ERROR: " + linea);
            }
            brError.close();

            // lectura del proceso2
            BufferedReader br2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));

            // mostramos la salida del proceso2
            String lector2;
            while ((lector2 = br2.readLine()) != null){
                System.out.println(lector2);
            }
            br2.close();

            p1.waitFor();
            p2.waitFor();

            System.out.println("Finalizada la ejecución");

        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Se interrumpió la espera.");
        }
    }


}
