package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentIngame.MenuIngame;
import projecte.td.domini.Aura;
import projecte.td.domini.IAuraRapidesa;
import projecte.td.domini.IAuraVida;
import projecte.td.joc.Tauler;
import projecte.td.factories.FactoriaUnitatsEnemics;
import projecte.td.managers.ManagerRecursos;
import projecte.td.managers.ManagerDinersAures;
import projecte.td.domini.UnitatAbstract;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerEnemics;
import projecte.td.managers.ManagerPerfil;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat es duu a terme l'acció principal del joc. S'hi desenvolupen les
 * waves on l'usuari ha de defensar la posicio. Es desenvolupa en temps real.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatInGame extends BasicGameState {

    // Identificador del estat
    public static final int ID = 5;
    // Contenidor del joc
    private GameContainer gc;
    // Contenidor d'estats que s'usara per accedir als diferents estats del joc
    private StateBasedGame state;
    // Fons de pantalla
    private Image imatgeFons;
    // Tauler que gestiona les posicions de les unitats, enemics i projectils
    private Tauler p;
    // Menu que engloba els botons que s'usen en aquest estat
    private MenuIngame mi;
    // Gestiona els diners que te el jugador durant la wave
    private ManagerDinersAures md;
    // Indica quina unitat hi ha en recamara per ser col·locada en el tauler
    private String unitat = "null";

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
        this.gc = container;
        this.state = game;
        imatgeFons = ManagerRecursos.getImage("fonsIngameImage");
    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
        Input input = gc.getInput();
        // Es comprova si s'ha fet click amb el boto esquerra del ratoli en la zona superior
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && input.getMouseY() <= 600) {
            if (unitat.equals("pala")) {
                // Si hi ha la pala activada s'esborrara la unitat de la posicio del tauler
                // clicada
                p.borrarUnitatAmiguesClick(input.getMouseX(), input.getMouseY());
                unitat = "null";
                mi.realitzaTransaccio();
            } else if (!unitat.equals("null") && p.comprovaClickCorrecte(input.getMouseX(), input.getMouseY())) {
                // Si hi ha una unitat disponible i la posicio del tauler disponible
                // Es posiciona una unitat amiga en el tauler i es resta el cost al manager
                // de diners
                int[] posFC = p.mirarCoordenadesClick(input.getMouseX(), input.getMouseY());
                if (p.comprovarClick(posFC[0], posFC[1])) {
                    UnitatAbstract u = null;
                    u = FactoriaUnitatsEnemics.getUnitatBona(unitat);
                    p.posicionaUnitatAmiga(posFC[0], posFC[1], u);
                    unitat = "null";
                    mi.realitzaTransaccio();
                }
            }
        } // Si s'ha clicat el boto mig del ratoli
        else if (input.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)) {
            // Si hi ha una aura en espera es comprova si la posicio del tauler on es cliqui
            // conte una unitat que pot equipar l'aura en questio. Si la pot equipar es col·loca
            // l'aura en la unitat
            if (md.isAuraEnEspera()) {
                UnitatAbstract ua = p.getUnitatAmiga(input.getMouseX(), input.getMouseY());
                if (ua instanceof IAuraRapidesa && md.getTipusAuraEspera().equals("MagRapidesa")) {
                    Aura aura = new Aura(md.getTipusAuraEspera(),
                            ManagerRecursos.getImage("auraRapidesaImage"), 2);
                    if (ua.potEquiparAura(aura) && !ua.isAuraActiva()) {
                        ua.activarAura(aura);
                        md.clearAures();
                        ManagerPerfil.sumaAuraColocada();
                    }
                } else if (ua instanceof IAuraVida && md.getTipusAuraEspera().equals("MagVida")) {
                    Aura aura = new Aura(md.getTipusAuraEspera(),
                            ManagerRecursos.getImage("auraVidaImage"), 10);
                    if (ua.potEquiparAura(aura) && !ua.isAuraActiva()) {
                        ua.activarAura(aura);
                        md.clearAures();
                        ManagerPerfil.sumaAuraColocada();
                    }
                }
            }
        } // Si s'ha clicat amb el boto dret del ratoli, es des-seleccionen les unitats o elements
        // seleccionats 
        else if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
            mi.clear();
            mi.reiniciarBotons();
            unitat = "null";
            p.setDibuixarQuadrat(false);
        }
        // S'updateja el tauler
        p.update(delta);
        // S'updateja el menu ingame
        mi.update();
        // Si hi ha un element esperant aquest s'assigna a l'atribut corresponent d'aquesta classe
        if (mi.isEnEspera()) {
            unitat = mi.getElementEsperant();
            mi.clear();
        }
        // Si hi ha una unitat seleccionada i el ratoli esta en la zona superior, es dibuixen els quadrats
        // que indiquen si es pot posicionar una unitat en una posicio del tauler.
        if (!unitat.equals("null") && input.getMouseY() <= 600 && p.comprovaClickCorrecte(input.getMouseX(), input.getMouseY())) {
            p.setDibuixarQuadrat(true);
            p.setPosicioDibuixQuadrat(input.getMouseX(), input.getMouseY());
        } else {
            p.setDibuixarQuadrat(false);
        }
        // Si el manager d'enemics te un enemic esperant per entrar al joc aquest es posiciona al tauler
        if (ManagerEnemics.isEnEspera()) {
            UnitatAbstract ua = ManagerEnemics.getUnitat();
            int random = ManagerEnemics.triarCarril();
            p.posicionaUnitatEnemiga(1000, random, ua);
        }
        // Comporova si la partida ha finalitzat i l'usuari ha perdut
        if (p.observarPartidaFinalitzada()) {
            p.borrarTot();
            ManagerPerfil.sumaPerdudes();
            ManagerPerfil.guardarEstadistiques();
            ManagerEnemics.pararTimer();
            state.enterState(EstatPerd.ID);
        }
        // Comprova si la partida ha finalitzat i l'usuari ha guanyat
        if (ManagerEnemics.fidelaWave() && !p.enemicsEnTauler()) {
            p.borrarTot();
            ManagerPerfil.sumaGuanyada();
            ManagerPerfil.guardarEstadistiques();
            ManagerEnemics.pararTimer();
            state.enterState(EstatGuanya.ID);
        }
        // S'actualitza el reproductor de música
        ReproductorMusica.update(gc);
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
        imatgeFons.draw(0, 0);
        p.dibuixar(g, gc);
        mi.render(gc, g);
        if (ManagerEnemics.isMostraCartell()) {
            ManagerEnemics.renderCartell(gc, g);
        }
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        p = new Tauler(6, 10, 1024, 600);
        md = new ManagerDinersAures();
        mi = new MenuIngame(gc, 0, 600, ManagerRecursos.getImage("contenidorIngameImage"), ManagerPerfil.getUnitatsTriades(), md, state);
        mi.clear();
        mi.reiniciarBotons();
        ManagerContext.setDiners(md);
        ManagerEnemics.iniciarCompteEnrere();
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        mi = null;
    }
}
