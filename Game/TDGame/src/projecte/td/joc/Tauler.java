package projecte.td.joc;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import projecte.td.domini.InterficieBomba;
import projecte.td.domini.Miner;
import projecte.td.managers.ManagerColisions;
import projecte.td.domini.Projectil;
import projecte.td.domini.ProjectilEstatic;
import projecte.td.domini.ProjectilMobil;
import projecte.td.domini.UnitatAbstract;
import projecte.td.domini.UnitatDispara;
import projecte.td.domini.UnitatEnemiga;
import projecte.td.domini.UnitatEnemigaAtkDistancia;
import projecte.td.domini.UnitatEnemigaAtkDistanciaSalta;
import projecte.td.domini.UnitatEnemigaAtkNormal;
import projecte.td.domini.UnitatEnemigaInvisible;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

/**
 * Classe Tauler encarregada de tot el funcionament de la Partida
 * @author Ernest Daban i David Alvarez
 */
public class Tauler {

    private int nFiles; //Número files tauler
    private int nColumnes; //Número columnes tauler
    private int nCeles; //Número celes del tauler
    private int ampladaTotal; //Amplada total del tauler
    private int llargadaTotal; //Llargada total del tauler
    private double amplada; //Amplada cela
    private double llargada; //Llargada cela
    private boolean[][] clicades; //Array que controla les celes clicades
    private boolean dibuixarQuadrat; //Condicio per a dibuixar els quadrats
    private int[] posicioQuadrat; //Coordenades del quadrat sel·leccionat
    private Rectangle[][] celes; //Array de Rectangles, les celes de tot el tauler
    private UnitatAbstract[][] unitatsAmigues; //Array de totes les unitats amigues del tauler
    ArrayList[] arrays_enemics;//Array de tots els enemics del tauler
    ArrayList<UnitatAbstract> enemics_morts;//Array per els enemics morts del tauler
    ArrayList[] arrays_projectils_amics;//Array de projectils amics
    ArrayList[] arrays_projectils_enemics;//Array de projectils enemics
    ArrayList<Projectil> projectils_finalitzats;//Array per els projectils finalitzats
    private boolean[] controlaFiles;//Array que controla les files ocupades per als enemics
    ManagerColisions mc;//Manager de colisions
    ArrayList<UnitatEnemigaAtkDistanciaSalta> unitatsSaltant;//Array per a enmagatzemar les unitats amigues que salten.

    /**
     * Constructor Tauler
     * @param nFiles Número de files del tauler
     * @param nColumnes Número de columnes del tauler
     * @param ampladaTotal Amplada total del tauler amb px
     * @param llargadaTotal Llargada total del tauler amb px
     */
    public Tauler(int nFiles, int nColumnes, int ampladaTotal, int llargadaTotal) {
        this.nFiles = nFiles;
        this.nColumnes = nColumnes;
        this.ampladaTotal = ampladaTotal;
        this.llargadaTotal = llargadaTotal;
        crearCeles();
        inicialitzar();
    }

