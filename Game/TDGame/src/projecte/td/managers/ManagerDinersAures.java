package projecte.td.managers;

/**
 * S'encarrega de gestionar els diners de que disposa l'usuari durant el transcurs
 * del joc. També realitza les transaccions pertinents quen es realitza la compra
 * d'una unitat.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class ManagerDinersAures {

    // Quantitat total de diners acumulats
    private int total;
    // Indica si hi ha una aura en espera per ser col·locada
    private boolean auraEnEspera;
    // Indica quin tipus d'aura hi ha en espera (si n'hi ha).
    private String tipusAuraEspera;

    /**
     * Constructor sense parametres
     */
    public ManagerDinersAures() {
        total = 200;
    }

    /**
     * S'afegeix una certa quantitat de diners als ja acumulats
     * @param quantitat : diners a sumar
     */
    public void afegirDiners(int quantitat) {
        total += quantitat;
        ManagerPerfil.sumaDiners(quantitat);
    }

    /**
     * Es reta una certa quantitat de diners als ja acumulats
     * @param quantitat : diners a restar
     */
    public void restarDiners(int quantitat) {
        total -=quantitat;
    }

    /**
     * Indica si hi ha suficients recursos per poguer
     * @param quantitat
     * @return
     */
    public boolean suficientsDinerst(int quantitat) {
        if (total - quantitat >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Retorna els diners que es porten acumulats fins ara
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * Col·loca els diners acumulats a la quantitat desitjada
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Reseteja els valors de les aures per tal de que no n'hi hagi cap de
     * disponible
     */
    public void clearAures() {
        auraEnEspera = false;
        tipusAuraEspera = "null";
    }

    /**
     * Indica si hi ha una aura en espera
     * @return true si hi ha una aura en espera, false en cas contrari
     */
    public boolean isAuraEnEspera() {
        return auraEnEspera;
    }

    /**
     * Retorna el tipus d'aura que hi ha en espera
     * @return String amb la informació de l'aura que hi ha esperant
     */
    public String getTipusAuraEspera() {
        return tipusAuraEspera;
    }

    /**
     * Col·loca el tipus d'aura desitjat en l'aura que hi ha activa esperant
     * @param tipusAuraEspera
     */
    public void setTipusAuraEspera(String tipusAuraEspera) {
        auraEnEspera = true;
        this.tipusAuraEspera = tipusAuraEspera;
    }
}
