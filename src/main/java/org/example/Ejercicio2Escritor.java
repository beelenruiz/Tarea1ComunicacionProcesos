package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio2Escritor {
    static void main(String[] args) {
        try {
            // lee el numero con System.in
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine());

            // sumamos 1 y mostramos, 10 veces
            for (int i = num + 1;i <= num + 10; i++){
                System.out.println(i);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
    }
}
