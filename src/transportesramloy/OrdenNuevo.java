package transportesramloy;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public final class OrdenNuevo extends javax.swing.JInternalFrame {

    protected static int id_origen = 0;
    protected static int id_destino = 0;

    /**
     * Creates new form OrdenNuevo
     */
    public OrdenNuevo() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
        id_origen = 0;
        id_destino = 0;
        listProductos();
        listSitios();
        jTextArea1.setComponentOrientation(ComponentOrientation.UNKNOWN);
        noPaste();
    }

    protected void listProductos() {
        selectContienen1.removeAllItems();
        ArrayList<String> lista = new ArrayList<>();
        Orden combo = new Orden();
        lista = combo.llena_combo();
        for (int i = 0; i < lista.size(); i++) {
            selectContienen1.addItem(lista.get(i));
            selectContienen2.addItem(lista.get(i));
            selectContienen3.addItem(lista.get(i));
            selectContienen4.addItem(lista.get(i));
        }
    }

    protected void listSitios() {
        Orden list = new Orden();
        ArrayList<String> lista = list.OrdenSitios();
        new TextAutoCompleter(txtRecogera, lista);
        new TextAutoCompleter(txtEntregara, lista);
    }

    protected void noPaste() {
        JTextField[] fields = new JTextField[10];
        fields[0] = txtPeso1;
        fields[1] = txtPeso2;
        fields[2] = txtPeso3;
        fields[3] = txtPeso4;
        fields[4] = txtFlete;
        fields[5] = txtSeguro;
        fields[6] = txtLineas;
        fields[7] = txtRecoleccion;
        fields[8] = txtEntrega;
        fields[9] = txtManiobras;

        for (JTextField field : fields) {
            InputMap map2 = field.getInputMap(JTextField.WHEN_FOCUSED);
            map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        }
    }

    protected void pesoEstimado() {
        Double peso1 = 0.0;
        if ("".equals(txtPeso1.getText()) || ".".equals(txtPeso1.getText())) {
        } else {
            peso1 = Double.parseDouble(txtPeso1.getText());
        }

        double peso2 = 0.0;
        if ("".equals(txtPeso2.getText()) || ".".equals(txtPeso2.getText())) {
        } else {
            peso2 = Double.parseDouble(txtPeso2.getText());
        }
        double peso3 = 0.0;
        if ("".equals(txtPeso3.getText()) || ".".equals(txtPeso3.getText())) {
        } else {
            peso3 = Double.parseDouble(txtPeso3.getText());
        }
        double peso4 = 0.0;
        if ("".equals(txtPeso4.getText()) || ".".equals(txtPeso4.getText())) {
        } else {
            peso4 = Double.parseDouble(txtPeso4.getText());
        }
        double total = peso1 + peso2 + peso3 + peso4;
        this.jTextArea1.setText(Double.toString(Math.round(total * 100.0) / 100.0));
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    protected void calcular() {
        Double flete;
        if ("".equals(txtFlete.getText()) || ".".equals(txtFlete.getText())) {
            flete = 0.0;
        } else {
            flete = Double.parseDouble(txtFlete.getText());
        }
        Double seguro;
        if ("".equals(txtSeguro.getText()) || ".".equals(txtSeguro.getText())) {
            seguro = 0.0;
        } else {
            seguro = Double.parseDouble(txtSeguro.getText());
        }
        Double olineas;
        if ("".equals(txtLineas.getText()) || ".".equals(txtLineas.getText())) {
            olineas = 0.0;
        } else {
            olineas = Double.parseDouble(txtLineas.getText());
        }
        Double recoleccion;
        if ("".equals(txtRecoleccion.getText()) || ".".equals(txtRecoleccion.getText())) {
            recoleccion = 0.0;
        } else {
            recoleccion = Double.parseDouble(txtRecoleccion.getText());
        }
        Double entrega;
        if ("".equals(txtEntrega.getText()) || ".".equals(txtEntrega.getText())) {
            entrega = 0.0;
        } else {
            entrega = Double.parseDouble(txtEntrega.getText());
        }
        Double maniobras;
        if ("".equals(txtManiobras.getText()) || ".".equals(txtManiobras.getText())) {
            maniobras = 0.0;
        } else {
            maniobras = Double.parseDouble(txtManiobras.getText());
        }

        Double importe = flete + seguro + olineas + recoleccion + entrega + maniobras;
        Double importeFinal = formatearDecimales(importe, 2);

        Double iva = importeFinal * 0.16;
        Double ivaFinal = formatearDecimales(iva, 2);

        Double subtotal = importeFinal + ivaFinal;
        Double subtotalFinal = formatearDecimales(subtotal, 2);

        Double retIva = importeFinal * 0.04;
        Double retIvaFinal = formatearDecimales(retIva, 2);

        Double total = subtotalFinal - retIvaFinal;
        Double totalFinal = formatearDecimales(total, 2);

        this.txtImporte.setText(Double.toString(importeFinal));
        this.txtIva.setText(Double.toString(ivaFinal));
        this.txtSubtotal.setText(Double.toString(subtotalFinal));
        this.txtRetIva.setText(Double.toString(retIvaFinal));
        this.txtTotal.setText(Double.toString(totalFinal));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        txtRemitente = new javax.swing.JTextField();
        txtRfc1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDomicilio1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRecogera = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDestinatario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRfc2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDomicilio2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEntregara = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtFraccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtClase = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCuota = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDeclarado = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtBultoNum1 = new javax.swing.JTextField();
        txtBultoClase1 = new javax.swing.JTextField();
        txtPeso1 = new javax.swing.JTextField();
        txtVolMts1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        selectContienen1 = new javax.swing.JComboBox<>();
        txtBultoNum2 = new javax.swing.JTextField();
        txtBultoClase2 = new javax.swing.JTextField();
        selectContienen2 = new javax.swing.JComboBox<>();
        txtPeso2 = new javax.swing.JTextField();
        txtVolMts2 = new javax.swing.JTextField();
        txtBultoNum3 = new javax.swing.JTextField();
        txtBultoClase3 = new javax.swing.JTextField();
        selectContienen3 = new javax.swing.JComboBox<>();
        txtPeso3 = new javax.swing.JTextField();
        txtVolMts3 = new javax.swing.JTextField();
        txtBultoNum4 = new javax.swing.JTextField();
        txtBultoClase4 = new javax.swing.JTextField();
        selectContienen4 = new javax.swing.JComboBox<>();
        txtPeso4 = new javax.swing.JTextField();
        txtVolMts4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        txtOperador = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtReembarcarse = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtPlacas = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtConduciraDe = new javax.swing.JTextField();
        txtConduciraA = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel42 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtFlete = new javax.swing.JTextField();
        txtSeguro = new javax.swing.JTextField();
        txtLineas = new javax.swing.JTextField();
        txtRecoleccion = new javax.swing.JTextField();
        txtEntrega = new javax.swing.JTextField();
        txtManiobras = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtRetIva = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("NUEVA CARTA PORTE");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Create Document_20px.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(876, 715));

        jPanel1.setPreferredSize(new java.awt.Dimension(858, 680));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        ImageIcon icon = new ImageIcon(this.getClass().getResource("fondos/" + TransportesRamloy.miFondo));
        Image img = icon.getImage();
        jPanel2 = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel2.setBackground(new java.awt.Color(247, 247, 247));

        jLabel1.setText("ORIGEN:");

        jLabel2.setText("REMITENTE:");

        txtOrigen.setEditable(false);

        txtRemitente.setEditable(false);

        txtRfc1.setEditable(false);

        jLabel3.setText("RFC:");

        txtDomicilio1.setEditable(false);

        jLabel4.setText("DOMICILIO:");

        txtRecogera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecogeraKeyTyped(evt);
            }
        });

        jLabel5.setText("SE RECOGERA EN:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Plus_15px.png"))); // NOI18N
        jButton1.setText("CLIENTE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRecogera, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRfc1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDomicilio1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRfc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRecogera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("DESTINO:");

        txtDestino.setEditable(false);

        jLabel7.setText("DESTINATARIO:");

        txtDestinatario.setEditable(false);

        jLabel8.setText("RFC:");

        txtRfc2.setEditable(false);

        jLabel9.setText("DOMICILIO:");

        txtDomicilio2.setEditable(false);

        jLabel10.setText("SE ENTREGARA EN:");

        txtEntregara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntregaraKeyTyped(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Plus_15px.png"))); // NOI18N
        jButton2.setText("PROVEEDOR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntregara, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDomicilio2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRfc2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRfc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDomicilio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEntregara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("FRACCION NUM:");

        txtFraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFraccionKeyTyped(evt);
            }
        });

        jLabel12.setText("CLASE:");

        txtClase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaseKeyTyped(evt);
            }
        });

        jLabel13.setText("CUOTA POR TONELADAS:");

        txtCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuotaKeyTyped(evt);
            }
        });

        jLabel14.setText("VALOR DECLARADO:");

        txtDeclarado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeclaradoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDeclarado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtFraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtDeclarado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("NUM.");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CLASE");

        txtBultoNum1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoNum1KeyTyped(evt);
            }
        });

        txtBultoClase1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoClase1KeyTyped(evt);
            }
        });

        txtPeso1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso1KeyTyped(evt);
            }
        });

        txtVolMts1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVolMts1KeyTyped(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("QUE SE DICE CONTIENEN");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("VOLUMEN");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("BULTOS");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("MTS 3");

        jLabel21.setText("PESO ESTIMADO");

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("PESO");

        selectContienen1.setEditable(true);

        txtBultoNum2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoNum2KeyTyped(evt);
            }
        });

        txtBultoClase2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoClase2KeyTyped(evt);
            }
        });

        selectContienen2.setEditable(true);

        txtPeso2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso2KeyTyped(evt);
            }
        });

        txtVolMts2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVolMts2KeyTyped(evt);
            }
        });

        txtBultoNum3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoNum3KeyTyped(evt);
            }
        });

        txtBultoClase3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoClase3KeyTyped(evt);
            }
        });

        selectContienen3.setEditable(true);

        txtPeso3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso3KeyTyped(evt);
            }
        });

        txtVolMts3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVolMts3KeyTyped(evt);
            }
        });

        txtBultoNum4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoNum4KeyTyped(evt);
            }
        });

        txtBultoClase4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBultoClase4KeyTyped(evt);
            }
        });

        selectContienen4.setEditable(true);

        txtPeso4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso4KeyTyped(evt);
            }
        });

        txtVolMts4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVolMts4KeyTyped(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(3);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(3);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtBultoNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBultoClase4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtBultoNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBultoClase3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtBultoNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBultoClase2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtBultoNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBultoClase1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectContienen4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectContienen3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectContienen2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectContienen1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPeso4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVolMts2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVolMts3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVolMts1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVolMts4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBultoNum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBultoClase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectContienen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVolMts3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBultoNum4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBultoClase4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectContienen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeso4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVolMts4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBultoNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBultoClase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBultoNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBultoClase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(selectContienen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPeso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVolMts2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtVolMts1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPeso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectContienen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //Also fire event after user leaves the field

        txtOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOperadorKeyTyped(evt);
            }
        });

        jLabel22.setText("OPERADOR:");

        jLabel23.setText("UNIDAD:");

        txtUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadKeyTyped(evt);
            }
        });

        jLabel24.setText("REEMBARCARSE CON:");

        txtReembarcarse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReembarcarseKeyTyped(evt);
            }
        });

        jLabel25.setText("PLACAS:");

        txtPlacas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacasKeyTyped(evt);
            }
        });

        jLabel26.setText("CONDUCIRA DE:");

        jLabel27.setText("A:");

        txtConduciraDe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConduciraDeKeyTyped(evt);
            }
        });

        txtConduciraA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConduciraAKeyTyped(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Plus_15px.png"))); // NOI18N
        jButton5.setText("OPERADOR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtConduciraDe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConduciraA, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReembarcarse, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(txtReembarcarse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtConduciraDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtConduciraA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtObservaciones.setRows(5);
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservaciones);

        jLabel42.setText("OBSERVACIONES:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel29.setText("CONCEPTO");

        jLabel30.setText("FLETE");

        jLabel31.setText("SEGURO");

        jLabel32.setText("O. LINEAS");

        jLabel33.setText("RECOLECCION");

        jLabel34.setText("ENTREGA A DOM.");

        jLabel35.setText("MANIOBRAS");

        txtFlete.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFlete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFleteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFleteKeyTyped(evt);
            }
        });

        txtSeguro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSeguro.setText("0");
        txtSeguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSeguroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSeguroKeyTyped(evt);
            }
        });

        txtLineas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLineas.setText("0");
        txtLineas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLineasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLineasKeyTyped(evt);
            }
        });

        txtRecoleccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRecoleccion.setText("0");
        txtRecoleccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRecoleccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecoleccionKeyTyped(evt);
            }
        });

        txtEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntrega.setText("0");
        txtEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntregaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntregaKeyTyped(evt);
            }
        });

        txtManiobras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtManiobras.setText("0");
        txtManiobras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtManiobrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtManiobrasKeyTyped(evt);
            }
        });

        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.setEnabled(false);

        jLabel36.setText("IMPORTE $");

        jLabel37.setText("IVA $");

        jLabel38.setText("SUB-TOTAL $");

        jLabel39.setText("RET. 4% IVA $");

        jLabel40.setText("TOTAL $");

        txtIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIva.setText("0.16");
        txtIva.setEnabled(false);

        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubtotal.setEnabled(false);

        txtRetIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRetIva.setText("0.04");
        txtRetIva.setEnabled(false);

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFlete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(txtRecoleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel40))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtManiobras, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRetIva, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtFlete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtLineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtRecoleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtManiobras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtRetIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Cancel_20px.png"))); // NOI18N
        jButton3.setText("CANCELAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transportesramloy/icons/Save_20px.png"))); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(65, 65, 65)
                .addComponent(jButton4)
                .addGap(239, 239, 239))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        OrdenSearchCliente dialogo = new OrdenSearchCliente(null, rootPaneCheckingEnabled);
        dialogo.setVisible(true);
        dialogo.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Alerts alerta = new Alerts();
        if (id_origen == 0) {
            alerta.alertWarning("DEVES DE SELECCIONAR UN REMITENTE.", "ERROR");
        } else if (id_destino == 0) {
            alerta.alertWarning("DEVES DE SELECCIONAR UN DESTINATARIO.", "ERROR");
        } else if (selectContienen1.getSelectedItem() == "") {
            alerta.alertWarning("DEVES DE AGREGAR EL TIPO DE PRODUCTO.", "ERROR");
        } else if (txtOperador.getText().length() == 0) {
            alerta.alertWarning("DEVES DE ELEGIR AL OPERADOR.", "ERROR");
        } else if (txtImporte.getText().length() == 0) {
            alerta.alertWarning("DEVES AGREGAR UN VALOR PARA CALCULAR EL IMPORTE.", "ERROR");
        } else if (Double.parseDouble(txtImporte.getText()) == 0) {
            alerta.alertWarning("EL VALOR DEL IMPORTE DEBE SER MAYOR QUE 0.", "ERROR");
        } else {
            Icon icono2 = new ImageIcon(getClass().getResource("icons/Help_48px.png"));
            int opcion = JOptionPane.showConfirmDialog(null, "¿DESEAS GUARDAR LOS DATOS?", "AVISO", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, icono2);
            if (opcion == 0) {
                int clave;
                //DATOS DE ORIGEN Y DESTINO
                String[] Array1 = new String[2];
                Array1[0] = this.txtRecogera.getText();
                Array1[1] = this.txtEntregara.getText();

                //FRACCIÓN, CLASE, CUOTA, TONELADAS
                String[] Array2 = new String[4];
                Array2[0] = this.txtFraccion.getText();
                Array2[1] = this.txtClase.getText();
                Array2[2] = this.txtCuota.getText();
                Array2[3] = this.txtDeclarado.getText();

                //DATOS DE LOS BULTOS
                String[] bultos = new String[8];
                bultos[0] = this.txtBultoNum1.getText();
                bultos[1] = this.txtBultoNum2.getText();
                bultos[2] = this.txtBultoNum3.getText();
                bultos[3] = this.txtBultoNum4.getText();
                bultos[4] = this.txtBultoClase1.getText();
                bultos[5] = this.txtBultoClase2.getText();
                bultos[6] = this.txtBultoClase3.getText();
                bultos[7] = this.txtBultoClase4.getText();

                //DATOS DE LOS PRODUCTOS
                String[] productos = new String[4];
                productos[0] = this.selectContienen1.getSelectedItem().toString().toUpperCase();
                productos[1] = this.selectContienen2.getSelectedItem().toString().toUpperCase();
                productos[2] = this.selectContienen3.getSelectedItem().toString().toUpperCase();
                productos[3] = this.selectContienen4.getSelectedItem().toString().toUpperCase();

                //DATOS DEL PESO
                String[] peso = new String[4];
                peso[0] = this.txtPeso1.getText();
                peso[1] = this.txtPeso2.getText();
                peso[2] = this.txtPeso3.getText();
                peso[3] = this.txtPeso4.getText();

                //DATOS DEL VOL MTS3
                String[] mts = new String[4];
                mts[0] = this.txtVolMts1.getText();
                mts[1] = this.txtVolMts2.getText();
                mts[2] = this.txtVolMts3.getText();
                mts[3] = this.txtVolMts4.getText();

                //TOTAL DE LOS PESOS
                String pesoEst;
                if ("".equals(this.jTextArea1.getText())) {
                    pesoEst = "0";
                } else {
                    pesoEst = this.jTextArea1.getText();
                }

                //DATOS DEL OPERADOR Y REEMBARCADOR
                String[] Array3 = new String[6];
                Array3[0] = this.txtOperador.getText();
                Array3[1] = this.txtUnidad.getText();
                Array3[2] = this.txtPlacas.getText();
                Array3[3] = this.txtReembarcarse.getText();
                Array3[4] = this.txtConduciraDe.getText();
                Array3[5] = this.txtConduciraA.getText();

                //DATOS DEL CALCULO DE IMPORTE
                Double[] Array4 = new Double[11];
                Array4[0] = Double.parseDouble(txtFlete.getText());
                Array4[1] = Double.parseDouble(txtSeguro.getText());
                Array4[2] = Double.parseDouble(txtLineas.getText());
                Array4[3] = Double.parseDouble(txtRecoleccion.getText());
                Array4[4] = Double.parseDouble(txtEntrega.getText());
                Array4[5] = Double.parseDouble(txtManiobras.getText());
                Array4[6] = Double.parseDouble(txtImporte.getText());
                Array4[7] = Double.parseDouble(txtIva.getText());
                Array4[8] = Double.parseDouble(txtSubtotal.getText());
                Array4[9] = Double.parseDouble(txtRetIva.getText());
                Array4[10] = Double.parseDouble(txtTotal.getText());

                String obser = this.txtObservaciones.getText();

                String str = String.valueOf(txtTotal.getText());

                int entero = Integer.parseInt(str.substring(0, str.indexOf('.')));
                int decimal = Integer.parseInt(str.substring(str.indexOf('.') + 1));
                ConvierteNumeros convertir = new ConvierteNumeros();
                String res = convertir.convertirLetras(entero);
                if (res == null) {
                    res = String.valueOf(entero);
                }
                String letra = res + " PESOS " + decimal + "/100 M.N.";

                Orden add = new Orden();
                clave = add.OrdenInsertar(id_origen, id_destino, Array1, Array2, bultos, productos, peso, mts, pesoEst, Array3, Array4, obser, letra);

                if (clave == -1) {
                    alerta.alertError("ERROR AL GUARDAR LOS DATOS.\nPOR FAVOR VERIFICA BIEN LOS DATOS E INTENTALO DE NUEVO.", "ERROR AL GUARDAR LOS DATOS");
                } else {
                    OrdenVer.verOrden_ID = clave;
                    alerta.alertSuccess("OPERACION REALIZADA CORRECTAMENTE.\n CARTA PORTE NO. " + clave, "CARTA PORTE GENERADA CORRECTAMENTE");
                    this.dispose();
                    Ventanas ventana = new Ventanas();
                    ventana.ventana_ver(clave);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        OrdenSearchProveedor dialogo = new OrdenSearchProveedor(null, rootPaneCheckingEnabled);
        dialogo.setVisible(true);
        dialogo.toFront();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Icon icono = new ImageIcon(getClass().getResource("icons/Help_48px.png"));
        int opcion = JOptionPane.showConfirmDialog(null, "¿DESEAS CALCELAR LA OPERACIÓN?", "AVISO", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, icono);
        if (opcion == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        OrdenSearchOperador add_operador = new OrdenSearchOperador(null, rootPaneCheckingEnabled);
        add_operador.setVisible(true);
        add_operador.toFront();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtBultoNum1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoNum1KeyTyped
        limit(10, txtBultoNum1, evt);
    }//GEN-LAST:event_txtBultoNum1KeyTyped

    private void txtFleteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFleteKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }

        if (this.txtFlete.getText().length() >= 10) {
            evt.consume();
            this.txtFlete.setForeground(Color.red);
        } else {
            this.txtFlete.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtFleteKeyTyped

    private void txtSeguroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeguroKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        if (this.txtSeguro.getText().length() >= 10) {
            evt.consume();
            this.txtSeguro.setForeground(Color.red);
        } else {
            this.txtSeguro.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtSeguroKeyTyped

    private void txtLineasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineasKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        if (this.txtLineas.getText().length() >= 10) {
            evt.consume();
            this.txtLineas.setForeground(Color.red);
        } else {
            this.txtLineas.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtLineasKeyTyped

    private void txtRecoleccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecoleccionKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        if (this.txtRecoleccion.getText().length() >= 10) {
            evt.consume();
            this.txtRecoleccion.setForeground(Color.red);
        } else {
            this.txtRecoleccion.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtRecoleccionKeyTyped

    private void txtEntregaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregaKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        if (this.txtEntrega.getText().length() >= 10) {
            evt.consume();
            this.txtEntrega.setForeground(Color.red);
        } else {
            this.txtEntrega.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtEntregaKeyTyped

    private void txtManiobrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManiobrasKeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        if (this.txtManiobras.getText().length() >= 10) {
            evt.consume();
            this.txtManiobras.setForeground(Color.red);
        } else {
            this.txtManiobras.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtManiobrasKeyTyped

    private void txtRecogeraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecogeraKeyTyped
        limit(50, txtRecogera, evt);
    }//GEN-LAST:event_txtRecogeraKeyTyped

    private void txtEntregaraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregaraKeyTyped
        limit(50, txtEntregara, evt);
    }//GEN-LAST:event_txtEntregaraKeyTyped

    private void txtFraccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFraccionKeyTyped
        limit(15, txtFraccion, evt);
    }//GEN-LAST:event_txtFraccionKeyTyped

    private void txtClaseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaseKeyTyped
        limit(15, txtClase, evt);
    }//GEN-LAST:event_txtClaseKeyTyped

    private void txtCuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaKeyTyped
        limit(15, txtCuota, evt);
    }//GEN-LAST:event_txtCuotaKeyTyped

    private void txtDeclaradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeclaradoKeyTyped
        limit(15, txtDeclarado, evt);
    }//GEN-LAST:event_txtDeclaradoKeyTyped

    private void txtBultoClase1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoClase1KeyTyped
        limit(10, txtBultoClase1, evt);
    }//GEN-LAST:event_txtBultoClase1KeyTyped

    private void txtPeso1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso1KeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        limit(10, txtPeso1, evt);
    }//GEN-LAST:event_txtPeso1KeyTyped

    private void txtVolMts1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVolMts1KeyTyped
        limit(10, txtVolMts1, evt);
    }//GEN-LAST:event_txtVolMts1KeyTyped

    private void txtOperadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOperadorKeyTyped
        limit(150, txtOperador, evt);
    }//GEN-LAST:event_txtOperadorKeyTyped

    private void txtUnidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadKeyTyped
        limit(50, txtUnidad, evt);
    }//GEN-LAST:event_txtUnidadKeyTyped

    private void txtPlacasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacasKeyTyped
        limit(15, txtPlacas, evt);
    }//GEN-LAST:event_txtPlacasKeyTyped

    private void txtReembarcarseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReembarcarseKeyTyped
        limit(50, txtReembarcarse, evt);
    }//GEN-LAST:event_txtReembarcarseKeyTyped

    private void txtConduciraDeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConduciraDeKeyTyped
        limit(50, txtConduciraDe, evt);
    }//GEN-LAST:event_txtConduciraDeKeyTyped

    private void txtConduciraAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConduciraAKeyTyped
        limit(50, txtConduciraA, evt);
    }//GEN-LAST:event_txtConduciraAKeyTyped

    private void txtFleteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFleteKeyReleased
        calcular();
    }//GEN-LAST:event_txtFleteKeyReleased

    private void txtSeguroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeguroKeyReleased
        calcular();
    }//GEN-LAST:event_txtSeguroKeyReleased

    private void txtLineasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineasKeyReleased
        calcular();
    }//GEN-LAST:event_txtLineasKeyReleased

    private void txtRecoleccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecoleccionKeyReleased
        calcular();
    }//GEN-LAST:event_txtRecoleccionKeyReleased

    private void txtEntregaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregaKeyReleased
        calcular();
    }//GEN-LAST:event_txtEntregaKeyReleased

    private void txtManiobrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtManiobrasKeyReleased
        calcular();
    }//GEN-LAST:event_txtManiobrasKeyReleased

    private void txtObservacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtObservacionesKeyTyped

    private void txtPeso2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso2KeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        limit(10, txtPeso2, evt);
    }//GEN-LAST:event_txtPeso2KeyTyped

    private void txtPeso3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso3KeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        limit(10, txtPeso3, evt);
    }//GEN-LAST:event_txtPeso3KeyTyped

    private void txtPeso4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso4KeyTyped
        char car = evt.getKeyChar();
        if (((car < '0') || (car > '9')) & ((car < '.') || car > '.')) {
            evt.consume();
        }
        limit(10, txtPeso4, evt);
    }//GEN-LAST:event_txtPeso4KeyTyped

    private void txtPeso1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso1KeyReleased
        pesoEstimado();
    }//GEN-LAST:event_txtPeso1KeyReleased

    private void txtPeso2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso2KeyReleased
        pesoEstimado();
    }//GEN-LAST:event_txtPeso2KeyReleased

    private void txtPeso3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso3KeyReleased
        pesoEstimado();
    }//GEN-LAST:event_txtPeso3KeyReleased

    private void txtPeso4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso4KeyReleased
        pesoEstimado();
    }//GEN-LAST:event_txtPeso4KeyReleased

    private void txtBultoNum2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoNum2KeyTyped
        limit(10, txtBultoNum2, evt);
    }//GEN-LAST:event_txtBultoNum2KeyTyped

    private void txtBultoNum3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoNum3KeyTyped
        limit(10, txtBultoNum3, evt);
    }//GEN-LAST:event_txtBultoNum3KeyTyped

    private void txtBultoNum4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoNum4KeyTyped
        limit(10, txtBultoNum4, evt);
    }//GEN-LAST:event_txtBultoNum4KeyTyped

    private void txtBultoClase2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoClase2KeyTyped
        limit(10, txtBultoClase2, evt);
    }//GEN-LAST:event_txtBultoClase2KeyTyped

    private void txtBultoClase3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoClase3KeyTyped
        limit(10, txtBultoClase3, evt);
    }//GEN-LAST:event_txtBultoClase3KeyTyped

    private void txtBultoClase4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBultoClase4KeyTyped
        limit(10, txtBultoClase4, evt);
    }//GEN-LAST:event_txtBultoClase4KeyTyped

    private void txtVolMts2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVolMts2KeyTyped
        limit(10, txtVolMts2, evt);
    }//GEN-LAST:event_txtVolMts2KeyTyped

    private void txtVolMts3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVolMts3KeyTyped
        limit(10, txtVolMts3, evt);
    }//GEN-LAST:event_txtVolMts3KeyTyped

    private void txtVolMts4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVolMts4KeyTyped
        limit(10, txtVolMts4, evt);
    }//GEN-LAST:event_txtVolMts4KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> selectContienen1;
    private javax.swing.JComboBox<String> selectContienen2;
    private javax.swing.JComboBox<String> selectContienen3;
    private javax.swing.JComboBox<String> selectContienen4;
    private javax.swing.JTextField txtBultoClase1;
    private javax.swing.JTextField txtBultoClase2;
    private javax.swing.JTextField txtBultoClase3;
    private javax.swing.JTextField txtBultoClase4;
    private javax.swing.JTextField txtBultoNum1;
    private javax.swing.JTextField txtBultoNum2;
    private javax.swing.JTextField txtBultoNum3;
    private javax.swing.JTextField txtBultoNum4;
    private javax.swing.JTextField txtClase;
    private javax.swing.JTextField txtConduciraA;
    private javax.swing.JTextField txtConduciraDe;
    private javax.swing.JTextField txtCuota;
    private javax.swing.JTextField txtDeclarado;
    protected static javax.swing.JTextField txtDestinatario;
    protected static javax.swing.JTextField txtDestino;
    protected static javax.swing.JTextField txtDomicilio1;
    protected static javax.swing.JTextField txtDomicilio2;
    private javax.swing.JTextField txtEntrega;
    private javax.swing.JTextField txtEntregara;
    private javax.swing.JTextField txtFlete;
    private javax.swing.JTextField txtFraccion;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtLineas;
    private javax.swing.JTextField txtManiobras;
    private javax.swing.JTextArea txtObservaciones;
    protected static javax.swing.JTextField txtOperador;
    protected static javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtPeso1;
    private javax.swing.JTextField txtPeso2;
    private javax.swing.JTextField txtPeso3;
    private javax.swing.JTextField txtPeso4;
    protected static javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtRecogera;
    private javax.swing.JTextField txtRecoleccion;
    private javax.swing.JTextField txtReembarcarse;
    protected static javax.swing.JTextField txtRemitente;
    private javax.swing.JTextField txtRetIva;
    protected static javax.swing.JTextField txtRfc1;
    protected static javax.swing.JTextField txtRfc2;
    private javax.swing.JTextField txtSeguro;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    protected static javax.swing.JTextField txtUnidad;
    private javax.swing.JTextField txtVolMts1;
    private javax.swing.JTextField txtVolMts2;
    private javax.swing.JTextField txtVolMts3;
    private javax.swing.JTextField txtVolMts4;
    // End of variables declaration//GEN-END:variables
}
