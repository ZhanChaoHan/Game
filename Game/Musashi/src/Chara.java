// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chara.java

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

abstract class Chara
{

    protected Chara(Image image)
    {
        img = image;
        kind = 0;
        id = 0;
        name = "";
        lv = 1;
        ep = 0;
        maxEp = 0x1869f;
        maxHp = 0;
        hp = 0;
        maxAp = 999;
        ap = 0;
        maxDp = 999;
        dp = 0;
        action = false;
        dir = 0;
        stat = 10;
        comment = "";
        lineY = 0;
        atkRange = 16;
        waitTime = 0;
        attackTime = 8;
        damageTime = 4;
        isEquip = false;
        moved = false;
        paralysis = false;
        isMetamo = false;
    }

    void move()
    {
    }

    void motion()
    {
    }

    boolean checkDead()
    {
        if(x < 0 || x > app.gwidth - 48 || y < 0 || y > app.gheight - 48)
            return true;
        return hp <= 0;
    }

    void attack()
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
            for(int j3 = 0; j3 < app.bg.size(); j3++)
            {
                Bg bg = (Bg)app.bg.elementAt(j3);
                if(x != bg.x || y + 48 != bg.y)
                    continue;
                int j = damage(bg);
                if(app.playSound && bg.id != 504 && bg.id != 505 && bg.id != 508)
                    playHeroAtk();
                bg.hp -= j;
                if(bg.hp <= 0 && bg.id == 503)
                    bg.attack(this);
                app.checkDeadAll();
            }

            if(x == app.musashi.x && y + 48 == app.musashi.y)
            {
                int k = damage(app.musashi);
                app.musashi.hp -= k;
                battleMsg(app.musashi, k);
            }
            break;

        case 1: // '\001'
            int k3 = 0;
            do
            {
                if(k3 >= app.enemy.size())
                    break;
                Enemy enemy1 = (Enemy)app.enemy.elementAt(k3);
                if(x - 48 == enemy1.x && y == enemy1.y)
                {
                    int l = damage(enemy1);
                    enemy1.hp -= l;
                    battleMsg(enemy1, l);
                    break;
                }
                k3++;
            } while(true);
            for(int l3 = 0; l3 < app.bg.size(); l3++)
            {
                Bg bg1 = (Bg)app.bg.elementAt(l3);
                if(x - 48 != bg1.x || y != bg1.y)
                    continue;
                int i1 = damage(bg1);
                if(app.playSound && bg1.id != 504 && bg1.id != 505 && bg1.id != 508)
                    playHeroAtk();
                bg1.hp -= i1;
                if(bg1.hp <= 0 && bg1.id == 503)
                    bg1.attack(this);
                app.checkDeadAll();
            }

            if(x - 48 == app.musashi.x && y == app.musashi.y)
            {
                int j1 = damage(app.musashi);
                app.musashi.hp -= j1;
                battleMsg(app.musashi, j1);
            }
            break;

        case 2: // '\002'
            int i4 = 0;
            do
            {
                if(i4 >= app.enemy.size())
                    break;
                Enemy enemy2 = (Enemy)app.enemy.elementAt(i4);
                if(x + 48 == enemy2.x && y == enemy2.y)
                {
                    int k1 = damage(enemy2);
                    enemy2.hp -= k1;
                    battleMsg(enemy2, k1);
                    break;
                }
                i4++;
            } while(true);
            for(int j4 = 0; j4 < app.bg.size(); j4++)
            {
                Bg bg2 = (Bg)app.bg.elementAt(j4);
                if(x + 48 != bg2.x || y != bg2.y)
                    continue;
                int l1 = damage(bg2);
                if(app.playSound && bg2.id != 504 && bg2.id != 505 && bg2.id != 508)
                    playHeroAtk();
                bg2.hp -= l1;
                if(bg2.hp <= 0 && bg2.id == 503)
                    bg2.attack(this);
                app.checkDeadAll();
            }

            if(x + 48 == app.musashi.x && y == app.musashi.y)
            {
                int i2 = damage(app.musashi);
                app.musashi.hp -= i2;
                battleMsg(app.musashi, i2);
            }
            break;

        case 3: // '\003'
            int k4 = 0;
            do
            {
                if(k4 >= app.enemy.size())
                    break;
                Enemy enemy3 = (Enemy)app.enemy.elementAt(k4);
                if(x == enemy3.x && y - 48 == enemy3.y)
                {
                    int j2 = damage(enemy3);
                    enemy3.hp -= j2;
                    battleMsg(enemy3, j2);
                    break;
                }
                k4++;
            } while(true);
            for(int l4 = 0; l4 < app.bg.size(); l4++)
            {
                Bg bg3 = (Bg)app.bg.elementAt(l4);
                if(x != bg3.x || y - 48 != bg3.y)
                    continue;
                int k2 = damage(bg3);
                if(app.playSound && bg3.id != 504 && bg3.id != 505 && bg3.id != 508)
                    playHeroAtk();
                bg3.hp -= k2;
                if(bg3.hp <= 0 && bg3.id == 503)
                    bg3.attack(this);
                app.checkDeadAll();
            }

