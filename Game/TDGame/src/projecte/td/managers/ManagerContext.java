package projecte.td.managers;

import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Aquest manager s'encarrega de servir tres objectes que s'han d'usar en diferents
 * estats
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerContext {

    // Context en el que es desenvolupa el joc
    private static GUIContext gui;
    // Objecte que s'utilitza per saltar d'un estat a un altre
    private static StateBasedGame state;
    // ManagerDinersAures amb la recaudacio de l'usuari
    private static ManagerDinersAures diners;

    // Getters i setters de la classe

    public static GUIContext getGui() {
        return gui;
    }

    public static void setGui(GUIContext gui) {
        ManagerContext.gui = gui;
    }

    public static StateBasedGame getState() {
        return state;
    }

    public static void setState(StateBasedGame state) {
        ManagerContext.state = state;
    }

    public static ManagerDinersAures getDiners() {
        return diners;
    }

    public static void setDiners(ManagerDinersAures diners) {
        ManagerContext.diners = diners;
    }
}
