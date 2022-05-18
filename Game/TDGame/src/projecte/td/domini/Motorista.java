package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe Motorista: UnitatAmiga que al primer contacte, surt disparada.
 * @author Ernest Daban i David Alvarez
 */
public class Motorista extends UnitatAbstract implements InterficieBomba {

    private Projectil projectil;//Projectil a disparar
    private boolean dispara;

    /**
     * Construcot classe Moneda
     * @param vida
     * @param frames
     * @param milisegons
     * @param projectil
     * @param sound
     */
    public Motorista(int vida, Image[] frames, int milisegons, Projectil projectil) {
        super(vida, frames, milisegons);
        this.projectil = projectil;
    }

    /**
     * Canvia la posicio de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX, posY);
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
    }

    /**
     * Efectua un impacte sobre la unitat
     * @param dany
     */
    @Override
    public void impacte(double dany) {
        vida -= 1;
        dispara = true;
    }

    /**
     * Renderitza la unitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        g.drawAnimation(animation, posX, posY);
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
        return projectil.cloneProjectil();
    }

    /**
     * Setter projectil
     * @param projectil
     */
    public void setProjectil(Projectil projectil) {
        this.projectil = projectil.cloneProjectil();
    }

}
