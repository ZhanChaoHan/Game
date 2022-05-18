/*
 * AbstractFx.java
 *
 * Created on January 24, 2008, 3:19 PM
 */

package mdes.slick.animation.fx;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Fx;

/**
 * A base Fx class that is used by most tweened animation Fx.
 * @author davedes
 */
public abstract class AbstractFx extends Fx {
    
    /** The easing for this Fx. */
    protected Easing easing;

    /**
     * Creates an AbstractFx with the specified duration and easing.
     * @param duration the duration to last in ms
     * @param easing the default easing
     */
    public AbstractFx(int duration, Easing easing) {
        super(duration);
        this.easing = easing;
    }

    /** 
     * Changes this animation's Easing.
     * @param easing the new easing
     */ 
    public void setEasing(Easing easing) {
        this.easing = easing;
    }

    /** 
     * Returns this animation's Easing.
     * @return the easing used by this animation
     */
    public Easing getEasing() {
        return easing;
    }
}
