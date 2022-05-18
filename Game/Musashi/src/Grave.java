// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java

import java.applet.AudioClip;

class Grave extends Bg
{

    Grave(int i)
    {
        name = "\u5893";
        hp = 1;
        x = i;
        y = 0;
        lineY = 0;
        id = 503;
        for(int j = 0; j < 4; j++)
        {
            for(int k = 0; k < 4; k++)
                motion[j][k] = 144;

        }

    }

    void attack(Chara chara)
    {
        chara.hp -= 5;
        chara.motionCount = 0;
        chara.stat = 12;
        app.addMsg("\u30D0\u30C1\u3042\u305F\u308A\u3081\u3063!");
        if(app.playSound)
            app.enemyAtk.play();
        app.checkDeadAll();
    }
}
