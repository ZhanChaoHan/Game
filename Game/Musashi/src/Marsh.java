// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java

import java.applet.AudioClip;

class Marsh extends Bg
{

    Marsh(int i)
    {
        name = "\u6CBC";
        hp = 999;
        x = i;
        y = 0;
        lineY = 0;
        id = 504;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 192;

        }

    }

    void attack(Chara chara)
    {
        chara.hp -= 4;
        chara.motionCount = 0;
        chara.stat = 12;
        app.addMsg("" + chara.name + "\u306F \u306C\u304B\u308B\u3093\u3060\u3063!");
        if(app.playSound)
            app.enemyAtk.play();
        app.checkDeadAll();
    }
}
