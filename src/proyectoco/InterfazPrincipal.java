package proyectoco;

import javax.swing.JOptionPane;
import lpsolve.*;

public class InterfazPrincipal extends javax.swing.JFrame {

    Archivo archivo;
    Lector lector;
    TSM tsm;

    public InterfazPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        archivo = new Archivo();
        lector = new Lector();
        //borrar esto después
        /*jTextFieldRutaArchivo.setText("C:\\Users\\Juan Olaya O\\Documents\\GitHub\\proyectoCO\\prueba1.txt");
        //jTextFieldRutaArchivo.setText("/home/juan/GitProjects/proyectoCO/prueba2.txt");
        //jTextFieldRutaArchivo.setText("/home/john/Escritorio/proyectoCO/prueba1.txt");
        lector.setArchivo(jTextFieldRutaArchivo.getText());
        tsm = lector.extraerInformacionTSM();

        String arreglo = tsm.imprimirArreglo();
        String matrizVentaTiempo = tsm.imprimirMatriz("Ventanas de Tiempo", tsm.getMatrizVentanasDeTiempo(), tsm.getCantLugares(), 2);
        String matrizDistancias = tsm.imprimirMatriz("Distancias", tsm.getMatrizDistancias(), tsm.getCantLugares(), tsm.getCantLugares());

        jTextAreaEntrada.setText(arreglo + matrizVentaTiempo + matrizDistancias);
        // Borrar hasta aquí*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaEntrada = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSolucion = new javax.swing.JTextArea();
        botonSolucionar = new javax.swing.JButton();
        jTextFieldRutaArchivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemSeleccionarArchivo = new javax.swing.JMenuItem();
        jMenuItemRestarurar = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaEntrada.setColumns(20);
        jTextAreaEntrada.setRows(5);
        jScrollPane1.setViewportView(jTextAreaEntrada);

        jTextAreaSolucion.setColumns(20);
        jTextAreaSolucion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSolucion);

        botonSolucionar.setText("Solucionar");
        botonSolucionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSolucionarActionPerformed(evt);
            }
        });

        jTextFieldRutaArchivo.setEnabled(false);

        jLabel1.setText("Ruta del Archivo:");

        jLabel2.setText("Archivo de Entrada");

        jLabel3.setText("Solución");

        jMenu1.setText("Archivo");

        jMenuItemSeleccionarArchivo.setText("Seleccionar Archivo");
        jMenuItemSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSeleccionarArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSeleccionarArchivo);

        jMenuItemRestarurar.setText("restaurar");
        jMenuItemRestarurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRestarurarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemRestarurar);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRutaArchivo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(botonSolucionar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                .addGap(194, 194, 194)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addGap(285, 285, 285))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRutaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonSolucionar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeleccionarArchivoActionPerformed

        String nombreArchivo = archivo.obtenerNombreArchivo();

        if (!nombreArchivo.equals("")) {
            jTextFieldRutaArchivo.setText(nombreArchivo);

            lector.setArchivo(jTextFieldRutaArchivo.getText());
            tsm = lector.extraerInformacionTSM();

            String arreglo = tsm.imprimirArreglo();
            String matrizVentaTiempo = tsm.imprimirMatriz("Ventanas de Tiempo", tsm.getMatrizVentanasDeTiempo(), tsm.getCantLugares(), 2);
            String matrizDistancias = tsm.imprimirMatriz("Distancias", tsm.getMatrizDistancias(), tsm.getCantLugares(), tsm.getCantLugares());

            //para Mostrar en jTextArea
            jTextAreaEntrada.setText(arreglo + matrizVentaTiempo + matrizDistancias);

        }
    }//GEN-LAST:event_jMenuItemSeleccionarArchivoActionPerformed

    private void botonSolucionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSolucionarActionPerformed

        if (!jTextFieldRutaArchivo.getText().equals("")) {

            //aquí va la solución que propongamos
            LpSolveCustom lp = new LpSolveCustom(tsm);
            lp.imprimir(lp.lpFormatContructor());
            lp.escribirArchivo(lp.lpFormatContructor());
            lp.ejecutarArchivo();

        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede generar una salida sin haber escogido un archivo");
        }
    }//GEN-LAST:event_botonSolucionarActionPerformed

    private void jMenuItemRestarurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRestarurarActionPerformed

        lector.reiniciarTodo();
        jTextAreaEntrada.setText("");
        jTextAreaSolucion.setText("");
        jTextFieldRutaArchivo.setText("");
    }//GEN-LAST:event_jMenuItemRestarurarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        try {
//
//            // Create a problem with 4 variables and 0 constraints
//            LpSolve solver = LpSolve.makeLp(0, 4);
//
//            // add constraints
//            solver.strAddConstraint("3 2 2 1", LpSolve.LE, 4);
//
//            solver.strAddConstraint("0 4 3 1", LpSolve.GE, 3);
//
//            // set objective function
//            solver.strSetObjFn("2 3 -2 3");
//
//            // solve the problem
//            solver.solve();
//
//            // print solution
//            System.out.println("Value of objective function: " + solver.getObjective());
//            double[] var = solver.getPtrVariables();
//            for (int i = 0; i < var.length; i++) {
//                System.out.println("Value of var[" + i + "] = " + var[i]);
//            }
//            // delete the problem and free memory
//            solver.deleteLp();
//
//        } catch (LpSolveException e) {
//        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSolucionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemRestarurar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSeleccionarArchivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaEntrada;
    private javax.swing.JTextArea jTextAreaSolucion;
    private javax.swing.JTextField jTextFieldRutaArchivo;
    // End of variables declaration//GEN-END:variables
}
