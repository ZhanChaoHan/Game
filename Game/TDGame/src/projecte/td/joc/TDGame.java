package projecte.td.joc;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.estats.EstatDades;
import projecte.td.estats.EstatEstadistiques;
import projecte.td.estats.EstatGuanya;
import projecte.td.estats.EstatInGame;
import projecte.td.estats.EstatInfoEnemic;
import projecte.td.estats.EstatInfoUnitats;
import projecte.td.estats.EstatIntro;
import projecte.td.estats.EstatLoading;
import projecte.td.estats.EstatMenuPrincipal;
import projecte.td.estats.EstatMostraInfoEnemics;
import projecte.td.estats.EstatMostraInfoUnitats;
import projecte.td.estats.EstatMusica;
import projecte.td.estats.EstatPerd;
import projecte.td.estats.EstatPerfil;
import projecte.td.estats.EstatSeguentWave;
import projecte.td.estats.EstatSortir;
import projecte.td.managers.ManagerRecursos;

/**
 * Es crea una instancia de StateBasedGame (joc basat en estats). Ens obliga a
 * implementar el metode initStatesList on afegirem els diferents estats que usarem
 * per avançar a través del joc.
 * @author David Alvarez Palau
 */
public class TDGame extends StateBasedGame {

    /**
     * Constructor amb un unic parametre
     * @param titol String que es mostrara en la finestra de l'aplicacio (si n'hi ha)
     */
    public TDGame(String titol) {
        super(titol);
    }

    /**
     * Aquest metode ha de ser implementat per totes les classes que extenguin de StateBasedGame
     * Indiquem al motor que s'han de carregar els recursos mes tard (EstatLoading)
     * Activem el Manager de recursos per poguer disposar d'imatges i sons
     * Afegim els diferents estats que em creat a la llista pertinent
     * @param container
     * @throws SlickException
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        // Es carregaran els recursos en diferit
        LoadingList.setDeferredLoading(true);
        // S'activa el manager de recursos
        ManagerRecursos.init();
        // S'afegeixen els diferents estats del jocs
        // Estat amb la intro del joc
        addState(new EstatIntro());
        // Estat on es carreguen els recursos
        addState(new EstatLoading());
        // Estat on es tria el perfil amb el que es vol jugar
        addState(new EstatPerfil());
        // Estat menu principal
        addState(new EstatMenuPrincipal());
        // Estat on s'informa al jugador de la seguent wave
        addState(new EstatSeguentWave());
        // Estat on es juga la partida
        addState(new EstatInGame());
        // Estat on es mostra el menu ingame
        // Estat que apareix si l'usuari no supera una wave
        addState(new EstatPerd());
        // Estat que apareix si l'usuari supera una wave
        addState(new EstatGuanya());
        // Estat dades informacio
        addState(new EstatDades());
        // Estat estadistiques
        addState(new EstatEstadistiques());
        // Estat inicial mostra info unitats
        addState(new EstatInfoUnitats());
        // Estat info especifica d'unitats
        addState(new EstatMostraInfoUnitats());
        // Estat inicial mostra info enemics
        addState(new EstatInfoEnemic());
        // Estat info especifica enemics
        addState(new EstatMostraInfoEnemics());
        // Estat opcions musica
        addState(new EstatMusica());
        // Estat on es confirma la sortida a l'escriptori
        addState(new EstatSortir());
        
    }

    @Override
    public boolean closeRequested() {
        return false;
    }
}
