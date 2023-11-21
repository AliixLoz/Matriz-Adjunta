package matrizadjunta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Aliix
 */
public class MatrizAdjunta {

    private static int[][] calcularMA(int[][] matriz) {
        int n = matriz.length;
        int[][] adjunta = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cofactor = Cofactor(matriz, i, j);
                adjunta[j][i] = cofactor;
            }
        }

        return adjunta;
    }

    private static int Cofactor(int[][] matriz, int fila, int columna) {
        int signo = (fila + columna) % 2 == 0 ? 1 : -1;
        int submatriz[][] = Submatriz(matriz, fila, columna);
        return signo * determinante(submatriz);
    }

    private static int[][] Submatriz(int[][] matriz, int fila, int columna) {
        int n = matriz.length;
        int[][] submatriz = new int[n - 1][n - 1];
        int subfila = 0, subcolumna = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != fila && j != columna) {
                    submatriz[subfila][subcolumna++] = matriz[i][j];

                    if (subcolumna == n - 1) {
                        subcolumna = 0;
                        subfila++;
                    }
                }
            }
        }

        return submatriz;
    }

    private static int determinante(int[][] matriz) {
        int n = matriz.length;

        if (n == 1) {
            return matriz[0][0];
        }

        if (n == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }

        int det = 0;

        for (int i = 0; i < n; i++) {
            int signo = (i % 2 == 0) ? 1 : -1;
            int cofactor = signo * matriz[0][i] * determinante(Submatriz(matriz, 0, i));
            det += cofactor;
        }

        return det;
    }

    private static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]" + "   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingresa el número de filas: ");
        int n = Integer.parseInt(reader.readLine());

        System.out.print("Ingresa el número de columnas: ");
        int m = Integer.parseInt(reader.readLine());

        int[][] matriz = new int[n][m];

        if (n == m) {
            System.out.println("Ingresa los elementos de la matriz:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print("Valor [" + (i + 1) + "][" + (j + 1) + "]: ");
                    matriz[i][j] = Integer.parseInt(reader.readLine());
                }
            }

            int[][] adjunta = calcularMA(matriz);
            System.out.println("\nMatriz Adjunta:");
            mostrarMatriz(adjunta);

            reader.close();
        } else {
            System.out.println("No se puede, no es una matriz cuadrada");
        }
    }

}
