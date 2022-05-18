// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainPanel.java

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel
    implements Runnable, KeyListener
{

    public MainPanel()
    {
        dbImage = null;
        fpSleep = new FPSleep(30);
        df = new DecimalFormat("0.0");
        enemys = new ObjectsArray("Enemy", 50);
        bullets = new ObjectsArray("Bullet", 400);
        shoots = new ObjectsArray("Shoot", 100);
        effects = new ObjectsArray("Effect", 400);
        player = new Player();
        getKeys = new GetKeys();
        running = false;
        do_reset = false;
        resetInterval = 0;
        backLoop = 0;
        menuMode = 0;
        menuZkey = 0;
        setPreferredSize(new Dimension(640, 480));
        setSize(640, 480);
        setFocusable(true);
        addKeyListener(this);
        GameObject.gameObjectInit(this);
        ImageIcon imageicon = new ImageIcon(getClass().getResource("image/objects.png"));
        image = imageicon.getImage();
        imageicon = new ImageIcon(getClass().getResource("image/system.png"));
        sysImage = imageicon.getImage();
        backImage = new Image[3];
        for(int i = 0; i < 3; i++)
        {
            ImageIcon imageicon1 = new ImageIcon(getClass().getResource((new StringBuilder()).append("image/bg_sky_").append(i).append(".png").toString()));
            backImage[i] = imageicon1.getImage();
        }

        myshot = new AudioClip[17];
        enhit = new AudioClip[17];
        enshot = new AudioClip[17];
        encrash = new AudioClip[17];
        reflect = new AudioClip[17];
        myhit = Applet.newAudioClip(getClass().getResource("wav/gun14_c.wav"));
        bosscrash = Applet.newAudioClip(getClass().getResource("wav/bom13_c.wav"));
        bombuse = Applet.newAudioClip(getClass().getResource("wav/power32.wav"));
        for(int j = 0; j < 17; j++)
        {
            myshot[j] = Applet.newAudioClip(getClass().getResource("wav/shoot05.wav"));
            enhit[j] = Applet.newAudioClip(getClass().getResource("wav/pi18.wav"));
            enshot[j] = Applet.newAudioClip(getClass().getResource("wav/puu64.wav"));
            encrash[j] = Applet.newAudioClip(getClass().getResource("wav/bom20_a.wav"));
            reflect[j] = Applet.newAudioClip(getClass().getResource("wav/puu35.wav"));
        }

        bgm = new AudioClip[5];
        for(int k = 0; k < 5; k++)
            bgm[k] = Applet.newAudioClip(getClass().getResource((new StringBuilder()).append("mid/bgm").append(k).append(".mid").toString()));

        wavTarg = 0;
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void keyPressed(KeyEvent keyevent)
    {
        getKeys.keyPressed(keyevent.getKeyCode());
    }

    public void keyReleased(KeyEvent keyevent)
    {
        getKeys.keyReleased(keyevent.getKeyCode());
    }

    public void gameReset()
    {
        do_reset = false;
        resetInterval = 0;
        menuMode = 1;
        stopBgm();
        player.setData(240D, 360D, 0.0D, 0.0D, 4, 0, 0, 5, '\u6731');
        shoots.allErase();
        bullets.allErase();
        enemys.allErase();
        effects.allErase();
    }

    public void stopBgm()
    {
        for(int i = 0; i < 5; i++)
            bgm[i].stop();

    }

    private void gameUpdate()
    {
        if(menuMode == 0)
        {
            shoots.allMove();
            player.move(getKeys);
            bullets.allMove();
            enemys.allMove();
            effects.allMove();
            if(getKeys.esc)
                menuMode = 2;
        }
    }

    private void gameRender()
    {
        if(dbImage == null)
        {
            dbImage = createImage(640, 480);
            if(dbImage == null)
                return;
            dbg = dbImage.getGraphics();
        }
        dbg.fillRect(0, 0, 640, 480);
        backLoop = (backLoop + 8) % 240;
        for(int i = -1; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
                dbg.drawImage(backImage[player.getStage()], j * 320, i * 240 + backLoop, (j + 1) * 320, (i + 1) * 240 + backLoop, 0, 0, 320, 240, null);

        }

        shoots.allDraw(dbg);
        player.draw(dbg);
        bullets.allDraw(dbg);
        enemys.allDraw(dbg);
        effects.allDraw(dbg);
        GameObject gameobject;
        if((gameobject = enemys.getObject(0)).getExist())
        {
            int k = player.getStage();
            int k1 = gameobject.getLife();
            if(gameobject.getSize() == 64 && gameobject.getLife() >= 1000)
            {
                k1 -= 1000;
                if(k == 0)
                    k = 1000;
                else
                if(k == 1)
                    k = 1500;
                else
                if(k == 2)
                    k = 6000;
                dbg.setColor(Color.BLACK);
                dbg.fillRect(39, 39, 402, 10);
                dbg.setColor(Color.RED);
                dbg.fillRect(40, 40, 400, 8);
                dbg.setColor(Color.GREEN);
                dbg.fillRect(40, 40, (400 * k1) / k, 8);
            }
        }
        for(int l = 0; l < 448; l += 32)
        {
            for(int l1 = 0; l1 < 480; l1 += 448)
                dbg.drawImage(sysImage, l, l1, l + 32, l1 + 32, 0, 32, 32, 64, null);

        }

        for(int i1 = 0; i1 < 448; i1 += 32)
            dbg.drawImage(sysImage, 0, i1, 32, i1 + 32, 0, 32, 32, 64, null);

        for(int j1 = 448; j1 < 640; j1 += 32)
        {
            for(int i2 = 0; i2 < 480; i2 += 32)
                dbg.drawImage(sysImage, j1, i2, j1 + 32, i2 + 32, 0, 32, 32, 64, null);

        }

        dbg.setColor(Color.WHITE);
        dbg.drawString((new StringBuilder()).append("FPS: ").append(df.format(fpSleep.getActualFPS())).toString(), 4, 16);
        dbg.drawImage(sysImage, 464, 48, 560, 64, 32, 32, 128, 48, null);
        paintNumber(464, 64, player.getHiscore(), 0x3b9aca00, true);
        dbg.drawImage(sysImage, 464, 112, 560, 128, 32, 48, 128, 64, null);
        paintNumber(464, 128, player.getScore(), 0x3b9aca00, true);
        dbg.drawImage(sysImage, 464, 176, 560, 192, 32, 64, 128, 80, null);
        paintNumber(464, 192, player.getLife(), 0x3b9aca00, false);
        dbg.drawImage(sysImage, 464, 240, 560, 256, 32, 80, 128, 96, null);
        paintNumber(464, 256, player.getPower() / 100, 0xf4240, false);
        paintNumber(576, 256, 10, 1, true);
        paintNumber(592, 256, player.getPower() % 100, 10, true);
        if(menuMode != 0)
        {
            dbg.setColor(Color.BLACK);
            dbg.fillRect(32, 32, 416, 416);
            dbg.setColor(Color.WHITE);
            if(menuMode == 1)
            {
                dbg.drawString("\u30DF\u30B3\u306E\u30AF\u30EA\u30B9\u30DE\u30B9\u3051\u3044\u304B\u304F\uFF15", 64, 64);
                if(player.getColor() == '\u6731')
                {
                    dbg.drawString("\u30D7\u30EC\u30A4\u30E4\u30FC\u30AD\u30E3\u30E9\uFF1A\u30EA\u30AA\u30F3", 64, 128);
                    dbg.drawString("\u6247\u578B\u30B7\u30E7\u30C3\u30C8\u306E\u5E83\u7BC4\u56F2\u653B\u6483\u578B\u3002", 64, 160);
                    dbg.drawString("\u3010\u2192\uFF1A\u30DF\u30B3\u306B\u5207\u308A\u66FF\u3048\u3011", 64, 192);
                    if(getKeys.right)
                        player.setData(240D, 360D, 0.0D, 0.0D, 4, 0, 0, 5, '\u8056');
                } else
                {
                    dbg.drawString("\u30D7\u30EC\u30A4\u30E4\u30FC\u30AD\u30E3\u30E9\uFF1A\u30DF\u30B3", 64, 128);
                    dbg.drawString("\u4E26\u884C\u30B7\u30E7\u30C3\u30C8\u306E\u524D\u65B9\u96C6\u4E2D\u653B\u6483\u578B\u3002", 64, 160);
                    dbg.drawString("\u3010\u2190\uFF1A\u30EA\u30AA\u30F3\u306B\u5207\u308A\u66FF\u3048\u3011", 64, 192);
                    if(getKeys.left)
                        player.setData(240D, 360D, 0.0D, 0.0D, 4, 0, 0, 5, '\u6731');
                }
                dbg.drawString("\uFF3A\u30AD\u30FC\u3067\u30B2\u30FC\u30E0\u30B9\u30BF\u30FC\u30C8", 64, 256);
                if(menuZkey == 1)
                    menuMode = 0;
            } else
            if(menuMode == 2)
            {
                dbg.drawString("\u4E00\u6642\u505C\u6B62", 64, 64);
                dbg.drawString("\uFF3A\u30AD\u30FC\u3067\u30B2\u30FC\u30E0\u518D\u958B", 64, 128);
                dbg.drawString("\uFF26\uFF11\uFF12\u30AD\u30FC\u3067\u30EA\u30BB\u30C3\u30C8", 64, 160);
                if(menuZkey == 1)
                    menuMode = 0;
            } else
            if(menuMode == 3)
            {
                dbg.drawString("\u3053\u3046\u3057\u3066\u306A\u3093\u3084\u304B\u3093\u3084\u306E\u672B\u3001\u7121\u4E8B\u306B", 64, 64);
                dbg.drawString("\u7AEF\u5348\u306E\u7BC0\u53E5\u306E\u8ECD\u52E2\u306F\u6EC5\u307C\u3055\u308C", 64, 96);
                dbg.drawString("\uFF15\u6708\uFF15\u65E5\u306F\u30AF\u30EA\u30B9\u30DE\u30B9\u306B\u306A\u3063\u305F\u3068\u3055\u3002", 64, 128);
                dbg.drawString("\u3081\u3067\u305F\u3057\u3081\u3067\u305F\u3057\u3002", 64, 160);
                dbg.drawString("\u30D7\u30ED\u30B0\u30E9\u30E0\u30FB\u753B\u50CF\u30FB\u30C7\u30B6\u30A4\u30F3\u30FB\u4F01\u753B", 60, 224);
                dbg.drawString("\u3000\u3000\u3000\u30FB\u4ED6\u3044\u308D\u3044\u308D\uFF1A\u79CB\u6708\u306D\u3053\u67F3", 64, 256);
                dbg.drawString("\u958B\u767A\u74B0\u5883\uFF1A\uFF2A\uFF41\uFF56\uFF41", 64, 288);
                dbg.drawString("\u52B9\u679C\u97F3\uFF1A\u30B6\u30FB\u30DE\u30C3\u30C1\u30E1\u30A4\u30AB\u30A1\u30BA\u3000OSA\u69D8", 64, 320);
                dbg.drawString("\uFF22\uFF27\uFF2D\uFF1A\u304B\u308B\u304C\u3082\u884C\u9032\u5C40\u3000\u3068\u3073\u69D8", 64, 352);
                dbg.drawString("\u304A\u3057\u307E\u3044", 64, 416);
            }
        }
        if(getKeys.z)
            menuZkey++;
        else
            menuZkey = 0;
        wavTarg = (wavTarg + 1) % 17;
    }

    public void paintNumber(int i, int j, int k, int l, boolean flag)
    {
        for(; l > 0; l /= 10)
        {
            int i1 = (k / l) * 16;
            k -= (i1 * l) / 16;
            if(flag || i1 > 0 || l == 1)
            {
                dbg.drawImage(sysImage, i, j, i + 16, j + 32, i1, 0, i1 + 16, 32, null);
                flag = true;
            } else
            {
                dbg.drawImage(sysImage, i, j, i + 16, j + 32, 176, 0, 192, 32, null);
            }
            i += 16;
        }

    }

    private void paintScreen()
    {
        try
        {
            Graphics g = getGraphics();
            if(g != null && dbImage != null)
                g.drawImage(dbImage, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            if(g != null)
                g.dispose();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void run()
    {
        running = true;
        do_reset = true;
        do
        {
            if(!running)
                break;
            if(do_reset)
                gameReset();
            gameUpdate();
            gameRender();
            paintScreen();
            fpSleep.sleep();
            if(getKeys.f12)
                do_reset = true;
            if(!player.getExist())
            {
                stopBgm();
                resetInterval++;
                if(resetInterval > 90)
                    do_reset = true;
            } else
            {
                resetInterval = 0;
            }
        } while(true);
        System.exit(0);
    }

    public void setMenuMode(int i)
    {
        menuMode = i;
    }

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private Graphics dbg;
    private Image dbImage;
    private FPSleep fpSleep;
    private DecimalFormat df;
    public static final int FIELD_UP = 32;
    public static final int FIELD_LEFT = 32;
    public static final int FIELD_DOWN = 448;
    public static final int FIELD_RIGHT = 448;
    public static final int WAV_ARRAY = 17;
    public static final int BGM_NUM = 5;
    public ObjectsArray enemys;
    public ObjectsArray bullets;
    public ObjectsArray shoots;
    public ObjectsArray effects;
    public Player player;
    private GetKeys getKeys;
    public Image image;
    public Image sysImage;
    public Image backImage[];
    public AudioClip myshot[];
    public AudioClip enhit[];
    public AudioClip enshot[];
    public AudioClip encrash[];
    public AudioClip reflect[];
    public AudioClip myhit;
    public AudioClip bosscrash;
    public AudioClip bombuse;
    public AudioClip bgm[];
    public int wavTarg;
    private boolean running;
    private boolean do_reset;
    private int resetInterval;
    private int backLoop;
    private int menuMode;
    private int menuZkey;
    private Thread gameLoop;
}
