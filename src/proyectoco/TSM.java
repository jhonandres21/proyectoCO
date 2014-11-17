package proyectoco;

/**
 *
 * @author juan
 */
public class TSM {

    int cantLugares;
    double[] arregloTiempoDeServicio;
    double[][] matrizVentanasDeTiempo;
    double[][] matrizDistancias;

    public int getCantLugares() {
        return cantLugares;
    }

    public void setCantLugares(int cantLugares) {
        this.cantLugares = cantLugares;
    }

    public double[] getTiempoDeServicio() {
        return arregloTiempoDeServicio;
    }

    public void setTiempoDeServicio(double[] tiempoDeServicio) {
        this.arregloTiempoDeServicio = tiempoDeServicio;
    }

    public double[][] getMatrizVentanasDeTiempo() {
        return matrizVentanasDeTiempo;
    }

    public void setMatrizVentanasDeTiempo(double[][] matrizVentanasDeTiempo) {
        this.matrizVentanasDeTiempo = matrizVentanasDeTiempo;
    }

    public double[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    public void setMatrizDistancias(double[][] matrizDistancias) {
        this.matrizDistancias = matrizDistancias;
    }

    public String imprimirArreglo() {

        String arreglo = "Arreglo Tiempo de Servicio: \n";
        //System.out.println("Arreglo Tiempo de Servicio:");

        for (int i = 0; i < arregloTiempoDeServicio.length; i++) {
            //System.out.print(arregloTiempoDeServicio[i] + " ");
            arreglo += arregloTiempoDeServicio[i] + "  ";
        }

        arreglo += "\n\n";
        //System.out.println();

        return arreglo;
    }

    public String imprimirMatriz(String nombre, double[][] matriz, int filas, int columnas) {

        String stringmatriz = "Matriz " + nombre + ": \n";
        //System.out.println("Imprimiendo matriz " + nombre);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                //System.out.print(matriz[i][j] + " ");

                stringmatriz += String.format("%12s", matriz[i][j]);
                //stringmatriz += matriz[i][j] + "       ";
            }
            //System.out.println();
            stringmatriz += "\n";
        }

        stringmatriz += "\n";

        return stringmatriz;
    }
}
