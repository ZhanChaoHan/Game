// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Sinuosity.java

package mm;

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;

// Referenced classes of package mm:
//            SinuosityPanel, SinuosityController, Globals, InfoPanel, 
//            StatPanel

public class Sinuosity extends Applet
{

    public Sinuosity()
    {
        this(false);
    }

    public Sinuosity(boolean makeWindow)
    {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        panel = new SinuosityPanel(this);
        SinuosityController.initialize(panel, infoPanel, statPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, "Center");
        if(makeWindow)
            createWindow();
    }

    public static void main(String args[])
    {
        (new Sinuosity(true)).runGameLoop();
    }

    public void init()
    {
        setBackground(Color.BLACK);
        setSize(mainPanel.getPreferredSize());
        Globals.setScreenSize(800, 600);
        setLayout(new BorderLayout());
        add(mainPanel, "Center");
        runGameLoop();
        mainPanel.requestFocus();
    }

    public void runGameLoop()
    {
        Thread loop = new Thread() {

            public void run()
            {
                SinuosityController.start();
            }


        };
        loop.start();
    }

    public void zeroPanelPosition()
    {
    }

    public void createWindow()
    {
        JFrame window = new JFrame("Dread Labyrinth");
        window.getContentPane().add(mainPanel);
        window.pack();
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        Globals.setScreenSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        window.setVisible(true);
    }

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private SinuosityPanel panel;
    private InfoPanel infoPanel;
    private StatPanel statPanel;
    private JScrollPane scrollPane;
}
