package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe UnitatAbstract: Unitats Amigues que no efectuen cap atac.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatAbstract extends Entitat implements IAuraVida {

    // Vida de la unitat
    protected float vida;
    // Vida de la unitat al col·locar-la al tauler
    protected float vidaTotal;
    // Animacion de la unitat
    protected Animation animation;
    // Aura que pot equipar la unitat
    Aura aura;
    // Float per dibuixar l'aura
    protected float alphaAura = 1;
    // Indica si la unitat te una aura activa
    protected boolean auraActiva;
    protected Animation animation_mort;
    private boolean soMort = true;

    /**
     *Constructor UnitatAbstract
     * @param vida
     * @param posX
     * @param posY
     * @param image
     * @param frames
     */
    public UnitatAbstract(int vida, Image[] frames, Image[] framesMort, int milisegons) {
        this.vida = vida;
        this.vidaTotal = vida;
        this.animation = new Animation(frames, milisegons);
        this.animation_mort = new Animation(framesMort, 30);

    }

    /**
     * Constructor UnitatAbstract
     * @param vida
     * @param frames
     * @param milisegons
     */
    public UnitatAbstract(int vida, Image[] frames, int milisegons) {
        this.vida = vida;
        this.vidaTotal = vida;
        this.animation = new Animation(frames, milisegons);

    }

    /**
     * Canvia la posicio de la unitat
     * @param x
     * @param y
     */
    public void setLocation(float x, float y) {
        super.posX = x;
        super.posY = y;
        super.shape = new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());

    }

    /**
     * Renderitza la unitat
     * @param gc
     * @param g
     */
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
        renderVida(gc, g);

    }

    /**
     * Renderitza la mort de la unitat
     * @param gc
     * @param g
     */
    public void renderMort(GameContainer gc, Graphics g) {
        if (mort && animation_mort != null) {
            g.drawAnimation(animation_mort, posX + shape.getWidth() / 2, posY);
            animation_mort.setLooping(false);
        }
    }

    /**
     * Metode que especifica si s'ha de reproduïr el so de la mort de la unitat o no
     * @return
     */
    public boolean efectuarSoMort() {
        if (mort && animation_mort != null && !animation_mort.isStopped() && soMort) {
            soMort = false;
            return true;
        }
        return false;
    }

    /**
     * Metode per no efectuar el so de la mort de la unitat
     */
    public void noEfectuarSoMort() {
        soMort = false;
    }

    /**
     * Getter mort
     * @return mort
     */
    @Override
    public boolean isMort() {
        if (animation_mort != null) {
            if (mort && animation_mort.isStopped()) {
                return true;
            }
        } else {
            if (mort) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metode que genera un impacte sobre la unitat
     * @param dany
     */
    public void impacte(double dany) {
        if (vida > 0) {
            vida -= dany;
        }
        if (vida <= 0) {
            mort = true;
        }
    }

    /**
     * Renderitza la vida de la unitat
     * @param gc
     * @param g
     */
    public void renderVida(GameContainer gc, Graphics g) {
        if (vida > 0) {
            g.setColor(Color.red);
            g.fillRect(posX + 5, posY - 15, (vida / vidaTotal) * 30, 5);
        }
        if (auraActiva) {
            aura.getImage().setAlpha(0.15f);
            g.drawImage(aura.getImage(), posX, posY);
        }
        g.setColor(Color.white);
        g.drawRect(posX + 5, posY - 15, 30, 5);
    }

    /**
     * Updateja la unitat
     * @param delta
     */
    public void update(int delta) {
    }

    /**
     * Metode per activar una aura
     * @param aura
     */
    @Override
    public void activarAura(Aura aura) {
        auraActiva = true;
        this.aura = aura;
        this.aura.setBo(this);
        this.aura.activarAura();
    }

    /**
     * Metode per desactivar una aura
     */
    @Override
    public void desactivarAura() {
        auraActiva = false;
        aura.desactivarAura();
        aura = null;
    }

    /**
     * Metode que especifica si pot equipar una aura o no
     * @param aura
     * @return
     */
    public boolean potEquiparAura(Aura aura) {
        if (aura.getTipus().equals("MagVida")) {
            return true;
        }
        return false;
    }

    /**
     * Getter auraActiva
     * @return
     */
    @Override
    public boolean isAuraActiva() {
        return auraActiva;
    }

    /**
     * Setter auraActiva
     * @param auraActiva
     */
    @Override
    public void setAuraActiva(boolean auraActiva) {
        this.auraActiva = auraActiva;
    }

    /**
     * Setter AlphaAura
     * @param alphaAura
     */
    public void setAlphaAura(float alphaAura) {
        this.alphaAura = alphaAura;
    }

    /**
     * Getter Animation
     * @return animation
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * Setter animation
     * @param animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * Getter aura
     * @return arua
     */
    public Aura getAura() {
        return aura;
    }

    /**
     * Setter aura
     * @param aura
     */
    public void setAura(Aura aura) {
        this.aura = aura;
    }

    /**
     * Getter vida
     * @return vida
     */
    public float getVida() {
        return vida;
    }

    /**
     * Setter vida
     * @param vida
     */
    public void setVida(float vida) {
        this.vida = vida;
    }

    /**
     * Getter vidaTotal
     * @return
     */
    public float getVidaTotal() {
        return vidaTotal;
    }

    /**
     * Setter vudaTotal
     * @param vidaTotal
     */
    public void setVidaTotal(float vidaTotal) {
        this.vidaTotal = vidaTotal;
    }
}
