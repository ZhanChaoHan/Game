// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Key.java

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Key extends KeyAdapter
{

    Key()
    {
    }

    public void keyPressed(KeyEvent keyevent)
    {
        if(app.isActive())
            switch(keyevent.getKeyCode())
            {
            case 37: // '%'
                left = true;
                break;

            case 39: // '\''
                right = true;
                break;

            case 38: // '&'
                up = true;
                break;

            case 40: // '('
                down = true;
                break;

            case 32: // ' '
                space = true;
                break;

            case 86: // 'V'
                v = true;
                break;

            case 70: // 'F'
                f = true;
                break;

            case 68: // 'D'
                d = true;
                break;
            }
    }

    public void keyReleased(KeyEvent keyevent)
    {
        if(app.isActive())
            switch(keyevent.getKeyCode())
            {
            case 37: // '%'
                left = false;
                break;

            case 39: // '\''
                right = false;
                break;

            case 38: // '&'
                up = false;
                break;

            case 40: // '('
                down = false;
                break;

            case 32: // ' '
                space = false;
                break;

            case 86: // 'V'
                v = false;
                break;

            case 70: // 'F'
                f = false;
                break;

            case 68: // 'D'
                d = false;
                break;
            }
    }

    static Musashi app;
    static boolean left;
    static boolean right;
    static boolean up;
    static boolean down;
    static boolean space;
    static boolean v;
    static boolean f;
    static boolean d;
    static boolean flag;
}
