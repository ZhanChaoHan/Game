/*
 * Timeline.java
 *
 * Created on January 24, 2008, 2:48 PM
 */

package mdes.slick.animation;

import java.util.*;

/**
 * <p>A Timeline holds a set of Fx that are fired one after another.</p>
 * @author davedes
 */
public class Timeline {
        
    /** Ticks for current Fx. */
    private int ticks = 0;
    
    /** The actions stored in this timeline. */
    private ArrayList<Fx> actions = new ArrayList<Fx>();
    
    /** The pointer for the current action. */
    private int pointer = 0;
    
    /** The current Fx action. */
    private Fx current = null;
    
    /** Whether this timeline is active (running). */
    private boolean active = false;
    
    /** Whether this timeline is looping. */
    private boolean looping = false;

    private int oldend;
    private int start = 0;
    private int end = Integer.MAX_VALUE;
    
    /**
     * Creates a new instance of Timeline that is not active and 
     * not looping.
     */
    public Timeline() {
    }
    
    /**
     * Adds the specified Fx to this Timeline.
     * @param fx the Fx to add
     */
    public void add(Fx fx) {
        actions.add(fx);
    }
    
    /**
     * Removes the specified Fx to this timeline.
     * @param fx the Fx to remove
     */
    public void remove(Fx fx) {
        actions.remove(fx);
    }
    
    /**
     * Changes the state of this timeline. If <tt>true</tt>,
     * the timeline will run, otherwise it will pause.
     * @param active whether the timeline should be running
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /** Restarts this timeline from the beginning (setting time to 0). */ 
    public void restart() {
        current = null;
        ticks = 0;
        pointer = start;
    }
    
    /** Clears all the Fx in this timeline and restarts at 0. */
    public void clear() {
        actions.clear();
        restart();
    }
    
    public int getStartIndex() {
        return start;
    }
    
    public int getEndIndex() {
        return end;
    }
    
    public int getOldEnd(){
    	return oldend;
    }
    
    
    public void setRange(int start, int end) {
        this.oldend = this.end;
        if (start > end){
            throw new IllegalArgumentException("start must be <= end");
        }
        if (start<0 || end<0){
            throw new IllegalArgumentException("start and end must be > 0");
        }
        this.start = start;
        this.end = end;
        if (pointer < start || pointer > end-1) {
            restart();
        }
    }
    
    /** 
     * Returns the number of ticks for the current Fx. 
     * @return the ticks for the current Fx, which is reset to
     *      0 when the Fx is changed
     */
    public int getTicks() {
        return ticks;
    }
    
    /**
     * Returns <tt>true</tt> if this timeline is running, <tt>false</tt>
     * if it is paused.
     * @return whether this timeline is active
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Returns the currently animating Fx.
     * @return the current fx
     */
    public Fx getCurrent() {
        if (actions.isEmpty()||pointer+1>actions.size())
            return null;
        else
            return (Fx)actions.get(pointer);
    }
    
    /**
     * Rewinds this timeline's Fx to their original states and calls
     * restart().
     */
    public void rewind() {
        for (int i=pointer; i>=0 && i<actions.size(); i--) {
            Fx fx = (Fx)actions.get(i);
            fx.rewind();
        }
        restart();
    }
        
    /*public void fastForward() {
        for (int i=pointer; i<actions.size(); i++) {
            current = (Fx)actions.get(i);
            current.fastForward();
        }
        pointer = actions.size()-1;
        current = null;
    }*/
    
    /**
     * Called to update this timeline and animate the attached Fx objects.
     * @param delta the delta time between frames
     */
    public void update(int delta) {
        if (!isActive())
            return;
        //move to next
        if (current==null && !actions.isEmpty()) {
            current = null;
            ticks = 0;
            if (pointer+1 > actions.size() || pointer+1 > end) {
                if (isLooping())
                    restart();
                else
                    setActive(false);
                return;
            }
            current = (Fx)actions.get(pointer++);
        }
        
        //update current
        if (current!=null) {
            ticks += delta;
            boolean cont = current.continueAnimation(ticks);
            if (!cont)
                current = null;
        }
    }
    
    /**
     * Returns <tt>true</tt> if this timeline should repeat from zero
     * after all Fx are finished, <tt>false</tt> otherwise. A terminating
     * (non-looping) timeline will be deactivated after it has finished.
     *
     * @return whether this timeline is looping
     */
    public boolean isLooping() {
        return looping;
    }
    
    /**
     * Sets this timeline's loop flag to <tt>looping</tt>. A terminating
     * (non-looping) timeline will be deactivated after it has finished.
     * @param looping <tt>true</tt> if this timeline should repeat from zero
     * after all Fx are finished, <tt>false</tt> otherwise
     */
    public void setLooping(boolean looping) {
        this.looping = looping;
    }
}
