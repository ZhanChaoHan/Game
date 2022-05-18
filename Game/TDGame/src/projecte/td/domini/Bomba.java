package projecte.td.domini;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe Bomba: Unitat Amiga Bomba Terrestre.
 * @author Ernest Daban i David Alvarez
 */
public class Bomba extends UnitatAbstract implements InterficieBomba {

    private Projectil projectil;//Projectil a disparar
    private boolean dispara;

    /**
     * Constructor bomba
     * @param vida
     * @param frames
     * @param milisegons
     * @param projectil
     * @param sound
     */
    public Bomba(int vida, Image[] frames, int milisegons, Projectil projectil) {
        super(vida, frames, milisegons);
        animation.setLooping(false);
        this.projectil = projectil;
    }

    /**
     * Canvia la posicio
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(0, posY);
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (animation.getFrame() < 9) {
            posX += 1;
        } else if (animation.isStopped()) {
            dispara = true;
        }
    }

    /**
     * Ha finalitzat el dispar
     */
    public void haDisparat() {
        dispara = false;
        mort = true;
    }

    /**
     * Getter dispara
     * @return dispara
     */
    public boolean isDispara() {
        return dispara;
    }

    /**
     * Setter dispara
     * @param dispara
     */
    public void setDispara(boolean dispara) {
        this.dispara = dispara;
    }

    /**
     * Getter projectil
     * @return projectil
     */
    public Projectil getProjectil() {
        return projectil;
    }

    /**
     * Setter projectil
     * @param projectil
     */
    public void setProjectil(Projectil projectil) {
        this.projectil = projectil.cloneProjectil();
    }

}
