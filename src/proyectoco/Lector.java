package proyectoco;

import java.io.BufferedReader;
import java.io.FileReader;

public class Lector {

    private String archivo;

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public void reiniciarTodo() {

        archivo = null;
    }

    public void validarEntrada() {

        String s;
        String[] valores = new String[3000];
        int contador = 0;

        try {

            FileReader fr = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(fr);

            while ((s = bf.readLine()) != null) {

                //System.out.println("valores dentro del archivo: " + s);
                valores[contador] = s;

                contador++;
            }//fin while

            System.out.println("--> PRUEBA: " + valores[2342]);


            int indice = 0;
            String valor1 = "";
            String valor2 = "";

            for (int i = 0; i < 2342; i++) {

                valor1 = valores[i];
                valor2 = valores[i+1];
                
                if(valor1.equals(valor2)){
                    System.out.println("Valor repetido: "  + valor1);
                }
                
                indice++;
            }



        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
        }
    }//fin método validar entrada   
}
