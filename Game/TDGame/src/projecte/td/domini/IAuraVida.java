package projecte.td.domini;

/**
 * Interficie Aura Vida: Utilitzada per saber si les unitats amigues
 * poden equipar aquesta aura.
 * @author Ernest Daban i David Alvarez
 */
public interface IAuraVida {

    //Observa si pot equipar una aura
    public boolean potEquiparAura(Aura aura);

    //Activa una aura
    public void activarAura(Aura aura);

    //Desactiva una aura
    public void desactivarAura();

    //Getter AuraActiva
    public boolean isAuraActiva();
    
    //Setter AuraActiva
    public void setAuraActiva(boolean activa);
}
