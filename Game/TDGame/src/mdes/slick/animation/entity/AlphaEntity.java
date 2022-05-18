/*
 * AlphaEntity.java
 *
 * Created on January 24, 2008, 4:20 PM
 */

package mdes.slick.animation.entity;

/**
 * An interface that defines an entity that can be translucent and animated.
 * @author davedes
 */
public interface AlphaEntity {
    /**
     * Returns this entity's alpha as a percentage.
     * @return the alpha, between 0 and 1
     */
    public float getAlpha();
    
    /**
     * Sets this entity's alpha to the given percentage.
     * @param alpha the new alpha, between 0 and 1
     */
    public void setAlpha(float alpha);
}
