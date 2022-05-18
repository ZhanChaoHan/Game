// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HowToPlayLevel.java

import gameEngine.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HowToPlayLevel extends Level
{

    public HowToPlayLevel(Component component)
    {
        super(component);
        MasterPanel masterpanel = (MasterPanel)component;
        keyboard = masterpanel.getKeyboard();
        config = masterpanel.getConfig();
        midi = masterpanel.getMidiPlayer();
        midi.load("sounds/lazarus.mid");
        midi.play(true);
        midi.loop(-1);
    }

    public void loadData()
    {
        MasterPanel masterpanel = (MasterPanel)parent;
        HUDSprite.loadImages(imgLoader);
        CaveNodeSprite.loadImages(imgLoader);
        AgentSprite.loadImages(imgLoader);
        WumpusSprite.loadImages(imgLoader);
        FXSprite.loadImages(imgLoader);
        HowToPlaySprite.loadImages(imgLoader);
        imgLoader.waitForAll();
        hudPanel = new HUDSprite(0.0D, 0.0D, 'p');
        hasGoldSprite = new HUDSprite(0.0D, 48D, 'g');
        hasFoodSprite = new HUDSprite(0.0D, 120D, 'f');
        hasArrowSprite = new HUDSprite(0.0D, 192D, 'a');
        bgSprite = new HowToPlaySprite(0.0D, 10D, 'b');
        htp2Sprite = new HowToPlaySprite(72D, 96D, '2');
        htp6Sprite = new HowToPlaySprite(72D, 96D, '6');
        enterSprite = new HowToPlaySprite(168D, 264D, 'e');
        Point point = gridToPanelCoords(0, 0);
        agentSprite = new AgentSprite(point.x, point.y);
        agentSprite2 = new AgentSprite(point.x, point.y);
        entranceSprite = new FXSprite(point.x, point.y, 'E');
        wumpusSprite = new WumpusSprite(0.0D, 0.0D, 'W');
        supmuwSprite = new WumpusSprite(0.0D, 0.0D, 'S');
        supmuwSprite2 = new WumpusSprite(0.0D, 0.0D, 'S');
        supmuwSprite3 = new WumpusSprite(0.0D, 0.0D, 'S');
        glitterSprite = new FXSprite(0.0D, 0.0D, 'G');
        breezeSprites = new ArrayList();
        stenchSprites = new ArrayList();
        mooSprites = new ArrayList();
        testFloor = new CaveNodeSprite(0.0D, 0.0D, 'F');
        testFloor.width = 72;
        testFloor.height = 72;
        testFloor2 = new CaveNodeSprite(0.0D, 0.0D, 'F');
        testFloor2.width = 72;
        testFloor2.height = 72;
        testFloor3 = new CaveNodeSprite(0.0D, 0.0D, 'F');
        testFloor3.width = 72;
        testFloor3.height = 72;
        testFloor4 = new CaveNodeSprite(0.0D, 0.0D, 'F');
        testFloor4.width = 72;
        testFloor4.height = 72;
        testFloor5 = new CaveNodeSprite(0.0D, 0.0D, 'F');
        testFloor5.width = 72;
        testFloor5.height = 72;
        pitSprite = new CaveNodeSprite(240D, 168D, 'P');
        slideNum = 0;
        timer1 = 0;
        initCurSlide();
    }

    public void clean()
    {
        super.clean();
        HUDSprite.clean();
        CaveNodeSprite.clean();
        AgentSprite.clean();
        HowToPlaySprite.clean();
        FXSprite.clean();
        keyboard = null;
        config = null;
    }

    public void timerLoop()
    {
        MasterPanel masterpanel = (MasterPanel)parent;
        if(keyboard.isTyped(config.VK_ESC))
        {
            masterpanel.startGame();
            return;
        }
        if(keyboard.isTyped(config.VK_ENTER))
        {
            slideNum++;
            initCurSlide();
        }
        testFloor.isVisible = false;
        testFloor2.isVisible = false;
        testFloor3.isVisible = false;
        testFloor4.isVisible = false;
        testFloor5.isVisible = false;
        pitSprite.isVisible = false;
        hudPanel.isVisible = false;
        hasGoldSprite.isVisible = false;
        hasFoodSprite.isVisible = false;
        hasArrowSprite.isVisible = false;
        htp2Sprite.isVisible = false;
        htp6Sprite.isVisible = false;
        agentSprite.isVisible = false;
        agentSprite2.isVisible = false;
        wumpusSprite.isVisible = false;
        supmuwSprite.isVisible = false;
        supmuwSprite2.isVisible = false;
        supmuwSprite3.isVisible = false;
        entranceSprite.isVisible = false;
        glitterSprite.isVisible = false;
        if(slideNum == 0)
        {
            agentSprite.isVisible = true;
            agentSprite.setDirection('S');
            agentSprite.x = 48D;
            agentSprite.y = 144D;
            agentSprite2.isVisible = true;
            testFloor.isVisible = true;
            testFloor.x = 216D;
            testFloor.y = 120D;
            glitterSprite.isVisible = true;
            glitterSprite.x = 216D;
            glitterSprite.y = 144D;
            if(agentSprite2.x > glitterSprite.x)
            {
                agentSprite2.x--;
                agentSprite2.setDirection('W');
            }
            if(agentSprite2.x == glitterSprite.x)
            {
                if(!agentSprite2.animation.equals("gold"))
                    agentSprite2.setAnimation("gold");
                timer1++;
                if(timer1 > 30)
                {
                    timer1 = 0;
                    agentSprite2.setAnimation("walk");
                    agentSprite2.x = 264D;
                }
            }
        } else
        if(slideNum == 1)
        {
            agentSprite.isVisible = true;
            agentSprite.x = 168D;
            agentSprite.y = 168D;
            htp2Sprite.isVisible = true;
        } else
        if(slideNum == 2)
        {
            wumpusSprite.isVisible = true;
            wumpusSprite.x = 72D;
            wumpusSprite.y = 144D;
            testFloor.isVisible = true;
            testFloor.x = 48D;
            testFloor.y = 120D;
            pitSprite.isVisible = true;
            pitSprite.x = 240D;
            pitSprite.y = 168D;
            testFloor2.isVisible = true;
            testFloor2.x = 216D;
            testFloor2.y = 144D;
        } else
        if(slideNum == 3)
        {
            supmuwSprite.isVisible = true;
            supmuwSprite.x = 72D;
            supmuwSprite.y = 144D;
            supmuwSprite2.isVisible = true;
            supmuwSprite2.x = 216D;
            supmuwSprite2.y = 96D;
            supmuwSprite3.isVisible = true;
            supmuwSprite3.x = 264D;
            supmuwSprite3.y = 216D;
            pitSprite.isVisible = true;
            pitSprite.x = 264D;
            pitSprite.y = 216D;
            wumpusSprite.isVisible = true;
            wumpusSprite.x = 240D;
            wumpusSprite.y = 96D;
            testFloor.isVisible = true;
            testFloor.x = 48D;
            testFloor.y = 120D;
            testFloor2.isVisible = true;
            testFloor2.x = 192D;
            testFloor2.y = 72D;
            testFloor3.isVisible = true;
            testFloor3.x = 216D;
            testFloor3.y = 72D;
            testFloor4.isVisible = true;
            testFloor4.x = 192D;
            testFloor4.y = 192D;
            testFloor5.isVisible = true;
            testFloor5.x = 240D;
            testFloor5.y = 192D;
            agentSprite.isVisible = true;
            agentSprite.setDirection('E');
            if(timer1 < 48)
                agentSprite.x++;
            if(timer1 >= 48 && timer1 < 54)
                agentSprite.x -= 4D;
            if(timer1 > 78)
            {
                agentSprite.x = 216D;
                timer1 = 0;
            }
            timer1++;
        } else
        if(slideNum == 4)
        {
            agentSprite.isVisible = true;
            agentSprite.x = 96D;
            agentSprite.y = 144D;
            wumpusSprite.isVisible = true;
            wumpusSprite.x = 240D;
            wumpusSprite.y = 144D;
            testFloor.isVisible = true;
            testFloor.x = 72D;
            testFloor.y = 120D;
            testFloor2.isVisible = true;
            testFloor2.x = 216D;
            testFloor2.y = 120D;
            hudPanel.isVisible = true;
            hasGoldSprite.isVisible = true;
            hasFoodSprite.isVisible = true;
            hasArrowSprite.isVisible = true;
            if(timer1 < 60)
            {
                wumpusSprite.setAnimation("alive");
                hasArrowSprite.isSelected = true;
            }
            if(timer1 >= 60 && timer1 < 80)
            {
                wumpusSprite.setAnimation("dead");
                agentSprite.setAnimation("shoot");
                hasArrowSprite.isSelected = false;
            }
            if(timer1 >= 80)
                agentSprite.setAnimation("walk");
            if(timer1 > 100)
                timer1 = 0;
            timer1++;
        } else
        if(slideNum == 5)
        {
            entranceSprite.isVisible = true;
            entranceSprite.x = 120D;
            entranceSprite.y = 192D;
            agentSprite.isVisible = true;
            htp6Sprite.isVisible = true;
            glitterSprite.isVisible = true;
            glitterSprite.x = 216D;
            glitterSprite.y = 168D;
            if(timer1 < 48)
            {
                if(!agentSprite.animation.equals("walk"))
                    agentSprite.setAnimation("walk");
                agentSprite.setDirection('E');
                agentSprite.x++;
            }
            if(timer1 >= 48 && timer1 < 78 && !agentSprite.animation.equals("gold"))
                agentSprite.setAnimation("gold");
            if(timer1 >= 78 && timer1 < 174)
            {
                if(!agentSprite.animation.equals("walk"))
                    agentSprite.setAnimation("walk");
                agentSprite.setDirection('W');
                agentSprite.x--;
            }
            if(timer1 >= 174 && timer1 < 198)
            {
                agentSprite.setDirection('S');
                agentSprite.y++;
            }
            if(timer1 > 222)
            {
                agentSprite.x = 168D;
                agentSprite.y = 168D;
                timer1 = 0;
            }
            timer1++;
        } else
        if(slideNum != 6 && slideNum != 7)
        {
            masterpanel.changeCurrentLevel("Menu");
            return;
        }
        animateAll();
        imgLoader.waitForAll();
    }

    public void initCurSlide()
    {
        if(slideNum == 0)
        {
            agentSprite2.x = 264D;
            agentSprite2.y = 144D;
            timer1 = 0;
        } else
        if(slideNum != 1)
            if(slideNum == 2)
            {
                FXSprite fxsprite = new FXSprite(72D, 120D, 'S');
                stenchSprites.add(fxsprite);
                fxsprite = new FXSprite(48D, 144D, 'S');
                stenchSprites.add(fxsprite);
                fxsprite = new FXSprite(96D, 144D, 'S');
                stenchSprites.add(fxsprite);
                fxsprite = new FXSprite(72D, 168D, 'S');
                stenchSprites.add(fxsprite);
                FXSprite fxsprite3 = new FXSprite(240D, 144D, 'B');
                breezeSprites.add(fxsprite3);
                fxsprite3 = new FXSprite(216D, 168D, 'B');
                breezeSprites.add(fxsprite3);
                fxsprite3 = new FXSprite(264D, 168D, 'B');
                breezeSprites.add(fxsprite3);
                fxsprite3 = new FXSprite(240D, 192D, 'B');
                breezeSprites.add(fxsprite3);
            } else
            if(slideNum == 3)
            {
                stenchSprites.clear();
                breezeSprites.clear();
                FXSprite fxsprite1 = new FXSprite(240D, 72D, 'S');
                stenchSprites.add(fxsprite1);
                fxsprite1 = new FXSprite(216D, 96D, 'S');
                stenchSprites.add(fxsprite1);
                fxsprite1 = new FXSprite(264D, 96D, 'S');
                stenchSprites.add(fxsprite1);
                fxsprite1 = new FXSprite(240D, 120D, 'S');
                stenchSprites.add(fxsprite1);
                FXSprite fxsprite4 = new FXSprite(48D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(72D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(96D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(48D, 144D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(96D, 144D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(48D, 168D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(72D, 168D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(96D, 168D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(192D, 72D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(216D, 72D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 72D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(192D, 96D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 96D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(192D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(216D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 120D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 192D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(264D, 192D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(288D, 192D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 216D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(288D, 216D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(240D, 240D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(264D, 240D, 'M');
                mooSprites.add(fxsprite4);
                fxsprite4 = new FXSprite(288D, 240D, 'M');
                mooSprites.add(fxsprite4);
                FXSprite fxsprite5 = new FXSprite(240D, 216D, 'B');
                breezeSprites.add(fxsprite5);
                fxsprite5 = new FXSprite(288D, 216D, 'B');
                breezeSprites.add(fxsprite5);
                fxsprite5 = new FXSprite(264D, 192D, 'B');
                breezeSprites.add(fxsprite5);
                fxsprite5 = new FXSprite(264D, 240D, 'B');
                breezeSprites.add(fxsprite5);
                agentSprite.x = 216D;
                agentSprite.y = 216D;
                timer1 = 0;
            } else
            if(slideNum == 4)
            {
                stenchSprites.clear();
                breezeSprites.clear();
                mooSprites.clear();
                FXSprite fxsprite2 = new FXSprite(240D, 120D, 'S');
                stenchSprites.add(fxsprite2);
                fxsprite2 = new FXSprite(216D, 144D, 'S');
                stenchSprites.add(fxsprite2);
                fxsprite2 = new FXSprite(264D, 144D, 'S');
                stenchSprites.add(fxsprite2);
                fxsprite2 = new FXSprite(240D, 168D, 'S');
                stenchSprites.add(fxsprite2);
                timer1 = 0;
            } else
            if(slideNum == 5)
            {
                stenchSprites.clear();
                timer1 = 0;
                agentSprite.x = 168D;
                agentSprite.y = 168D;
            } else
            if(slideNum != 6);
    }

    private Point gridToPanelCoords(int i, int j)
    {
        i = 48 + 24 * i;
        j = 264 - 24 * j;
        return new Point(i, j);
    }

    private void animateAll()
    {
        hudPanel.animate(imgLoader);
        hasGoldSprite.animate(imgLoader);
        hasFoodSprite.animate(imgLoader);
        hasArrowSprite.animate(imgLoader);
        agentSprite.animate(imgLoader);
        agentSprite2.animate(imgLoader);
        wumpusSprite.animate(imgLoader);
        supmuwSprite.animate(imgLoader);
        supmuwSprite2.animate(imgLoader);
        supmuwSprite3.animate(imgLoader);
        bgSprite.animate(imgLoader);
        htp2Sprite.animate(imgLoader);
        htp6Sprite.animate(imgLoader);
        enterSprite.animate(imgLoader);
        FXSprite fxsprite;
        for(Iterator iterator = breezeSprites.iterator(); iterator.hasNext(); fxsprite.animate(imgLoader))
            fxsprite = (FXSprite)iterator.next();

        FXSprite fxsprite1;
        for(Iterator iterator1 = stenchSprites.iterator(); iterator1.hasNext(); fxsprite1.animate(imgLoader))
            fxsprite1 = (FXSprite)iterator1.next();

        FXSprite fxsprite2;
        for(Iterator iterator2 = mooSprites.iterator(); iterator2.hasNext(); fxsprite2.animate(imgLoader))
            fxsprite2 = (FXSprite)iterator2.next();

        glitterSprite.animate(imgLoader);
        entranceSprite.animate(imgLoader);
    }

    public void render(Graphics2D graphics2d)
    {
        MasterPanel masterpanel = (MasterPanel)parent;
        graphics2d.setBackground(new Color(8, 8, 16));
        graphics2d.clearRect(0, 0, 340, 288);
        bgSprite.render(graphics2d);
        htp2Sprite.render(graphics2d);
        htp6Sprite.render(graphics2d);
        testFloor.render(graphics2d);
        testFloor2.render(graphics2d);
        testFloor3.render(graphics2d);
        testFloor4.render(graphics2d);
        testFloor5.render(graphics2d);
        pitSprite.render(graphics2d);
        hudPanel.render(graphics2d);
        hasGoldSprite.render(graphics2d);
        hasFoodSprite.render(graphics2d);
        hasArrowSprite.render(graphics2d);
        wumpusSprite.render(graphics2d);
        supmuwSprite.render(graphics2d);
        supmuwSprite2.render(graphics2d);
        supmuwSprite3.render(graphics2d);
        agentSprite.render(graphics2d);
        agentSprite2.render(graphics2d);
        FXSprite fxsprite;
        for(Iterator iterator = breezeSprites.iterator(); iterator.hasNext(); fxsprite.render(graphics2d))
            fxsprite = (FXSprite)iterator.next();

        FXSprite fxsprite1;
        for(Iterator iterator1 = stenchSprites.iterator(); iterator1.hasNext(); fxsprite1.render(graphics2d))
            fxsprite1 = (FXSprite)iterator1.next();

        FXSprite fxsprite2;
        for(Iterator iterator2 = mooSprites.iterator(); iterator2.hasNext(); fxsprite2.render(graphics2d))
            fxsprite2 = (FXSprite)iterator2.next();

        glitterSprite.render(graphics2d);
        entranceSprite.render(graphics2d);
        graphics2d.setColor(new Color(255, 255, 255));
        if(slideNum == 0)
        {
            graphics2d.drawString("This is the Hero.", 24, 96);
            graphics2d.drawString("The Hero likes gold!", 192, 96);
            graphics2d.drawString("Gold likes to sparkle!", 192, 111);
        } else
        if(slideNum == 1)
        {
            drawCenteredString("The cave the gold hides in is very dark. The Hero can only", 168, 48, graphics2d);
            drawCenteredString("see parts of the cave that he's already been to. ", 168, 63, graphics2d);
            drawCenteredString("Walls also become visible only if you bump into them.", 168, 78, graphics2d);
        } else
        if(slideNum == 2)
        {
            drawCenteredString("This is the Wumpus.", 72, 48, graphics2d);
            drawCenteredString("The Wumpus likes eating heroes!", 72, 63, graphics2d);
            drawCenteredString("He also smells bad.", 72, 78, graphics2d);
            drawCenteredString("Nobody likes the Wumpus.", 72, 93, graphics2d);
            drawCenteredString("This is a pit.", 240, 72, graphics2d);
            drawCenteredString("Pits like surrounding themselves", 240, 87, graphics2d);
            drawCenteredString("with gentle breezes!", 240, 102, graphics2d);
            drawCenteredString("Pits also eat heroes.", 240, 117, graphics2d);
        } else
        if(slideNum == 3)
        {
            drawCenteredString("This is the Supmuw.", 72, 48, graphics2d);
            drawCenteredString("The Supmuw likes to say Moo!", 72, 63, graphics2d);
            drawCenteredString("Usually the Supmuw is friendly", 72, 78, graphics2d);
            drawCenteredString("and he'll give the Hero food!", 72, 93, graphics2d);
            drawCenteredString("However, the Supmuw becomes", 240, 48, graphics2d);
            drawCenteredString("murderous if he smells the Wumpus.", 240, 63, graphics2d);
            drawCenteredString("If the Supmuw is in a pit, he'll", 240, 168, graphics2d);
            drawCenteredString("keep the Hero from falling in!", 240, 183, graphics2d);
        } else
        if(slideNum == 4)
        {
            drawCenteredString("The Hero has a Magic Arrow!", 168, 48, graphics2d);
            drawCenteredString("He can fire it from his bow to slay the Wumpus!", 168, 63, graphics2d);
            drawCenteredString("Be careful... The Hero only has ONE Arrow.", 168, 78, graphics2d);
        } else
        if(slideNum == 5)
        {
            drawCenteredString("Help the Hero find the gold,", 168, 48, graphics2d);
            drawCenteredString("return to the cave's entrance alive,", 168, 63, graphics2d);
            drawCenteredString("and climb out to be VICTORIOUS!,", 168, 78, graphics2d);
        } else
        if(slideNum == 6)
        {
            drawCenteredString("Here are the controls:,", 168, 48, graphics2d);
            drawCenteredString("Turn left .......LEFT ARROW", 168, 78, graphics2d);
            drawCenteredString("Turn right .......RIGHT ARROW", 168, 93, graphics2d);
            drawCenteredString("Move forward ...........UP ARROW", 168, 108, graphics2d);
            drawCenteredString("Grab gold ................ENTER", 168, 123, graphics2d);
            drawCenteredString("Shoot arrow .............SHIFT", 168, 138, graphics2d);
            drawCenteredString("Climb out of cave entrance ............CTRL", 168, 153, graphics2d);
            drawCenteredString("Return to Title .............ESC", 168, 168, graphics2d);
        } else
        if(slideNum == 7)
        {
            drawCenteredString("Credits:,", 168, 48, graphics2d);
            drawCenteredString("Design: Stephen Lindberg, Dr. Bill Eberle", 168, 78, graphics2d);
            drawCenteredString("Art: Stephen Lindberg", 168, 93, graphics2d);
            drawCenteredString("Programming: Stephen Lindberg", 168, 108, graphics2d);
            drawCenteredString("Co-programming: Chris Leatherwood", 168, 123, graphics2d);
            drawCenteredString("Music: Circy, Mega Man 1, Zero Wing", 168, 138, graphics2d);
            drawCenteredString("Sounds: Stephen Lindberg, Clickteam, teh Interwebs", 168, 153, graphics2d);
            drawCenteredString("", 168, 168, graphics2d);
        }
        enterSprite.render(graphics2d);
    }

    public void drawCenteredString(String s, int i, int j, Graphics g)
    {
        FontMetrics fontmetrics = g.getFontMetrics();
        i -= fontmetrics.stringWidth(s) / 2;
        g.drawString(s, i, j);
    }

    private HUDSprite hudPanel;
    private HUDSprite hasGoldSprite;
    private HUDSprite hasFoodSprite;
    private HUDSprite hasArrowSprite;
    private HowToPlaySprite bgSprite;
    private HowToPlaySprite htp2Sprite;
    private HowToPlaySprite htp6Sprite;
    private HowToPlaySprite enterSprite;
    private AgentSprite agentSprite;
    private AgentSprite agentSprite2;
    private WumpusSprite wumpusSprite;
    private WumpusSprite supmuwSprite;
    private WumpusSprite supmuwSprite2;
    private WumpusSprite supmuwSprite3;
    private FXSprite entranceSprite;
    private FXSprite glitterSprite;
    private ArrayList breezeSprites;
    private ArrayList stenchSprites;
    private ArrayList mooSprites;
    private CaveNodeSprite testFloor;
    private CaveNodeSprite testFloor2;
    private CaveNodeSprite testFloor3;
    private CaveNodeSprite testFloor4;
    private CaveNodeSprite testFloor5;
    private CaveNodeSprite pitSprite;
    private int slideNum;
    private int timer1;
    private Keyboard keyboard;
    private ConfigWumpus config;
    private MidiPlayer midi;
}
