package projecte.td.domini;

/**
 * Classe Projectil: Objecte que dispara una unitat Amiga o Enemiga.
 * @author Ernest Daban i David Alvarez
 */
public abstract class Projectil extends Entitat {

    protected double dany;//Dany produït per el projectil

    /**
     * Constructor classe Projectil
     * @param dany
     */
    public Projectil(double dany) {
        this.dany = dany;
    }

    /**
     * Canvia la posició del projectil
     * @param posX
     * @param posY
     */
    public void setLocation(float posX, float posY) {
        super.posX = posX;
        super.posY = posY;
    }

    /**
     * Updateja el projectil
     * @param delta
     */
    public void update(int delta) {
    }

    /**
     * Metode per simular un impacte
     */
    public abstract void impacte();

    /**
     * Metode emprat per a clonar un projectil
     * @return
     */
    public abstract Projectil cloneProjectil();

    /**
     * Getter dany
     * @return dany
     */
    public double getDany() {
        return dany;
    }

    /**
     * Setter dany
     * @param dany
     */
    public void setDany(int dany) {
        this.dany = dany;
    }
}
