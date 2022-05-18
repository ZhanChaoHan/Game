/*
 * TestCompoundFx.java
 *
 * Created on January 24, 2008, 7:27 PM
 */

package mdes.slick.animation.test;

import mdes.slick.animation.entity.*;
import mdes.slick.animation.fx.*;
import mdes.slick.animation.*;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.fills.*;

/**
 *
 * @author davedes
 */
public class TestCompoundFx extends BasicGame implements AlphaEntity, LocationEntity {
    
    public static void main(String[] args) throws Exception {
        AppGameContainer app = new AppGameContainer(new TestCompoundFx());
        app.setDisplayMode(800,600,false);
        app.setTargetFrameRate(60);
        app.start();
    }
    
    /** Creates a new instance of TestCompoundFx */
    public TestCompoundFx() {
        super("TestCompoundFx");
    }
    
    private Timeline timeline;
    private Image image;
    private Color filter = new Color(Color.white);
    private float x, y;
    
    public void init(GameContainer container) throws SlickException {
        image = new Image("res/test/globe.png");
        container.getGraphics().setBackground(new Color(118, 118, 118));
        container.setMinimumLogicUpdateInterval(1);
        container.setMaximumLogicUpdateInterval(1);
        
        timeline = new Timeline();
        timeline.setLooping(true);
        timeline.setActive(true);
        
        Easing ease = Easing.ELASTIC_OUT;
        x = 100;
        y = 200;
        Fx move1 = new LocationFx(3000, this, 100, 200, 200, 200, ease);
        Fx move2 = new LocationFx(3000, this, 200, 200, 100, 200, ease);
        
        ease = Easing.EXPO_OUT;
        Fx fade1 = new AlphaFx(2000, this, 1f, 0.25f, ease);
        Fx fade2 = new AlphaFx(2000, this, 0.25f, 1f, ease);
        
        timeline.add(new CompoundFx(move1, fade1));
        timeline.add(new CompoundFx(move2, fade2));
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        timeline.update(delta);
        
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
            container.exit();
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        image.draw(x, y, filter);
    }

    public float getAlpha() {
        return filter.a;
    }

    public void setAlpha(float alpha) {
        filter.a = alpha;
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
}
