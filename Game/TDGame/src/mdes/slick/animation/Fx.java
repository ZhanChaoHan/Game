/*
 * Fx.java
 *
 * Created on January 24, 2008, 12:28 PM
 */

package mdes.slick.animation;

/**
 * The Fx class is responsible for producing an animated effect
 * (usually on an overlay) for the allotted duration. Subclasses
 * should provide functionality in the animate, rewind and fastForward
 * methods. Those seeking an empty Fx (ie: a delay) should see EmptyFx.
 * 
 * @author davedes
 */
public abstract class Fx {
    
    /** The duration this effect will last for. */
    private int duration = 0;
    
    /** 
     * Creates a new instance of Fx with the specified duration. 
     * @param duration the duration this animation should last for (in ticks, roughly ms)
     */
    public Fx(int duration) {
        this.setDuration(duration);
    }
    
    /** 
     * Called by the timeline to continue the animation. If this 
     * method returns false, the timeline will move on to the next 
     * animation. Most subclasses should not override this method.
     * 
     * @param time the Timeline's current ticks
     * @return <tt>true</tt> if this animation is still ongoing
     */
    protected boolean continueAnimation(int time) {
        if (time > getDuration()) { //finished
            return false;
        }
        animate(time);
        return true;
    }
    
    /**
     * Animates this Fx with the specified current time (ticks).
     * @param time the current time, either in frames or in 
     *      seconds/milliseconds
     */
    public abstract void animate(int time);
    
    /** Rewinds this Fx to the start state. */
    public abstract void rewind();
    
    /** Fast forwards this Fx to the end state. */
    public abstract void fastForward();
    
    /**
     * Returns the duration this Fx is to last.
     * @return the duration for this Fx
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration for this Fx.
     * @param duration the new duration to use
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
