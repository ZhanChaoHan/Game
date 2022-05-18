// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainPanel.java

package Typing;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Referenced classes of package Typing:
//            Opponent, Player, Ikebukuro, Akiba, 
//            Sibuya, Sinbasi

public class MainPanel extends JPanel
    implements KeyListener, Runnable
{

    public MainPanel()
    {
        offImage = null;
        setPreferredSize(new Dimension(650, 400));
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        gameState = 0;
        arrowImage = loadImage("arrow.gif");
        boardImage = loadImage("board2.jpg");
        selectImage1 = loadImage("L/LSinbasi3.gif");
        selectImage2 = loadImage("L/LSibuya3.gif");
        selectImage3 = loadImage("L/LAkiba3.gif");
        selectImage4 = loadImage("L/LIkebukuro3.gif");
        tokyoImage = loadImage("tokyo.jpg");
        sibuyaImage = loadImage("sibuya.jpg");
        akihabaraImage = loadImage("akihabara.jpg");
        yuurakutyouImage = loadImage("yuurakutyou.jpg");
        ikebukuroImage = loadImage("ikebukuro.jpg");
        syokyuuImage = loadImage("syokyuu.jpg");
        tyuukyuuImage = loadImage("tyuukyuu.jpg");
        zyoukyuuImage = loadImage("zyoukyuu.jpg");
        tyouzyoukyuuImage = loadImage("tyouzyoukyuu.jpg");
        determinFlag = false;
        fightSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/fight.wav"));
        readySound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/ready.wav"));
        youWinSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/youWin.wav"));
        youLoseSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/youLose.wav"));
        typeWriterSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/typeWriter.wav"));
        determinationSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/puu52.wav"));
        selectSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/hit49.wav"));
        gameOverSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/gameOver.wav"));
        dawnSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/DownA@11.wav"));
        beepSound = Applet.newAudioClip(Thread.currentThread().getContextClassLoader().getResource("music/beep.wav"));
        stage = 1;
        select = 0;
        determinMenu = 3;
        selectLevelCount = 0;
        determinLevel = false;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run()
    {
        while(gameThread == Thread.currentThread()) 
        {
            gameRender();
            if(g_off != null)
            {
                long RefreshTime = System.currentTimeMillis();
                try
                {
                    Thread.sleep(20L);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                switch(gameState)
                {
                case 0: // '\0'
                    title();
                    break;

                case 18: // '\022'
                    selectChara();
                    break;

                case 5: // '\005'
                    ready();
                    break;

                case 1: // '\001'
                    stage1();
                    break;

                case 2: // '\002'
                    stage2();
                    break;

                case 3: // '\003'
                    stage3();
                    break;

                case 14: // '\016'
                    readyFight();
                    break;

                case 15: // '\017'
                    youWin();
                    break;

                case 16: // '\020'
                    youLose();
                    break;

                case 9: // '\t'
                    gameOver();
                    break;

                case 17: // '\021'
                    nowLoading();
                    break;

                case 19: // '\023'
                    opponentDown();
                    break;

                case 20: // '\024'
                    playerDown();
                    break;

                case 10: // '\n'
                    congratulation();
                    break;

                case 21: // '\025'
                    selectLevel();
                    break;

                case 22: // '\026'
                    config();
                    break;
                }
                paintScreen();
                while(System.currentTimeMillis() - RefreshTime < 13L) ;
            }
        }
    }

    private void config()
    {
        g_off.setColor(Color.white);
        Font font = new Font(null, 1, 25);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.drawString("\u3010\u64CD\u4F5C\u65B9\u6CD5\u3011", (650 - fontMetrics.stringWidth("\u3010\u64CD\u4F5C\u65B9\u6CD5\u3011")) / 2, 30);
        g_off.drawString("\u3010\u30EC\u30D9\u30EB\u306B\u3064\u3044\u3066\u3011", (650 - fontMetrics.stringWidth("\u3010\u30EC\u30D9\u30EB\u306B\u3064\u3044\u3066\u3011")) / 2, 120);
        g_off.drawString("\u3010\u6CE8\u610F\u3011", (650 - fontMetrics.stringWidth("\u3010\u6CE8\u610F\u3011")) / 2, 300);
        font = new Font(null, 1, 15);
        g_off.setFont(font);
        g_off.drawString("\u9078\u629E\uFF1A\u77E2\u5370\u30AD\u30FC", 50, 60);
        g_off.drawString("\u6C7A\u5B9A\uFF1AENTER\u30AD\u30FC", 50, 80);
        g_off.drawString("\u521D\u7D1A\u2026\u6B63\u78BA\u306B\u30BF\u30A4\u30D7\u3067\u304D\u308B\u30EC\u30D9\u30EB", 50, 150);
        g_off.drawString("\u4E2D\u7D1A\u2026\u666E\u901A\u306E\u4EBA\u3088\u308A\u3061\u3087\u3063\u3074\u308A\u3046\u307E\u3044\u4EBA\u3002\u666E\u6BB5\u304B\u3089\u30D1\u30BD\u30B3\u30F3\u3092\u4F7F\u3063\u3066\u3044\u308B\u4EBA\u306F", 50, 170);
        g_off.drawString("\u3000\u3000\u3000\u30AF\u30EA\u30A2\u3057\u3066\u307B\u3057\u3044\u30EC\u30D9\u30EB\u3067\u3059", 50, 190);
        g_off.drawString("\u4E0A\u7D1A\u2026\u3069\u3053\u3067\u3082\u901A\u7528\u3059\u308B\u30EC\u30D9\u30EB\u3002\u30D7\u30ED\u30B0\u30E9\u30DE\u30FC\u306A\u3089\u30AF\u30EA\u30A2\u3057\u3066\u307B\u3057\u3044\u3067\u3059", 50, 210);
        g_off.drawString("\u8D85\u4E0A\u7D1A\u2026\u30BF\u30A4\u30D7\u306E\u9054\u4EBA\u3002\u5468\u308A\u306B\u81EA\u6162\u3057\u3061\u3083\u3044\u307E\u3057\u3087\u3046", 50, 230);
        g_off.drawString("\u30BF\u30A4\u30D7\u3067\u304D\u308B\u6587\u5B57\u306F\u753B\u9762\u306B\u8868\u793A\u3055\u308C\u305F\u30ED\u30FC\u30DE\u5B57\u306E\u307F\u3067\u3059", 50, 330);
        g_off.drawString("\u300C\u4F8B:\u6210\u5C31 jouju\u300D\u3068\u3044\u3063\u305F\u5834\u5408\u306B\u306F\u3001[jouju\u300D\u3068\u30BF\u30A4\u30D7\u3057\u305F\u3068\u304D\u3060\u3051", 50, 350);
        g_off.drawString("\u6B63\u89E3\u306B\u306A\u308A\u307E\u3059\u3002\u300Czyouzyu\u300D,\u300Czyouju\u300D,\u300Cjouzyu\u300D\u306F\u30DF\u30B9\u3068\u306A\u308A\u307E\u3059\u306E\u3067", 50, 370);
        g_off.drawString("\u3054\u6CE8\u610F\u304F\u3060\u3055\u3044", 50, 390);
        if(enter)
            gameState = 17;
    }

    private void selectLevel()
    {
        g_off.setColor(Color.white);
        Font font = new Font(null, 1, 30);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.drawString("\u30EC\u30D9\u30EB\u3092\u9078\u3093\u3067\u304F\u3060\u3055\u3044", (650 - fontMetrics.stringWidth("\u30EC\u30D9\u30EB\u3092\u9078\u3093\u3067\u304F\u3060\u3055\u3044")) / 2, 30);
        g_off.drawImage(syokyuuImage, 50, 50, null);
        g_off.drawImage(tyuukyuuImage, 200, 50, null);
        g_off.drawImage(zyoukyuuImage, 350, 50, null);
        g_off.drawImage(tyouzyoukyuuImage, 500, 50, null);
        g_off.setColor(Color.RED);
        if(enter && !determinLevel)
        {
            determinLevel = true;
            selectLevel = select;
            determinationSound.play();
        }
        if(determinLevel)
        {
            if(selectLevelCount < 50)
            {
                if(selectLevelCount % 8 < 4)
                {
                    g_off.drawRect(49 + 150 * select, 49, 101, 301);
                    g_off.drawRect(48 + 150 * select, 48, 103, 303);
                    g_off.drawRect(47 + 150 * select, 47, 105, 305);
                    g_off.drawRect(46 + 150 * select, 46, 107, 307);
                }
            } else
            {
                select = 0;
                gameState = 18;
            }
            selectLevelCount++;
        } else
        {
            g_off.drawRect(49 + 150 * select, 49, 101, 301);
            g_off.drawRect(48 + 150 * select, 48, 103, 303);
            g_off.drawRect(47 + 150 * select, 47, 105, 305);
            g_off.drawRect(46 + 150 * select, 46, 107, 307);
        }
    }

    private void congratulation()
    {
        if(enter)
        {
            typeWriterSound.play();
            gameState = 17;
        } else
        {
            Font font = new Font("Bookman Old Style", 1, 60);
            g_off.setFont(font);
            FontMetrics fontMetrics = getFontMetrics(font);
            g_off.setColor(Color.white);
            g_off.drawString("CONGRATULATION!", (650 - fontMetrics.stringWidth("CONGRATULATION!")) / 2, 100);
            font = new Font("HG\u884C\u66F8\u4F53", 1, 30);
            g_off.setFont(font);
            fontMetrics = getFontMetrics(font);
            g_off.setColor(Color.white);
            if(selectLevel == 0)
            {
                g_off.drawString("\u3042\u306A\u305F\u306F\u3010\u521D\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F\uFF01", (650 - fontMetrics.stringWidth("\u3042\u306A\u305F\u306F\u3010\u521D\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F")) / 2, 200);
                g_off.drawString("\u4E0A\u306E\u7D1A\u306B\u3082\u3069\u3093\u3069\u3093\u6311\u6226\u3057\u307E\u3057\u3087\u3046(^^)", (650 - fontMetrics.stringWidth("\u4E0A\u306E\u7D1A\u306B\u3082\u3069\u3093\u3069\u3093\u6311\u6226\u3057\u307E\u3057\u3087\u3046(^^)")) / 2, 250);
            } else
            if(selectLevel == 1)
            {
                g_off.drawString("\u3042\u306A\u305F\u306F\u3010\u4E2D\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F\uFF01", (650 - fontMetrics.stringWidth("\u3042\u306A\u305F\u306F\u3010\u4E2D\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F")) / 2, 200);
                g_off.drawString("\u4E0A\u306E\u7D1A\u306B\u3082\u3069\u3093\u3069\u3093\u6311\u6226\u3057\u307E\u3057\u3087\u3046(^^)", (650 - fontMetrics.stringWidth("\u4E0A\u306E\u7D1A\u306B\u3082\u3069\u3093\u3069\u3093\u6311\u6226\u3057\u307E\u3057\u3087\u3046(^^)")) / 2, 250);
            } else
            if(selectLevel == 2)
            {
                g_off.drawString("\u3042\u306A\u305F\u306F\u3010\u4E0A\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F\uFF01", (650 - fontMetrics.stringWidth("\u3042\u306A\u305F\u306F\u3010\u4E0A\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F")) / 2, 200);
                g_off.drawString("\u305C\u3072\u3053\u306E\u529B\u3092\u4ED5\u4E8B\u306B\u751F\u304B\u3057\u3066\u304F\u3060\u3055\u3044(^^)v", (650 - fontMetrics.stringWidth("\u305C\u3072\u3053\u306E\u529B\u3092\u4ED5\u4E8B\u306B\u751F\u304B\u3057\u3066\u304F\u3060\u3055\u3044(^^)v")) / 2, 250);
            } else
            if(selectLevel == 3)
            {
                g_off.drawString("\u3042\u306A\u305F\u306F\u3010\u8D85\u4E0A\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F\uFF01", (650 - fontMetrics.stringWidth("\u3042\u306A\u305F\u306F\u3010\u8D85\u4E0A\u7D1A\u3011\u306B\u5408\u683C\u3057\u307E\u3057\u305F")) / 2, 200);
                g_off.drawString("\u3059\u3001\u3059\u3054\u3044\u3067\u3059\uFF01\u03A3(\264\u25BD\uFF40\uFF89)\uFF89", (650 - fontMetrics.stringWidth("\u3059\u3001\u3059\u3054\u3044\u3067\u3059\uFF01\u03A3(\264\u25BD\uFF40\uFF89)\uFF89")) / 2, 250);
                g_off.drawString("\u3059\u3054\u904E\u304E\u3066\u5468\u308A\u306E\u4EBA\u304C\u3072\u304F\u304B\u3082\u7B11", (650 - fontMetrics.stringWidth("\u3059\u3054\u904E\u304E\u3066\u5468\u308A\u306E\u4EBA\u304C\u3072\u304F\u304B\u3082\u7B11")) / 2, 300);
            }
        }
    }

    private void stage3()
    {
        gameMain();
    }

    private void stage2()
    {
        gameMain();
    }

    private void opponentDown()
    {
        drawCharacter();
        if(opponentDownCount < 5)
            opponent.setImageNumber(5);
        else
        if(opponentDownCount < 10)
            opponent.setImageNumber(6);
        else
        if(opponentDownCount < 15)
            opponent.setImageNumber(7);
        else
        if(opponentDownCount < 25)
        {
            if(opponentDownCount == 15)
                dawnSound.play();
            opponent.setImageNumber(8);
        } else
        {
            gameState = 15;
        }
        opponentDownCount++;
    }

    private void playerDown()
    {
        player.setX(150);
        drawCharacter();
        if(playerDownCount < 5)
            player.setImageNumber(5);
        else
        if(playerDownCount < 10)
            player.setImageNumber(6);
        else
        if(playerDownCount < 15)
            player.setImageNumber(7);
        else
        if(playerDownCount < 25)
        {
            if(playerDownCount == 15)
                dawnSound.play();
            player.setImageNumber(8);
        } else
        {
            gameState = 16;
        }
        playerDownCount++;
    }

    private void selectChara()
    {
        Font font = new Font(null, 1, 80);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.white);
        switch(select)
        {
        case 0: // '\0'
            g_off.drawImage(yuurakutyouImage, 0, 0, 650, 400, null);
            g_off.drawString("\u6709\u697D\u753A", (650 - fontMetrics.stringWidth("\u6709\u697D\u753A")) / 2, 360);
            break;

        case 1: // '\001'
            g_off.drawImage(sibuyaImage, 0, 0, 650, 400, null);
            g_off.drawString("\u6E0B\u8C37", (650 - fontMetrics.stringWidth("\u6E0B\u8C37")) / 2, 360);
            break;

        case 2: // '\002'
            g_off.drawImage(akihabaraImage, 0, 0, 650, 400, null);
            g_off.drawString("\u79CB\u8449\u539F", (650 - fontMetrics.stringWidth("\u79CB\u8449\u539F")) / 2, 360);
            break;

        case 3: // '\003'
            g_off.drawImage(ikebukuroImage, 0, 0, 650, 400, null);
            g_off.drawString("\u6C60\u888B", (650 - fontMetrics.stringWidth("\u6C60\u888B")) / 2, 360);
            break;
        }
        g_off.setColor(Color.black);
        font = new Font(null, 1, 30);
        g_off.setFont(font);
        fontMetrics = getFontMetrics(font);
        g_off.drawString("\u30AD\u30E3\u30E9\u3092\u9078\u3093\u3067\u304F\u3060\u3055\u3044", (650 - fontMetrics.stringWidth("\u30AD\u30E3\u30E9\u3092\u9078\u3093\u3067\u304F\u3060\u3055\u3044")) / 2, 30);
        g_off.drawImage(selectImage1, 50, 50, null);
        g_off.drawImage(selectImage2, 200, 50, null);
        g_off.drawImage(selectImage3, 350, 50, null);
        g_off.drawImage(selectImage4, 500, 50, null);
        g_off.setColor(Color.RED);
        if(enter && !determinFlag)
        {
            determinFlag = true;
            determinationSound.play();
        }
        if(determinFlag)
        {
            if(selectAnimeCount < 50)
            {
                if(selectAnimeCount % 8 < 4)
                {
                    g_off.drawRect(49 + 150 * select, 49, 101, 211);
                    g_off.drawRect(48 + 150 * select, 48, 103, 213);
                    g_off.drawRect(47 + 150 * select, 47, 105, 215);
                    g_off.drawRect(46 + 150 * select, 46, 107, 217);
                }
            } else
            {
                gameState = 5;
            }
            selectAnimeCount++;
        } else
        {
            g_off.drawRect(49 + 150 * select, 49, 101, 211);
            g_off.drawRect(48 + 150 * select, 48, 103, 213);
            g_off.drawRect(47 + 150 * select, 47, 105, 215);
            g_off.drawRect(46 + 150 * select, 46, 107, 217);
        }
    }

    private void nowLoading()
    {
        Font font = new Font("Bookman Old Style", 1, 45);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.WHITE);
        if(nowLoadingAnimeCount % 32 < 8)
            g_off.drawString("Now Loading", 170, (400 + fontMetrics.getHeight()) / 2 - 50);
        else
        if(nowLoadingAnimeCount % 32 < 16)
            g_off.drawString("Now Loading.", 170, (400 + fontMetrics.getHeight()) / 2 - 50);
        else
        if(nowLoadingAnimeCount % 32 < 24)
            g_off.drawString("Now Loading..", 170, (400 + fontMetrics.getHeight()) / 2 - 50);
        else
        if(nowLoadingAnimeCount % 32 < 32)
            g_off.drawString("Now Loading...", 170, (400 + fontMetrics.getHeight()) / 2 - 50);
        if(nowLoadingAnimeCount > 64)
        {
            select = 0;
            stage = 1;
            selectLevel = 0;
            determinFlag = false;
            determinMenu = 3;
            determinLevel = false;
            titleAnimeCount = 0;
            selectAnimeCount = 0;
            nowLoadingAnimeCount = 0;
            gameState = 0;
        }
        nowLoadingAnimeCount++;
    }

    private void gameOver()
    {
        if(gameOverAnimeCount == 0)
            gameOverSound.play();
        Font font = new Font("Bookman Old Style", 1, 90);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.WHITE);
        g_off.drawString("GAME OVER", (650 - fontMetrics.stringWidth("GAME OVER")) / 2, (400 + fontMetrics.getHeight()) / 2 - 50);
        if(enter)
        {
            typeWriterSound.play();
            gameState = 17;
        }
        gameOverAnimeCount++;
    }

    private void youLose()
    {
        if(youLoseAnimeCount == 0)
            youLoseSound.play();
        drawCharacter();
        Font font = new Font("Bookman Old Style", 1, 120);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.RED);
        g_off.drawString("YOU LOSE", (650 - fontMetrics.stringWidth("YOU LOSE")) / 2, (400 + fontMetrics.getHeight()) / 2 - 100);
        youLoseAnimeCount++;
        if(youLoseAnimeCount > 80)
        {
            typeWriterSound.play();
            gameState = 9;
        }
    }

    private void youWin()
    {
        if(youWinAnimeCount == 0)
            youWinSound.play();
        drawCharacter();
        Font font = new Font("Bookman Old Style", 1, 120);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.RED);
        g_off.drawString("YOU WIN", (650 - fontMetrics.stringWidth("YOU WIN")) / 2, (400 + fontMetrics.getHeight()) / 2 - 100);
        youWinAnimeCount++;
        if(youWinAnimeCount > 50)
        {
            if(stage < 3)
            {
                if(enter)
                {
                    stage++;
                    typeWriterSound.play();
                    gameState = 5;
                }
            } else
            {
                typeWriterSound.play();
                gameState = 10;
            }
            font = new Font(null, 1, 40);
            g_off.setFont(font);
            fontMetrics = getFontMetrics(font);
            g_off.setColor(Color.white);
            g_off.drawString("NEXT STAGE\u3078", (650 - fontMetrics.stringWidth("NEXT STAGE\u3078")) / 2, (400 + fontMetrics.getHeight()) / 2 + 110);
        }
    }

    private void readyFight()
    {
        drawCharacter();
        Font font = new Font("Bookman Old Style", 1, 200);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.RED);
        if(readyFightAnimeCount < 70)
        {
            if(readyFightAnimeCount == 0)
                readySound.play();
            g_off.drawString("R E A D Y", 650 - readyFightAnimeCount * 30, (400 + fontMetrics.getHeight()) / 2 - 100);
            if(readyFightAnimeCount == 60)
                fightSound.play();
        } else
        if(readyFightAnimeCount < 110)
            g_off.drawString("FIGHT", (650 - fontMetrics.stringWidth("FIGHT")) / 2, (400 + fontMetrics.getHeight()) / 2 - 100);
        else
            gameState = stage;
        player.setPower((int)(((double)playerPower / 20D) * (double)powerAnimeCount));
        opponent.setPower((int)(((double)opponentPower / 20D) * (double)powerAnimeCount));
        if(powerAnimeCount < 20)
            powerAnimeCount++;
        readyFightAnimeCount++;
    }

    private void drawBoard()
    {
        g_off.drawImage(boardImage, 0, 250, 650, 150, null);
    }

    private void beAttacked()
    {
        if(beAttackedAnimeFlag)
        {
            opponent.attackSound();
            player.damage();
            opponent.setImageNumber(attackCount % 2);
            player.setImageNumber(4);
        } else
        if(!attackAnimeFlag)
            if(attackCount % 16 < 9)
                opponent.setImageNumber(2);
            else
                opponent.setImageNumber(3);
    }

    private void attack()
    {
        if(attackAnimeFlag)
        {
            player.attackSound();
            opponent.damage();
            player.setImageNumber(attackCount % 2);
            opponent.setImageNumber(4);
        } else
        if(!beAttackedAnimeFlag)
            if(attackCount % 16 < 9)
                player.setImageNumber(2);
            else
                player.setImageNumber(3);
    }

    private void gameMain()
    {
        if(!reading)
        {
            random = new Random();
            index = random.nextInt(target.length);
            questionStr = target[index][1];
            countForAttack = 0;
            reading = true;
        }
        if(countForAttack > time)
        {
            beAttackedAnimeFlag = true;
            countForAttack = 0;
        }
        attack();
        beAttacked();
        drawCharacter();
        drawBoard();
        writeQuestion();
        if(player.getPower() <= 0)
            player.kill();
        else
        if(opponent.getPower() <= 0)
            opponent.kill();
        if(player.isDead())
            gameState = 20;
        if(opponent.isDead())
            gameState = 19;
        attackAnimeFlag = false;
        beAttackedAnimeFlag = false;
        attackCount++;
        countForAttack++;
    }

    private void drawCharacter()
    {
        drawBoard();
        g_off.drawImage(backImage, 0, 0, 650, 250, null);
        player.draw(g_off);
        opponent.draw(g_off);
    }

    private void writeQuestion()
    {
        Font font = new Font("moji", 1, 20);
        g_off.setFont(font);
        FontMetrics fontMetrics = getFontMetrics(font);
        g_off.setColor(Color.white);
        g_off.drawString(target[index][0], (650 - fontMetrics.stringWidth(target[index][0])) / 2, (250 + (150 + fontMetrics.getHeight()) / 2) - fontMetrics.getHeight() - 10);
        font = new Font("moji", 1, 25);
        g_off.setFont(font);
        fontMetrics = getFontMetrics(font);
        g_off.drawString(questionStr, (650 - fontMetrics.stringWidth(questionStr)) / 2, 250 + (150 + fontMetrics.getHeight()) / 2);
        g_off.drawString(inStr, (650 - fontMetrics.stringWidth(questionStr)) / 2, 250 + (150 + fontMetrics.getHeight()) / 2 + fontMetrics.getHeight());
    }

    private void stage1()
    {
        gameMain();
    }

    private void ready()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("question/question" + stage + ".txt")));
            target = new String[100][2];
            String str;
            for(int i = 0; (str = reader.readLine()) != null; i++)
            {
                StringTokenizer st = new StringTokenizer(str, " ");
                for(int j = 0; st.hasMoreTokens(); j++)
                    target[i][j] = st.nextToken();

            }

            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        switch(select)
        {
        case 0: // '\0'
            player = new Player(250, selectLevel, "L/LSinbasi");
            break;

        case 1: // '\001'
            player = new Player(250, selectLevel, "L/LSibuya");
            break;

        case 2: // '\002'
            player = new Player(250, selectLevel, "L/LAkiba");
            break;

        case 3: // '\003'
            player = new Player(250, selectLevel, "L/LIkebukuro");
            break;
        }
        if(select == 0)
            switch(stage)
            {
            case 1: // '\001'
                opponent = new Ikebukuro(310, 50, "R/RIkebukuro");
                break;

            case 2: // '\002'
                opponent = new Akiba(310, 100, "R/RAkiba");
                break;

            case 3: // '\003'
                opponent = new Sibuya(310, 150, "R/RSibuya");
                break;
            }
        else
        if(select == 1)
            switch(stage)
            {
            case 1: // '\001'
                opponent = new Ikebukuro(310, 50, "R/RIkebukuro");
                break;

            case 2: // '\002'
                opponent = new Akiba(310, 100, "R/RAkiba");
                break;

            case 3: // '\003'
                opponent = new Sinbasi(310, 150, "R/RSinbasi");
                break;
            }
        else
        if(select == 2)
            switch(stage)
            {
            case 1: // '\001'
                opponent = new Ikebukuro(310, 50, "R/RIkebukuro");
                break;

            case 2: // '\002'
                opponent = new Sibuya(310, 100, "R/RSibuya");
                break;

            case 3: // '\003'
                opponent = new Sinbasi(310, 150, "R/RSinbasi");
                break;
            }
        else
        if(select == 3)
            switch(stage)
            {
            case 1: // '\001'
                opponent = new Sibuya(310, 50, "R/RSibuya");
                break;

            case 2: // '\002'
                opponent = new Akiba(310, 100, "R/RAkiba");
                break;

            case 3: // '\003'
                opponent = new Sinbasi(310, 150, "R/RSinbasi");
                break;
            }
        if(opponent instanceof Akiba)
            backImage = akihabaraImage;
        else
        if(opponent instanceof Sibuya)
            backImage = sibuyaImage;
        else
        if(opponent instanceof Ikebukuro)
            backImage = ikebukuroImage;
        else
        if(opponent instanceof Sinbasi)
            backImage = yuurakutyouImage;
        switch(selectLevel)
        {
        case 0: // '\0'
            time = 80;
            break;

        case 1: // '\001'
            time = 40;
            break;

        case 2: // '\002'
            time = 22;
            break;

        case 3: // '\003'
            time = 18;
            break;
        }
        reading = false;
        inStr = "";
        titleAnimeCount = 0;
        determinMenu = 3;
        readyFightAnimeCount = 0;
        powerAnimeCount = 0;
        youWinAnimeCount = 0;
        youLoseAnimeCount = 0;
        gameOverAnimeCount = 0;
        nowLoadingAnimeCount = 0;
        attackCount = 0;
        opponentDownCount = 0;
        playerDownCount = 0;
        selectAnimeCount = 0;
        selectLevelCount = 0;
        countForAttack = 0;
        determinFlag = false;
        determinLevel = false;
        playerPower = player.getPower();
        opponentPower = opponent.getPower();
        attackAnimeFlag = false;
        beAttackedAnimeFlag = false;
        gameState = 14;
    }

    private void title()
    {
        if(enter && select == 0 && determinMenu != select)
        {
            determinationSound.play();
            determinMenu = select;
        } else
        if(enter && select == 2 && determinMenu != select)
            System.exit(0);
        else
        if(enter && select == 1 && determinMenu != select)
        {
            determinationSound.play();
            determinMenu = select;
        } else
        {
            g_off.drawImage(tokyoImage, 0, 0, null);
            Font font = new Font("Bookman Old Style", 1, 90);
            g_off.setFont(font);
            FontMetrics fontMetrics = getFontMetrics(font);
            g_off.setColor(Color.white);
            g_off.drawString("Typing", (650 - fontMetrics.stringWidth("Typing")) / 2, (400 + fontMetrics.getHeight()) / 2 - 180);
            font = new Font("Bookman Old Style", 1, 60);
            g_off.setFont(font);
            fontMetrics = getFontMetrics(font);
            g_off.drawString("@", (650 - fontMetrics.stringWidth("@")) / 2, (400 + fontMetrics.getHeight()) / 2 - 100);
            g_off.drawString("Tokyo", (650 - fontMetrics.stringWidth("Tokyo")) / 2, (400 + fontMetrics.getHeight()) / 2 - 40);
            font = new Font("Bookman Old Style", 1, 20);
            fontMetrics = getFontMetrics(font);
            g_off.setFont(font);
            g_off.setColor(Color.black);
            if(determinMenu == 0)
            {
                if(titleAnimeCount > 20)
                    gameState = 21;
                if(titleAnimeCount % 5 == 0)
                    g_off.drawString("GAME START", (650 - fontMetrics.stringWidth("GAME START")) / 2, (400 + fontMetrics.getHeight()) / 2 + 60);
                titleAnimeCount++;
            } else
            {
                g_off.drawString("GAME START", (650 - fontMetrics.stringWidth("GAME START")) / 2, (400 + fontMetrics.getHeight()) / 2 + 60);
            }
            if(determinMenu == 1)
            {
                if(titleAnimeCount > 20)
                    gameState = 22;
                if(titleAnimeCount % 5 == 0)
                    g_off.drawString("MANUAL", (650 - fontMetrics.stringWidth("MANUAL")) / 2, (400 + fontMetrics.getHeight()) / 2 + 100);
                titleAnimeCount++;
            } else
            {
                g_off.drawString("MANUAL", (650 - fontMetrics.stringWidth("MANUAL")) / 2, (400 + fontMetrics.getHeight()) / 2 + 100);
            }
            g_off.drawString("END", (650 - fontMetrics.stringWidth("END")) / 2, (400 + fontMetrics.getHeight()) / 2 + 140);
            g_off.drawImage(arrowImage, (650 - fontMetrics.stringWidth("GAME START")) / 2 - 30, (400 + fontMetrics.getHeight()) / 2 + 45 + 40 * select, null);
        }
    }

    private void gameRender()
    {
        if(offImage == null)
        {
            offImage = createImage(650, 400);
            if(offImage == null)
                return;
            g_off = offImage.getGraphics();
        }
        g_off.setColor(Color.BLACK);
        g_off.fillRect(0, 0, 650, 400);
    }

    public void paintScreen()
    {
        try
        {
            Graphics g = getGraphics();
            if(g != null && offImage != null)
                g.drawImage(offImage, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            if(g != null)
                g.dispose();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void keyTyped(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(gameState == 1 || gameState == 2 || gameState == 3)
        {
            char key = e.getKeyChar();
            if(key == questionStr.charAt(inStr.length()))
            {
                typeWriterSound.play();
                inStr += key;
                attackAnimeFlag = true;
                countForAttack = 0;
                if(inStr.equals(questionStr))
                    initialyzeText();
            } else
            {
                countForAttack = 0;
                beepSound.play();
                beAttackedAnimeFlag = true;
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
        case 10: // '\n'
            enter = false;
            break;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == 10)
            enter = true;
        if(gameState == 0)
        {
            if(determinMenu == 3)
            {
                if(keyCode == 40 && select < 2)
                {
                    selectSound.play();
                    select++;
                }
                if(keyCode == 38 && select > 0)
                {
                    selectSound.play();
                    select--;
                }
            }
        } else
        if(gameState == 21)
        {
            if(!determinLevel)
            {
                if(keyCode == 39 && select < 3)
                {
                    selectSound.play();
                    select++;
                }
                if(keyCode == 37 && select > 0)
                {
                    selectSound.play();
                    select--;
                }
            }
        } else
        if(gameState == 18 && !determinFlag)
        {
            if(keyCode == 39 && select < 3)
            {
                selectSound.play();
                select++;
            }
            if(keyCode == 37 && select > 0)
            {
                selectSound.play();
                select--;
            }
        }
    }

    private void initialyzeText()
    {
        inStr = "";
        reading = false;
    }

    private Image loadImage(String filename)
    {
        ImageIcon icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("image/" + filename));
        return icon.getImage();
    }

    public static final int WIDTH = 650;
    public static final int HEIGHT = 400;
    public static final int NORTH_HEIGHT = 250;
    public static final int SOUTH_HEIGHT = 150;
    public static boolean enter;
    private Image offImage;
    private Graphics g_off;
    public static int gameState;
    public static final int START_SCREEN = 0;
    public static final int STAGE1 = 1;
    private final int STAGE2 = 2;
    private final int STAGE3 = 3;
    private final int READY = 5;
    private final int GAME_OVER = 9;
    private final int CONGRATULATION = 10;
    private final int READY_FIGHT = 14;
    private final int YOU_WIN = 15;
    private final int YOU_LOSE = 16;
    private final int NOW_LOADING = 17;
    private final int SELECT_CHARA = 18;
    private final int OPPONENT_DOWN = 19;
    private final int PLAYER_DOWN = 20;
    private final int SELECT_LEVEL = 21;
    private final int CONFIG_SCREEN = 22;
    private int stage;
    Thread gameThread;
    private String target[][];
    private boolean reading;
    private String questionStr;
    private String inStr;
    private int index;
    Random random;
    Player player;
    Opponent opponent;
    private int playerPower;
    private int opponentPower;
    private int select;
    private static final int GAME_START = 0;
    private static final int CONFIG = 1;
    private static final int END = 2;
    private Image arrowImage;
    private Image backImage;
    private Image boardImage;
    private Image selectImage1;
    private Image selectImage2;
    private Image selectImage3;
    private Image selectImage4;
    private Image tokyoImage;
    private Image sibuyaImage;
    private Image yuurakutyouImage;
    private Image akihabaraImage;
    private Image ikebukuroImage;
    private Image syokyuuImage;
    private Image tyuukyuuImage;
    private Image zyoukyuuImage;
    private Image tyouzyoukyuuImage;
    public AudioClip fightSound;
    public AudioClip readySound;
    public AudioClip youWinSound;
    public AudioClip youLoseSound;
    public AudioClip typeWriterSound;
    public AudioClip determinationSound;
    public AudioClip selectSound;
    public AudioClip gameOverSound;
    public AudioClip dawnSound;
    public AudioClip beepSound;
    private int titleAnimeCount;
    private int readyFightAnimeCount;
    private int youWinAnimeCount;
    private int youLoseAnimeCount;
    private int powerAnimeCount;
    private int gameOverAnimeCount;
    private int nowLoadingAnimeCount;
    private int attackCount;
    private int opponentDownCount;
    private int playerDownCount;
    private int selectAnimeCount;
    private int selectLevelCount;
    private int countForAttack;
    private boolean attackAnimeFlag;
    private boolean beAttackedAnimeFlag;
    private int determinMenu;
    private boolean determinFlag;
    private boolean determinLevel;
    private int selectLevel;
    private int time;
}
