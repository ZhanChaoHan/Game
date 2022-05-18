package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import projecte.td.fx.ImageFxFadeInOut;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerRecursos;

/**
 * En aquest estat es mostra una petita intro
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatIntro extends BasicGameState {

    // Identificador del estat
    public static final int ID = 0;
    // Imatge del logo
    private Image imatgeIntro;
    // Imatge del logo
    private Image imatgeIntro2;
    // Efecte que s'usara per fer els canvis d'estat
    private ImageFxFadeInOut fxFadeInOutLogo;
    // Efecte que s'usara per fer els canvis d'estat
    private ImageFxFadeInOut fxFadeInOutLogo2;
    // Variable comptador que fara de "timer"
    private int comptador = 0;
    // Limit al que podra arribar la variable comptador
    private int limitComptador = 12000;

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
        ManagerContext.setGui(container);
        ManagerContext.setState(game);
        imatgeIntro = ManagerRecursos.getImage("fonsIntroImage");
        imatgeIntro2 = ManagerRecursos.getImage("fonsIntro2Image");
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
        fxFadeInOutLogo.update(delta);
        fxFadeInOutLogo2.update(delta);
        comptador += delta;
        // Si el comptador supera el limit o es prem la tecla Escape es procedira a carregar el seguent estat
        // en aquest cas, Estat Loading on es carregaran els recursos necessaris
        if (comptador > limitComptador || container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
            game.enterState(EstatLoading.ID, new FadeOutTransition(), new FadeInTransition());
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
        fxFadeInOutLogo.render(container, g);
        fxFadeInOutLogo2.render(container, g);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        fxFadeInOutLogo = new ImageFxFadeInOut(gc, imatgeIntro, 0, 0, 48, 51, 0, 4000);
        fxFadeInOutLogo2 = new ImageFxFadeInOut(gc, imatgeIntro2, 0, 0, 48, 51, 5000, 10000);
    }
}

