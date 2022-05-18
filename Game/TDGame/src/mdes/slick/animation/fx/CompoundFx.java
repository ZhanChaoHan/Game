/*
 * CompoundFx.java
 *
 * Created on January 24, 2008, 7:24 PM
 */

package mdes.slick.animation.fx;

import mdes.slick.animation.Fx;

/**
 * An Fx that animates two Fx's synchronously.
 * @author davedes
 */
public class CompoundFx extends Fx {
    
    protected Fx one;
    protected Fx other;
    
    /** Creates a new instance of CompoundFx */
    public CompoundFx(Fx one, Fx other) {
        super(Math.max(one.getDuration(), other.getDuration()));
        this.one = one;
        this.other = other;
    }
    
    public void animate(int time) {
        one.animate(time);
        other.animate(time);
    }

    public void rewind() {
        one.rewind();
        other.rewind();
    }

    public void fastForward() {
        one.fastForward();
        other.fastForward();
    }
}
