/*
 * ImageAlpha.java
 *
 * Created on January 24, 2008, 6:34 PM
 */

package mdes.slick.animation.entity.renderable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A helper class for creating a RenderableAlpha from an Image.
 * @author davedes
 */
public class ImageAlpha implements RenderableAlpha {
    
    /** The filter used in drawing (for alpha). */
    protected Color filter = new Color(1f, 1f, 1f, 1f);
    
    /** The image used in rendering.*/
    protected Image image;
    
    /** 
     * Creates an ImageAlpha from the specified image ref (creates a regular Image). 
     * @param str the ref for the image
     */
    public ImageAlpha(String str) throws SlickException {
        this(new Image(str));
    }
    
    /** 
     * Creates an ImageAlpha from the specified Image. 
     * @param image the image for this entity
     */
    public ImageAlpha(Image image) {
        this.image = image;
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
     * Returns the filter used in rendering the image.
     * @return the color filter that handles alpha
     */
    public Color getFilter() {
        return filter;
    }

    /**
     * Renders the image with the specified location and scale.
     * @param x the x location
     * @param y the y location
     * @param width the width
     * @param height the height
     */
    public void draw(float x, float y, float width, float height) {
        image.draw(x, y, width, height, filter);
    }
    
    /**
     * Renders the image with the specified location.
     * @param x the x location
     * @param y the y location
     */
    public void draw(float x, float y) {
        image.draw(x, y, filter);
    } 

    /**
     * Returns the image for this entity.
     * @return the image used in rendering 
     */
    public Image getImage() {
        return image;
    }

    /**
     * Changes the image for this entity.
     * @param image the new image to use in rendering
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
