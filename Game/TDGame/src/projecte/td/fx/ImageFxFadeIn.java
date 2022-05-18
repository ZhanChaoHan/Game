package projecte.td.fx;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Fx;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.AlphaEntity;
import mdes.slick.animation.fx.AlphaFx;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Aquesta classe s'utilitza per realitzar fade ins en la pantalla intro
 * @author Modificada per David Alvarez Palau i Ernest Daban Macià
 */
public class ImageFxFadeIn extends AbstractComponent implements AlphaEntity {

    private int x;
    private int y;
    private int startAt;
    private int longUpdate;
    private int counter;
    private Image image;
    private Rectangle area;
    private Fx fx;
    private Timeline timeline;
    private Color filter = new Color(Color.white);

    /**
     * Constructor amb 8 parametres
     * @param container
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     * @param startAt
     * @param longUpdate
     */
    public ImageFxFadeIn(GUIContext container, Image image, int x, int y,
            int width, int height, int startAt, int longUpdate) {
        super(container);
        this.x = x;
        this.y = y;
        this.startAt = startAt;
        this.longUpdate = longUpdate;
        this.image = image;
        setLocation(x, y);
        area = new Rectangle(x, y, width, height);
        timeline = new Timeline();
        Easing ease = Easing.CUBIC_IN;
        fx = new AlphaFx(2000, this, .0f, 1f, ease);
        timeline.add(fx);
    }

    /**
     * S'inicia el fade in (fos de negre)
     */
    private void fxFadeIn() {
        timeline.rewind();
        timeline.setActive(true);
    }

    /**
     * Es renderitza la imatge
     * @param container
     * @param g
     */
    public void render(GUIContext container, Graphics g) {
        if (counter > startAt && counter < startAt + longUpdate) {
            image.draw(x, y, filter);
        }
    }

    /**
     * S'actualitza la lógica de la classe
     * @param delta
     */
    public void update(int delta) {
        if (counter == startAt) {
            fxFadeIn();
        } else if (counter > startAt && counter < startAt + longUpdate) {
            timeline.update(delta);
        }
        counter += delta;
    }

    /**
     * Es posiciona l'area en el lloc correcte
     * @param xPos
     * @param yPos
     */
    public void setLocation(int xPos, int yPos) {
        if (area != null) {
            area.setX(xPos);
            area.setY(yPos);
        }
    }

    // Getters i setters
    public int getX() {
        return (int) area.getX();
    }

    public int getY() {
        return (int) area.getY();
    }

    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    public float getAlpha() {
        return filter.a;
    }

    public void setAlpha(float alpha) {
        filter.a = alpha;
    }
}
