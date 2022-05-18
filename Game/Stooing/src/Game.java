// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Game.java

import java.awt.Container;
import javax.swing.JFrame;

public class Game extends JFrame
{

    public Game()
    {
        setTitle("\u30DF\u30B3\u306E\u30AF\u30EA\u30B9\u30DE\u30B9\u3051\u3044\u304B\u304F\uFF15");
        setResizable(false);
        MainPanel mainpanel = new MainPanel();
        Container container = getContentPane();
        container.add(mainpanel);
        pack();
    }

    public static void main(String args[])
    {
        Game game = new Game();
        game.setDefaultCloseOperation(3);
        game.setVisible(true);
    }
}
