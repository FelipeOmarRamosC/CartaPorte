package transportesramloy;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.Graphics;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class ClientesDialog extends javax.swing.JDialog {

    protected static int cliente_id;

    /**
     * Creates new form ClientesDialog
     *
     * @param parent
     * @param modal
     */
    public ClientesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ImageIcon icon1 = new ImageIcon(getClass().getResource("icons/Clientes_20px.png"));
        setIconImage(icon1.getImage());
        Clientes ver = new Clientes();
        ver.ClientesVer(cliente_id);
        setLocationRelativeTo(menu.panelPrincipal);
    }

    protected void limit(int limite, JTextField caja, KeyEvent evt) {
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
        if (caja.getText().length() >= limite) {
            evt.consume();
            caja.setForeground(Color.red);
        } else {
            caja.setForeground(Color.black);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cli_id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cli_nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cli_rfc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cli_calle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cli_colonia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cli_pobla = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cli_estado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cli_cp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cli_tel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        check_activo = new javax.swing.JCheckBox();
        check_inactivo = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("fondos/" + TransportesRamloy.miFondo));
        Image img = icon.getImage();
        jPanel1 = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Clientes_60px.png"))); // NOI18N
        jLabel10.setText("CLIENTE NO. ");

        cli_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cli_id.setText("#");

        jLabel1.setText("NOMBRE:");

        cli_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_nombreKeyTyped(evt);
            }
        });

        jLabel7.setText("RFC:");

        cli_rfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_rfcKeyTyped(evt);
            }
        });

        jLabel2.setText("CALLE:");

        cli_calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_calleKeyTyped(evt);
            }
        });

        jLabel3.setText("COLONIA:");

        cli_colonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_coloniaKeyTyped(evt);
            }
        });

        jLabel4.setText("POBLACIÓN:");

        cli_pobla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_poblaKeyTyped(evt);
            }
        });

        jLabel5.setText("ESTADO:");

        cli_estado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_estadoKeyTyped(evt);
            }
        });

        jLabel8.setText("CP:");

        cli_cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_cpKeyTyped(evt);
            }
        });

        jLabel6.setText("TELÉFONO:");

        cli_tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cli_telKeyTyped(evt);
            }
        });

        jLabel9.setText("ESTATUS:");

        check_activo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        check_activo.setText("ACTIVO");
        check_activo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_activoItemStateChanged(evt);
            }
        });

        check_inactivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        check_inactivo.setText("INACTIVO");
        check_inactivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_inactivoItemStateChanged(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Save_20px.png"))); // NOI18N
        jButton1.setText("GUARDAR CAMBIOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cli_id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cli_calle, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel3)
                                        .addGap(96, 96, 96))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cli_colonia, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cli_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(cli_rfc, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cli_pobla, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cli_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(cli_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cli_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(check_activo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check_inactivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cli_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_rfc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cli_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_calle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cli_colonia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cli_pobla, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cli_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(check_activo)
                    .addComponent(check_inactivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (cli_nombre.getText().length() == 0) {
            Alerts alerta = new Alerts();
            alerta.alertWarning("EL NOMBRE DEL CLIENTE ES NECESARIO.", "ERROR");
        } else {
            Icon icono2 = new ImageIcon(getClass().getResource("icons/Help_48px.png"));
            int opcion = JOptionPane.showConfirmDialog(null, "¿DESEAS GUARDAR LOS DATOS?", "AVISO", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, icono2);
            if (opcion == 0) {
                String[] datos = new String[9];
                datos[0] = cli_nombre.getText();
                datos[1] = cli_calle.getText();
                datos[2] = cli_colonia.getText();
                datos[3] = cli_pobla.getText();
                datos[4] = cli_estado.getText();
                datos[5] = cli_tel.getText();
                datos[6] = cli_rfc.getText();
                datos[7] = cli_cp.getText();
                if (check_activo.isSelected()) {
                    datos[8] = "ACTIVO";
                } else {
                    datos[8] = "INACTIVO";
                }
                Clientes update = new Clientes();
                update.ClientesActualizar(cliente_id, datos);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void check_activoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_activoItemStateChanged
        if (check_activo.isSelected()) {
            check_activo.setSelected(true);
            check_activo.setForeground(Color.decode("#00A65A"));
            check_inactivo.setSelected(false);
            check_inactivo.setForeground(Color.black);
        } else {
            check_activo.setSelected(false);
            check_activo.setForeground(Color.black);
            check_inactivo.setSelected(true);
            check_inactivo.setForeground(Color.decode("#DD4B39"));
        }
    }//GEN-LAST:event_check_activoItemStateChanged

    private void check_inactivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_inactivoItemStateChanged
        if (check_inactivo.isSelected()) {
            check_inactivo.setSelected(true);
            check_inactivo.setForeground(Color.decode("#DD4B39"));
            check_activo.setSelected(false);
            check_activo.setForeground(Color.black);
        } else {
            check_inactivo.setSelected(false);
            check_inactivo.setForeground(Color.black);
            check_activo.setSelected(true);
            check_activo.setForeground(Color.decode("#00A65A"));
        }
    }//GEN-LAST:event_check_inactivoItemStateChanged

    private void cli_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_nombreKeyTyped
        limit(150, cli_nombre, evt);
    }//GEN-LAST:event_cli_nombreKeyTyped

    private void cli_rfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_rfcKeyTyped
        limit(20, cli_rfc, evt);
    }//GEN-LAST:event_cli_rfcKeyTyped

    private void cli_calleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_calleKeyTyped
        limit(100, cli_calle, evt);
    }//GEN-LAST:event_cli_calleKeyTyped

    private void cli_coloniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_coloniaKeyTyped
        limit(100, cli_colonia, evt);
    }//GEN-LAST:event_cli_coloniaKeyTyped

    private void cli_poblaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_poblaKeyTyped
        limit(50, cli_pobla, evt);
    }//GEN-LAST:event_cli_poblaKeyTyped

    private void cli_estadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_estadoKeyTyped
        limit(30, cli_estado, evt);
    }//GEN-LAST:event_cli_estadoKeyTyped

    private void cli_cpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_cpKeyTyped
        limit(5, cli_cp, evt);
    }//GEN-LAST:event_cli_cpKeyTyped

    private void cli_telKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_telKeyTyped
        limit(30, cli_tel, evt);
    }//GEN-LAST:event_cli_telKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    protected static javax.swing.JCheckBox check_activo;
    protected static javax.swing.JCheckBox check_inactivo;
    protected static javax.swing.JTextField cli_calle;
    protected static javax.swing.JTextField cli_colonia;
    protected static javax.swing.JTextField cli_cp;
    protected static javax.swing.JTextField cli_estado;
    protected static javax.swing.JLabel cli_id;
    protected static javax.swing.JTextField cli_nombre;
    protected static javax.swing.JTextField cli_pobla;
    protected static javax.swing.JTextField cli_rfc;
    protected static javax.swing.JTextField cli_tel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
