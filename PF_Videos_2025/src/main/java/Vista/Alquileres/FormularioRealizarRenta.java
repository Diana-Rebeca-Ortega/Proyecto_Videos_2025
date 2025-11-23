/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

package Vista.Alquileres;
import Controlador.ClienteDAO;
import Controlador.CopiaPeliculaDAO;
import Controlador.PeliculaDAO;
import Modelo.Alquiler;
import Modelo.Cliente;
import Modelo.Pelicula;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
public class FormularioRealizarRenta extends javax.swing.JDialog {
private Alquiler alquiler;
private boolean datosGuardados;
      public FormularioRealizarRenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dateDevolucion.setEnabled(false);
        mostrarFechaActual();
        dateDevolucion.setMinSelectableDate(new java.util.Date());
    }
     public boolean isDatosGuardados() {
        return datosGuardados;
    }
public Alquiler getAlquiler() {
    return alquiler;
}

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
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_IDpelicula = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_CopiasDisponibles = new javax.swing.JLabel();
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

        jLabel1.setText("ID de Pelicula");

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

        jLabel23.setBackground(new java.awt.Color(0, 0, 204));
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setText("Realizar busqueda por titulo");

        jLabel5.setText("ID_Pelicula");

        txt_IDpelicula.setText("...");

        jLabel2.setText("Copias Disponibles");

        txt_CopiasDisponibles.setText("...");

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
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cajaBuscadorPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscarPelicula)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Director, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_CopiasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jLabel2)
                                    .addComponent(txt_AlquilerDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaBuscadorPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_buscarPelicula))))
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
                    .addComponent(txt_CopiasDisponibles))
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
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txt_Apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(110, Short.MAX_VALUE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_NombreCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Apellido1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Apellido2)))
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

    private void cajaBuscadorPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaBuscadorPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaBuscadorPeliculaActionPerformed

    private void cajaBuscadorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaBuscadorClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaBuscadorClienteActionPerformed

    private void btnRentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentarActionPerformed
