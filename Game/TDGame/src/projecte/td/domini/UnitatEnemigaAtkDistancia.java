package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe UnitatEnemigaAtkDistancia: Unitat Enemiga que dispara
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaAtkDistancia extends UnitatEnemiga {

    int cadencia;//Cadencia del timer
    protected ProjectilEstatic projectil;//Projectil que dispararà la unitat
    protected Timer timer;//Timer emprat per disparar
    protected boolean dispara = true;//Si dispara o no
    protected float posXProj;//CoordenadaX projectil
    protected float posYProj;//CoordenadaY projectil

    /**
     * Constructor de la classe
     * @param vida Vida de la unitat
     * @param frames Imatges Animacio camina
     * @param framesMort Imatges Animacio de la seva mort
     * @param milisegons Milisegons entre imatge i imatge de l'Animacio camina
     * @param frames2 Imatges Animacio ataca
     * @param velocitat  Velocitat de la unitat
     * @param milisegonsAtck Milisegons entre imatge i imatge de l'Animacio ataca
     * @param cadencia Cadencia del timer
     * @param projectil Projectil que dispararà la unitat
     * @param posXProj CoordenadaX projectil
     * @param posYProj CoordenadaY projectil
     */
    public UnitatEnemigaAtkDistancia(int vida, int cadencia, Image[] frames, Image[] framesMort, int milisegons, ProjectilEstatic projectil, Image[] frames2, double velocitat, int milisegonsAtck, float posXProj, float posYProj) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck);
        this.cadencia = cadencia;
        this.projectil = projectil;
        this.posXProj = posXProj;
        this.posYProj = posYProj;
        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!mort) {
                    dispara = true;
                }
            }
        });
    }

    /**
     * Setter per canviar la posició de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX - projectil.getAnimation().getWidth() + posXProj, posY + posYProj);
    }

    /**
     * Activa els dispars de la unitat
     */
    public void activarDispars() {
        timer.start();
        super.activat = true;
    }

    /**
     * La unitat a finalitzat el dispar
     */
    public void haDisparat() {
        dispara = false;
    }

    /**
     * Desactiva els dispars de la unitat
     */
    public void desactivarDispars() {
        activat = false;
        timer.stop();
    }

    /**
     * Getter estaDisparant
     * @return true si esta disparant
     */
    public boolean estaDisparant() {
        return dispara;
    }

    /**
     * Setter per ajustar la cadencia
     */
    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    /**
     * Getter cadencia
     * @return
     */
    public int getCadencia() {
        return cadencia;
    }

    /**
     * Setter cadencia
     * @param cadencia
     */
    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    /**
     * Getter projectil
     * @return projectil
     */
    public Projectil getProjectil() {
        return projectil.cloneProjectil();
    }
}
