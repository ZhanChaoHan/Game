// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java

import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

class Enemy extends Chara
{

    Enemy()
    {
        super(app.imgMusashi);
        lineY = 0;
        kind = 2;
        action = false;
        dir = 0;
        stat = 10;
        motionChange = 0;
        motionCount = 0;
        motionWait = 10;
        attackCount = 0;
        waitCount = 0;
        waitTime = 0;
        moveEnd = false;
        moveType = 0;
        motionType = 0;
    }

    Enemy(Image image)
    {
        super(image);
        lineY = 0;
        kind = 2;
        action = false;
        dir = 0;
        stat = 10;
        motionChange = 0;
        motionCount = 0;
        motionWait = 10;
        attackCount = 0;
        waitCount = 0;
        waitTime = 0;
        moveEnd = false;
        moveType = 0;
        motionType = 0;
    }

    void motion()
    {
        motionCount++;
        if(!paralysis)
            if(stat == 10)
            {
                if(motionCount > motionWait)
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
        if(paralysis)
            moved = true;
        else
        if(moveType == 0)
        {
            if((app.musashi.x == x && app.musashi.y - 48 == y || app.musashi.x == x && app.musashi.y + 48 == y || app.musashi.y == y && app.musashi.x - 48 == x || app.musashi.y == y && app.musashi.x + 48 == x) && !moved)
            {
                if(app.musashi.x == x && app.musashi.y - 48 == y)
                    dir = 0;
                else
                if(app.musashi.x == x && app.musashi.y + 48 == y)
                    dir = 3;
                else
                if(app.musashi.y == y && app.musashi.x - 48 == x)
                    dir = 2;
                else
                if(app.musashi.y == y && app.musashi.x + 48 == x)
                    dir = 1;
                attack();
                moved = true;
            } else
            if(app.musashi.x > x && !moved)
            {
                dir = 2;
                if(checkHit())
                {
                    if(app.musashi.y < y && !moved)
                    {
                        dir = 3;
                        if(checkHit())
                        {
                            moved = true;
                            waitCount = waitTime + 1;
                        } else
                        {
                            moved = true;
                            y -= 48;
                        }
                    } else
                    if(app.musashi.y > y && !moved)
                    {
                        dir = 0;
                        if(checkHit())
                        {
                            moved = true;
                            waitCount = waitTime + 1;
                        } else
                        {
                            moved = true;
                            y += 48;
                        }
                    } else
                    {
                        moved = true;
                    }
                } else
                {
                    moved = true;
                    x += 48;
                }
            } else
            if(app.musashi.x < x && !moved)
            {
                dir = 1;
                if(checkHit())
                {
                    if(app.musashi.y < y && !moved)
                    {
                        dir = 3;
                        if(checkHit())
                        {
                            moved = true;
                            waitCount = waitTime + 1;
                        } else
                        {
                            moved = true;
                            y -= 48;
                        }
                    } else
                    if(app.musashi.y > y && !moved)
                    {
                        dir = 0;
                        if(checkHit())
                        {
                            moved = true;
                            waitCount = waitTime + 1;
                        } else
                        {
                            moved = true;
                            y += 48;
                        }
                    } else
                    {
                        moved = true;
                    }
                } else
                {
                    moved = true;
                    x -= 48;
                }
            } else
            if(app.musashi.y > y && !moved)
            {
                dir = 0;
                if(checkHit())
                {
                    moved = true;
                    waitCount = waitTime + 1;
                } else
                {
                    moved = true;
                    y += 48;
                }
            } else
            if(app.musashi.y < y && !moved)
            {
                dir = 3;
                if(checkHit())
                {
                    moved = true;
                    waitCount = waitTime + 1;
                } else
                {
                    moved = true;
                    y -= 48;
                }
            } else
            {
                moved = true;
            }
        } else
        if(moveType == 1)
        {
            if((app.musashi.x == x && app.musashi.y - 48 == y || app.musashi.x == x && app.musashi.y + 48 == y || app.musashi.y == y && app.musashi.x - 48 == x || app.musashi.y == y && app.musashi.x + 48 == x) && !moved)
            {
                if(app.musashi.x == x && app.musashi.y - 48 == y)
                    dir = 0;
                else
                if(app.musashi.x == x && app.musashi.y + 48 == y)
                    dir = 3;
                else
                if(app.musashi.y == y && app.musashi.x - 48 == x)
                    dir = 2;
                else
                if(app.musashi.y == y && app.musashi.x + 48 == x)
                    dir = 1;
                attack();
                moved = true;
            } else
            if(!moved)
                switch(app.getRandom(4))
                {
                case 0: // '\0'
                    dir = 3;
                    if(!checkHit())
                    {
                        moved = true;
                        y -= 48;
                    } else
                    {
                        moved = true;
                    }
                    break;

                case 1: // '\001'
                    dir = 1;
                    if(!checkHit())
                    {
                        moved = true;
                        x -= 48;
                    } else
                    {
                        moved = true;
                    }
                    break;

                case 2: // '\002'
                    dir = 2;
                    if(!checkHit())
                    {
                        moved = true;
                        x += 48;
                    } else
                    {
                        moved = true;
                    }
                    break;

                case 3: // '\003'
                    dir = 0;
                    if(!checkHit())
                    {
                        moved = true;
                        y += 48;
                    } else
                    {
                        moved = true;
                    }
                    break;
                }
        } else
        if(moveType == 2)
        {
            if(!moved)
            {
                if(app.getRandom(3) == 2)
                    attack();
                moved = true;
            }
        } else
        if(moveType == 3)
            if((app.musashi.x == x && app.musashi.y - 48 == y || app.musashi.x == x && app.musashi.y + 48 == y || app.musashi.y == y && app.musashi.x - 48 == x || app.musashi.y == y && app.musashi.x + 48 == x) && !moved)
            {
                if(app.musashi.x == x && app.musashi.y - 48 == y)
                    dir = 0;
                else
                if(app.musashi.x == x && app.musashi.y + 48 == y)
                    dir = 3;
                else
                if(app.musashi.y == y && app.musashi.x - 48 == x)
                    dir = 2;
                else
                if(app.musashi.y == y && app.musashi.x + 48 == x)
                    dir = 1;
                attack();
                moved = true;
            } else
            if(!moved)
                if(dir == 2)
                {
                    if(!checkHit())
                    {
                        moved = true;
                        x += 48;
                    } else
                    {
                        moved = true;
                        dir = 1;
                    }
                } else
                if(dir == 1)
                {
                    if(!checkHit())
                    {
                        moved = true;
                        x -= 48;
                    } else
                    {
                        moved = true;
                        dir = 2;
                    }
                } else
                {
                    switch(app.getRandom(2))
                    {
                    case 0: // '\0'
                        dir = 1;
                        break;

                    case 1: // '\001'
                        dir = 2;
                        break;
                    }
                }
        if(moved)
        {
            waitCount++;
            if(waitCount > waitTime)
            {
                if(motionType == 0 && !paralysis)
                    if(app.getHeroy() > y)
                    {
                        if(app.getHerox() - 48 > x)
                            dir = 2;
                        else
                        if(app.getHerox() + 48 < x)
                            dir = 1;
                        else
                            dir = 0;
                    } else
                    if(app.getHeroy() == y && app.getHerox() > x)
                        dir = 2;
                    else
                    if(app.getHeroy() == y && app.getHerox() < x)
                        dir = 1;
                    else
                    if(app.getHerox() - 48 > x)
                        dir = 2;
                    else
                    if(app.getHerox() + 48 < x)
                        dir = 1;
                    else
                        dir = 3;
                action = false;
                waitCount = 0;
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

    protected int motion[][] = {
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
    protected int atkMotion[] = {
        576, 624, 672, 720
    };
    protected int lineY;
    protected boolean moveEnd;
    protected int moveType;
    protected int motionType;
}
