package projecte.td.estats;

import java.util.ArrayList;
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
import projecte.td.componentGUI.BotoSeleccio;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;
import projecte.td.utilitats.ReproductorMusica;

/**
 * Es mostren les unitats disponibles del jugador en el moment actual. L'usuari pot clicar
 * i entrar en un altre estat on es mostra informació de la unitat en questio.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatInfoUnitats extends BasicGameState {

    // Identificador del estat
    public static final int ID = 10;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoDades;
    // Wave actual
    private int wave;
    // Posicio X on es començara a col·locar el primer boto de tria d'unitat
    private int posXVariable;
    // Posicio Y on es començara a col·locar el primer boto de tria d'unitat
    private int posYVariable;
    // ArrayList on es guarden els botons de seleccio d'unitats
    private ArrayList<BotoSeleccio> botonsSeleccio;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Titol del estat
    private Image titolEstat;
    // Imatge del text del boto Tornar
    private Image textTornar;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;

    // Arxiu de Configuracio amb informació de la wave actual
    private ArxiuConfiguracio waves;
    // Unitats que es mostraran com a disponibles
    private String unitatsTriades;

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
        textTornar = ManagerRecursos.getImage("textTornarImage");
        titolEstat = ManagerRecursos.getImage("textUnitatsGranImage");


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
        // Es comprova si es clica algun dels botons
        for (BotoSeleccio bs : botonsSeleccio) {
            if (bs.isNotaCanvi()) {
                // Si s'ha clicat un boto s'informa al manager de perfil i s'accedeix
                // al seguent estat
                bs.setNotaCanvi(false);
                ManagerPerfil.setInformacioUnitat(bs.getUnitat());
                state.enterState(EstatMostraInfoUnitats.ID);
            }
        }
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
        titolEstat.draw(250, 130);
        botoDades.render(container, g);
        for (BotoSeleccio b : botonsSeleccio) {
            b.render(container, g);
        }
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        posXVariable = 290;
        posYVariable = 220;
        botonsSeleccio = new ArrayList<BotoSeleccio>();
        crearBotonsMenuNormal();
        afegirListeners();
        wave = ManagerPerfil.getWaveActual();
        waves = Configuracio.getWaves();
        unitatsTriades = waves.getPropietatString("unitatsDisponibles" + wave);
        crearBotons();
        posicionarBotons();
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar al menu principal
        botoDades = new BotoMenu(container, imatgeBotoNormal, 380, 570);
        botoDades.setMouseOverImage(imatgeBotoOver);
        botoDades.setImageText(textTornar);

        botoDades.setActiu(true);
    }

    /**
     * Crea els botons necessaris
     */
    private void crearBotons() {
        String[] s = unitatsTriades.split("-");
        BotoSeleccio.setImatgeCarta(ManagerRecursos.getImage("botoCartaImage"));
        BotoSeleccio.setImatgeCartaOver(ManagerRecursos.getImage("botoCartaOverImage"));
        for (String text : s) {
            BotoSeleccio bs = new BotoSeleccio(container, ManagerRecursos.getImage("carta" + text + "Image"),
                    0, 0, text);
            bs.addListener();
     
            bs.setActiu(true);
            botonsSeleccio.add(bs);
        }
    }

    /**
     * Posiciona els botons a la posicio que els pertoca
     */
    private void posicionarBotons() {
        int columnes = 0;
        int files = 0;
        for (BotoSeleccio b : botonsSeleccio) {
            int posicioBotoX = (columnes * 90) + posXVariable;
            int posicioBotoY = (files * 110) + posYVariable;
            b.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == 4) {
                columnes = 0;
                files++;
            } else {
                columnes++;
            }
        }
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoDades.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatDades.ID);
            }
        });
    }
}