// 1. Obtener la Fecha de Devolución
        java.util.Date fechaDevolucionUtil = dateDevolucion.getDate();

        // 2. Validación Inicial: La Fecha de Devolución no puede ser nula.
        if (fechaDevolucionUtil == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar una Fecha de Devolución.", "Error de Datos", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alquiler nuevoAlquiler = new Alquiler();
        CopiaPeliculaDAO copiaDao = new CopiaPeliculaDAO();

        int idPelicula = -1;
        int idCopiaRentada = -1; // Inicializar la variable de la copia
        java.sql.Date fechaRentaSQL = null;
        double costoDiario = 0; // Se moverá la declaración fuera del try para usarla abajo
        java.util.Date fechaRentaUtil = null; // Se moverá la declaración fuera del try para usarla abajo

        try {
            // 3. Obtener y Validar IDs (Pelicula y Cliente)
            idPelicula = Integer.parseInt(cajaBuscadorPelicula.getText());  
            int idSucursal = 0; 

            // 🔑 BÚSQUEDA DE COPIA DISPONIBLE
            idCopiaRentada = copiaDao.obtenerIdCopiaDisponible(idPelicula, idSucursal);

            if (idCopiaRentada == -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No hay copias disponibles para esta película en esta sucursal.", "Error de Stock", javax.swing.JOptionPane.ERROR_MESSAGE);
                return; // Detiene la ejecución si no hay copias
            }

            nuevoAlquiler.setIdPelicula(idPelicula);

            // ID de Cliente
            int idCliente = Integer.parseInt(cajaBuscadorCliente.getText());
            nuevoAlquiler.setIdCliente(idCliente);    

            String costoDiarioStr = txt_AlquilerDiario.getText().trim();
            costoDiario = Double.parseDouble(costoDiarioStr); // Usamos la variable declarada arriba
            nuevoAlquiler.setCostoDiario(costoDiario); // Esto es el costo por día

            // 4. Obtener y Convertir Fecha de Renta (del JLabel)
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fechaRentaStr = txt_fechaRenta.getText();
            fechaRentaUtil = dateFormat.parse(fechaRentaStr); // Usamos la variable declarada arriba  
            // Conversión final a java.sql.Date
            fechaRentaSQL = new java.sql.Date(fechaRentaUtil.getTime());    

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: El ID de Película, Cliente o Costo no es un número válido. Verifique la búsqueda.", "Error de Datos", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;  
        } catch (java.text.ParseException ex) {
            System.err.println("Error al parsear la fecha de renta: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, "Error de Sistema: La fecha de renta no tiene el formato correcto.", "Error Interno", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 5. Convertir la Fecha de Devolución a java.sql.Date
        java.sql.Date fechaDevolucionSQL = new java.sql.Date(fechaDevolucionUtil.getTime());    
        
        // --- 🔑 INICIO DEL CÁLCULO DE LA TARIFA FINAL ---
        
        // Calcular la diferencia en milisegundos
        long diffMilli = fechaDevolucionUtil.getTime() - fechaRentaUtil.getTime();
        
        // Convertir milisegundos a días
        long diffDays = diffMilli / (24 * 60 * 60 * 1000); 

        // Asegurarse de que sea al menos 1 día
        if (diffDays == 0) {
            diffDays = 1;
        }
        
        // Calcular la tarifa total
        double tarifaTotal = costoDiario * (double) diffDays;
        
        // 6. Asignar Fechas, Estado y COPIA al objeto Alquiler
        nuevoAlquiler.setFechaAlquiler(fechaRentaSQL);
        nuevoAlquiler.setFechaDevolucion(fechaDevolucionSQL);
        nuevoAlquiler.setEstado("RENTADO");        
        nuevoAlquiler.setIdCopia(idCopiaRentada);    

        // 🔑 ASIGNACIÓN DEL COSTO TOTAL
        nuevoAlquiler.setCostoFinal(tarifaTotal); 
        
        // 7. Preparar la Salida del Diálogo
        this.alquiler = nuevoAlquiler;
        this.datosGuardados = true;
        this.dispose();
    
    }//GEN-LAST:event_btnRentarActionPerformed

    private void btn_cancelarRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarRegistroClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarRegistroClienteActionPerformed

    private void btn_buscarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarPeliculaActionPerformed
    String idTexto = cajaBuscadorPelicula.getText().trim();
        if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la película.", "Error de Búsqueda", javax.swing.JOptionPane.WARNING_MESSAGE);
        limpiarDatosPelicula(); // Método para limpiar los JLabel si está vacío
        return; }
        try {
              int idPelicula = Integer.parseInt(idTexto);
                PeliculaDAO peliculaDAO = new PeliculaDAO(); 
                Pelicula peliculaEncontrada = peliculaDAO.obtenerPeliculaPorId(idPelicula);
                // 4. Verificar el resultado de la búsqueda
        if (peliculaEncontrada != null) {
            int copiasDisponibles = peliculaDAO.contarCopiasDisponibles(idPelicula);
            if (copiasDisponibles > 0) {
                txt_CopiasDisponibles.setText(String.valueOf(copiasDisponibles));
                // Opcional: Habilitar el botón RENTAR
                btnRentar.setEnabled(true); 
            } else {
                txt_CopiasDisponibles.setText("0");
                // Opcional: Deshabilitar el botón RENTAR si no hay copias
                btnRentar.setEnabled(false);
                javax.swing.JOptionPane.showMessageDialog(this, "Esta película no tiene copias disponibles para la renta.", "Sin Stock", javax.swing.JOptionPane.WARNING_MESSAGE);
            }
            // A. PELÍCULA ENCONTRADA: Cargar los datos en los JLabel
            cargarDatosPelicula(peliculaEncontrada);
            javax.swing.JOptionPane.showMessageDialog(this, "Película cargada exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            // B. PELÍCULA NO ENCONTRADA: Mostrar error y limpiar
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ninguna película con el ID: " + idPelicula, "Película No Encontrada", javax.swing.JOptionPane.ERROR_MESSAGE);
            limpiarDatosPelicula();
        }
        } catch (NumberFormatException e) {
        // Manejar error si el usuario no ingresó un número
        javax.swing.JOptionPane.showMessageDialog(this, "El ID de Película debe ser un número válido.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        limpiarDatosPelicula();
    } catch (Exception e) {
        // Manejar cualquier otro error (ej. error de conexión a DB)
        javax.swing.JOptionPane.showMessageDialog(this, "Error al buscar en la base de datos: " + e.getMessage(), "Error de Sistema", javax.swing.JOptionPane.ERROR_MESSAGE);
        limpiarDatosPelicula();
    }
    }//GEN-LAST:event_btn_buscarPeliculaActionPerformed

    private void btn_buscadorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscadorClienteActionPerformed
     String idTexto = cajaBuscadorCliente.getText().trim();
    
    if (idTexto.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar el ID del cliente.", "Error de Búsqueda", javax.swing.JOptionPane.WARNING_MESSAGE);
        limpiarDatosCliente(); // Limpiar los JLabel si el campo está vacío
        return;
    }
    
    try {
        int idCliente = Integer.parseInt(idTexto);
        ClienteDAO clienteDAO = new ClienteDAO(); 
        Cliente clienteEncontrado = clienteDAO.obtenerClientePorId(idCliente); // Debes crear este método en tu DAO
        
        if (clienteEncontrado != null) {
            // A. CLIENTE ENCONTRADO: Cargar los datos en los JLabel
            cargarDatosCliente(clienteEncontrado);
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente cargado exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            // B. CLIENTE NO ENCONTRADO: Mostrar error y limpiar
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con el ID: " + idCliente, "Cliente No Encontrado", javax.swing.JOptionPane.ERROR_MESSAGE);
            limpiarDatosCliente();
        }
        } catch (NumberFormatException e) {
        // Manejar error si el usuario no ingresó un número
        javax.swing.JOptionPane.showMessageDialog(this, "El ID de Cliente debe ser un número válido.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
        limpiarDatosCliente();
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al buscar en la base de datos: " + e.getMessage(), "Error de Sistema", javax.swing.JOptionPane.ERROR_MESSAGE);
        limpiarDatosCliente();
    }
    }//GEN-LAST:event_btn_buscadorClienteActionPerformed

    private void dateDevolucionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDevolucionPropertyChange
     if ("date".equals(evt.getPropertyName())) {
        calcularCostoFinal();
    }
    }//GEN-LAST:event_dateDevolucionPropertyChange
//METODOS EXTRAS 
    private void limpiarDatosPelicula() {
    txt_TituloPelicula.setText("...");
    txt_Director.setText("...");
    txt_Categoria.setText("...");
    txt_AlquilerDiario.setText("...");
    verificarHabilitacionFecha();
    }
    private void cargarDatosPelicula(Pelicula p) {
    txt_IDpelicula.setText( String.valueOf( p.getIdPelicula()));
    txt_TituloPelicula.setText(p.getTitulo());
    txt_Director.setText(p.getDirector());
    txt_Categoria.setText(p.getCategoria());
    txt_AlquilerDiario.setText(String.valueOf(p.getPrecioAlquiler())); 
    verificarHabilitacionFecha();
    }
    private  void limpiarDatosCliente() {
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
    final String VALOR_DEFECTO_LABEL = "...";
    boolean peliculaSeleccionada = !tituloPelicula.isEmpty() && !tituloPelicula.equals(VALOR_DEFECTO_LABEL);
    boolean clienteSeleccionado = !nombreCliente.isEmpty() && !nombreCliente.equals(VALOR_DEFECTO_LABEL);
    //Habilitar el JDateChooser y el botón RENTAR solo si AMBOS son verdaderos.
    if (peliculaSeleccionada && clienteSeleccionado) {
        // LÍNEA QUE HABILITA EL CALENDARIO SI AMBOS ESTÁN SELECCIONADOS
        dateDevolucion.setEnabled(true); 
        btnRentar.setEnabled(true); 
    } else {
        // DESHABILITA EL CALENDARIO SI FALTA ALGUNO
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
    private void calcularCostoFinal() {
    // 1. Obtener Costo Diario (debe ser un valor numérico sin el '$')
    String costoDiarioTexto = txt_AlquilerDiario.getText().trim();
    double costoDiario = 0.0;
    
    // Si la película no está cargada, sale y mantiene el costo en $0.00
    if (costoDiarioTexto.equals("...")) {
        jLabel30.setText("$0.00");
        return;
    }
    
    try {
        // Se asegura de que el ID sea numérico (aunque no se usa para el cálculo del precio)
        int idPelicula = Integer.parseInt(cajaBuscadorPelicula.getText()); 
        // Intenta obtener el costo diario de la película
        costoDiario = Double.parseDouble(costoDiarioTexto);
    } catch (NumberFormatException e) {
        System.err.println("Error al convertir costo diario a número: " + e.getMessage());
        jLabel30.setText("ERROR");
        return;
    }

    // 2. Obtener Fechas
    // La fecha de renta se considera la fecha actual del sistema.
    java.util.Date fechaRenta = new java.util.Date(); 
    java.util.Date fechaDevolucion = dateDevolucion.getDate(); 
    
    // Validación de fecha de devolución y comparación
    if (fechaDevolucion == null || fechaDevolucion.before(fechaRenta)) {
        // Si no hay fecha de devolución seleccionada o es anterior a la renta:
        jLabel30.setText("$0.00");
        return;
    }
    
    // 3. Calcular la diferencia en días
    
    // Calcula la diferencia en milisegundos
    long diffMillis = fechaDevolucion.getTime() - fechaRenta.getTime();
    
    // 1 día en milisegundos: 24 horas * 60 minutos * 60 segundos * 1000 milisegundos
    final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    
    // Calcula el número de días, redondeando hacia arriba para incluir el día completo si es necesario
    long diferenciaDias = (long) Math.ceil((double) diffMillis / MILLIS_PER_DAY);
    
    // Asegura que el número mínimo de días sea 1 si la fecha de devolución es hoy
    if (diferenciaDias == 0) {
        diferenciaDias = 1;
    }
    
    // 4. Calcular Costo Total
    double costoTotal = costoDiario * diferenciaDias;
    
    // 5. Mostrar resultado en el JLabel (jLabel30)
    jLabel30.setText("$" + String.format("%.2f", costoTotal));
}
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRentar;
    private javax.swing.JButton btn_buscadorCliente;
    private javax.swing.JButton btn_buscarPelicula;
    private javax.swing.JButton btn_cancelarRegistroCliente;
    private javax.swing.JTextField cajaBuscadorCliente;
    private javax.swing.JTextField cajaBuscadorPelicula;
    private com.toedter.calendar.JDateChooser dateDevolucion;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel txt_CopiasDisponibles;
    private javax.swing.JLabel txt_Director;
    private javax.swing.JLabel txt_IDpelicula;
    private javax.swing.JLabel txt_NombreCliente;
    private javax.swing.JLabel txt_TituloPelicula;
    private javax.swing.JLabel txt_fechaRenta;
    // End of variables declaration//GEN-END:variables

}
