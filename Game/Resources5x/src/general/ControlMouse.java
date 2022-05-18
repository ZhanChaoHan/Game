// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ControlMouse.java

package general;

import java.awt.event.*;
import java.util.HashMap;

// Referenced classes of package general:
//            Model

public class ControlMouse
    implements MouseListener, MouseMotionListener
{

    public ControlMouse(Model model)
    {
        this.model = model;
    }

    public void mousePressed(MouseEvent e)
    {
        if(!model.getLevelRunning() && ((Boolean)Model.screens.get(Model.screenType.TITLE)).booleanValue() && model.getMarkedIndex() == 1)
        {
            Model.screens.put(Model.screenType.TITLE, Boolean.valueOf(false));
            Model.screens.put(Model.screenType.INTRO, Boolean.valueOf(false));
            Model.screens.put(Model.screenType.LOADINGLEVEL, Boolean.valueOf(true));
            model.setLevel(0);
            model.init();
            Model.setStopAllSounds(true);
        }
        Model.paused = false;
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
    }

    private Model model;
}
