package proyectoco;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        //Restricción de camino 
        for (int i = 0; i < cantLugares; i++) {
            for (int j = 0; j < cantLugares; j++) {
                if (j != 0 && i != j) {
                    format += "b" + (j + 1) + " >= " + "b" + (i + 1) + " + " + matrizDistancias[i][j] + " + " + "Te" + (i + 1) + " + " + arregloTiempoDeServicio[i] + " - " + 10000.0 + " + " + 10000.0 + " * " + "x" + (i + 1) + (j + 1) + ";";
                    format += "\n";
                }
            }
        }

        //Restricción de Asignación de Tiempo de viaje
        for (int i = 0; i < cantLugares; i++) {
            for (int j = 0; j < cantLugares; j++) {
                if (j != 0 && i != j) {
                    format += "b" + (j + 1) + " - " + 10000.0 + " + " + 10000.0 + " * " + "x" + (i + 1) + (j + 1) + " <= " + "b" + (i + 1) + " + " + matrizDistancias[i][j] + " + " + "Te" + (i + 1) + " + " + arregloTiempoDeServicio[i] + ";";
                    format += "\n";
                }
            }
        }

        //Restriccion Ventanas de Tiempo cota inferior
        for (int i = 0; i < cantLugares; i++) {

            format += matrizVentanasDeTiempo[i][0] + " <= " + "b" + (i + 1) + " + " + "Te" + (i + 1)/* + " <= " + matrizVentanasDeTiempo[i][1]*/ + ";";
            format += "\n";
        }

        //Restriccion Ventanas de Tiempo cota superior
        for (int i = 0; i < cantLugares; i++) {
            format += "b" + (i + 1) + " <= " + matrizVentanasDeTiempo[i][1] + ";";
            format += "\n";
        }

        //Restriccion Tiempo de Espera
        for (int j = 0; j < cantLugares; j++) {
            for (int i = 0; i < cantLugares; i++) {
                if (i != j) {
                    format += "Te" + (j + 1) + " >= " + matrizVentanasDeTiempo[j][0] + " - b" + (i + 1) + " - " + matrizDistancias[i][j] + " - " + "Te" + (i + 1) + " - " + arregloTiempoDeServicio[i] + " - " + 10000.0 + " + " + 10000.0 + " * " + "x" + (i + 1) + (j + 1) + ";";
                    format += "\n";
                }
            }
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
            //FileWriter fw = new FileWriter("/home/john/Escritorio/modelo.lp");
            FileWriter fw = new FileWriter("C:\\Users\\Juan Olaya O\\Documents\\GitHub\\proyectoCO\\modelo.lp");
            fw.write(formato);

            //Cierro el stream
            fw.close();

        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        } catch (Exception ex) {
        }
    }

    public String ejecutarArchivo() {
        LpSolve solver = null;
        String respuesta = "";
        ArrayList<String> recorrido = new ArrayList();
        double esperaTotal = 0;

        try {

            //solver = LpSolve.readLp("/home/juan/GitProjects/proyectoCO/modelo1.lp", NORMAL, "Test 1");
            //solver = LpSolve.readLp("/home/john/Escritorio/modelo.lp", NORMAL, "Test 1");
            solver = LpSolve.readLp("C:\\Users\\Juan Olaya O\\Documents\\GitHub\\proyectoCO\\modelo.lp", NORMAL, "Test 1");
            solver.solve();

            // print solution
            if (solver.getObjective() == 1.0E30) {
                respuesta += "No existe solución.\nEl problema con los parámetros actuales NO TIENE SOLUCIÓN FACTIBLE";
            } else {
                respuesta += "La solucion encontrada fue: " + solver.getObjective();
                respuesta += "\n\n";
                System.out.println("La solucion encontrada fue: " + solver.getObjective());
                double[] var = solver.getPtrVariables();
                int j = 1;
                for (int i = 0; i < var.length; i++) {
                    System.out.println(solver.getColName(j) + " = " + var[i]);
                    if (solver.getColName(j).startsWith("Te")) {
                        esperaTotal += var[i];
                    } else if (solver.getColName(j).startsWith("x") && var[i] == 1) {
                        recorrido.add(solver.getColName(j));
                    }
                    j++;
                }

                respuesta += "La ruta encontrada fue:\n" + buscarRuta(recorrido) + "\n\n";
                respuesta += "El tiempo de espera total fue: " + esperaTotal + "\n\n";

                // delete the problem and free memory
                solver.deleteLp();
            }
        } catch (LpSolveException ex) {
            System.out.println("Error: " + ex);
        }

        return respuesta;
    }

    public String buscarRuta(ArrayList<String> recorrido) {
        String ruta = "";
        String inicio = "1";
        while (!recorrido.isEmpty()) {
            for (int i = 0; i < recorrido.size(); i++) {
                if (recorrido.get(i).substring(1, 2).equals(inicio)) {
                    if ((recorrido.size() - 1) == 0) {
                        ruta += inicio + " -> 1";
                    } else {
                        ruta += inicio + " -> ";
                    }
                    inicio = recorrido.get(i).substring(2);
                    recorrido.remove(i);
                    break;
                }
            }
        }

        return ruta;
    }

    public void imprimir(String string) {
        System.out.println(string);
    }
}
