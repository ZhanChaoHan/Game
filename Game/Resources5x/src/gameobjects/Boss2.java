// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Boss2.java

package gameobjects;

import collision.Collision;
import general.*;
import java.util.ArrayList;
import movement.Movement;

// Referenced classes of package gameobjects:
//            Enemy, Hero, Boss1, Sprite

public class Boss2 extends Enemy
{

    public Boss2(String tempImage, int x, int y, int w, int h, int d, Movement m, 
            Collision c, AmmoHandlerBoss a, int s, double velX, double velY, 
            float angle, int e, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, d, m, c, null, null, s, velX, velY, angle, e, 0, "1", nrImages, animInterval);
        animDiverse = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_diverse").toString(), 2, 0.5D, -1, "png");
        states[0] = 1;
        ammoHandler = a;
    }

    public void isDiverse()
    {
        if(explosionHandler.getExplCounter() == explosionHandler.getExplosions().size())
        {
            states[7] = 0;
            states[4] = 1;
        }
    }

    public void checkShooting(ArrayList ammos, Sprite hero)
    {
        if(states[3] == 0 && states[4] == 0 && ammoHandler != null && states[7] == 0 && Model.inGameArea(this, Hero.scrollPrevX, Hero.scrollPrevY) && (!GameControl.bossMode || Boss1.energy > 0))
            ammoHandler.checkAmmoTime(ammos, this, hero);
    }

    private AmmoHandlerBoss ammoHandler;
    public static boolean movementFinished;
}
