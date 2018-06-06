package transportesramloy;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public final class ScreenSplash {

    final SplashScreen splash;
    //texto que se muestra a medida que se va cargando el screensplah
    final String[] texto = {"Ramloy", "configuration", "library",
        "files XYZ", "forms", "icons", "properties",
        "jasperReports", "backgrounds", "...",
        "database", "server", "...",
        ""};

    public ScreenSplash() {
        splash = SplashScreen.getSplashScreen();
    }

    public void animar() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            for (int i = 1; i < texto.length; i++) {
                //se pinta texto del array
                g.setColor(new Color(86, 0, 0));//color de fondo del texto
                g.fillRect(203, 328, 280, 12);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.drawString("Cargando " + texto[i - 1] + "...", 203, 338);
                g.setColor(Color.WHITE);//color de barra de progeso
                g.fillRect(204, 308, (i * 307 / texto.length), 12);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {2.0f};
                BasicStroke dashed = new BasicStroke(9.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
                g.setStroke(dashed);
                g.setColor(Color.PINK);//color de barra de progeso
                g.setColor(new Color(86, 0, 0));
                g.drawLine(205, 314, 510, 314);
                //se actualiza todo
                splash.update();
                try {
                    Thread.sleep(100);//221
                } catch (InterruptedException ex) {
                    Logger.getLogger(ScreenSplash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            splash.close();
        }
        //una vez terminada la animación se muestra la aplicación principal
        try {
            menu ventanaPrincipal = new menu();
            ventanaPrincipal.setExtendedState(MAXIMIZED_BOTH);
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ABRIR EL MENU.");
        }
    }

}
