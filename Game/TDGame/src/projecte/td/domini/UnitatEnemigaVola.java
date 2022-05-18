package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import projecte.td.managers.ManagerPerfil;

/**
 * Classe UnitatEnemigaVola: Unitat enemiga que apareix volant al tauler.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaVola extends UnitatEnemigaAtkNormal {

    private float posYFinal;
    private boolean inici;
    private float posXVol;
    private int contadorSo;

    /**
     * Constructor UnitatEnemigaVola
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param velocitat
     * @param dany
     * @param soAtck
     */
    public UnitatEnemigaVola(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat, double dany) {
        super(vida, frames, framesMort, milisegons, velocitat, dany);
        calcularVol();

    }

    /**
     * Calcula la posició on volarà
     */
    public void calcularVol() {
        posXVol = (int) (Math.random() * 600) + 400;
    }

    /**
     * Canvia la posicio de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        if (!inici) {
            posYFinal = posY;
            inici = true;
            super.setLocation(posXVol, 0);
        } else {
            super.setLocation(posXVol, posY);
        }


    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (posX <= 0 - getWidth() / 2) {
            haArribat = true;
        }
        if (!activat) {
            if (posYFinal > posY) {
                posY += velocitat * delta;
                setLocation(posXVol, posY);
            } else if (posYFinal <= posY) {
                posXVol -= velocitat * delta;
                setLocation(posXVol, posY);
            }
        }
    }

    /**
     * Renderitza la unitat
     * @param gc
     * @param g
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        g.drawAnimation(animation, posX, posY);
        if (activat) {
            if (!animation.isStopped() && !soAcabat && animation.getFrame() == 0) {
              
                soAcabat = true;
            }

        }
        if (animation.getFrame() == animation.getFrameCount() - 1 && contadorSo % 20 == 0) {
            soAcabat = false;

        }
        contadorSo++;
    }
}
