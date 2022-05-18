// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java

import java.applet.AudioClip;
import java.util.Vector;

class Doronta extends Enemy
{

    Doronta(int i)
    {
        name = "\u30C9\u30ED\u30F3\u592A";
        lv = 1;
        maxHp = 30;
        hp = 30;
        ap = 12;
        dp = 0;
        ep = 8;
        x = i;
        y = 0;
        lineY = 672;
        motionWait = 10;
    }

    void attack()
    {
        if(app.getRandom(3) == 1 && app.musashi.seedW > 0)
        {
            stat = 11;
            motionCount = 0;
            app.musashi.seedW--;
            if(app.playSound)
                app.seedUse.play();
            app.addMsg("\u767D\uFF89\u7A2E\u3092 \u76D7\u307E\u308C\u305F!");
        } else
        {
            stat = 11;
            motionCount = 0;
            switch(dir)
            {
            default:
                break;

            case 0: // '\0'
                int i3 = 0;
                do
                {
                    if(i3 >= app.enemy.size())
                        break;
                    Enemy enemy = (Enemy)app.enemy.elementAt(i3);
                    if(x == enemy.x && y + 48 == enemy.y)
                    {
                        int i = damage(enemy);
                        enemy.hp -= i;
                        battleMsg(enemy, i);
                        break;
                    }
                    i3++;
                } while(true);
                i3 = 0;
                do
                {
                    if(i3 >= app.bg.size())
                        break;
                    Bg bg = (Bg)app.bg.elementAt(i3);
                    if(x == bg.x && y + 48 == bg.y)
                    {
                        int j = damage(bg);
                        if(app.playSound && bg.id != 504 && bg.id != 505 && bg.id != 508)
                            app.heroAtk.play();
                        bg.hp -= j;
                        break;
                    }
                    i3++;
                } while(true);
                if(x == app.musashi.x && y + 48 == app.musashi.y)
                {
                    int k = damage(app.musashi);
                    app.musashi.hp -= k;
                    battleMsg(app.musashi, k);
                }
                break;

            case 1: // '\001'
                int j3 = 0;
                do
                {
                    if(j3 >= app.enemy.size())
                        break;
                    Enemy enemy1 = (Enemy)app.enemy.elementAt(j3);
                    if(x - 48 == enemy1.x && y == enemy1.y)
                    {
                        int l = damage(enemy1);
                        enemy1.hp -= l;
                        battleMsg(enemy1, l);
                        break;
                    }
                    j3++;
                } while(true);
                j3 = 0;
                do
                {
                    if(j3 >= app.bg.size())
                        break;
                    Bg bg1 = (Bg)app.bg.elementAt(j3);
                    if(x - 48 == bg1.x && y == bg1.y)
                    {
                        int i1 = damage(bg1);
                        if(app.playSound && bg1.id != 504 && bg1.id != 505 && bg1.id != 508)
                            app.heroAtk.play();
                        bg1.hp -= i1;
                        break;
                    }
                    j3++;
                } while(true);
                if(x - 48 == app.musashi.x && y == app.musashi.y)
                {
                    int j1 = damage(app.musashi);
                    app.musashi.hp -= j1;
                    battleMsg(app.musashi, j1);
                }
                break;

            case 2: // '\002'
                int k3 = 0;
                do
                {
                    if(k3 >= app.enemy.size())
                        break;
                    Enemy enemy2 = (Enemy)app.enemy.elementAt(k3);
                    if(x + 48 == enemy2.x && y == enemy2.y)
                    {
                        int k1 = damage(enemy2);
                        enemy2.hp -= k1;
                        battleMsg(enemy2, k1);
                        break;
                    }
                    k3++;
                } while(true);
                k3 = 0;
                do
                {
                    if(k3 >= app.bg.size())
                        break;
                    Bg bg2 = (Bg)app.bg.elementAt(k3);
                    if(x + 48 == bg2.x && y == bg2.y)
                    {
                        int l1 = damage(bg2);
                        if(app.playSound && bg2.id != 504 && bg2.id != 505 && bg2.id != 508)
                            app.heroAtk.play();
                        bg2.hp -= l1;
                        break;
                    }
                    k3++;
                } while(true);
                if(x + 48 == app.musashi.x && y == app.musashi.y)
                {
                    int i2 = damage(app.musashi);
                    app.musashi.hp -= i2;
                    battleMsg(app.musashi, i2);
                }
                break;

            case 3: // '\003'
                int l3 = 0;
                do
                {
                    if(l3 >= app.enemy.size())
                        break;
                    Enemy enemy3 = (Enemy)app.enemy.elementAt(l3);
                    if(x == enemy3.x && y - 48 == enemy3.y)
                    {
                        int j2 = damage(enemy3);
                        enemy3.hp -= j2;
                        battleMsg(enemy3, j2);
                        break;
                    }
                    l3++;
                } while(true);
                l3 = 0;
                do
                {
                    if(l3 >= app.bg.size())
                        break;
                    Bg bg3 = (Bg)app.bg.elementAt(l3);
                    if(x == bg3.x && y - 48 == bg3.y)
                    {
                        int k2 = damage(bg3);
                        if(app.playSound && bg3.id != 504 && bg3.id != 505 && bg3.id != 508)
                            app.heroAtk.play();
                        bg3.hp -= k2;
                        break;
                    }
                    l3++;
                } while(true);
                if(x == app.musashi.x && y - 48 == app.musashi.y)
                {
                    int l2 = damage(app.musashi);
                    app.musashi.hp -= l2;
                    battleMsg(app.musashi, l2);
                }
                break;
            }
        }
    }
}
