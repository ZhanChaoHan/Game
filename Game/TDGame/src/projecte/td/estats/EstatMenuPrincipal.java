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
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * Es mostra el Menu Principal on l'usuari pot escollir en quina opció del menu
 * entrar
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatMenuPrincipal extends BasicGameState {

    // Identificador del estat
    public static final int ID = 3;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per continuar jugant des de l'ultim punt on es va deixar el joc
    private BotoMenu botoNovaPartida;
    // Boto per accedir al menu opcions
    private BotoMenu botoOpcions;
    // Boto per accedir a l'estat canviar perfil per canviar el perfil actiu d'usuari
    private BotoMenu botoCanviarPerfil;
    // Boto per sortir del joc
    private BotoMenu botoSortir;
    // Boto per accedir a les opcions de musica
    private BotoMenu botoMusica;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsIn;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsOut;
    // ArrayList per moure tots els botons quan sigui necessari
    private ArrayList<BotoMenu> botons;
    // Comptador per realitzar moviment en els botons
    private int comptador;
    // Indica a quin estat s'ha de canviar
    private int canviAEstat;
    // Float per utilitzar en alpha bending
    private float transparencia;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Image amb el logotip del joc
    private Image imatgeTitol;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Imatge del boto Play
    private Image imatgeBotoPlay;
    // Imatge del boto Over Play
    private Image imatgeBotoOverPlay;
    // Imatge del text Jugar del boto
    private Image imatgeTextJugar;
    // Imatge del text Perfil del boto
    private Image imatgeTextPerfil;
    // Imatge del text dades
    private Image imatgeTextDades;
    // Imatge del text Sortir del boto
    private Image imatgeTextSortir;


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
        imatgeFons = ManagerRecursos.getImage("fonsMenuImage");
        imatgeTitol = ManagerRecursos.getImage("fonsTitolImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfilNormalImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeBotoPlay = ManagerRecursos.getImage("botoPlayImage");
        imatgeBotoOverPlay = ManagerRecursos.getImage("botoPlayOverImage");
        imatgeTextJugar = ManagerRecursos.getImage("textJugarImage");
        imatgeTextPerfil = ManagerRecursos.getImage("textPerfilImage");
        imatgeTextDades = ManagerRecursos.getImage("textDadesImage");
        imatgeTextSortir = ManagerRecursos.getImage("textSortirImage");
      
    }

    /**
     * Es reinicia el valor de les variables usades en aquest estat
     */
    private void reiniciarVariables() {
        botons = new ArrayList<BotoMenu>();
        canviAEstat = 0;
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu nova partida
        botoNovaPartida = new BotoMenu(container, imatgeBotoNormal, 150, 440);
        botoNovaPartida.setMouseOverImage(imatgeBotoOver);
        botoNovaPartida.setImageText(imatgeTextJugar);
 
        botons.add(botoNovaPartida);
        // BotoMenu menu opcions
        botoOpcions = new BotoMenu(container, imatgeBotoNormal, 600, 440);
        botoOpcions.setMouseOverImage(imatgeBotoOver);
        botoOpcions.setImageText(imatgeTextDades);
      
        botons.add(botoOpcions);
        // BotoMenu canvi de perfil
        botoCanviarPerfil = new BotoMenu(container, imatgeBotoNormal, 150, 585);
        botoCanviarPerfil.setMouseOverImage(imatgeBotoOver);
        botoCanviarPerfil.setImageText(imatgeTextPerfil);
    
        botons.add(botoCanviarPerfil);
        // BotoMenu Sortir del joc
        botoSortir = new BotoMenu(container, imatgeBotoNormal, 600, 585);
        botoSortir.setMouseOverImage(imatgeBotoOver);
        botoSortir.setImageText(imatgeTextSortir);
       
        botons.add(botoSortir);
        // BotoMenu Sortir del joc
        botoMusica = new BotoMenu(container, imatgeBotoPlay, 480, 510);
        botoMusica.setMouseOverImage(imatgeBotoOverPlay);

        botons.add(botoMusica);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoNovaPartida.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatSeguentWave.ID;
                }
            }
        });
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatDades.ID;
                }
            }
        });
        botoCanviarPerfil.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatPerfil.ID;
                }
            }
        });
        botoSortir.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    state.enterState(EstatSortir.ID);
                }
            }
        });
        botoMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    state.enterState(EstatMusica.ID);
                }
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
        // Si s'esta entrant a l'estat o se n'esta sortint es dura a terme
        // l'efecte de transparencia
        if (alphaBotonsIn) {
            comptador += 50;
            if (comptador % 100 == 0) {
                // Els botons es van tornant menys transparents
                transparencia += 0.05;
            }
            if (transparencia >= 1) {
                // Quan els botons ja no son transaprents s'activen
                ReproductorMusica.setVolumMusic();
                ReproductorMusica.last();
                botoNovaPartida.setActiu(true);
                botoOpcions.setActiu(true);
                botoCanviarPerfil.setActiu(true);
                botoSortir.setActiu(true);
                botoMusica.setActiu(true);
                botoMusica.setAcceptingInput(true);
                alphaBotonsIn = false;
                comptador = 0;
            }
        } else if (alphaBotonsOut) {
            // Quan els botons es tornen transparents es desactiven
            botoNovaPartida.setActiu(false);
            botoOpcions.setActiu(false);
            botoCanviarPerfil.setActiu(false);
            botoSortir.setActiu(false);
            botoMusica.setActiu(false);
            botoMusica.setAcceptingInput(false);
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia -= 0.05;
            }
            // Quan la transparencia arriba a 0 s'accedeix al seguent estat
            if (transparencia <= 0) {
                state.enterState(canviAEstat);
            }
        }
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
        imatgeTitol.draw(290,100);
        imatgeBotoNormal.setAlpha(transparencia);
        imatgeBotoOver.setAlpha(transparencia);
        imatgeBotoPlay.setAlpha(transparencia);
        imatgeBotoOverPlay.setAlpha(transparencia);
        imatgeTextJugar.setAlpha(transparencia);
        imatgeTextDades.setAlpha(transparencia);
        imatgeTextPerfil.setAlpha(transparencia);
        imatgeTextSortir.setAlpha(transparencia);
        botoNovaPartida.render(container, g);
        botoOpcions.render(container, g);
        botoCanviarPerfil.render(container, g);
        botoSortir.render(container, g);
        botoMusica.render(container, g);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        reiniciarVariables();
        crearBotonsMenuNormal();
        afegirListeners();
        alphaBotonsIn = true;

    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        alphaBotonsOut = false;
        imatgeBotoNormal.setAlpha(1f);
        imatgeBotoOver.setAlpha(1f);
        imatgeTextJugar.setAlpha(1f);
        imatgeTextDades.setAlpha(1f);
        imatgeTextPerfil.setAlpha(1f);
        imatgeTextSortir.setAlpha(1f);
        imatgeBotoPlay.setAlpha(1f);
        imatgeBotoOverPlay.setAlpha(1f);
        comptador = 0;
    }
}
