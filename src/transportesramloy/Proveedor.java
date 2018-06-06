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
public class Proveedor {

    Alerts alerta = new Alerts();

    protected void Buscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "NOMBRE", "ESTATUS"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[3];

        try {
            String sql = "SELECT PROV_ID, PROV_NOMBRE, PROV_STATUS "
                    + "FROM proveedores "
                    + "WHERE PROV_ID='" + cadena + "'"
                    + "OR PROV_NOMBRE LIKE '%" + cadena + "%' "
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
            st.close();
            st = null;
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla.getColumnModel().getColumn(2).setMaxWidth(57);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR LOS PROVEEDORES");
        }
    }

    protected void Ver(int id_proveedor) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT * FROM proveedores WHERE PROV_ID=? LIMIT 1");
            }
            psSelectConClave.setInt(1, id_proveedor);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        ProveedorDialog.prov_id.setText(rs.getString(1));
                        ProveedorDialog.prov_nombre.setText(rs.getString(2));
                        ProveedorDialog.prov_calle.setText(rs.getString(3));
                        ProveedorDialog.prov_colonia.setText(rs.getString(4));
                        ProveedorDialog.prov_pobla.setText(rs.getString(5));
                        ProveedorDialog.prov_estado.setText(rs.getString(6));
                        ProveedorDialog.prov_tel.setText(rs.getString(7));
                        ProveedorDialog.prov_rfc.setText(rs.getString(8));
                        ProveedorDialog.prov_cp.setText(rs.getString(9));
                        if ("ACTIVO".equals(rs.getString(10))) {
                            ProveedorDialog.check_activo.setSelected(true);
                        } else {
                            ProveedorDialog.check_inactivo.setSelected(true);
                        }
                    }
                } else {
                    alerta.alertWarning("EL PROVEEDOR NO PUDO SER ENCONTRADO POR QUE FUE ELIMINADO. \nPOR FAVOR ACTUALICE LA BÚSQUEDA DE PROVEEDORES.", "EL PROVEEDOR NO EXISTE");
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER AL PROVEEDOR.");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS DATOS DEL PROVEEDOR");
        }
    }

    protected void Actualizar(int prov_id, String[] datos) {
        try {
            try (PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE proveedores SET "
                    + "PROV_NOMBRE=?, "
                    + "PROV_CALLE=?, "
                    + "PROV_COLONIA=?, "
                    + "PROV_POBLA=?, "
                    + "PROV_ESTADO=?, "
                    + "PROV_TEL=?, "
                    + "PROV_RFC=?, "
                    + "PROV_CP=?, "
                    + "PROV_STATUS=?"
                    + "WHERE PROV_ID=?")) {
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
                ps.setInt(10, prov_id);
                ps.executeUpdate();
                ps.close();
            }
            alerta.alertSuccess("EL PROVEEDOR NO. " + prov_id + " FUE ACTUALIZADO CORRECTAMENTE.", "PROVEEDOR ACTUALIZADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ACTUALIZAR AL PROVEEDOR");
        }
    }

    protected boolean Agregar(String[] datos) {
        try {
            String consulta = "INSERT INTO proveedores (PROV_NOMBRE, PROV_CALLE, PROV_COLONIA, PROV_POBLA, PROV_ESTADO, PROV_TEL, PROV_RFC, PROV_CP, PROV_STATUS) "
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
                alerta.alertSuccess("EL PROVEEDOR NO. " + rs.getInt(1) + " FUE REGISTRADO CORRECTAMENTE.", "PROVEEDOR REGISTRADO");
            }
            rs.close();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL REGISTRAR AL PROVEEDOR");
            return false;
        }
    }

}
