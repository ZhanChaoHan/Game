package projecte.td.utilitats;

/**
 * S'encarrega de carregar en memòria els diferents Arxius de Configuració i servir-los
 * en el moment oportú
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class Configuracio {

    // En aquest arxiu s'hi guarden les rutes dels altres fitxers de configuració
    private static ArxiuConfiguracio configuracio;
    // Característiques dels enemics
    private static ArxiuConfiguracio enemics;
    // Recursos amb les rutes de les imatges, audio, musica i fonts
    private static ArxiuConfiguracio recursos;
    // Opcions generals del joc
    private static ArxiuConfiguracio opcions;
    // Característiques de les unitats
    private static ArxiuConfiguracio unitats;
    // Opcions del perfil 1
    private static ArxiuConfiguracio perfil1;
    // Opcions del perfil 2
    private static ArxiuConfiguracio perfil2;
    // Opcions del perfil 3
    private static ArxiuConfiguracio perfil3;
    // Característiques de les waves
    private static ArxiuConfiguracio wave;

    // Aquest arxiu es carregarà estaticament al iniciar l'aplicació
    static {
        configuracio = new ArxiuConfiguracio("config/td.cfg");
    }

    // Els mètodes següents s'encarreguen de carregar en memòria l'ArxiuConfiguracio
    // (en el cas que no estigui encara carregat) i a continuació retornar-lo quan
    // aquest es sol·licitat per un altre objecte.
    public static ArxiuConfiguracio getPerfil1() {
        if (perfil1 == null) {
            perfil1 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil1"));
        }
        return perfil1;
    }

    public static ArxiuConfiguracio getPerfil2() {
        if (perfil2 == null) {
            perfil2 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil2"));
        }
        return perfil2;
    }

    public static ArxiuConfiguracio getPerfil3() {
        if (perfil3 == null) {
            perfil3 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil3"));
        }
        return perfil3;
    }

    public static ArxiuConfiguracio getWaves() {
        if (wave == null) {
            wave = new ArxiuConfiguracio(configuracio.getPropietatString("wave"));
        }
        return wave;
    }

    public static ArxiuConfiguracio getRecursos() {
        if (recursos == null) {
            recursos = new ArxiuConfiguracio(configuracio.getPropietatString("recursos"));
        }
        return recursos;
    }

    public static ArxiuConfiguracio getOpcions() {
        if (opcions == null) {
            opcions = new ArxiuConfiguracio(configuracio.getPropietatString("opcions"));
        }
        return opcions;
    }

    public static ArxiuConfiguracio getUnitats() {
        if (unitats == null) {
            unitats = new ArxiuConfiguracio(configuracio.getPropietatString("unitats"));
        }
        return unitats;
    }

    public static ArxiuConfiguracio getEnemics() {
        if (enemics == null) {
            enemics = new ArxiuConfiguracio(configuracio.getPropietatString("enemics"));
        }
        return enemics;
    }
}
