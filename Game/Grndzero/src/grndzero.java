// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   grndzero.java

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.net.URL;
import java.util.*;

public class grndzero extends Applet
    implements Runnable, KeyListener, MouseListener, MouseMotionListener, ComponentListener, ActionListener, FocusListener
{
    private class Camera
        implements Viewer
    {

        public void setFocus(Viewer focus)
        {
            following = focus;
            caught = false;
        }

        public void calculate()
        {
            if(!caught)
            {
                if(dx < 0.0D)
                    dx += mapWidth;
                if(dx > (double)mapWidth)
                    dx -= mapWidth;
                if(!following.isCamera())
                {
                    destX = following.getX() + gameWidth / 2;
                    destY = following.getY() + gameHeight / 2;
                    if(Math.abs((dx + (double)mapWidth) - (double)destX) < Math.abs(dx - (double)destX))
                        destX -= mapWidth;
                    if(Math.abs(dx - (double)mapWidth - (double)destX) < Math.abs(dx - (double)destX))
                        destX += mapWidth;
                    double tx = 0.0D;
                    double ty = 0.0D;
                    difX = (int)Math.abs(dx - (double)destX);
                    difY = (int)Math.abs(dy - (double)destY);
                    if(difX > difY)
                    {
                        tx = tSpd;
                        ty = tSpd * ((double)difY / (double)difX);
                    }
                    if(difX < difY)
                    {
                        tx = tSpd * ((double)difX / (double)difY);
                        ty = tSpd;
                    }
                    if(difX == difY)
                    {
                        tx = tSpd;
                        ty = tSpd;
                    }
                    if((double)difX < Math.abs(tx))
                    {
                        dx = destX;
                    } else
                    {
                        if(dx < (double)destX)
                            dx += tx;
                        if(dx > (double)destX)
                            dx -= tx;
                    }
                    if((double)difY < Math.abs(ty))
                    {
                        dy = destY;
                    } else
                    {
                        if(dy < (double)destY)
                            dy += ty;
                        if(dy > (double)destY)
                            dy -= ty;
                    }
                    if((double)difX <= Math.abs(tx) && (double)difY <= Math.abs(ty))
                        caught = true;
                }
            } else
            if(camera.following != null)
            {
                dx = following.getX() + gameWidth / 2;
                dy = following.getY() + gameHeight / 2;
            }
            x = (int)dx;
            y = (int)dy;
        }

        public int getX()
        {
            if(following.isCamera())
            {
                dx = x;
                return x - gameWidth / 2;
            }
            if(caught)
                return following.getX();
            else
                return x - gameWidth / 2;
        }

        public int getY()
        {
            if(following.isCamera())
            {
                dy = y;
                return y - gameHeight / 2;
            }
            if(caught)
                return following.getY();
            else
                return y - gameHeight / 2;
        }

        public boolean isCamera()
        {
            return true;
        }

        public int origX;
        public int origY;
        public int x;
        public int y;
        int destX;
        int destY;
        int difX;
        int difY;
        int multX;
        int multY;
        double dx;
        double dy;
        double tSpd;
        Viewer following;
        boolean caught;
 
        private Camera()
        {

            tSpd = 5D;
            caught = true;
        }

        Camera(Camera camera1)
        {
            this();
        }
    }

    class DebugWindow extends Frame
    {
        class DebugWindowListener extends WindowAdapter
        {

            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }

            DebugWindowListener()
            {
         
            }
        }


        private TextArea txtbox;
        private DebugWindowListener debugWindowListener;
        final grndzero this$0;


        public DebugWindow(FocusListener listener)
        {
            this$0 = grndzero.this;
           
            setSize(500, 300);
            txtbox = new TextArea("", 20, 60);
            add(txtbox);
            addWindowListener(debugWindowListener);
        }
    }

    private class Key
    {

        public void kick()
        {
            _wasPressed = false;
        }

        public void halt()
        {
            _wasPressed = false;
            _pressed = false;
        }

        public boolean wasPressed()
        {
            return _wasPressed;
        }

        public void press(long when)
        {
            _whenLast = when;
            _pressed = true;
        }

        public void release(long when)
        {
            if(_whenLast < when)
            {
                _whenLast = when;
                _pressed = false;
            }
        }

        public boolean isPressed()
        {
            if(_pressed)
                _wasPressed = true;
            return _pressed;
        }

        private long _whenLast;
        private boolean _pressed;
        private boolean _wasPressed;
        public int key;
 
        public Key(int nKey)
        {
        
            _pressed = false;
            _wasPressed = false;
            key = nKey;
        }
    }


    public grndzero()
    {
        debugString = "";
        setting = new HashMap();
        key = new ArrayList();
        keySpace = 0;
        keyUp = 0;
        keyDown = 0;
        keyLeft = 0;
        keyRight = 0;
        keyShift = 0;
        keyN = 0;
        hasFocus = false;
        gameWidth = 600;
        gameHeight = 400;
        screenWidth = 0;
        screenHeight = 0;
        mapWidth = 1024;
        mapHeight = 400;
        camera = new Camera(null);
        refreshCamera = false;
        mapOffX = 0;
        mapOffY = 0;
        mouseX = 0;
        mouseY = 0;
        grabX = 0;
        grabY = 0;
        mouseA = 0.0D;
        shotPowerWish = 400;
        meterDirection = 1;
        imgNumber = new ArrayList();
        player = new ArrayList();
        currentPlayer = 0;
        proj = new ArrayList();
        PlayerX = 100;
        PlayerY = 50;
        PlayerAA = 90;
        PlayerFace = 1;
        tracker = new MediaTracker(this);
        img = getClass().getResource("res/");
        drmNormal = AlphaComposite.SrcAtop;
        drmCut = AlphaComposite.Clear;
        drmReplace = AlphaComposite.Src;
    }

    public void init()
    {
        debugWindow = new DebugWindow(this);
        debugWindow.pack();
        debugWindow.setVisible(true);
        Debug("Starting init()");
        Debug("init()   Loading Images ...");
        Debug((new StringBuilder("   Resource URL:")).append(img).toString());
        imgLostFocus = getImage(img, "focusLost.png");
        tracker.addImage(imgLostFocus, nextID());
        player.add(new Chara());
        ((Chara)player.get(player.size() - 1)).spriteSheet = getImage(img, "sheetTin.png");
        tracker.addImage(((Chara)player.get(player.size() - 1)).spriteSheet, nextID());
        player.add(new Chara());
        ((Chara)player.get(player.size() - 1)).spriteSheet = getImage(img, "sheetThrash.png");
        tracker.addImage(((Chara)player.get(player.size() - 1)).spriteSheet, nextID());
        testProjectile = getImage(img, "rocket1.png");
        tracker.addImage(testProjectile, nextID());
        imgPowerMeter = getImage(img, "powerMeter.png");
        tracker.addImage(imgPowerMeter, nextID());
        imgPowerBar = getImage(img, "powerBar.png");
        tracker.addImage(imgPowerBar, nextID());
        imgPowerLast = getImage(img, "powerMeterLast.png");
        tracker.addImage(imgPowerLast, nextID());
        imgPowerWish = getImage(img, "powerMeterWish.png");
        tracker.addImage(imgPowerWish, nextID());
        imgAngleArrow = getImage(img, "angleArrow.png");
        tracker.addImage(imgAngleArrow, nextID());
        for(int cnt = 0; cnt < 10; cnt++)
        {
            imgNumber.add(getImage(img, (new StringBuilder("n")).append(cnt).append(".png").toString()));
            tracker.addImage((Image)imgNumber.get(imgNumber.size() - 1), nextID());
        }

        screenWidth = getSize().width;
        screenHeight = getSize().height;
        metricsX = (double)gameWidth / (double)screenWidth;
        metricsY = (double)gameHeight / (double)screenHeight;
        imgDbl = createImage(gameWidth, gameHeight);
        bufDbl = imgDbl.getGraphics();
        imgCurGrab = getImage(img, "cur_grab.png");
        tracker.addImage(imgCurGrab, nextID());
        imgCurPoint = getImage(img, "cur_point.png");
        tracker.addImage(imgCurPoint, nextID());
        imgBackground = getImage(img, "blusky.png");
        tracker.addImage(imgBackground, nextID());
        imgGround = new BufferedImage(mapWidth, mapHeight, 2);
        imgGround2 = new BufferedImage(mapWidth, mapHeight, 2);
        Debug("init()   Waiting for Images to load...");
        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException e)
        {
            System.out.println((new StringBuilder("ERROR FOUND! ")).append(e).toString());
        }
        Debug("init()   Setting up generic players...");
        ((Chara)player.get(0)).x = 200;
        ((Chara)player.get(0)).y = 50;
        ((Chara)player.get(0)).loadSprite(((Chara)player.get(0)).spriteSheet);
        ((Chara)player.get(1)).x = 500;
        ((Chara)player.get(1)).y = 50;
        ((Chara)player.get(1)).loadSprite(((Chara)player.get(1)).spriteSheet);
        camera.setFocus((Viewer)player.get(0));
        Debug("init()   Hiding the system Cursor");
        int pixels[] = new int[256];
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
        java.awt.Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
        setCursor(transparentCursor);
        Debug("init()   Adding Listeners...");
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
        addKeyListener(this);
        Debug("init()   Loading game keys...");
        key.add(new Key(32));
        keySpace = key.size() - 1;
        key.add(new Key(38));
        keyUp = key.size() - 1;
        key.add(new Key(40));
        keyDown = key.size() - 1;
        key.add(new Key(37));
        keyLeft = key.size() - 1;
        key.add(new Key(39));
        keyRight = key.size() - 1;
        key.add(new Key(16));
        keyShift = key.size() - 1;
        key.add(new Key(78));
        keyN = key.size() - 1;
        Debug("init()   Creating generic button...");
        okButton = new Button("A button");
        okButton.addActionListener(this);
        okButton.setForeground(Color.red);
        okButton.setBounds(20, 20, 100, 30);
        Debug("init()   Starting run() Thread");
        loop = new Thread(this);
        loop.start();
        Debug("init()  End of init()");
    }

    public void run()
    {
        Debug("run()    Thread Reached");
        newMap(0L);
        long timeProjectiles = 0L;
        long timePowerMeter = 0L;
        long timeAimMeter = 0L;
        long walkTime = 0L;
        long timeCamera = 0L;
        do
        {
            refreshCamera = false;
            if(timeProjectiles < System.currentTimeMillis())
            {
                timeProjectiles = System.currentTimeMillis() + 10L;
                for(int current = 0; current < proj.size(); current++)
                    runProjectile((Projectile)proj.get(current));

            }
            if(timePowerMeter < System.currentTimeMillis())
            {
                timePowerMeter = System.currentTimeMillis() + 5L;
                if(!((Key)key.get(keySpace)).wasPressed() && ((Key)key.get(keySpace)).isPressed())
                {
                    shotPower = 0;
                    shotPowerActual = 0;
                    meterDirection = 1;
                }
                if(((Key)key.get(keySpace)).isPressed())
                {
                    shotPower += meterDirection;
                    if(shotPower == 0)
                        meterDirection = 1;
                    else
                    if(shotPower == 400)
                        meterDirection = -1;
                    if(shotPower > shotPowerWish - 10 && shotPower < shotPowerWish + 10)
                        shotPowerActual = shotPowerWish;
                    else
                        shotPowerActual = shotPower;
                } else
                if(((Key)key.get(keySpace)).wasPressed())
                {
                    proj.add(new Projectile());
                    int c = proj.size() - 1;
                    ((Projectile)proj.get(c)).x = ((Chara)player.get(currentPlayer)).x;
                    ((Projectile)proj.get(c)).y = ((Chara)player.get(currentPlayer)).y;
                    double spd = 0.014999999999999999D * (double)shotPowerActual;
                    ((Projectile)proj.get(c)).setSpeed(spd);
                    ((Projectile)proj.get(c)).setAngle(((Chara)player.get(currentPlayer)).aimAngle * ((Chara)player.get(currentPlayer)).face - 90);
                    ((Projectile)proj.get(c)).x = ((Projectile)proj.get(c)).x + ((Projectile)proj.get(c)).gx * 8D;
                    ((Projectile)proj.get(c)).y = ((Projectile)proj.get(c)).y + ((Projectile)proj.get(c)).gy * 8D;
                    ((Key)key.get(keySpace)).kick();
                    shotPowerLast = shotPowerActual;
                    camera.setFocus((Viewer)proj.get(c));
                }
            }
            if(timeAimMeter < System.currentTimeMillis())
            {
                timeAimMeter = System.currentTimeMillis() + 80L;
                int aimSpeed = 1;
                if(((Key)key.get(keyShift)).isPressed())
                    aimSpeed = 5;
                if(((Key)key.get(keyUp)).isPressed())
                    ((Chara)player.get(currentPlayer)).aimAngle += aimSpeed;
                if(((Key)key.get(keyDown)).isPressed())
                    ((Chara)player.get(currentPlayer)).aimAngle -= aimSpeed;
                if(((Chara)player.get(currentPlayer)).aimAngle < 0)
                    ((Chara)player.get(currentPlayer)).aimAngle = 0;
                if(((Chara)player.get(currentPlayer)).aimAngle > 180)
                    ((Chara)player.get(currentPlayer)).aimAngle = 180;
            }
            if(walkTime < System.currentTimeMillis() && ((Chara)player.get(currentPlayer)).alt == null)
            {
                walkTime = System.currentTimeMillis() + 40L;
                if(((Key)key.get(keyLeft)).wasPressed() && ((Key)key.get(keyLeft)).isPressed())
                {
                    int py = ((Chara)player.get(currentPlayer)).y;
                    int px = ((Chara)player.get(currentPlayer)).x - 1;
                    if(px < 0)
                        px += mapWidth;
                    for(int colour = imgGround.getRGB(px, py + 18); colour != 0; colour = imgGround.getRGB(px, py + 18))
                        py--;

                    if(((Chara)player.get(currentPlayer)).y - py < 8)
                    {
                        ((Chara)player.get(currentPlayer)).x = px;
                        ((Chara)player.get(currentPlayer)).y = py;
                        ((Chara)player.get(currentPlayer)).storeCount++;
                        if(((Chara)player.get(currentPlayer)).storeCount > 13)
                        {
                            ((Chara)player.get(currentPlayer)).storeCount = 0;
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        }
                        if(((Chara)player.get(currentPlayer)).storeCount > -1)
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        if(((Chara)player.get(currentPlayer)).storeCount > 2)
                            ((Chara)player.get(currentPlayer)).storeHash = "w1";
                        if(((Chara)player.get(currentPlayer)).storeCount > 6)
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        if(((Chara)player.get(currentPlayer)).storeCount > 9)
                            ((Chara)player.get(currentPlayer)).storeHash = "w2";
                    }
                } else
                if(((Key)key.get(keyLeft)).isPressed())
                {
                    camera.setFocus((Viewer)player.get(currentPlayer));
                    ((Chara)player.get(currentPlayer)).storeHash = "f";
                    ((Chara)player.get(currentPlayer)).storeCount = 0;
                    ((Chara)player.get(currentPlayer)).face = -1;
                } else
                if(((Key)key.get(keyLeft)).wasPressed())
                {
                    ((Chara)player.get(currentPlayer)).storeHash = "f";
                    ((Chara)player.get(currentPlayer)).storeCount = 0;
                    ((Key)key.get(keyLeft)).kick();
                }
                if(((Key)key.get(keyRight)).wasPressed() && ((Key)key.get(keyRight)).isPressed())
                {
                    int py = ((Chara)player.get(currentPlayer)).y;
                    int px = ((Chara)player.get(currentPlayer)).x + 1;
                    if(px > mapWidth - 1)
                        px -= mapWidth - 1;
                    for(int colour = imgGround.getRGB(px, py + 18); colour != 0; colour = imgGround.getRGB(px, py + 18))
                        py--;

                    if(((Chara)player.get(currentPlayer)).y - py < 8)
                    {
                        ((Chara)player.get(currentPlayer)).x = px;
                        ((Chara)player.get(currentPlayer)).y = py;
                        ((Chara)player.get(currentPlayer)).storeCount++;
                        if(((Chara)player.get(currentPlayer)).storeCount > 13)
                        {
                            ((Chara)player.get(currentPlayer)).storeCount = 0;
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        }
                        if(((Chara)player.get(currentPlayer)).storeCount > -1)
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        if(((Chara)player.get(currentPlayer)).storeCount > 2)
                            ((Chara)player.get(currentPlayer)).storeHash = "w1";
                        if(((Chara)player.get(currentPlayer)).storeCount > 6)
                            ((Chara)player.get(currentPlayer)).storeHash = "f";
                        if(((Chara)player.get(currentPlayer)).storeCount > 9)
                            ((Chara)player.get(currentPlayer)).storeHash = "w2";
                    }
                } else
                if(((Key)key.get(keyRight)).isPressed())
                {
                    camera.setFocus((Viewer)player.get(currentPlayer));
                    ((Chara)player.get(currentPlayer)).storeHash = "f";
                    ((Chara)player.get(currentPlayer)).storeCount = 0;
                    ((Chara)player.get(currentPlayer)).face = 1;
                } else
                if(((Key)key.get(keyRight)).wasPressed())
                {
                    ((Chara)player.get(currentPlayer)).storeHash = "f";
                    ((Chara)player.get(currentPlayer)).storeCount = 0;
                    ((Key)key.get(keyRight)).kick();
                }
            }
            if(timeCamera < System.currentTimeMillis() || refreshCamera)
            {
                timeCamera = System.currentTimeMillis() + 5L;
                camera.calculate();
            }
            int mMouseX = 0;
            int x1 = (mouseX + mapWidth) - ((Chara)player.get(currentPlayer)).x - mapOffX;
            int x2 = mouseX - ((Chara)player.get(currentPlayer)).x - mapOffX;
            if(min(Math.abs(x1), Math.abs(x2)) == Math.abs(x1))
                mMouseX = x1;
            else
                mMouseX = x2;
            mouseA = (double)(90 - PlayerAA * ((Chara)player.get(currentPlayer)).face) * 0.017453292519943295D;
            for(int c = 0; c < player.size(); c++)
                if(((Chara)player.get(c)).alt == null && imgGround.getRGB(((Chara)player.get(c)).x, ((Chara)player.get(c)).y + 18) == 0)
                    ((Chara)player.get(c)).y++;

            if(((Key)key.get(keyN)).isPressed())
            {
                Debug("Tab");
                currentPlayer++;
                if(currentPlayer > player.size() - 1)
                    currentPlayer = 0;
                ((Key)key.get(keyN)).halt();
                camera.setFocus((Viewer)player.get(currentPlayer));
            }
            repaint();
        } while(true);
    }

    public void runProjectile(Projectile proObj)
    {
        if(camera.following == proObj)
            refreshCamera = true;
        proObj.advance();
        if(proObj.alt != null)
        {
            proObj.alt.x = proObj.getX();
            proObj.alt.y = proObj.getY() - 18;
        }
        if(proObj.x > (double)mapWidth)
            proObj.x -= mapWidth;
        else
        if(proObj.x < 0.0D)
            proObj.x += mapWidth;
        if(proObj.x >= 0.0D && proObj.x <= (double)mapWidth && proObj.y >= 0.0D && proObj.y <= (double)mapHeight)
        {
            int colour = imgGround.getRGB((int)proObj.x, (int)proObj.y);
            if(colour != 0)
            {
                Graphics2D bufGround = (Graphics2D)imgGround.getGraphics();
                int tmpX = 0;
                int tmpY = 0;
                double difX = proObj.x - proObj.prevX;
                double difY = proObj.y - proObj.prevY;
                double stepX;
                double stepY;
                int maxSteps;
                if(Math.abs(difX) > Math.abs(difY))
                {
                    maxSteps = (int)Math.abs(difX);
                    if(difX > 0.0D)
                        stepX = 1.0D;
                    else
                        stepX = -1D;
                    stepY = difY / difX;
                } else
                {
                    maxSteps = (int)Math.abs(difY);
                    if(difY > 0.0D)
                        stepY = 1.0D;
                    else
                        stepY = -1D;
                    stepX = difX / difY;
                }
                for(int c = 0; c < maxSteps + 1; c++)
                {
                    tmpX = (int)(proObj.prevX + stepX * (double)c);
                    tmpY = (int)(proObj.prevY + stepY * (double)c);
                    if(tmpX < 0)
                        tmpX += mapWidth;
                    if(tmpX > mapWidth)
                        tmpX -= mapWidth;
                    boolean collide = false;
                    for(int cnt = 0; cnt < player.size(); cnt++)
                        if(proObj.alt != player.get(cnt))
                        {
                            double dist = Math.abs(Math.sqrt(Math.pow(tmpX - ((Chara)player.get(cnt)).x, 2D) + Math.pow(tmpY - ((Chara)player.get(cnt)).y, 2D)));
                            System.out.print((new StringBuilder("projectile ")).append(dist).append(" ").toString());
                            if(dist < (double)proObj.damRad)
                            {
                                collide = true;
                                proj.add(new Projectile());
                                ((Projectile)proj.get(proj.size() - 1)).alt = (Chara)player.get(cnt);
                                ((Projectile)proj.get(proj.size() - 1)).setSpeed(((double)proObj.damRad - dist) / 20D);
                                ((Projectile)proj.get(proj.size() - 1)).setAngle(Math.atan2(((Chara)player.get(cnt)).y - 18 - tmpY, ((Chara)player.get(cnt)).x - tmpX) * -57.295779513082323D);
                                Debug((new StringBuilder("Angle")).append(((Projectile)proj.get(proj.size() - 1)).angle).toString());
                                ((Projectile)proj.get(proj.size() - 1)).x = ((Chara)player.get(cnt)).x;
                                ((Projectile)proj.get(proj.size() - 1)).y = ((Chara)player.get(cnt)).y + 22;
                                ((Chara)player.get(cnt)).alt = (Projectile)proj.get(proj.size() - 1);
                                camera.setFocus((Viewer)player.get(cnt));
                                System.out.print(" HIT!");
                            }
                            System.out.println("");
                        }

                    colour = imgGround.getRGB(tmpX, tmpY);
                    if(colour == 0 && !collide)
                        continue;
                    if(proObj.alt == null)
                    {
                        Graphics2D bufGround2 = (Graphics2D)imgGround2.getGraphics();
                        bufGround.setComposite(drmReplace);
                        bufGround.setColor(new Color(0, 0, 0, 0));
                        bufGround.fillOval(tmpX - proObj.damRad / 2, tmpY - proObj.damRad / 2, proObj.damRad, proObj.damRad);
                        bufGround.fillOval((tmpX - proObj.damRad / 2) + mapWidth, tmpY - proObj.damRad / 2, proObj.damRad, proObj.damRad);
                        bufGround.fillOval(tmpX - proObj.damRad / 2 - mapWidth, tmpY - proObj.damRad / 2, proObj.damRad, proObj.damRad);
                        bufGround2.setComposite(drmReplace);
                        bufGround2.setColor(new Color(0, 0, 0, 0));
                        bufGround2.fillOval((tmpX - proObj.damRad / 2) + 3, (tmpY - proObj.damRad / 2) + 3, proObj.damRad - 6, proObj.damRad - 6);
                        bufGround2.fillOval((tmpX - proObj.damRad / 2) + mapWidth + 3, (tmpY - proObj.damRad / 2) + 3, proObj.damRad - 6, proObj.damRad - 6);
                        bufGround2.fillOval((tmpX - proObj.damRad / 2 - mapWidth) + 3, (tmpY - proObj.damRad / 2) + 3, proObj.damRad - 6, proObj.damRad - 6);
                    } else
                    {
                        proObj.alt.alt = null;
                    }
                    proj.remove(proObj);
                    break;
                }

            }
        }
    }

    public void update(Graphics g)
    {
        bufDbl.fillRect(0, 0, gameWidth, gameHeight);
        drawInGame((Graphics2D)bufDbl.create());
        paint(g);
    }

    public void paint(Graphics g)
    {
        drawCount++;
        if(drawCount == 1)
            g.drawImage(imgDbl, 0, 0, screenWidth, screenHeight, null);
        drawCount--;
    }

    public void drawInGame(Graphics2D g)
    {
        Graphics2D a = (Graphics2D)g.create();
        AffineTransform zeroZero = a.getTransform();
        g.drawImage(imgBackground, 0, 0, gameWidth, gameHeight, null);
        mapOffX = gameWidth / 2 - camera.getX();
        if(mapOffX > mapWidth)
            mapOffX -= mapWidth;
        if(mapOffX < 0)
            mapOffX += mapWidth;
        mapOffY = gameHeight / 2 - camera.getY();
        if(mapHeight - mapOffY > gameHeight)
            mapOffY = mapHeight - gameHeight;
        a.translate(mapOffX, mapOffY);
        AffineTransform oldTransform = a.getTransform();
        a.drawImage(imgGround2, 0, 0, null);
        a.drawImage(imgGround, 0, 0, null);
        a.drawImage(imgGround2, -mapWidth, 0, null);
        a.drawImage(imgGround, -mapWidth, 0, null);
        double armAng = 0.0D;
        for(int count = 0; count < player.size(); count++)
        {
            Chara Player = (Chara)player.get(count);
            String dctn;
            if(Player.face == 1)
                dctn = "r";
            else
                dctn = "l";
            String frame = Player.storeHash;
            Sprite Arm = (Sprite)Player.sprite.get((new StringBuilder("a")).append(dctn).toString());
            armAng = (double)(90 - Player.aimAngle * Player.face) * 0.017453292519943295D;
            a.drawImage(Player.getSprite((new StringBuilder(String.valueOf(frame))).append(dctn).toString()), Player.x - 15 - mapWidth, Player.y - 13, null);
            a.translate((Player.x - mapWidth) + 1, Player.y + 1);
            a.rotate(armAng);
            a.drawImage(Arm.img, Arm.hingeX, Arm.hingeY, null);
            a.setTransform(oldTransform);
            a.drawImage(Player.getSprite((new StringBuilder(String.valueOf(frame))).append(dctn).toString()), Player.x - 15, Player.y - 13, null);
            a.translate(Player.x + 1, Player.y + 2);
            a.rotate(armAng);
            a.drawImage(Arm.img, Arm.hingeX, Arm.hingeY, null);
            a.setTransform(oldTransform);
        }

        for(int current = 0; current < proj.size(); current++)
            try
            {
                if(((Projectile)proj.get(current)).alt == null)
                {
                    a.translate(((Projectile)proj.get(current)).x, ((Projectile)proj.get(current)).y);
                    a.rotate(((Projectile)proj.get(current)).radians);
                    a.drawImage(testProjectile, -7, -3, null);
                    a.setTransform(oldTransform);
                    a.translate(((Projectile)proj.get(current)).x - (double)mapWidth, ((Projectile)proj.get(current)).y);
                    a.rotate(((Projectile)proj.get(current)).radians);
                    a.drawImage(testProjectile, -7, -3, null);
                    a.setTransform(oldTransform);
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println((new StringBuilder("INDEX OUT OF BOUNDS ")).append(current).append("/").append(proj.size() - 1).toString());
            }

        g.setTransform(zeroZero);
        g.drawImage(imgPowerMeter, 60, 347, null);
        if(shotPower != 0)
            g.drawImage(imgPowerBar, 100, 373, shotPowerActual, 9, null);
        g.drawImage(imgPowerLast, 96 + shotPowerLast, 378, null);
        g.drawImage(imgPowerWish, 96 + shotPowerWish, 366, null);
        g.translate(80, 367);
        g.rotate((double)(90 - ((Chara)player.get(currentPlayer)).aimAngle * ((Chara)player.get(currentPlayer)).face) * 0.017453292519943295D);
        g.drawImage(imgAngleArrow, 5, -4, null);
        g.setTransform(zeroZero);
        int nw = 0;
        int rn = ((Chara)player.get(currentPlayer)).aimAngle * ((Chara)player.get(currentPlayer)).face - 90;
        if(rn < 0)
            rn += 360;
        int n3 = rn % 10;
        rn /= 10;
        int n2 = rn % 10;
        rn /= 10;
        int n1 = rn % 10;
        if(n1 != 0)
            nw = (nw + ((Image)imgNumber.get(n1)).getWidth(null)) - 1;
        else
            n1 = -1;
        if(n2 == 0 && n1 != -1 || n2 != 0)
            nw = (nw + ((Image)imgNumber.get(n2)).getWidth(null)) - 1;
        else
            n2 = -1;
        nw = (nw + ((Image)imgNumber.get(n3)).getWidth(null)) - 1;
        g.translate(80 - nw / 2, 360);
        if(n1 != -1)
        {
            g.drawImage((Image)imgNumber.get(n1), 0, 0, null);
            g.translate(((Image)imgNumber.get(n1)).getWidth(null) - 1, 0);
        }
        if(n2 != -1)
        {
            g.drawImage((Image)imgNumber.get(n2), 0, 0, null);
            g.translate(((Image)imgNumber.get(n2)).getWidth(null) - 1, 0);
        }
        g.drawImage((Image)imgNumber.get(n3), 0, 0, null);
        g.setTransform(zeroZero);
        if(boolGrab)
            g.drawImage(imgCurGrab, mouseX, mouseY, null);
        else
            g.drawImage(imgCurPoint, mouseX, mouseY, null);
        if(!hasFocus())
            g.drawImage(imgLostFocus, 0, 0, gameWidth, gameHeight, null);
    }

    public void mouseClicked(MouseEvent evt)
    {
        evt.isMetaDown();
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent evt)
    {
        if(evt.isMetaDown() || evt.getButton() == 3 || evt.isControlDown() && evt.getButton() == 1)
        {
            if(evt.isMetaDown())
                Debug_("MetaDown, ");
            if(evt.getButton() == 3)
                Debug_(" BUTTON3 detected, ");
            if(evt.isControlDown())
                Debug_(" Ctrl detected, ");
            Debug(" dragging allowed.");
            camera.x = camera.following.getX() + gameWidth / 2;
            camera.y = camera.following.getY() + gameHeight / 2;
            camera.setFocus(camera);
            grabX = (int)((double)evt.getX() * metricsX);
            grabY = (int)((double)evt.getY() * metricsY);
            camera.origX = camera.x;
            camera.origY = camera.y;
            boolGrab = true;
        } else
        if(mouseY > 360 && mouseY < 387 && mouseX > 98 && mouseX < 500)
            shotPowerWish = mouseX - 99;
    }

    public void mouseReleased(MouseEvent evt)
    {
        boolGrab = false;
    }

    public void mouseDragged(MouseEvent evt)
    {
        mouseX = (int)((double)evt.getX() * metricsX);
        mouseY = (int)((double)evt.getY() * metricsY);
        if(boolGrab)
        {
            camera.x = camera.origX - (mouseX - grabX);
            if(camera.x < 0)
                camera.x = camera.x + mapWidth;
            if(camera.x > mapWidth)
                camera.x = camera.x - mapWidth;
            camera.y = camera.origY - (mouseY - grabY);
        } else
        if(mouseY > 360 && mouseY < 387 && mouseX > 98 && mouseX < 500)
            shotPowerWish = mouseX - 99;
    }

    public void mouseMoved(MouseEvent evt)
    {
        mouseX = (int)((double)evt.getX() * metricsX);
        mouseY = (int)((double)evt.getY() * metricsY);
    }

    public long newMap(long seed)
    {
        Random rndm = new Random();
        if(seed != 0L)
        {
            rndm.setSeed(seed);
        } else
        {
            seed = rndm.nextLong();
            rndm.setSeed(seed);
        }
        ArrayList points = new ArrayList();
        int mapX = 20;
        int mapY = mapHeight / 2;
        points.add(new mapPoint());
        int newX = 0;
        int newY = 0;
        int c = -1;
        while(mapX < mapWidth - 50) 
        {
            newX = rndm.nextInt(30) + 15;
            mapX += newX;
            if(mapX > mapWidth)
                mapX = mapWidth;
            do
                newY = rndm.nextInt(40) - 20;
            while(mapY + newY <= 0 || mapY + newY >= mapHeight);
            mapY += newY;
            points.add(new mapPoint());
            c = points.size() - 1;
            ((mapPoint)points.get(c)).x = mapX;
            ((mapPoint)points.get(c)).y = mapY;
        }
        ((mapPoint)points.get(0)).y = 200;
        Graphics2D bufGround = imgGround.createGraphics();
        bufGround.setColor(new Color(0, 0, 0, 0));
        bufGround.fillRect(0, 0, mapWidth, mapHeight);
        bufGround.setColor(new Color(255, 255, 255));
        for(int current = 0; current < points.size(); current++)
        {
            int cur0;
            if(current - 1 < 0)
                cur0 = ((mapPoint)points.get(points.size() - 1)).y;
            else
                cur0 = ((mapPoint)points.get(current - 1)).y;
            int cur1 = ((mapPoint)points.get(current)).y;
            int smallX = ((mapPoint)points.get(current)).x;
            mapY = cur1;
            int cur2;
            int bigX;
            if(current + 1 > points.size() - 1)
            {
                cur2 = ((mapPoint)points.get(current - (points.size() - 1))).y;
                bigX = ((mapPoint)points.get(current - (points.size() - 1))).x + mapWidth;
            } else
            {
                cur2 = ((mapPoint)points.get(current + 1)).y;
                bigX = ((mapPoint)points.get(current + 1)).x;
            }
            int cur3;
            if(current + 2 > points.size() - 1)
                cur3 = ((mapPoint)points.get((current + 1) - (points.size() - 1))).y;
            else
                cur3 = ((mapPoint)points.get(current + 2)).y;
            int difX = bigX - smallX;
            for(int stepX = 0; stepX < difX + 1; stepX++)
            {
                int cubeY = (int)CubicInterPolation(cur0, cur1, cur2, cur3, (double)stepX / (double)difX);
                bufGround.drawLine(smallX + stepX, mapY, smallX + stepX, cubeY);
                mapY = cubeY;
            }

        }

        Image imgTmpDirt = getImage(img, "ground1.png");
        tracker.addImage(imgTmpDirt, nextID());
        Image imgTmpGrass = getImage(img, "grass1.png");
        tracker.addImage(imgTmpGrass, nextID());
        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
        int dirtWidth = imgTmpDirt.getWidth(null);
        int dirtHeight = imgTmpDirt.getHeight(null);
        BufferedImage imgDirt = new BufferedImage(dirtWidth, dirtHeight, 2);
        Graphics bufDirt = imgDirt.getGraphics();
        bufDirt.drawImage(imgTmpDirt, 0, 0, null);
        int grassWidth = imgTmpGrass.getWidth(null);
        int grassHeight = imgTmpGrass.getHeight(null);
        BufferedImage imgGrass = new BufferedImage(grassWidth, grassHeight, 2);
        Graphics bufGrass = imgGrass.getGraphics();
        bufGrass.drawImage(imgTmpGrass, 0, 0, null);
        BufferedImage imgGrassOver = new BufferedImage(mapWidth, mapHeight, 2);
        Graphics bufGrassOver = imgGrassOver.getGraphics();
        bufGrassOver.setColor(new Color(0, 0, 0, 0));
        bufGrassOver.fillRect(0, 0, mapWidth, mapHeight);
        bufGround.setColor(new Color(0, 255, 0));
        boolean startDraw = false;
        int grassR = 0;
        float shader = 128F / (float)mapHeight;
        for(mapX = 0; mapX < mapWidth; mapX++)
        {
            startDraw = false;
            grassR = 0;
            for(mapY = 0; mapY < mapHeight; mapY++)
            {
                int colour = imgGround.getRGB(mapX, mapY);
                if(colour == -1)
                    startDraw = true;
                if(startDraw)
                {
                    int colour2 = imgDirt.getRGB(mapX % dirtWidth, mapY % dirtHeight);
                    int sh = 192 - (int)((float)mapY * shader);
                    int colour3 = 0xff000000 | Overlay(RGB(sh, sh, sh), colour2);
                    imgGround.setRGB(mapX, mapY, colour3);
                    int colourBack = 0xff000000 | Overlay(RGB(sh, sh, sh), RGB(88, 62, 0));
                    imgGround2.setRGB(mapX, mapY, colourBack);
                    if(++grassR < grassHeight)
                    {
                        colour2 = imgGrass.getRGB(mapX % grassWidth, grassR);
                        imgGrassOver.setRGB(mapX, mapY, colour2);
                    }
                }
            }

        }

        bufGround.drawImage(imgGrassOver, 0, 0, null);
        return seed;
    }

    public double CubicInterPolation(double y0, double y1, double y2, double y3, double mu)
    {
        double mu2 = mu * mu;
        double a0 = (y3 - y2 - y0) + y1;
        double a1 = y0 - y1 - a0;
        double a2 = y2 - y0;
        double a3 = y1;
        return a0 * mu * mu2 + a1 * mu2 + a2 * mu + a3;
    }

    public static int Overlay(int colour1, int colour2)
    {
        int r = getRed(colour1);
        int g = getGreen(colour1);
        int b = getBlue(colour1);
        int r3 = 0;
        int g3 = 0;
        int b3 = 0;
        if(RGB(r, g, b) == 0);
        float r1 = ((float)r - 1.0F) / 255F;
        float g1 = ((float)g - 1.0F) / 255F;
        float b1 = ((float)b - 1.0F) / 255F;
        float r2 = ((float)getRed(colour2) - 1.0F) / 255F;
        float g2 = ((float)getGreen(colour2) - 1.0F) / 255F;
        float b2 = ((float)getBlue(colour2) - 1.0F) / 255F;
        if(r1 <= 0.5F)
            r3 = (int)(2.0F * r1 * r2 * 255F);
        else
            r3 = (int)((1.0F - 2.0F * (1.0F - r1) * (1.0F - r2)) * 255F);
        if(g1 <= 0.5F)
            g3 = (int)(2.0F * g1 * g2 * 255F);
        else
            g3 = (int)((1.0F - 2.0F * (1.0F - g1) * (1.0F - g2)) * 255F);
        if(b1 <= 0.5F)
            b3 = (int)(2.0F * b1 * b2 * 255F);
        else
            b3 = (int)((1.0F - 2.0F * (1.0F - b1) * (1.0F - b2)) * 255F);
        return RGB(r3, g3, b3);
    }

    public static int RGB(int r, int g, int b)
    {
        int completeColor = r << 16 | g << 8 | b;
        return completeColor;
    }

    public static int RGB(int r, int g, int b, int a)
    {
        int completeColor = a << 24 | r << 16 | g << 8 | b;
        return completeColor;
    }

    public static int getRed(int colour)
    {
        return (colour & 0xff0000) >>> 16;
    }

    public static int getGreen(int colour)
    {
        return (colour & 0xff00) >>> 8;
    }

    public static int getBlue(int colour)
    {
        return colour & 0xff;
    }

    public static int getAlpha(int colour)
    {
        return (colour & 0xff000000) >>> 24;
    }

    public int min(int num1, int num2)
    {
        if(num1 > num2)
            return num2;
        else
            return num1;
    }

    public int max(int num1, int num2)
    {
        if(num1 > num2)
            return num1;
        else
            return num2;
    }

    public void componentHidden(ComponentEvent componentevent)
    {
    }

    public void componentMoved(ComponentEvent componentevent)
    {
    }

    public void componentResized(ComponentEvent evt)
    {
        screenWidth = getSize().width;
        screenHeight = getSize().height;
        metricsX = (double)gameWidth / (double)screenWidth;
        metricsY = (double)gameHeight / (double)screenHeight;
    }

    public void componentShown(ComponentEvent componentevent)
    {
    }

    public void actionPerformed(ActionEvent evt)
    {
        evt.getSource();
    }

    public void keyPressed(KeyEvent evt)
    {
        int keyCode = evt.getKeyCode();
        for(int keys = 0; keys < key.size(); keys++)
            if(keyCode == ((Key)key.get(keys)).key)
                ((Key)key.get(keys)).press(evt.getWhen());

    }

    public void keyReleased(KeyEvent evt)
    {
        int keyCode = evt.getKeyCode();
        for(int keys = 0; keys < key.size(); keys++)
            if(keyCode == ((Key)key.get(keys)).key)
                ((Key)key.get(keys)).release(evt.getWhen());

    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void readSettings()
    {
    	 InputStream inStream = null;
    	    BufferedReader bufReader = null;
    	    try
    	    {
    	      inStream = getClass().getResourceAsStream("res/settings.txt");
    	      bufReader = new BufferedReader(new InputStreamReader(inStream));
    	      String ln;
    	      while ((ln = bufReader.readLine()) != null)
    	      {
    	   
    	        String[] lnSplit = ln.split("=");
    	        this.setting.put(lnSplit[0], lnSplit[1]);
    	      }
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	      try
    	      {
    	        if (bufReader != null) bufReader.close();
    	        if (inStream != null) inStream.close(); 
    	      }
    	      catch (IOException ex) {
    	        ex.printStackTrace();
    	      }
    	    }
    	    finally
    	    {
    	      try
    	      {
    	        if (bufReader != null) bufReader.close();
    	        if (inStream != null) inStream.close(); 
    	      }
    	      catch (IOException e) {
    	        e.printStackTrace();
    	      }
    	    }
    }

    public int nextID()
    {
        nextInt++;
        return nextInt;
    }


    public void Debug(String txt)
    {
      this.debugString = (this.debugString + txt + "\n");
      this.debugWindow.txtbox.setText(this.debugString);
    }

    public void Debug_(String txt)
    {
      this.debugString += txt;
      this.debugWindow.txtbox.setText(this.debugString);
    }

    public void focusGained(FocusEvent arg0)
    {
        hasFocus = true;
        Debug("gained");
    }

    public void focusLost(FocusEvent arg0)
    {
        hasFocus = false;
        Debug("lost");
    }

    private static final long serialVersionUID = 0x4b6df85d5ce9935aL;
    final double DEGtoRAD = 0.017453292519943295D;
    final double RADtoDEG = -57.295779513082323D;
    final boolean DEBUGMODE = true;
    DebugWindow debugWindow;
    String debugString;
    Map setting;
    Thread loop;
    int drawCount;
    ArrayList key;
    int keySpace;
    int keyUp;
    int keyDown;
    int keyLeft;
    int keyRight;
    int keyShift;
    int keyN;
    boolean hasFocus;
    Button okButton;
    int gameWidth;
    int gameHeight;
    int screenWidth;
    int screenHeight;
    double metricsX;
    double metricsY;
    int mapWidth;
    int mapHeight;
    Camera camera;
    boolean refreshCamera;
    int mapOffX;
    int mapOffY;
    int mouseX;
    int mouseY;
    int grabX;
    int grabY;
    boolean boolGrab;
    double mouseA;
    Graphics bufDbl;
    Image imgDbl;
    Image imgBackground;
    BufferedImage imgGround;
    BufferedImage imgGround2;
    Image imgCurGrab;
    Image imgCurPoint;
    Image imgLostFocus;
    Image imgPowerMeter;
    Image imgPowerBar;
    Image imgPowerLast;
    Image imgPowerWish;
    int shotPower;
    int shotPowerActual;
    int shotPowerLast;
    int shotPowerWish;
    int meterDirection;
    Image imgAngleArrow;
    ArrayList imgNumber;
    Image testProjectile;
    Image spriteSheet;
    ArrayList player;
    int currentPlayer;
    ArrayList proj;
    int PlayerX;
    int PlayerY;
    int PlayerAA;
    int PlayerFace;
    MediaTracker tracker;
    private int nextInt;
    URL img;
    AlphaComposite drmNormal;
    AlphaComposite drmCut;
    AlphaComposite drmReplace;
}
