// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Bg.java

import java.applet.AudioClip;

class Drop extends Bg
{

    Drop(int i, int j)
    {
        name = "\u843D\u3068\u3057\u7A74";
        hp = 100;
        x = i;
        y = j;
        lineY = 384;
        id = 508;
        for(int k = 0; k < 4; k++)
        {
            for(int l = 0; l < 4; l++)
                motion[k][l] = 528;

        }

    }

    void attack(Chara chara)
    {
        if(chara.kind == 1)
            chara.hp -= 30;
        else
            chara.hp -= 256;
        if(chara.kind == 1)
        {
            chara.stat = 12;
            chara.motionCount = 0;
        }
        app.addMsg("" + chara.name + "\u306F \u98F2\u307F\u8FBC\u307E\u308C\u305F\u3063!");
        hp -= 100;
        if(chara.kind == 1)
        {
            if(app.playSound)
                app.enemyAtk.play();
        } else
        if(app.playSound)
            app.fall.play();
        if(chara.hp <= 0 && chara.kind != 1)
        {
            app.addMsg(chara.ep + "\u306E\u7D4C\u9A13\u5024\u3092\u624B\u306B\u3044\u308C\u305F!");
            app.musashi.ep += chara.ep;
            app.musashi.levelUp();
        }
        app.checkDeadAll();
    }
}
