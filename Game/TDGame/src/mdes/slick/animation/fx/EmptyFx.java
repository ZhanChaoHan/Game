/*
 * EmptyFx.java
 *
 * Created on January 24, 2008, 4:00 PM
 */

package mdes.slick.animation.fx;

import mdes.slick.animation.Fx;

/**
 *
 * @author davedes
 */
public class EmptyFx extends Fx {
    
    /**
     * Creates a new instance of EmptyFx
     */
    public EmptyFx(int duration) {
        super(duration);
    }
    
    public void animate(int time) { 
    }
    
    public void rewind() {
    }
    
    public void fastForward() {
    }
    
    public String toString() {
        return "EmptyFx";
    }
}
