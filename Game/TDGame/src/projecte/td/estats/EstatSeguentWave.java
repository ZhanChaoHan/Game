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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.componentGUI.MenuSeleccio;
import projecte.td.managers.ManagerEnemics;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * L'usuari pot escollir en aquest estat les unitats que usara quan jugui la seguent wave.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatSeguentWave extends BasicGameState {

    // Identificador del estat
    public static final int ID = 4;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als diferents estats del joc
    private StateBasedGame state;
    // Fons de pantalla
    private Image imatgeFons;
    // Posicio on es situa el fons de l'estat
    private int posYFons;
    // Image del boto X que s'utilitza per començar a jugar
    private Image imatgeBotoX;
    // Image del boto V que s'utilitza per començar a jugar.
    private Image imatgeBotoV;
    // Image del boto X Over que s'utilitza per començar a jugar
    private Image imatgeBotoXOver;
    // Image del boto V Over que s'utilitza per començar a jugar.
    private Image imatgeBotoVOver;
    // Objecte menu que comprova les unitats que es poden seleccionar i les que s'han seleccionat
    private MenuSeleccio ms;
    // Boto per accedir al seguent estat
    private BotoMenu botoContinuar;
    // Boto per tornar al menu principal
    private BotoMenu botoTornar;
    // Boto per canviar a una wave menys
    private BotoMenu botoEnrere;
    // Boto per canviar a una wave mes
    private BotoMenu botoEndavant;
    // Indica si s'ha apretat algun boto
    private boolean botoApretat;
    // Comptador que s'usa en el moviment del fons
    private int comptador;

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
        imatgeFons = ManagerRecursos.getImage("fonsSeguentWaveImage");
        imatgeBotoX = ManagerRecursos.getImage("botoXImage");
        imatgeBotoV = ManagerRecursos.getImage("botoVImage");
        imatgeBotoXOver = ManagerRecursos.getImage("botoXOverImage");
        imatgeBotoVOver = ManagerRecursos.getImage("botoVOverImage");

    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        ms = new MenuSeleccio(container, 0, 0);
        crearBotons();
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        ms = null;
        botoApretat = false;
        comptador = 0;
        posYFons = 0;
        botoContinuar.setLocation(660, 630);
        botoTornar.setLocation(800, 630);
    }

    /**
     * Crea els botons necessaris per continuar o tornar enrere
     */
    private void crearBotons() {
        botoContinuar = new BotoMenu(container, imatgeBotoV, 660, 630);
        botoContinuar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (ms.unitatsNoNull()) {
                    botoApretat = true;
                    ManagerEnemics.iniciaWave(ManagerPerfil.getWave());
                }
            }
        });
        botoContinuar.setMouseOverImage(imatgeBotoVOver);
   
        botoContinuar.setActiu(true);

        botoTornar = new BotoMenu(container, imatgeBotoX, 800, 630);
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID, new FadeOutTransition(), new FadeInTransition());
            }
        });
        botoTornar.setMouseOverImage(imatgeBotoXOver);
      
        botoTornar.setActiu(true);

        botoEnrere = new BotoMenu(container, ManagerRecursos.getImage("botoEnrereImage"), 676, 228);
        botoEnrere.setMouseOverImage(ManagerRecursos.getImage("botoEnrereOverImage"));
        botoEnrere.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (ManagerPerfil.potRestarWave()) {
                    ManagerPerfil.restaWaveActual();
                    ManagerPerfil.assignarPropietats();
                    state.enterState(ID);
                }
            }
        });

        botoEnrere.setActiu(true);

        botoEndavant = new BotoMenu(container, ManagerRecursos.getImage("botoEndavantImage"), 884, 228);
        botoEndavant.setMouseOverImage(ManagerRecursos.getImage("botoEndavantOverImage"));
        botoEndavant.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (ManagerPerfil.potSumarWave()) {
                    ManagerPerfil.sumaWaveActual();
                    ManagerPerfil.assignarPropietats();
                    state.enterState(ID);
                }
            }
        });

        botoEndavant.setActiu(true);
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
        ms.update(game, state, delta);
        // Si s'apreta el boto V (jugar wave) els botons es desactiven
        if (botoApretat) {
            if (botoContinuar.isActiu() || botoTornar.isActiu()) {
                botoContinuar.setActiu(false);
                botoTornar.setActiu(false);
                ms.silenciarBotons();
                botoContinuar.setActiu(false);
                botoTornar.setActiu(false);
                botoEndavant.setActiu(false);
                botoEnrere.setActiu(false);
            }
            // S'inicia el moviment del fons de l'estat per adaptar-lo al seguent
            comptador += 1;
            posYFons += 4;
            // Es posicionen els botons
            botoContinuar.setLocation(660, botoContinuar.getY() + 4);
            botoTornar.setLocation(800, botoTornar.getY() + 4);
            botoEndavant.setLocation(botoEndavant.getX(), botoEndavant.getY() + 4);
            botoEnrere.setLocation(botoEnrere.getX(), botoEnrere.getY() + 4);
            ms.moureBotons(4);
            if (comptador > 150) {
                // S'agafen les unitats que s'han escollit i s'accedeix al seguent estat
                ManagerPerfil.setUnitatsTriades(ms.agafarUnitats());
                state.enterState(5);
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
        imatgeFons.draw(0, posYFons);
        botoContinuar.render(container, g);
        botoTornar.render(container, g);
        botoEnrere.render(container, g);
        botoEndavant.render(container, g);
        ms.render(game, state, g);
    }
}