    /**
     * Crea les celes del tauler
     */
    private void crearCeles() {
        nCeles = nFiles * nColumnes;
        clicades = new boolean[nFiles][nColumnes];
        celes = new Rectangle[nFiles][nColumnes];
        amplada = ampladaTotal / nColumnes;
        llargada = llargadaTotal / nFiles;
        int x = 0;
        int y = 0;
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                Rectangle r = new Rectangle(x, y, ampladaTotal / nColumnes, llargadaTotal / nFiles);
                celes[fil][col] = r;
                x += amplada;
            }
            x = 0;
            y += llargada;
        }
    }

    /**
     * Inicialitza tots els Arrays per enmagatzemar Entitats del Tauler
     */
    private void inicialitzar() {
        unitatsAmigues = new UnitatAbstract[nFiles][nColumnes];
        controlaFiles = new boolean[nFiles];
        arrays_enemics = new ArrayList[nFiles];
        arrays_projectils_amics = new ArrayList[nFiles];
        arrays_projectils_enemics = new ArrayList[nFiles];
        for (int i = 0; i < nFiles; i++) {
            ArrayList<UnitatAbstract> enemics = new ArrayList<UnitatAbstract>();
            arrays_enemics[i] = enemics;
            ArrayList<Projectil> projectils_amics = new ArrayList<Projectil>();
            arrays_projectils_amics[i] = projectils_amics;
            ArrayList<Projectil> projectils_enemics = new ArrayList<Projectil>();
            arrays_projectils_enemics[i] = projectils_enemics;
        }
        projectils_finalitzats = new ArrayList<Projectil>();
        enemics_morts = new ArrayList<UnitatAbstract>();
        mc = new ManagerColisions(arrays_enemics, arrays_projectils_amics, arrays_projectils_enemics, controlaFiles, unitatsAmigues);
        unitatsSaltant = new ArrayList<UnitatEnemigaAtkDistanciaSalta>();
    }

    /**
     * Comprova si s'ha efectuat un click dins d'alguna cela del tauler
     * @param x Coordenada x del click
     * @param y Coordenada y del click
     * @return true si el click es correcte
     */
    public boolean comprovaClickCorrecte(int x, int y) {
        int[] posFC = {-1, -1};
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                if (celes[fil][col].contains(x, y)) {
                    posFC[0] = fil;
                    posFC[1] = col;
                }
            }
        }
        if (posFC[0] == -1 && posFC[1] == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Comprova a quina cela pertany el click
     * @param x Coordenada x del click
     * @param y Coordenada y del click
     * @return posicio de la fila i la columna de la cela al array
     */
    public int[] mirarCoordenadesClick(int x, int y) {
        int[] posFC = {0, 0};
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                if (celes[fil][col].contains(x, y)) {
                    posFC[0] = fil;
                    posFC[1] = col;
                }
            }
        }
        return posFC;
    }

    /**
     * Mira si la cela està ocupada
     * @param fil numero fila
     * @param col numero columna
     * @return true si la cela està vuida
     */
    public boolean comprovarClick(int fil, int col) {
        if (!clicades[fil][col]) {
            return true;

        }
        return false;
    }

    /**
     * Posiciona una unitat Amiga al tauler
     * @param fil Numero fila
     * @param col Numero columna
     * @param unitatAmiga UnitatAmiga per a posicionar.
     */
    public void posicionaUnitatAmiga(int fil, int col, UnitatAbstract unitatAmiga) {
        clicades[fil][col] = true;
        //Posiciona unitat Amiga
        float Lx = (float) (celes[fil][col].getCenterX() - (double) unitatAmiga.getAnimation().getImage(0).getWidth() / 2);
        float Ly = (float) (celes[fil][col].getY() + llargada - unitatAmiga.getAnimation().getImage(0).getHeight());
        unitatAmiga.setLocation(Lx, Ly);
        unitatsAmigues[fil][col] = unitatAmiga;
    }

    /**
     * Getter Unitat Amiga
     * @param x coordenada x del click
     * @param y coordenada y del click
     * @return UnitatAmiga clicada
     */
    public UnitatAbstract getUnitatAmiga(int x, int y) {
        int[] posFC = mirarCoordenadesClick(x, y);
        return unitatsAmigues[posFC[0]][posFC[1]];
    }

    /**
     * Elimina una unitat Amiga del tauler
     * @param fil numero fia
     * @param col numero columna
     */
    private void eliminaUnitatAmiga(int fil, int col) {
        if (unitatsAmigues[fil][col] instanceof UnitatDispara) {
            UnitatDispara ud = (UnitatDispara) unitatsAmigues[fil][col];
            ud.desactivarDispars();//Parar timers
        } else if (unitatsAmigues[fil][col] instanceof Miner) {
            Miner miner = (Miner) unitatsAmigues[fil][col];
            miner.desactivarTimer();//Parar timers
        }
        unitatsAmigues[fil][col] = null;
        clicades[fil][col] = false;
        stopAtacUnitatsEnemigues(fil);
    }

    /**
     * Posiciona una unitat enemiga al tauler
     * @param x coordenada
     * @param y coordenada
     * @param enemic UnitatEnemiga a posicionar
     */
    public void posicionaUnitatEnemiga(int x, int y, UnitatAbstract enemic) {
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                if (celes[fil][col].contains(x, y)) {
                    enemic.setLocation(ampladaTotal, (float) (celes[fil][col].getY() + llargada - enemic.getAnimation().getHeight()));
                    arrays_enemics[fil].add(enemic);
                    if (!controlaFiles[fil] && !(enemic instanceof UnitatEnemigaInvisible)) {
                        controlaFiles[fil] = true;
                    }

                }
            }
        }
    }

    /**
     * Para l'atac de les UnitatsAmigues
     */
    private void stopUnitatsAmigues() {
        for (int i = 0; i < nFiles; i++) {
            if (arrays_enemics[i].isEmpty()) {
                controlaFiles[i] = false;
                for (UnitatAbstract amic : unitatsAmigues[i]) {
                    if (amic instanceof UnitatDispara) {
                        UnitatDispara ud = (UnitatDispara) amic;
                        ud.desactivarDispars();
                    }
                }
            }
        }
    }

    /**
     * Para l'atac de les unitats Enemigues
     * @param fila Numero fila
     */
    private void stopAtacUnitatsEnemigues(int fila) {
        for (Object en : arrays_enemics[fila]) {
            if (en instanceof UnitatEnemigaAtkDistancia) {
                UnitatEnemigaAtkDistancia enemic = (UnitatEnemigaAtkDistancia) en;
                enemic.desactivarDispars();
            } else if (en instanceof UnitatEnemigaAtkNormal) {
                UnitatEnemigaAtkNormal enemic = (UnitatEnemigaAtkNormal) en;
                enemic.setActivat(false);
            }

        }

    }

    /**
     * Dispara una UnitatDispara
     * @param ud UnitatDispara
     * @param numFila Numero fila
     * @param p Projectil de la unitat en qüestio
     */
    private void dispararUnitatDispara(UnitatDispara ud, int numFila, Projectil p) {
        if (ud.estaDisparant()) {
            arrays_projectils_amics[numFila].add(p);
            ud.haDisparat();
          
            ManagerPerfil.sumaBala();
        }
        if (!ud.estaActivat()) {
            ud.activarDispars();
        }
    }

    /**
     * Dispara totes les unitats Amigues
     */
    private void dispararUnitatsAmigues() {
        for (int i = 0; i < nFiles; i++) {
            for (UnitatAbstract t : unitatsAmigues[i]) {
                if (t != null && t instanceof InterficieBomba) {
                    InterficieBomba u = (InterficieBomba) t;
                    if (u.isDispara()) {
                        arrays_projectils_amics[i].add(u.getProjectil());
                        u.haDisparat();
          
                        ManagerPerfil.sumaBala();
                    }
                } else {
                    if (controlaFiles[i]) {
                        if (t != null && t instanceof UnitatDispara) {
                            for (Object ob : arrays_enemics[i]) {
                                UnitatAbstract enemic = (UnitatAbstract) ob;
                                UnitatDispara ud = (UnitatDispara) t;
                                Projectil p = ud.getProjectil();
                                if (enemic.getPosX() >= ud.getPosX()) {
                                    if (p instanceof ProjectilMobil) {
                                        dispararUnitatDispara(ud, i, p);
                                    } else if (p instanceof ProjectilEstatic) {
                                        if (!arrays_enemics[i].isEmpty()) {
                                            if (enemic.getPosX() <= ud.getPosX() + 150) {
                                                dispararUnitatDispara(ud, i, p);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Dispara totes les unitats Enemigues
     */
    private void disparaUnitatsEnemigues() {
        for (int i = 0; i < nFiles; i++) {
            if (controlaFiles[i]) {
                for (Object en : arrays_enemics[i]) {
                    if (en instanceof UnitatEnemigaAtkDistancia) {
                        UnitatEnemigaAtkDistancia enemic = (UnitatEnemigaAtkDistancia) en;
                        if (enemic.estaActivat() && enemic.estaDisparant()) {
                            arrays_projectils_enemics[i].add(enemic.getProjectil());
                            enemic.haDisparat();
                          
                        }
                    }
                }
            }
        }
    }

    /**
     * Dibuixa tot el contingut del tauler
     * @param g Graphics
     * @param gc GameContainer
     */
    public void dibuixar(Graphics g, GameContainer gc) {
        for (int i = 0; i < nFiles; i++) {
            for (UnitatAbstract t : unitatsAmigues[i]) {
                if (t != null) {
                    t.render(gc, g);
                }
            }
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                enemic.render(gc, g);
            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                p.render(gc, g);
            }
            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                p.render(gc, g);
            }
            for (UnitatAbstract t : unitatsAmigues[i]) {
                if (t != null) {
                    t.renderMort(gc, g);
                    if(t.efectuarSoMort()){
                      
                    }
                }
            }
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                enemic.renderMort(gc, g);
            }
        }
        dibuixarQuadrat(g, gc);
    }

    /**
     * Dibuixa els marges de les caselles sel·leccionades
     * @param g Graphics
     * @param gc GameContainer
     */
    private void dibuixarQuadrat(Graphics g, GameContainer gc) {
        if (dibuixarQuadrat) {
            int[] quadricula = mirarCoordenadesClick(posicioQuadrat[0], posicioQuadrat[1]);
            if (quadricula[0] != 15) {
                Rectangle rectangle = getCela(quadricula[0], quadricula[1]);
                if (comprovarClick(quadricula[0], quadricula[1])) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.red);
                }
                g.setLineWidth(2);
                g.drawRoundRect(rectangle.getX() + 2, rectangle.getY() + 2, rectangle.getWidth() - 2,
                        rectangle.getHeight() - 2, 15);
                g.setColor(Color.white);
                g.setLineWidth(1);
            }
        }
    }

    /**
     * Elimina tots els projectils i els enemics finalitzats i morts.
     */
    private void finalitzarProjectils_Enemics() {
        for (int i = 0; i < nFiles; i++) {
            for (Projectil b : projectils_finalitzats) {
                arrays_projectils_amics[i].remove(b);
            }
            for (Projectil b : projectils_finalitzats) {
                arrays_projectils_enemics[i].remove(b);
            }
            for (UnitatAbstract en : enemics_morts) {
                arrays_enemics[i].remove(en);
            }

        }
        projectils_finalitzats.clear();
        enemics_morts.clear();
    }

    /**
     * Posiciona les unitats que salten al tauler
     */
    private void colocarUnitatsSaltant() {
        for (UnitatEnemigaAtkDistanciaSalta eS : unitatsSaltant) {
            posicionaUnitatEnemigaSalta((int) eS.getPosX(), eS.getySalt(), eS);
        }
        unitatsSaltant.clear();
    }

    /**
     * Posiciona una unitat que salta al tauler
     * @param x CoordenadaX
     * @param y CoordenadaY
     * @param enemic UnitatAbstract
     */
    private void posicionaUnitatEnemigaSalta(int x, int y, UnitatAbstract enemic) {

        int[] PosFC = mirarCoordenadesClick(x, y);
        enemic.setMort(false);
        enemic.setLocation(x, (float) (celes[PosFC[0]][PosFC[1]].getY() + llargada - enemic.getAnimation().getHeight()));
        arrays_enemics[PosFC[0]].add(enemic);
        if (!controlaFiles[PosFC[0]]) {
            controlaFiles[PosFC[0]] = true;
        }
    }

    /**
     * Comprova les morts de totes les unitats del tauler
     * @param cond true si les volem sumar al perfil de cada jugador.
     */
    private void comprovarMorts(boolean cond) {
        for (int i = 0; i < nFiles; i++) {
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                if (enemic.isMort()) {
                    if (cond) {
                        ManagerPerfil.sumaMort();//Sumar morts
                    }
                    if (enemic instanceof UnitatEnemigaAtkDistancia) {
                        UnitatEnemigaAtkDistancia ud = (UnitatEnemigaAtkDistancia) enemic;
                        ud.desactivarDispars();
                    }
                    enemics_morts.add(enemic);
                }
            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                if (p.isMort()) {
                    projectils_finalitzats.add(p);
                }
            }
            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                if (p.isMort()) {
                    projectils_finalitzats.add(p);
                }
            }
            for (int col = 0; col < nColumnes; col++) {
                if (unitatsAmigues[i][col] != null) {
                    if (unitatsAmigues[i][col].isMort()) {
                        eliminaUnitatAmiga(i, col);
                    }
                }
            }
        }
    }

    /**
     * Acciona totes les unitats del tauler.
     * @param delta
     */
    private void accionarUnitats(int delta) {
        for (int i = 0; i < nFiles; i++) {
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                //Unitats Enemigues que salten:
                if (enemic instanceof UnitatEnemigaAtkDistanciaSalta) {
                    UnitatEnemigaAtkDistanciaSalta eS = (UnitatEnemigaAtkDistanciaSalta) enemic;
                    if (eS.isSaltant() && !eS.estaActivat() && eS.haFinalitzatAnimacio()) {
                        eS.setMort(true);
                        eS.calculaSalt(llargadaTotal);
                        eS.haSaltat();
                        unitatsSaltant.add(eS);
                    }
                }
                //Unitats Enemigues que son invisibles:
                if (enemic instanceof UnitatEnemigaInvisible) {
                    UnitatEnemigaInvisible ui = (UnitatEnemigaInvisible) enemic;
                    if (!ui.isInvisible()) {
                        controlaFiles[i] = true;
                    }
                }
                enemic.update(delta);
            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                p.update(delta);
            }
            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                p.update(delta);
            }
            for (int col = 0; col < nColumnes; col++) {
                if (unitatsAmigues[i][col] != null) {
                    unitatsAmigues[i][col].update(delta);
                }
            }
        }
    }

    /**
     * Fa update de tot el tauler
     * @param delta
     */
    public void update(int delta) {
        accionarUnitats(delta);
        comprovarMorts(true);
        dispararUnitatsAmigues();
        stopUnitatsAmigues();
        finalitzarProjectils_Enemics();
        colocarUnitatsSaltant();
        mc.comprovarColisions();
        disparaUnitatsEnemigues();
    }

    /**
     * Setter per a dibuixar la cela o no
     * @param dibuixarQuadrat
     */
    public void setDibuixarQuadrat(boolean dibuixarQuadrat) {
        this.dibuixarQuadrat = dibuixarQuadrat;
    }

    /**
     * Setter posicio per a dibuixar la cela
     * @param x CoordenadaX
     * @param y CoordenaY
     */
    public void setPosicioDibuixQuadrat(int x, int y) {
        posicioQuadrat = new int[2];
        posicioQuadrat[0] = x;
        posicioQuadrat[1] = y;
    }

    /**
     * Borra una unitat sel·leccionada amb un click
     * @param x CoordenadaX del click
     * @param y CoordenadaY del click
     */
    public void borrarUnitatAmiguesClick(int x, int y) {
        int[] PosFC = mirarCoordenadesClick(x, y);
        eliminaUnitatAmiga(PosFC[0], PosFC[1]);
    }

    /**
     * Borra totes les unitats del Tauler
     */
    public void borrarTot() {
        for (int i = 0; i < nFiles; i++) {
            for (UnitatAbstract amic : unitatsAmigues[i]) {
                if (amic != null) {
                    amic.setMort(true);
                    amic.noEfectuarSoMort();
                }
            }
            for (Object ob : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) ob;
                enemic.setMort(true);

            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                p.setMort(true);
            }

            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                p.setMort(true);
            }
        }
        comprovarMorts(false);
        finalitzarProjectils_Enemics();
    }

    /**
     * Observa si la partida a finalitzat
     * @return true si ha finalitzat
     */
    public boolean observarPartidaFinalitzada() {
        for (int i = 0; i < nFiles; i++) {
            if (!arrays_enemics[i].isEmpty()) {
                for (Object ob : arrays_enemics[i]) {
                    UnitatEnemiga enemic = (UnitatEnemiga) ob;
                    if (enemic.isHaArribat()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Getter cela
     * @param a Fila
     * @param b Columna
     * @return
     */
    public Rectangle getCela(int a, int b) {
        return celes[a][b];
    }
    /**
     * Mira si hi han enemics al tauler
     * @return true si hi han
     */
    public boolean enemicsEnTauler() {
        for (int i = 0; i < nFiles; i++) {
            if (!arrays_enemics[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
