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
 * En aquest estat s'informa a l'usuari que ha perdut la partida i es dona l'opció de rejugar la wave
 * o tornar al menu principal
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatPerd extends BasicGameState {

    // Identificador del estat
    public static final int ID = 6;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoReiniciarWave;
    // Boto per tornar al menu principal
    private BotoMenu botoMenuPrincipal;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Titol del estat
    private Image titolEstat;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Image del boto repetir
    private Image textRepetir;
    // Imatge del text sortir
    private Image textSortir;


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
        titolEstat = ManagerRecursos.getImage("textPerdreImage");
        textRepetir = ManagerRecursos.getImage("textRepetirImage");
        textSortir = ManagerRecursos.getImage("textSortirImage");

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
        titolEstat.draw(250, 150);
        botoMenuPrincipal.render(container, g);
        botoReiniciarWave.render(container, g);
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
        // BotoMenu tornar a jugar wave
        botoReiniciarWave = new BotoMenu(container, imatgeBotoNormal, 380, 350);
        botoReiniciarWave.setMouseOverImage(imatgeBotoOver);
        botoReiniciarWave.setImageText(textRepetir);
       
        botoReiniciarWave.setActiu(true);
        // BotoMenu tornar al menu principal
        botoMenuPrincipal = new BotoMenu(container, imatgeBotoNormal, 380, 490);
        botoMenuPrincipal.setMouseOverImage(imatgeBotoOver);
        botoMenuPrincipal.setImageText(textSortir);

        botoMenuPrincipal.setActiu(true);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoReiniciarWave.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatSeguentWave.ID);
            }
        });
        botoMenuPrincipal.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }
}
