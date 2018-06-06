package transportesramloy;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public abstract class Reportes {

    private static JasperReport report;
    private static JasperPrint reportFilled;
    private static JasperViewer viewer;

    public static void createReport1(int id_orden) {
        try {
            String path = "src\\reports\\ReporteOriginal.jasper";
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("TALON_ID", id_orden);
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            reportFilled = JasperFillManager.fillReport(report, parametro, Conexion.obtener());
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR EL REPORTE PDF.");
        }
    }

    public static void createReport2(int id_orden) {
        try {
            String path = "src\\reports\\ReporteCopia.jasper";
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("TALON_ID", id_orden);
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            reportFilled = JasperFillManager.fillReport(report, parametro, Conexion.obtener());
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR EL REPORTE PDF.");
        }
    }

    public static void viewReport(int id_orden) {
        try {
            viewer = new JasperViewer(reportFilled, false);
            viewer.setIconImage(new ImageIcon("src\\reports\\PDF_20px.png").getImage());
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setTitle("CARTA PORTE NO. " + id_orden);
            viewer.setVisible(true);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ABRIR EL REPORTE PDF.");
        }
    }

    public static void exportReport(int id_orden) {
        try {
            String path = "src\\pdf\\OrdenServicioNo" + id_orden + ".pdf";
            JasperExportManager.exportReportToPdfFile(reportFilled, path);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR EL REPORTE PDF.");
        }
    }

}
