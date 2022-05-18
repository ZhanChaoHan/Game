package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe UnitatDispara: Unitats Amigues que efectuen dispars.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatDispara extends UnitatAbstract implements IAuraRapidesa {

    private int cadencia;
    private Projectil projectil;
    // Timer per controlar cada quants segons dispara la unitat
    private Timer timer;
    private boolean dispara = true;
    private boolean activat;
    private float posXProj;//CoordendaX posicio projectil
    private float posYProj;//CoordendaY posicio projectil


    /**
     * Constructor classe UnitatDispara
     * @param vida
     * @param cadencia
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param projectil
     * @param posXProj
     * @param posYProj
     * @param sound
     */
    public UnitatDispara(int vida, int cadencia, Image[] frames, Image[] framesMort, int milisegons,
            Projectil projectil, float posXProj, float posYProj) {
        super(vida, frames, framesMort, milisegons);
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
     * Canvia la posicio de la unitat
     * @param posX
     * @param posY
     */
    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX + animation.getWidth() + posXProj, posY + posYProj);

    }

    /**
     * Metode que especifica si pot equipar una aura o no
     * @param aura
     * @return
     */
    @Override
    public boolean potEquiparAura(Aura aura) {
        if (aura.getTipus().equals("MagRapidesa") || aura.getTipus().equals("MagVida")) {
            return true;
        }
        return false;
    }

    /**
     * Mètode emprat per a activar els dispars de la unitat
     */
    public void activarDispars() {
        timer.start();
        activat = true;
    }

    /**
     * Mètode per indicar que el dispar ha finalitzat
     */
    public void haDisparat() {
        dispara = false;
    }

    /**
     * Mètode emprat per a desactivar els dispars de la unitat
     */
    public void desactivarDispars() {
        activat = false;
        timer.stop();
    }

    /**
     * Getter activat
     * @return activat
     */
    public boolean estaActivat() {
        return activat;
    }

    /**
     * Getter disparant
     * @return
     */
    public boolean estaDisparant() {
        return dispara;
    }

    /**
     * Ajusta el timer
     */
    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    /**
     * Getter cadencia
     * @return cadencia
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
