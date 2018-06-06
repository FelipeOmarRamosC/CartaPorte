package transportesramloy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class ComboFondo {

    private String FONDO_IMG;
    private String FONDO_NOM;

    public ComboFondo(String imagen, String nombre) {
        this.FONDO_IMG = imagen;
        this.FONDO_NOM = nombre;
    }

    public ComboFondo() {
    }

    public String getImg() {
        return FONDO_IMG;
    }

    public void setImg(String imagen) {
        this.FONDO_IMG = imagen;
    }

    public String getNombre() {
        return FONDO_NOM;
    }

    public void setNombre(String nombre) {
        this.FONDO_NOM = nombre;
    }

    public void mostrarFondos(JComboBox<ComboFondo> jComboBox1) {
        try {
            String query = "SELECT FONDO_IMG, FONDO_NOM FROM fondos WHERE FONDO_STATUS=0";
            ResultSet rs;
            try (Statement st = Conexion.obtener().createStatement()) {
                rs = st.executeQuery(query);
                while (rs.next()) {
                    jComboBox1.addItem(new ComboFondo(rs.getString("FONDO_IMG"), rs.getString("FONDO_NOM")));
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ComboFondo.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        //return FONDO_IMG + " " + FONDO_NOM;
        return FONDO_NOM;
    }

}
