package projecte.td.domini;

import org.newdawn.slick.Sound;

/**
 * Interficie Bomba: Utilitzada per totes les UnitatsAmigues que son Bombes.
 * @author Ernest Daban i David Alvarez
 */
public interface InterficieBomba {

    public void haDisparat();

    public boolean isDispara();

    public void setDispara(boolean dispara);

    public Projectil getProjectil();

    public void setProjectil(Projectil projectil);

}
