package projecte.td.componentGUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Contenidor per mostrar imatges
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class LabelSeleccio {

    // Imatge de la carta
    private Image imatgeCarta;
    // Imatge del enemic
    private Image imatgeEnemic;
    // Posicio x de la label
    private int posX;
    // Posicio y de la label
    private int posY;

    /**
     * Constructor amb 2 parametres
     * @param imatgeCarta imatge de la carta
     * @param imatgeEnemic imatge de l'enemic
     */
    public LabelSeleccio(Image imatgeCarta, Image imatgeEnemic) {
        this.imatgeCarta = imatgeCarta;
        this.imatgeEnemic = imatgeEnemic;
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(imatgeCarta, posX, posY);
        g.drawImage(imatgeEnemic, posX + 15, posY + 15);
    }

    /**
     * Posiciona el boto a les coordenades indicades
     * @param x posicio x del label
     * @param y posicio y del label
     */
    public void setLocation(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // Getters i setters
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
