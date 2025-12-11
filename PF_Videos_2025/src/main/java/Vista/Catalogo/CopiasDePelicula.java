
package Vista.Catalogo;

import Controlador.CopiaPeliculaDAO;
import Controlador.PeliculaDAO;
import Modelo.CopiaPelicula;
import Modelo.Pelicula;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CopiasDePelicula extends javax.swing.JDialog {
    private Pelicula peliculaActual;
 
    private final int idPelicula;  
    private int idSucursalActual;
    private boolean datosGuardados = false; 
    CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();
    public CopiasDePelicula(java.awt.Frame parent, boolean modal, int idPelicula, int idSucursalActual) {
        super(parent, modal);
        initComponents();
        this.idPelicula = idPelicula;
        this.idSucursalActual = idSucursalActual;
        this.setLocationRelativeTo(null);
        cargarDatosCopiaPelicula(this.idPelicula, this.idSucursalActual);
        aplicarEstiloTabla();
    }
 private void aplicarEstiloTabla() {
    
    Color COLOR_PRIMARIO_OSCURO = new Color(0, 51, 102); // Azul oscuro (como su menú lateral)
    Color COLOR_ENCABEZADO_FONDO = new Color(240, 240, 240); // Gris muy claro para el fondo
    Color COLOR_SELECCION_FILA = new Color(210, 230, 255); // Azul claro suave
    Color COLOR_TEXTO_ENCABEZADO = Color.BLACK;
    JTableHeader header = tabla_copias_peliculas.getTableHeader();
    header.setFont(new Font("Segoe UI", Font.BOLD, 13)); // Fuente en negrita
    header.setBackground(COLOR_ENCABEZADO_FONDO);
    header.setForeground(COLOR_TEXTO_ENCABEZADO);
    header.setBorder(null);
    tabla_copias_peliculas.setBackground(Color.WHITE); 
    tabla_copias_peliculas.setSelectionBackground(COLOR_SELECCION_FILA); 
    tabla_copias_peliculas.setRowHeight(25); 
    tabla_copias_peliculas.setShowGrid(false); 
    tabla_copias_peliculas.setIntercellSpacing(new java.awt.Dimension(0, 0)); 
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
     for (int i = 0; i < tabla_copias_peliculas.getColumnCount(); i++) {        
        if (i != 1 && i != 2 && i != 3) { 
             tabla_copias_peliculas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
        
    public final void cargarDatosCopiaPelicula(int idPelicula, int idSucursalActual) {
        PeliculaDAO peliculaDao = new PeliculaDAO();
        
        try {
            // 1. Cargar la información general de la Película (Catálogo)
            this.peliculaActual = peliculaDao.obtenerPeliculaPorId(idPelicula);

            if (peliculaActual != null) {
                // 2. Llenar las etiquetas del formulario con datos del Catálogo
                txt_ID_Pelicula.setText(String.valueOf(peliculaActual.getIdPelicula()));
                txt_titulo.setText(peliculaActual.getTitulo());
                txt_director.setText(peliculaActual.getDirector());
                // Muestra la sucursal actual (usando el parámetro)
                txt_sucursal.setText(String.valueOf(idSucursalActual)); 
                int copiasDisponibles = copiaDao.contarCopiasDisponibles(idPelicula);
                txt_copiastotales.setText(String.valueOf(copiasDisponibles));
                // 3. Cargar las copias individuales de esa Película en la tabla
                cargarCopiasEnTabla(idPelicula, idSucursalActual);
                
            } else {
                JOptionPane.showMessageDialog(this, "Película con ID " + idPelicula + " no encontrada.", "Error de Carga", JOptionPane.ERROR_MESSAGE);
                this.dispose(); // Cierra el formulario si no encuentra la película
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la película: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            // Manejo de log si es necesario
        }
    }
    private void cargarCopiasEnTabla(int idPelicula, int idSucursal) {
        CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();
        
        // Usamos el método que desarrollamos previamente para obtener solo las copias 
        // relevantes para esta Película y esta Sucursal.
        List<CopiaPelicula> copias = copiaDao.obtenerCopiasPorPeliculaYSucursal(idPelicula, idSucursal); 
        
        // 1. Definir el modelo de la tabla
        String[] nombresColumnas = {"ID_Copia_Pelicula", "ID_Pelicula", "ID_Sucursal", "Estado"};
        DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0); 
        
        // 2. Llenar el modelo con los datos de las copias
        for (CopiaPelicula copia : copias) {
            model.addRow(new Object[]{
                copia.getIdCopiaPelicula(),
                copia.getIdPelicula(),
                copia.getIdSucursal(),
                copia.getEstado()
            });
        }
        
        // 3. Asignar el modelo a la JTable del formulario
        tabla_copias_peliculas.setModel(model);
        
        // Opcional: Ajustar el ancho de las columnas
        if (tabla_copias_peliculas.getColumnModel().getColumnCount() > 0) {
            tabla_copias_peliculas.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla_copias_peliculas.getColumnModel().getColumn(3).setPreferredWidth(120);
        }
    }
      public boolean isDatosGuardados() {
        return datosGuardados;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_ID_Pelicula = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_director = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_sucursal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_copias_peliculas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txt_copiastotales = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("COPIAS DE LA PELICULA");

        jLabel1.setText("ID Pelicula:");

        jLabel2.setText("Titulo:");

        txt_ID_Pelicula.setText("...");

        txt_titulo.setText("...");

        jLabel3.setText("Director:");

        txt_director.setText("...");

        jLabel5.setText("ID_Sucursal");

        txt_sucursal.setText("...");

        tabla_copias_peliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID_Copia_Pelicula", "ID_Pelicula", "ID_Sucursal", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tabla_copias_peliculas);

        jLabel4.setText("COPIAS TOTALES DISPONIBLES:");

        txt_copiastotales.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ID_Pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_copiastotales, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_director, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ID_Pelicula)
                    .addComponent(jLabel4)
                    .addComponent(txt_copiastotales))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_titulo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_director))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_sucursal))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_copias_peliculas;
    private javax.swing.JLabel txt_ID_Pelicula;
    private javax.swing.JLabel txt_copiastotales;
    private javax.swing.JLabel txt_director;
    private javax.swing.JLabel txt_sucursal;
    private javax.swing.JLabel txt_titulo;
    // End of variables declaration//GEN-END:variables
}
