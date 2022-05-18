package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe ProjectilMobil: Projectil amb moviment representat per una Imatge .
 * @author Ernest Daban i David Alvarez
 */
public class ProjectilMobil extends Projectil implements Cloneable {

    protected Image image;

    /**
     * Constructor classe ProjectilMobil
     * @param dany
     * @param image
     */
    public ProjectilMobil(double dany, Image image) {
        super(dany);
        this.image = image;
    }

    /**
     * Canvia la posicio del projectil
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        super.shape = new Rectangle(posX, posY, image.getWidth(), image.getHeight());
    }

    /**
     * Updateja el projectil
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (posX > 1024) {
            mort = true;
        } else {
            posX += 0.2 * delta;
            shape.setLocation(posX, posY);
        }
    }

    /**
     * Rederitza el projectil
     * @param gc
     * @param g
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawImage(image, posX, posY);
    }

    /**
     * Copia el projectil
     * @return projectil clonat
     */
    @Override
    public Object clone() {
        return new ProjectilMobil(dany, image);
    }

    /**
     * Getter projectil clonat
     * @return projectil
     */
    public Projectil cloneProjectil() {
        Projectil p = (Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    /**
     * Genera impacte
     */
    public void impacte() {
        this.mort = true;
    }
}
