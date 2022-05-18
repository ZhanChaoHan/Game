package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * Es pregunta a l'usuari si realment vol sortir de l'aplicació
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatSortir extends BasicGameState {

    // Identificador de l'estat
    public static final int ID = 15;
    // Contenidors del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per sortir de l'aplicacio
    private BotoMenu botoSi;
    // Boto per tornar al menu principal
    private BotoMenu botoNo;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoXNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoXOver;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoVNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoVOver;
    // Image del text "Vols sortir de l'aplicacio
    private Image textSortida;


    /**
     * BasicGameState ens obliga a implementar aquest metode
     * @return int amb l'ID de l'estat del joc
     */
    public int getID() {
        return ID;
    }

    /**
     * Aqui s'inicialitzen les variables necessaries per al correcte funcionament del estat
     * @param container
     * @param game
     * @throws SlickException
     */
    public void init(GameContainer container, StateBasedGame state) {
        this.container = container;
        this.state = state;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoXNormal = ManagerRecursos.getImage("botoXImage");
        imatgeBotoXOver = ManagerRecursos.getImage("botoXOverImage");
        imatgeBotoVNormal = ManagerRecursos.getImage("botoVImage");
        imatgeBotoVOver = ManagerRecursos.getImage("botoVOverImage");
        textSortida = ManagerRecursos.getImage("textSortidaImage");
        crearBotons();
        afegirListeners();
    }

    /**
     * Es creen els botons del estat
     */
    private void crearBotons() {
        // Crear els botons
        // BotoMenu Si, per quan es vol sortir del joc
        botoSi = new BotoMenu(container, imatgeBotoVNormal, 425, 400);
        botoSi.setMouseOverImage(imatgeBotoVOver);
       
        botoSi.setActiu(true);
        // BotoMenu No, per continuar jugant
        botoNo = new BotoMenu(container, imatgeBotoXNormal, 525, 400);
        botoNo.setMouseOverImage(imatgeBotoXOver);
    
        botoNo.setActiu(true);
    }

    /**
     * S'afegeixen els listeners als botons
     */
    private void afegirListeners() {
        // Listener BotoMenu Si
        botoSi.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                container.exit();
            }
        });
        // Listener BotoMenu No
        botoNo.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer game, StateBasedGame state, int delta) {
        ReproductorMusica.update(container);
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        imatgeFons.draw(0, 0);
        textSortida.draw(250, 280);
        botoSi.render(container, g);
        botoNo.render(container, g);
    }
}


