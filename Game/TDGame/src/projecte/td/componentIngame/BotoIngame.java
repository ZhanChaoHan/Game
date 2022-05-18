package projecte.td.componentIngame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 * Son els botons que s'utilitzen en l'estat EstatIngame
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class BotoIngame extends AbstractComponent {

    private static boolean botoSeleccionat;
    // imatge que mostra si el boto es pot clicar
    private static Image imatgeTapada;
    // imatge que mostra si el boto esta seleccionat
    private static Image imatgeSeleccionat;
    // imatge que es mostra quan el boto no esta seleccionat
    private static Image imatgeNoSeleccionat;
    // imatge que mostra si el boto esta seleccionat
    private static Image imatgeSeleccionat2;
    // imatge que es mostra si el boto no esta seleccionat
    private static Image imatgeNoSeleccionat2;
    // imatge que identifica cada boto
    private Image imatgeNormal;
    // flag per determinar si el boto esta disponible
    private boolean disponible;
    // flag per determinar si s'ha clicat el boto
    private boolean clicat;
    // flag per determinar si el boto esta seleccionat
    private boolean seleccionat;
    // flag per determinar si el timer del boto esta activat
    private boolean timerActiu;
    // shape que s'utilitza per les posicions dels botons i control d'events (clicks)
    private Shape area;
    // el que costa la unitat que representa el boto
    private int cost;
    // informa de l'accio que s'ha de dur a terme
    private String accio;
    // s'utilitza per inhabilitar el boto durant un periode de temps
    private Timer timer;
    // comprova el estat per renderitzar el boto
    private boolean cond;

    /**
     * Botons que s'utilitzen en el transcurs del joc (ingame) per seleccionar les unitats o elements
     * que s'han de col·locar en l'escenari.
     * @param gui: context en el que es col·locara el boto
     * @param imatgeNormal: imatge que representara el boto
     * @param x : posicio en l'eix X
     * @param y : posicio en l'eix Y
     * @param cost : cost del que val l'element representat pel boto
     */
    public BotoIngame(GUIContext gui, Image imatgeNormal, int x, int y, int cost, boolean cond) {
        super(gui);
        this.imatgeNormal = imatgeNormal;
        this.cost = cost;
        if (cond) {
            area = new Rectangle(x, y, imatgeSeleccionat.getWidth(), imatgeSeleccionat.getHeight());
        } else {
            area = new Rectangle(x, y, imatgeSeleccionat2.getWidth(), imatgeSeleccionat2.getHeight());
        }

        accio = "null";
        this.cond = cond;
    }

    /**
     * Comprova si el boto esta disponible
     * @param diners : diners necessaris per comprar l'element
     */
    public void update(int diners) {
        if (diners >= cost && !timerActiu) {
            disponible = true;
        } else {
            disponible = false;
        }
    }

    /**
     * Es renderitzen tots els components del boto
     * @param gc : context en el que s'ha de renderitzar
     * @param g : objecte grafics que s'utilitza per renderitzar imatges i animacions
     */
    public void render(GUIContext gc, Graphics g) {
        Image img = null;
        Image imgN = null;
        int a=0;
        int b=0;
        int c=0;
        if (cond) {
            img = imatgeSeleccionat;
            imgN = imatgeNoSeleccionat;
            a=15;
            b=36;
            c=53;
        } else {
            img = imatgeSeleccionat2;
            imgN = imatgeNoSeleccionat2;
        }
        if (disponible && seleccionat) {
            g.drawImage(img, area.getX(), area.getY());
            g.drawImage(imatgeNormal, area.getX() + a, area.getY() + a);
        } else if (disponible) {
            g.drawImage(imgN, area.getX(), area.getY());
            g.drawImage(imatgeNormal, area.getX() + a, area.getY() + a);
        } else {
            g.drawImage(imgN, area.getX(), area.getY());
            g.drawImage(imatgeNormal, area.getX() + a, area.getY() + a);
            g.drawImage(imatgeTapada, area.getX() + b, area.getY() + c);
        }
    }

    /**
     * Posiciona l'element al punt indicat
     * @param x : posicio en l'eix X
     * @param y: posicio en l'eix Y
     */
    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }

    /**
     * Comprova si el mouse clica aquest element
     * @param button : boto que s'ha clicat
     * @param mx : posicio X del mouse quan s'ha clicat
     * @param my : posicio Y del mouse quan s'ha clicat
     */
    @Override
    public void mousePressed(int button, int mx, int my) {
        if (area.contains(mx, my)) {
            notifyListeners();
        }
    }

    /**
     * Reinicia els valors d'algunes variables
     */
    public void clear() {
        accio = "null";
        clicat = false;
        seleccionat = false;
        botoSeleccionat = false;
    }

    /**
     * Afegeix un listener a aquest boto
     * @param unitat
     */
    public void addListener(final String unitat) {
        addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (disponible && !botoSeleccionat) {
                    botoSeleccionat = true;
                    clicat = true;
                    seleccionat = true;
                    accio = unitat;
                }
            }
        });
    }

    /**
     * Activa el timer per inhabilitar el boto durant un periode de temps determinat
     */
    public void activarTimer() {
        disponible = false;
        timerActiu = true;
        timer = new Timer(3000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                clear();
                timerActiu = false;
            }
        });
        timer.start();
        timer.setRepeats(false);
    }

    // Getters i setters
    public void setClicat(boolean clicat) {
        this.clicat = clicat;
    }

    public boolean isClicat() {
        return clicat;
    }

    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public int getX() {
        return (int) area.getX();
    }

    public int getY() {
        return (int) area.getY();
    }

    public String getAccio() {
        return accio;
    }

    public void setAccio(String accio) {
        this.accio = accio;
    }

    public Shape getArea() {
        return area;
    }

    public void setArea(Shape area) {
        this.area = area;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Image getImatgeNormal() {
        return imatgeNormal;
    }

    public void setImatgeNormal(Image imatgeNormal) {
        this.imatgeNormal = imatgeNormal;
    }

    public static Image getImatgeSeleccionat() {
        return imatgeSeleccionat;
    }

    public static void setImatgeSeleccionat(Image imatgeSeleccionat) {
        BotoIngame.imatgeSeleccionat = imatgeSeleccionat;
    }

    public static Image getImatgeTapada() {
        return imatgeTapada;
    }

    public static void setImatgeTapada(Image imatgeTapada) {
        BotoIngame.imatgeTapada = imatgeTapada;
    }

    public boolean isSeleccionat() {
        return seleccionat;
    }

    public void setSeleccionat(boolean seleccionat) {
        this.seleccionat = seleccionat;
    }

    public static Image getImatgeNoSeleccionat() {
        return imatgeNoSeleccionat;
    }

    public static void setImatgeNoSeleccionat(Image imatgeNoSeleccionat) {
        BotoIngame.imatgeNoSeleccionat = imatgeNoSeleccionat;
    }

    public static void setImatgeNoSeleccionat2(Image imatgeNoSeleccionat2) {
        BotoIngame.imatgeNoSeleccionat2 = imatgeNoSeleccionat2;
    }

    public static void setImatgeSeleccionat2(Image imatgeSeleccionat2) {
        BotoIngame.imatgeSeleccionat2 = imatgeSeleccionat2;
    }
}
