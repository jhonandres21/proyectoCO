package proyectoco;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Archivo extends JFrame {

    public Archivo() {
    }

    public String obtenerNombreArchivo() {

        String rutaAbsolutaArchivo = "";

        try {

            File nombre = obtenerArchivo();
            if (nombre.exists()) {
                rutaAbsolutaArchivo = nombre.getAbsolutePath();
            }

        } catch (Exception e) {
        }

        return rutaAbsolutaArchivo;

    } // fin del método obtenerRuta

    private File obtenerArchivo() {

        JFileChooser selectorArchivos = new JFileChooser();

        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("txt", "txt");

        selectorArchivos.setFileFilter(filtroImagen);
        selectorArchivos.setAcceptAllFileFilterUsed(false);


        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivos.setDialogTitle("Escoger Archivo");

        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            dispose();
        }

        File nombreArchivo = null;

        if (resultado == JFileChooser.APPROVE_OPTION) {

            nombreArchivo = selectorArchivos.getSelectedFile();

            if ((nombreArchivo == null) || (nombreArchivo.getName().equals(""))) {
                JOptionPane.showMessageDialog(this, "No has escogido ningún Archivo!", "", JOptionPane.ERROR_MESSAGE);
                dispose();
            }

            return nombreArchivo;
        }
        return nombreArchivo;

    } // fin del método obtenerArchivo
} // fin de la clase 

