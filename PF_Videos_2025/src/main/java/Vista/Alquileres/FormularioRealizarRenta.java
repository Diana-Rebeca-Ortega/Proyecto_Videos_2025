package Vista.Alquileres;
import Controlador.AlquilerDAO;
import Controlador.ClienteDAO;
import Controlador.CopiaPeliculaDAO;
import Controlador.PeliculaDAO;
import Modelo.Alquiler;
import Modelo.Cliente;
import Modelo.Pelicula;
import Vista.Filtros.BuscadorPredictivo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
public class FormularioRealizarRenta extends javax.swing.JDialog {
    
private final AlquilerDAO alquilerDAO = new AlquilerDAO();
private final Alquiler nuevoAlquiler = new Alquiler();
private final int idSucursalActual = 0;

private int idCopiaSeleccionada = -1; 
private double alquilerDiarioCargado = 0.0;
private boolean datosGuardados;

      public FormularioRealizarRenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarConfiguracionUI();
    }
      
      private void inicializarConfiguracionUI() {
        configurarListenersExtras();
        BuscadorPredictivo.registrarAutocompletado(cajaBuscadorPelicula);
        dateDevolucion.setEnabled(false);
        mostrarFechaActual();
        btnRentar.setEnabled(false);
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 250));
        dateDevolucion.setMinSelectableDate(new java.util.Date());
    }
    public boolean isDatosGuardados() {    return datosGuardados; }
    public Alquiler getAlquiler() { return nuevoAlquiler; }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cajaBuscadorPelicula = new javax.swing.JTextField();
        btn_buscarPelicula = new javax.swing.JButton();
        txt_TituloPelicula = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_Categoria = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_Director = new javax.swing.JLabel();
        txt_AlquilerDiario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_IDpelicula = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_IDCopia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cajaBuscadorCliente = new javax.swing.JTextField();
        btn_buscadorCliente = new javax.swing.JButton();
        txt_Apellido1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_NombreCliente = new javax.swing.JLabel();
        txt_Apellido2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_fechaRenta = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dateDevolucion = new com.toedter.calendar.JDateChooser();
        btnRentar = new javax.swing.JButton();
        btn_cancelarRegistroCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("REGISTRAR NUEVA RENTA DE PELICULA");

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Escanee el Codigo de la Copia de Pelicula o Escriba su ID ");

        cajaBuscadorPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaBuscadorPeliculaActionPerformed(evt);
            }
        });

        btn_buscarPelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/busqueda.png"))); // NOI18N
        btn_buscarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarPeliculaActionPerformed(evt);
            }
        });

        txt_TituloPelicula.setText("...");

        jLabel7.setText("Categoria");

        txt_Categoria.setText("...");

        jLabel13.setText("Alquiler (Diario)");

        jLabel9.setText("Director");

        txt_Director.setText("...");

        txt_AlquilerDiario.setText("...");

        jLabel3.setText("Titulo de la Pelicula");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Datos de Pelicula");

        jLabel5.setText("ID_Pelicula");

        txt_IDpelicula.setText("...");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/codigo-qr.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/escaneo-de-codigo-de-barras.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("ID Copia Pelicula");

        txt_IDCopia.setText("...");
        txt_IDCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDCopiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Director, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_IDCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_IDpelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_TituloPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_AlquilerDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cajaBuscadorPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_buscarPelicula))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaBuscadorPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_buscarPelicula)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_IDpelicula)
                    .addComponent(txt_Categoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TituloPelicula)
                    .addComponent(txt_AlquilerDiario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Director)
                    .addComponent(txt_IDCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel15.setText("Fecha de Registro");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("DD/MM/AAAA");

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel21.setText("ID de Cliente");

        cajaBuscadorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaBuscadorClienteActionPerformed(evt);
            }
        });

        btn_buscadorCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/busqueda.png"))); // NOI18N
        btn_buscadorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscadorClienteActionPerformed(evt);
            }
        });

        txt_Apellido1.setText("...");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Datos de Cliente");

        jLabel18.setText("Apellido2");

        jLabel19.setText("Nombre(s)");

        jLabel20.setText("Apellido 1");

        txt_NombreCliente.setText("...");

        txt_Apellido2.setText("...");

        jLabel22.setBackground(new java.awt.Color(0, 0, 204));
        jLabel22.setForeground(new java.awt.Color(0, 0, 255));
        jLabel22.setText("Realizar busqueda por Nombre del Cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cajaBuscadorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscadorCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addComponent(jLabel19)
                    .addComponent(txt_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscadorCliente)
                    .addComponent(cajaBuscadorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NombreCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Apellido1)
                    .addComponent(txt_Apellido2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Costo Final de Renta");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setText("$00.00");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Información de Renta");

        txt_fechaRenta.setText("...");

        jLabel26.setText("Fecha de Renta");

        jLabel28.setText("Fecha de Devolución/Vencimiento");

        dateDevolucion.setToolTipText("Para habilitar el calendario debe seleccionar Pelicula y Cliente");
        dateDevolucion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDevolucionPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(txt_fechaRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(dateDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28)
                                .addGap(16, 16, 16)))
                        .addComponent(txt_fechaRenta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dateDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnRentar.setBackground(new java.awt.Color(153, 255, 153));
        btnRentar.setText("RENTAR");
        btnRentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentarActionPerformed(evt);
            }
        });

        btn_cancelarRegistroCliente.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelarRegistroCliente.setText("CANCELAR");
        btn_cancelarRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarRegistroClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRentar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)))
                        .addGap(207, 207, 207))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRentar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaBuscadorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaBuscadorClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaBuscadorClienteActionPerformed

    private void btnRentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentarActionPerformed
   int idCopiaRentada = extraerIdCopiaValido();
        if (idCopiaRentada == -1) return;

        int idPeliculaMaestra = obtenerIdPeliculaMaestra(idCopiaRentada);
        if (idPeliculaMaestra == -1) return;

        if (!cargarDatosTransaccion(idPeliculaMaestra, idCopiaRentada)) return;
        if (!procesarYCalcularFechas()) return;

        ejecutarRegistroAlquiler();
    }//GEN-LAST:event_btnRentarActionPerformed

    private void btn_cancelarRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarRegistroClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarRegistroClienteActionPerformed

    private void btn_buscadorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscadorClienteActionPerformed
    verificarEstadoBotonRentar();
        String idTexto = cajaBuscadorCliente.getText().trim();
        
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el ID del cliente.", "Error de Búsqueda", JOptionPane.WARNING_MESSAGE);
            limpiarDatosCliente();
            return;
        }
        
        buscarYAsignarCliente(idTexto);
    }//GEN-LAST:event_btn_buscadorClienteActionPerformed

    private void dateDevolucionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDevolucionPropertyChange
       if ("date".equals(evt.getPropertyName())) {
            verificarEstadoBotonRentar();
            calcularCostoFinal();
        }
    }//GEN-LAST:event_dateDevolucionPropertyChange

    private void btn_buscarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarPeliculaActionPerformed
 String textoBusqueda = cajaBuscadorPelicula.getText().trim();
        
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID de copia o el título de la película.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (textoBusqueda.matches("\\d+")) { 
            procesarBusquedaPorIdCopia(Integer.parseInt(textoBusqueda));
        } else { 
            procesarBusquedaPorTitulo(textoBusqueda);
        }
    }//GEN-LAST:event_btn_buscarPeliculaActionPerformed

    private void cajaBuscadorPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaBuscadorPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaBuscadorPeliculaActionPerformed

    private void txt_IDCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDCopiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDCopiaActionPerformed

    // MÉTODOS DE LOGICA EXTRACTADA (CLEAN CODE)
  

    private int extraerIdCopiaValido() {
        try {
            return Integer.parseInt(txt_IDCopia.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar o buscar una película para obtener un ID de Copia válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private int obtenerIdPeliculaMaestra(int idCopiaRentada) {
        CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();
        int idMaestra = copiaDao.obtenerIdPeliculaMaestraPorCopia(idCopiaRentada);
        if (idMaestra == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar la película maestra para la copia proporcionada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return idMaestra;
    }

    private boolean cargarDatosTransaccion(int idPelicula, int idCopia) {
        try {
            int idCliente = Integer.parseInt(cajaBuscadorCliente.getText().trim());
            double costoDiario = Double.parseDouble(txt_AlquilerDiario.getText().trim());
            
            nuevoAlquiler.setIdPelicula(idPelicula);
            nuevoAlquiler.setIdCopia(idCopia);
            nuevoAlquiler.setIdCliente(idCliente);
            nuevoAlquiler.setCostoDiario(costoDiario);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de Cliente o Costo Diario inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean procesarYCalcularFechas() {
        java.util.Date fechaDevolucionUtil = dateDevolucion.getDate();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        try {
            java.util.Date fechaRentaUtil = dateFormat.parse(txt_fechaRenta.getText());
            java.sql.Date fechaRentaSQL = new java.sql.Date(fechaRentaUtil.getTime());
            java.sql.Date fechaDevolucionSQL = new java.sql.Date(fechaDevolucionUtil.getTime());

            long diffDays = alquilerDAO.calcularDiasRenta(fechaRentaSQL, fechaDevolucionSQL);
            double tarifaTotal = nuevoAlquiler.getCostoDiario() * (double) diffDays;
            
            jLabel30.setText(String.format("$%.2f", tarifaTotal));

            nuevoAlquiler.setFechaAlquiler(fechaRentaSQL);
            nuevoAlquiler.setFechaDevolucion(fechaDevolucionSQL);
            nuevoAlquiler.setEstado("RENTADO");
            nuevoAlquiler.setCostoFinal(tarifaTotal);
            return true;
        } catch (java.text.ParseException ex) {
            System.getLogger(FormularioRealizarRenta.class.getName()).log(System.Logger.Level.ERROR, "Error al parsear la fecha de renta", ex);
            return false;
        }
    }

    private void ejecutarRegistroAlquiler() {
        this.datosGuardados = true;
        this.dispose();
    }

    private void procesarBusquedaPorIdCopia(int idCopiaRentada) {
        CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();
        PeliculaDAO peliculaDao = new PeliculaDAO();
        int idPeliculaMaestra = copiaDao.obtenerIdPeliculaMaestraPorCopia(idCopiaRentada);

        if (idPeliculaMaestra == -1) {
            JOptionPane.showMessageDialog(this, "La copia con ID " + idCopiaRentada + " no está registrada o no está disponible.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
            limpiarDatosPelicula();
            return;
        }

        Pelicula pelicula = peliculaDao.obtenerPeliculaPorId(idPeliculaMaestra);
        if (pelicula != null) {
            desplegarDatosPeliculaEnUI(pelicula, idCopiaRentada);
        }
    }

    private void procesarBusquedaPorTitulo(String titulo) {
        PeliculaDAO peliculaDao = new PeliculaDAO();
        CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();
        List<Pelicula> resultados = peliculaDao.buscarPeliculasDinamico(titulo, "TITULO");

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron películas que coincidan con: " + titulo, "Sin Coincidencias", JOptionPane.INFORMATION_MESSAGE);
            limpiarDatosPelicula();
            return;
        } 
        
        if (resultados.size() > 1) {
            JOptionPane.showMessageDialog(this, "Se encontraron múltiples coincidencias (" + resultados.size() + "). Intente escribir un título más específico.", "Múltiples Coincidencias", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Pelicula pelicula = resultados.get(0);
        int idCopiaLibre = copiaDao.obtenerIdCopiaDisponible(pelicula.getIdPelicula(), this.idSucursalActual);
        
        if (idCopiaLibre != -1) {
            desplegarDatosPeliculaEnUI(pelicula, idCopiaLibre);
        } else {
            JOptionPane.showMessageDialog(this, "La película '" + pelicula.getTitulo() + "' no tiene copias disponibles en esta sucursal.", "Agotado", JOptionPane.WARNING_MESSAGE);
            txt_IDCopia.setText("NO DISPONIBLE");
            limpiarDatosPelicula();
        }
    }

    private void desplegarDatosPeliculaEnUI(Pelicula pelicula, int idCopia) {
        txt_IDpelicula.setText(String.valueOf(pelicula.getIdPelicula()));
        txt_TituloPelicula.setText(pelicula.getTitulo());
        txt_Categoria.setText(pelicula.getCategoria());
        txt_Director.setText(pelicula.getDirector());
        txt_AlquilerDiario.setText(String.valueOf(pelicula.getPrecioAlquiler()));
        txt_IDCopia.setText(String.valueOf(idCopia));
        this.idCopiaSeleccionada = idCopia;
        verificarEstadoBotonRentar();
    }

    private void buscarYAsignarCliente(String idTexto) {
        try {
            int idCliente = Integer.parseInt(idTexto);
            ClienteDAO clienteDAO = new ClienteDAO(); 
            Cliente clienteEncontrado = clienteDAO.obtenerClientePorId(idCliente);
            
            if (clienteEncontrado != null) {
                cargarDatosCliente(clienteEncontrado);
                JOptionPane.showMessageDialog(this, "Cliente cargado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con el ID: " + idCliente, "Cliente No Encontrado", JOptionPane.ERROR_MESSAGE);
                limpiarDatosCliente();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de Cliente debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            limpiarDatosCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar en la base de datos: " + e.getMessage(), "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            limpiarDatosCliente();
        }
    }


    // MÉTODOS EXTRA 
 

    private void configurarListenersExtras() {
        cajaBuscadorPelicula.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                verificarEstadoBotonRentar();
            }
        });
    }

    private void limpiarDatosPelicula() {
        txt_TituloPelicula.setText("...");
        txt_Director.setText("...");
        txt_Categoria.setText("...");
        txt_AlquilerDiario.setText("...");
        txt_IDpelicula.setText("...");   
        this.idCopiaSeleccionada = -1;
        verificarHabilitacionFecha();
    }

    private void limpiarDatosCliente() {
        txt_NombreCliente.setText("...");
        txt_Apellido1.setText("...");
        txt_Apellido2.setText("...");
        verificarHabilitacionFecha();
    }

    private void cargarDatosCliente(Cliente c) {
        txt_NombreCliente.setText(c.getNombre());
        txt_Apellido1.setText(c.getApellido1()); 
        txt_Apellido2.setText(c.getApellido2()); 
        verificarHabilitacionFecha();
    }

    private void verificarHabilitacionFecha() {
        String tituloPelicula = txt_TituloPelicula.getText().trim();
        String nombreCliente = txt_NombreCliente.getText().trim();
        final String VALOR_DEFECTO = "...";
        
        boolean peliculaSeleccionada = !tituloPelicula.isEmpty() && !tituloPelicula.equals(VALOR_DEFECTO);
        boolean clienteSeleccionado = !nombreCliente.isEmpty() && !nombreCliente.equals(VALOR_DEFECTO);
        
        if (peliculaSeleccionada && clienteSeleccionado) {
            dateDevolucion.setEnabled(true); 
            btnRentar.setEnabled(true); 
        } else {
            dateDevolucion.setEnabled(false); 
            dateDevolucion.setDate(null);
            btnRentar.setEnabled(false); 
            jLabel30.setText("$0.00");
        }
    }

    private void mostrarFechaActual() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaHoy = dateFormat.format(new java.util.Date());
        txt_fechaRenta.setText(fechaHoy);
        jLabel16.setText(fechaHoy);    
    }

    private void mostrarError(final String mensaje) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(FormularioRealizarRenta.this, mensaje, "Error en Transacción", JOptionPane.ERROR_MESSAGE);
            btn_buscarPelicula.setEnabled(true);
            cajaBuscadorPelicula.setEnabled(true);
        });
    }

    private void calcularCostoFinal() {   
        String costoDiarioTexto = txt_AlquilerDiario.getText().trim();
        if (costoDiarioTexto.equals("...")) {
            jLabel30.setText("$0.00");
            return;
        }   
        try {              
            double costoDiario = Double.parseDouble(costoDiarioTexto);
            java.util.Date fechaRenta = new java.util.Date(); 
            java.util.Date fechaDevolucion = dateDevolucion.getDate();     
            
            if (fechaDevolucion == null || fechaDevolucion.before(fechaRenta)) { 
                jLabel30.setText("$0.00");
                return;
            }    
            
            int diferenciaDias = alquilerDAO.calcularDiasRenta(fechaRenta, fechaDevolucion);
            double costoTotal = costoDiario * (double) diferenciaDias;
            jLabel30.setText("$" + String.format("%.2f", costoTotal));
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir costo diario a número: " + e.getMessage());
            jLabel30.setText("ERROR");
        }
    }

    private void verificarEstadoBotonRentar() {
        boolean copiaOK = (this.idCopiaSeleccionada != -1); 
        boolean clienteOK = !cajaBuscadorCliente.getText().trim().isEmpty(); 
        boolean fechaOK = (dateDevolucion.getDate() != null);     
        btnRentar.setEnabled(copiaOK && clienteOK && fechaOK);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRentar;
    private javax.swing.JButton btn_buscadorCliente;
    private javax.swing.JButton btn_buscarPelicula;
    private javax.swing.JButton btn_cancelarRegistroCliente;
    private javax.swing.JTextField cajaBuscadorCliente;
    private javax.swing.JTextField cajaBuscadorPelicula;
    private com.toedter.calendar.JDateChooser dateDevolucion;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel txt_AlquilerDiario;
    private javax.swing.JLabel txt_Apellido1;
    private javax.swing.JLabel txt_Apellido2;
    private javax.swing.JLabel txt_Categoria;
    private javax.swing.JLabel txt_Director;
    private javax.swing.JTextField txt_IDCopia;
    private javax.swing.JLabel txt_IDpelicula;
    private javax.swing.JLabel txt_NombreCliente;
    private javax.swing.JLabel txt_TituloPelicula;
    private javax.swing.JLabel txt_fechaRenta;
    // End of variables declaration//GEN-END:variables

}
