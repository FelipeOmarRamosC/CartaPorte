package transportesramloy;

import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Alerts {

    public void alertSuccess(String texto, String title) {
        Icon icono = new ImageIcon(getClass().getResource("icons/ok_48.png"));
        JOptionPane.showMessageDialog(null, texto, title, JOptionPane.PLAIN_MESSAGE, icono);
    }

    public void alertError(String texto, String title) {
        Icon icono = new ImageIcon(getClass().getResource("icons/cancel_48.png"));
        JOptionPane.showMessageDialog(null, texto, title, JOptionPane.PLAIN_MESSAGE, icono);
    }

    public void alertInfo(String texto, String title) {
        Icon icono = new ImageIcon(getClass().getResource("icons/info_48.png"));
        JOptionPane.showMessageDialog(null, texto, title, JOptionPane.PLAIN_MESSAGE, icono);
    }

    public void alertWarning(String texto, String title) {
        Icon icono = new ImageIcon(getClass().getResource("icons/error_48.png"));
        JOptionPane.showMessageDialog(null, texto, title, JOptionPane.PLAIN_MESSAGE, icono);
    }

    public void alertNoSearch() {
        Icon icono = new ImageIcon(getClass().getResource("icons/clear_search_48.png"));
        JOptionPane.showMessageDialog(null, "NO SE ENCONTRARON RESULTADOS. \nPOR FAVOR VERIFICA BIEN LOS DATOS...", "BÚSQUEDA NO ENCONTRADA", JOptionPane.PLAIN_MESSAGE, icono);
    }

    void alertError(SQLException e, String error_al_ver_el_cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
