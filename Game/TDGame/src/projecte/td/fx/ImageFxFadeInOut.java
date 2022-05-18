package projecte.td.fx;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 * Aquesta classe s'utilitza per realitzar fade out i fade in en la pantalla intro
 * @author Modificada per David Alvarez Palau i Ernest Daban Macià
 */
public class ImageFxFadeInOut extends AbstractComponent {

    private int x;
    private int y;
    private int width;
    private int heigth;
    private ImageFxFadeIn fxFadeIn;
    private ImageFxFadeOut fxFadeOut;

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
    public ImageFxFadeInOut(GUIContext container, Image image, int x, int y,
            int width, int height, int startAt, int longUpdate) {
        super(container);
        fxFadeIn = new ImageFxFadeIn(container, image, x, y, width, height,
                startAt, longUpdate - startAt);
        fxFadeOut = new ImageFxFadeOut(container, image, x, y, width, height,
                startAt + (longUpdate - startAt), longUpdate);
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = height;
    }

    /**
     * Es renderitza la imatge
     * @param container
     * @param g
     */
    @Override
    public void render(GUIContext container, Graphics g) {
        fxFadeIn.render(container, g);
        fxFadeOut.render(container, g);
    }

    /**
     * S'actualitza la lógica de la classe
     * @param delta
     */
    public void update(int delta) {
        fxFadeIn.setLocation(x, y);
        fxFadeOut.setLocation(x, y);
        fxFadeIn.update(delta);
        fxFadeOut.update(delta);
    }

    /**
     * Es posiciona l'area en el lloc correcte
     * @param xPos
     * @param yPos
     */
    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters i setters
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return heigth;
    }
}
