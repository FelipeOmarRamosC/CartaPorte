package transportesramloy;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 *
 */
public class TransportesRamloy {

    public static String miFondo;

    private static void tema() {
        try {
            String name = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(name);
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            UIManager.put("MenuItem.background", Color.white);
            UIManager.put("MenuItem.opaque", true);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }

    private static void inicio() {
        Ajustes selectFondo = new Ajustes();
        miFondo = selectFondo.fondoObtener();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        tema();
        inicio();
        new ScreenSplash().animar();
    }

}
