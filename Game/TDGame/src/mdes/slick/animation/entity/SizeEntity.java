/*
 * SizeEntity.java
 *
 * Created on January 24, 2008, 11:04 AM
 */

package mdes.slick.animation.entity;

/**
 * An interface that defines an entity that can be resized and animated. 
 * @author davedes
 */
public interface SizeEntity {
    /**
     * Resizes this entity width the given width and height (in pixels).
     * @param width the new width
     * @param height the new height
     */
    public void setSize(float width, float height);
    
    /**
     * Returns the width of this entity (in pixels).
     * @return the width
     */
    public float getWidth();
    
    /**
     * Returns the height of this entity (in pixels).
     * @return the height
     */
    public float getHeight();
}
