// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WumplusMain.java

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WumplusMain extends JFrame
    implements WindowListener, WindowFocusListener, ComponentListener
{

    public WumplusMain(boolean flag)
    {
        super("Adventure Kitteh!!! Wumplus World!!!");
        screenX = 680;
        screenY = 576;
        setSize(800, 700);
        addWindowListener(this);
        addWindowFocusListener(this);
        addComponentListener(this);
        grDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        oldDisplay = grDev.getDisplayMode();
        if(flag)
        {
            System.out.println("Trying to start program in Fullscreen mode.");
            if(grDev.isFullScreenSupported())
            {
                System.out.println("FullScreen is supported");
                setUndecorated(true);
                DisplayMode displaymode = new DisplayMode(800, 600, 32, 0);
                try
                {
                    grDev.setFullScreenWindow(this);
                    grDev.setDisplayMode(displaymode);
                    System.out.println("Change resolution: Success!");
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append("Change resolution: FAIL! ").append(exception1).toString());
                    flag = false;
                }
            }
            setExtendedState(6);
        }
        screenP = new MasterPanel(this);
        bgFraming = new JPanel();
        bgFraming.setBackground(Color.black);
        add(bgFraming);
        bgFraming.setLayout(null);
        bgFraming.add(screenP);
        screenP.setSize(screenX, screenY);
        setDefaultCloseOperation(3);
        setVisible(true);
        recenterScreen();
        try
        {
            screenP.requestFocusInWindow();
        }
        catch(Exception exception) { }
        System.out.println("Game Window successfully created!!!");
    }

    public void recenterScreen()
    {
        Dimension dimension = getSize();
        int i = dimension.width;
        int j = dimension.height;
        screenP.setLocation((i - screenX) / 2, (j - screenY) / 2);
    }

    public void windowActivated(WindowEvent windowevent)
    {
        System.out.println("Window Activated");
    }

    public void windowClosed(WindowEvent windowevent)
    {
        System.out.println("Adventure Kitteh program terminated. Restoring original display settings.");
        grDev.setDisplayMode(oldDisplay);
    }

    public void windowClosing(WindowEvent windowevent)
    {
        System.out.println("Window closing");
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
        System.out.println("Window deactivated");
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
        System.out.println("Window Deiconified");
        try
        {
            recenterScreen();
            screenP.requestFocusInWindow();
        }
        catch(Exception exception) { }
    }

    public void windowIconified(WindowEvent windowevent)
    {
        System.out.println("Window Iconified");
    }

    public void windowOpened(WindowEvent windowevent)
    {
        System.out.println("Window opened");
    }

    public void windowGainedFocus(WindowEvent windowevent)
    {
        System.out.println("Window gained focus");
        try
        {
            recenterScreen();
            screenP.requestFocusInWindow();
        }
        catch(Exception exception) { }
    }

    public void windowLostFocus(WindowEvent windowevent)
    {
        System.out.println("Window lost focus");
    }

    public void componentHidden(ComponentEvent componentevent)
    {
    }

    public void componentMoved(ComponentEvent componentevent)
    {
    }

    public void componentResized(ComponentEvent componentevent)
    {
        recenterScreen();
    }

    public void componentShown(ComponentEvent componentevent)
    {
    }

    public static void main(String args[])
    {
        String args1[] = args;
        int i = args1.length;
        for(int j = 0; j < i; j++)
        {
            String s = args1[j];
            System.out.println(s);
        }

        WumplusMain wumplusmain;
        if(args.length == 0)
            wumplusmain = new WumplusMain(false);
        else
        if(args[0].equals("fullscreen"))
            wumplusmain = new WumplusMain(true);
    }

    private MasterPanel screenP;
    private JPanel bgFraming;
    private int screenX;
    private int screenY;
    private GraphicsDevice grDev;
    private DisplayMode oldDisplay;
}
