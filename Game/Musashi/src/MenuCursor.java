// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chara.java

import java.applet.AudioClip;
import java.awt.Graphics;
import java.util.Vector;

class MenuCursor extends Chara
{

    MenuCursor()
    {
        super(app.imgMusashi);
        itemPos = 0;
        seedPos = 0;
        itemIndex = 0;
        seedIndex = 0;
        motionFlag = true;
        menuMainSeed_x = 20;
        menuMainSeed_y = 36;
        menuMainItem_x = 160;
        menuMainItem_y = 36;
        menuMainBgmOn_x = 20;
        menuMainBgmOn_y = 222;
        menuMainBgmOff_x = 160;
        menuMainBgmOff_y = 222;
        menuMainGet_x = 20;
        menuMainGet_y = 66;
        menuItem_x = 4;
        menuItem_y = 10;
        menuItemUse_x = 20;
        menuItemUse_y = 226;
        menuItemDump_x = 160;
        menuItemDump_y = 226;
        menuSeed_x = 4;
        menuSeed_y = 56;
        menuSeedUse_x = 20;
        menuSeedUse_y = 226;
        motionFlag = true;
        motionWait = 15;
        moveMenuMainSeed();
        se = new SeedEffect();
    }

    void move()
    {
        if(Key.left && Key.flag)
        {
            app.keyWaitCount = 0;
            Key.flag = false;
            switch(pos)
            {
            case 12: // '\f'
                moveMenuMainItem();
                break;

            case 13: // '\r'
                moveMenuMainSeed();
                break;

            case 21: // '\025'
                moveMenuMainItem();
                break;

            case 16: // '\020'
                moveMenuItem(1);
                break;

            case 18: // '\022'
                moveMenuItemDump();
                break;

            case 19: // '\023'
                moveMenuItemUse();
                break;

            case 17: // '\021'
                moveMenuSeed(1);
                break;
            }
        } else
        if(Key.right && Key.flag)
        {
            app.keyWaitCount = 0;
            Key.flag = false;
            switch(pos)
            {
            case 12: // '\f'
                moveMenuMainItem();
                break;

            case 13: // '\r'
                moveMenuMainSeed();
                break;

            case 21: // '\025'
                moveMenuMainItem();
                break;

            case 16: // '\020'
                moveMenuItem(2);
                break;

            case 18: // '\022'
                moveMenuItemDump();
                break;

            case 19: // '\023'
                moveMenuItemUse();
                break;

            case 17: // '\021'
                moveMenuSeed(2);
                break;
            }
        } else
        if(Key.up && Key.flag)
        {
            app.keyWaitCount = 0;
            Key.flag = false;
            switch(pos)
            {
            case 12: // '\f'
                moveMenuMainGet();
                break;

            case 13: // '\r'
                moveMenuMainGet();
                break;

            case 21: // '\025'
                moveMenuMainSeed();
                break;

            case 16: // '\020'
                moveMenuItem(4);
                break;

            case 17: // '\021'
                moveMenuSeed(4);
                break;
            }
        } else
        if(Key.down && Key.flag)
        {
            app.keyWaitCount = 0;
            Key.flag = false;
            switch(pos)
            {
            case 12: // '\f'
                moveMenuMainGet();
                break;

            case 13: // '\r'
                moveMenuMainGet();
                break;

            case 21: // '\025'
                moveMenuMainSeed();
                break;

            case 16: // '\020'
                moveMenuItem(5);
                break;

            case 17: // '\021'
                moveMenuSeed(5);
                break;
            }
        } else
        if(Key.space && Key.flag)
        {
            app.keyWaitCount = 0;
            Key.flag = false;
            switch(pos)
            {
            case 12: // '\f'
                exeMenuMainSeed();
                break;

            case 13: // '\r'
                exeMenuMainItem();
                break;

            case 21: // '\025'
                exeMenuMainGet();
                break;

            case 16: // '\020'
                exeMenuItem();
                break;

            case 18: // '\022'
                exeMenuItemUse();
                break;

            case 19: // '\023'
                exeMenuItemDump();
                break;

            case 17: // '\021'
                exeMenuSeed();
                break;

            case 20: // '\024'
                exeMenuSeedUse(seedPos);
                break;
            }
        } else
        if(Key.f && Key.flag)
        {
            Key.flag = false;
            switch(pos)
            {
            case 11: // '\013'
                Musashi _tmp = app;
                app.gameMode = 2;
                break;

            case 12: // '\f'
                Musashi _tmp1 = app;
                app.gameMode = 2;
                break;

            case 13: // '\r'
                Musashi _tmp2 = app;
                app.gameMode = 2;
                break;

            case 21: // '\025'
                Musashi _tmp3 = app;
                app.gameMode = 2;
                break;

            case 17: // '\021'
                Musashi _tmp4 = app;
                app.menuMode = 11;
                moveMenuMainSeed();
                break;

            case 16: // '\020'
                Musashi _tmp5 = app;
                app.menuMode = 11;
                moveMenuMainItem();
                break;

            case 18: // '\022'
                Musashi _tmp6 = app;
                app.menuMode = 13;
                exeMenuMainItem();
                break;

            case 19: // '\023'
                Musashi _tmp7 = app;
                app.menuMode = 13;
                exeMenuMainItem();
                break;

            case 20: // '\024'
                Musashi _tmp8 = app;
                app.menuMode = 12;
                exeMenuMainSeed();
                break;
            }
        }
    }

