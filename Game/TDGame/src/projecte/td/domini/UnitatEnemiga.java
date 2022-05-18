package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe UnitatEnemiga
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemiga extends UnitatAbstract {

    protected boolean activat; //Si colisiona, estarà activat
    protected Animation atck; //Animacio de l'atac de la unitat
    protected double velocitat; //Velocitat de la unitat
    protected boolean haArribat;

    /**
     * Constructor Unitat Enemiga
     * @param vida Vida de la unitat
     * @param frames Imatges Animacio camina
     * @param framesMort Imatges Animacio de la seva mort
     * @param milisegons Milisegons entre imatge i imatge de l'Animacio camina
     * @param frames2 Imatges Animacio ataca
     * @param velocitat  Velocitat de la unitat
     * @param milisegonsAtck Milisegons entre imatge i imatge de l'Animacio ataca
     */
    public UnitatEnemiga(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck) {
        super(vida, frames, framesMort, milisegons);
        atck = new Animation(frames2, milisegonsAtck);
        this.velocitat = velocitat;
    }

    /**
     * Constructor Unitat Enemiga
     * @param vida Vida de la unitat
     * @param frames Imatges Animacio camina
     * @param framesMort Imatges Animacio de la seva mort
     * @param milisegons Milisegons entre imatge i imatge de l'Animacio camina
     * @param velocitat  Velocitat de la unitat
     */
    public UnitatEnemiga(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat) {
        super(vida, frames, framesMort, milisegons);
        this.velocitat = velocitat;
    }

    /**
     * Setter per canviar la posició de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
    }

    /**
     * Getter actvat
     * @return true si esta activat
     */
    public boolean estaActivat() {
        return activat;
    }

    /**
     * Fa update de la unitat
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
            g.drawAnimation(atck, posX, posY);

        } else {
            g.drawAnimation(animation, posX, posY);
        }
    }

    /**
     * Dibuixa l'animació de la seva mort
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void renderMort(GameContainer gc, Graphics g) {
        if (mort && animation_mort != null) {
            g.drawAnimation(animation_mort, (posX + shape.getWidth() / 2) - animation_mort.getWidth(), posY);
            animation_mort.setLooping(false);
        }
    }

    /**
     * Getter arribat
     * @return true si ha arribat al final del tauler
     */
    public boolean isHaArribat() {
        return haArribat;
    }

}
