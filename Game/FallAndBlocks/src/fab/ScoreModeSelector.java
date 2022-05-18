// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScoreModeSelector.java

package fab;

import java.awt.Image;
import javax.swing.ImageIcon;

// Referenced classes of package fab:
//            MessageText, Message

public class ScoreModeSelector
{

    public ScoreModeSelector()
    {
        ImageIcon iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("message.png"));
        background = iid.getImage();
        background = background.getScaledInstance(120, 160, 0);
        MessageText mt[] = {
            new MessageText("Filter:"), new MessageText(""), new MessageText("*all*", true, false, true, 1), new MessageText("small", false, false, false, 1), new MessageText("normal", false, false, false, 1), new MessageText("big", false, false, false, 1), new MessageText("hardcore", false, false, false, 1)
        };
        message = new Message(4, 222, 120, 160, background, mt, 0);
        message.InitMessage();
    }

    public String Next()
    {
        int curr = message.Next();
        return message.t[curr].text.substring(0, 1);
    }

    public String Prev()
    {
        int curr = message.Prev();
        return message.t[curr].text.substring(0, 1);
    }

    final int WIDTH = 120;
    final int HEIGHT = 160;
    final int POSX = 4;
    final int POSY = 222;
    Image background;
    Message message;
}
