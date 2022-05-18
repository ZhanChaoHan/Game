// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Text.java

package fab;

import java.awt.*;
import javax.swing.JPanel;

public class Text extends JPanel
{
    static enum FontSize 
    {

        BIG,SMALL
    }

    static enum Position
    {

        LEFT,RIGHT,MIDDLE
    }


    public Text()
    {
        big = new Font("Helvetica", 1, 18);
        bigm = getFontMetrics(big);
        small = new Font("Helvetica", 0, 10);
        smallm = getFontMetrics(small);
        cursor = true;
    }

    public void Draw(String msg, int x, int y, Graphics g)
    {
        g.setFont(big);
        DrawText(x, y, msg, g);
    }

    public void Draw(String msg, int x, int y, Graphics g, Position p)
    {
        g.setFont(big);
        int sx = x;
        int sy = y;
        
        switch(p)
        {
        case LEFT: // '\001'
        {
            sx = x;
            sy = y;
            break;
        }

        case RIGHT: // '\002'
        {
            int dx = bigm.stringWidth(msg);
            sx = x - dx;
            sy = y;
            break;
        }

        case MIDDLE: // '\003'
        {
            int dx = bigm.stringWidth(msg) / 2;
            sx = x - dx;
            sy = y;
            break;
        }
        }
        DrawText(sx, sy, msg, g);
    }

    private void DrawText(int x, int y, String msg, Graphics g)
    {
        g.setColor(FONT_COLOR);
        g.drawString(msg, x, y);
    }

    public void Draw(String msg, int x, int y, Graphics g, Position p, FontSize s)
    {
        int dx = 0;
        int sx = x;
        int sy = y;
        switch(s)
        {
        case BIG: 
            g.setFont(big);
            dx = bigm.stringWidth(msg);
            break;

        case SMALL: 
            g.setFont(small);
            dx = smallm.stringWidth(msg);
            break;
        }
        switch(p.ordinal())
        {
        case 1: // '\001'
            sx = x;
            sy = y;
            break;

        case 2: // '\002'
            sx = x - dx;
            sy = y;
            break;

        case 3: // '\003'
            sx = x - dx;
            sy = y;
            break;
        }
        DrawText(sx, sy, msg, g);
    }

    public void DrawCursor(String msg, int x, int y, Graphics g, Position p, FontSize s)
    {
        int dx = 0;
        int sx = x;
        int sy = y;
        switch(s)
        {
        case BIG: // '\001'
            g.setFont(big);
            dx = bigm.stringWidth(msg);
            break;

        case SMALL: // '\002'
            g.setFont(small);
            dx = smallm.stringWidth(msg);
            break;
        }
        if(cursor)
        {
            msg = (new StringBuilder()).append(msg).append("_").toString();
            cursor = false;
        } else
        {
            cursor = true;
        }
        switch(p)
        {
        case LEFT: // '\001'
            sx = x;
            sy = y;
            break;

        case RIGHT: // '\002'
            sx = x - dx;
            sy = y;
            break;

        case MIDDLE: // '\003'
            dx /= 2;
            sx = x - dx;
            sy = y;
            break;
        }
        DrawText(sx, sy, msg, g);
    }

    public final int FONT_BIG = 18;
    public final int FONT_SMALL = 10;
    final Color FONT_COLOR = new Color(255, 146, 0);
    final Color FONT_COLOR_SHADOW = new Color(200, 0, 0);
    Font big;
    FontMetrics bigm;
    Font small;
    FontMetrics smallm;
    boolean cursor;
}
