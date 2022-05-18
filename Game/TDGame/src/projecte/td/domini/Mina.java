package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe Mina: Unitat Amiga Mina.
 * @author Ernest Daban i David Alvarez
 */
public class Mina extends UnitatAbstract implements InterficieBomba {

    private Projectil projectil;//Projectil mina
    private boolean dispara;
    private Timer timer;//Timer temps activar
    private boolean activa;

    /**
     * Constructor mina
     * @param vida
     * @param frames
     * @param milisegons
     * @param projectil
     * @param sound
     */
    public Mina(int vida, Image[] frames, int milisegons, Projectil projectil) {
        super(vida, frames, milisegons);
        this.projectil = projectil;
        timer = new Timer(5000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                activa = true;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Canvia la posicio de la mina
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(0, 0);
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
    }

    /**
     * Efectua un impacte
     * @param dany
     */
    @Override
    public void impacte(double dany) {
        if (activa) {
            dispara = true;
            projectil.setLocation(posX - ((projectil.getWidth() - shape.getWidth()) / 2), posY + shape.getHeight() - projectil.getHeight() + 5);
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
        if (!activa) {
            Image image = animation.getImage(0);
            g.drawImage(image, posX, posY);
        } else {
            g.drawAnimation(animation, posX, posY);
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
