/*
 * AlphaFx.java
 *
 * Created on January 24, 2008, 4:21 PM
 */

package mdes.slick.animation.fx;

import mdes.slick.animation.Easing;
import mdes.slick.animation.entity.AlphaEntity;

/**
 * A fade animation that affects the specified AlphaEntity.
 * 
 * @author davedes
 */
public class AlphaFx extends AbstractFx {
    
    /** The start alpha. */
    private float startAlpha;
    
    /** The end alpha. */
    private float endAlpha;
    
    /** The alpha entity. */
    private AlphaEntity entity;
    
    /**
     * Creates a new instance of AlphaFx with the specified parameters.
     *
     * @param duration the duration this Fx should last for
     * @param entity the entity to modify
     * @param startAlpha the starting alpha
     * @param endAlpha the ending alpha
     * @param easing the easing
     */
    public AlphaFx(int duration, AlphaEntity entity, float startAlpha, float endAlpha, Easing easing) {
        super(duration, easing);
        this.entity = entity;
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
    }
    
    /**
     * Creates a new instance of AlphaFx with the specified parameters and
     * the start at the entity's current alpha (through getAlpha).
     *
     * @param duration the duration this Fx should last for
     * @param entity the entity to modify
     * @param endAlpha the ending alpha
     * @param easing the easing
     */
    public AlphaFx(int duration, AlphaEntity entity, float endAlpha, Easing easing) {
        this(duration, entity, entity.getAlpha(), endAlpha, easing);
    }
    
    public void setEntity(AlphaEntity entity) {
        this.entity = entity;
    }
    
    public AlphaEntity getEntity() {
        return entity;
    }
    
    public void animate(int time) {
        float change = endAlpha-startAlpha;
        float duration = (float)getDuration();
        float a = easing.ease(time, startAlpha, change, duration);
        if (a<0f)
            a=0f;
        else if (a>1f)
            a=1f;
        entity.setAlpha(a);
    }
    
    public void rewind() {
        entity.setAlpha(startAlpha);
    }
    
    public void fastForward() {
        entity.setAlpha(endAlpha);
    }
    
    public String toString() {
        return "FadeFx";
    }

    public float getStartAlpha() {
        return startAlpha;
    }

    public void setStartAlpha(float startAlpha) {
        this.startAlpha = startAlpha;
    }

    public float getEndAlpha() {
        return endAlpha;
    }

    public void setEndAlpha(float endAlpha) {
        this.endAlpha = endAlpha;
    }
}