package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat es mostren els botons a través dels quals s'accedeix a la biblioteca
 * d'informació del joc
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatDades extends BasicGameState {

    // Identificador del estat
    public static final int ID = 8;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per veure les estadistiques
    private BotoMenu botoEstadistiques;
    // Boto per accedir a la informacio de les unitats
    private BotoMenu botoUnitats;
    // Boto per accedir a la informacio dels enemics
    private BotoMenu botoEnemics;
    // Boto per tornar al menu principal
    private BotoMenu botoTornar;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Titol de l'estat
    private Image textTitol;
    // Text del boto Estadistiques
    private Image textEstadistiques;
    // Text del boto Unitats
    private Image textUnitats;
    // Text del boto Enemics
    private Image textEnemics;
    // Text del boto Tornar
    private Image textTornar;


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
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.state = game;
        this.container = container;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfilNormalImage");
        textTitol = ManagerRecursos.getImage("textDadesGranImage");
        textEnemics = ManagerRecursos.getImage("textEstadistiquesImage");
        textUnitats = ManagerRecursos.getImage("textUnitatsImage");
        textEnemics = ManagerRecursos.getImage("textEnemicsImage");
        textEstadistiques = ManagerRecursos.getImage("textEstadistiquesImage");
        textTornar = ManagerRecursos.getImage("textTornarImage");


    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        ReproductorMusica.update(container);
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        imatgeFons.draw(0, 0);
        textTitol.draw(248, 145);
        botoUnitats.render(container, g);
        botoEstadistiques.render(container, g);
        botoEnemics.render(container, g);
        botoTornar.render(container, g);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotonsMenuNormal();
        afegirListeners();
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar al menu principal
        botoEstadistiques = new BotoMenu(container, imatgeBotoNormal, 380, 270);
        botoEstadistiques.setMouseOverImage(imatgeBotoOver);
        botoEstadistiques.setImageText(textEstadistiques);
      
        botoEstadistiques.setActiu(true);
        // BotoMenu per seguir jugant en la següent Wave
        botoUnitats = new BotoMenu(container, imatgeBotoNormal, 380, 370);
        botoUnitats.setMouseOverImage(imatgeBotoOver);
        botoUnitats.setImageText(textUnitats);

        botoUnitats.setActiu(true);
        // BotoMenu tornar a jugar wave
        botoEnemics = new BotoMenu(container, imatgeBotoNormal, 380, 470);
        botoEnemics.setMouseOverImage(imatgeBotoOver);
        botoEnemics.setImageText(textEnemics);
    
        botoEnemics.setActiu(true);
        // BotoMenu per seguir jugant en la següent Wave
        botoTornar = new BotoMenu(container, imatgeBotoNormal, 380, 570);
        botoTornar.setMouseOverImage(imatgeBotoOver);
        botoTornar.setImageText(textTornar);

        botoTornar.setActiu(true);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoUnitats.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatInfoUnitats.ID);
            }
        });
        botoEstadistiques.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatEstadistiques.ID);
            }
        });
        botoEnemics.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatInfoEnemic.ID);
            }
        });
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }
}
