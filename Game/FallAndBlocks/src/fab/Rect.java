// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Rect.java

package fab;

import java.awt.Color;
import java.awt.Graphics;

// Referenced classes of package fab:
//            BlockShape

public class Rect
{

    public Rect(int x, int y, int w, int h, BlockShape.ShapeType st)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.st = st;
    }

    public void Draw(Graphics g)
    {
        g.setColor(RealColor(st));
        g.fillRect(x + 1, y + 1, w, h);
    }
//  LINE,SQUARE,STYPE,ZTYPE,TTYPE,LTYPE,RTYPE,EMPTY
    public static Color RealColor(BlockShape.ShapeType t)
    {
        Color c = Color.BLACK;
      
        switch(t)
        {
        case LINE: // '\001'
            c = Color.RED;
            break;

        case SQUARE: // '\002'
            c = new Color(50, 200, 50);
            break;

        case STYPE: // '\003'
            c = new Color(100, 100, 255);
            break;

        case ZTYPE: // '\004'
            c = Color.ORANGE;
            break;

        case TTYPE: // '\005'
            c = Color.CYAN;
            break;

        case LTYPE: // '\006'
            c = Color.MAGENTA;
            break;

        case RTYPE: // '\007'
            c = Color.YELLOW;
            break;
        }
        return c;
    }

    int x;
    int y;
    int w;
    int h;
    BlockShape.ShapeType st;
}
