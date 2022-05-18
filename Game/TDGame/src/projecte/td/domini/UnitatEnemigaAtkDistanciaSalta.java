package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe UnitatEnemigaAtkDistanciaSalta: Unitat enemiga que salta i dispara
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaAtkDistanciaSalta extends UnitatEnemigaAtkDistancia {

    private boolean salta;//Esta saltant o no
    private boolean haRebutImpacte; //Ha rebut impacte
    private int ySalt;//Posicio y del salt
    private Animation salt;//Animacio del salt
    private boolean primeraMort;//true si ha "mort" 1 cop

    /**
     * Constructor UnitatEnemigaAtkDistanciaSalta
     * @param vida
     * @param cadencia
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param projectil
     * @param frames2
     * @param velocitat
     * @param milisegonsAtck
     * @param posXProj
     * @param posYProj
     * @param frames3
     */
    public UnitatEnemigaAtkDistanciaSalta(int vida, int cadencia, Image[] frames, Image[] framesMort, int milisegons, ProjectilEstatic projectil, Image[] frames2, double velocitat, int milisegonsAtck, float posXProj, float posYProj, Image[] frames3) {
        super(vida, cadencia, frames, framesMort, milisegons, projectil, frames2, velocitat, milisegonsAtck, posXProj, posYProj);
        salt = new Animation(frames3, 100);
    }

    /**
     * Impacte sobre la unitat
     * @param dany
     */
    @Override
    public void impacte(double dany) {
        super.impacte(dany);
        //Observa el primer impacte
        if (!haRebutImpacte) {
            haRebutImpacte = true;
            salta = true;
        }
    }

    /**
     * Ha saltat
     */
    public void haSaltat() {
        salta = false;
    }

    /**
     * Getter salta
     * @return
     */
    public boolean isSaltant() {
        return salta;
    }

    /**
     * Calcula la coordenada y del salt aleatoriament
     * @param llargadaTotal
     */
    public void calculaSalt(int llargadaTotal) {
        ySalt = (int) (Math.random() * llargadaTotal);
    }

    /**
     * Getter coordenadaY salt
     * @return
     */
    public int getySalt() {
        return ySalt;
    }

    /**
     * Dibuixa la unitat
     * @param gc GameContainer
     * @param g Graphics
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        //si està activat dibuixa l'animacio del seu atac
        if (activat) {
            g.drawAnimation(atck, posX, posY);
        } //si està saltant, mostra l'animació del salt
        else if (salta) {
            g.drawAnimation(salt, posX, posY);
            salt.setLooping(false);
        } //si no està ni activat, ni saltant, mostra l'animació normal
        else {
            g.drawAnimation(animation, posX, posY);
        }
    }

    /**
     * Observa si ha finalitzat l'animació del salt
     * @return
     */
    public boolean haFinalitzatAnimacio() {
        return salt.isStopped();
    }

    /**
     * Observa si la unitat esta morta
     * @return
     */
    @Override
    public boolean isMort() {
        //Primera mort, avans d'efectuar el salt
        if (!primeraMort && mort) {
            primeraMort = true;
            return true;
        } //Segona mort
        else {
            if (animation_mort != null) {
                if (mort && animation_mort.isStopped()) {
                    return true;
                }
            } else {
                if (mort) {
                    return true;
                }
            }
            return false;
        }
    }
}
