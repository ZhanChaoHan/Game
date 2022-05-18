package projecte.td.utilitats;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

public class ReproductorMusica {

    private static List<String> noms = new ArrayList<String>();
    private static float volumMusica;
    private static int indexActual = -1;
    private static int indexMax = -1;
    private static int indexMin = -1;
    private static boolean repeteix = false;
    private static boolean aleatori = false;
    private static boolean reproduint = false;

    transient static SecureRandom random = null;

    static {
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
        }
    }

    public static void init() {
       

    }

    public static void toggleShuffle() {
        aleatori = !aleatori;
    }

    public static void toggleRepeatAll() {
        repeteix = !repeteix;
    }

    public static void last() {
        if (indexActual < indexMax && !reproduint) {
            indexActual = indexMax;
            changeMusic();
            reproduint = true;
        }
    }

    public static void first() {
        if (indexActual != 0 && indexMin == 0 && !reproduint) {
            indexActual = indexMin;
            changeMusic();
            reproduint = true;
        }
    }

    public static void next() {
        if (indexActual < indexMax) {
            indexActual++;
            changeMusic();
        }
    }

    public static void prev() {
        if (indexActual > indexMin) {
            indexActual--;
            changeMusic();
        }
    }

    public static void update(GameContainer container) {

    }

    public static void stop() {
      
    }

    public static void pause() {
       
    }

    public static void resume() {
       
    }

    private static void changeMusic() {
      
    }

    public static int getMusicCount() {
        return noms.size();
    }

    public static int getCurrentIndex() {
        return indexActual;
    }

    public static boolean isRepeatAll() {
        return repeteix;
    }

    public static boolean isShuffle() {
        return aleatori;
    }

    public static void setVolumMusic() {
        volumMusica = ManagerPerfil.getVolumMusica() / 100f;
    }
}
