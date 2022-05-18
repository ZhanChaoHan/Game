/*
 * TestEasing.java
 *
 * Created on January 24, 2008, 12:44 PM
 */

package mdes.slick.animation.test;

import mdes.slick.animation.Easing;
import org.newdawn.slick.*;

/**
 *
 * @author davedes
 */
public class TestEasing extends BasicGame {
    
    public static void main(String[] args) throws Exception {
        AppGameContainer app = new AppGameContainer(new TestEasing());
        app.setDisplayMode(800,600,false);
        app.start();
    }
    
    /**
     * Creates a new instance of TestEasing
     */
    public TestEasing() {
        super("TestEase");
    }
    
    private float x = 100;
    private float y = 100;
    private Image img; 
    
    private float time = 0;
    private float begin = x;
    private float finish = 400;
    private float change = finish-begin;
    private float duration = 2000;
    
    private Easing[] easings = new Easing[] {
        Easing.LINEAR,
        Easing.QUAD_IN, Easing.QUAD_OUT, Easing.QUAD_IN_OUT,
        Easing.CUBIC_IN, Easing.CUBIC_OUT, Easing.CUBIC_IN_OUT,
        Easing.QUARTIC_IN, Easing.QUARTIC_OUT, Easing.QUARTIC_IN_OUT,
        Easing.QUINTIC_IN, Easing.QUINTIC_OUT, Easing.QUINTIC_IN_OUT,
        Easing.SINE_IN, Easing.SINE_OUT, Easing.SINE_IN_OUT,
        Easing.EXPO_IN, Easing.EXPO_OUT, Easing.EXPO_IN_OUT,
        Easing.CIRC_IN, Easing.CIRC_OUT, Easing.CIRC_IN_OUT,
        Easing.ELASTIC_IN, Easing.ELASTIC_OUT, Easing.ELASTIC_IN_OUT,
        Easing.BACK_IN, Easing.BACK_OUT, Easing.BACK_IN_OUT,
        Easing.BOUNCE_IN, Easing.BOUNCE_OUT, Easing.BOUNCE_IN_OUT,
        new Easing.BackOut(4f)
    };
    
    private String[] easingNames = new String[] {
        "Easing.LINEAR",
        "Easing.QUAD_IN", "Easing.QUAD_OUT", "Easing.QUAD_IN_OUT",
        "Easing.CUBIC_IN", "Easing.CUBIC_OUT", "Easing.CUBIC_IN_OUT",
        "Easing.QUARTIC_IN", "Easing.QUARTIC_OUT", "Easing.QUARTIC_IN_OUT",
        "Easing.QUINTIC_IN", "Easing.QUINTIC_OUT", "Easing.QUINTIC_IN_OUT",
        "Easing.SINE_IN", "Easing.SINE_OUT", "Easing.SINE_IN_OUT",
        "Easing.EXPO_IN", "Easing.EXPO_OUT", "Easing.EXPO_IN_OUT",
        "Easing.CIRC_IN", "Easing.CIRC_OUT", "Easing.CIRC_IN_OUT",
        "Easing.ELASTIC_IN", "Easing.ELASTIC_OUT", "Easing.ELASTIC_IN_OUT",
        "Easing.BACK_IN", "Easing.BACK_OUT", "Easing.BACK_IN_OUT",
        "Easing.BOUNCE_IN", "Easing.BOUNCE_OUT", "Easing.BOUNCE_IN_OUT",
        "Custom Easing.BackOut"
    };
    
    int pointer = 0;
    
    public void init(GameContainer container) throws SlickException {
        img = new Image("res/test/globe.png");
        container.getGraphics().setBackground(new Color(118, 118, 118));
        container.setMinimumLogicUpdateInterval(1);
        container.setMaximumLogicUpdateInterval(1);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        if (time+delta < duration) {
            time += delta;
            x = (int)easings[pointer].ease(time, begin, change, duration);
        }
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
            container.exit();
    }
    
    private void restart() {
        time = 0;
        x = begin;
    }
    
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_SPACE) {
            restart();
        } else if (key == Input.KEY_LEFT) {
            restart();
            if (pointer>0)
                pointer--;
            else
                pointer = easings.length-1;
        } else if (key == Input.KEY_RIGHT) {
            restart();
            if (pointer<easings.length-1)
                pointer++;
            else 
                pointer = 0;
        }
    }
        
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawImage(img, x, y);
                
        g.setColor(Color.white);
        g.drawString("Press SPACE to restart animation.", 10, 25);
        g.drawString("Press LEFT and RIGHT arrow keys to change easing.", 10, 45);
        g.drawString("Current Easing: "+easingNames[pointer]
                    +" ("+(pointer+1)+"/"+easingNames.length+")", 10, 60);
    }
}
