/*
 * TestFx.java
 *
 * Created on January 24, 2008, 2:12 PM
 */

package mdes.slick.animation.test;

import mdes.slick.animation.fx.*;
import mdes.slick.animation.Easing;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.entity.SizeEntity;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.AlphaEntity;
import org.newdawn.slick.*;

/**
 *
 * @author davedes
 */
public class TestFx extends BasicGame {
    
    public static void main(String[] args) throws Exception {
        AppGameContainer app = new AppGameContainer(new TestFx());
        app.setDisplayMode(800,600,false);
        app.start();
    }
    
    /** Creates a new instance of TestFx */
    public TestFx() {
        super("TestFx");
    }
    
    private MyComponent myComponent;
    private Timeline resize, motion, fade;
    
    public void init(GameContainer container) throws SlickException {
        myComponent = new MyComponent(new Image("res/test/globe.png"));
        container.getGraphics().setBackground(new Color(118, 118, 118));
        container.setMinimumLogicUpdateInterval(1);
        container.setMaximumLogicUpdateInterval(1);
        
        float startWidth = myComponent.getWidth();
        float startHeight = myComponent.getHeight();
        float endWidth = myComponent.getWidth()*1.35f;
        float endHeight = myComponent.getHeight()*1.35f;
        Easing ease = Easing.ELASTIC_OUT;
        
        resize = new Timeline();
        resize.add(new SizeFx(2000, myComponent, endWidth, endHeight, ease));
        resize.add(new SizeFx(1000, myComponent, endWidth, endHeight, startWidth, startHeight, ease));
        resize.add(new EmptyFx(1000));
        resize.setLooping(true);
        
        float startX = myComponent.getX();
        float startY = myComponent.getY();
        float endX = myComponent.getX()*2.55f;
        float endY = myComponent.getY();
        ease = Easing.BOUNCE_OUT;
        
        motion = new Timeline();
        motion.add(new LocationFx(3000, myComponent, endX, endY, ease));
        motion.add(new LocationFx(3000, myComponent, endX, endY, startX, startY, ease));
        motion.add(new EmptyFx(1000));
        motion.setLooping(true);
        
        float endAlpha = .25f;
        fade = new Timeline();
        ease = Easing.EXPO_OUT;
        fade.add(new AlphaFx(2000, myComponent, endAlpha, ease));
        fade.add(new AlphaFx(2000, myComponent, endAlpha, 1f, ease));
        fade.setLooping(true);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {

        resize.update(delta);
        motion.update(delta);
        fade.update(delta);
        
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
            container.exit();
    }
    
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_1) {
            resize.setActive(!resize.isActive());
        } else if (key == Input.KEY_2) {
            motion.setActive(!motion.isActive());
        } else if (key == Input.KEY_3) {
            fade.setActive(!fade.isActive());
        } else if (key == Input.KEY_SPACE) {
            resize.rewind();
            motion.rewind();
            fade.rewind();
        }
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        myComponent.draw(g);
        
        String paused;
        
        paused = resize.isActive() ? "running" : "paused";
        g.drawString("Resizer Timeline (1): "+paused, 10, 25);
        
        paused = motion.isActive() ? "running" : "paused";
        g.drawString("Motion Timeline (2): "+paused, 10, 40);
        
        paused = fade.isActive() ? "running" : "paused";
        g.drawString("Fade Timeline (3): "+paused, 10, 55);
        
        g.drawString("Use the 1-3 keys to toggle the timelines.", 10, 70);
        g.drawString("Press SPACE to rewind all timelines.", 10, 85);
    }
    
    public class MyComponent implements SizeEntity, LocationEntity, AlphaEntity {
        private float x=100, y=200, width, height;
        private Image image;
        private Color filter = new Color(Color.white);
        
        public MyComponent(Image image) {
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.image = image;
        }
        
        public void draw(Graphics g) {
            image.draw(x,y,width,height,filter);
        }

        public void setSize(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public float getWidth() {
            return width;
        }

        public float getHeight() {
            return height;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setLocation(float x, float y) {
            this.x = x;
            this.y = y;
        }
        
        public void setAlpha(float opacity) {
            filter.a = opacity;
        }
        
        public float getAlpha() {
            return filter.a;
        }
    }
}
