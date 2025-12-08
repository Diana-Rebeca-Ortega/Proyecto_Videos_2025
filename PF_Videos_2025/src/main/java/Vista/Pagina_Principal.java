package Vista;

import Vista.Configuracion.Panel_Configuracion;
import Vista.Reportes.Panel_Reportes;
import Vista.Alquileres.Panel_Alquileres;
import Vista.Catalogo.PanelCatalogo;
import Vista.Empleados.Panel_Empleados;
import Vista.Sucursal.PanelSucursales;
import Vista.Cliente.Panel_Cliente;
import java.awt.Color;

public class Pagina_Principal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pagina_Principal.class.getName());

    public Pagina_Principal() {
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_botones = new javax.swing.JPanel();
        btn_sucursales = new javax.swing.JButton();
        btn_configuracion = new javax.swing.JButton();
        btn_reportes = new javax.swing.JButton();
        btn_alquileres = new javax.swing.JButton();
        btn_clientes = new javax.swing.JButton();
        btn_catalogo = new javax.swing.JButton();
        btn_empleados = new javax.swing.JButton();
        pnl_perfil = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(1, 20, 124));

        jPanel1.setBackground(new java.awt.Color(1, 20, 124));
        jPanel1.setPreferredSize(new java.awt.Dimension(280, 600));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        panel_botones.setBackground(new java.awt.Color(1, 20, 124));
        panel_botones.setPreferredSize(new java.awt.Dimension(300, 20));

        btn_sucursales.setBackground(new java.awt.Color(1, 20, 124));
        btn_sucursales.setForeground(new java.awt.Color(255, 255, 255));
        btn_sucursales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_blancos/icons8-ubicación-de-inicio-24.png"))); // NOI18N
        btn_sucursales.setText("Sucursales");
        btn_sucursales.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_sucursales.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_sucursales.setPreferredSize(new java.awt.Dimension(150, 23));
        btn_sucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sucursalesActionPerformed(evt);
            }
        });

        btn_configuracion.setBackground(new java.awt.Color(1, 20, 124));
        btn_configuracion.setForeground(new java.awt.Color(255, 255, 255));
        btn_configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ajuste.png"))); // NOI18N
        btn_configuracion.setText("Configuracion");
        btn_configuracion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_configuracion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configuracionActionPerformed(evt);
            }
        });

        btn_reportes.setBackground(new java.awt.Color(1, 20, 124));
        btn_reportes.setForeground(new java.awt.Color(255, 255, 255));
        btn_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/grafico-de-barras.png"))); // NOI18N
        btn_reportes.setText("Reportes");
        btn_reportes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_reportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportesActionPerformed(evt);
            }
        });

        btn_alquileres.setBackground(new java.awt.Color(1, 20, 124));
        btn_alquileres.setForeground(new java.awt.Color(255, 255, 255));
        btn_alquileres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/alquilar.png"))); // NOI18N
        btn_alquileres.setText("Alquileres");
        btn_alquileres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_alquileres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_alquileres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alquileresActionPerformed(evt);
            }
        });

        btn_clientes.setBackground(new java.awt.Color(1, 20, 124));
        btn_clientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente (1).png"))); // NOI18N
        btn_clientes.setText("Clientes");
        btn_clientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_clientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });

        btn_catalogo.setBackground(new java.awt.Color(1, 20, 124));
        btn_catalogo.setForeground(new java.awt.Color(255, 255, 255));
        btn_catalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/claqueta.png"))); // NOI18N
        btn_catalogo.setText("Cátalogo/Peliculas");
        btn_catalogo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_catalogo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_catalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_catalogoActionPerformed(evt);
            }
        });

        btn_empleados.setBackground(new java.awt.Color(1, 20, 124));
        btn_empleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_blancos/trabajar.png"))); // NOI18N
        btn_empleados.setText("Empleados");
        btn_empleados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_empleados.setFocusCycleRoot(true);
        btn_empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_empleadosActionPerformed(evt);
            }
        });

        pnl_perfil.setBackground(new java.awt.Color(1, 20, 124));

        jLabel1.setBackground(new java.awt.Color(137, 206, 232));
        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_blancos/icons8-disney-movies-96.png"))); // NOI18N
        jLabel1.setText("Disney Club");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnl_perfilLayout = new javax.swing.GroupLayout(pnl_perfil);
        pnl_perfil.setLayout(pnl_perfilLayout);
        pnl_perfilLayout.setHorizontalGroup(
            pnl_perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_perfilLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        pnl_perfilLayout.setVerticalGroup(
            pnl_perfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_botonesLayout = new javax.swing.GroupLayout(panel_botones);
        panel_botones.setLayout(panel_botonesLayout);
        panel_botonesLayout.setHorizontalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_perfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_sucursales, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_catalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addComponent(btn_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_alquileres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_configuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panel_botonesLayout.setVerticalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botonesLayout.createSequentialGroup()
                .addComponent(pnl_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_alquileres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jPanel1.add(panel_botones);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        pnlContent.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
private final Color COLOR_ACTIVO = new Color(251, 190, 79); // Amarillo Acento
private final Color COLOR_INACTIVO = new Color(1, 20, 124); // Azul Profundo

public void setActiveButton(javax.swing.JButton button) {
    btn_empleados.setBackground(COLOR_INACTIVO);
    btn_clientes.setBackground(COLOR_INACTIVO);
    btn_reportes.setBackground(COLOR_INACTIVO);
    btn_catalogo.setBackground(COLOR_INACTIVO);
    btn_alquileres.setBackground(COLOR_INACTIVO);
    btn_sucursales.setBackground(COLOR_INACTIVO);
    btn_configuracion.setBackground(COLOR_INACTIVO);
    
    button.setBackground(COLOR_ACTIVO);
}
   
    
    
    private void btn_sucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sucursalesActionPerformed
        setActiveButton(btn_sucursales);
        PanelSucursales pnlSucursales = new PanelSucursales();
            pnlContent.removeAll();
    pnlContent.add(pnlSucursales, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout
    pnlContent.revalidate();
    pnlContent.repaint();
    }//GEN-LAST:event_btn_sucursalesActionPerformed

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
              setActiveButton(btn_clientes);
        Panel_Cliente pnlClientes = new Panel_Cliente();
        pnlClientes.cargarClientesATabla();
            pnlContent.removeAll();
    pnlContent.add(pnlClientes, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout
    pnlContent.revalidate();
    pnlContent.repaint();
        System.out.println("Vista.Pagina_Principal.btn_clientesActionPerformed()");
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportesActionPerformed
    setActiveButton(btn_reportes);
        Panel_Reportes pnl_reportes = new Panel_Reportes();
     pnlContent.removeAll();
    pnlContent.add(pnl_reportes, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout

     pnlContent.revalidate();
    pnlContent.repaint();
    }//GEN-LAST:event_btn_reportesActionPerformed

    private void btn_alquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alquileresActionPerformed
        setActiveButton(btn_alquileres);
        Panel_Alquileres pnl_Alquiler = new Panel_Alquileres();
  pnlContent.removeAll();
    pnlContent.add(pnl_Alquiler, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout

     pnlContent.revalidate();
    pnlContent.repaint();

    }//GEN-LAST:event_btn_alquileresActionPerformed

    private void btn_catalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_catalogoActionPerformed
      setActiveButton(btn_catalogo);
        PanelCatalogo pnl_catalogo = new PanelCatalogo();
  pnlContent.removeAll();
    pnlContent.add(pnl_catalogo, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout

     pnlContent.revalidate();
    pnlContent.repaint();
    }//GEN-LAST:event_btn_catalogoActionPerformed

    private void btn_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_empleadosActionPerformed
          setActiveButton(btn_empleados);
        Panel_Empleados pnl_Empleados = new Panel_Empleados();
  pnlContent.removeAll();
    pnlContent.add(pnl_Empleados, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout

     pnlContent.revalidate();
    pnlContent.repaint();
    }//GEN-LAST:event_btn_empleadosActionPerformed

    private void btn_configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configuracionActionPerformed
       setActiveButton(btn_configuracion);
        Panel_Configuracion pnl_configuracion = new Panel_Configuracion();
  pnlContent.removeAll();
    pnlContent.add(pnl_configuracion, java.awt.BorderLayout.CENTER); // Asume pnlContent usa BorderLayout

     pnlContent.revalidate();
    pnlContent.repaint();
    }//GEN-LAST:event_btn_configuracionActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new Pagina_Principal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alquileres;
    private javax.swing.JButton btn_catalogo;
    private javax.swing.JButton btn_clientes;
    private javax.swing.JButton btn_configuracion;
    private javax.swing.JButton btn_empleados;
    private javax.swing.JButton btn_reportes;
    private javax.swing.JButton btn_sucursales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnl_perfil;
    // End of variables declaration//GEN-END:variables
}
