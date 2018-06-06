package transportesramloy;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Conexion {

    private static Connection cnx = null;

    public static Connection obtener() {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/transportes_ramloy", "root", "");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR DE CONEXIÓN", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERROR DEL DRIVER", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "ERROR DE RESULTADO", JOptionPane.ERROR_MESSAGE);
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }

}
