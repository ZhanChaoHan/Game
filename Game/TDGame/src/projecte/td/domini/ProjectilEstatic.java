package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe ProjectilEstatic: Projectil sense moviment representat per una Animació .
 * @author Ernest Daban i David Alvarez
 */
public class ProjectilEstatic extends Projectil implements Cloneable {

    Animation animation;
    Image[] frames;
    int milisegons;

    /**
     * Constructor classe ProjectilEstatic
     * @param dany
     * @param frames
     * @param milisegons
     */
    public ProjectilEstatic(double dany, Image[] frames, int milisegons) {
        super(dany);
        this.frames = frames;
        this.animation = new Animation(frames, milisegons);
        this.animation.setLooping(false);
        this.milisegons = milisegons;
    }

    /**
     * Canvia la posicio del projectil
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        super.shape = new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());
    }

    /**
     * Updateja el projectil
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (animation.isStopped()) {
            mort = true;
        }
    }

    /**
     * Copia el projectil
     * @return projectil clonat
     */
    @Override
    public Object clone() {
        return new ProjectilEstatic(dany, frames, milisegons);
    }

    /**
     * Getter projectil clonat
     * @return
     */
    public Projectil cloneProjectil() {
        Projectil p = (Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    /**
     * Rederitza el projectil
     * @param gc
     * @param g
     */
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
    }

    /**
     * Genera imapcte
     */
    public void impacte() {
    }

    /**
     * Getter animation
     * @return animation
     */
    public Animation getAnimation() {
        return animation;
    }
}
