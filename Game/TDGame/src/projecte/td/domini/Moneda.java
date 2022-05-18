package projecte.td.domini;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import projecte.td.managers.ManagerRecursos;

/**
 * Classe Moneda: Objecte clicable que extreuen els miners.
 * @author Ernest Daban i David Alvarez
 */
public class Moneda extends AbstractComponent {

    private Shape area; //Shape que omple la moneda
    private Image imatgeMoneda;//Imatge moneda
    private int comptador;
    private float transparencia = 1;
    private boolean desapareix;
    private boolean actiu;

    /**
     * Constructor classe Moneda
     * @param gui
     * @param posX
     * @param posY
     * @param tipus
     */
    public Moneda(GUIContext gui, int posX, int posY, String tipus) {
        super(gui);
        imatgeMoneda = getImageCorrecta(tipus);
        area = getArea(tipus, posX, posY);
        addListener();
    }

    /**
     * Getter Area Moneda
     * @param tipus
     * @param posX
     * @param posY
     * @return area2
     */
    private Shape getArea(String tipus, int posX, int posY) {
        Shape area2 = null;
        if (tipus.equals("Miner")) {
            area2 = new Rectangle(posX + 32, posY + 45, imatgeMoneda.getWidth(), imatgeMoneda.getHeight());
        } else if (tipus.equals("MagVida")) {
            area2 = new Rectangle(posX + 15, posY + 25, imatgeMoneda.getWidth(), imatgeMoneda.getHeight());
        } else if (tipus.equals("MagRapidesa")) {
            area2 = new Rectangle(posX + 20, posY + 20, imatgeMoneda.getWidth(), imatgeMoneda.getHeight());
        }
        return area2;
    }

    /**
     * Getter imatge correcte
     * @param tipus(Miner,MagVida,MagRapidesa)
     * @return image
     */
    private Image getImageCorrecta(String tipus) {
        Image image = null;
        if (tipus.equals("Miner")) {
            image = ManagerRecursos.getImage("monedaImage");
        } else if (tipus.equals("MagVida")) {
            image = ManagerRecursos.getImage("monedaVidaImage");
        } else if (tipus.equals("MagRapidesa")) {
            image = ManagerRecursos.getImage("monedaRapidesaImage");
        }
        return image;
    }

    /**
     * Updateja la Moneda
     */
    public void update() {
        comptador++;
        if (comptador > 200) {
            transparencia -= 0.002;
        }
        if (transparencia <= 0) {
            desapareix = true;
        }
    }

    /**
     * Es renderitzen tots els components del boto
     * @param gc : context en el que s'ha de renderitzar
     * @param g : objecte grafics que s'utilitza per renderitzar imatges i animacions
     */
    public void render(GUIContext gc, Graphics g) {
        imatgeMoneda.setAlpha(transparencia);
        g.drawImage(imatgeMoneda, area.getX(), area.getY());
        imatgeMoneda.setAlpha(1f);
    }

    /**
     * Afegeix un listener a aquest boto
     * @param unitat
     */
    public void addListener() {
        addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                actiu = true;
            }
        });
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
     * Canvia la posició de la moneda
     * @param x
     * @param y
     */
    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }
    /**
     * Getter Height
     * @return heght
     */
    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }
    /**
     * Getter width
     * @return width
     */
    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }
    /**
     * Getter X
     * @return X
     */
    public int getX() {
        return (int) area.getX();
    }
    /**
     * Getter Y
     * @return Y
     */
    public int getY() {
        return (int) area.getY();
    }
    /**
     * Getter desapareix
     * @return desapareix
     */
    public boolean isDesapareix() {
        return desapareix;
    }
    /**
     * Setter desapareix
     * @param desapareix
     */
    public void setDesapareix(boolean desapareix) {
        this.desapareix = desapareix;
    }
    /**
     * Getter actiu
     * @return actiu
     */
    public boolean isActiu() {
        return actiu;
    }
    /**
     * Setter actiu
     * @param actiu
     */
    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    /**
     * Getter transperencia
     * @return
     */
    public float getTransparencia() {
        return transparencia;
    }
    /**
     * Setter transperencia
     * @param transparencia
     */
    public void setTransparencia(float transparencia) {
        this.transparencia = transparencia;
    }
}
