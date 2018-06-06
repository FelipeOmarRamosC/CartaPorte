package transportesramloy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Ajustes {

    public String fondoObtener() {
        String fondo = "fondo.jpg";
        try {
            String query = "SELECT FONDO_IMG FROM fondos WHERE FONDO_STATUS=1 LIMIT 1";
            try (Statement st = Conexion.obtener().createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    fondo = rs.getString(1);
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, String.valueOf(e), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return fondo;
    }

    protected boolean fondoActualizar(String imagen) {
        try {
            String fondoUpdate = TransportesRamloy.miFondo;
            TransportesRamloy.miFondo = imagen;
            try (PreparedStatement ps1 = Conexion.obtener().prepareStatement("UPDATE fondos SET FONDO_STATUS=? WHERE FONDO_IMG=?")) {
                ps1.setInt(1, 0);
                ps1.setString(2, fondoUpdate);
                ps1.executeUpdate();
                ps1.close();
            }
            try (PreparedStatement ps2 = Conexion.obtener().prepareStatement("UPDATE fondos SET FONDO_STATUS=? WHERE FONDO_IMG=?")) {
                ps2.setInt(1, 1);
                ps2.setString(2, imagen);
                ps2.executeUpdate();
                ps2.close();
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, String.valueOf(e), "ERROR AL ACTUALIZAR EL FONDO.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
