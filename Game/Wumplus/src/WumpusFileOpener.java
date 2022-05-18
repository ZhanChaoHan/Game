// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WumpusFileOpener.java

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WumpusFileOpener extends FileDialog
    implements WindowListener
{

    public WumpusFileOpener(Frame frame)
    {
        super(frame);
        parentFrame = frame;
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowClosing(WindowEvent windowevent)
    {
        parentFrame.toFront();
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    private Frame parentFrame;
}
