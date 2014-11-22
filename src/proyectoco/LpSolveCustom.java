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

        //Funcion Obj
        format += "min: ";
        int contador = 1;

        /*double normalizador = 1.0;

         normalizador = 1.0 / (cantLugares - 1);
         //Distancias
         for (int j = 0; j < matrizDistancias.length; j++) {
         for (int i = 0; i < matrizDistancias.length; i++) {
         if (i != j) {
         if (contador == cantLugares - 1) {
         if (j != contador) {
         format += matrizDistancias[i][j] + " * x" + (i + 1) + (j + 1) + " + " + normalizador + "Te" + (j + 1) + " + " + (normalizador * arregloTiempoDeServicio[j]) + " + ";
         contador = 1;
         } else {
         format += matrizDistancias[i][j] + " * x" + (i + 1) + (j + 1) + " + " + normalizador + "Te" + (j + 1) + " + " + (normalizador * arregloTiempoDeServicio[j]);
         contador = 1;
         }
         } else {
         format += matrizDistancias[i][j] + " * x" + (i + 1) + (j + 1) + " + " + normalizador + "Te" + (j + 1) + " + " + (normalizador * arregloTiempoDeServicio[j]) + " + ";
         contador++;
         }
         }
         }
         }*/
        //Distancias
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (!(i == j)) {
                    format += matrizDistancias[i][j] + " * x" + (i + 1) + (j + 1) + " + ";
                    contador++;
                }
            }
        }

        //Esperas
        for (int i = 0; i < matrizDistancias.length; i++) {
            if (i == 0) {
                format += "Te" + (i + 1);
            } else {
                format += " + " + "Te" + (i + 1);
            }
        }
        //Tiempos de Servicio
        for (int i = 0; i < matrizDistancias.length; i++) {
            format += " + " + arregloTiempoDeServicio[i];

        }

        format += ";\n\n";

        //Restriccion Sumatoria Xij = 1 1<= j <= N
        contador = 1;
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (contador == cantLugares - 1) {
                        format += "x" + (i + 1) + (j + 1) + " = 1;";
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
                        format += "x" + (i + 1) + (j + 1) + " = 1;";
                        contador = 1;
                    } else {
                        format += "x" + (i + 1) + (j + 1) + " + ";
                        contador++;
                    }
                }
            }
            format += "\n";
        }
        //Sin retroceder
        contador = 1;
        for (int i = 0; i < cantLugares; i++) {
            for (int j = contador; j <= cantLugares; j++) {
                if (contador == j) {
                    continue;
                } else {
                    format += "x" + contador + j + " + " + "x" + j + contador + " <= 1;\n";
                }
            }
            contador++;
        }
        //Restricción de camino único
        for (int i = 0; i < cantLugares; i++) {
            for (int j = 0; j < cantLugares; j++) {
                if (j != 0 && i != j) {
                    format += "b" + (j + 1) + " >= " + "b" + (i + 1) + " + " + matrizDistancias[i][j] + " + " + "Te" + (i + 1) + " + " + arregloTiempoDeServicio[i] + " - " + 10000.0 + " + " + 10000.0 + " * " + "x" + (i + 1) + (j + 1) + ";";
                    format += "\n";
                }
            }
        }

        //Restriccion Ventanas de Tiempo
        for (int i = 0; i < cantLugares; i++) {

            format += matrizVentanasDeTiempo[i][0] + " <= " + "b" + (i + 1) + " + " + "Te" + (i + 1)/* + " <= " + matrizVentanasDeTiempo[i][1]*/ + ";";
            format += "\n";
        }

        //Restriccion Ventanas de Tiempo
        for (int i = 0; i < cantLugares; i++) {
            format += "b" + (i + 1) + " <= " + matrizVentanasDeTiempo[i][1] + ";";
            format += "\n";
        }

        format += "\n";
        //Definiciones xij
        contador = 1;
        format += "bin ";
        for (int i = 0; i < matrizDistancias.length; i++) {
            for (int j = 0; j < matrizDistancias.length; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (i == matrizDistancias.length - 1 && j == matrizDistancias.length - 2) {
                        format += "x" + (i + 1) + (j + 1) + ";";
                    } else {
                        format += "x" + (i + 1) + (j + 1) + ", ";
                    }
                }
            }
        }

        return format;
    }

    public void escribirArchivo(String formato) {

        try {
            //esta ruta toca ponerla absoluta porque estamos trabajando en otro directorio
            //FileWriter fw = new FileWriter("/home/juan/GitProjects/proyectoCO/modelo1.lp");
            FileWriter fw = new FileWriter("C:\\Users\\Juan Olaya O\\Documents\\GitHub\\proyectoCO\\modelo.lp");
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
            //solver = LpSolve.readLp("/home/juan/GitProjects/proyectoCO/modelo1.lp", NORMAL, "Test 1");
            solver = LpSolve.readLp("C:\\Users\\Juan Olaya O\\Documents\\GitHub\\proyectoCO\\modelo.lp", NORMAL, "Test 1");
            solver.solve();

            // print solution
            System.out.println("Value of objective function: " + solver.getObjective());
            double[] var = solver.getPtrVariables();

            for (int i = 0; i < var.length; i++) {
                System.out.println(solver.getColName(i) + " = " + var[i]);
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
