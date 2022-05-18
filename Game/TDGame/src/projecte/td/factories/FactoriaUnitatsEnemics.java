package projecte.td.factories;

import projecte.td.managers.ManagerRecursos;
import projecte.td.domini.*;
import projecte.td.utilitats.*;

/**
 * Aquesta classe factory serveix enemics o unitats del tipus que se li passi com a
 * parametre.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class FactoriaUnitatsEnemics {

    // Arxiu de configuracio amb la informació de les unitats
    private static ArxiuConfiguracio unitats;
    private static ArxiuConfiguracio enemics;

    /**
     * Crea una unitat i la retorna
     * @param tipus String amb el tipus d'unitat sol·licitat
     * @return UnitatAbstact amb la unitat sol·licitada
     */
    public static UnitatAbstract getUnitatBona(String tipus) {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        UnitatAbstract bo = null;
        // Unitat Pistoler
        if (tipus.equals("Pistoler")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaPistoler"),
                    unitats.getPropietatInt("cadenciaPistoler"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationPistoler")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortPistoler")),
                    unitats.getPropietatInt("milisegonsPistoler"),
                    new ProjectilMobil(unitats.getDoublePropietat("danyProjectilPistoler"),
                    ManagerRecursos.getImage(unitats.getPropietatString("projectilPistoler"))),
                    unitats.getPropietatFloat("projXPistoler"), unitats.getPropietatFloat("projYPistoler"));
        } // Unitat Escopeta
        else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaEscopeta"),
                    unitats.getPropietatInt("cadenciaEscopeta"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscopeta")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortEscopeta")),
                    unitats.getPropietatInt("milisegonsEscopeta"),
                    new ProjectilEstatic(unitats.getDoublePropietat("danyProjectilEscopeta"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilEscopeta")),
                    unitats.getPropietatInt("milisegonsProjectilEscopeta")),
                    unitats.getPropietatFloat("projXEscopeta"), unitats.getPropietatFloat("projYEscopeta")
                  );
        } // Unitat MetralletaLleugera
        else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaMetralletaLleugera"),
                    unitats.getPropietatInt("cadenciaMetralletaLleugera"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMetralletaLleugera")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortMetralletaLleugera")),
                    unitats.getPropietatInt("milisegonsMetralletaLleugera"),
                    new ProjectilMobil(unitats.getDoublePropietat("danyProjectilMetralletaLleugera"),
                    ManagerRecursos.getImage(unitats.getPropietatString("projectilMetralletaLleugera"))),
                    unitats.getPropietatFloat("projXMetralletaLleugera"), unitats.getPropietatFloat("projYMetralletaLleugera"));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaCaixa"),
                    unitats.getPropietatInt("cadenciaCaixa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationCaixa")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortCaixa")),
                    unitats.getPropietatInt("milisegonsCaixa"),
                    new ProjectilMobil(unitats.getDoublePropietat("danyProjectilCaixa"),
                    ManagerRecursos.getImage(unitats.getPropietatString("projectilCaixa"))),
                    unitats.getPropietatFloat("projXCaixa"), unitats.getPropietatFloat("projYCaixa"));
        }// Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaFoc"),
                    unitats.getPropietatInt("cadenciaFoc"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationFoc")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortFoc")),
                    unitats.getPropietatInt("milisegonsFoc"),
                    new ProjectilEstatic(unitats.getDoublePropietat("danyProjectilFoc"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilFoc")),
                    unitats.getPropietatInt("milisegonsProjectilFoc")),
                    unitats.getPropietatFloat("projXFoc"), unitats.getPropietatFloat("projYFoc"));
        }// Unitat Miner
        else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMiner")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortMiner")),
                    unitats.getPropietatInt("milisegonsMiner"), unitats.getPropietatString("tipusMiner")
                    );
        } // Unitat Mag Vida
        else if (tipus.equals("MagVida")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagVida"),
                    unitats.getPropietatInt("cadenciaMagVida"),
                    unitats.getPropietatInt("capacitatMagVida"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagVida")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortMagVida")),
                    unitats.getPropietatInt("milisegonsMagVida"), unitats.getPropietatString("tipusMagVida"));
        } // Unitat Mag Rapidesa
        else if (tipus.equals("MagRapidesa")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagRapidesa"),
                    unitats.getPropietatInt("cadenciaMagRapidesa"),
                    unitats.getPropietatInt("capacitatMagRapidesa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagRapidesa")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortMagRapidesa")),
                    unitats.getPropietatInt("milisegonsMagRapidesa"), unitats.getPropietatString("tipusMagRapidesa"));
        } // Unitat Escut
        else if (tipus.equals("Escut")) {
            bo = new UnitatAbstract(unitats.getPropietatInt("vidaEscut"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscut")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortEscut")),
                    unitats.getPropietatInt("milisegonsEscut"));
        }//Unitat Aigua
        else if (tipus.equals("Aigua")) {
            bo = new UnitatAigua(unitats.getPropietatInt("vidaAigua"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationAigua")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMortAigua")),
                    unitats.getPropietatInt("milisegonsAigua"));
        } // Unitat Bomba
        else if (tipus.equals("Bomba")) {
            bo = new Bomba(unitats.getPropietatInt("vidaBomba"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBomba")),
                    unitats.getPropietatInt("milisegonsBomba"),
                    new ProjectilEstatic(unitats.getDoublePropietat("danyProjectilBomba"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilBomba")),
                    unitats.getPropietatInt("milisegonsProjectilBomba")));
        } // Unitat Mina
        else if (tipus.equals("Mina")) {
            bo = new Mina(unitats.getPropietatInt("vidaMina"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMina")),
                    unitats.getPropietatInt("milisegonsMina"),
                    new ProjectilEstatic(unitats.getDoublePropietat("danyProjectilMina"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilMina")),
                    unitats.getPropietatInt("milisegonsProjectilMina")));
        } // Unitat Motorista
        else if (tipus.equals("Motorista")) {
            bo = new Motorista(unitats.getPropietatInt("vidaMotorista"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMotorista")),
                    unitats.getPropietatInt("milisegonsMotorista"),
                    new ProjectilAnimat(unitats.getDoublePropietat("danyProjectilMotorista"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilMotorista")),
                    unitats.getPropietatInt("milisegonsProjectilMotorista")));
        } // Unitat BombaAerea
        else if (tipus.equals("BombaAerea")) {
            bo = new BombaAerea(unitats.getPropietatInt("vidaBombaAerea"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBombaAerea")),
                    unitats.getPropietatInt("milisegonsBombaAerea"),
                    new ProjectilEstatic(unitats.getDoublePropietat("danyProjectilBombaAerea"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("projectilBombaAerea")),
                    unitats.getPropietatInt("milisegonsProjectilBombaAerea")));
        }
        return bo;
    }

    /**
     * Crea un enemic i el retorna
     * @param tipus String amb el tipus d'enemic sol·licitat
     * @return UnitatAbstact amb l'enemic sol·licitada
     */
    public static UnitatAbstract getUnitatDolenta(String tipus) {
        if (enemics == null) {
            enemics = Configuracio.getEnemics();
        }
        UnitatAbstract dolent = null;
        //Enemic Momia
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(enemics.getPropietatInt("vidaMomia"),
                    enemics.getPropietatInt("cadenciaMomia"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMomia1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortMomia")),
                    enemics.getPropietatInt("milisegonsMomia1"),
                    new ProjectilEstatic(enemics.getDoublePropietat("atacProjectilMomia"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("projectilMomia")),
                    enemics.getPropietatInt("milisegonsProjectilMomia")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMomia2")),
                    enemics.getDoublePropietat("velocitatMomia"), enemics.getPropietatInt("milisegonsMomia2"),
                    enemics.getPropietatFloat("projXMomia"), enemics.getPropietatFloat("projYMomia"));
        } //Enemic Natiu
        else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(enemics.getPropietatInt("vidaNatiu"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationNatiu1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortNatiu")),
                    enemics.getPropietatInt("milisegonsNatiu1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationNatiu2")),
                    enemics.getDoublePropietat("velocitatNatiu"), enemics.getPropietatInt("milisegonsNatiu2"),
                    enemics.getDoublePropietat("danyNatiu"));
        } //Enemic Espasa
        else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(enemics.getPropietatInt("vidaEspasa"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationEspasa1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortEspasa")),
                    enemics.getPropietatInt("milisegonsEspasa1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationEspasa2")),
                    enemics.getDoublePropietat("velocitatEspasa"), enemics.getPropietatInt("milisegonsEspasa2"),
                    enemics.getDoublePropietat("danyEspasa"));
        } //Enemic Ufo
        else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(enemics.getPropietatInt("vidaUfo"),
                    enemics.getPropietatInt("cadenciaUfo"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationUfo1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortUfo")),
                    enemics.getPropietatInt("milisegonsUfo1"),
                    new ProjectilEstatic(enemics.getDoublePropietat("atacProjectilUfo"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("projectilUfo")),
                    enemics.getPropietatInt("milisegonsProjectilUfo")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationUfo2")),
                    enemics.getDoublePropietat("velocitatUfo"), enemics.getPropietatInt("milisegonsUfo2"),
                    enemics.getPropietatFloat("projXUfo"), enemics.getPropietatFloat("projYUfo"));
        } //Enemic Cranc
        else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(enemics.getPropietatInt("vidaCranc"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationCranc1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortCranc")),
                    enemics.getPropietatInt("milisegonsCranc1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationCranc2")),
                    enemics.getDoublePropietat("velocitatCranc"), enemics.getPropietatInt("milisegonsCranc2"),
                    enemics.getDoublePropietat("danyCranc"));
        } //Enemic Robot
        else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistanciaSalta(enemics.getPropietatInt("vidaRobot"),
                    enemics.getPropietatInt("cadenciaRobot"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationRobot1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortRobot")),
                    enemics.getPropietatInt("milisegonsRobot1"),
                    new ProjectilEstatic(enemics.getDoublePropietat("atacProjectilRobot"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("projectilRobot")),
                    enemics.getPropietatInt("milisegonsProjectilRobot")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationRobot2")),
                    enemics.getDoublePropietat("velocitatRobot"), enemics.getPropietatInt("milisegonsRobot2"),
                    enemics.getPropietatFloat("projXRobot"), enemics.getPropietatFloat("projYRobot"), 
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationRobot3")));
        } //Enemic Cuc
        else if (tipus.equals("Cuc")) {
            dolent = new UnitatEnemigaAtkNormal(enemics.getPropietatInt("vidaCuc"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationCuc1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortCuc")),
                    enemics.getPropietatInt("milisegonsCuc1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationCuc2")),
                    enemics.getDoublePropietat("velocitatCuc"), enemics.getPropietatInt("milisegonsCuc2"),
                    enemics.getDoublePropietat("danyCuc"));
        } //Enemic Insecte
        else if (tipus.equals("Insecte")) {
            dolent = new UnitatEnemigaVola(enemics.getPropietatInt("vidaInsecte"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationInsecte1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortInsecte")),
                    enemics.getPropietatInt("milisegonsInsecte"), enemics.getDoublePropietat("velocitatInsecte"),
                    enemics.getDoublePropietat("danyInsecte"));
        } //Enemic Gos
        else if (tipus.equals("Gos")) {
            dolent = new UnitatEnemigaInvisible(enemics.getPropietatInt("vidaGos"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationGos1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortGos")),
                    enemics.getPropietatInt("milisegonsGos1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationGos2")),
                    enemics.getDoublePropietat("velocitatGos"), enemics.getPropietatInt("milisegonsGos2"),
                    enemics.getDoublePropietat("danyGos"),
                    ManagerRecursos.getImageArray("gosCaminaAnimation"),
                    ManagerRecursos.getImageArray("gosInvisibleAnimation"), enemics.getPropietatInt("milisegonsGos3"),
                    enemics.getPropietatInt("milisegonsGos4"));
        } //Enemic Zombie
        else if (tipus.equals("Zombie")) {
            dolent = new UnitatEnemigaExplosio(enemics.getPropietatInt("vidaZombie"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationZombie1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortZombie")),
                    enemics.getPropietatInt("milisegonsZombie1"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationZombie2")),
                    enemics.getDoublePropietat("velocitatZombie"), enemics.getPropietatInt("milisegonsZombie2"),
                    enemics.getDoublePropietat("danyZombie"));
        } //Enemic BolaNeu
        else if (tipus.equals("BolaNeu")) {
            dolent = new UnitatEnemigaBolaNeu(enemics.getPropietatInt("vidaBolaNeu"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationBolaNeu1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortBolaNeu")),
                    enemics.getPropietatInt("milisegonsBolaNeu"), enemics.getDoublePropietat("velocitatBolaNeu"),
                    enemics.getDoublePropietat("danyBolaNeu"));
        } //Enemic Yeti
        else if (tipus.equals("Yeti")) {
            dolent = new UnitatEnemigaAtkDistancia(enemics.getPropietatInt("vidaYeti"),
                    enemics.getPropietatInt("cadenciaYeti"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationYeti1")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationMortYeti")),
                    enemics.getPropietatInt("milisegonsYeti1"),
                    new ProjectilEstatic(enemics.getDoublePropietat("atacProjectilYeti"),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("projectilYeti")),
                    enemics.getPropietatInt("milisegonsProjectilYeti")),
                    ManagerRecursos.getImageArray(enemics.getPropietatString("animationYeti2")),
                    enemics.getDoublePropietat("velocitatYeti"), enemics.getPropietatInt("milisegonsYeti2"),
                    enemics.getPropietatFloat("projXYeti"), enemics.getPropietatFloat("projYYeti"));
        }
        return dolent;
    }
}
