package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import projecte.td.managers.ManagerPerfil;

/**
 * Classe UnitatEnemigaAtkNormal: Unitat enemiga que ataca cos a cos.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaAtkNormal extends UnitatEnemiga {

    double dany;//Dany de l'atac
    protected boolean soAcabat;

    /**
     * Constructor UnitatEnemigaAtkNormal
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param frames2
     * @param velocitat
     * @param milisegonsAtck
     * @param dany
     */
    public UnitatEnemigaAtkNormal(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck, double dany) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck);
        this.dany = dany;
    }

    /**
     * Constructor UnitatEnemigaAtkNormal
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param velocitat
     * @param dany
     */
    public UnitatEnemigaAtkNormal(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat, double dany) {
        super(vida, frames, framesMort, milisegons, velocitat);
        this.dany = dany;
    }

    /**
     * Getter activat
     * @return true si està activat
     */
    public boolean isActivat() {
        return activat;
    }

    /**
     * Setter activat
     * @param activat
     */
    public void setActivat(boolean activat) {
        this.activat = activat;
    }

    /**
     * Getter dany
     * @return dany
     */
    public double getDany() {
        if (!mort) {
            return dany;
        } else {
            return 0;
        }
    }

    /**
     * Dibuixa l'unitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat) {
            float x = getPosX() - (atck.getWidth() - getWidth());
            g.drawAnimation(atck, x, posY);
            if (!atck.isStopped() && !soAcabat && atck.getFrame() == 0) {
               
                soAcabat = true;
            }

        } else {
            g.drawAnimation(animation, posX, posY);
        }
        if(atck.getFrame()==atck.getFrameCount()-1){
            soAcabat=false;
        }
    }
}
