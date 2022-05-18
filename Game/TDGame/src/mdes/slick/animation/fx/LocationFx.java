/*
 * LocationFx.java
 *
 * Created on January 24, 2008, 4:04 PM
 */

package mdes.slick.animation.fx;

import mdes.slick.animation.Easing;
import mdes.slick.animation.entity.LocationEntity;

/**
 *
 * @author davedes
 */
public class LocationFx extends AbstractFx {
    
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private LocationEntity entity;
    
    public LocationFx(int duration, LocationEntity entity, float startX, float startY, float endX, float endY, Easing easing) {
        super(duration, easing);
        this.entity = entity;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    public LocationFx(int duration, LocationEntity entity, float endX, float endY, Easing easing) {
        this(duration, entity, entity.getX(), entity.getY(), endX, endY, easing);
    }
    
    public void animate(int time) {
        float changeX = endX-startX;
        float changeY = endY-startY;
        float duration = (float)getDuration();
        float nx = easing.ease(time, startX, changeX, duration);
        float ny = easing.ease(time, startY, changeY, duration);
        entity.setLocation(nx, ny);
    }
    
    public void rewind() {
        getEntity().setLocation(startX, startY);
    }
    
    public void fastForward() {
        getEntity().setLocation(endX, endY);
    }
    
    public String toString() {
        return "MoveFx";
    }

    public LocationEntity getEntity() {
        return entity;
    }

    public void setEntity(LocationEntity entity) {
        this.entity = entity;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}