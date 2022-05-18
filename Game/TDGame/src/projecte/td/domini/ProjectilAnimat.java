package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import projecte.td.managers.ManagerRecursos;

/**
 * Classe ProjectilAnimat: Projectil amb moviment representat per una Animació .
 * @author Ernest Daban i David Alvarez
 */
public class ProjectilAnimat extends Projectil implements Cloneable {

    private Animation animation;
    private int milisegons;

    /**
     * Constructor ProjectilAnimat
     * @param dany
     * @param frames
     * @param milisegons
     */
    public ProjectilAnimat(double dany, Image[] frames, int milisegons) {
        super(dany);
        this.milisegons = milisegons;
        this.animation = new Animation(frames, milisegons);
    }

    /**
     * Canvia la posicio del projectil
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.posX = posX;
        super.posY = posY;
        super.shape = new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());
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
            posX += 0.4 * delta;
            shape.setLocation(posX, posY);
        }
    }

    /**
     * Renderitza el projectil
     * @param gc
     * @param g
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
    }

    /**
     * Copia el projectil
     * @return
     */
    @Override
    public Object clone() {
        return new ProjectilAnimat(dany, ManagerRecursos.getImageArray("motoristaAnimation"), milisegons);
    }

    /**
     * Getter projectil clonat
     * @return
     */
    @Override
    public Projectil cloneProjectil() {
        Projectil p = (Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    /**
     * Efectua un impacte
     */
    @Override
    public void impacte() {
    }
}
