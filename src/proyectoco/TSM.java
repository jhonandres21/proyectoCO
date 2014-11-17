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

    public void imprimirArreglo() {
        System.out.println("Arreglo Tiempo de Servicio:");
        for (int i = 0; i < arregloTiempoDeServicio.length; i++) {
            System.out.print(arregloTiempoDeServicio[i] + " ");
        }
    }

    public void imprimirMatriz(String nombre, double[][] matriz, int filas, int columnas) {
        System.out.println("Imprimiendo matriz " + nombre);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
