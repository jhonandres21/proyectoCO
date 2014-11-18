package proyectoco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lector {

    private String archivo;

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void reiniciarTodo() {
        archivo = null;
    }

    //En este método se devolverá la entrada para mostrar en el JTextArea, 
    //con el fin de mostrar e identificar textualmente cada uno de las partes
    //que componen la entrada del archivo
    public TSM extraerInformacionTSM() {
        TSM tsm = new TSM();
        int cantLugares = 0;
        double[] arregloTiempoDeServicio = null;
        double[][] matrizVentanasDeTiempo = null;
        double[][] matrizDistancias = null;

        int contador = 0;

        BufferedReader br = null;
        try {
            FileReader archivoTSM = new FileReader(archivo);
            br = new BufferedReader(archivoTSM);
            String line;
            while ((line = br.readLine()) != null) {
                if (contador == 0) {
                    cantLugares = Integer.parseInt(line);
                    
                    arregloTiempoDeServicio = new double[cantLugares];
                    matrizVentanasDeTiempo = new double[cantLugares][2];
                    matrizDistancias = new double[cantLugares][cantLugares];
                    contador++;
                } else {
                    if (contador <= cantLugares) {
                        String[] split;
                        split = line.split(" ");
                        arregloTiempoDeServicio[contador - 1] = Double.parseDouble(split[1]);
                        matrizVentanasDeTiempo[contador - 1][0] = Double.parseDouble(split[2]);
                        matrizVentanasDeTiempo[contador - 1][1] = Double.parseDouble(split[3]);

                        contador++;
                    } else {
                        String[] split;
                        split = line.split(" ");
                        //Escrbir diagonal como 0's
                        for (int i = 0; i < cantLugares; i++) {
                            matrizDistancias[i][i] = 0;
                        }
                        int lugar1 = Integer.parseInt(split[0]);
                        int lugar2 = Integer.parseInt(split[1]);

                        matrizDistancias[lugar1 - 1][lugar2 - 1] = Double.parseDouble(split[2]);

                    }
                }
            }
            tsm.setCantLugares(cantLugares);
            tsm.setTiempoDeServicio(arregloTiempoDeServicio);
            tsm.setMatrizVentanasDeTiempo(matrizVentanasDeTiempo);
            tsm.setMatrizDistancias(matrizDistancias);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tsm;
    }
}
