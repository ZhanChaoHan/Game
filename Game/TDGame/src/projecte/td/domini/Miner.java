package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerDinersAures;
import projecte.td.managers.ManagerPerfil;

/**
 * Classe Miner: Unitat Amiga encargada de treure diners o aures.
 * @author Ernest Daban i David Alvarez
 */
public class Miner extends UnitatAbstract implements IAuraRapidesa {

    private int cadencia;
    private int capacitat;
    private Timer timer;
    private Moneda moneda;//Objecte que extreurà la unitat
    private static ManagerDinersAures diners;//Manager encargat dels diners
    private String tipus;//Tipus miner(Miner,MagVida,MagRapidesa)
 
    private boolean haSonat;

    /**
     * Constructor classe Miner
     * @param vida
     * @param cadencia
     * @param capacitat
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param tipus
     * @param sound
     */
    public Miner(int vida, int cadencia, int capacitat, Image[] frames, Image[] framesMort,
            int milisegons, String tipus) {
        super(vida, frames, framesMort, milisegons);
        this.cadencia = cadencia;
        this.capacitat = capacitat;
        this.tipus = tipus;
        diners = ManagerContext.getDiners();
        activar();

    }

    /**
     * Dibuixa la unitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        g.drawAnimation(animation, posX, posY);
        if (moneda != null) {
            moneda.render(gc, g);
        }
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (moneda != null) {
            moneda.update();
            if (!haSonat) {
            
                haSonat = true;
            }
            if (moneda.isDesapareix()) {
                moneda = null;
            } else if (moneda.isActiu()) {
                if (tipus.equals("Miner")) {
                    diners.afegirDiners(capacitat);
                } else if (tipus.equals("MagVida") || tipus.equals("MagRapidesa")) {
                    diners.setTipusAuraEspera(tipus);
                }
                moneda = null;
            }
        }
    }

    /**
     * Crea i activa el timer
     */
    public void activar() {
        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moneda = new Moneda(ManagerContext.getGui(), (int) posX, (int) posY, tipus);
                haSonat = false;
            }
        });
        timer.start();
    }

    /**
     * Comrpova si pot equipar o no l'aura
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
     * Ajusta el timer de la unitat
     */
    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    /**
     * Getter auraActiva;
     * @return auraActiva
     */
    @Override
    public boolean isAuraActiva() {
        return auraActiva;
    }

    /**
     * Getter capacitat
     * @return capacitat
     */
    public int getCapacitat() {
        return capacitat;
    }

    /**
     * Setter Capacitat
     * @param capacitat
     */
    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
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
     * Para el timer
     */
    public void desactivarTimer() {
        if (timer != null) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
    }

}
