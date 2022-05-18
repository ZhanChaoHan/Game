package projecte.td.componentGUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import projecte.td.managers.ManagerPerfil;

/**
 * Son els botons que s'utilitzen en l'estat EstatSeguentWave
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class BotoSeleccio extends BotoMenu {

    // Unitat que representa el boto
    private String unitat;
    // Imatge de la carta
    private static Image imatgeCarta;
    // Imatge de la carta over
    private static Image imatgeCartaOver;
    // Indica si s'ha triat el boto
    private boolean botoTriat;
    // Indica si el boto nota un canvi
    private boolean notaCanvi;

    /**
     * Constructor amb 5 parametres
     * @param container joc
     * @param image imatge que representa el boto
     * @param x posicio x del boto
     * @param y posicio y del boto
     * @param unitat informació de la unitat
     */
    public BotoSeleccio(GUIContext container, Image image, int x, int y, String unitat) {
        super(container, image, x, y);
        area = new Rectangle(x, y, imatgeCarta.getWidth(), imatgeCarta.getHeight());
        this.unitat = unitat;
    }

    /**
     * Afegeix un listener per events
     */
    public void addListener() {
        addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (actiu) {
                    botoTriat = !botoTriat;
                    notaCanvi = true;
                }
            }
        });
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param g
     */
    @Override
    public void render(GUIContext container, Graphics g) {
        if (imatgeActual != null) {
            if (!over || !actiu) {
                g.drawImage(imatgeCarta, area.getX(), area.getY());
            } else {
                g.drawImage(imatgeCartaOver, area.getX(), area.getY());
            }
            int xp = (int) (area.getX() + ((getWidth() - imatgeActual.getWidth()) / 2));
            int yp = (int) (area.getY() + ((getHeight() - imatgeActual.getHeight()) / 2));
            imatgeActual.draw(xp, yp, colorActual);
        } else {
            g.setColor(colorActual);
            g.fill(area);
        }
        updateImage();
    }

    /**
     * Actualitza la imatge que s'ha de mostrar del boto segons l'estat en que es trobi
     */
    @Override
    protected void updateImage() {
        if (!over) {
            imatgeActual = imatgeNormal;
            colorActual = colorNormal;
            state = NORMAL;
            noClick = false;
            reproduit = false;
        } else {
            if (click) {
                if ((state != MOUSE_CLICK) && (noClick)) {
                    if (soClick != null && actiu) {
                        soClick.play(1, (float)ManagerPerfil.getVolumEfectes() / 100);
                    }
                    imatgeActual = mouseDownImage;
                    colorActual = colorMouseClick;
                    state = MOUSE_CLICK;
                    notifyListeners();
                    noClick = false;
                }
            } else {
                noClick = true;
                if (state != MOUSE_OVER) {
                    if (soOver != null) {
                        if (!reproduit && actiu) {
                            soOver.play(1, (float)ManagerPerfil.getVolumEfectes() / 100);
                            reproduit = true;
                        }
                    }
                    if (actiu) {
                        imatgeActual = imatgeMouseOver;
                    }
                    colorActual = colorMouserOver;
                    state = MOUSE_OVER;
                }
            }
        }

        click = false;
        state = NORMAL;
    }

    // Getters i setters
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        over = area.contains(newx, newy);
    }

    public boolean isBotoTriat() {
        return botoTriat;
    }

    public void setBotoTriat(boolean botoTriat) {
        this.botoTriat = botoTriat;
    }

    public boolean isNotaCanvi() {
        return notaCanvi;
    }

    public void setNotaCanvi(boolean notaCanvi) {
        this.notaCanvi = notaCanvi;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public static Image getImatgeCarta() {
        return imatgeCarta;
    }

    public static void setImatgeCarta(Image imatgeCarta) {
        BotoSeleccio.imatgeCarta = imatgeCarta;
    }

    public static Image getImatgeCartaOver() {
        return imatgeCartaOver;
    }

    public static void setImatgeCartaOver(Image imatgeCartaOver) {
        BotoSeleccio.imatgeCartaOver = imatgeCartaOver;
    }
}
