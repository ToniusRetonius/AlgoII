package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float res [] = new float[largo];
        for (int i = 0 ; i < largo ; i++){
            float x = entrada.nextFloat();
            res[i] = x;
        }
        return res;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float [][] res = new float[filas][columnas];
        for (int f = 0; f < filas; f ++){
            float x [] = leerVector(entrada, columnas);
            res[f] = x ; 

        }
        return res;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        for (int i = 1; i <= alto; i++) {
            for (int j = 0; j < alto -i ; j++) {
                 salida.print(" ");
            }

            for (int j=0 ; j <= 2*(i-1); j++) {
                salida.print("*");
            }
            for (int j = 0; j < alto -i ; j++) {
                 salida.print(" ");
            }
            salida.println();
        }
    }
}
