package projecte.td.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import projecte.td.domini.UnitatAbstract;
import projecte.td.factories.FactoriaUnitatsEnemics;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;

/**
 * Aquest manager s'encarrega d'extreure els enemics que han d'apareixer en una wave
 * Ajustar el timing de sortida de cada enemic. S'activa amb un timer al inici de la wave
 * i es para una vegada s'ha perdut o guanyat
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerEnemics {

    // ID de la wave actual
    private static int waveActual;
    // Temps total que ha de durar la wave
    private static int tempsTotal;
    // Variable auxiliar on s'emmagatzmema el temps a sumar anterior
    private static int tempsAnterior;
    // Arxiu amb les caracteristiques de la wave actual
    private static ArxiuConfiguracio propietatsWave;
    // Arxiu amb les caracteristiques dels enemics
    private static ArxiuConfiguracio enemics;
    // Enemics que apareixeran en la wave
    private static String enemicsWave;
    // Llista de tots els enemics que sortiran
    private static List llistaEnemics;
    // Timer que s'usa com a compte enrere abans de que comencin a sortir enemics
    private static Timer timerInici;
    // Timer que s'utilitza per anar disparant la sortida de cada enemic
    private static Timer timer;
    // Indica si hi ha un enemic esperant per sortir
    private static boolean unitatEsperant;
    // Indica si la wave es troba en la fase inicial
    private static boolean inici;
    // Indica si s'ha de mostrar en pantalla la imatge de "Go" quan comenci la wave
    private static boolean mostraCartell;
    // Indica quin tipus d'enemic hi ha esperant per sortir
    private static String unitatEnEspera;
    // Imatge que es mostra quan s'acaba el compte enrere i comença la wave
    private static Image iniciWaveImatge;

    /**
     * Inicialitza els valors necessaris per organitzar una wave
     * @param wave enter amb el numero de wave a jugar
     */
    public static void iniciaWave(int wave) {
        waveActual = wave;
        // S'agafen els arxius de configuració
        propietatsWave = Configuracio.getWaves();
        enemics = Configuracio.getEnemics();
        enemicsWave = propietatsWave.getPropietatString("enemicsWave" + waveActual);
        tempsTotal = propietatsWave.getPropietatInt("temps" + waveActual);
        llistaEnemics = new ArrayList();
        tempsAnterior = 0;
        inici = true;
        iniciWaveImatge = ManagerRecursos.getImage("iniciWaveImage");
        carregaEnemics();
    }

    /**
     * S'encarrega de tractar la informació i crear la llista d'enemics que ha d'apareixer
     * en la wave que es jugara. Un cop creada la llista aquesta s'ordena per temps
     */
    private static void carregaEnemics() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        String[] unitats = enemicsWave.split("-");
        for (String z : unitats) {
            String[] informacio = z.split(":");
            int totalUnitats = Integer.parseInt(informacio[1]);
            int comptador = 0;
            for (int k = 0; k < totalUnitats; k++) {
                if (k == 0) {
                    llistaEnemics.add(new InfoEnemicManager(informacio[0], tempsTotal / totalUnitats));
                    comptador += tempsTotal / totalUnitats;
                } else {
                    int tempsUnitat = comptador + (tempsTotal / totalUnitats);
                    llistaEnemics.add(new InfoEnemicManager(informacio[0], tempsUnitat));
                    comptador += tempsTotal / totalUnitats;
                }
            }
        }
        ordenarArray();
    }

    /**
     * Ordena la llista d'enemics per temps de sortida
     */
    public static void ordenarArray() {
        Collections.sort(llistaEnemics);
    }

    /**
     * Crea el timer encarregat de disparar les unitats cap al tauler
     * @param temps
     */
    public static void crearTimer(int temps) {
        timer = new Timer(temps, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (inici) {
                    inici = false;
                    mostraCartell = false;
                } else {
                    if (llistaEnemics.size() >= 1) {
                        ajustarTimer();
                        unitatEsperant = true;
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Ajusta el temps del timer per adatar-lo al del seguent enemic que sortira
     */
    private static void ajustarTimer() {
        InfoEnemicManager iem = (InfoEnemicManager) llistaEnemics.get(0);
        if (iem.getTempsSortida() > tempsAnterior) {
            timer.setDelay(iem.getTempsSortida() - tempsAnterior);
        }
        unitatEnEspera = iem.getTipusEnemic();
        tempsAnterior = iem.getTempsSortida();
        llistaEnemics.remove(iem);
    }

    /**
     * Es tria un carril aleatoriament per on sortira la unitat
     * @return
     */
    public static int triarCarril() {
        int random = (int) (Math.random() * 600);
        return random;
    }

    /**
     * Para els dos timers que s'usen en aquesta classe
     */
    public static void pararTimer() {
        if (timer != null) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
        if (timerInici != null) {
            if (timerInici.isRunning()) {
                timerInici.stop();
            }
        }
        timer = null;
        timerInici = null;
    }

    /**
     * Indica si ja han sortit tots els enemics
     * @return
     */
    public static boolean fidelaWave() {
        if (llistaEnemics.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Activa el compte enrere abans de començar a disparar enemics
     */
    public static void iniciarCompteEnrere() {
        timerInici = new Timer(15000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mostraCartell = true;
                crearTimer(4000);
            }
        });
        timerInici.setRepeats(false);
        timerInici.start();
    }

    /**
     * Dibuixa el cartell d'inici en pantalla
     * @param gc
     * @param g
     */
    public static void renderCartell(GameContainer gc, Graphics g) {
        iniciWaveImatge.draw(gc.getWidth() / 2 - iniciWaveImatge.getWidth() / 2 , 80);
    }

    // Getters i setters
    public static boolean isEnEspera() {
        return unitatEsperant;
    }

    public static UnitatAbstract getUnitat() {
        unitatEsperant = false;
        return FactoriaUnitatsEnemics.getUnitatDolenta(unitatEnEspera);
    }

    public static boolean isMostraCartell() {
        return mostraCartell;
    }

    public static void setMostraCartell(boolean mostra) {
        mostraCartell = mostra;
    }
}
