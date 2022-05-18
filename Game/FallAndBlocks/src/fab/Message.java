// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Message.java

package fab;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

// Referenced classes of package fab:
//            Text, MessageText

public class Message
{
    private  enum State
    {

   
        IN,SHOW,OUT
    }


    public Message(int x, int y, int w, int h, Image img, MessageText t[], int delay)
    {
        dx = 0;
        speeddirection = 1;
        speed = 35F;
        noout = false;
        txt = new Text();
        this.x = x;
        this.y = y;
        this.img = img;
        this.t = t;
        this.delay = delay;
        this.w = w;
        this.h = h;
        if(delay == 0)
            noout = true;
        delaydx = delay;
    }

    public void InitMessage()
    {
        dx = 200;
        state = State.IN;
        speed = 35F;
        delaydx = delay;
    }

    public boolean ShowMessage()
    {
      
        switch(state.ordinal())
        {
        default:
            break;

        case 1: // '\001'
            speed = speed - speed / 10F;
            dx -= (int)speed;
            if(x + dx < x)
            {
                dx = 0;
                state = State.SHOW;
            }
            break;

        case 2: // '\002'
            if(delaydx-- < 0 && !noout)
                state = State.OUT;
            break;

        case 3: // '\003'
            speed += speed / 10F;
            dx -= (int)speed;
            if(dx < -1000)
                return false;
            break;
        }
        return true;
    }

    public void Draw(Graphics g, JPanel p)
    {
        g.drawImage(img, x + dx, y, p);
        for(int i = 0; i < t.length; i++)
        {
            String msg = t[i].text;
            if(t[i].input)
            {
                txt.getClass();
                txt.DrawCursor(msg, x + dx + 0 + w / 2, y + i * (18 + 2) + 32, g, Text.Position.MIDDLE, Text.FontSize.BIG);
            } else
            {
                txt.getClass();
                txt.Draw(msg, x + dx + 0 + w / 2, y + i * (18 + 2) + 32, g, Text.Position.MIDDLE);
            }
            if(!t[i].selected)
                continue;
            txt.getClass();
            txt.getClass();
            g.drawRect(((x + dx + 0) - -10) + 0, y + i * (18 + 2) + 32 + -16, w + -20, 18 + 2);
            if(t[i].selectionactive)
            {
                txt.getClass();
                txt.getClass();
                g.drawRect(((x + dx + 0) - -10) + 0 + 1, y + i * (18 + 2) + 32 + -16 + 1, (w + -20) - 2, (18 + 2) - 2);
            }
        }

    }

    public void MessageOut()
    {
        state = State.OUT;
    }

    public int Next()
    {
        int curr = FindCurrent();
        int next;
        if(curr < t.length - 1 && t[curr + 1].group == t[curr].group)
        {
            t[curr + 1].selected = true;
            t[curr + 1].selectionactive = true;
            next = curr + 1;
        } else
        {
            int i;
            for(i = 0; t[i].group != t[curr].group; i++);
            t[i].selected = true;
            t[i].selectionactive = true;
            next = i;
        }
        t[curr].selected = false;
        t[curr].selectionactive = false;
        return next;
    }

    public int Prev()
    {
        int curr = FindCurrent();
        int next;
        if(curr > 0 && t[curr - 1].group == t[curr].group)
        {
            t[curr - 1].selected = true;
            t[curr - 1].selectionactive = true;
            next = curr - 1;
        } else
        {
            int i;
            for(i = t.length - 1; t[i].group != t[curr].group; i--);
            t[i].selected = true;
            t[i].selectionactive = true;
            next = i;
        }
        t[curr].selected = false;
        t[curr].selectionactive = false;
        return next;
    }

    private int FindCurrent()
    {
        for(int i = 0; i < t.length; i++)
            if(t[i].selectionactive)
                return i;

        return 0;
    }

    State state;
    private final int TDX = 0;
    private final int TDY = 32;
    private final int SPEED = 35;
    private final int DX = 200;
    private final int SELECTION_DWIDTH = -20;
    private final int SELECTION_DX = 0;
    private final int SELECTION_DY = -16;
    int x;
    int y;
    int w;
    int h;
    int dx;
    int delay;
    int delaydx;
    int speeddirection;
    float speed;
    Image img;
    MessageText t[];
    private boolean noout;
    Text txt;
}
