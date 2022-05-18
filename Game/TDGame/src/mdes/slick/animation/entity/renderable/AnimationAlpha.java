/*
 * AnimationAlpha.java
 *
 * Created on January 24, 2008, 6:41 PM
 */

package mdes.slick.animation.entity.renderable;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;

/**
 * A helper class for creating a RenderableAlpha from an Animation.
 * @author davedes
 */
public class AnimationAlpha implements RenderableAlpha {
    
    /** The filter used in drawing (for alpha). */
    protected Color filter = new Color(1f, 1f, 1f, 1f);
    
    /** The animation to render. */
    protected Animation animation;
    
    /**
     * Creates a new instance of this class with the specified animation.
     * @param animation the animation to use
     */
    public AnimationAlpha(Animation animation) {
        this.animation = animation;
    }
    
    /**
     * Returns this entity's alpha as a percentage.
     * @return the alpha, between 0 and 1
     */
    public float getAlpha() {
        return filter!=null ? filter.a : 0f;
    }

    /**
     * Sets this entity's alpha to the given percentage.
     * @param alpha the new alpha, between 0 and 1
     */
    public void setAlpha(float alpha) {
        if (filter!=null)
            filter.a = alpha;
    }

    /**
     * Returns the filter used in rendering the animation.
     * @return the color filter that handles alpha
     */
    public Color getFilter() {
        return filter;
    }

    /**
     * Renders the animation at the specified location.
     * @param x the x location
     * @param y the y location
     */
    public void draw(float x, float y) {
        animation.draw(x, y, filter);
    }
    
    /**
     * Returns the animation for this entity.
     * @return the animation
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * Sets the animation for this entity.
     * @param animation the new animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}