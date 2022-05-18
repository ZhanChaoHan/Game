package mdes.slick.animation.fx;

import mdes.slick.animation.Easing;
import mdes.slick.animation.entity.SizeEntity;

public class SizeFx extends AbstractFx {
    
    private float startWidth;
    private float startHeight;
    private float endWidth;
    private float endHeight;
    private SizeEntity entity;
    
    public SizeFx(int duration, SizeEntity entity, float startWidth, float startHeight, float endWidth, float endHeight, Easing easing) {
        super(duration, easing);
        this.entity = entity;
        this.startWidth = startWidth;
        this.startHeight = startHeight;
        this.endWidth = endWidth;
        this.endHeight = endHeight;
    }
    
    public SizeFx(int duration, SizeEntity entity, float endWidth, float endHeight, Easing easing) {
        this(duration, entity, entity.getWidth(), entity.getHeight(), endWidth, endHeight, easing);
    }
    
    public void setEntity(SizeEntity entity) {
        this.entity = entity;
    }
    
    public SizeEntity getEntity() {
        return entity;
    }
    
    public void animate(int time) {
        float changeWidth = endWidth-startWidth;
        float changeHeight = endHeight-startHeight;
        float duration = (float)getDuration();
        float nw = easing.ease(time, startWidth, changeWidth, duration);
        float nh = easing.ease(time, startHeight, changeHeight, duration);
        entity.setSize(nw, nh);
    }
    
    public void rewind() {
        entity.setSize(startWidth, startHeight);
    }
    
    public void fastForward() {
        entity.setSize(endWidth, endHeight);
    }
    
    public String toString() {
        return "ResizeFx";
    }

    public float getStartWidth() {
        return startWidth;
    }

    public void setStartWidth(float startWidth) {
        this.startWidth = startWidth;
    }

    public float getStartHeight() {
        return startHeight;
    }

    public void setStartHeight(float startHeight) {
        this.startHeight = startHeight;
    }

    public float getEndWidth() {
        return endWidth;
    }

    public void setEndWidth(float endWidth) {
        this.endWidth = endWidth;
    }

    public float getEndHeight() {
        return endHeight;
    }

    public void setEndHeight(float endHeight) {
        this.endHeight = endHeight;
    }
}