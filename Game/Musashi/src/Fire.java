// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java

import java.applet.AudioClip;

class Fire extends Bg
{

    Fire(int i)
    {
        name = "\u708E";
        hp = 999;
        x = i;
        y = 0;
        lineY = 0;
        id = 505;
        for(int j = 0; j < 4; j++)
        {
            motion[j][0] = 240;
            motion[j][1] = 288;
            motion[j][2] = 240;
            motion[j][3] = 288;
        }

    }

    void attack(Chara chara)
    {
        chara.hp -= 12;
        chara.motionCount = 0;
        chara.stat = 12;
        app.addMsg("" + chara.name + "\u306F \u30A2\u30C4\u3044\u3063!");
        if(app.playSound)
            app.enemyAtk.play();
        app.checkDeadAll();
    }

    void motion()
    {
        motionCount++;
        if(stat == 10 && motionCount > 20)
        {
            motionChange++;
            if(motionChange == 4)
                motionChange = 0;
            motionCount = 0;
        }
    }
}
