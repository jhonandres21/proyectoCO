/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoco;

/**
 *
 * @author juan
 */
public class LpSolve {

    TSM tsm;

    public LpSolve(TSM tsm) {

        this.tsm = tsm;

    }

    public String lpFormatContructor() {
        String format = "";
        int cantLugares = tsm.getCantLugares();
        double[] arregloTiempoDeServicio = tsm.getTiempoDeServicio();
        double[][] matrizVentanasDeTiempo = tsm.getMatrizVentanasDeTiempo();
        double[][] matrizDistancias = tsm.getMatrizDistancias();

        format += "/* model.lp */\n\n";
        //Funcion Obj
        format += "min: ";
        int contador = 1;
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (contador == cantLugares - 1) {
                        format += "(" + arregloTiempoDeServicio[i] + "+" + matrizDistancias[i][j] + ")" + " x" + (i + 1) + (j + 1);
                        contador = 1;
                    } else {
                        format += "(" + arregloTiempoDeServicio[i] + "+" + matrizDistancias[i][j] + ")" + " x" + (i + 1) + (j + 1) + " + ";
                        contador++;
                    }
                }
            }
        }
        //Restriccion Sumatoria Xij = 1 1<= j <= N
        format += ";\n\n";
        contador = 1;
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (contador == cantLugares - 1) {
                        format += "x" + (i + 1) + (j + 1) + " = 1";
                        contador = 1;
                    } else {
                        format += "x" + (i + 1) + (j + 1) + " + ";
                        contador++;
                    }
                }
            }
            format += "\n";
        }

        //Restriccion Sumatoria Xij = 1 1<= i <= N
        contador = 1;
        for (int j = 0; j < cantLugares; j++) {
            for (int i = 0; i < cantLugares; i++) {
                if (i == j) {
                    continue;
                } else {
                    if (contador == cantLugares - 1) {
                        format += "x" + (i + 1) + (j + 1) + " = 1";
                        contador = 1;
                    } else {
                        format += "x" + (i + 1) + (j + 1) + " + ";
                        contador++;
                    }
                }
            }
            format += "\n";
        }

        /*int inicial = -1;
         for (int i = 0; i < matrizVentanasDeTiempo.length; i++) {
         if (matrizVentanasDeTiempo[i][0] == 0) {
         inicial = i;
         }
         }

         contador = 1;
         for (int i = 0; i < matrizDistancias.length; i++) {
         for (int j = 0; j < matrizDistancias.length; j++) {

         }
         }
         */
        //Restricción de camino único
        for (int j = 0; j < cantLugares; j++) {
            for (int i = 0; i < cantLugares; i++) {
                if (i == j) {
                    continue;
                } else {
                    format += "b" + (j + 1) + " >= " + "b" + (i + 1) + " + (" + arregloTiempoDeServicio[i] + " + " + matrizDistancias[i][j] + ")" + " - " + 999999999 + "(" + " 1 - " + "x" + (i + 1) + (j + 1) + ");";
                    format += "\n";
                }
            }
        }
        //Restriccion Ventanas de Tiempo
        for (int i = 0; i < cantLugares; i++) {
            format += matrizVentanasDeTiempo[i][0] + " <= " + "b" + (i + 1) + " <= " + matrizVentanasDeTiempo[i][1] + ";";
            format += "\n";
        }

        //Definicion bi;
        for (int i = 0; i < cantLugares; i++) {
            format += "b" + (i + 1) + " >= 0;\n";
        }

        //Restricciones obvias
        format += "bin ";
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    format += "x" + (i + 1) + (j + 1) + ", ";
                }
            }
        }

        return format;
    }

    public void imprimir(String formato) {
        System.out.println(formato);
    }

}
