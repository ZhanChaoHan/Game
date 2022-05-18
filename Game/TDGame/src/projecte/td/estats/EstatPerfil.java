package projecte.td.estats;

import org.newdawn.slick.Font;
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
import projecte.td.utilitats.Configuracio;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat s'escollira el perfil d'usuari que es vol utilitzar
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class EstatPerfil extends BasicGameState {

    // Identificador del BasicGameState
    public static final int ID = 2;
    // Contenidor del joc
    private GameContainer container;
    // Estat on es podra controlar l'acces als altres estats
    private StateBasedGame state;
    // Botons per triar el perfil corresponent
    // Boto Perfil1
    private BotoMenu botoPerfil1;
    // Boto Borrar Perfil 1
    private BotoMenu botoBorrar1;
    // Boto Perfil2
    private BotoMenu botoPerfil2;
    // Boto Borrar Perfil 2
    private BotoMenu botoBorrar2;
    // Boto Perfil3
    private BotoMenu botoPerfil3;
    // Boto Borrar Perfil 3
    private BotoMenu botoBorrar3;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsIn;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia 
    private boolean alphaBotonsOut;
    // Indica si els botons es poden renderitzar
    private boolean carregat;
    // Float per utilitzar en alpha bending
    private float transparencia;
    // Comptador per realitzar moviment en els botons
    private int comptador;
    // Imatge fons de pantalla
    private Image imatgeFons;
    // Imatge on es mostra el logotip del joc
    private Image imatgeTitol;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Image del boto per esborra perfils
    private Image imatgeBotoBorrar;
    // Image del boto per esborrar perfils over
    private Image imatgeBotoBorrarOver;
    // Image del text perfil 1
    private Image imatgeTextPerfil1;
    // Image del text perfil 2
    private Image imatgeTextPerfil2;
    // Image del text perfil 3
    private Image imatgeTextPerfil3;
    // ArxiuConfiguracio per agafar informació sobre els perfils
    private ArxiuConfiguracio perfils;
    // Font que s'usa per renderitzar el text
    private Font font;
    // String que s'utilitza per veure si s'ha reiniciat un perfil
    private String reiniciarPerfil;
    // S'utilitza per comprovar si un perfil sa reiniciat
    private int comptadorReiniciarPerfil;

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
        imatgeBotoBorrar = ManagerRecursos.getImage("botoXImage");
        imatgeBotoBorrarOver = ManagerRecursos.getImage("botoXOverImage");
        imatgeTextPerfil1 = ManagerRecursos.getImage("textPerfil1Image");
        imatgeTextPerfil2 = ManagerRecursos.getImage("textPerfil2Image");
        imatgeTextPerfil3 = ManagerRecursos.getImage("textPerfil3Image");
        font = ManagerRecursos.getFont("dejavuNormalFont");

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
            // Quan els botons ja no son transaprents s'activen
            if (transparencia >= 1) {
                botoPerfil1.setActiu(true);
                botoPerfil2.setActiu(true);
                botoPerfil3.setActiu(true);
                botoBorrar1.setActiu(true);
                botoBorrar2.setActiu(true);
                botoBorrar3.setActiu(true);
                alphaBotonsIn = false;
                comptador = 0;
            }
        } else if (alphaBotonsOut) {
            // Quan els botons es tornen transparents es desactiven
            botoPerfil1.setActiu(false);
            botoPerfil2.setActiu(false);
            botoPerfil3.setActiu(false);
            botoBorrar1.setActiu(false);
            botoBorrar2.setActiu(false);
            botoBorrar3.setActiu(false);
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia -= 0.05;
            }
            // Quan la transparencia arriba a 0 s'accedeix al seguent estat
            if (transparencia <= 0) {
                state.enterState(EstatMenuPrincipal.ID);
            }
            ReproductorMusica.update(container);
        }
        // Si s'ha esborrat un perfil es mostra un missatge per pantalla
        if (!reiniciarPerfil.equals("null")) {
            comptadorReiniciarPerfil++;
            if (comptadorReiniciarPerfil == 70) {
                reiniciarPerfil = "null";
                comptadorReiniciarPerfil = 0;
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
    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        imatgeFons.draw(0, 0);
        imatgeTitol.draw(290,100);
        if (carregat) {
            imatgeBotoBorrar.setAlpha(transparencia);
            imatgeBotoNormal.setAlpha(transparencia);
            imatgeBotoOver.setAlpha(transparencia);
            imatgeTextPerfil1.setAlpha(transparencia);
            imatgeTextPerfil2.setAlpha(transparencia);
            imatgeTextPerfil3.setAlpha(transparencia);
            botoPerfil1.render(game, g);
            botoPerfil2.render(game, g);
            botoPerfil3.render(game, g);
            botoBorrar1.render(game, g);
            botoBorrar2.render(game, g);
            botoBorrar3.render(game, g);
            if (!reiniciarPerfil.equals("null")) {
                g.setFont(font);
                g.drawString("S'ha reiniciat el " + reiniciarPerfil, 370, 690);
            }
        }
    }

    /**
     * Crea els botons necessaris per al menu de perfils
     * @param container
     */
    private void crearBotons(GameContainer container) {
        // Crear els botons
        // Perfil 1
        botoPerfil1 = new BotoMenu(container, imatgeBotoNormal, 420, 300);
        botoPerfil1.setLocation(container.getWidth() / 2 - botoPerfil1.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 380);
        botoPerfil1.setMouseOverImage(imatgeBotoOver);
        botoPerfil1.setImageText(imatgeTextPerfil1);

        botoBorrar1 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar1.setLocation(container.getWidth() / 2 - botoBorrar1.getWidth() / 2 + 145, 379);
        botoBorrar1.setMouseOverImage(imatgeBotoBorrarOver);
       
        // Perfil 2
        botoPerfil2 = new BotoMenu(container, imatgeBotoNormal, 420, 450);
        botoPerfil2.setLocation(container.getWidth() / 2 - botoPerfil2.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 500);
        botoPerfil2.setMouseOverImage(imatgeBotoOver);
        botoPerfil2.setImageText(imatgeTextPerfil2);

        botoBorrar2 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar2.setLocation(container.getWidth() / 2 - botoBorrar2.getWidth() / 2 + 145, 499);
        botoBorrar2.setMouseOverImage(imatgeBotoBorrarOver);
  
        // Perfil 3
        botoPerfil3 = new BotoMenu(container, imatgeBotoNormal, 420, 600);
        botoPerfil3.setLocation(container.getWidth() / 2 - botoPerfil3.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 620);
        botoPerfil3.setMouseOverImage(imatgeBotoOver);
        botoPerfil3.setImageText(imatgeTextPerfil3);
       
        botoBorrar3 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar3.setLocation(container.getWidth() / 2 - botoBorrar3.getWidth() / 2 + 145, 619);
        botoBorrar3.setMouseOverImage(imatgeBotoBorrarOver);
      
    }

    /**
     * Afegeix els escoltadors necessaris per aquests botons
     */
    private void afegirListeners() {

        // Listener BotoMenu Perfil 1
        botoPerfil1.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    ManagerPerfil.init(1);
                    alphaBotonsOut = true;
                }
            }
        });
        // Listener BotoMenu Borrar 1
        botoBorrar1.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    perfils = Configuracio.getPerfil1();
                    resetEstadistiques();
                    reiniciarPerfil = "Perfil 1";
                }
            }
        });

        // Listener BotoMenu Perfil 2
        botoPerfil2.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    ManagerPerfil.init(2);
                    alphaBotonsOut = true;
                }
            }
        });
        // Listener BotoMenu Borrar 2
        botoBorrar2.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    perfils = Configuracio.getPerfil2();
                    resetEstadistiques();
                    reiniciarPerfil = "Perfil 2";
                }
            }
        });

        // Listener BotoMenu Perfil 3
        botoPerfil3.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                {
                    ManagerPerfil.init(3);
                    alphaBotonsOut = true;
                }
            }
        });
        // Listener BotoMenu Borrar 3
        botoBorrar3.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    perfils = Configuracio.getPerfil3();
                    resetEstadistiques();
                    perfils.guardar();
                    reiniciarPerfil = "Perfil 3";
                }
            }
        });
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotons(gc);
        carregat = true;
        afegirListeners();
        alphaBotonsIn = true;
        reiniciarPerfil = "null";
        comptadorReiniciarPerfil = 0;
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        carregat = false;
        comptador = 0;
        alphaBotonsOut = false;
        imatgeBotoBorrar.setAlpha(1f);
        imatgeBotoNormal.setAlpha(1f);
        imatgeBotoOver.setAlpha(1f);
        imatgeTextPerfil1.setAlpha(1f);
        imatgeTextPerfil2.setAlpha(1f);
        imatgeTextPerfil3.setAlpha(1f);
    }

    /**
     * Reinicia les estadistiques d'un perfil
     */
    private void resetEstadistiques() {
        perfils.setPropietatInt("seguentWave", 1);
        perfils.setPropietatInt("seguentWave", 1);
        perfils.setPropietatInt("totalAures", 0);
        perfils.setPropietatInt("totalUnitats", 0);
        perfils.setPropietatInt("totalMorts", 0);
        perfils.setPropietatInt("totalGuanyades", 0);
        perfils.setPropietatInt("totalPerdudes", 0);
        perfils.setPropietatInt("totalBales", 0);
        perfils.setPropietatInt("totalDiners", 0);
        perfils.guardar();
    }
}
