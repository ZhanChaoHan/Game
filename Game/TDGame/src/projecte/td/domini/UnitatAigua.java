package projecte.td.domini;

import org.newdawn.slick.Image;

/**
 * Classe UnitatAigua: Unitats Amiga utilitzada per acabar amb les Boles de Neu.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatAigua extends UnitatAbstract {

    /**
     * Constructor classe UnitatAigua
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     */
    public UnitatAigua(int vida, Image[] frames, Image[] framesMort, int milisegons) {
        super(vida, frames, framesMort, milisegons);
    }
}
