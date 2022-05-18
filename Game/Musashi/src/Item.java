// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java

import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.Vector;

class Item extends Chara
{

    Item()
    {
        super(app.imgMusashi);
        kind = 3;
        dir = 0;
        stat = 10;
        comment = "";
        motionChange = 0;
        motionCount = 0;
        attackCount = 0;
        waitCount = 0;
        lineY = 0;
    }

    void move()
    {
    }

    void motion()
    {
    }

    void consu(Hero hero)
    {
        hp--;
        if(hp <= 0)
        {
            for(int i = 0; i < app.musashi.bag.size(); i++)
            {
                Item item = (Item)app.musashi.bag.elementAt(i);
                if(item.hp > 0)
                    continue;
                if(item.isEquip)
                    app.musashi.notEquip(item);
                app.musashi.bag.removeElementAt(i);
                if(app.menuCursor.itemPos == app.musashi.bag.size() && app.menuCursor.itemPos != 0)
                    if(app.menuCursor.itemPos == app.menuCursor.itemIndex)
                    {
                        app.menuCursor.itemPos--;
                        app.menuCursor.itemIndex--;
                    } else
                    {
                        app.menuCursor.itemPos--;
                    }
                app.addMsg("" + item.name + "\u306F \u58CA\u308C\u3066\u3057\u307E\u3063\u305F!");
                if(app.playSound)
                    app.crash.play();
            }

        }
    }

    void draw(Graphics g)
    {
        if(stat == 10)
            g.drawImage(img, x, y, x + 48, y + 48, motion[dir][motionChange], lineY, motion[dir][motionChange] + 48, lineY + 48, app);
        else
        if(stat == 11)
            g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
    }

    public static final int BOX_X = 432;
    public static final int SEED_X = 480;
}
