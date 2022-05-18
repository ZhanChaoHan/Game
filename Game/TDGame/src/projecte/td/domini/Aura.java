package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Image;

/**
 * Classe Aura: Aures per a col·locar a les unitats amigues.
 * @author Ernest Daban i David Alvarez
 */
public class Aura {

    private String tipus;
    private Image image;
    private int capacitat;
    UnitatAbstract bo;//Unitat on es col·locarà l'aura
    Timer timer;

    /**
     * Constructor Aura
     * @param tipus
     * @param image
     * @param capacitat
     */
    public Aura(String tipus, Image image, int capacitat) {
        this.tipus = tipus;
        this.image = image;
        this.capacitat = capacitat;
    }

    /**
     * Actva l'aura
     */
    public void activarAura() {
        if (tipus.equals("MagRapidesa")) {
            if (bo instanceof Miner) {
                Miner miner = (Miner) bo;
                miner.setCadencia(miner.getCadencia() / capacitat);
                miner.ajustarTimer();
            } else if (bo instanceof UnitatDispara) {
                UnitatDispara ud = (UnitatDispara) bo;
                ud.setCadencia(ud.getCadencia() / capacitat);
                ud.ajustarTimer();
            }
        } else if (tipus.equals("MagVida")) {
            timer = new Timer(500, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (bo.getVida() <= bo.getVidaTotal() + 1) {
                        bo.setVida(bo.getVida() + 1);
                    }
                }
            });
            timer.start();
        }
    }

    /**
     * Desactiva l'aura
     */
    public void desactivarAura() {
        if (tipus.equals("MagRapidesa")) {
            //bo.setCadencia(bo.getCadencia() * capacitat);
        } else if (tipus.equals("MagVida")) {
            timer.stop();
            timer = null;
        }
    }

    /**
     * Getter Unitat Amiga
     * @return bo
     */
    public UnitatAbstract getBo() {
        return bo;
    }

    /**
     * Setter Unitat Amiga
     * @param bo
     */
    public void setBo(UnitatAbstract bo) {
        this.bo = bo;
    }

    /**
     * Getter Image
     * @return IMage
     */
    public Image getImage() {
        return image;
    }

    /**
     * Setter Image
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Getter tipus
     * @return tipus
     */
    public String getTipus() {
        return tipus;
    }

    /**
     * Setter tipus
     * @param tipus
     */
    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
