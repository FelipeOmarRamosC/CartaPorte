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
public class Otros {

    Alerts alerta = new Alerts();

    protected void SitiosBuscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "NOMBRE"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[2];
        try {
            String query = "SELECT SITIO_ID, SITIO_NOM FROM sitios WHERE SITIO_NOM LIKE '%" + cadena + "%' ORDER BY SITIO_ID DESC";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                rs.beforeFirst();
            } else {
                alerta.alertNoSearch();
            }
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                modelo.addRow(registros);
            }
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);

            rs.close();
            st.close();
            st = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR LOS SITIOS");
        }
    }

    protected void SitiosVer(int id_sitio) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT * FROM sitios WHERE SITIO_ID=? LIMIT 1");
            }
            psSelectConClave.setInt(1, id_sitio);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        SitiosDialog.id_sitio.setText(rs.getString(1));
                        SitiosDialog.sitio_nom.setText(rs.getString(2));
                    }
                } else {
                    alerta.alertWarning("EL SITIO NO PUDO SER ENCONTRADO POR QUE FUE ELIMINADO. \nPOR FAVOR ACTUALICE LA BÚSQUEDA DE SITIOS.", "EL SITIO NO EXISTE");
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER EL SITIO");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS DATOS DEL SITIO");
        }
    }

    protected boolean SitiosAgregar(String sitio_nom) {
        try {
            String query = "INSERT INTO sitios (SITIO_NOM) VALUES (?)";
            PreparedStatement ps = Conexion.obtener().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            if (sitio_nom.length() > 50) {
                sitio_nom = sitio_nom.substring(0, 50);
            }
            ps.setString(1, sitio_nom);
            ps.executeUpdate();
            // Se obtiene la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                alerta.alertSuccess("EL SITIO NO. " + rs.getInt(1) + " FUE REGISTRADO CORRECTAMENTE.", "SITIO REGISTRADO");
            }
            rs.close();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL REGISTRAR EL SITIO");
            return false;
        }
    }

    protected void SitiosActualizar(int sitio_id, String sitio_nom) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE sitios SET SITIO_NOM=? WHERE SITIO_ID=?");
            if (sitio_nom.length() > 50) {
                sitio_nom = sitio_nom.substring(0, 50);
            }
            ps.setString(1, sitio_nom);
            ps.setInt(2, sitio_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL SITIO NO. " + sitio_id + " FUE ACTUALIZADO CORRECTAMENTE.", "SITIO ACTUALIZADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ACTUALIZAR EL SITIO");
        }
    }

    protected void SitiosEliminar(int sitio_id) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("DELETE FROM sitios WHERE SITIO_ID=?");
            ps.setInt(1, sitio_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL SITIO NO. " + sitio_id + " FUE ELIMINADO CORRECTAMENTE.", "SITIO ELIMINADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ELIMINAR EL SITIO");
        }
    }

    protected void ProductosBuscar(JTable tabla, String cadena) {
        DefaultTableModel modelo;
        String[] titulo = {"ID", "PRODUCTO", "ESTADO"};
        modelo = new DefaultTableModel(null, titulo);
        String[] registros = new String[3];
        try {
            String query = "SELECT PRO_ID, PRO_NOM, PRO_STATUS "
                    + "FROM productos "
                    + "WHERE PRO_ID='" + cadena + "' "
                    + "OR PRO_NOM LIKE '%" + cadena + "%' "
                    + "OR PRO_STATUS='" + cadena + "' "
                    + "ORDER BY PRO_ID DESC";
            Statement st = Conexion.obtener().createStatement();
            ResultSet rs = st.executeQuery(query);
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
            alerta.alertError(String.valueOf(e), "ERROR AL MOSTRAR LOS PRODUCTOS");
        }
    }

    protected void ProductosVer(int id_producto) {
        PreparedStatement psSelectConClave = null;
        try {
            if (null == psSelectConClave) {
                psSelectConClave = Conexion.obtener().prepareStatement("SELECT * FROM productos WHERE PRO_ID=? LIMIT 1");
            }
            psSelectConClave.setInt(1, id_producto);
            try (ResultSet rs = psSelectConClave.executeQuery()) {
                if (rs.next()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        ProductosDialog.prod_id.setText(rs.getString(1));
                        ProductosDialog.prod_nom.setText(rs.getString(2));
                        if ("ACTIVO".equals(rs.getString(3))) {
                            ProductosDialog.check_activo.setSelected(true);
                        } else {
                            ProductosDialog.check_inactivo.setSelected(true);
                        }
                    }
                } else {
                    alerta.alertWarning("EL PRODUCTO NO PUDO SER ENCONTRADO POR QUE FUE ELIMINADO. \nPOR FAVOR ACTUALICE LA BÚSQUEDA DE PRODUCTOS.", "EL PRODUCTO NO EXISTE");
                }
                rs.close();
            } catch (SQLException e) {
                alerta.alertError(String.valueOf(e), "ERROR AL VER EL PRODUCTO");
            }
            psSelectConClave.close();
            psSelectConClave = null;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL CARGAR LOS DATOS DEL PRODUCTO");
        }
    }

    protected void ProductosActualizar(int prod_id, String prod_nom, String prod_status) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("UPDATE productos SET PRO_NOM=?, PRO_STATUS=? WHERE PRO_ID=?");
            if (prod_nom.length() > 200) {
                prod_nom = prod_nom.substring(0, 200);
            }
            ps.setString(1, prod_nom);
            ps.setString(2, prod_status);
            ps.setInt(3, prod_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL PRODUCTO NO. " + prod_id + " FUE ACTUALIZADO CORRECTAMENTE.", "PRODUCTO ACTUALIZADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ACTUALIZAR EL PRODUCTO");
        }
    }

    protected void ProductosEliminar(int prod_id) {
        try {
            PreparedStatement ps = Conexion.obtener().prepareStatement("DELETE FROM productos WHERE PRO_ID=?");
            ps.setInt(1, prod_id);
            ps.executeUpdate();
            ps.close();
            ps = null;
            alerta.alertSuccess("EL PRODUCTO NO. " + prod_id + " FUE ELIMINADO CORRECTAMENTE.", "PRODUCTO ELIMINADO");
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL ELIMINAR EL PRODUCTO");
        }
    }

    protected boolean ProductosAgregar(String prod_nom) {
        try {
            String query = "INSERT INTO productos (PRO_NOM, PRO_STATUS) VALUES (?,?)";
            PreparedStatement ps = Conexion.obtener().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            if (prod_nom.length() > 200) {
                prod_nom = prod_nom.substring(0, 200);
            }
            ps.setString(1, prod_nom);
            ps.setString(2, "ACTIVO");
            ps.executeUpdate();
            // Se obtiene la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                alerta.alertSuccess("EL PRODUCTO NO. " + rs.getInt(1) + " FUE REGISTRADO CORRECTAMENTE.", "PRODUCTO REGISTRADO");
            }
            rs.close();
            ps.close();
            ps = null;
            return true;
        } catch (SQLException e) {
            alerta.alertError(String.valueOf(e), "ERROR AL REGISTRAR EL PRODUCTO");
            return false;
        }
    }

}
