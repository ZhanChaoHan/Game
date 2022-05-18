// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hero.java

import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.Vector;

class Hero extends Chara
{

    Hero()
    {
        super(app.imgMusashi);
        lineY = 48;
        weaponE_x = 16;
        weaponE_y = 0;
        armorE_x = 16;
        armorE_y = 0;
        moved = false;
        tmpMaxHp = 0;
        tmpAp = 0;
        tmpDp = 0;
        metamoCount = 0;
        kind = 1;
        name = "\u30E0\u30B5\u30B7";
        lv = 1;
        maxHp = 15;
        hp = 15;
        ap = 5;
        dp = 0;
        ep = 0;
        action = true;
        moved = false;
        dir = 3;
        hand = new Hand(0);
        wear = new Wear(0);
        stat = 10;
        isEquip = false;
        bag = new Vector();
        seedW = 0;
        seedGr = 0;
        seedB = 0;
        seedGo = 0;
        weapon = hand;
        armor = wear;
        x = 144;
        y = 96;
        motionChange = 0;
        motionCount = 0;
        attackCount = 0;
        waitCount = 0;
        changeDir = false;
        waitTime = 7;
        attackTime = 7;
        damageTime = 4;
        tmpMaxHp = 0;
        tmpAp = 0;
        tmpDp = 0;
        isMetamo = false;
        metamoCount = 0;
    }

    boolean checkDead()
    {
        if(x < 0 || x > app.gwidth - 48 || y < 0 || y > app.gheight - 48)
        {
            dead();
            return true;
        }
        if(hp <= 0)
        {
            if(seedB >= 1)
            {
                if(isMetamo)
                {
                    app.musashi.maxHp = app.musashi.tmpMaxHp;
                    app.musashi.hp = app.musashi.tmpMaxHp;
                    app.musashi.ap = app.musashi.tmpAp;
                    app.musashi.dp = app.musashi.tmpDp;
                    app.musashi.lineY = 48;
                    app.musashi.kind = 1;
                    app.musashi.metamoCount = 0;
                    isMetamo = false;
                }
                hp = maxHp;
                seedB--;
                app.addMsg("\u30E0\u30B5\u30B7\u306F \u3084\u3089\u308C\u306A\u304B\u3063\u305F!");
                app.addMsg("\u5927\u4E08\u592B\u3060\u3063\u305F!");
                maxCheck(this);
                if(app.playSound)
                    app.heal.play();
                levelUp();
            } else
            {
                dead();
            }
            return true;
        } else
        {
            return false;
        }
    }

    void dead()
    {
        if(hp <= 0)
            hp = 0;
        stat = 10;
        if(app.playSound)
            app.green.stop();
        Musashi _tmp = app;
        app.gameMode = 5;
    }

    void motion()
    {
        if(!changeDir)
            motionCount++;
        else
            motionChange = 0;
        if(stat == 10)
        {
            if(motionCount > 10)
            {
                motionChange++;
                if(motionChange == 4)
                    motionChange = 0;
                motionCount = 0;
            }
        } else
        if(stat == 11)
        {
            if(motionCount > attackTime)
            {
                stat = 10;
                motionCount = 0;
            }
        } else
        if(stat == 12 && motionCount > damageTime)
        {
            stat = 10;
            motionCount = 0;
        }
    }

    void move()
    {
        if(Key.left && Key.flag && !moved)
        {
            Key.flag = false;
            app.nextMsg();
            dir = 1;
            if(!changeDir && !checkHit() && x != 0)
            {
                moved = true;
                x -= 48;
                if(x < 0)
                    x = 0;
                getItem();
            }
        } else
        if(Key.right && Key.flag && !moved)
        {
            Key.flag = false;
            app.nextMsg();
            dir = 2;
            if(!changeDir && !checkHit() && x != 240)
            {
                moved = true;
                x += 48;
                if(x > app.getgw() - 48)
                    x = app.getgw() - 48;
                getItem();
            }
        } else
        if(Key.up && Key.flag && !moved)
        {
            Key.flag = false;
            app.nextMsg();
            dir = 3;
            if(!changeDir && !checkHit())
            {
                moved = true;
                y -= 48;
                if(y < 96)
                {
                    y += 48;
                    app.scroll();
                }
                if(y < 0)
                    y = 0;
                getItem();
            }
        } else
        if(Key.down && Key.flag && !moved)
        {
            Key.flag = false;
            app.nextMsg();
            dir = 0;
            if(!changeDir && !checkHit() && y != 144)
            {
                moved = true;
                y += 48;
                if(y > app.getgh() - 48)
                    y = app.getgh() - 48;
                getItem();
            }
        } else
        if(Key.space && Key.flag && !moved)
        {
            Key.flag = false;
            app.nextMsg();
            changeDir = false;
            moved = true;
            attack();
        } else
        if(Key.v && Key.flag && !moved)
        {
            Key.flag = false;
            if(changeDir)
                changeDir = false;
            else
                changeDir = true;
        } else
        if(Key.f && Key.flag && !moved && !isMetamo)
        {
            Key.flag = false;
            Musashi _tmp = app;
            app.gameMode = 3;
        }
        if(moved)
        {
            Musashi _tmp1 = app;
            if(app.gameMode == 2)
            {
                waitCount++;
                if(waitCount > waitTime)
                {
                    action = false;
                    waitCount = 0;
                    if(isMetamo)
                        metamoCount--;
                    if(isMetamo && metamoCount == 0)
                    {
                        app.musashi.maxHp = app.musashi.tmpMaxHp;
                        app.musashi.hp = app.musashi.tmpMaxHp;
                        app.musashi.ap = app.musashi.tmpAp;
                        app.musashi.dp = app.musashi.tmpDp;
                        app.musashi.lineY = 48;
                        app.musashi.kind = 1;
                        app.musashi.metamoCount = 0;
                        app.addMsg("\u30E0\u30B5\u30B7\u306F \u5143\u306B\u623B\u3063\u305F");
                        if(app.playSound)
                            app.heal.play();
                        isMetamo = false;
                        levelUp();
                    }
                }
            }
        }
        if(!action)
        {
            for(int i = 0; i < app.bg.size(); i++)
            {
                Bg bg = (Bg)app.bg.elementAt(i);
                if((bg.id == 504 || bg.id == 505 || bg.id == 508) && bg.x == x && bg.y == y)
                    bg.attack(this);
            }

        }
    }

