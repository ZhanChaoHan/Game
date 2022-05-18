package projecte.td.componentGUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import projecte.td.managers.ManagerPerfil;

/**
 * Son els botons principals del joc que s'utilitzen per desplasar-se entre menus
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class BotoMenu extends AbstractComponent {

    // Estats del boto
    protected static final int NORMAL = 1;
    protected static final int MOUSE_CLICK = 2;
    protected static final int MOUSE_OVER = 3;
    // Imatge del boto sense mouse over
    protected Image imatgeNormal;
    // Imatge del boto amb mouse over
    protected Image imatgeMouseOver;
    // Imatge del boto quan es fa click
    protected Image mouseDownImage;
    // Imatge del text que ha de contenir el boto
    protected Image imageText;
    // Colors del boto per si no tenen imatge
    protected Color colorNormal = Color.white;
    protected Color colorMouserOver = Color.white;
    protected Color colorMouseClick = Color.white;
    // So del boto al mouse over
    protected Sound soOver;
    // So del boto al ser clicat
    protected Sound soClick;
    // Area que ocupa el boto
    protected Shape area;
    // Imatge actual del boto (la que es renderitzara)
    protected Image imatgeActual;
    // Color actual del boto
    protected Color colorActual;
    // Indica si hi ha mouse over
    protected boolean over;
    // Indica si s'ha clicat amb el ratoli
    protected boolean click;
    // Indica si no s'ha clicat
    protected boolean noClick;
    // Indica si el so s'ha acabat de reproduir
    protected boolean reproduit;
    // Indica si el boto ha de respondre a events
    protected boolean actiu;
    // Estat actual del boto
    protected int state = NORMAL;

    /**
     * Constructor amb 4 parametres
     * @param container joc
     * @param image imatge sense mouse over del boto
     * @param x posicio x del boto
     * @param y posicio y del boto
     */
    public BotoMenu(GUIContext container, Image image, int x, int y) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
    }
    
    /**
     * Constructor amb 6 parametres
     * @param container joc
     * @param image imatge sense mouse over del boto
     * @param x posicio x del boto
     * @param y posicio y del boto
     * @param width amplada del boto
     * @param height alsada del boto
     */
    public BotoMenu(GUIContext container, Image image, int x, int y, int width,
            int height) {
        this(container, image, new Rectangle(x, y, width, height));
    }

    /**
     * Constructor amb 3 parametres
     * @param container joc
     * @param image imatge sense mouse over del boto
     * @param shape area que ocupa el boto
     */
    public BotoMenu(GUIContext container, Image image, Shape shape) {
        super(container);

        area = shape;
        imatgeNormal = image;
        imatgeActual = image;
        imatgeMouseOver = image;
        mouseDownImage = image;

        colorActual = colorNormal;

        state = NORMAL;
        Input input2 = container.getInput();
        over = area.contains(input2.getMouseX(), input2.getMouseY());
        click = input2.isMouseButtonDown(0);
        updateImage();
    }

    /**
     * Posiciona el boto a les coordenades indicades
     * @param x posicio x del boto
     * @param y posicio y del boto
     */
    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param g
     */
    public void render(GUIContext container, Graphics g) {
        // Si ja te una imatge
        if (imatgeActual != null) {
            int xp = (int) (area.getX() + ((getWidth() - imatgeActual.getWidth()) / 2));
            int yp = (int) (area.getY() + ((getHeight() - imatgeActual.getHeight()) / 2));
            imatgeActual.draw(xp, yp, colorActual);
        } else {
            g.setColor(colorActual);
            g.fill(area);
        }
        // Si te imatge de text la dibuixem
        if (imageText != null) {
            int xp = (int) (area.getX() + ((getWidth() - imageText.getWidth()) / 2));
            int yp = (int) (area.getY() + ((getHeight() - imageText.getHeight()) / 2));

            imageText.draw(xp, yp, colorActual);
        }
        updateImage();
    }

    /**
     * Actualitza la imatge que s'ha de mostrar del boto segons l'estat en que es trobi
     */
    protected void updateImage() {
        // No mouse over
        if (!over) {
            imatgeActual = imatgeNormal;
            colorActual = colorNormal;
            state = NORMAL;
            noClick = false;
            reproduit = false;
        } else {
            // Mouse Over
            if (click) {
                // Clicat
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
                // No clicat
                noClick = true;
                if (state != MOUSE_OVER) {
                    if (soOver != null) {
                        if (!reproduit && actiu) {
                            soOver.play(1, (float)ManagerPerfil.getVolumEfectes() / 100);
                            reproduit = true;
                        }
                    }
                    // Si esta actiu...
                    if (actiu) {
                        imatgeActual = imatgeMouseOver;
                        colorActual = colorMouserOver;
                        state = MOUSE_OVER;
                    }
                }
            }
        }
        click = false;
        state = NORMAL;
    }

    /**
     * Comprova si hi ha mouse over amb el boto
     * @param oldx
     * @param oldy
     * @param newx
     * @param newy
     */
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        over = area.contains(newx, newy);
    }

    /**
     * Comprova si s'ha clicat en el boto
     * @param button
     * @param mx
     * @param my
     */
    @Override
    public void mousePressed(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0 && actiu) {
            click = true;
        }
    }

    /**
     * Comprova si s'ha produit un mouse released
     * @param button
     * @param mx
     * @param my
     */
    @Override
    public void mouseReleased(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0) {
            click = false;
        }
    }

    // Getters i setters
    public int getX() {
        return (int) area.getX();
    }

    public int getY() {
        return (int) area.getY();
    }

    public void setNormalColor(Color color) {
        colorNormal = color;
    }

    public void setMouseOverColor(Color color) {
        colorMouserOver = color;
    }

    public void setMouseDownColor(Color color) {
        colorMouseClick = color;
    }

    public void setNormalImage(Image image) {
        imatgeNormal = image;
    }

    public void setMouseOverImage(Image image) {
        imatgeMouseOver = image;
    }

    public void setImageText(Image image) {
        imageText = image;
    }

    public void setMouseDownImage(Image image) {
        mouseDownImage = image;
    }

    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public GUIContext getContainer() {
        return container;
    }

    public Image getImatgeActual() {
        return imatgeActual;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public boolean isActiu() {
        return this.actiu;
    }

    public void setMouseOverSound(Sound sound) {
        soOver = sound;
    }

    public void setMouseDownSound(Sound sound) {
        soClick = sound;
    }
}
