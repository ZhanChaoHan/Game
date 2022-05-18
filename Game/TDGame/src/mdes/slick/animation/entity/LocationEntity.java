/*
 * LocationEntity.java
 *
 * Created on January 24, 2008, 4:04 PM
 */

package mdes.slick.animation.entity;

/**
 * An interface that defines an entity that can be moved and animated. 
 * @author davedes
 */
public interface LocationEntity extends FxEntity {
    
    /**
     * Returns the current x location in the (x,y) coordinate space.
     * @return the x location
     */
    public float getX();
    
    /**
     * Returns the current y location in the (x,y) coordinate space.
     * @return the y location
     */
    public float getY();
    
    /**
     * Moves this entity to the specified location.
     * @param x the new x position
     * @param y the new y position
     */
    public void setLocation(float x, float y);
}
