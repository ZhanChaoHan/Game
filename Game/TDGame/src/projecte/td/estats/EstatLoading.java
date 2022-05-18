package projecte.td.estats;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat es carreguen els recursos tot mostrant un fons de pantalla i una barra progressiva
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatLoading extends BasicGameState {

    // Identificador de l'estat
    public static final int ID = 1;
    // Contenidor del joc
    private GameContainer game;
    // Imatge fons de pantalla
    private Image imatgeFons;
    // Imatge on es mostra el logotip del joc
    private Image imatgeTitol;
    // Posicio del titol en l'eix de les X
    private int posXTitol;
    // Posicio del titol en l'eix de les Y
    private int posYTitol;
    // Permet carregar els recursos en diferit (mes tard)
    private DeferredResource recurs;
    // Total de recursos a carregar
    private float total;
    // Recursos carregats fins ara
    private float carregat;
    // Variable comptador que s'utilitza per que el text aparegui i desaparegui
    private long parpadeig;
    // El text es començara mostrant al principi
    private boolean mostra = true;
    // Indica si s'esta realitzant l'efecte de moviment del titol (logotip)
    private boolean moviment;
    // Indica si s'ha finalitzat el moviment del titol
    private boolean fiMoviment;
    // Font que s'usa per renderitzar el text
    private Font font;
    // Color de la barra del loading
    private Color colorBarra = new Color(107 / 255f, 18 / 255f, 12 / 255f,
            255 / 255f);

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
        this.game = container;
        imatgeFons = ManagerRecursos.getImage("fonsMenuImage");
        imatgeTitol = ManagerRecursos.getImage("fonsTitolImage");
        font = ManagerRecursos.getFont("dejavuNormalFont");
        posXTitol = 290;
        posYTitol = 200;
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
        // Si els recursos carregats igualen els totals procedim a entrar al seguent estat
        // quan l'usuari premi el boto esquerra del ratoli
        if (carregat == total) {
            Input input = container.getInput();
            // S'inicia un efecte on es moura la posicio del titol
            if (moviment) {
                posYTitol -= 0.5;
                if (posYTitol <= 100) {
                    moviment = false;
                    fiMoviment = true;
                }
            } else if (fiMoviment) {
                // Si s'acaba de realitzar el moviment s'inicia el reproductor de música i s'accedeix
                // a l'estat seguent
                ReproductorMusica.init();
                ReproductorMusica.toggleRepeatAll();
                ReproductorMusica.toggleShuffle();
                game.enterState(EstatPerfil.ID);
            }
            // Quan estan tots els recursos carregats i es prem el boto esquerre del ratolí s'inicia
            // el moviment del titol
            if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                moviment = true;
            }
            // La variable parpadeig s'utilitza per realitzar l'efecte de pampallugues del text que
            // s'escriu una vegada s'han carregat tots els recursos
            if (parpadeig >= 700) {
                mostra = !mostra;
                parpadeig = 0;
            }
            parpadeig += delta;
        }

        // Si encara queden recursos per carregar es procedeix a la carrega dels mateixos
        // Es una zona de risc i s'ha de controlar amb el pertinent try catch
        if (LoadingList.get().getRemainingResources() > 0) {
            recurs = LoadingList.get().getNext();
            try {
                recurs.load();
                carregat = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
            } catch (Exception ioe) {
                throw new SlickException("S'ha produit un error al carrerar els recursos ...");
            }
        } else {
            carregat = total;
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
        imatgeFons.draw(0, 0, container.getWidth(), container.getHeight());
        imatgeTitol.draw(posXTitol, posYTitol);
        g.setColor(colorBarra);
        g.fillRect(270, 650, (carregat / total) * 490, 30);
        g.setColor(Color.white);
        g.drawRect(270, 650, 490, 30);
        g.setColor(Color.white);
        g.setFont(font);
        // Si s'han carregat tots els recursos es mostra un missatge indicatiu
        if (carregat == total) {
            if (mostra) {
                g.drawString("Fes click per jugar o prem Escape per sortir", 260, 610);
            }
        } else {
            g.drawString("Carregant. Espera siusplau ...", 360, 610);
        }
        g.setColor(Color.black);
    }

    /**
     * Override de KeyPressed, si ja s'han carregat tot els recursos i es prem la tecla escape
     * surt del joc
     * @param key
     * @param c
     */
    @Override
    public void keyPressed(int key, char c) {
        if (Input.KEY_ESCAPE == key && carregat == total) {
            game.exit();
        }
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        total = LoadingList.get().getTotalResources();
        moviment = false;
        fiMoviment = false;
        posXTitol = 290;
        posYTitol = 200;
    }
}
