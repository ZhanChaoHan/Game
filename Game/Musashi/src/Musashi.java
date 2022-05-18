// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Musashi.java

import java.applet.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Vector;

public class Musashi extends Applet
    implements Runnable
{

    public Musashi()
    {
        next = new int[6];
        debug = false;
        soundHeroAtk = 1;
        playSound = true;
        gameOverCount = 0;
        enemyDraw = false;
        allKillCount = 0;
        sendScore = true;
        gameClear = false;
    }

    public void init()
    {
        width = 384;
        height = 256;
        gwidth = 288;
        gheight = 192;
        ver = 0.91000000000000003D;
        setBackground(Color.white);
        setForeground(Color.black);
        if(debug)
        {
            width += 400;
            height += 400;
        }
        msg = new String[64];
        offImg = createImage(width, height);
        gv = offImg.getGraphics();
        stdFont = new Font("Serif", 1, 18);
        smallFont = new Font("Serif", 1, 14);
        gv.setFont(stdFont);
        Chara.app = this;
        Key.app = this;
        ScoreFrame.app = this;
        addKeyListener(new Key());
        mt = new MediaTracker(this);
        imgMusashi = getImage(getDocumentBase(), "spr_t2.gif");
        imgMusashi2 = getImage(getDocumentBase(), "spr_t3.gif");
        mt.addImage(imgMusashi, 0);
        mt.addImage(imgMusashi2, 1);
        heroAtk = getAudioClip(getCodeBase(), "heroAtk.au");
        heroAtk2 = getAudioClip(getCodeBase(), "heroAtk.au");
        enemyAtk = getAudioClip(getCodeBase(), "enemyAtk.au");
        lvUp = getAudioClip(getCodeBase(), "lvUp.au");
        heal = getAudioClip(getCodeBase(), "heal.au");
        miss = getAudioClip(getCodeBase(), "miss.au");
        get = getAudioClip(getCodeBase(), "get.au");
        seedUse = getAudioClip(getCodeBase(), "seedUse.au");
        itemUse = getAudioClip(getCodeBase(), "itemUse.au");
        crash = getAudioClip(getCodeBase(), "crash.au");
        fall = getAudioClip(getCodeBase(), "fall.au");
        wood = getAudioClip(getCodeBase(), "wood.au");
        green = getAudioClip(getCodeBase(), "ry.au");
        katana = getAudioClip(getCodeBase(), "katana.au");
        gameMode = 4;
        menuMode = 11;
        msgTime = 80;
        requestFocus();
    }

    public void start()
    {
        if(gameThread == null)
        {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stop()
    {
        if(playSound)
            green.stop();
        gameThread = null;
    }

    public void run() {
        while (true)
          if (this.gameThread == Thread.currentThread())
            if (isActive()) {
              this.gv.clearRect(0, 0, this.width, this.height);

              switch (this.gameMode) { case 4:
                this.gv.drawString("よみこみちゅう...", 130, 150);
                repaint();
                gameInit(); break;
              case 1:
                gameTop(); break;
              case 2:
                gameMain(); break;
              case 3:
                gameMenu(); break;
              case 5:
                gameOver(); break;
              case 6:
                gameAllKill(); break;
              case 7:
                gameClear();
              }

              if (this.debug == true) debugWindow(this.gv);
              repaint();
              try
              {
                Thread.sleep(20L);
              }
              catch (InterruptedException localInterruptedException)
              {
              }
            }
      }

    private void gameInit()
    {
        boolean flag = false;
        enemy = new Vector();
        item = new Vector();
        bg = new Vector();
        if(mt.statusAll(true) == 8)
        {
            gameReady();
            gameMode = 1;
        }
    }

    private void gameReady()
    {
        gv.setColor(Color.black);
        musashi = new Hero();
        enemy = new Vector();
        item = new Vector();
        bg = new Vector();
        enemyIndex = 0;
        distance = 0;
        gameOverCount = 0;
        gameOverSe = true;
        gameTopSe = true;
        keyWaitCount = 0;
        keyWaitTime = 8;
        menuCursor = new MenuCursor();
        menuMode = 11;
        se = new SeedEffect();
        clearMsg();
        gameClear = false;
        if(playSound)
            green.stop();
        requestFocus();
    }

    private void gameTop()
    {
        byte byte0 = 96;
        byte byte1 = 48;
        byte byte2 = 96;
        gv.drawRect(68, 20, 247, 151);
        gv.fillRect(72, 24, 240, 144);
        gv.drawImage(imgMusashi, byte0, byte1, byte0 + 192, byte1 + 48, 144, 384, 336, 432, this);
        gv.drawImage(imgMusashi, byte0, byte2, byte0 + 192, byte2 + 48, 336, 384, 528, 432, this);
        drawCenter("\u30B9\u30DA\u30FC\u30B9\u30AD\u30FC\u3067\u30B9\u30BF\u30FC\u30C8!", 216);
        if(gameTopSe)
        {
            if(playSound)
                katana.play();
            gameTopSe = false;
        }
        if(Key.space && Key.flag)
        {
            gameMode = 2;
            Key.flag = false;
            if(playSound)
                itemUse.play();
            addMsg("\u300C\u3088\u30FC\u3057\u3001\u304C\u3093\u3070\u308B\u305E\u30FC!\u300D");
            if(playSound)
                green.loop();
        }
        if(!Key.flag)
            keyWaitCount++;
        if(keyWaitCount > keyWaitTime)
        {
            Key.flag = true;
            keyWaitCount = 0;
        }
    }

    private void gameOver()
    {
        gameOverCount++;
        if(gameOverCount > 150)
        {
            if(gameOverSe)
            {
                Key.flag = false;
                if(playSound)
                    miss.play();
                gameOverSe = false;
            }
            gv.setColor(Color.white);
            gv.fillRect(0, 0, width, height);
            gv.setColor(Color.black);
            drawCenter("\u30E0\u30B5\u30B7\u306F " + distance + "m\u5730\u70B9\u3067 \u305F\u304A\u308C\u305F\u2026", 50);
            drawCenter("\u30B9\u30DA\u30FC\u30B9\u30AD\u30FC\u3092\u62BC\u3057\u3066\u304F\u3060\u3055\u3044", 224);
            gv.drawImage(imgMusashi, musashi.x, musashi.y, musashi.x + 48, musashi.y + 48, 720, 0, 768, 48, this);
            if(Key.space && Key.flag)
            {
                Key.flag = false;
                if(sendScore)
                {
                    String s = "http://www23.atpages.jp/macbe/musashi/cgi-bin/distance_send.cgi?score=" + String.valueOf(distance);
                    try
                    {
                        URL url = new URL(s);
                        getAppletContext().showDocument(url);
                    }
                    catch(IOException ioexception)
                    {
                        ioexception.printStackTrace();
                    }
                } else
                {
                    gameMode = 4;
                }
            }
        }
        if(gameOverCount < 150)
        {
            drawGround(gv);
            for(int i = 0; i < item.size(); i++)
            {
                Item item1 = (Item)item.elementAt(i);
                item1.draw(gv);
            }

            for(int j = 0; j < bg.size(); j++)
            {
                Bg bg1 = (Bg)bg.elementAt(j);
                bg1.draw(gv);
            }

            for(int k = 0; k < enemy.size(); k++)
            {
                Enemy enemy1 = (Enemy)enemy.elementAt(k);
                musashi.draw(gv);
                enemy1.draw(gv);
            }

            if(!enemyDraw)
                musashi.draw(gv);
            if(musashi.maxHp / 4 > musashi.hp)
                gv.setColor(Color.red);
            else
                gv.setColor(Color.black);
            statWindow(gv);
            msgWindow(gv);
        }
        if(!Key.flag)
            keyWaitCount++;
        if(keyWaitCount > keyWaitTime)
        {
            Key.flag = true;
            keyWaitCount = 0;
        }
    }

    private void gameMain()
    {
        if(musashi.action)
        {
            if(!sendMsg)
                musashi.move();
            if((Key.space || Key.up || Key.down || Key.left || Key.right) && Key.flag)
            {
                Key.flag = false;
                nextMsg();
            }
        } else
        {
            if(enemyIndex < enemy.size())
            {
                Enemy enemy3 = (Enemy)enemy.elementAt(enemyIndex);
                if(enemy3.action)
                {
                    if(!sendMsg)
                        enemy3.move();
                } else
                {
                    enemyIndex++;
                    enemy3.moved = false;
                    enemy3.action = true;
                }
            } else
            if(enemyIndex == enemy.size())
            {
                musashi.action = true;
                musashi.moved = false;
                enemyIndex = 0;
            }
            if((Key.space || Key.up || Key.down || Key.left || Key.right) && Key.flag && msg[0] != null)
            {
                Key.flag = false;
                nextMsg();
            }
        }
        musashi.motion();
        for(int i = 0; i < enemy.size(); i++)
        {
            Enemy enemy1 = (Enemy)enemy.elementAt(i);
            enemy1.motion();
        }

        for(int j = 0; j < bg.size(); j++)
        {
            Bg bg1 = (Bg)bg.elementAt(j);
            bg1.motion();
        }

        for(int k = 0; k < item.size(); k++)
        {
            Item item1 = (Item)item.elementAt(k);
            item1.motion();
        }

        drawGround(gv);
        for(int l = 0; l < item.size(); l++)
        {
            Item item2 = (Item)item.elementAt(l);
            item2.draw(gv);
        }

        for(int i1 = 0; i1 < bg.size(); i1++)
        {
            Bg bg2 = (Bg)bg.elementAt(i1);
            bg2.draw(gv);
        }

        for(int j1 = 0; j1 < enemy.size(); j1++)
        {
            Enemy enemy2 = (Enemy)enemy.elementAt(j1);
            enemy2.draw(gv);
        }

        musashi.draw(gv);
        if(!Key.flag)
            keyWaitCount++;
        if(keyWaitCount > keyWaitTime)
        {
            Key.flag = true;
            keyWaitCount = 0;
        }
        if(musashi.maxHp / 4 > musashi.hp)
            gv.setColor(Color.red);
        else
            gv.setColor(Color.black);
        statWindow(gv);
        msgWindow(gv);
        if(sendMsg)
            msgCursor(gv);
    }

    private void gameMenu()
    {
        statWindowMenu(gv);
        switch(menuMode)
        {
        case 11: // '\013'
            menuMainWindow(gv);
            break;

        case 12: // '\f'
            menuSeedWindow(gv);
            break;

        case 15: // '\017'
            menuSeedWindow(gv);
            break;

        case 13: // '\r'
            menuItemWindow(gv);
            break;

        case 14: // '\016'
            menuItemWindow(gv);
            break;
        }
        if(!Key.flag)
            keyWaitCount++;
        if(keyWaitCount > keyWaitTime)
        {
            Key.flag = true;
            keyWaitCount = 0;
        }
        menuCursor.move();
        menuCursor.motion();
        menuCursor.draw(gv);
    }

    private void gameAllKill()
    {
        gv.setColor(Color.black);
        gv.fillRect(0, 0, width, height);
        allKillCount++;
        switch(allKillCount)
        {
        case 25: // '\031'
            if(playSound)
                heroAtk.play();
            break;

        case 40: // '('
            if(playSound)
                heroAtk.play();
            break;

        case 70: // 'F'
            if(playSound)
                heroAtk.play();
            break;

        case 100: // 'd'
            if(playSound)
                heroAtk.play();
            break;

        case 110: // 'n'
            if(playSound)
                heroAtk.play();
            break;

        case 120: // 'x'
            if(playSound)
                heroAtk.play();
            break;
        }
        if(allKillCount >= 140)
        {
            gameMode = 2;
            allKillCount = 0;
            musashi.levelUp();
        }
    }

    private void gameClear()
    {
        if(gameClear)
        {
            if(gameOverSe)
            {
                Key.flag = false;
                if(playSound)
                    seedUse.play();
                gameOverSe = false;
            }
            gv.setColor(Color.white);
            gv.fillRect(0, 0, width, height);
            gv.setColor(Color.black);
            int i = distance + musashi.seedGo * 100 + musashi.seedB * 50 + musashi.seedGr * 5 + musashi.seedW * 1;
            drawCenter("\u30E0\u30B5\u30B7\u306F " + i + "m\u3067 \u30AF\u30EA\u30A2\u3057\u305F\uFF01", 50);
            drawCenter("\u3042\u308A\u304C\u3068\u3046\u3054\u3056\u3044\u307E\u3057\u305F\uFF01", 200);
            drawCenter("\u30B9\u30DA\u30FC\u30B9\u30AD\u30FC\u3092\u62BC\u3057\u3066\u304F\u3060\u3055\u3044", 234);
            gv.drawImage(imgMusashi, 168, 96, 216, 144, 48, 480, 96, 528, this);
            if(Key.space && Key.flag)
            {
                Key.flag = false;
                if(sendScore)
                {
                    String s = "http://www23.atpages.jp/macbe/musashi/cgi-bin/distance_send.cgi?score=" + String.valueOf(i);
                    try
                    {
                        URL url = new URL(s);
                        getAppletContext().showDocument(url);
                    }
                    catch(IOException ioexception)
                    {
                        ioexception.printStackTrace();
                    }
                } else
                {
                    gameMode = 4;
                }
            }
        } else
        {
            gameOverCount++;
            if(gameOverCount > 150)
            {
                if(gameOverCount < 400)
                {
                    drawCenter("\"\u30E0\u30B5\u30B7\u2026 \u3088\u304F\u3053\u3053\u307E\u3067\u6765\u307E\u3057\u305F\"", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                } else
                if(gameOverCount < 420)
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                else
                if(gameOverCount < 600)
                {
                    drawCenter("\"\u3042\u306A\u305F\u306F \u3082\u3046\u2026\"", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                } else
                if(gameOverCount < 620)
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                else
                if(gameOverCount < 800)
                {
                    drawCenter("\"\u5F31\u866B\u306A\u3093\u304B\u3058\u3083 \u3042\u308A\u307E\u305B\u3093\"", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                } else
                if(gameOverCount < 820)
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                else
                if(gameOverCount < 1000)
                {
                    drawCenter("\"\u3053\u308C\u304B\u3089\u306F\u2026\"", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                } else
                if(gameOverCount < 1020)
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                else
                if(gameOverCount < 1200)
                {
                    drawCenter("\"\u80F8\u3092\u5F35\u3063\u3066 \u304C\u3093\u3070\u308A\u306A\u3055\u3044\"", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                } else
                if(gameOverCount < 1400)
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 432, 48, 480, 96, this);
                else
                if(gameOverCount == 1400)
                {
                    if(playSound)
                        seedUse.play();
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 48, 480, 96, 528, this);
                } else
                if(gameOverCount < 1800)
                {
                    drawCenter("\u304C\u3093\u3070\u308B\u305E\u30FC\uFF01", 50);
                    gv.drawImage(imgMusashi, 168, 96, 216, 144, 48, 480, 96, 528, this);
                } else
                if(gameOverCount >= 1800)
                {
                    gv.setColor(Color.white);
                    gv.fillRect(0, 0, width, height);
                    gv.setColor(Color.black);
                    gv.drawString("\u5230\u9054\u8DDD\u96E2", 40, 40);
                    gv.drawString(distance + "m", 300, 40);
                    drawCenter("\u30AF\u30EA\u30A2\u30DC\u30FC\u30CA\u30B9", 80);
                    gv.drawString("\u91D1\uFF89\u7A2E", 40, 120);
                    gv.drawString("\u9ED2\uFF89\u7A2E", 40, 150);
                    gv.drawString("\u7070\uFF89\u7A2E", 40, 180);
                    gv.drawString("\u767D\uFF89\u7A2E", 40, 210);
                    gv.drawString("" + musashi.seedGo, 110, 120);
                    gv.drawString("" + musashi.seedB, 110, 150);
                    gv.drawString("" + musashi.seedGr, 110, 180);
                    gv.drawString("" + musashi.seedW, 110, 210);
                    gv.drawString("\u500B\u3000\327\u3000100", 160, 120);
                    gv.drawString("\u500B\u3000\327\u300050", 160, 150);
                    gv.drawString("\u500B\u3000\327\u30005", 160, 180);
                    gv.drawString("\u500B\u3000\327\u30001", 160, 210);
                    gv.drawString("\uFF1D", 270, 120);
                    gv.drawString("\uFF1D", 270, 150);
                    gv.drawString("\uFF1D", 270, 180);
                    gv.drawString("\uFF1D", 270, 210);
                    gv.drawString(musashi.seedGo * 100 + "m", 300, 120);
                    gv.drawString(musashi.seedB * 50 + "m", 300, 150);
                    gv.drawString(musashi.seedGr * 5 + "m", 300, 180);
                    gv.drawString(musashi.seedW * 1 + "m", 300, 210);
                    drawCenter("\u25BC", 240);
                    if(Key.space && Key.flag)
                    {
                        Key.flag = false;
                        gameClear = true;
                    }
                }
            } else
            {
                drawGround(gv);
                for(int j = 0; j < item.size(); j++)
                {
                    Item item1 = (Item)item.elementAt(j);
                    item1.draw(gv);
                }

                for(int k = 0; k < bg.size(); k++)
                {
                    Bg bg1 = (Bg)bg.elementAt(k);
                    bg1.draw(gv);
                }

                for(int l = 0; l < enemy.size(); l++)
                {
                    Enemy enemy1 = (Enemy)enemy.elementAt(l);
                    musashi.draw(gv);
                    enemy1.draw(gv);
                }

                if(!enemyDraw)
                    musashi.draw(gv);
                if(musashi.maxHp / 4 > musashi.hp)
                    gv.setColor(Color.red);
                else
                    gv.setColor(Color.black);
                statWindow(gv);
                msgWindow(gv);
            }
        }
        if(!Key.flag)
            keyWaitCount++;
        if(keyWaitCount > keyWaitTime)
        {
            Key.flag = true;
            keyWaitCount = 0;
        }
    }

    int returnDistance()
    {
        return distance;
    }

    void checkDeadAll()
    {
        musashi.checkDead();
        for(int i = 0; i < enemy.size(); i++)
        {
            Enemy enemy1 = (Enemy)enemy.elementAt(i);
            if(enemy1.checkDead())
            {
                enemy.removeElementAt(i);
                i--;
            }
        }

        for(int j = 0; j < bg.size(); j++)
        {
            Bg bg1 = (Bg)bg.elementAt(j);
            if(bg1.checkDead())
            {
                bg.removeElementAt(j);
                j--;
            }
        }

        for(int k = 0; k < item.size(); k++)
        {
            Item item1 = (Item)item.elementAt(k);
            if(item1.checkDead())
            {
                item.removeElementAt(k);
                k--;
            }
        }

    }

    int getw()
    {
        return width;
    }

    int geth()
    {
        return height;
    }

    int getgw()
    {
        return gwidth;
    }

    int getgh()
    {
        return gheight;
    }

    int xPos(int i)
    {
        if(i < 10)
            return 0;
        if(10 <= i && i < 100)
            return -10;
        if(100 <= i && i < 1000)
            return -20;
        if(1000 <= i && i < 10000)
            return -30;
        return 10000 > i || i >= 0x186a0 ? 0 : -40;
    }

    private void drawCenter(String s, int i)
    {
        FontMetrics fontmetrics = getFontMetrics(gv.getFont());
        gv.drawString(s, (width - fontmetrics.stringWidth(s)) / 2, i);
    }

    public void statWindow(Graphics g)
    {
        g.drawRoundRect(292, 2, 88, 192, 8, 8);
        g.drawString("Lv", 298, 22);
        g.drawString("" + musashi.lv, xPos(musashi.lv) + 360, 22);
        g.drawString("Hp", 298, 52);
        g.drawString("" + musashi.hp, xPos(musashi.hp) + 360, 52);
        g.drawString("" + musashi.maxHp, xPos(musashi.maxHp) + 360, 74);
        g.drawString("\u767D", 298, 102);
        g.drawString("" + musashi.seedW, xPos(musashi.seedW) + 360, 102);
        g.drawString("\u7070", 298, 122);
        g.drawString("" + musashi.seedGr, xPos(musashi.seedGr) + 360, 122);
        g.drawString("\u9ED2", 298, 142);
        g.drawString("" + musashi.seedB, xPos(musashi.seedB) + 360, 142);
        g.drawString("\u91D1", 298, 162);
        g.drawString("" + musashi.seedGo, xPos(musashi.seedGo) + 360, 162);
        g.drawString(distance + "m", xPos(distance) + 350, 188);
    }

    public void statWindowMenu(Graphics g)
    {
        g.drawRoundRect(292, 2, 88, 252, 8, 8);
        g.drawString("Lv", 298, 22);
        g.drawString("" + musashi.lv, xPos(musashi.lv) + 360, 22);
        g.drawString("Hp", 298, 52);
        g.drawString("" + musashi.hp, xPos(musashi.hp) + 360, 52);
        g.drawString("" + musashi.maxHp, xPos(musashi.maxHp) + 360, 74);
        g.drawString("Ex", 298, 102);
        g.drawString("" + musashi.ep, xPos(musashi.ep) + 360, 102);
        g.drawString("Ap", 298, 122);
        g.drawString("" + musashi.ap, xPos(musashi.ap) + 360, 122);
        g.drawString("Dp", 298, 142);
        g.drawString("" + musashi.dp, xPos(musashi.dp) + 360, 142);
        g.drawString("\u767D", 298, 166);
        g.drawString("" + musashi.seedW, xPos(musashi.seedW) + 360, 166);
        g.drawString("\u7070", 298, 186);
        g.drawString("" + musashi.seedGr, xPos(musashi.seedGr) + 360, 186);
        g.drawString("\u9ED2", 298, 206);
        g.drawString("" + musashi.seedB, xPos(musashi.seedB) + 360, 206);
        g.drawString("\u91D1", 298, 226);
        g.drawString("" + musashi.seedGo, xPos(musashi.seedGo) + 360, 226);
        g.drawString(distance + "m", xPos(distance) + 350, 248);
    }

    public void menuMainWindow(Graphics g)
    {
        g.drawRoundRect(2, 2, 80, 26, 8, 8);
        g.drawRoundRect(2, 30, 284, 67, 8, 8);
        g.drawRoundRect(2, 120, 44, 28, 8, 8);
        g.drawRoundRect(2, 150, 284, 76, 8, 8);
        g.drawString("\u30E1\u30CB\u30E5\u30FC", 4, 22);
        g.drawString("\u30B7\u30FC\u30C9", 40, 54);
        g.drawString("\u6301\u3061\u7269", 180, 54);
        g.drawString("\u3072\u308D\u3046", 40, 84);
        g.drawString("\u88C5\u5099", 6, 142);
        g.drawString("\u6B66:" + musashi.weapon.name, 6, 178);
        if(musashi.weapon != musashi.hand)
        {
            g.drawString("" + musashi.weapon.ap, 210, 178);
            g.drawString("" + musashi.weapon.hp, 250, 178);
        }
        g.drawString("\u9632:" + musashi.armor.name, 6, 212);
        if(musashi.armor != musashi.wear)
        {
            g.drawString("" + musashi.armor.dp, 210, 212);
            g.drawString("" + musashi.armor.hp, 250, 212);
        }
        g.drawRoundRect(200, 128, 34, 20, 8, 8);
        g.drawRoundRect(246, 128, 34, 20, 8, 8);
        g.setFont(smallFont);
        g.drawString("\u5F37\u3055", 204, 144);
        g.drawString("\u8010\u4E45", 250, 144);
        g.setFont(stdFont);
    }

    public void menuSeedWindow(Graphics g)
    {
        byte byte0 = 36;
        int i = 56;
        char c = '\334';
        g.drawString("\u30B7\u30FC\u30C9", 4, 24);
        g.drawRoundRect(2, 2, 60, 30, 8, 8);
        g.drawRoundRect(2, 34, 284, 184, 8, 8);
        g.drawRoundRect(2, 220, 284, 34, 8, 8);
        int j = menuCursor.seedIndex;
        do
        {
            if(j >= se.seedEffectMax)
                break;
            g.drawString(se.seedName[j][0], byte0, i);
            g.drawString(se.seedName[j][1], c, i);
            if(j == menuCursor.seedIndex + 7)
                break;
            i += 22;
            j++;
        } while(true);
        if(menuMode == 12)
            g.drawString(se.seedName[menuCursor.seedPos][2], 8, 245);
        else
        if(menuMode == 15)
            g.drawString("\u3064\u304B\u3046", 40, 244);
        g.drawRoundRect(216, 12, 34, 20, 8, 8);
        g.setFont(smallFont);
        g.drawString("\u6D88\u8CBB", 219, 28);
        g.setFont(stdFont);
    }

    public int menuItemWindow(Graphics g)
    {
        byte byte0 = 36;
        int i = 56;
        char c = '\322';
        char c1 = '\372';
        g.drawString("\u6301\u3061\u7269", 4, 24);
        g.drawRoundRect(2, 2, 60, 30, 8, 8);
        g.drawRoundRect(2, 34, 284, 184, 8, 8);
        g.drawRoundRect(2, 220, 284, 34, 8, 8);
        if(musashi.bag.size() == 0)
        {
            g.drawString("\u4F55\u3082\u3042\u308A\u307E\u305B\u3093", 20, 58);
            return 0;
        }
        int j = menuCursor.itemIndex;
        do
        {
            if(j >= musashi.bag.size())
                break;
            Item item1 = (Item)musashi.bag.elementAt(j);
            g.drawString(item1.name, byte0, i);
            Hero _tmp = musashi;
            if(item1.kind == 21)
            {
                g.drawString("" + item1.ap, c, i);
            } else
            {
                Hero _tmp1 = musashi;
                if(item1.kind == 22)
                    g.drawString("" + item1.dp, c, i);
            }
            g.drawString("" + item1.hp, c1, i);
            if(item1.isEquip)
            {
                Hero _tmp2 = musashi;
                if(item1.kind == 21)
                {
                    musashi.weaponE_y = i - 18;
                    if(musashi.weapon != musashi.hand)
                        g.drawImage(musashi.img, musashi.weaponE_x, musashi.weaponE_y, musashi.weaponE_x + 16, musashi.weaponE_y + 24, 624, 24, 640, 48, this);
                } else
                {
                    Hero _tmp3 = musashi;
                    if(item1.kind == 22)
                    {
                        musashi.armorE_y = i - 18;
                        if(musashi.armor != musashi.wear)
                            g.drawImage(musashi.img, musashi.armorE_x, musashi.armorE_y, musashi.armorE_x + 16, musashi.armorE_y + 24, 624, 24, 640, 48, this);
                    }
                }
            }
            if(j == menuCursor.itemIndex + 7)
                break;
            i += 22;
            j++;
        } while(true);
        if(menuMode == 13)
        {
            if(musashi.bag.size() > 0)
            {
                Item item2 = (Item)musashi.bag.elementAt(menuCursor.itemPos);
                g.drawString(item2.comment, 8, 244);
            }
        } else
        if(menuMode == 14)
        {
            Item item3 = (Item)musashi.bag.elementAt(menuCursor.itemPos);
            if(item3.isEquip)
                g.drawString("\u88C5\u5099\u306F\u305A\u3059", 40, 244);
            else
                g.drawString("\u88C5\u5099\u3059\u308B", 40, 244);
            g.drawString("\u3059\u3066\u308B", 180, 244);
        }
        g.drawRoundRect(200, 12, 34, 20, 8, 8);
        g.drawRoundRect(246, 12, 34, 20, 8, 8);
        g.setFont(smallFont);
        g.drawString("\u5F37\u3055", 204, 28);
        g.drawString("\u8010\u4E45", 250, 28);
        g.setFont(stdFont);
        return 0;
    }

    public void msgWindow(Graphics g)
    {
        boolean flag = false;
        g.drawRoundRect(2, 196, 378, 54, 8, 8);
        if(msg[0] != null)
            g.drawString(msg[0], 8, 218);
        if(msg[1] != null)
            g.drawString(msg[1], 8, 242);
    }

    public void clearMsg()
    {
        for(int i = 0; i < 8; i++)
            msg[i] = null;

        sendMsg = false;
    }

    void addMsg(String s)
    {
        int i;
        for(i = 0; msg[i] != null; i++);
        sendMsg = true;
        msg[i] = s;
    }

    int nextMsg()
    {
        int i = 0;
        if(msg[0] == null)
            return 0;
        if(msg[2] == null)
        {
            msg[0] = null;
            msg[1] = null;
            sendMsg = false;
            return 0;
        }
        for(msg[i] = null; msg[i + 1] != null; i++)
        {
            msg[i] = msg[i + 1];
            msg[i + 1] = null;
        }

        return 0;
    }

    void msgCursor(Graphics g)
    {
        g.drawString("\u25BC", 354, 242);
    }

    public void setNextChara()
    {
        if(distance != 995 && distance != 1995)
        {
            for(int i = 0; i < 6; i++)
                next[i] = charaTable[getRandom(32)];

            for(int j = 0; j < 6; j++)
                switch(next[j])
                {
                case 1: // '\001'
                    setBg(j);
                    break;

                case 2: // '\002'
                    setEnemy(j);
                    break;

                case 3: // '\003'
                    setItem(j);
                    break;
                }

        } else
        if(distance == 995)
        {
            next[0] = 0;
            next[1] = 0;
            next[2] = 2;
            next[3] = 0;
            next[4] = 0;
            next[5] = 0;
            setEnemy(2);
            addMsg("\u300C\u30E0\u30B5\u30B7\u2026\u52DD\u8CA0\u3060!!\u300D");
        } else
        if(distance == 1995)
        {
            next[0] = 0;
            next[1] = 0;
            next[2] = 2;
            next[3] = 0;
            next[4] = 0;
            next[5] = 0;
            setEnemy(2);
            addMsg("\u300C\u30E0\u30B5\u30B7\u2026");
            addMsg("\u3000\u6C7A\u7740\u306E\u6642\u3060!!\u300D");
        }
    }

    public int setEnemy(int i)
    {
        char c = '\0';
        int j = 0;
        byte byte0 = 8;
        if(enemy.size() >= 4)
            return 0;
        switch(i)
        {
        case 0: // '\0'
            c = '\0';
            break;

        case 1: // '\001'
            c = '0';
            break;

        case 2: // '\002'
            c = '`';
            break;

        case 3: // '\003'
            c = '\220';
            break;

        case 4: // '\004'
            c = '\300';
            break;

        case 5: // '\005'
            c = '\360';
            break;
        }
        if(distance < 50)
            j = enemyTable[0][getRandom(byte0)];
        else
        if(50 <= distance && distance < 100)
            j = enemyTable[1][getRandom(byte0)];
        else
        if(100 <= distance && distance < 150)
            j = enemyTable[2][getRandom(byte0)];
        else
        if(150 <= distance && distance < 200)
            j = enemyTable[3][getRandom(byte0)];
        else
        if(200 <= distance && distance < 300)
            j = enemyTable[4][getRandom(byte0)];
        else
        if(300 <= distance && distance < 400)
            j = enemyTable[5][getRandom(byte0)];
        else
        if(400 <= distance && distance < 500)
            j = enemyTable[6][getRandom(byte0)];
        else
        if(500 <= distance && distance < 550)
            j = enemyTable[7][getRandom(byte0)];
        else
        if(550 <= distance && distance < 600)
            j = enemyTable[8][getRandom(byte0)];
        else
        if(600 <= distance && distance < 700)
            j = enemyTable[9][getRandom(byte0)];
        else
        if(700 <= distance && distance < 850)
            j = enemyTable[10][getRandom(byte0)];
        else
        if(850 <= distance && distance < 900)
            j = enemyTable[11][getRandom(byte0)];
        else
        if(900 <= distance && distance < 990)
            j = enemyTable[12][getRandom(byte0)];
        else
        if(995 <= distance && distance < 996)
            j = enemyTable[14][getRandom(byte0)];
        else
        if(990 <= distance && distance < 1000)
            j = enemyTable[13][getRandom(byte0)];
        else
        if(1000 <= distance && distance < 1100)
            j = enemyTable[15][getRandom(byte0)];
        else
        if(1100 <= distance && distance < 1200)
            j = enemyTable[16][getRandom(byte0)];
        else
        if(1200 <= distance && distance < 1300)
            j = enemyTable[17][getRandom(byte0)];
        else
        if(1300 <= distance && distance < 1400)
            j = enemyTable[18][getRandom(byte0)];
        else
        if(1400 <= distance && distance < 1500)
            j = enemyTable[19][getRandom(byte0)];
        else
        if(1500 <= distance && distance < 1600)
            j = enemyTable[20][getRandom(byte0)];
        else
        if(1600 <= distance && distance < 1700)
            j = enemyTable[21][getRandom(byte0)];
        else
        if(1700 <= distance && distance < 1800)
            j = enemyTable[22][getRandom(byte0)];
        else
        if(1800 <= distance && distance < 1900)
            j = enemyTable[23][getRandom(byte0)];
        else
        if(1900 <= distance && distance < 1990)
            j = enemyTable[24][getRandom(byte0)];
        else
        if(1995 <= distance && distance < 1996)
            j = enemyTable[25][getRandom(byte0)];
        else
        if(1990 <= distance)
            j = enemyTable[13][getRandom(byte0)];
        else
            j = enemyTable[24][getRandom(byte0)];
        switch(j)
        {
        case 0: // '\0'
            return 0;

        case 1: // '\001'
            enemy.addElement(new Furaly(c));
            break;

        case 2: // '\002'
            enemy.addElement(new Ubon(c));
            break;

        case 3: // '\003'
            enemy.addElement(new Kageo(c));
            break;

        case 4: // '\004'
            enemy.addElement(new Gocchi(c));
            break;

        case 5: // '\005'
            enemy.addElement(new Bean(c));
            break;

        case 6: // '\006'
            enemy.addElement(new DKnight(c));
            break;

        case 7: // '\007'
            enemy.addElement(new Majibo(c));
            break;

        case 8: // '\b'
            enemy.addElement(new Dragon(c));
            break;

        case 9: // '\t'
            enemy.addElement(new Shinobi(c));
            break;

        case 10: // '\n'
            enemy.addElement(new SEater(c));
            break;

        case 11: // '\013'
            enemy.addElement(new Shogun(c));
            break;

        case 12: // '\f'
            enemy.addElement(new Deathboll(c));
            break;

        case 13: // '\r'
            enemy.addElement(new Doronta(c));
            break;

        case 14: // '\016'
            enemy.addElement(new Heart(c));
            break;

        case 15: // '\017'
            enemy.addElement(new Mtai(c));
            break;

        case 16: // '\020'
            enemy.addElement(new Kojiro(c));
            break;

        case 17: // '\021'
            enemy.addElement(new Soroly(c));
            break;

        case 18: // '\022'
            enemy.addElement(new Ubobon(c));
            break;

        case 19: // '\023'
            enemy.addElement(new Kageo2(c));
            break;

        case 20: // '\024'
            enemy.addElement(new Ngocchi(c));
            break;

        case 21: // '\025'
            enemy.addElement(new BeanKai(c));
            break;

        case 22: // '\026'
            enemy.addElement(new WKnight(c));
            break;

        case 23: // '\027'
            enemy.addElement(new Mejibo(c));
            break;

        case 24: // '\030'
            enemy.addElement(new DragonKing(c));
            break;

        case 25: // '\031'
            enemy.addElement(new MtShinobi(c));
            break;

        case 26: // '\032'
            enemy.addElement(new Seeder(c));
            break;

        case 27: // '\033'
            enemy.addElement(new Daimyo(c));
            break;

        case 28: // '\034'
            enemy.addElement(new Hellboll(c));
            break;

        case 29: // '\035'
            enemy.addElement(new Doronbo(c));
            break;

        case 30: // '\036'
            enemy.addElement(new Heart2(c));
            break;

        case 31: // '\037'
            enemy.addElement(new Mtai2(c));
            break;

        case 32: // ' '
            enemy.addElement(new Kojiro2(c));
            break;
        }
        return 0;
    }

    public int setBg(int i)
    {
        char c = '\0';
        int j = 0;
        byte byte0 = 8;
        if(bg.size() >= 10)
            return 0;
        switch(i)
        {
        case 0: // '\0'
            c = '\0';
            break;

        case 1: // '\001'
            c = '0';
            break;

        case 2: // '\002'
            c = '`';
            break;

        case 3: // '\003'
            c = '\220';
            break;

        case 4: // '\004'
            c = '\300';
            break;

        case 5: // '\005'
            c = '\360';
            break;
        }
        if(distance < 50)
            j = bgTable[0][getRandom(byte0)];
        else
        if(50 <= distance && distance < 100)
            j = bgTable[1][getRandom(byte0)];
        else
        if(100 <= distance && distance < 200)
            j = bgTable[2][getRandom(byte0)];
        else
        if(200 <= distance && distance < 300)
            j = bgTable[3][getRandom(byte0)];
        else
        if(300 <= distance && distance < 400)
            j = bgTable[4][getRandom(byte0)];
        else
        if(400 <= distance && distance < 500)
            j = bgTable[5][getRandom(byte0)];
        else
        if(500 <= distance && distance < 550)
            j = bgTable[6][getRandom(byte0)];
        else
        if(550 <= distance && distance < 600)
            j = bgTable[7][getRandom(byte0)];
        else
        if(600 <= distance && distance < 700)
            j = bgTable[8][getRandom(byte0)];
        else
        if(700 <= distance && distance < 850)
            j = bgTable[9][getRandom(byte0)];
        else
        if(850 <= distance && distance < 900)
            j = bgTable[10][getRandom(byte0)];
        else
        if(900 <= distance && distance < 990)
            j = bgTable[11][getRandom(byte0)];
        else
        if(990 <= distance && distance < 1000)
            j = bgTable[12][getRandom(byte0)];
        else
        if(1000 <= distance && distance < 1100)
            j = bgTable[13][getRandom(byte0)];
        else
        if(1100 <= distance && distance < 1200)
            j = bgTable[14][getRandom(byte0)];
        else
        if(1200 <= distance && distance < 1300)
            j = bgTable[15][getRandom(byte0)];
        else
        if(1300 <= distance && distance < 1400)
            j = bgTable[16][getRandom(byte0)];
        else
        if(1400 <= distance && distance < 1500)
            j = bgTable[17][getRandom(byte0)];
        else
        if(1500 <= distance && distance < 1600)
            j = bgTable[18][getRandom(byte0)];
        else
        if(1600 <= distance && distance < 1700)
            j = bgTable[19][getRandom(byte0)];
        else
        if(1700 <= distance && distance < 1800)
            j = bgTable[20][getRandom(byte0)];
        else
        if(1800 <= distance && distance < 1900)
            j = bgTable[21][getRandom(byte0)];
        else
        if(1900 <= distance && distance < 1990)
            j = bgTable[22][getRandom(byte0)];
        else
        if(distance == 1995)
            j = bgTable[22][getRandom(byte0)];
        else
        if(1990 <= distance)
            j = bgTable[23][getRandom(byte0)];
        switch(j)
        {
        case 0: // '\0'
            return 0;

        case 1: // '\001'
            bg.addElement(new Wood(c, 0));
            break;

        case 2: // '\002'
            bg.addElement(new Rock(c));
            break;

        case 3: // '\003'
            bg.addElement(new Grave(c));
            break;

        case 4: // '\004'
            bg.addElement(new Marsh(c));
            break;

        case 5: // '\005'
            bg.addElement(new Fire(c));
            break;

        case 6: // '\006'
            bg.addElement(new Statue(c));
            break;

        case 7: // '\007'
            bg.addElement(new Moai(c));
            break;
        }
        return 0;
    }

    public int setItem(int i)
    {
        char c = '\0';
        boolean flag = false;
        int l = 0;
        if(item.size() >= 3)
            return 0;
        switch(i)
        {
        case 0: // '\0'
            c = '\0';
            break;

        case 1: // '\001'
            c = '0';
            break;

        case 2: // '\002'
            c = '`';
            break;

        case 3: // '\003'
            c = '\220';
            break;

        case 4: // '\004'
            c = '\300';
            break;

        case 5: // '\005'
            c = '\360';
            break;
        }
        l = equipSeedTable[getRandom(16)];
        if(l == 1)
        {
            int j;
            if(distance <= 500)
                j = seedTable1[getRandom(64)];
            else
            if(distance <= 1990)
                j = seedTable2[getRandom(64)];
            else
                j = 0;
            switch(j)
            {
            case 0: // '\0'
                return 0;

            case 1: // '\001'
                item.addElement(new WhiteSeed(c));
                break;

            case 2: // '\002'
                item.addElement(new GraySeed(c));
                break;

            case 3: // '\003'
                item.addElement(new BlackSeed(c));
                break;

            case 4: // '\004'
                item.addElement(new GoldSeed(c));
                break;
            }
        } else
        if(l == 2)
        {
            int k;
            if(distance <= 500)
                k = equipTable1[getRandom(64)];
            else
            if(distance <= 1990)
                k = equipTable2[getRandom(128)];
            else
                k = 0;
            switch(k)
            {
            case 0: // '\0'
                return 0;

            case 1: // '\001'
                item.addElement(new WoodClub(c));
                break;

            case 2: // '\002'
                item.addElement(new IronPipe(c));
                break;

            case 3: // '\003'
                item.addElement(new PunchGlove(c));
                break;

            case 4: // '\004'
                item.addElement(new Nunchaku(c));
                break;

            case 5: // '\005'
                item.addElement(new JSword(c));
                break;

            case 6: // '\006'
                item.addElement(new IyashiSword(c));
                break;

            case 7: // '\007'
                item.addElement(new Kiriichimonji(c));
                break;

            case 8: // '\b'
                item.addElement(new Kaladbolg(c));
                break;

            case 9: // '\t'
                item.addElement(new MusashiSword(c));
                break;

            case 10: // '\n'
                item.addElement(new YomiSword(c));
                break;

            case 11: // '\013'
                item.addElement(new LeatherShirt(c));
                break;

            case 12: // '\f'
                item.addElement(new Vest(c));
                break;

            case 13: // '\r'
                item.addElement(new NonameArmor(c));
                break;

            case 14: // '\016'
                item.addElement(new RingMail(c));
                break;

            case 15: // '\017'
                item.addElement(new Hauberk(c));
                break;

            case 16: // '\020'
                item.addElement(new NoShieldArmor(c));
                break;

            case 17: // '\021'
                item.addElement(new NitenArmor(c));
                break;

            case 18: // '\022'
                item.addElement(new Tamamono(c));
                break;
            }
        }
        return 0;
    }

    public void scroll()
    {
        for(int i = 0; i < enemy.size(); i++)
        {
            Enemy enemy1 = (Enemy)enemy.elementAt(i);
            enemy1.y += 48;
        }

        for(int j = 0; j < bg.size(); j++)
        {
            Bg bg1 = (Bg)bg.elementAt(j);
            bg1.y += 48;
        }

        for(int k = 0; k < item.size(); k++)
        {
            Item item1 = (Item)item.elementAt(k);
            item1.y += 48;
        }

        checkDeadAll();
        addDistance();
        if(distance == 2000)
        {
            if(playSound)
                green.stop();
            gameMode = 7;
        } else
        {
            setNextChara();
        }
    }

    public void addDistance()
    {
        distance++;
    }

    public int getRandom(int i)
    {
        int j = (int)Math.round(Math.random() * (double)i);
        if(j == i)
            j = 0;
        return j;
    }

    public int getHerox()
    {
        return musashi.x;
    }

    public int getHeroy()
    {
        return musashi.y;
    }

    public void debugWindow(Graphics g)
    {
        if(menuCursor != null)
        {
            g.drawString("menuCursor.x = " + menuCursor.x, 400, 20);
            g.drawString("musashi.action = " + musashi.action, 400, 50);
            g.drawString("sendMsg = " + sendMsg, 400, 80);
        }
    }

    public void drawGround(Graphics g)
    {
        if(distance >= 0 && distance < 500)
        {
            for(int i = 0; i < 192; i += 48)
            {
                for(int j13 = 0; j13 < 288; j13 += 48)
                    g.drawImage(imgMusashi, j13, i, j13 + 48, i + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 500)
        {
            for(int j = 0; j < 48; j += 48)
            {
                for(int k13 = 0; k13 < 288; k13 += 48)
                    g.drawImage(imgMusashi, k13, j, k13 + 48, j + 48, 576, 0, 624, 48, this);

            }

            for(int k = 48; k < 192; k += 48)
            {
                for(int l13 = 0; l13 < 288; l13 += 48)
                    g.drawImage(imgMusashi, l13, k, l13 + 48, k + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 501)
        {
            for(int l = 0; l < 96; l += 48)
            {
                for(int i14 = 0; i14 < 288; i14 += 48)
                    g.drawImage(imgMusashi, i14, l, i14 + 48, l + 48, 576, 0, 624, 48, this);

            }

            for(int i1 = 96; i1 < 192; i1 += 48)
            {
                for(int j14 = 0; j14 < 288; j14 += 48)
                    g.drawImage(imgMusashi, j14, i1, j14 + 48, i1 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 502)
        {
            for(int j1 = 0; j1 < 144; j1 += 48)
            {
                for(int k14 = 0; k14 < 288; k14 += 48)
                    g.drawImage(imgMusashi, k14, j1, k14 + 48, j1 + 48, 576, 0, 624, 48, this);

            }

            for(int k1 = 144; k1 < 192; k1 += 48)
            {
                for(int l14 = 0; l14 < 288; l14 += 48)
                    g.drawImage(imgMusashi, l14, k1, l14 + 48, k1 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance >= 503 && distance <= 550)
        {
            for(int l1 = 0; l1 < 192; l1 += 48)
            {
                for(int i15 = 0; i15 < 288; i15 += 48)
                    g.drawImage(imgMusashi, i15, l1, i15 + 48, l1 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 551)
        {
            for(int i2 = 0; i2 < 48; i2 += 48)
            {
                for(int j15 = 0; j15 < 288; j15 += 48)
                    g.drawImage(imgMusashi, j15, i2, j15 + 48, i2 + 48, 672, 0, 720, 48, this);

            }

            for(int j2 = 48; j2 < 192; j2 += 48)
            {
                for(int k15 = 0; k15 < 288; k15 += 48)
                    g.drawImage(imgMusashi, k15, j2, k15 + 48, j2 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 552)
        {
            for(int k2 = 0; k2 < 96; k2 += 48)
            {
                for(int l15 = 0; l15 < 288; l15 += 48)
                    g.drawImage(imgMusashi, l15, k2, l15 + 48, k2 + 48, 672, 0, 720, 48, this);

            }

            for(int l2 = 96; l2 < 192; l2 += 48)
            {
                for(int i16 = 0; i16 < 288; i16 += 48)
                    g.drawImage(imgMusashi, i16, l2, i16 + 48, l2 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 553)
        {
            for(int i3 = 0; i3 < 144; i3 += 48)
            {
                for(int j16 = 0; j16 < 288; j16 += 48)
                    g.drawImage(imgMusashi, j16, i3, j16 + 48, i3 + 48, 672, 0, 720, 48, this);

            }

            for(int j3 = 144; j3 < 192; j3 += 48)
            {
                for(int k16 = 0; k16 < 288; k16 += 48)
                    g.drawImage(imgMusashi, k16, j3, k16 + 48, j3 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance >= 554 && distance < 900)
        {
            for(int k3 = 0; k3 < 192; k3 += 48)
            {
                for(int l16 = 0; l16 < 288; l16 += 48)
                    g.drawImage(imgMusashi, l16, k3, l16 + 48, k3 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 900)
        {
            for(int l3 = 0; l3 < 48; l3 += 48)
            {
                for(int i17 = 0; i17 < 288; i17 += 48)
                    g.drawImage(imgMusashi, i17, l3, i17 + 48, l3 + 48, 528, 0, 576, 48, this);

            }

            for(int i4 = 48; i4 < 192; i4 += 48)
            {
                for(int j17 = 0; j17 < 288; j17 += 48)
                    g.drawImage(imgMusashi, j17, i4, j17 + 48, i4 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 901)
        {
            for(int j4 = 0; j4 < 96; j4 += 48)
            {
                for(int k17 = 0; k17 < 288; k17 += 48)
                    g.drawImage(imgMusashi, k17, j4, k17 + 48, j4 + 48, 528, 0, 576, 48, this);

            }

            for(int k4 = 96; k4 < 192; k4 += 48)
            {
                for(int l17 = 0; l17 < 288; l17 += 48)
                    g.drawImage(imgMusashi, l17, k4, l17 + 48, k4 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 902)
        {
            for(int l4 = 0; l4 < 144; l4 += 48)
            {
                for(int i18 = 0; i18 < 288; i18 += 48)
                    g.drawImage(imgMusashi, i18, l4, i18 + 48, l4 + 48, 528, 0, 576, 48, this);

            }

            for(int i5 = 144; i5 < 192; i5 += 48)
            {
                for(int j18 = 0; j18 < 288; j18 += 48)
                    g.drawImage(imgMusashi, j18, i5, j18 + 48, i5 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance >= 903 && distance <= 1000)
        {
            for(int j5 = 0; j5 < 192; j5 += 48)
            {
                for(int k18 = 0; k18 < 288; k18 += 48)
                    g.drawImage(imgMusashi, k18, j5, k18 + 48, j5 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 1001)
        {
            for(int k5 = 0; k5 < 48; k5 += 48)
            {
                for(int l18 = 0; l18 < 288; l18 += 48)
                    g.drawImage(imgMusashi, l18, k5, l18 + 48, k5 + 48, 672, 0, 720, 48, this);

            }

            for(int l5 = 48; l5 < 192; l5 += 48)
            {
                for(int i19 = 0; i19 < 288; i19 += 48)
                    g.drawImage(imgMusashi, i19, l5, i19 + 48, l5 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 1002)
        {
            for(int i6 = 0; i6 < 96; i6 += 48)
            {
                for(int j19 = 0; j19 < 288; j19 += 48)
                    g.drawImage(imgMusashi, j19, i6, j19 + 48, i6 + 48, 672, 0, 720, 48, this);

            }

            for(int j6 = 96; j6 < 192; j6 += 48)
            {
                for(int k19 = 0; k19 < 288; k19 += 48)
                    g.drawImage(imgMusashi, k19, j6, k19 + 48, j6 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 1003)
        {
            for(int k6 = 0; k6 < 144; k6 += 48)
            {
                for(int l19 = 0; l19 < 288; l19 += 48)
                    g.drawImage(imgMusashi, l19, k6, l19 + 48, k6 + 48, 672, 0, 720, 48, this);

            }

            for(int l6 = 144; l6 < 192; l6 += 48)
            {
                for(int i20 = 0; i20 < 288; i20 += 48)
                    g.drawImage(imgMusashi, i20, l6, i20 + 48, l6 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance >= 1004 && distance < 1700)
        {
            for(int i7 = 0; i7 < 192; i7 += 48)
            {
                for(int j20 = 0; j20 < 288; j20 += 48)
                    g.drawImage(imgMusashi, j20, i7, j20 + 48, i7 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1700)
        {
            for(int j7 = 0; j7 < 48; j7 += 48)
            {
                for(int k20 = 0; k20 < 288; k20 += 48)
                    g.drawImage(imgMusashi, k20, j7, k20 + 48, j7 + 48, 576, 0, 624, 48, this);

            }

            for(int k7 = 48; k7 < 192; k7 += 48)
            {
                for(int l20 = 0; l20 < 288; l20 += 48)
                    g.drawImage(imgMusashi, l20, k7, l20 + 48, k7 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1701)
        {
            for(int l7 = 0; l7 < 96; l7 += 48)
            {
                for(int i21 = 0; i21 < 288; i21 += 48)
                    g.drawImage(imgMusashi, i21, l7, i21 + 48, l7 + 48, 576, 0, 624, 48, this);

            }

            for(int i8 = 96; i8 < 192; i8 += 48)
            {
                for(int j21 = 0; j21 < 288; j21 += 48)
                    g.drawImage(imgMusashi, j21, i8, j21 + 48, i8 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1702)
        {
            for(int j8 = 0; j8 < 144; j8 += 48)
            {
                for(int k21 = 0; k21 < 288; k21 += 48)
                    g.drawImage(imgMusashi, k21, j8, k21 + 48, j8 + 48, 576, 0, 624, 48, this);

            }

            for(int k8 = 144; k8 < 192; k8 += 48)
            {
                for(int l21 = 0; l21 < 288; l21 += 48)
                    g.drawImage(imgMusashi, l21, k8, l21 + 48, k8 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance >= 1703 && distance <= 1800)
        {
            for(int l8 = 0; l8 < 192; l8 += 48)
            {
                for(int i22 = 0; i22 < 288; i22 += 48)
                    g.drawImage(imgMusashi, i22, l8, i22 + 48, l8 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 1801)
        {
            for(int i9 = 0; i9 < 48; i9 += 48)
            {
                for(int j22 = 0; j22 < 288; j22 += 48)
                    g.drawImage(imgMusashi, j22, i9, j22 + 48, i9 + 48, 672, 0, 720, 48, this);

            }

            for(int j9 = 48; j9 < 192; j9 += 48)
            {
                for(int k22 = 0; k22 < 288; k22 += 48)
                    g.drawImage(imgMusashi, k22, j9, k22 + 48, j9 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 1802)
        {
            for(int k9 = 0; k9 < 96; k9 += 48)
            {
                for(int l22 = 0; l22 < 288; l22 += 48)
                    g.drawImage(imgMusashi, l22, k9, l22 + 48, k9 + 48, 672, 0, 720, 48, this);

            }

            for(int l9 = 96; l9 < 192; l9 += 48)
            {
                for(int i23 = 0; i23 < 288; i23 += 48)
                    g.drawImage(imgMusashi, i23, l9, i23 + 48, l9 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance == 1803)
        {
            for(int i10 = 0; i10 < 144; i10 += 48)
            {
                for(int j23 = 0; j23 < 288; j23 += 48)
                    g.drawImage(imgMusashi, j23, i10, j23 + 48, i10 + 48, 672, 0, 720, 48, this);

            }

            for(int j10 = 144; j10 < 192; j10 += 48)
            {
                for(int k23 = 0; k23 < 288; k23 += 48)
                    g.drawImage(imgMusashi, k23, j10, k23 + 48, j10 + 48, 576, 0, 624, 48, this);

            }

        } else
        if(distance >= 1804 && distance < 1900)
        {
            for(int k10 = 0; k10 < 192; k10 += 48)
            {
                for(int l23 = 0; l23 < 288; l23 += 48)
                    g.drawImage(imgMusashi, l23, k10, l23 + 48, k10 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1900)
        {
            for(int l10 = 0; l10 < 48; l10 += 48)
            {
                for(int i24 = 0; i24 < 288; i24 += 48)
                    g.drawImage(imgMusashi, i24, l10, i24 + 48, l10 + 48, 528, 0, 576, 48, this);

            }

            for(int i11 = 48; i11 < 192; i11 += 48)
            {
                for(int j24 = 0; j24 < 288; j24 += 48)
                    g.drawImage(imgMusashi, j24, i11, j24 + 48, i11 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1901)
        {
            for(int j11 = 0; j11 < 96; j11 += 48)
            {
                for(int k24 = 0; k24 < 288; k24 += 48)
                    g.drawImage(imgMusashi, k24, j11, k24 + 48, j11 + 48, 528, 0, 576, 48, this);

            }

            for(int k11 = 96; k11 < 192; k11 += 48)
            {
                for(int l24 = 0; l24 < 288; l24 += 48)
                    g.drawImage(imgMusashi, l24, k11, l24 + 48, k11 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance == 1902)
        {
            for(int l11 = 0; l11 < 144; l11 += 48)
            {
                for(int i25 = 0; i25 < 288; i25 += 48)
                    g.drawImage(imgMusashi, i25, l11, i25 + 48, l11 + 48, 528, 0, 576, 48, this);

            }

            for(int i12 = 144; i12 < 192; i12 += 48)
            {
                for(int j25 = 0; j25 < 288; j25 += 48)
                    g.drawImage(imgMusashi, j25, i12, j25 + 48, i12 + 48, 672, 0, 720, 48, this);

            }

        } else
        if(distance >= 1903 && distance < 1998)
        {
            for(int j12 = 0; j12 < 192; j12 += 48)
            {
                for(int k25 = 0; k25 < 288; k25 += 48)
                    g.drawImage(imgMusashi, k25, j12, k25 + 48, j12 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 1998)
        {
            for(int k12 = 48; k12 < 192; k12 += 48)
            {
                for(int l25 = 0; l25 < 288; l25 += 48)
                    g.drawImage(imgMusashi, l25, k12, l25 + 48, k12 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 1999)
        {
            for(int l12 = 96; l12 < 192; l12 += 48)
            {
                for(int i26 = 0; i26 < 288; i26 += 48)
                    g.drawImage(imgMusashi, i26, l12, i26 + 48, l12 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance == 2000)
        {
            for(int i13 = 144; i13 < 192; i13 += 48)
            {
                for(int j26 = 0; j26 < 288; j26 += 48)
                    g.drawImage(imgMusashi, j26, i13, j26 + 48, i13 + 48, 528, 0, 576, 48, this);

            }

        } else
        if(distance < 2001);
    }

    public void paint(Graphics g)
    {
        g.drawImage(offImg, 0, 0, this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public static final int GAME_TOP = 1;
    public static final int GAME_MAIN = 2;
    public static final int GAME_MENU = 3;
    public static final int GAME_INIT = 4;
    public static final int GAME_OVER = 5;
    public static final int GAME_ALLKILL = 6;
    public static final int GAME_CLEAR = 7;
    public static final int CHARA = 48;
    public static final int ENEMY_MAX = 4;
    public static final int ITEM_MAX = 3;
    public static final int BG_MAX = 10;
    public static final int GROW_MAX = 24;
    public static final int MSG1_X = 8;
    public static final int MSG1_Y = 218;
    public static final int MSG2_X = 8;
    public static final int MSG2_Y = 242;
    public static final int MENU_MAIN = 11;
    public static final int MENU_SEED = 12;
    public static final int MENU_ITEM = 13;
    public static final int MENU_ITEM_USE = 14;
    public static final int MENU_SEED_USE = 15;
    protected int gameMode;
    protected int menuMode;
    private Thread gameThread;
    private Font stdFont;
    private Font smallFont;
    private Image offImg;
    protected Graphics gv;
    private int width;
    private int height;
    int gwidth;
    int gheight;
    private DecimalFormat threeFig;
    private DecimalFormat fiveFig;
    Image imgMusashi;
    Image imgMusashi2;
    private MediaTracker mt;
    protected Hero musashi;
    private int next[];
    protected int distance;
    private boolean debug;
    private double ver;
    protected Vector enemy;
    protected Vector item;
    protected Vector bg;
    private int enemyIndex;
    protected String msg[];
    protected String msg2;
    protected String oldMsg1;
    protected String oldMsg2;
    protected int msgCount;
    protected int msgTime;
    protected int keyWaitCount;
    protected int keyWaitTime;
    protected MenuCursor menuCursor;
    private SeedEffect se;
    protected boolean sendMsg;
    protected int soundHeroAtk;
    protected AudioClip heroAtk;
    protected AudioClip heroAtk2;
    protected AudioClip enemyAtk;
    protected AudioClip lvUp;
    protected AudioClip seedUse;
    protected AudioClip get;
    protected AudioClip heal;
    protected AudioClip miss;
    protected AudioClip itemUse;
    protected AudioClip green;
    protected AudioClip crash;
    protected AudioClip wood;
    protected AudioClip fall;
    protected AudioClip katana;
    protected boolean playSound;
    private int gameOverCount;
    private boolean gameOverSe;
    private boolean gameTopSe;
    private boolean enemyDraw;
    protected int allKillCount;
    protected boolean sendScore;
    private boolean gameClear;
    int charaTable[] = {
        0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 2, 1, 0, 0, 0, 1, 1, 1, 
        0, 0, 0, 0, 2, 0, 0, 0, 3, 0, 
        0, 0
    };
    int enemyTable[][] = {
        {
            1, 1, 1, 1, 1, 1, 1, 1
        }, {
            1, 1, 1, 1, 2, 2, 2, 2
        }, {
            1, 1, 2, 2, 4, 4, 4, 4
        }, {
            2, 4, 4, 4, 4, 13, 13, 13
        }, {
            4, 2, 4, 4, 3, 3, 1, 3
        }, {
            1, 1, 4, 4, 5, 5, 5, 5
        }, {
            7, 7, 3, 7, 7, 3, 7, 7
        }, {
            5, 5, 5, 5, 5, 6, 6, 6
        }, {
            4, 4, 7, 7, 11, 11, 11, 11
        }, {
            9, 9, 9, 9, 11, 11, 11, 11
        }, {
            12, 12, 12, 12, 13, 13, 7, 8
        }, {
            8, 8, 13, 13, 10, 10, 10, 10
        }, {
            14, 14, 14, 14, 14, 14, 7, 7
        }, {
            0, 0, 0, 0, 0, 0, 0, 0
        }, {
            16, 16, 16, 16, 16, 16, 16, 16
        }, {
            17, 17, 17, 17, 19, 19, 19, 19
        }, {
            15, 15, 21, 21, 21, 21, 21, 21
        }, {
            28, 28, 28, 28, 29, 29, 29, 29
        }, {
            27, 27, 27, 27, 25, 25, 25, 25
        }, {
            18, 18, 18, 18, 23, 23, 23, 23
        }, {
            26, 26, 26, 26, 26, 26, 26, 26
        }, {
            20, 20, 20, 20, 20, 20, 20, 20
        }, {
            22, 22, 22, 22, 22, 22, 22, 22
        }, {
            24, 24, 23, 23, 23, 23, 23, 23
        }, {
            30, 30, 30, 30, 30, 30, 30, 30
        }, {
            32, 32, 32, 32, 32, 32, 32, 32
        }
    };
    int bgTable[][] = {
        {
            1, 1, 1, 1, 1, 1, 1, 1
        }, {
            1, 1, 1, 1, 3, 3, 3, 3
        }, {
            2, 2, 2, 2, 2, 2, 2, 2
        }, {
            2, 2, 2, 2, 4, 4, 4, 4
        }, {
            3, 3, 3, 3, 3, 3, 3, 3
        }, {
            4, 4, 4, 4, 4, 4, 4, 4
        }, {
            6, 6, 6, 6, 6, 6, 6, 6
        }, {
            4, 4, 4, 4, 4, 4, 4, 4
        }, {
            1, 1, 1, 1, 5, 5, 5, 5
        }, {
            5, 5, 5, 5, 5, 5, 5, 5
        }, {
            6, 6, 6, 6, 6, 6, 6, 6
        }, {
            7, 7, 7, 7, 7, 7, 7, 7
        }, {
            0, 0, 0, 0, 0, 0, 0, 0
        }, {
            1, 1, 3, 3, 3, 3, 3, 3
        }, {
            1, 1, 1, 1, 1, 1, 1, 1
        }, {
            2, 2, 2, 2, 2, 2, 4, 4
        }, {
            4, 4, 4, 4, 4, 4, 4, 4
        }, {
            7, 7, 7, 7, 7, 7, 7, 7
        }, {
            4, 4, 4, 4, 4, 1, 1, 1
        }, {
            4, 4, 1, 1, 1, 1, 1, 1
        }, {
            0, 0, 0, 0, 0, 0, 0, 0
        }, {
            5, 5, 5, 5, 5, 5, 5, 5
        }, {
            6, 6, 6, 6, 6, 6, 6, 6
        }, {
            0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    int equipSeedTable[] = {
        1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 
        1, 1, 1, 1, 2, 1
    };
    int seedTable1[] = {
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 
        1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 
        1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 
        2, 1, 1, 1
    };
    int seedTable2[] = {
        1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 
        1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 
        1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 
        1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 
        1, 3, 1, 1, 1, 1, 1, 1, 4, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1
    };
    int equipTable1[] = {
        1, 12, 2, 3, 11, 4, 1, 11, 1, 11, 
        5, 1, 12, 2, 1, 2, 11, 1, 12, 11, 
        1, 13, 1, 12, 1, 11, 1, 12, 1, 3, 
        11, 1, 11, 1, 11, 1, 5, 1, 11, 1, 
        1, 11, 1, 1, 11, 1, 11, 4, 11, 1, 
        14, 11, 1, 11, 1, 11, 2, 1, 13, 1, 
        14, 1, 11, 1, 11, 2, 11, 11, 1, 4, 
        1, 12, 11, 1, 11, 1, 4, 1, 1, 11, 
        1, 15, 1, 3, 11, 1, 11, 1, 6, 1, 
        11, 3, 11, 1, 11, 1, 11, 1, 11, 1, 
        13, 1, 11, 1, 11, 1, 1, 1, 13, 1, 
        11, 1, 2, 1, 12, 1, 1, 11, 1, 12, 
        11, 1, 11, 2, 1, 2, 1, 11
    };
    int equipTable2[] = {
        4, 12, 1, 2, 4, 6, 1, 3, 3, 1, 
        12, 1, 2, 1, 2, 1, 1, 7, 1, 5, 
        4, 15, 1, 3, 1, 11, 1, 4, 14, 4, 
        1, 1, 2, 1, 3, 1, 1, 12, 2, 12, 
        1, 11, 1, 1, 1, 3, 1, 1, 1, 1, 
        14, 1, 5, 1, 1, 3, 1, 11, 1, 15, 
        1, 1, 6, 1, 2, 6, 1, 2, 3, 1, 
        3, 1, 13, 1, 1, 11, 1, 2, 1, 1, 
        1, 3, 13, 3, 4, 1, 18, 1, 1, 6, 
        1, 1, 1, 5, 1, 1, 1, 8, 1, 1, 
        1, 5, 1, 1, 1, 13, 1, 1, 1, 1, 
        1, 1, 4, 1, 15, 1, 11, 1, 1, 9, 
        1, 1, 1, 16, 1, 7, 1, 1, 1, 7, 
        1, 13, 1, 7, 2, 1, 1, 11, 1, 4, 
        1, 1, 1, 1, 1, 1, 1, 12, 1, 1, 
        2, 12, 1, 1, 14, 1, 2, 1, 1, 1, 
        4, 5, 1, 2, 10, 1, 2, 14, 1, 3, 
        3, 6, 1, 1, 1, 1, 1, 1, 13, 1, 
        1, 5, 2, 4, 1, 13, 1, 1, 1, 5, 
        2, 1, 3, 1, 1, 16, 1, 2, 1, 3, 
        1, 1, 11, 1, 5, 7, 1, 1, 4, 6, 
        1, 1, 3, 1, 7, 3, 5, 1, 2, 11, 
        1, 4, 1, 1, 1, 8, 1, 2, 2, 2, 
        2, 1, 1, 17, 1, 11, 1, 1, 1, 1, 
        1, 12, 1, 5, 1, 1, 12, 1, 1, 11, 
        1, 2, 1, 2, 1, 2
    };
}
