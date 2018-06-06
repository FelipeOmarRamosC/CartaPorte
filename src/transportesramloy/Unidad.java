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
public class Unidad {

    Alerts alerta = new Alerts();

    protected void Buscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "NOMBRE", "UNIDAD", "PLACA", "ESTATUS"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[5];

        try {
            String sql = "SELECT UNI_ID, UNI_NOMBRE, UNI_UNIDAD, UNI_PLACA, UNI_STATUS "
                    + "FROM unidades "
                    + "WHERE UNI_ID='" + cadena + "' "
                    + "OR UNI_NOMBRE LIKE '%" + cadena + "%' "
                    + "OR UNI_UNIDAD LIKE '%" + cadena + "%' "
                    + "OR UNI_PLACA LIKE '%" + cadena + "%' "
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
                if (null == rs.getString(5)) {
                    registros[4] = rs.getString(5);
                } else {
                    switch (rs.getString(5)) {
                        case "ACTIVO":
                            registros[4] = "<html><span style='color:white; font-weight:bold; background-color:#00a65a;' >ACTIVO</span></html>";
                            break;
                        case "INACTIVO":
                            registros[4] = "<html><span style='color:white; font-weight:bold; background-color:#dd4b39;' >INACTIVO</span></html>";
                            break;
                        default:
                            registros[4] = rs.getString(6);
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
            tabla.getColumnModel().getColumn(4).setMaxWidth(57);
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR LOS OPERADORES");
        }
    }

    protected void Ver(int id_proveedor) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT * FROM unidades WHERE UNI_ID=? LIMIT 1");
            }
            psSelectConClave.setInt(1, id_proveedor);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        UnidadDialog.uni_id.setText(rs.getString(1));
                        UnidadDialog.uni_nombre.setText(rs.getString(2));
                        UnidadDialog.uni_dir.setText(rs.getString(3));
                        UnidadDialog.uni_tel.setText(rs.getString(4));
                        UnidadDialog.uni_unidad.setText(rs.getString(5));
                        UnidadDialog.uni_placa.setText(rs.getString(6));
                        if ("ACTIVO".equals(rs.getString(7))) {
                            UnidadDialog.check_activo.setSelected(true);
                        } else {
                            UnidadDialog.check_inactivo.setSelected(true);
                        }
                    }
                } else {
                    alerta.alertWarning("EL OPERADOR NO PUDO SER ENCONTRADO POR QUE FUE ELIMINADO. \nPOR FAVOR ACTUALICE LA BÚSQUEDA DE OPERADORES.", "EL OPERADOR NO EXISTE");
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER AL OPERADOR");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS DATOS DEL OPERADOR");
        }
    }

    protected void Actualizar(int uni_id, String[] datos) {
        try {
            try (PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE unidades SET "
                    + "UNI_NOMBRE=?, "
                    + "UNI_DIR=?, "
                    + "UNI_TEL=?, "
                    + "UNI_UNIDAD=?, "
                    + "UNI_PLACA=?, "
                    + "UNI_STATUS=?"
                    + "WHERE UNI_ID=?")) {
                if (datos[0].length() > 150) {
                    datos[0] = datos[0].substring(0, 150);
                }
                if (datos[1].length() > 150) {
                    datos[1] = datos[1].substring(0, 150);
                }
                if (datos[2].length() > 30) {
                    datos[2] = datos[2].substring(0, 30);
                }
                if (datos[3].length() > 15) {
                    datos[3] = datos[3].substring(0, 15);
                }
                if (datos[4].length() > 15) {
                    datos[4] = datos[4].substring(0, 15);
                }
                ps.setString(1, datos[0]);
                ps.setString(2, datos[1]);
                ps.setString(3, datos[2]);
                ps.setString(4, datos[3]);
                ps.setString(5, datos[4]);
                ps.setString(6, datos[5]);
                ps.setInt(7, uni_id);
                ps.executeUpdate();
                ps.close();
            }
            alerta.alertSuccess("EL OPERADOR NO. " + uni_id + " FUE ACTUALIZADO CORRECTAMENTE.", "OPERADOR ACTUALIZADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ACTUALIZAR AL OPERADOR");
        }
    }

    protected void Eliminar(int uni_id) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("DELETE FROM unidades WHERE UNI_ID=?");
            ps.setInt(1, uni_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL OPERADOR NO. " + uni_id + " FUE ELIMINADO CORRECTAMENTE.", "OPERADOR ELIMINADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ELIMINAR AL OPERADOR");
        }
    }

    protected boolean Agregar(String[] datos) {
        try {
            String consulta = "INSERT INTO unidades (UNI_NOMBRE, UNI_DIR, UNI_TEL, UNI_UNIDAD, UNI_PLACA, UNI_STATUS) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = Conexion.obtener().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);

            if (datos[0].length() > 150) {
                datos[0] = datos[0].substring(0, 150);
            }
            if (datos[1].length() > 150) {
                datos[1] = datos[1].substring(0, 150);
            }
            if (datos[2].length() > 30) {
                datos[2] = datos[2].substring(0, 30);
            }
            if (datos[3].length() > 15) {
                datos[3] = datos[3].substring(0, 15);
            }
            if (datos[4].length() > 15) {
                datos[4] = datos[4].substring(0, 15);
            }
            ps.setString(1, datos[0]);
            ps.setString(2, datos[1]);
            ps.setString(3, datos[2]);
            ps.setString(4, datos[3]);
            ps.setString(5, datos[4]);
            ps.setString(6, "ACTIVO");
            ps.executeUpdate();
            // Se obtiene la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                alerta.alertSuccess("EL OPERADOR NO. " + rs.getInt(1) + " FUE REGISTRADO CORRECTAMENTE.", "OPERADOR REGISTRADO");
            }
            rs.close();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL REGISTRAR AL OPERADOR");
            return false;
        }
    }
}
