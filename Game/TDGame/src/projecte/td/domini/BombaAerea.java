package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe BombaAerea: Unitat Amiga Bomba caiguda del cel.
 * @author Ernest Daban i David Alvarez
 */
public class BombaAerea extends UnitatAbstract implements InterficieBomba {

    private float posicioYExplosio;//Posicio final explocio
    private Projectil projectil;//Projectil a disparar
    private boolean dispara;

    /**
     * Constructor BombaAerea
     * @param vida
     * @param frames
     * @param milisegons
     * @param projectil
     * @param sound
     */
    public BombaAerea(int vida, Image[] frames, int milisegons, Projectil projectil) {
        super(vida, frames, milisegons);
        this.projectil = projectil;
    }

    /**
     * Canvia a posicio de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        posicioYExplosio = posY;
        super.setLocation(posX, -20);
        projectil.setLocation(0, 0);
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        float y = super.getPosY();
        y += 4;
        super.setLocation(posX, y);
        //Quan arribi al final, explotarà
        if (posY >= posicioYExplosio) {
            projectil.setLocation(posX - ((projectil.getWidth() - shape.getWidth()) / 2), posicioYExplosio + shape.getHeight() - projectil.getHeight() + 5);
            dispara = true;
        }
    }

    /**
     * Efectua impacte
     * @param dany
     */
    @Override
    public void impacte(double dany) {
    }

    /**
     * Dibuixa la unitat
     * @param gc
     * @param g
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);

    }

    /**
     * Dispar finalitzat
     */
    public void haDisparat() {
        dispara = false;
        mort = true;
    }

    /**
     * Getter dispara
     * @return
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
     * @return
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
