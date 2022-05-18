package projecte.td.managers;

import projecte.td.utilitats.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import projecte.td.utilitats.Configuracio;

/**
 * Aquesta classe s'encarrega de mappejar els diferents recursos que s'utilitzaran en el joc i
 * posteriorment, servir-los quan es sol·licitin des d'algun punt de l'aplicació.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerRecursos {

    // Map on es col·loquen les imatges
    private static Map<String, Image> imatges = new HashMap<String, Image>();
    // Mapn on es col·loquen els arrays d'imatges
    private static Map<String, Image[]> animacions = new HashMap<String, Image[]>();
    // Map on es col·loquen les fonts de text
    private static Map<String, Font> fonts = new HashMap<String, Font>();
    // Arxiu de configuració on hi ha els mappings amb la ruta de l'arxiu
    private static ArxiuConfiguracio recursos;

    /**
     * Aquest metode es crea una única vegada, quan s'inicia l'aplicació
     */
    public static void init() {
        carregaObjectesJoc();
    }

    /**
     * S'encarrega d'agafar tots els recursos que s'han de carregar, crear l'objecte
     * pertinent i col·locar-lo en el mapping adequat
     */
    private static void carregaObjectesJoc() {
        recursos = Configuracio.getRecursos();
        Set<Object> claus = recursos.getTotesPropietats();
        for (Object objecte : claus) {
            String clau = (String) objecte;
            try {
                crearObjectes(clau);
            } catch (Exception ex) {
                throw new RuntimeException("No s'ha pogut crear el recurs : " + clau);
            }
        }
    }

    /**
     * Crea un recurs a partir d'un string passat. Depenen del sufix que tingui
     * la cadena de text es creara un tipus de recurs o un altre
     * @param clau : Recurs a carregar
     * @throws Exception
     */
    private static void crearObjectes(String clau) throws Exception {
        String valor = recursos.getPropietatString(clau);
        if (clau.endsWith("Sound")) {
           
        } else if (clau.endsWith("Music")) {
      
        } else if (clau.endsWith("Image")) {
            imatges.put(clau, new Image(valor));
        } else if (clau.endsWith("Animation")) {
            animacions.put(clau, convertirImatgeArray(valor));
        } else if (clau.endsWith("Font")) {
            fonts.put(clau, new AngelCodeFont(valor + ".fnt", valor + ".png", true));
        }
    }

    /**
     * A partir d'un string carrega els recursos (imatges) pertinents i les retorna
     * @param valor : Recursos a carregar
     * @return arrai d'imatges resultant
     * @throws Exception
     */
    private static Image[] convertirImatgeArray(String valor) throws Exception {
        String[] frames = valor.split("-");
        Image[] image = new Image[frames.length];
        for (int z = 0; z < frames.length; z++) {
            image[z] = new Image(frames[z]);
        }
        return image;
    }

    // Getters i setters
    public static Image getImage(String name) {
        return imatges.get(name);
    }

    public static Image[] getImageArray(String name) {
        return animacions.get(name);
    }

    public static Font getFont(String name) {
        return fonts.get(name);
    }

   
}
