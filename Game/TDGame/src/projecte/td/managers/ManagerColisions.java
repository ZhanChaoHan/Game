package projecte.td.managers;

import projecte.td.domini.*;
import java.util.ArrayList;

/**
 * S'encarrega de comprovar si hi han col·lisions entre unitats, projectils i enemics
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerColisions {

    // Arrai bidimensional on s'hi guarden les unitats amigues
    private UnitatAbstract[][] unitatsAmigues;
    // Arrais on es guarden els enemics de cada carril (per separat)
    ArrayList[] arrays_enemics;
    // Arrais on es guarden els projectils amics de cada carril (per separat)
    ArrayList[] arrays_projectils_amics;
    // Arrais on es guarden els projectils enemics de cada carril (per separat)
    ArrayList[] arrays_projectils_enemics;
    // Indica si hi han enemics en els carrils
    private boolean[] controlaFiles;
    
    /**
     * Constructor amb diversos parametres
     * @param arrays_enemics: array d'enemics
     * @param arrays_projectils_amics : array de projectils amics
     * @param arrays_projectils_enemics: array de projectils enemics
     * @param controlaFiles
     * @param unitatsAmigues
     */
    public ManagerColisions(ArrayList[] arrays_enemics, ArrayList[] arrays_projectils_amics, ArrayList[] arrays_projectils_enemics, boolean[] controlaFiles, UnitatAbstract[][] unitatsAmigues) {
        this.arrays_enemics = arrays_enemics;
        this.arrays_projectils_amics = arrays_projectils_amics;
        this.arrays_projectils_enemics = arrays_projectils_enemics;
        this.controlaFiles = controlaFiles;
        this.unitatsAmigues = unitatsAmigues;
    }

    /**
     * S'encarrega de comprovar si es produeix una col·lisió entre els elements
     * que poden col·lisionar
     */
    public void comprovarColisions() {
        //Colisions Projectils Amics-Enemics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (controlaFiles[i]) {
                for (Object ob1 : arrays_enemics[i]) {
                    UnitatAbstract enemic = (UnitatAbstract) ob1;
                    for (Object ob2 : arrays_projectils_amics[i]) {
                        Projectil p = (Projectil) ob2;
                        // Es comprova si la unitat es del tipus invisible
                        if (enemic instanceof UnitatEnemigaInvisible) {
                            UnitatEnemigaInvisible ui = (UnitatEnemigaInvisible) enemic;
                            ui.canviShape();
                            if (!ui.isInvisible() && ui.collideWith(p.getShape())) {
                                enemic.impacte(p.getDany());
                                p.impacte();
                            }
                            ui.segonCanviShape();
                        } // Es comprova si l'enemic impacta amb un projectil amic
                        else if (enemic.collideWith(p.getShape())) {
                            if (p instanceof ProjectilMobil) {
                                if (!p.isMort()) {
                                    enemic.impacte(p.getDany());
                                }
                            } else {
                                enemic.impacte(p.getDany());
                            }
                            p.impacte();
                        }
                    }
                }
            }
        }
        //Colisions Projectils Enemics-Amics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (controlaFiles[i]) {
                for (int col = 0; col < unitatsAmigues[i].length; col++) {
                    if (unitatsAmigues[i][col] != null) {
                        for (Object ob : arrays_projectils_enemics[i]) {
                            Projectil p = (Projectil) ob;
                            if (unitatsAmigues[i][col].collideWith(p.getShape())) {
                                unitatsAmigues[i][col].impacte(p.getDany());
                                p.impacte();
                            }
                        }
                    }
                }
            }
        }
        //Colisions Enemics-Amics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (!arrays_enemics[i].isEmpty()) {
                for (Object ob1 : arrays_enemics[i]) {
                    UnitatEnemiga enemic = (UnitatEnemiga) ob1;
                    for (UnitatAbstract unitatAmiga : unitatsAmigues[i]) {
                        if (unitatAmiga != null) {
                            if (unitatAmiga.collideWith(enemic.getShape()) && unitatAmiga.getPosX() <= enemic.getPosX()) {
                                if (enemic instanceof UnitatEnemigaAtkDistancia) {
                                    UnitatEnemigaAtkDistancia enemicD = (UnitatEnemigaAtkDistancia) enemic;
                                    enemicD.activarDispars();
                                } else if (enemic instanceof UnitatEnemigaAtkNormal) {
                                    UnitatEnemigaAtkNormal enemicN = (UnitatEnemigaAtkNormal) enemic;
                                    enemicN.setActivat(true);
                                    //Bola Neu
                                    if (enemic instanceof UnitatEnemigaBolaNeu) {
                                        if (unitatAmiga instanceof UnitatAigua) {
                                            UnitatEnemigaBolaNeu un = (UnitatEnemigaBolaNeu) enemic;
                                            un.rebreAigua();
                                        } else {
                                            unitatAmiga.impacte(enemicN.getDany());
                                        }
                                    } else {
                                        unitatAmiga.impacte(enemicN.getDany());
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
