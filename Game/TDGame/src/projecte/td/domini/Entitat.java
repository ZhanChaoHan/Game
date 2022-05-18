package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 * Classe Entitat.
 * @author Ernest Daban i David Alvarez
 */
public abstract class Entitat {

    float posX; //Coordenada X posicio
    float posY; //Coordenada Y posicio
    boolean mort;
    Shape shape; //Figura geomètrica que representa l'entitat

    public Entitat() {
    }

    /**
     * Updateja la entitat
     * @param delta
     */
    public abstract void update(int delta);

    /**
     * Dibuixa la entitat
     * @param gc GameContainer
     * @param g Graphics
     */
    public abstract void render(GameContainer gc, Graphics g);

    /**
     * Canvia la posició de la entitat
     * @param x coordendaX
     * @param y coordenadaY
     */
    public abstract void setLocation(float x, float y);

    /**
     * Metode que comprova si la Shape d'aquesta entitat, col·lisiona amb una altre
     * @param s Shape UnitatEnemiga
     * @return true si col·lisiona
     */
    public boolean collideWith(Shape s) {
        if (shape.intersects(s)) {
            return true;
        }
        return false;
    }

    /**
     * Getter mort
     * @return
     */
    public boolean isMort() {
        return mort;
    }

    /**
     * Setter mort
     * @param mort
     */
    public void setMort(boolean mort) {
        this.mort = mort;
    }

    /**
     * Getter posX
     * @return Posx
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Setter posY
     * @param posX
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Getter posY
     * @return posy
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Setter posY
     * @param posY
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Getter Shape
     * @return shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Setter shape
     * @param shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Getter Height
     * @return height
     */
    public float getHeight() {
        return shape.getHeight();
    }

    /**
     * Getter width
     * @return width
     */
    public float getWidth() {
        return shape.getWidth();
    }
}
