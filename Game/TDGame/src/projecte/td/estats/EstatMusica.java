package projecte.td.estats;

import org.newdawn.slick.Color;
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
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat es carreguen els recursos tot mostrant un fons de pantalla i una barra progressiva
 * @author David Alvarez Palau
 */
public class EstatMusica extends BasicGameState {

    // Identificador de l'estat
    public static final int ID = 16;
    // Contenidor del joc
    private GameContainer game;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Volum dels efectes
    private int volumEfectes;
    // Volum de la musica
    private int volumMusica;
    // Imatge fons de pantalla
    private Image imatgeFons;
    // Titol del estat
    private Image titolEstat;
    // Text del boto tornar
    private Image textTornar;
    // Boto last track
    private BotoMenu botoEnrere;
    // Boto Stop
    private BotoMenu botoStop;
    // Boto Next Track
    private BotoMenu botoEndavant;
    // Boto per tornar al menu principal
    private BotoMenu botoTornar;
    // Boto per sumar volum als efectes
    private BotoMenu botoSumaEfectes;
    // Boto per restar volum als efectes
    private BotoMenu botoRestaEfectes;
    // Boto per sumar volum a la musica
    private BotoMenu botoSumaMusica;
    // Boto per restar volum a la musica
    private BotoMenu botoRestaMusica;


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
        this.state = game;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        textTornar = ManagerRecursos.getImage("textTornarImage");
        titolEstat = ManagerRecursos.getImage("textMusicaImage");

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
        imatgeFons.draw(0, 0, container.getWidth(), container.getHeight());
        titolEstat.draw(250, 130);
        botoEndavant.render(container, g);
        botoEnrere.render(container, g);
        botoStop.render(container, g);
        botoTornar.render(container, g);
        botoRestaEfectes.render(container, g);
        botoSumaEfectes.render(container, g);
        botoRestaMusica.render(container, g);
        botoSumaMusica.render(container, g);
        g.setColor(Color.black);
        g.drawString("Volum Musica", 455, 400);
        g.drawString("Volum Efectes", 455, 470);
        g.setColor(Color.red);
        g.fillRect(botoRestaMusica.getX() + botoRestaMusica.getWidth() + 6, botoRestaMusica.getY() + 20, ((float) volumMusica / 100) * 170, 5);
        g.fillRect(botoRestaEfectes.getX() + botoRestaEfectes.getWidth() + 6, botoRestaEfectes.getY() + 20, ((float) volumEfectes / 100) * 170, 5);
        g.setColor(Color.black);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotons();
        volumEfectes = ManagerPerfil.getVolumEfectes();
        volumMusica = ManagerPerfil.getVolumMusica();
    }

    private void crearBotons() {
        botoEnrere = new BotoMenu(game, ManagerRecursos.getImage("botoLastImage"), 359, 267);
        botoEnrere.setMouseOverImage(ManagerRecursos.getImage("botoLastOverImage"));
        botoEnrere.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.prev();
            }
        });
      
        botoEnrere.setActiu(true);

        botoStop = new BotoMenu(game, ManagerRecursos.getImage("botoStopImage"), 479, 267);
        botoStop.setMouseOverImage(ManagerRecursos.getImage("botoStopOverImage"));
        botoStop.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.stop();
            }
        });

        botoStop.setActiu(true);

        botoEndavant = new BotoMenu(game, ManagerRecursos.getImage("botoNextImage"), 599, 267);
        botoEndavant.setMouseOverImage(ManagerRecursos.getImage("botoNextOverImage"));
        botoEndavant.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.next();
            }
        });
   
        botoEndavant.setActiu(true);

        botoTornar = new BotoMenu(game, ManagerRecursos.getImage("botoPerfil2OverImage"), 380, 570);
        botoTornar.setMouseOverImage(ManagerRecursos.getImage("botoPerfilNormalImage"));
        botoTornar.setImageText(textTornar);
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID, new FadeOutTransition(), new FadeInTransition());
            }
        });
     
        botoTornar.setActiu(true);

        botoRestaEfectes = new BotoMenu(game, ManagerRecursos.getImage("botoEnrereImage"), 395, 470);
        botoRestaEfectes.setMouseOverImage(ManagerRecursos.getImage("botoEnrereOverImage"));
        botoRestaEfectes.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumEfectes > 10) {
                    volumEfectes -= 10;
                    ManagerPerfil.setVolumEfectes(volumEfectes);
                    ManagerPerfil.guardarValorsMusica();
                }
            }
        });
    
        botoRestaEfectes.setActiu(true);

        botoSumaEfectes = new BotoMenu(game, ManagerRecursos.getImage("botoEndavantImage"), 605, 470);
        botoSumaEfectes.setMouseOverImage(ManagerRecursos.getImage("botoEndavantOverImage"));
        botoSumaEfectes.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumEfectes < 100) {
                    volumEfectes += 10;
                    ManagerPerfil.setVolumEfectes(volumEfectes);
                    ManagerPerfil.guardarValorsMusica();
                }
            }
        });
    
        botoSumaEfectes.setActiu(true);

        botoRestaMusica = new BotoMenu(game, ManagerRecursos.getImage("botoEnrereImage"), 395, 400);
        botoRestaMusica.setMouseOverImage(ManagerRecursos.getImage("botoEnrereOverImage"));
        botoRestaMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumMusica > 10) {
                    volumMusica -= 10;
                    ManagerPerfil.setVolumMusica(volumMusica);
                    ManagerPerfil.guardarValorsMusica();
                    ReproductorMusica.setVolumMusic();
                    ReproductorMusica.update(game);
                }
            }
        });
       
        botoRestaMusica.setActiu(true);

        botoSumaMusica = new BotoMenu(game, ManagerRecursos.getImage("botoEndavantImage"), 605, 400);
        botoSumaMusica.setMouseOverImage(ManagerRecursos.getImage("botoEndavantOverImage"));
        botoSumaMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumMusica < 100) {
                    volumMusica += 10;
                    ManagerPerfil.setVolumMusica(volumMusica);
                    ManagerPerfil.guardarValorsMusica();
                    ReproductorMusica.setVolumMusic();
                    ReproductorMusica.update(game);
                }
            }
        });
  
        botoSumaMusica.setActiu(true);
    }
}