    void getItem()
    {
        for(int i = 0; i < app.item.size(); i++)
        {
            Item item = (Item)app.item.elementAt(i);
            if(x != item.x || y != item.y)
                continue;
            if(item.kind == 23)
            {
                if(app.playSound)
                    app.get.play();
                switch(item.id)
                {
                case 301: 
                    seedW++;
                    app.addMsg(item.name + "\u3092 \u624B\u306B\u5165\u308C\u305F");
                    app.item.removeElementAt(i);
                    break;

                case 302: 
                    seedGr++;
                    app.addMsg(item.name + "\u3092 \u624B\u306B\u5165\u308C\u305F");
                    app.item.removeElementAt(i);
                    break;

                case 303: 
                    seedB++;
                    app.addMsg(item.name + "\u3092 \u624B\u306B\u5165\u308C\u305F");
                    app.item.removeElementAt(i);
                    break;

                case 304: 
                    seedGo++;
                    app.addMsg(item.name + "\u3092 \u624B\u306B\u5165\u308C\u305F");
                    app.item.removeElementAt(i);
                    break;
                }
                continue;
            }
            if(bag.size() < 16)
            {
                if(app.playSound)
                    app.get.play();
                bag.addElement(item);
                app.addMsg(item.name + "\u3092 \u624B\u306B\u5165\u308C\u305F");
                app.item.removeElementAt(i);
            } else
            {
                app.addMsg("\u6301\u3061\u7269\u304C \u3044\u3063\u3071\u3044\u3060");
            }
        }

    }

    public void equip(Item item)
    {
        if(item.kind == 21)
        {
            if(weapon == hand)
            {
                weapon = item;
                ap += item.ap;
                dp += item.dp;
                item.isEquip = true;
                if(app.playSound)
                    app.itemUse.play();
            }
        } else
        if(item.kind == 22 && armor == wear)
        {
            armor = item;
            ap += item.ap;
            dp += item.dp;
            item.isEquip = true;
            if(app.playSound)
                app.itemUse.play();
        }
    }

    public void notEquip(Item item)
    {
        if(item.isEquip)
        {
            if(item.kind == 21)
                weapon = hand;
            else
            if(item.kind == 22)
                armor = wear;
            ap -= item.ap;
            dp -= item.dp;
            item.isEquip = false;
            if(app.playSound)
                app.miss.play();
        }
    }

    void draw(Graphics g)
    {
        if(stat == 10)
            g.drawImage(img, x, y, x + 48, y + 48, motion[dir][motionChange], lineY, motion[dir][motionChange] + 48, lineY + 48, app);
        else
        if(stat == 11)
            switch(dir)
            {
            case 0: // '\0'
                if(y != 144)
                    g.drawImage(img, x, y + atkRange, x + 48, y + 48 + atkRange, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                else
                    g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                break;

            case 1: // '\001'
                if(x != 0)
                    g.drawImage(img, x - atkRange, y, (x + 48) - atkRange, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                else
                    g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                break;

            case 2: // '\002'
                if(x != 240)
                    g.drawImage(img, x + atkRange, y, x + 48 + atkRange, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                else
                    g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                break;

            case 3: // '\003'
                if(y != 0)
                    g.drawImage(img, x, y - atkRange, x + 48, (y + 48) - atkRange, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                else
                    g.drawImage(img, x, y, x + 48, y + 48, atkMotion[dir], lineY, atkMotion[dir] + 48, lineY + 48, app);
                break;

            default:
                g.drawImage(img, x, y, x + 48, y + 48, motion[dir][motionChange], lineY, motion[dir][motionChange] + 48, lineY + 48, app);
                break;
            }
        else
        if(stat == 12)
            g.drawImage(img, x, y, x + 48, y + 48, 0, 0, 48, 48, app);
    }

    protected Vector bag;
    protected Item weapon;
    protected Item armor;
    private int motion[][] = {
        {
            0, 48, 0, 96
        }, {
            144, 192, 144, 240
        }, {
            288, 336, 288, 384
        }, {
            432, 480, 432, 528
        }
    };
    private int atkMotion[] = {
        576, 624, 672, 720
    };
    protected int lineY;
    protected boolean changeDir;
    protected int seedW;
    protected int seedGr;
    protected int seedB;
    protected int seedGo;
    protected Hand hand;
    protected Wear wear;
    protected int weaponE_x;
    protected int weaponE_y;
    protected int armorE_x;
    protected int armorE_y;
    protected boolean moved;
    protected int tmpMaxHp;
    protected int tmpAp;
    protected int tmpDp;
    protected int metamoCount;
}
