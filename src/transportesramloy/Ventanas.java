package transportesramloy;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;

/**
 *
 * @author Ing. Felipe Omar Ramos C. Desarrollador Mikrobyte Puebla S.A. de C.V.
 */
public class Ventanas {

    public void ventana_buscar() {
        OrdenSearch ventana_buscar = new OrdenSearch();
        ventana_buscar.setTitle("BUSCAR CARTA PORTE");
        menu.panelPrincipal.add(ventana_buscar);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = ventana_buscar.getSize();
        ventana_buscar.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana_buscar.moveToFront();
        ventana_buscar.show();
    }

    public void ventana_nuevo() {
        String k = "";
        JInternalFrame[] a = menu.panelPrincipal.getAllFrames();
        int filas = a.length;
        for (int i = 0; i < filas; i++) {
            String c = a[i].getTitle();
            if ("NUEVA CARTA PORTE".compareTo(c) == 0) {
                k = "Abierto";
                if (a[i].isDisplayable()) {
                } else {
                    a[i].pack();
                }
                a[i].moveToFront();
                try {
                    a[i].setSelected(true);
                } catch (PropertyVetoException e) {
                    //System.out.println(e);
                }
            } else {
            }
        }
        if (k.compareTo("") == 0) {
            OrdenNuevo ventana_nuevo = new OrdenNuevo();
            menu.panelPrincipal.add(ventana_nuevo);
            ventana_nuevo.toFront();
            ventana_nuevo.show();
        } else {
            Alerts alerta = new Alerts();
            alerta.alertWarning("LA VENTANA QUE INTENTAS ABRIR YA ESTÁ ABIERTA.\n"
                    + "PARA ABRIR UNA NUEVA VENTANA CONTINUA CON LA ANTERIOR O CIERRA LA VENTANA.", "AVISO");
        }
    }

    public void ventana_ver(int id_orden) {
        OrdenVer ventana_ver = new OrdenVer();
        ventana_ver.setTitle("VER CARTA PORTE NO. " + id_orden);
        menu.panelPrincipal.add(ventana_ver);
        ventana_ver.toFront();
        ventana_ver.show();
    }

    public void ventana_CLIBuscar() {
        ClientesSearch ventan_cliente = new ClientesSearch();
        ventan_cliente.setTitle("CLIENTES");
        menu.panelPrincipal.add(ventan_cliente);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = ventan_cliente.getSize();
        ventan_cliente.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventan_cliente.toFront();
        ventan_cliente.show();
    }

    public void ventana_CLINuevo() {
        ClientesNuevo ventan_CNuevo = new ClientesNuevo();
        ventan_CNuevo.setTitle("NUEVO CLIENTE");
        menu.panelPrincipal.add(ventan_CNuevo);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = ventan_CNuevo.getSize();
        ventan_CNuevo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventan_CNuevo.toFront();
        ventan_CNuevo.show();
    }

    public void ventana_PROVBuscar() {
        ProveedorSearch vtn_search = new ProveedorSearch();
        vtn_search.setTitle("PROVEEDORES");
        menu.panelPrincipal.add(vtn_search);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_search.getSize();
        vtn_search.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_search.toFront();
        vtn_search.show();
    }

    public void ventana_PROVNuevo() {
        ProveedorNuevo vtn_nuevo = new ProveedorNuevo();
        vtn_nuevo.setTitle("NUEVO PROVEEDOR");
        menu.panelPrincipal.add(vtn_nuevo);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_nuevo.getSize();
        vtn_nuevo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_nuevo.toFront();
        vtn_nuevo.show();
    }

    public void ventana_UNIBuscar() {
        UnidadSearch vtn_search = new UnidadSearch();
        vtn_search.setTitle("OPERADORES");
        menu.panelPrincipal.add(vtn_search);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_search.getSize();
        vtn_search.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_search.toFront();
        vtn_search.show();
    }

    public void ventana_UNINuevo() {
        UnidadNuevo vtn_nuevo = new UnidadNuevo();
        vtn_nuevo.setTitle("NUEVO OPERADOR");
        menu.panelPrincipal.add(vtn_nuevo);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_nuevo.getSize();
        vtn_nuevo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_nuevo.toFront();
        vtn_nuevo.show();
    }

    public void ventana_SITBuscar() {
        SitiosSearch vtn_search = new SitiosSearch();
        vtn_search.setTitle("SITIOS");
        menu.panelPrincipal.add(vtn_search);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_search.getSize();
        vtn_search.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_search.toFront();
        vtn_search.show();
    }

    public void ventana_PRODBuscar() {
        ProductosSearch vtn_search = new ProductosSearch();
        vtn_search.setTitle("PRODUCTOS");
        menu.panelPrincipal.add(vtn_search);
        Dimension desktopSize = menu.panelPrincipal.getSize();
        Dimension FrameSize = vtn_search.getSize();
        vtn_search.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        vtn_search.toFront();
        vtn_search.show();
    }

}
