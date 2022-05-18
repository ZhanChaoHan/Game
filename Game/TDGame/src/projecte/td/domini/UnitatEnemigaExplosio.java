package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import projecte.td.managers.ManagerPerfil;

/**
 * Classe UnitatEnemigaExplosio: Unitat enemiga que explosiona quan té contacte amb una unitat Amiga.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaExplosio extends UnitatEnemigaAtkNormal {

    boolean cremant;
    Timer timer;

    /**
     * Constructor UnitatEnemigaExplosio
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param frames2
     * @param velocitat
     * @param milisegonsAtck
     * @param dany
     */
    public UnitatEnemigaExplosio(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck, double dany) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck, dany);
        timer = new Timer(6000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mort = true;
                timer.stop();
            }
        });
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
        posX -= velocitat * delta;
        setLocation(posX, posY);
        //Si esta cremant, no està mort i el timer no s'ha iniciat; inicia el timer.
        if (cremant && !timer.isRunning() && !mort) {
            timer.start();
        }
    }

    /**
     * Dibuixa la unitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat || cremant) {
            cremant = true;
            g.drawAnimation(atck, posX, posY + animation.getHeight() - atck.getHeight());
            if(!soAcabat){
                soAcabat=true;
            }

        } else {
            g.drawAnimation(animation, posX, posY);
        }
    }
}
