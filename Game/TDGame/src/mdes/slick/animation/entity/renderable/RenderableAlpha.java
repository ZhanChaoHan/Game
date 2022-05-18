/*
 * RenderableAlpha.java
 *
 * Created on January 24, 2008, 6:34 PM
 */

package mdes.slick.animation.entity.renderable;

import mdes.slick.animation.entity.AlphaEntity;
import org.newdawn.slick.Renderable;

/**
 * A Slick Renderable that supports alpha change and animation. This
 * class is provided for convenience, along with AnimationAlpha and 
 * ImageAlpha. Using RenderableAlpha means that an AlphaFx can be 
 * applied to any alpha renderable, in most cases an Image or an Animation.
 * 
 * @author davedes
 */
public interface RenderableAlpha extends Renderable, AlphaEntity {
}
