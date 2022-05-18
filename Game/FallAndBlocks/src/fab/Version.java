// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Version.java

package fab;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Referenced classes of package fab:
//            Text

public class Version
{

    public Version(String version, int posx, int posy, int width)
    {
        copyright = "Asphalt Galaxy (c) 2012";
        text = new Text();
        this.version = (new StringBuilder()).append("v. ").append(version).toString();
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        ImageIcon iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("message.png"));
        background = iid.getImage();
        text.getClass();
        background = background.getScaledInstance(width, 10 + 6, 0);
    }

    public void Draw(Graphics g, JPanel p)
    {
        g.drawImage(background, posx, posy, p);
        text.Draw(copyright, posx + 4, posy + 10, g, Text.Position.LEFT, Text.FontSize.SMALL);
        text.Draw(version, (posx + width) - 4, posy + 10, g, Text.Position.RIGHT, Text.FontSize.SMALL);
    }

    String version;
    String copyright;
    private final int TEXT_DX = 4;
    private final int TEXT_DY = 10;
    int posx;
    int posy;
    int width;
    Image background;
    Text text;
}