    void exeMenuMainSeed()
    {
        Musashi _tmp = app;
        app.menuMode = 12;
        pos = 17;
        x = menuSeed_x;
        y = (seedPos - seedIndex) * 22 + 39;
        menuSeed_y = y;
    }

    void exeMenuMainItem()
    {
        Musashi _tmp = app;
        app.menuMode = 13;
        pos = 16;
        x = menuItem_x;
        y = (itemPos - itemIndex) * 22 + 39;
        menuItem_y = y;
    }

    void exeMenuMainGet()
    {
        app.musashi.getItem();
        Musashi _tmp = app;
        app.gameMode = 2;
    }

    void exeMenuMainBgmOn()
    {
    }

    void exeMenuMainBgmOff()
    {
    }

    void exeMenuItem()
    {
        if(app.musashi.bag.size() != 0)
        {
            Musashi _tmp = app;
            app.menuMode = 14;
            moveMenuItemUse();
        }
    }

    void exeMenuItemUse()
    {
        Item item = (Item)app.musashi.bag.elementAt(itemPos);
        if(item.isEquip)
        {
            app.musashi.notEquip(item);
        } else
        {
            if(item.kind == 21)
                if(app.musashi.weapon != app.musashi.hand)
                {
                    app.musashi.notEquip(app.musashi.weapon);
                    app.musashi.equip(item);
                } else
                {
                    app.musashi.equip(item);
                }
            if(item.kind == 22)
                if(app.musashi.armor != app.musashi.wear)
                {
                    app.musashi.notEquip(app.musashi.armor);
                    app.musashi.equip(item);
                } else
                {
                    app.musashi.equip(item);
                }
        }
        exeMenuMainItem();
    }

    void exeMenuItemDump()
    {
        Item item = (Item)app.musashi.bag.elementAt(itemPos);
        if(item.isEquip)
            app.musashi.notEquip(item);
        app.musashi.bag.removeElementAt(itemPos);
        if(app.playSound)
            app.miss.play();
        if(itemPos == app.musashi.bag.size() && itemPos != 0)
            if(itemPos == itemIndex)
            {
                itemPos--;
                itemIndex--;
            } else
            {
                itemPos--;
            }
        exeMenuMainItem();
    }

    void exeMenuSeed()
    {
        Musashi _tmp = app;
        app.menuMode = 15;
        moveMenuSeedUse();
    }

    void exeMenuSeedUse(int i)
    {
        se.exe(i);
        app.musashi.moved = true;
        app.musashi.changeDir = false;
        moveMenuMainSeed();
        Musashi _tmp = app;
        if(app.gameMode != 6)
        {
            Musashi _tmp1 = app;
            app.gameMode = 2;
        }
    }

    void moveMenuMainSeed()
    {
        x = menuMainSeed_x;
        y = menuMainSeed_y;
        pos = 12;
        Musashi _tmp = app;
        app.menuMode = 11;
    }

    void moveMenuMainItem()
    {
        x = menuMainItem_x;
        y = menuMainItem_y;
        pos = 13;
        Musashi _tmp = app;
        app.menuMode = 11;
    }

