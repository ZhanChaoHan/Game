// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TypingMain.java

package Typing;

import java.awt.Container;
import javax.swing.JFrame;

// Referenced classes of package Typing:
//            MainPanel

public class TypingMain extends JFrame
{

    public TypingMain()
    {
        setTitle("Typing@Tokyo");
        setResizable(false);
        Container contentPane = getContentPane();
        MainPanel mainPanel = new MainPanel();
        contentPane.add(mainPanel, "Center");
        pack();
    }

    public static void main(String args[])
    {
        TypingMain frame = new TypingMain();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.validate();
    }
}
