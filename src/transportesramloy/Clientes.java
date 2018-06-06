package transportesramloy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Clientes {

    Alerts alerta = new Alerts();

    protected void ClientesBuscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "NOMBRE", "ESTATUS"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[3];
        String sql = "SELECT CLI_ID, CLI_NOMBRE, CLI_STATUS "
                + "FROM clientes "
                + "WHERE CLI_ID='" + cadena + "'"
                + "OR CLI_NOMBRE LIKE '%" + cadena + "%' "
                + "ORDER BY CLI_ID DESC";
        try {
            Statement st = Conexion.obtener().createStatement();
            try (ResultSet rs = st.executeQuery(sql)) {
                if (rs.next()) {
                    rs.beforeFirst();
                } else {
                    alerta.alertNoSearch();
                }
                while (rs.next()) {
                    registros[0] = rs.getString(1);
                    registros[1] = rs.getString(2);
                    if (null == rs.getString(3)) {
                        registros[2] = rs.getString(3);
                    } else {
                        switch (rs.getString(3)) {
                            case "ACTIVO":
                                registros[2] = "<html><span style='color:white; font-weight:bold; background-color:#00a65a;' >ACTIVO</span></html>";
                                break;
                            case "INACTIVO":
                                registros[2] = "<html><span style='color:white; font-weight:bold; background-color:#dd4b39;' >INACTIVO</span></html>";
                                break;
                            default:
                                registros[2] = rs.getString(3);
                                break;
                        }
                    }
                    modelo.addRow(registros);
                }
                rs.close();
            }
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla.getColumnModel().getColumn(2).setMaxWidth(57);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR LOS CLIENTES");
        }
    }

    protected void ClientesVer(int id_cliente) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT * FROM clientes WHERE CLI_ID=? LIMIT 1");
            }
            psSelectConClave.setInt(1, id_cliente);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        ClientesDialog.cli_id.setText(rs.getString(1));
                        ClientesDialog.cli_nombre.setText(rs.getString(2));
                        ClientesDialog.cli_calle.setText(rs.getString(3));
                        ClientesDialog.cli_colonia.setText(rs.getString(4));
                        ClientesDialog.cli_pobla.setText(rs.getString(5));
                        ClientesDialog.cli_estado.setText(rs.getString(6));
                        ClientesDialog.cli_tel.setText(rs.getString(7));
                        ClientesDialog.cli_rfc.setText(rs.getString(8));
                        ClientesDialog.cli_cp.setText(rs.getString(9));
                        if ("ACTIVO".equals(rs.getString(10))) {
                            ClientesDialog.check_activo.setSelected(true);
                        } else {
                            ClientesDialog.check_inactivo.setSelected(true);
                        }
                    }
                } else {
                    alerta.alertWarning("EL CLIENTE NO PUDO SER ENCONTRADO POR QUE FUE ELIMINADO. \nPOR FAVOR ACTUALICE LA BÚSQUEDA DE CLIENTES.", "EL CLIENTE NO EXISTE");
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER AL CLIENTE");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR CLIENTES");
        }
    }

    protected void ClientesActualizar(int cli_id, String[] datos) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE clientes SET "
                    + "CLI_NOMBRE=?, "
                    + "CLI_CALLE=?, "
                    + "CLI_COLONIA=?, "
                    + "CLI_POBLA=?, "
                    + "CLI_ESTADO=?, "
                    + "CLI_TEL=?, "
                    + "CLI_RFC=?, "
                    + "CLI_CP=?, "
                    + "CLI_STATUS=?"
                    + "WHERE CLI_ID=?");

            if (datos[0].length() > 150) {
                datos[0] = datos[0].substring(0, 150);
            }
            if (datos[1].length() > 100) {
                datos[1] = datos[1].substring(0, 100);
            }
            if (datos[2].length() > 100) {
                datos[2] = datos[2].substring(0, 100);
            }
            if (datos[3].length() > 50) {
                datos[3] = datos[3].substring(0, 50);
            }
            if (datos[4].length() > 30) {
                datos[4] = datos[4].substring(0, 30);
            }
            if (datos[5].length() > 30) {
                datos[5] = datos[5].substring(0, 30);
            }
            if (datos[6].length() > 20) {
                datos[6] = datos[6].substring(0, 20);
            }
            if (datos[7].length() > 5) {
                datos[7] = datos[7].substring(0, 5);
            }
            ps.setString(1, datos[0]);
            ps.setString(2, datos[1]);
            ps.setString(3, datos[2]);
            ps.setString(4, datos[3]);
            ps.setString(5, datos[4]);
            ps.setString(6, datos[5]);
            ps.setString(7, datos[6]);
            ps.setString(8, datos[7]);
            ps.setString(9, datos[8]);
            ps.setInt(10, cli_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL CLIENTE NO. " + cli_id + " FUE ACTUALIZADO CORRECTAMENTE.", "CLIENTE ACTUALIZADO");
        } catch (SQLException e) {
            alerta.alertSuccess(String.valueOf(e), "ERROR AL ACTUALIZAR AL CLIENTE");
        }
    }

    protected boolean ClientesAgregar(String[] datos) {
        try {
            String consulta = "INSERT INTO clientes (CLI_NOMBRE, CLI_CALLE, CLI_COLONIA, CLI_POBLA, CLI_ESTADO, CLI_TEL, CLI_RFC, CLI_CP, CLI_STATUS) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Conexion.obtener().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            if (datos[0].length() > 150) {
                datos[0] = datos[0].substring(0, 150);
            }
            if (datos[1].length() > 100) {
                datos[1] = datos[1].substring(0, 100);
            }
            if (datos[2].length() > 100) {
                datos[2] = datos[2].substring(0, 100);
            }
            if (datos[3].length() > 50) {
                datos[3] = datos[3].substring(0, 50);
            }
            if (datos[4].length() > 30) {
                datos[4] = datos[4].substring(0, 30);
            }
            if (datos[5].length() > 30) {
                datos[5] = datos[5].substring(0, 30);
            }
            if (datos[6].length() > 20) {
                datos[6] = datos[6].substring(0, 20);
            }
            if (datos[7].length() > 5) {
                datos[7] = datos[7].substring(0, 5);
            }
            ps.setString(1, datos[0]);
            ps.setString(2, datos[1]);
            ps.setString(3, datos[2]);
            ps.setString(4, datos[3]);
            ps.setString(5, datos[4]);
            ps.setString(6, datos[5]);
            ps.setString(7, datos[6]);
            ps.setString(8, datos[7]);
            ps.setString(9, "ACTIVO");
            ps.executeUpdate();
            // Se obtiene la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                alerta.alertSuccess("EL CLIENTE NO. " + rs.getInt(1) + " FUE REGISTRADO CORRECTAMENTE.", "CLIENTE REGISTRADO");
            }
            rs.close();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL REGISTRAR AL CLIENTE");
            return false;
        }
    }

}