    void moveMenuMainBgmOn()
    {
        x = menuMainBgmOn_x;
        y = menuMainBgmOn_y;
        pos = 14;
        Musashi _tmp = app;
        app.menuMode = 11;
    }

    void moveMenuMainBgmOff()
    {
        x = menuMainBgmOff_x;
        y = menuMainBgmOff_y;
        pos = 15;
        Musashi _tmp = app;
        app.menuMode = 11;
    }

    void moveMenuMainGet()
    {
        x = menuMainGet_x;
        y = menuMainGet_y;
        pos = 21;
        Musashi _tmp = app;
        app.menuMode = 11;
    }

    int moveMenuItem(int i)
    {
        int j = app.musashi.bag.size();
        pos = 16;
        if(j == 0)
            return 0;
        switch(i)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        default:
            break;

        case 4: // '\004'
            if(itemPos != 0)
            {
                itemPos--;
                if(itemPos == itemIndex - 1)
                    itemIndex--;
            }
            break;

        case 5: // '\005'
            if(itemPos == j - 1)
                break;
            itemPos++;
            if(itemPos == itemIndex + 8)
                itemIndex++;
            break;
        }
        y = (itemPos - itemIndex) * 22 + 39;
        menuItem_y = y;
        return 0;
    }

    void moveMenuItemUse()
    {
        pos = 18;
        x = menuItemUse_x;
        y = menuItemUse_y;
    }

    void moveMenuItemDump()
    {
        pos = 19;
        x = menuItemDump_x;
        y = menuItemDump_y;
    }

    void moveMenuSeed(int i)
    {
        int j = se.seedEffectMax;
        pos = 17;
        switch(i)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        default:
            break;

        case 4: // '\004'
            if(seedPos != 0)
            {
                seedPos--;
                if(seedPos == seedIndex - 1)
                    seedIndex--;
            }
            break;

        case 5: // '\005'
            if(seedPos == j - 1)
                break;
            seedPos++;
            if(seedPos == seedIndex + 8)
                seedIndex++;
            break;
        }
        y = (seedPos - seedIndex) * 22 + 39;
        menuSeed_y = y;
    }

    void moveMenuSeedUse()
    {
        pos = 20;
        x = menuSeedUse_x;
        y = menuSeedUse_y;
    }

    void motion()
    {
        motionCount++;
        if(motionCount > motionWait)
        {
            if(motionFlag)
                motionFlag = false;
            else
                motionFlag = true;
            motionCount = 0;
        }
    }

    void draw(Graphics g)
    {
        if((pos != 16 || app.musashi.bag.size() != 0) && motionFlag)
            g.drawImage(img, x, y, x + 16, y + 24, 624, 0, 640, 24, app);
        if(pos == 18 || pos == 19)
            g.drawImage(img, menuItem_x, menuItem_y, menuItem_x + 16, menuItem_y + 24, 624, 0, 640, 24, app);
        if(pos == 20)
            g.drawImage(img, menuSeed_x, menuSeed_y, menuSeed_x + 16, menuSeed_y + 24, 624, 0, 640, 24, app);
    }

    public static final int MENU_MAIN = 11;
    public static final int MENU_MAIN_SEED = 12;
    public static final int MENU_MAIN_ITEM = 13;
    public static final int MENU_BGM_ON = 14;
    public static final int MENU_BGM_OFF = 15;
    public static final int MENU_ITEM = 16;
    public static final int MENU_SEED = 17;
    public static final int MENU_ITEM_USE = 18;
    public static final int MENU_ITEM_DUMP = 19;
    public static final int MENU_SEED_USE = 20;
    public static final int MENU_MAIN_GET = 21;
    int pos;
    int itemPos;
    int seedPos;
    int itemIndex;
    int seedIndex;
    boolean motionFlag;
    int menuMainSeed_x;
    int menuMainSeed_y;
    int menuMainItem_x;
    int menuMainItem_y;
    int menuMainBgmOn_x;
    int menuMainBgmOn_y;
    int menuMainBgmOff_x;
    int menuMainBgmOff_y;
    int menuMainGet_x;
    int menuMainGet_y;
    int menuItem_x;
    int menuItem_y;
    int menuItemUse_x;
    int menuItemUse_y;
    int menuItemDump_x;
    int menuItemDump_y;
    int menuSeed_x;
    int menuSeed_y;
    int menuSeedUse_x;
    int menuSeedUse_y;
    SeedEffect se;
}
