/*
 * TestSmoothUI.java
 *
 * Created on January 24, 2008, 6:03 PM
 */

package mdes.slick.animation.test;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.SizeEntity;
import mdes.slick.animation.entity.renderable.ImageAlpha;
import mdes.slick.animation.entity.renderable.RenderableAlpha;
import mdes.slick.animation.fx.AlphaFx;
import mdes.slick.animation.fx.*;
import org.newdawn.slick.*;

/**
 *
 * @author davedes
 */
public class TestSmoothUI extends BasicGame {
    
    public static void main(String[] args) throws Exception {
        AppGameContainer app = new AppGameContainer(new TestSmoothUI());
        app.setDisplayMode(800,600,false);
        app.start();
    }
    
    /** Creates a new instance of TestSmoothUI */
    public TestSmoothUI() {
        super("TestSmoothUI");
    }
    
    UIButton[] buttons;
    Image sky;
    
    public void init(GameContainer container) throws SlickException {
        buttons = new UIButton[4];
        
        Image upImg = new Image("res/test/button.png");
        Image rolloverImg = new Image("res/test/button_hover.png");
        sky = new Image("res/test/sky.png");
        
        final int panelX = 100;
        final int panelY = 200;
        int py = panelY;
        for (int i=0; i<buttons.length; i++) {
            UIButton btn = new UIButton(upImg, rolloverImg);
            btn.x = panelX;
            btn.y = py;
            py += btn.getHeight()+15;
            buttons[i] = btn;
        }
    }
    
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        int mx = input.getMouseX();
        int my = input.getMouseY();
        boolean pressed = input.isMousePressed(0);
        for (int i=0; i<buttons.length; i++) {
            UIButton btn = buttons[i];
            if (mx>=btn.x && mx<=btn.x+btn.maxWidth()
                    && my>=btn.y && my<=btn.y+btn.maxHeight()) {
                if (!btn.mouseInside) {
                    btn.mouseInside = true;
                    btn.onMouseEnter();
                }
            } else {
                if (btn.mouseInside) {
                    btn.mouseInside = false;
                    btn.onMouseExit();
                }
            }
            
            if (pressed && !btn.mouseDown && btn.mouseInside) {
                btn.mouseDown = true;
                btn.press();
            } else if (!pressed && btn.mouseDown) {
                btn.mouseDown = false;
            }
            
            btn.update(delta);
        }
        
        if (input.isKeyPressed(Input.KEY_ESCAPE))
            container.exit();
    }
    
    public void render(GameContainer container, Graphics g) throws SlickException {
        sky.draw(0, 0, container.getWidth(), container.getHeight());
        for (int i=0; i<buttons.length; i++)
            buttons[i].draw();
    }
    
    protected class UIButton implements SizeEntity {
        
        private Timeline timeline;
        private ImageAlpha up, rollover;
        private float width, height;
        float x, y;
                
        boolean mouseInside = false;
        boolean mouseDown = false;
        
        private float startW, startH;
        
        public UIButton(Image upImg, Image rolloverImg) throws SlickException {
            this.up = new ImageAlpha(upImg);
            this.rollover = new ImageAlpha(rolloverImg);
            rollover.setAlpha(0f);
            
            this.width = upImg.getWidth();
            this.height = upImg.getHeight();
                        
            final Easing fadeIn = Easing.CUBIC_OUT;
            final Easing fadeOut = Easing.LINEAR;
            final int durationIn = 500;
            final int durationOut = 500;
            AlphaFx upFadeOut = new AlphaFx(durationOut, up, 1f, 0f, fadeOut);
            AlphaFx upFadeIn = new AlphaFx(durationIn, up, 0f, 1f, fadeIn);
            AlphaFx rolloverFadeIn = new AlphaFx(durationIn, rollover, 0f, 1f, fadeIn);
            AlphaFx rolloverFadeOut = new AlphaFx(durationOut, rollover, 1f, 0f, fadeOut);
            
            CompoundFx fade1 = new CompoundFx(upFadeOut, rolloverFadeIn);
            CompoundFx fade2 = new CompoundFx(upFadeIn, rolloverFadeOut);
            
            startW = width;
            startH = height;
            float endW = width*1.1f;
            float endH = height*1.1f;
            SizeFx scale1 = new SizeFx(1000, this, startW, startH, endW, endH, Easing.ELASTIC_OUT);
            SizeFx scale2 = new SizeFx(400, this, endW, endH, startW, startH, Easing.CUBIC_OUT);
            
            timeline = new Timeline();
            timeline.add(new CompoundFx(fade1, scale1));
            timeline.add(new CompoundFx(fade2, scale2));
        }
        
        public float maxHeight() {
            return startH*1.1f;
        }
        
        public float maxWidth() {
            return startW*1.1f;
        }
        
        public void update(int delta) {
            timeline.update(delta);
        }
        
        public void draw() {
            up.draw(x, y, width, height);
            rollover.draw(x, y, width, height);
        }
        
        public void press() {
            timeline.setActive(true);
            timeline.setRange(0, 1);
            timeline.restart();
        }
        
        public void onMouseEnter() {
            timeline.setActive(true);
            timeline.setRange(0, 1);
            timeline.restart();
        }
                
        public void onMouseExit() {
            timeline.setActive(true);
            timeline.setRange(1, 2);
            timeline.restart();
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
    }
}
