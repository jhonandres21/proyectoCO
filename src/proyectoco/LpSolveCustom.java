package proyectoco;

import java.io.FileWriter;
import java.io.IOException;
import lpsolve.*;
import static lpsolve.LpSolve.NORMAL;

/**
 *
 * @author juan
 */
public class LpSolveCustom {

    TSM tsm;

    public LpSolveCustom(TSM tsm) {

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

    public void escribirArchivo(String formato) {

        try {
            //esta ruta toca ponerla absoluta porque estamos trabajando en otro directorio
            FileWriter fw = new FileWriter("modelo.lp");
            fw.write(formato);

            //Cierro el stream
            fw.close();

        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        } catch (Exception ex) {
        }
    }

    public void ejecutarArchivo() {

        try {
            
            LpSolve solver;
            solver = LpSolve.readLp("modelo.lp", NORMAL, "Test 1");
            solver.solve();

            // print solution
            System.out.println("Value of objective function: " + solver.getObjective());
            double[] var = solver.getPtrVariables();

            for (int i = 0; i < var.length; i++) {
                System.out.println("Value of var[" + i + "] = " + var[i]);
            }
            // delete the problem and free memory
            solver.deleteLp();

        } catch (LpSolveException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void imprimir(String formato) {
        System.out.println(formato);
    }
}
