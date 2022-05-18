package projecte.td.domini;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import projecte.td.managers.ManagerPerfil;

/**
 * Classe UnitatEnemigaInvisible: Unitat enemiga que es invisible fins que colisiona amb una unitat amiga.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaInvisible extends UnitatEnemigaAtkNormal {

    private boolean invisible = true;
    private boolean apareix = true;
    private Animation animation2;
    private Animation ainvisible;

    /**
     * Constructor UnitatEnemigaInvisible
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param frames2
     * @param velocitat
     * @param milisegonsAtck
     * @param dany
     * @param frames3
     */
    public UnitatEnemigaInvisible(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck, double dany, Image[] frames3, Image[] frames4, int milisegons3, int milisegons4) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck, dany);
        animation2 = new Animation(frames3, milisegons3);
        ainvisible = new Animation(frames4, milisegons4);
    }

    /**
     * Canvia la shape
     */
    public void canviShape() {
        shape = new Rectangle(posX + 48, posY + 12, animation.getWidth() - 48, animation.getHeight() - 12);
    }

    /**
     * Torna la shape a la seva posicio
     */
    public void segonCanviShape() {
        super.setLocation(posX, posY);
    }

    /**
     * Dibuixa l aunitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        //Si es invisible no mostra res
        if (!invisible) {
            if (apareix && activat) {
                //Mostra quan apareix i para l'animacio
                g.drawAnimation(atck, (posX + (shape.getWidth()) - atck.getWidth()), posY + shape.getHeight() - atck.getHeight());
                atck.stopAt(31);
            } else if (!apareix && activat) {
                renderVida(gc, g);
                g.drawAnimation(animation, posX, posY);
                if (!animation.isStopped() && !soAcabat &&animation.getFrame()==0) {
                    
                    soAcabat = true;
                }
            } else if (!activat) {
                renderVida(gc, g);
                g.drawAnimation(animation2, posX, posY);
            }
        } else {
            g.drawAnimation(ainvisible, posX + shape.getWidth() / 2, posY);
        }
        if(animation.getFrame()==animation.getFrameCount()-1){
            soAcabat=false;
        }
    }

    /**
     * Actualitza la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (posX <= 0 - getWidth() / 2) {
            haArribat = true;
        }
        if (!activat) {
            posX -= velocitat * delta;
            setLocation(posX, posY);
        }
        if (activat) {
            invisible = false;
            if (atck.isStopped()) {
                apareix = false;
            }
        }
    }
    /**
     * Getter dany
     * @return
     */
    @Override
    public double getDany() {
        if (!mort && !invisible && !apareix) {
            return dany;
        } else {
            return 0;
        }
    }
    /**
     * Genera un impacte sobre la unitat
     * @param dany
     */
    @Override
    public void impacte(double dany) {
        if (!invisible && !apareix) {
            super.impacte(dany);
        }
    }
    /**
     * Getter invisible
     * @return
     */
    public boolean isInvisible() {
        return invisible;
    }
}
