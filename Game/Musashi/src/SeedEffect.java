// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SeedEffect.java

import java.applet.AudioClip;
import java.util.Vector;

class SeedEffect extends Item
{

    SeedEffect()
    {
        seedEffectMax = 16;
        kind = 24;
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

    void draw()
    {
    }

    void exe(int i)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            if(hasSeed(app.musashi, 1, 1, i))
                growWood(i, app.musashi);
            break;

        case 1: // '\001'
            if(hasSeed(app.musashi, 1, 3, i))
                setDrop(i, app.musashi);
            break;

        case 2: // '\002'
            if(hasSeed(app.musashi, 2, 1, i))
                beParalysis(i, app.musashi);
            break;

        case 3: // '\003'
            if(hasSeed(app.musashi, 1, 1, i))
                hpUp(i, app.musashi, 20);
            break;

        case 4: // '\004'
            if(hasSeed(app.musashi, 2, 1, i))
                hpUp(i, app.musashi, 50);
            break;

        case 5: // '\005'
            if(hasSeed(app.musashi, 1, 3, i))
                hpUp(i, app.musashi, 60);
            break;

        case 6: // '\006'
            if(hasSeed(app.musashi, 1, 5, i))
                hpUp(i, app.musashi, 100);
            break;

        case 7: // '\007'
            if(hasSeed(app.musashi, 2, 2, i))
                hpUp(i, app.musashi, 9999);
            break;

        case 8: // '\b'
            if(hasSeed(app.musashi, 1, 3, i))
                apUp(i, app.musashi, app.musashi.weapon, 1);
            break;

        case 9: // '\t'
            if(hasSeed(app.musashi, 1, 3, i))
                hpUp(i, app.musashi.weapon, 30);
            break;

        case 10: // '\n'
            if(hasSeed(app.musashi, 2, 1, i))
                hpUp(i, app.musashi.armor, 30);
            break;

        case 11: // '\013'
            if(hasSeed(app.musashi, 2, 1, i))
                maxHpUp(i, app.musashi, 10);
            break;

        case 12: // '\f'
            if(hasSeed(app.musashi, 3, 1, i))
                maxHpUp(i, app.musashi, 30);
            break;

        case 13: // '\r'
            if(hasSeed(app.musashi, 3, 1, i))
                metamo(i);
            break;

        case 14: // '\016'
            if(hasSeed(app.musashi, 4, 1, i))
                dpUp(i, app.musashi, app.musashi.armor, 5);
            break;

        case 15: // '\017'
            if(hasSeed(app.musashi, 4, 1, i))
                allKill(i);
            break;
        }
    }

    boolean hasSeed(Hero hero, int i, int j, int k)
    {
        int l = 0;
        switch(i)
        {
        case 1: // '\001'
            l = hero.seedW;
            break;

        case 2: // '\002'
            l = hero.seedGr;
            break;

        case 3: // '\003'
            l = hero.seedB;
            break;

        case 4: // '\004'
            l = hero.seedGo;
            break;
        }
        if(l >= j)
        {
            switch(i)
            {
            case 1: // '\001'
                hero.seedW -= j;
                break;

            case 2: // '\002'
                hero.seedGr -= j;
                break;

            case 3: // '\003'
                hero.seedB -= j;
                break;

            case 4: // '\004'
                hero.seedGo -= j;
                break;
            }
            return true;
        } else
        {
            app.addMsg(seedName[k][0] + "\u306F \u5931\u6557!");
            return false;
        }
    }

    void metamo(int i)
    {
        int j = 0;
        app.musashi.isMetamo = true;
        app.musashi.tmpMaxHp = app.musashi.maxHp;
        app.musashi.tmpAp = app.musashi.ap;
        app.musashi.tmpDp = app.musashi.dp;
        j = app.getRandom(2);
        switch(j)
        {
        default:
            break;

        case 0: // '\0'
            app.musashi.maxHp = 325;
            app.musashi.hp = 325;
            app.musashi.ap = 54;
            app.musashi.dp = 10;
            app.musashi.lineY = 432;
            app.musashi.kind = 2;
            if(app.playSound)
                app.seedUse.play();
            app.addMsg("\u30E0\u30B5\u30B7\u306F \u30C9\u30E9\u30B4\u30F3\u306B\u5909\u8EAB\u3057\u305F\u3063!");
            break;

        case 1: // '\001'
            app.musashi.maxHp = 10;
            app.musashi.hp = 10;
            app.musashi.ap = 3;
            app.musashi.dp = 0;
            app.musashi.lineY = 96;
            app.musashi.kind = 2;
            if(app.playSound)
                app.seedUse.play();
            app.addMsg("\u30E0\u30B5\u30B7\u306F \u30D5\u30E9\u30EA\u30FC\u306B\u5909\u8EAB\u3057\u305F\u3063!");
            break;
        }
        app.musashi.metamoCount = 30;
    }

    void allKill(int i)
    {
        int j = 0;
        if(app.enemy.size() != 0)
        {
            for(int k = 0; k < app.enemy.size(); k++)
            {
                Enemy enemy = (Enemy)app.enemy.elementAt(k);
                enemy.hp -= 9999;
                enemy.stat = 12;
                if(enemy.hp <= 0)
                    j += enemy.ep;
            }

            app.checkDeadAll();
            app.musashi.ep += j;
            app.addMsg("\u307E\u3068\u3081\u3066\u3084\u3063\u3064\u3051\u305F\u3063!");
            app.addMsg(j + "\u306E\u7D4C\u9A13\u5024\u3092\u624B\u306B\u3044\u308C\u305F!");
            Musashi _tmp = app;
            app.gameMode = 6;
            if(app.playSound)
                app.katana.play();
        } else
        {
            app.addMsg("\u8AB0\u3082 \u3044\u306A\u304B\u3063\u305F\u2026");
        }
    }

    void beParalysis(int i, Chara chara)
    {
label0:
        switch(chara.dir)
        {
        default:
            break;

        case 0: // '\0'
            int j = 0;
            do
            {
                if(j >= app.enemy.size())
                    break;
                Enemy enemy = (Enemy)app.enemy.elementAt(j);
                if(chara.x == enemy.x && chara.y + 48 == enemy.y)
                {
                    enemy.paralysis = true;
                    app.addMsg("" + enemy.name + "\u306F \u307E\u3072\u3063\u305F!");
                    if(app.playSound)
                        app.seedUse.play();
                    break;
                }
                j++;
            } while(true);
            break;

        case 1: // '\001'
            int k = 0;
            do
            {
                if(k >= app.enemy.size())
                    break label0;
                Enemy enemy1 = (Enemy)app.enemy.elementAt(k);
                if(chara.x - 48 == enemy1.x && chara.y == enemy1.y)
                {
                    enemy1.paralysis = true;
                    app.addMsg("" + enemy1.name + "\u306F \u307E\u3072\u3063\u305F!");
                    if(app.playSound)
                        app.seedUse.play();
                    break label0;
                }
                k++;
            } while(true);

        case 2: // '\002'
            int l = 0;
            do
            {
                if(l >= app.enemy.size())
                    break label0;
                Enemy enemy2 = (Enemy)app.enemy.elementAt(l);
                if(chara.x + 48 == enemy2.x && chara.y == enemy2.y)
                {
                    enemy2.paralysis = true;
                    app.addMsg("" + enemy2.name + "\u306F \u307E\u3072\u3063\u305F!");
                    if(app.playSound)
                        app.seedUse.play();
                    break label0;
                }
                l++;
            } while(true);

        case 3: // '\003'
            int i1 = 0;
            do
            {
                if(i1 >= app.enemy.size())
                    break label0;
                Enemy enemy3 = (Enemy)app.enemy.elementAt(i1);
                if(chara.x == enemy3.x && chara.y - 48 == enemy3.y)
                {
                    enemy3.paralysis = true;
                    app.addMsg("" + enemy3.name + "\u306F \u307E\u3072\u3063\u305F!");
                    if(app.playSound)
                        app.seedUse.play();
                    break label0;
                }
                i1++;
            } while(true);
        }
    }

    void setDrop(int i, Chara chara)
    {
        int j = 0;
        int k = 0;
        if(!chara.checkHit())
        {
            switch(chara.dir)
            {
            case 0: // '\0'
                j = chara.x;
                k = chara.y + 48;
                break;

            case 1: // '\001'
                j = chara.x - 48;
                k = chara.y;
                break;

            case 2: // '\002'
                j = chara.x + 48;
                k = chara.y;
                break;

            case 3: // '\003'
                j = chara.x;
                k = chara.y - 48;
                break;
            }
            app.bg.addElement(new Drop(j, k));
            app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
            app.addMsg(seedName[i][3]);
            if(app.playSound)
                app.seedUse.play();
        } else
        {
            app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
        }
    }

    void growWood(int i, Chara chara)
    {
        int j = 0;
        int k = 0;
        if(!chara.checkHit())
        {
            switch(chara.dir)
            {
            case 0: // '\0'
                j = chara.x;
                k = chara.y + 48;
                break;

            case 1: // '\001'
                j = chara.x - 48;
                k = chara.y;
                break;

            case 2: // '\002'
                j = chara.x + 48;
                k = chara.y;
                break;

            case 3: // '\003'
                j = chara.x;
                k = chara.y - 48;
                break;
            }
            app.bg.addElement(new Wood(j, k));
            app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
            app.addMsg(seedName[i][3]);
            if(app.playSound)
                app.wood.play();
        } else
        {
            app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
        }
    }

    int growWoodLine(int i, Chara chara)
    {
        Wood wood = new Wood(0, 0);
        int j = 0;
        int k = 0;
        switch(chara.dir)
        {
        case 0: // '\0'
            k = chara.y + 48;
            wood.y = chara.y;
            break;

        case 1: // '\001'
            j = chara.x - 48;
            wood.x = chara.x;
            break;

        case 2: // '\002'
            j = chara.x + 48;
            wood.x = chara.x;
            break;

        case 3: // '\003'
            k = chara.y - 48;
            wood.y = chara.y;
            break;
        }
        wood.dir = chara.dir;
        if(chara.dir == 0 || chara.dir == 3)
        {
            if(chara.dir == 0 && chara.y == 144)
            {
                app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
                return 0;
            }
            for(int l = 0; l < 240; l += 48)
                if(!wood.checkHit())
                {
                    app.bg.addElement(new Wood(j, k));
                    wood.x += 48;
                    j += 48;
                } else
                {
                    wood.x += 48;
                    j += 48;
                }

        } else
        if(chara.dir == 1 || chara.dir == 2)
        {
            if(chara.dir == 1 && chara.x == 0 || chara.dir == 2 && chara.x == 240)
            {
                app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
                return 0;
            }
            for(int i1 = 0; i1 < 144; i1 += 48)
                if(!wood.checkHit())
                {
                    app.bg.addElement(new Wood(j, k));
                    wood.y += 48;
                    k += 48;
                } else
                {
                    wood.y += 48;
                    k += 48;
                }

        }
        app.bg.addElement(new Wood(j, k));
        app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
        app.addMsg(seedName[i][3]);
        if(app.playSound)
            app.wood.play();
        return 0;
    }

    void growWoodCross(int i, Chara chara)
    {
        boolean flag = false;
        boolean flag1 = false;
        int j2 = chara.dir;
        chara.dir = 0;
        if(!chara.checkHit())
        {
            int j1 = chara.y + 48;
            int j = chara.x;
            app.bg.addElement(new Wood(j, j1));
        }
        chara.dir = 1;
        if(!chara.checkHit())
        {
            int k = chara.x - 48;
            int k1 = chara.y;
            app.bg.addElement(new Wood(k, k1));
        }
        chara.dir = 2;
        if(!chara.checkHit())
        {
            int l = chara.x + 48;
            int l1 = chara.y;
            app.bg.addElement(new Wood(l, l1));
        }
        chara.dir = 3;
        if(!chara.checkHit())
        {
            int i2 = chara.y - 48;
            int i1 = chara.x;
            app.bg.addElement(new Wood(i1, i2));
        }
        chara.dir = j2;
        app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
        app.addMsg(seedName[i][3]);
        if(app.playSound)
            app.wood.play();
    }

    void hpUp(int i, Chara chara, int j)
    {
        if(chara.id != 101 && chara.id != 201)
        {
            chara.hp += j;
            app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
            app.addMsg(seedName[i][3]);
            maxCheck(chara);
            if(app.playSound)
                app.heal.play();
        } else
        {
            app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
        }
    }

    void apUp(int i, Chara chara, Item item, int j)
    {
        if(item.id != 101)
        {
            chara.ap += j;
            item.ap += j;
            app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
            app.addMsg(seedName[i][3]);
            maxCheck(chara);
            if(app.playSound)
                app.seedUse.play();
        } else
        {
            app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
        }
    }

    void dpUp(int i, Chara chara, Item item, int j)
    {
        if(item.id != 201)
        {
            chara.dp += j;
            item.dp += j;
            app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
            app.addMsg(seedName[i][3]);
            maxCheck(chara);
            if(app.playSound)
                app.seedUse.play();
        } else
        {
            app.addMsg(seedName[i][0] + "\u306F \u5931\u6557!");
        }
    }

    void maxHpUp(int i, Chara chara, int j)
    {
        chara.maxHp += j;
        app.addMsg(seedName[i][0] + "\u3092 \u3064\u304B\u3063\u305F!");
        app.addMsg(seedName[i][3]);
        maxCheck(chara);
        if(app.playSound)
            app.seedUse.play();
    }

    int seedEffectMax;
    String seedName[][] = {
        {
            "\u6728\u970A", "\u767D1", "\u76EE\u306E\u524D\u306B \u6728\u3092\u751F\u3084\u3059\u305E!", "\u6728\u304C \u751F\u3048\u305F\u3063!"
        }, {
            "\u843D\u3068\u3057\u7A74", "\u767D3", "\u76EE\u306E\u524D\u306B \u843D\u3068\u3057\u7A74\u3092\u4F5C\u308B\u305E!", "\u843D\u3068\u3057\u7A74\u3092 \u4F5C\u3063\u305F"
        }, {
            "\u30DE\u30D2\u30DE\u30D2", "\u70701", "\u76EE\u306E\u524D\u306E\u6575\u3092 \u307E\u3072\u3055\u305B\u308B\u305E!", "\u2026"
        }, {
            "20\u5143\u6C17", "\u767D1", "HP\u304C 20\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!", "\u4F53\u529B\u304C \u5C11\u3057\u56DE\u5FA9\u3057\u305F"
        }, {
            "50\u5143\u6C17", "\u70701", "HP\u304C 50\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!", "\u4F53\u529B\u304C \u56DE\u5FA9\u3057\u305F"
        }, {
            "60\u5143\u6C17", "\u767D3", "HP\u304C 60\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!", "\u4F53\u529B\u304C \u3051\u3063\u3053\u3046\u56DE\u5FA9\u3057\u305F"
        }, {
            "100\u5143\u6C17", "\u767D5", "HP\u304C 100\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!", "\u4F53\u529B\u304C \u304B\u306A\u308A\u56DE\u5FA9\u3057\u305F"
        }, {
            "\u5168\u5FEB", "\u70702", "HP\u304C \u5168\u90E8\u56DE\u5FA9\u3057\u3061\u3083\u3046\u305E!", "\u4F53\u529B\u304C \u5B8C\u5168\u306B\u56DE\u5FA9\u3057\u305F!"
        }, {
            "\u5200\u7814\u78E8", "\u767D3", "\u6B66\u5668\u306E\u653B\u6483\u529B\u304C 1\u4E0A\u304C\u308B\u305E!", "\u6B66\u5668\u304C \u5F37\u304F\u306A\u3063\u305F"
        }, {
            "\u5200\u935B\u51B6", "\u767D3", "\u6B66\u5668\u306E\u8010\u4E45\u529B\u304C 30\u4E0A\u304C\u308B\u305E!", "\u6B66\u5668\u304C \u4E08\u592B\u306B\u306A\u3063\u305F"
        }, {
            "\u93A7\u88DC\u4FEE", "\u70701", "\u9632\u5177\u306E\u8010\u4E45\u529B\u304C 30\u4E0A\u304C\u308B\u305E!", "\u9632\u5177\u304C \u4E08\u592B\u306B\u306A\u3063\u305F"
        }, {
            "\u6D3B\u529B\u7A2E", "\u70701", "HP\u306E\u6700\u5927\u5024\u304C 10\u4E0A\u304C\u308B\u305E!", "\u4F53\u529B\u304C \u3075\u3048\u305F"
        }, {
            "\u8D85\u529B\u7A2E", "\u9ED21", "HP\u306E\u6700\u5927\u5024\u304C 30\u4E0A\u304C\u308B\u305E!", "\u4F53\u529B\u304C \u304B\u306A\u308A\u3075\u3048\u305F!"
        }, {
            "\u7269\u602A", "\u9ED21", "\u30E2\u30F3\u30B9\u30BF\u30FC\u306B \u5909\u8EAB\u3057\u3061\u3083\u3046\u305E!", "\u30E2\u30F3\u30B9\u30BF\u30FC\u306B \u306A\u3063\u305F!"
        }, {
            "\u93A7\u8D85\u529B", "\u91D11", "\u9632\u5177\u306E\u9632\u5FA1\u529B\u304C 5\u4E0A\u304C\u308B\u305E!", "\u9632\u5177\u304C \u3059\u3054\u304F\u5F37\u304F\u306A\u3063\u305F!"
        }, {
            "\u5FC5\u6BBA", "\u91D11", "\u6575\u3092\u5168\u6EC5\u3055\u305B\u308B\u305E!", "\u2026"
        }
    };
}
