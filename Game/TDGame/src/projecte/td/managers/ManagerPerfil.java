package projecte.td.managers;

import projecte.td.utilitats.*;
import projecte.td.utilitats.Configuracio;

/**
 * En aquest manager es control·la quin es el perfil que hi ha actiu. També s'hi
 * guarden les dades referents a opcions de l'usuari o estadistiques.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerPerfil {

    // Perfil del jugador que s'ha triat per jugar
    private static int perfilTriat;
    // Wave a partir de la qual el jugador començara a jugar
    private static int wave;
    // Wave que s'esta jugant actualment
    private static int waveActual;
    // Volum de la música
    private static int volumMusica;
    // Volum dels efectes de so
    private static int volumEfectes = 20;
    // Total de temps que durara la wave
    private static int tempsTotal;
    // Total d'enemics que apareixeran
    private static int enemicsTotals;
    // Unitats disponibles per triar en la wave actual
    private static String unitatsDisponibles;
    // Enemics que sortiran en la wave
    private static String enemicsWave;
    // Unitats que ha triat el jugador per jugar la wave
    private static String unitatsTriades;
    // S'utilitza per guardar informació referent a la unitat triada quan es canvia d'estat
    private static String informacioUnitat;
    // Total d'enemics que han estat mortes per les unitats del jugador
    private static int totalMorts;
    // Total d'unitats col·locades en el tauler pel jugador
    private static int totalUnitatsColocades;
    // Total de bales disparades per les unitats
    private static int totalBales;
    // Total de partides que ha guanyat el jugador
    private static int totalGuanyades;
    // Total de partides que ha perdut el jugador
    private static int totalPerdudes;
    // Total de diners acumulats en totes les partides pel jugador
    private static int totalDinersGuanyats;
    // Total aures col·locades
    private static int totalAuresColocades;
    // Dades del perfil triat
    private static ArxiuConfiguracio dadesPerfil;
    // Dades de la wave actual
    private static ArxiuConfiguracio dadesWave;
    // Indica si el jugador ha aconseguit superar una wave
    private static boolean canviWave;

    /**
     * Inicialitza les variables i recursos necessaris per usar la classe
     * @param perfil
     */
    public static void init(int perfil) {
        // Es carrega l'arxiu pertinent
        perfilTriat = perfil;
        if (perfilTriat == 1) {
            dadesPerfil = Configuracio.getPerfil1();
        } else if (perfilTriat == 2) {
            dadesPerfil = Configuracio.getPerfil2();
        } else if (perfilTriat == 3) {
            dadesPerfil = Configuracio.getPerfil3();
        }
        // Es carreguen les dades de la wave que s'ha de jugar
        dadesWave = Configuracio.getWaves();
        wave = dadesPerfil.getPropietatInt("seguentWave");
        waveActual = dadesPerfil.getPropietatInt("seguentWave");
        volumMusica = dadesPerfil.getPropietatInt("volumMusica");
        volumEfectes = dadesPerfil.getPropietatInt("volumEfectes");
        // S'assignen les propietats pertinents
        assignarPropietats();
        totalMorts = 0;
        totalBales = 0;
        totalGuanyades = 0;
        totalPerdudes = 0;
        totalDinersGuanyats = 0;
        totalAuresColocades = 0;
    }

    /**
     * Quan un jugador supera una wave s'escriuen les dades necessaries
     */
    public static void passaASeguentWave() {
        if (waveActual==wave) {
            wave++;
            waveActual++;
            dadesPerfil.setPropietatInt("seguentWave", wave);
            dadesPerfil.guardar();
            canviWave = true;
        }
    }

    /**
     * Assigna les unitats disponibles que tindra el jugador per escollir en la pantalla seguent wave
     */
    public static void assignarPropietats() {
        unitatsDisponibles = dadesWave.getPropietatString("unitatsDisponibles" + wave);
        tempsTotal = dadesWave.getPropietatInt("temps" + wave);
        enemicsTotals = dadesWave.getPropietatInt("nombreEnemics" + wave);
        enemicsWave = retornaEnemics();
    }

    /**
     * Retorna un string amb els enemics que apareixeran en la seguent wave a jugar
     * @return String amb informació dels enemics
     */
    private static String retornaEnemics() {
        String enemics = "";
        String[] unitatsEnemigues = dadesWave.getPropietatString("enemicsWave" + wave).split("-");
        for (String z : unitatsEnemigues) {
            enemics += z.split(":")[0] + "-";
        }
        return enemics.substring(0, enemics.length() - 1);
    }

    /**
     * Retorna les unitats disponibles en la seguent wave
     * @return unitats disponibles
     */
    public static String getUnitatsDisponibles() {
        if (canviWave) {
            assignarPropietats();
            canviWave = false;
        }
        return unitatsDisponibles;
    }

    /**
     * Indica si es pot canviar la wave a jugar en l'estat seguent wave
     * @return
     */
    public static boolean potRestarWave() {
        if (wave > 1) {
            return true;
        }
        return false;
    }

    /**
     * Indica si es pot canviar la wave a jugar en l'estat seguent wave
     * @return
     */
    public static boolean potSumarWave() {
        if (wave < waveActual) {
            return true;
        }
        return false;
    }

    /**
     * Resta una unitat a la wave actual (necessari en l'estat EstatSeguentWave)
     */
    public static void restaWaveActual() {
        wave--;
    }

    /**
     * Suma una unitat a la wave actual (necessari en l'estat EstatSeguentWave)
     */
    public static void sumaWaveActual() {
        wave++;
    }

    /**
     * Retorna els enemics que apareixeran en la wave a jugar
     * @return
     */
    public static String getEnemicsWave() {
        if (canviWave) {
            assignarPropietats();
            canviWave = false;
        }
        return enemicsWave;
    }

    /**
     * Es guarden les estadistiques del jugador en l'arxiu de text pertinent
     */
    public static void guardarEstadistiques() {
        int total = dadesPerfil.getPropietatInt("totalMorts");
        total += totalMorts;
        dadesPerfil.setPropietatInt("totalMorts", total);
        total = dadesPerfil.getPropietatInt("totalBales");
        total += totalBales;
        dadesPerfil.setPropietatInt("totalBales", total);
        total = dadesPerfil.getPropietatInt("totalGuanyades");
        total += totalGuanyades;
        dadesPerfil.setPropietatInt("totalGuanyades", total);
        total = dadesPerfil.getPropietatInt("totalPerdudes");
        total += totalPerdudes;
        dadesPerfil.setPropietatInt("totalPerdudes", total);
        total = dadesPerfil.getPropietatInt("totalDiners");
        total += totalDinersGuanyats;
        dadesPerfil.setPropietatInt("totalDiners", total);
        total = dadesPerfil.getPropietatInt("totalAures");
        total += totalAuresColocades;
        dadesPerfil.setPropietatInt("totalAures", total);
        total = dadesPerfil.getPropietatInt("totalUnitats");
        total += totalUnitatsColocades;
        dadesPerfil.setPropietatInt("totalUnitats", total);
        dadesPerfil.guardar();
    }

    /**
     * Guarda els valors de volums en l'arxiu de text pertinent
     */
    public static void guardarValorsMusica() {
        dadesPerfil.setPropietatInt("volumMusica", volumMusica);
        dadesPerfil.setPropietatInt("volumEfectes", volumEfectes);
        dadesPerfil.guardar();
    }

    // Metodes per sumar punts a les estadistiques del jugador
    public static void sumaMort() {
        totalMorts++;
    }

    public static void sumaBala() {
        totalBales++;
    }

    public static void sumaUnitat() {
        totalUnitatsColocades++;
    }

    public static void sumaGuanyada() {
        totalGuanyades++;
    }

    public static void sumaPerdudes() {
        totalPerdudes++;
    }

    public static void sumaDiners(int diners) {
        totalDinersGuanyats += diners;
    }

    public static void sumaAuraColocada() {
        totalAuresColocades++;
    }

    // Metodes que retornen el valor actual d'una certa estadistica
    public static int getUnitats() {
        return dadesPerfil.getPropietatInt("totalUnitats");
    }

    public static int getAures() {
        return dadesPerfil.getPropietatInt("totalAures");
    }

    public static int getTotalMorts() {
        return dadesPerfil.getPropietatInt("totalMorts");
    }

    public static int getGuanyades() {
        return dadesPerfil.getPropietatInt("totalGuanyades");
    }

    public static int getPerdudes() {
        return dadesPerfil.getPropietatInt("totalPerdudes");
    }

    public static int getBales() {
        return dadesPerfil.getPropietatInt("totalBales");
    }

    public static int getDiners() {
        return dadesPerfil.getPropietatInt("totalDiners");
    }

    // Getters i setters
    public static void setUnitatsTriades(String triades) {
        unitatsTriades = triades;
    }

    public static String getUnitatsTriades() {
        return unitatsTriades;
    }

    public static int getWave() {
        return wave;
    }

    public static int getWaveActual() {
        return waveActual;
    }

    public static void setWave(int wave) {
        ManagerPerfil.wave = wave;
    }

    public static int getEnemicsTotals() {
        return enemicsTotals;
    }

    public static void setEnemicsTotals(int enemicsTotals) {
        ManagerPerfil.enemicsTotals = enemicsTotals;
    }

    public static int getTempsTotal() {
        return tempsTotal;
    }

    public static void setTempsTotal(int tempsTotal) {
        ManagerPerfil.tempsTotal = tempsTotal;
    }

    public int getTotalAuresColocades() {
        return totalAuresColocades;
    }

    public static String getInformacioUnitat() {
        return informacioUnitat;
    }

    public static void setInformacioUnitat(String informacioEnemic) {
        ManagerPerfil.informacioUnitat = informacioEnemic;
    }

    public static int getPerfilTriat() {
        return perfilTriat;
    }

    public static ArxiuConfiguracio getArxiuConfiguracio() {
        return dadesPerfil;
    }

    public static int getVolumEfectes() {
        return volumEfectes;
    }

    public static void setVolumEfectes(int volumEfectes) {
        ManagerPerfil.volumEfectes = volumEfectes;
    }

    public static int getVolumMusica() {
        return volumMusica;
    }

    public static void setVolumMusica(int volumMusica) {
        ManagerPerfil.volumMusica = volumMusica;
    }
}
