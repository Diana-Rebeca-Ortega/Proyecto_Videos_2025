package Vista.Catalogo;

import Modelo.Pelicula;
import javax.swing.JOptionPane;
import Controlador.PeliculaDAO;

public class ModificacionesPelicula extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModificacionesPelicula.class.getName());
    private Pelicula peliculaActual; 
    private final int idPelicula;    
    private boolean datosGuardados = false; 
  
   public ModificacionesPelicula(java.awt.Frame parent, boolean modal, int idPelicula) {
        super(parent, modal);
        initComponents();
        this.idPelicula = idPelicula;
        cargarDatosPelicula(this.idPelicula);
        this.setLocationRelativeTo(parent); 
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 250));         
    }
    public boolean isDatosGuardados() {
        return datosGuardados;
    }
    public final void cargarDatosPelicula(int idPelicula) {
     PeliculaDAO dao = new PeliculaDAO();
        try {
            this.peliculaActual = dao.obtenerPeliculaPorId(idPelicula);
            if (peliculaActual != null) {
                caja_titulo.setText(peliculaActual.getTitulo());
                caja_director.setText(peliculaActual.getDirector());
                combo_categoria.setSelectedItem(peliculaActual.getCategoria());                
                caja_precioAlquiler.setText(String.valueOf(peliculaActual.getPrecioAlquiler())); 
                caja_costoAdquisicion.setText(String.valueOf(peliculaActual.getCosteAdquisicion()));
                caja_stockTotal.setText(String.valueOf(peliculaActual.getStockTotal()));                
            } else {
                JOptionPane.showMessageDialog(this, "Película con ID " + idPelicula + " no encontrada.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
                this.dispose(); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la película: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, "Error al cargar datos de película ID: " + idPelicula, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_cambiarPelicula = new javax.swing.JButton();
        caja_director = new javax.swing.JTextField();
        btn_cancelarRegistroCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        caja_precioAlquiler = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        caja_costoAdquisicion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        caja_stockTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        caja_titulo = new javax.swing.JTextField();
        combo_categoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel13.setText("Alquiler (Diario)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("MODIFICAR PELICULA");

        jLabel14.setText("Stock Total");

        jLabel3.setText("Director");

        btn_cambiarPelicula.setBackground(new java.awt.Color(153, 255, 153));
        btn_cambiarPelicula.setText("GUARDAR");
        btn_cambiarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarPeliculaActionPerformed(evt);
            }
        });

        btn_cancelarRegistroCliente.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelarRegistroCliente.setText("CANCELAR");
        btn_cancelarRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarRegistroClienteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Datos Generales");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Finanzas");

        caja_precioAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_precioAlquilerActionPerformed(evt);
            }
        });

        jLabel6.setText("Precio para venta al publico");

        caja_costoAdquisicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_costoAdquisicionActionPerformed(evt);
            }
        });

        jLabel1.setText("Titulo");

        jLabel2.setText("Categoria");

        caja_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_tituloActionPerformed(evt);
            }
        });

        combo_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Acción", "Aventura", "Ciencia Ficción", "Comedia", "Drama", "Fantasía", "Terror", "Musical", "Documental", "Otro" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cambiarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caja_precioAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caja_stockTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(caja_titulo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(caja_director, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(caja_costoAdquisicion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(caja_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caja_director, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caja_precioAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(caja_costoAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(9, 9, 9)
                .addComponent(caja_stockTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cambiarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cambiarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarPeliculaActionPerformed
     String tituloStr = caja_titulo.getText().trim();
        String directorStr = caja_director.getText().trim();
        String precioAlquilerStr = caja_precioAlquiler.getText().trim();
        String costoAdquisicionStr = caja_costoAdquisicion.getText().trim();
        String stockTotalStr = caja_stockTotal.getText().trim();
        String categoriaStr = combo_categoria.getSelectedItem() != null ? combo_categoria.getSelectedItem().toString() : "";

        // 1. Validar campos obligatorios de texto/categoría
        if (tituloStr.isEmpty() || directorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Título y el Director son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (categoriaStr.equals("Seleccione una opción") || categoriaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Categoría válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        // 2. Validar campos obligatorios numéricos
        if (precioAlquilerStr.isEmpty() || costoAdquisicionStr.isEmpty() || stockTotalStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos de Alquiler, Venta y Stock son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double precioAlquiler = Double.parseDouble(precioAlquilerStr);
            double costoAdquisicion = Double.parseDouble(costoAdquisicionStr);
            int stockTotal = Integer.parseInt(stockTotalStr);            
            if (precioAlquiler < 0 || costoAdquisicion < 0 || stockTotal < 0) {
                JOptionPane.showMessageDialog(this, "Los precios y el stock deben ser números no negativos.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Pelicula peliculaModificada = new Pelicula();
            peliculaModificada.setIdPelicula(this.idPelicula);
            peliculaModificada.setTitulo(tituloStr);
            peliculaModificada.setCategoria(categoriaStr);
            peliculaModificada.setDirector(directorStr);
            peliculaModificada.setPrecioAlquiler(precioAlquiler);
            peliculaModificada.setCosteAdquisicion(costoAdquisicion);
            peliculaModificada.setStockTotal(stockTotal);
            
            PeliculaDAO dao = new PeliculaDAO();
            if (dao.modificarPelicula(peliculaModificada)) { 
                datosGuardados = true;
                JOptionPane.showMessageDialog(this, "Película modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar la película. La base de datos no fue actualizada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos de precios y stock deben ser números válidos. Use punto (.) para decimales si aplica.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error durante la modificación: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, "Error al modificar la película.", e);
        }
    }//GEN-LAST:event_btn_cambiarPeliculaActionPerformed

    private void btn_cancelarRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarRegistroClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarRegistroClienteActionPerformed

    private void caja_precioAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_precioAlquilerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_precioAlquilerActionPerformed

    private void caja_costoAdquisicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_costoAdquisicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_costoAdquisicionActionPerformed

    private void caja_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_tituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_tituloActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarPelicula;
    private javax.swing.JButton btn_cancelarRegistroCliente;
    private javax.swing.JTextField caja_costoAdquisicion;
    private javax.swing.JTextField caja_director;
    private javax.swing.JTextField caja_precioAlquiler;
    private javax.swing.JTextField caja_stockTotal;
    private javax.swing.JTextField caja_titulo;
    private javax.swing.JComboBox<String> combo_categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
