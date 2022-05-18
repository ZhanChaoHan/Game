// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FallAndBlocks.java

package fab;

import javax.swing.JApplet;

// Referenced classes of package fab:
//            Board

public class FallAndBlocks extends JApplet
{

    public FallAndBlocks()
    {
        add(new Board());
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
    }

    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 500;
}