            if(x == app.musashi.x && y - 48 == app.musashi.y)
            {
                int l2 = damage(app.musashi);
                app.musashi.hp -= l2;
                battleMsg(app.musashi, l2);
            }
            break;
        }
    }

    void playHeroAtk()
    {
        if(app.soundHeroAtk == 1)
        {
            app.heroAtk.play();
            app.soundHeroAtk = 2;
        } else
        {
            app.heroAtk2.play();
            app.soundHeroAtk = 1;
        }
    }

    int damage(Chara chara)
    {
        if(app.getRandom(10) == 7)
        {
            int k = 0;
            return k;
        }
        int i = ap - chara.dp;
        int j = app.getRandom(40);
        int l = (i * 80) / 100 + (i * j) / 100;
        if(l <= 0)
            l = app.getRandom(2) + 1;
        return l;
    }

    int battleMsg(Chara chara, int i)
    {
        if(i > 0)
        {
            chara.motionCount = 0;
            chara.stat = 12;
            if(app.playSound)
                if(chara.kind == 2)
                    app.heroAtk.play();
                else
                    app.enemyAtk.play();
            app.addMsg(name + "\u306F " + chara.name + "\u306B " + i + "\u306E\u30C0\u30E1\u30FC\u30B8\uFF01");
            if(chara.paralysis)
            {
                chara.paralysis = false;
                chara.moved = false;
            }
            if(kind == 1)
            {
                app.musashi.weapon.consu(app.musashi);
                if(app.musashi.weapon.id == 107)
                {
                    int j = (app.musashi.maxHp / 100) * 3;
                    app.musashi.hp += j;
                    maxCheck(this);
                } else
                if(app.musashi.weapon.id == 111)
                {
                    app.musashi.hp--;
                    app.musashi.checkDead();
                }
            }
            if(chara.kind == 1)
            {
                app.musashi.armor.consu(app.musashi);
                if(app.musashi.armor.id == 209)
                {
                    int k = app.getRandom(4);
                    if(k == 3 && app.musashi.maxHp > 15)
                        app.musashi.maxHp--;
                    maxCheck(this);
                }
            }
            if(chara.hp <= 0 && chara.kind != 1 && chara.name != "\u30E0\u30B5\u30B7")
            {
                app.checkDeadAll();
                app.addMsg(name + "\u306F " + chara.name + "\u3092\u305F\u304A\u3057\u305F!");
                app.addMsg(chara.ep + "\u306E\u7D4C\u9A13\u5024\u3092\u624B\u306B\u3044\u308C\u305F!");
                ep += chara.ep;
                if(!isMetamo)
                    levelUp();
            }
        } else
        if(i <= 0)
        {
            app.addMsg("\u30DF\u30B9!!");
            if(app.playSound)
                app.miss.play();
        }
        app.checkDeadAll();
        return 0;
    }

    boolean checkHit()
    {
        switch(dir)
        {
        case 0: // '\0'
            if(y + 48 >= 192)
                return true;
            for(int i = 0; i < app.enemy.size(); i++)
            {
                Enemy enemy = (Enemy)app.enemy.elementAt(i);
                if(x == enemy.x && y + 48 == enemy.y)
                    return true;
            }

            for(int j = 0; j < app.bg.size(); j++)
            {
                Bg bg = (Bg)app.bg.elementAt(j);
                if((bg.id == 504 || bg.id == 505 || bg.id == 508) && this == app.musashi && x == bg.x && y + 48 == bg.y)
                    return false;
                if(bg.id == 508 && kind == 2 && x == bg.x && y + 48 == bg.y)
                    return false;
                if(x == bg.x && y + 48 == bg.y)
                    return true;
            }

            return false;

        case 1: // '\001'
            if(x - 48 < 0)
                return true;
            for(int k = 0; k < app.enemy.size(); k++)
            {
                Enemy enemy1 = (Enemy)app.enemy.elementAt(k);
                if(x - 48 == enemy1.x && y == enemy1.y)
                    return true;
            }

            for(int l = 0; l < app.bg.size(); l++)
            {
                Bg bg1 = (Bg)app.bg.elementAt(l);
                if((bg1.id == 504 || bg1.id == 505 || bg1.id == 508) && this == app.musashi && x - 48 == bg1.x && y == bg1.y)
                    return false;
                if(bg1.id == 508 && kind == 2 && x - 48 == bg1.x && y == bg1.y)
                    return false;
                if(x - 48 == bg1.x && y == bg1.y)
                    return true;
            }

            return false;

        case 2: // '\002'
            if(x + 48 >= 288)
                return true;
            for(int i1 = 0; i1 < app.enemy.size(); i1++)
            {
                Enemy enemy2 = (Enemy)app.enemy.elementAt(i1);
                if(x + 48 == enemy2.x && y == enemy2.y)
                    return true;
            }

            for(int j1 = 0; j1 < app.bg.size(); j1++)
            {
                Bg bg2 = (Bg)app.bg.elementAt(j1);
                if((bg2.id == 504 || bg2.id == 505 || bg2.id == 508) && this == app.musashi && x + 48 == bg2.x && y == bg2.y)
                    return false;
                if(bg2.id == 508 && kind == 2 && x + 48 == bg2.x && y == bg2.y)
                    return false;
                if(x + 48 == bg2.x && y == bg2.y)
                    return true;
            }

            return false;

        case 3: // '\003'
            if(y - 48 < 0)
                return true;
            for(int k1 = 0; k1 < app.enemy.size(); k1++)
            {
                Enemy enemy3 = (Enemy)app.enemy.elementAt(k1);
                if(x == enemy3.x && y - 48 == enemy3.y)
                    return true;
            }

            for(int l1 = 0; l1 < app.bg.size(); l1++)
            {
                Bg bg3 = (Bg)app.bg.elementAt(l1);
                if((bg3.id == 504 || bg3.id == 505 || bg3.id == 508) && this == app.musashi && x == bg3.x && y - 48 == bg3.y)
                    return false;
                if(bg3.id == 508 && kind == 2 && x == bg3.x && y - 48 == bg3.y)
                    return false;
                if(x == bg3.x && y - 48 == bg3.y)
                    return true;
            }

            return false;
        }
        return false;
    }

    int levelUp()
    {
        int i = lv;
        if(lv >= 30)
            return 0;
        do
        {
            if(ep < expTable[lv + 1])
                break;
            lv++;
            ap++;
            maxHp += 5 + app.getRandom(4);
            hp += maxHp / 2;
        } while(lv < 30);
        if(i < lv)
        {
            app.addMsg("\u30EC\u30D9\u30EB" + lv + "\u3078 \u3088\u3046\u3053\u305D!");
            if(app.playSound)
                app.lvUp.play();
        }
        maxCheck(this);
        return 0;
    }

    void draw(Graphics g)
    {
        if(stat == 10)
            g.drawImage(img, x, y, x + 48, y + 48, motion[dir][motionChange], lineY, motion[dir][motionChange] + 48, lineY + 48, app);
        else
        if(stat == 11)
            g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
    }

    int getx()
    {
        return x;
    }

    int gety()
    {
        return y;
    }

    void maxCheck(Chara chara)
    {
        if(chara.hp > chara.maxHp)
            chara.hp = chara.maxHp;
        if(chara.ap > chara.maxAp)
            chara.ap = chara.maxAp;
        if(chara.dp > chara.maxDp)
            chara.dp = chara.maxDp;
        if(chara.ep > chara.maxEp)
            chara.ep = chara.maxEp;
    }

    protected static final int CHARA = 48;
    protected static final int BAG_MAX = 16;
    protected static final int HERO = 1;
    protected static final int ENEMY = 2;
    protected static final int ITEM = 3;
    protected static final int BG = 4;
    protected static final int FRONT = 0;
    protected static final int LEFT = 1;
    protected static final int RIGHT = 2;
    protected static final int BACK = 3;
    protected static final int UP = 4;
    protected static final int DOWN = 5;
    protected static final int WALK = 10;
    protected static final int ATK = 11;
    protected static final int DAMAGE = 12;
    protected static final int WEAPON = 21;
    protected static final int ARMOR = 22;
    protected static final int SEED = 23;
    protected static final int SEED_EF = 24;
    protected static Musashi app;
    protected Image img;
    protected int x;
    protected int y;
    protected int kind;
    protected int id;
    protected String name;
    protected int lv;
    protected int ep;
    protected int maxEp;
    protected int maxHp;
    protected int hp;
    protected int maxAp;
    protected int ap;
    protected int maxDp;
    protected int dp;
    protected Item weapon;
    protected Item armor;
    protected boolean action;
    protected int dir;
    protected int stat;
    protected String comment;
    protected boolean isEquip;
    protected boolean isMetamo;
    protected boolean moved;
    protected boolean paralysis;
    protected int motionChange;
    protected int motionCount;
    protected int motionWait;
    protected int attackCount;
    protected int attackTime;
    protected int waitCount;
    protected int waitTime;
    protected int damageTime;
    protected int motion[][] = {
        {
            0, 0, 0, 0
        }, {
            0, 0, 0, 0
        }, {
            0, 0, 0, 0
        }, {
            0, 0, 0, 0
        }
    };
    protected int atkMotion[] = {
        0, 0, 0, 0
    };
    protected int lineY;
    protected int atkRange;
    int expTable[] = {
        0, 0, 4, 10, 20, 40, 80, 120, 180, 260, 
        360, 500, 680, 880, 1160, 1460, 1780, 2180, 2620, 3000, 
        3550, 4250, 4910, 5680, 6560, 7460, 8360, 9260, 9550, 9760, 
        9999
    };
}
