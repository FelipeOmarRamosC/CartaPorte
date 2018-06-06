package transportesramloy;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Orden {

    Alerts alerta = new Alerts();

    protected void OrdenOrigen(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"NO.", "ORIGEN", "REMITENTE", "RFC", "DOMICILIO"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[5];

        try {
            String sql = "SELECT CLI_ID, CLI_ESTADO, CLI_NOMBRE, CLI_RFC, CLI_POBLA "
                    + "FROM clientes "
                    + "WHERE CLI_STATUS='ACTIVO' "
                    + "AND (CLI_ID='" + cadena + "' "
                    + "OR CLI_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR CLI_POBLA LIKE '%" + cadena + "%' "
                    + "OR CLI_ESTADO LIKE '%" + cadena + "%' "
                    + "OR CLI_RFC LIKE '%" + cadena + "%') "
                    + "ORDER BY CLI_ID DESC";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
            } else {
                alerta.alertNoSearch();
            }
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = rs.getString(5);
                modelo.addRow(registros);
            }
            rs.close();
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR EL ORIGEN");
        }
    }

    protected void OrdenDestino(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"NO.", "DESTINO", "DESTINATARIO", "RFC", "DOMICILIO"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[5];

        try {
            String sql = "SELECT PROV_ID, PROV_ESTADO, PROV_NOMBRE, PROV_RFC, PROV_POBLA "
                    + "FROM proveedores "
                    + "WHERE PROV_STATUS='ACTIVO' "
                    + "AND (PROV_ID='" + cadena + "' "
                    + "OR PROV_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR PROV_POBLA LIKE '%" + cadena + "%' "
                    + "OR PROV_ESTADO LIKE '%" + cadena + "%' "
                    + "OR PROV_RFC LIKE '%" + cadena + "%') "
                    + "ORDER BY PROV_ID DESC";

            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
            } else {
                alerta.alertNoSearch();
            }
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = rs.getString(5);
                modelo.addRow(registros);
            }
            rs.close();
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR EL DESTINO");
        }
    }

    protected void OrdenOperador(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"NO.", "OPERADOR", "UNIDAD", "PLACA"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[4];

        try {
            String sql = "SELECT UNI_ID, UNI_NOMBRE, UNI_UNIDAD, UNI_PLACA "
                    + "FROM unidades "
                    + "WHERE UNI_STATUS='ACTIVO' "
                    + "AND (UNI_ID='" + cadena + "' "
                    + "OR UNI_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR UNI_UNIDAD LIKE '%" + cadena + "%' "
                    + "OR UNI_PLACA LIKE '%" + cadena + "%') "
                    + "ORDER BY UNI_ID DESC";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
            } else {
                alerta.alertNoSearch();
            }
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                modelo.addRow(registros);
            }
            rs.close();
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR AL OPERADOR");
        }
    }

    public ArrayList<String> llena_combo() {
        ArrayList<String> lista = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE PRO_STATUS='ACTIVO'";
        try {
            try (Statement st = Conexion.obtener().createStatement()) {
                ResultSet rs = st.executeQuery(query);
                lista.add("");
                while (rs.next()) {
                    lista.add(rs.getString(2));
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS OPERADORES");
        }
        return lista;
    }

    int OrdenInsertar(int origen, int destino, String[] Array1, String[] Array2, String[] bultos, String[] productos, String[] peso, String[] mts, String pesoEst, String[] Array3, Double[] Array4, String obser, String letra) {
        int claveGenerada = -1;
        Statement csS;
        ResultSet rsS;
        PreparedStatement psSitios;
        String sql;
        try {
            String recoge = Array1[0].trim();
            if (!recoge.isEmpty()) {
                //RECOGERA NO ESTA VACIO
                if (recoge.length() > 50) {
                    recoge = recoge.substring(0, 50);
                }
                sql = "SELECT * FROM sitios WHERE SITIO_NOM='" + recoge + "' LIMIT 1";
                csS = Conexion.obtener().createStatement();
                rsS = csS.executeQuery(sql);
                if (false == rsS.last()) {
                    //NO EXISTE SITIO RECOGE
                    String insert1 = "INSERT INTO sitios (SITIO_NOM) VALUES (?)";
                    psSitios = Conexion.obtener().prepareStatement(insert1);
                    psSitios.setString(1, recoge);
                    psSitios.executeUpdate();
                    psSitios.close();
                }
                rsS.close();
                csS.close();
                csS = null;
            }
            String entrega = Array1[1].trim();
            if (!entrega.isEmpty()) {
                //ENTREGARA NO ESTA VACIO
                if (entrega.length() > 50) {
                    entrega = entrega.substring(0, 50);
                }
                sql = "SELECT * FROM sitios WHERE SITIO_NOM='" + entrega + "' LIMIT 1";
                csS = Conexion.obtener().createStatement();
                rsS = csS.executeQuery(sql);
                if (false == rsS.last()) {
                    //NO EXISTE SITIO ENTREGA
                    String insert1 = "INSERT INTO sitios (SITIO_NOM) VALUES (?)";
                    psSitios = Conexion.obtener().prepareStatement(insert1);
                    psSitios.setString(1, entrega);
                    psSitios.executeUpdate();
                    psSitios.close();
                }
                rsS.close();
                csS.close();
                csS = null;
            }

            String consulta = "INSERT INTO talones (CLIENTE_ID, PROVEEDOR_ID, TAL_RECOGE, TAL_ENTREGA, TAL_FRACCION, TAL_CLASE, TAL_CUOTA, TAL_VALOR, TAL_BUL_NUM1, TAL_BUL_NUM2, TAL_BUL_NUM3, TAL_BUL_NUM4, TAL_BUL_CLASE1, TAL_BUL_CLASE2, TAL_BUL_CLASE3, TAL_BUL_CLASE4, TAL_PRODUCTO1, TAL_PRODUCTO2, TAL_PRODUCTO3, TAL_PRODUCTO4, TAL_PESO1, TAL_PESO2, TAL_PESO3, TAL_PESO4, TAL_VOL_MTS1, TAL_VOL_MTS2, TAL_VOL_MTS3, TAL_VOL_MTS4, TAL_VOL_PESO, TAL_OPERADOR, TAL_UNIDAD, TAL_PLACAS, TAL_REEMBARCARSE, TAL_CONDU_DE, TAL_CONDU_A, TAL_OBSER, TAL_FLETE, TAL_SEGURO, TAL_OLINEAS, TAL_RECOLEC, TAL_E_DOM, TAL_MANIOBRAS, TAL_IMPORTE, TAL_IVA, TAL_SUBTOTAL, TAL_RETENIDO, TAL_TOTAL, TAL_LETRA ,TAL_FECHA, TAL_STATUS) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = Conexion.obtener().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, origen);//CLIENTE_ID
                ps.setInt(2, destino);//PROVEEDOR_ID

                //*** TAL_RECOGE ***
                if (recoge.length() > 50) {
                    recoge = recoge.substring(0, 50);
                }
                ps.setString(3, recoge);
                //***************

                //*** TAL_ENTREGA ***
                if (entrega.length() > 50) {
                    entrega = entrega.substring(0, 50);
                }
                ps.setString(4, entrega);
                //***************

                //*** TAL_FRACCION ***
                if (Array2[0].length() > 15) {
                    Array2[0] = Array2[0].substring(0, 15);
                }
                ps.setString(5, Array2[0]);
                //***************

                //*** TAL_CLASE ***
                if (Array2[1].length() > 15) {
                    Array2[1] = Array2[1].substring(0, 15);
                }
                ps.setString(6, Array2[1]);
                //***************

                //*** TAL_CUOTA ***
                if (Array2[2].length() > 15) {
                    Array2[2] = Array2[2].substring(0, 15);
                }
                ps.setString(7, Array2[2]);
                //***************

                //*** TAL_VALOR ***
                if (Array2[3].length() > 15) {
                    Array2[3] = Array2[3].substring(0, 15);
                }
                ps.setString(8, Array2[3]);
                //***************

                //*** TAL_BUL_NUM1 ***
                if (bultos[0].length() > 10) {
                    bultos[0] = bultos[0].substring(0, 10);
                }
                ps.setString(9, bultos[0]);
                //***************

                //*** TAL_BUL_NUM2 ***
                if (bultos[1].length() > 10) {
                    bultos[1] = bultos[1].substring(0, 10);
                }
                ps.setString(10, bultos[1]);
                //***************

                //*** TAL_BUL_NUM3 ***
                if (bultos[2].length() > 10) {
                    bultos[2] = bultos[2].substring(0, 10);
                }
                ps.setString(11, bultos[2]);
                //***************

                //*** TAL_BUL_NUM4 ***
                if (bultos[3].length() > 10) {
                    bultos[3] = bultos[3].substring(0, 10);
                }
                ps.setString(12, bultos[3]);
                //***************

                //*** TAL_BUL_CLASE1 ***
                if (bultos[4].length() > 10) {
                    bultos[4] = bultos[4].substring(0, 10);
                }
                ps.setString(13, bultos[4]);
                //***************

                //*** TAL_BUL_CLASE2 ***
                if (bultos[5].length() > 10) {
                    bultos[5] = bultos[5].substring(0, 10);
                }
                ps.setString(14, bultos[5]);
                //***************

                //*** TAL_BUL_CLASE3 ***
                if (bultos[6].length() > 10) {
                    bultos[6] = bultos[6].substring(0, 10);
                }
                ps.setString(15, bultos[6]);
                //***************

                //*** TAL_BUL_CLASE4 ***
                if (bultos[7].length() > 10) {
                    bultos[7] = bultos[7].substring(0, 10);
                }
                ps.setString(16, bultos[7]);
                //***************

                //*** TAL_PRODUCTO1 ***
                if (productos[0].length() > 200) {
                    productos[0] = productos[0].substring(0, 200);
                }
                ps.setString(17, productos[0]);
                //***************

                //*** TAL_PRODUCTO2 ***
                if (productos[1].length() > 200) {
                    productos[1] = productos[1].substring(0, 200);
                }
                ps.setString(18, productos[1]);
                //***************

                //*** TAL_PRODUCTO3 ***
                if (productos[2].length() > 200) {
                    productos[2] = productos[2].substring(0, 200);
                }
                ps.setString(19, productos[2]);
                //***************

                //*** TAL_PRODUCTO4 ***
                if (productos[3].length() > 200) {
                    productos[3] = productos[3].substring(0, 200);
                }
                ps.setString(20, productos[3]);
                //***************

                //*** TAL_PESO1 ***
                if (peso[0].length() > 10) {
                    peso[0] = peso[0].substring(0, 10);
                }
                ps.setString(21, peso[0]);
                //***************

                //*** TAL_PESO2 ***
                if (peso[1].length() > 10) {
                    peso[1] = peso[1].substring(0, 10);
                }
                ps.setString(22, peso[1]);
                //***************

                //*** TAL_PESO3 ***
                if (peso[2].length() > 10) {
                    peso[2] = peso[2].substring(0, 10);
                }
                ps.setString(23, peso[2]);
                //***************

                //*** TAL_PESO4 ***
                if (peso[3].length() > 10) {
                    peso[3] = peso[3].substring(0, 10);
                }
                ps.setString(24, peso[3]);
                //***************

                //*** TAL_VOL_MTS1 ***
                if (mts[0].length() > 10) {
                    mts[0] = mts[0].substring(0, 10);
                }
                ps.setString(25, mts[0]);
                //***************

                //*** TAL_VOL_MTS2 ***
                if (mts[1].length() > 10) {
                    mts[1] = mts[1].substring(0, 10);
                }
                ps.setString(26, mts[1]);
                //***************

                //*** TAL_VOL_MTS3 ***
                if (mts[2].length() > 10) {
                    mts[2] = mts[2].substring(0, 10);
                }
                ps.setString(27, mts[2]);
                //***************

                //*** TAL_VOL_MTS4 ***
                if (mts[3].length() > 10) {
                    mts[3] = mts[3].substring(0, 10);
                }
                ps.setString(28, mts[3]);
                //***************

                ps.setString(29, pesoEst);//TAL_VOL_PESO

                //*** TAL_OPERADOR ***
                if (Array3[0].length() > 150) {
                    Array3[0] = Array3[0].substring(0, 150);
                }
                ps.setString(30, Array3[0]);
                //***************

                //*** TAL_UNIDAD ***
                if (Array3[1].length() > 50) {
                    Array3[1] = Array3[1].substring(0, 50);
                }
                ps.setString(31, Array3[1]);
                //***************

                //*** TAL_PLACAS ***
                if (Array3[2].length() > 15) {
                    Array3[2] = Array3[2].substring(0, 15);
                }
                ps.setString(32, Array3[2]);
                //***************

                //*** TAL_REEMBARCARSE ***
                if (Array3[3].length() > 50) {
                    Array3[3] = Array3[3].substring(0, 50);
                }
                ps.setString(33, Array3[3]);
                //***************

                //*** TAL_CONDU_DE ***
                if (Array3[4].length() > 50) {
                    Array3[4] = Array3[4].substring(0, 50);
                }
                ps.setString(34, Array3[4]);
                //***************

                //*** TAL_CONDU_A ***
                if (Array3[5].length() > 50) {
                    Array3[5] = Array3[5].substring(0, 50);
                }
                ps.setString(35, Array3[5]);
                //***************

                ps.setString(36, obser);//TAL_OBSER

                ps.setDouble(37, Array4[0]);//TAL_FLETE
                ps.setDouble(38, Array4[1]);//TAL_SEGURO
                ps.setDouble(39, Array4[2]);//TAL_OLINEAS
                ps.setDouble(40, Array4[3]);//TAL_RECOLEC
                ps.setDouble(41, Array4[4]);//TAL_E_DOM
                ps.setDouble(42, Array4[5]);//TAL_MANIOBRAS
                ps.setDouble(43, Array4[6]);//TAL_IMPORTE
                ps.setDouble(44, Array4[7]);//TAL_IVA
                ps.setDouble(45, Array4[8]);//TAL_SUBTOTAL
                ps.setDouble(46, Array4[9]);//TAL_RETENIDO
                ps.setDouble(47, Array4[10]);//TAL_TOTAL

                ps.setString(48, letra);//TAL_LETRA

                Date date = new Date();
                DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ps.setString(49, hourdateFormat.format(date));//TAL_FECHA

                ps.setString(50, "CERRADO");//TAL_STATUS

                ps.executeUpdate();

                // Se obtiene la clave generada
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    claveGenerada = rs.getInt(1);
                }
                rs.close();
                ps.close();
            } //CLIENTE_ID
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL GUARDAR EL REGISTRO");
        }
        return claveGenerada;
    }

    protected void OrdenBuscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"NO.", "ORIGEN", "REMITENTE", "DESTINO", "DESTINATARIO", "ESTATUS"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[6];

        try {
            String sql = "SELECT talones.TAL_ID, clientes.CLI_ESTADO, clientes.CLI_NOMBRE, proveedores.PROV_ESTADO, proveedores.PROV_NOMBRE, talones.TAL_STATUS "
                    + "FROM talones "
                    + "INNER JOIN clientes "
                    + "ON talones.CLIENTE_ID=clientes.CLI_ID "
                    + "INNER JOIN proveedores "
                    + "ON talones.PROVEEDOR_ID=proveedores.PROV_ID "
                    + "WHERE talones.TAL_ID='" + cadena + "' "
                    + "OR clientes.CLI_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR proveedores.PROV_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR talones.TAL_STATUS LIKE '%" + cadena + "%' "
                    + "ORDER BY talones.TAL_ID DESC";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
            } else {
                alerta.alertNoSearch();
            }
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = rs.getString(5);
                if (null == rs.getString(6)) {
                    registros[5] = rs.getString(6);
                } else {
                    switch (rs.getString(6)) {
                        case "CERRADO":
                            registros[5] = "<html><span style='color:white; font-weight:bold; background-color:#00a65a;' >CERRADO</span></html>";
                            break;
                        case "CANCELADO":
                            registros[5] = "<html><span style='color:white; font-weight:bold; background-color:#dd4b39;' >CANCELADO</span></html>";
                            break;
                        default:
                            registros[5] = rs.getString(6);
                            break;
                    }
                }
                modelo.addRow(registros);
            }
            rs.close();
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(60);
            tabla.getColumnModel().getColumn(5).setMaxWidth(70);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL BUSCAR LA CARTA PORTE");
        }
    }

    protected void OrdenVer(int id_orden) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT talones.TAL_ID, "
                        + "clientes.CLI_ESTADO, clientes.CLI_NOMBRE, clientes.CLI_RFC, clientes.CLI_POBLA, talones.TAL_RECOGE, "
                        + "proveedores.PROV_ESTADO, proveedores.PROV_NOMBRE, proveedores.PROV_RFC, proveedores.PROV_POBLA, talones.TAL_ENTREGA, "
                        + "talones.TAL_FRACCION, talones.TAL_CLASE, talones.TAL_CUOTA, talones.TAL_VALOR, "
                        + "talones.TAL_BUL_NUM1, talones.TAL_BUL_NUM2, talones.TAL_BUL_NUM3, talones.TAL_BUL_NUM4, "
                        + "talones.TAL_BUL_CLASE1, talones.TAL_BUL_CLASE2, talones.TAL_BUL_CLASE3, talones.TAL_BUL_CLASE4, "
                        + "talones.TAL_PRODUCTO1, talones.TAL_PRODUCTO2, talones.TAL_PRODUCTO3, talones.TAL_PRODUCTO4, "
                        + "talones.TAL_PESO1, talones.TAL_PESO2, talones.TAL_PESO3, talones.TAL_PESO4, "
                        + "talones.TAL_VOL_MTS1, talones.TAL_VOL_MTS2, talones.TAL_VOL_MTS3, talones.TAL_VOL_MTS4, "
                        + "talones.TAL_VOL_PESO, "
                        + "talones.TAL_OPERADOR, talones.TAL_UNIDAD, talones.TAL_PLACAS, "
                        + "talones.TAL_REEMBARCARSE, talones.TAL_CONDU_DE, talones.TAL_CONDU_A, "
                        + "talones.TAL_OBSER, "
                        + "talones.TAL_FLETE, talones.TAL_SEGURO, talones.TAL_OLINEAS, talones.TAL_RECOLEC, talones.TAL_E_DOM, talones.TAL_MANIOBRAS, "
                        + "talones.TAL_IMPORTE, talones.TAL_IVA, talones.TAL_SUBTOTAL, talones.TAL_RETENIDO, talones.TAL_TOTAL, "
                        + "DATE_FORMAT(talones.TAL_FECHA, '%d/%m/%Y - %H:%i:%s') AS TAL_FECHA, talones.TAL_STATUS "
                        + "FROM talones "
                        + "INNER JOIN clientes "
                        + "ON talones.CLIENTE_ID=clientes.CLI_ID "
                        + "INNER JOIN proveedores "
                        + "ON talones.PROVEEDOR_ID=proveedores.PROV_ID "
                        + "WHERE talones.TAL_ID=? "
                        + "LIMIT 1");
            }
            psSelectConClave.setInt(1, id_orden);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        OrdenVer.var1.setText(Integer.toString(rs.getInt("TAL_ID")));
                        //origen - destino
                        OrdenVer.txt2.setText(rs.getString("CLI_ESTADO"));
                        OrdenVer.txt3.setText(rs.getString("CLI_NOMBRE"));
                        OrdenVer.txt4.setText(rs.getString("CLI_RFC"));
                        OrdenVer.txt5.setText(rs.getString("CLI_POBLA"));
                        OrdenVer.txt6.setText(rs.getString("TAL_RECOGE"));

                        OrdenVer.txt7.setText(rs.getString("PROV_ESTADO"));
                        OrdenVer.txt8.setText(rs.getString("PROV_NOMBRE"));
                        OrdenVer.txt9.setText(rs.getString("PROV_RFC"));
                        OrdenVer.txt10.setText(rs.getString("PROV_POBLA"));
                        OrdenVer.txt11.setText(rs.getString("TAL_ENTREGA"));

                        //fraccion num
                        OrdenVer.txt12.setText(rs.getString("TAL_FRACCION"));
                        OrdenVer.txt13.setText(rs.getString("TAL_CLASE"));
                        OrdenVer.txt14.setText(rs.getString("TAL_CUOTA"));
                        OrdenVer.txt15.setText(rs.getString("TAL_VALOR"));

                        //que se dice contienen
                        OrdenVer.txtnum1.setText(rs.getString("TAL_BUL_NUM1"));
                        OrdenVer.txtnum2.setText(rs.getString("TAL_BUL_NUM2"));
                        OrdenVer.txtnum3.setText(rs.getString("TAL_BUL_NUM3"));
                        OrdenVer.txtnum4.setText(rs.getString("TAL_BUL_NUM4"));

                        OrdenVer.txtclase1.setText(rs.getString("TAL_BUL_CLASE1"));
                        OrdenVer.txtclase2.setText(rs.getString("TAL_BUL_CLASE2"));
                        OrdenVer.txtclase3.setText(rs.getString("TAL_BUL_CLASE3"));
                        OrdenVer.txtclase4.setText(rs.getString("TAL_BUL_CLASE4"));

                        OrdenVer.txtproduc1.setText(rs.getString("TAL_PRODUCTO1"));
                        OrdenVer.txtproduc2.setText(rs.getString("TAL_PRODUCTO2"));
                        OrdenVer.txtproduc3.setText(rs.getString("TAL_PRODUCTO3"));
                        OrdenVer.txtproduc4.setText(rs.getString("TAL_PRODUCTO4"));

                        OrdenVer.txtpeso1.setText(rs.getString("TAL_PESO1"));
                        OrdenVer.txtpeso2.setText(rs.getString("TAL_PESO2"));
                        OrdenVer.txtpeso3.setText(rs.getString("TAL_PESO3"));
                        OrdenVer.txtpeso4.setText(rs.getString("TAL_PESO4"));

                        OrdenVer.txtmts1.setText(rs.getString("TAL_VOL_MTS1"));
                        OrdenVer.txtmts2.setText(rs.getString("TAL_VOL_MTS2"));
                        OrdenVer.txtmts3.setText(rs.getString("TAL_VOL_MTS3"));
                        OrdenVer.txtmts4.setText(rs.getString("TAL_VOL_MTS4"));

                        OrdenVer.txtpesoest.setText(rs.getString("TAL_VOL_PESO"));

                        //reembarco
                        OrdenVer.txt22.setText(rs.getString("TAL_OPERADOR"));
                        OrdenVer.txt23.setText(rs.getString("TAL_UNIDAD"));
                        OrdenVer.txt24.setText(rs.getString("TAL_PLACAS"));
                        OrdenVer.txt25.setText(rs.getString("TAL_REEMBARCARSE"));
                        OrdenVer.txt26.setText(rs.getString("TAL_CONDU_DE"));
                        OrdenVer.txt27.setText(rs.getString("TAL_CONDU_A"));

                        //OBSERVACIONES
                        OrdenVer.txt28.setText(rs.getString("TAL_OBSER"));

                        //CANTIDADES
                        OrdenVer.txt29.setText(rs.getString("TAL_FLETE"));
                        OrdenVer.txt30.setText(rs.getString("TAL_SEGURO"));
                        OrdenVer.txt31.setText(rs.getString("TAL_OLINEAS"));
                        OrdenVer.txt32.setText(rs.getString("TAL_RECOLEC"));
                        OrdenVer.txt33.setText(rs.getString("TAL_E_DOM"));
                        OrdenVer.txt34.setText(rs.getString("TAL_MANIOBRAS"));

                        OrdenVer.txt35.setText(rs.getString("TAL_IMPORTE"));
                        OrdenVer.txtiva.setText(rs.getString("TAL_IVA"));
                        OrdenVer.txt36.setText(rs.getString("TAL_SUBTOTAL"));
                        OrdenVer.txtretiva.setText(rs.getString("TAL_RETENIDO"));
                        OrdenVer.txt37.setText(rs.getString("TAL_TOTAL"));

                        OrdenVer.labelFecha.setText(rs.getString("TAL_FECHA"));
                        OrdenVer.labelStatus.setText(rs.getString("TAL_STATUS"));

                        //CAMBIA EL COLOR DEL STATUS
                        if ("CERRADO".equals(rs.getString("TAL_STATUS"))) {
                            OrdenVer.labelStatus.setForeground(Color.decode("#00a65a"));
                        } else if ("CANCELADO".equals(rs.getString("TAL_STATUS"))) {
                            OrdenVer.labelStatus.setForeground(Color.decode("#dd4b39"));
                        }
                    }
                } else {
                    alerta.alertNoSearch();
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER LA CARTA PORTE");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "EROR AL CARGAR LOS DATOS DE LA CARTA PORTE");
        }
    }

    public ArrayList<String> OrdenSitios() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sitios";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getString(2));
            }
            rs.close();
            st.close();
            st = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS SITIOS");
        }
        return lista;
    }

    protected boolean OrdenCancelar(int tal_id) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE talones SET TAL_STATUS=? WHERE TAL_ID=?");
            ps.setString(1, "CANCELADO");
            ps.setInt(2, tal_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CANCELAR LA CARTA PORTE");
            return false;
        }
    }

}
