// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ControlKeyboard.java

package general;

import gameobjects.Hero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import movement.MoveHeroPlatform;

// Referenced classes of package general:
//            Model, Pauser

public class ControlKeyboard
    implements KeyListener
{

    public ControlKeyboard(Model model)
    {
        this.model = model;
        fireAmmo = fireSuperAmmo = keyPressed = ctrl = upKeyLocked = false;
    }

    public void keyPressed(KeyEvent e)
    {
        if(!model.getLevelRunning() && !keyPressed)
        {
            if(e.getKeyCode() == 10)
                if(((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue() && model.getMarkedIndex() == 1)
                {
                    Model.screens.put(Model.screenType.TITLE, Boolean.valueOf(false));
                    Model.screens.put(Model.screenType.INTRO, Boolean.valueOf(false));
                    Model.screens.put(Model.screenType.LOADINGLEVEL, Boolean.valueOf(true));
                    model.setLevel(0);
                    model.init();
                    keyPressed = true;
                    Model.setStopAllSounds(true);
                } else
                if(((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue() && model.getMarkedIndex() == 0)
                    model.setExit(true);
                else
                if(((Boolean)Model.screens.get(Model.screenType.INFO)).booleanValue())
                {
                    Model.screens.put(Model.screenType.INFO, Boolean.valueOf(false));
                    Model.screens.put(Model.screenType.TITLE, Boolean.valueOf(true));
                    model.setMarkedIndex(1);
                    keyPressed = true;
                }
            if(((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue() && e.getKeyCode() == 88)
            {
                model.setMarkedArea(true, false);
                Model.addSoundToList("/sounds/select1.wav");
            } else
            if(((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue() && e.getKeyCode() == 40)
            {
                model.setMarkedArea(false, true);
                Model.addSoundToList("/sounds/select1.wav");
            }
            if(e.getKeyCode() == 10 && !Pauser.paused)
                keyPressed = true;
            if(((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue())
            {
                if(e.getKeyCode() == 77 && !Model.isMute && !keyPressed)
                {
                    Model.isMute = true;
                    keyPressed = true;
                }
                if(e.getKeyCode() == 77 && Model.isMute && !keyPressed)
                {
                    Model.isMute = false;
                    keyPressed = true;
                }
            }
        }
        if(model.getLevelRunning() && !model.getLevelCompleted() && !model.getGameCompleted() && !Hero.isExiting)
        {
            if(Model.paused && !keyPressed && (e.getKeyCode() == 80 || e.getKeyCode() == 10))
            {
                Model.paused = false;
                keyPressed = true;
            }
            if(!Model.paused)
            {
                if(e.getKeyCode() == 37)
                {
                    model.setHeroDir(0, true);
                    model.setHeroDir(1, false);
                } else
                if(e.getKeyCode() == 39)
                {
                    model.setHeroDir(1, true);
                    model.setHeroDir(0, false);
                }
                if(e.getKeyCode() == 67 && Hero.canJump && !Hero.isFalling && !upKeyLocked)
                {
                    model.setHeroDir(2, true);
                    model.setHeroDir(3, false);
                    Hero.isClimbing = false;
                    upKeyLocked = true;
                } else
                if(e.getKeyCode() == 38)
                    model.setHeroClimbing("up");
                else
                if(e.getKeyCode() == 40)
                    model.setHeroClimbing("down");
                if(e.getKeyCode() == 38 && Hero.isOverlappingExit && !Hero.isExiting)
                {
                    model.getHero().setState(7, 1);
                    Hero.isExiting = true;
                    model.getHero().setX(Hero.exitX);
                    model.getHero().setY(Hero.exitY);
                    Model.setStopAllSounds(true);
                    Model.addSoundToList("/sounds/exit1.wav");
                }
                if(e.getKeyCode() == 88 && !fireAmmo && !Hero.isClimbing)
                {
                    model.setHeroShooting(true, 0);
                    fireAmmo = true;
                }
                e.getKeyCode();
                if(e.getKeyCode() == 32 && !fireSuperAmmo)
                {
                    model.setHeroShooting(true, 1);
                    fireSuperAmmo = true;
                }
                if((e.getKeyCode() == 81 || e.getKeyCode() == 27) && !Hero.isExiting)
                {
                    Model.screens.put(Model.screenType.TITLE, Boolean.valueOf(true));
                    model.setLevelRunning(false);
                    model.setMarkedIndex(1);
                    model.setLevel(0);
                    Hero.scrollPrevX = 0;
                    model.deleteHero();
                    model.setDrawHeroScore(false);
                    Model.setStopAllSounds(true);
                    Model.addSoundToList("/sounds/intro2.wav");
                }
                if(e.getKeyCode() == 76)
                    ctrl = true;
                if(!keyPressed && e.getKeyCode() == 80)
                {
                    Model.paused = true;
                    keyPressed = true;
                }
                if(!keyPressed && e.getKeyCode() == 112 && !model.isInHeroDir(0) && !model.isInHeroDir(1) && !model.isInHeroDir(2) && !model.isInHeroDir(3))
                {
                    Model.paused = true;
                    keyPressed = true;
                }
            }
        }
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyReleased(KeyEvent e)
    {
        if(model.getLevelRunning() && !Model.paused && !model.getLevelCompleted() && !model.getGameCompleted() && !Hero.isExiting)
        {
            if(e.getKeyCode() == 37)
                model.setHeroDir(0, false);
            if(e.getKeyCode() == 39)
                model.setHeroDir(1, false);
            if(e.getKeyCode() == 38 && Hero.isClimbing)
                model.setHeroDir(2, false);
            if(e.getKeyCode() == 40 && Hero.isClimbing)
                model.setHeroDir(3, false);
            if(e.getKeyCode() == 67)
            {
                model.getHero().getMovement().setUpKeyReleased();
                upKeyLocked = false;
            }
            if(e.getKeyCode() == 88 || e.getKeyCode() == 32)
            {
                model.setHeroShooting(false, 0);
                fireAmmo = false;
            }
            if(e.getKeyCode() == 32)
            {
                model.setHeroShooting(false, 1);
                fireSuperAmmo = false;
            }
        }
        if(e.getKeyCode() == 10 || e.getKeyCode() == 32 || e.getKeyCode() == 80 || e.getKeyCode() == 112 || e.getKeyCode() == 77)
            keyPressed = false;
        if(e.getKeyCode() == 76)
            ctrl = false;
    }

    private Model model;
    private boolean fireAmmo;
    private boolean fireSuperAmmo;
    private boolean keyPressed;
    private boolean ctrl;
    private boolean upKeyLocked;
}
