// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Boss1.java

package gameobjects;

import collision.Collision;
import general.*;
import java.util.ArrayList;
import movement.Movement;

// Referenced classes of package gameobjects:
//            Enemy, Hero, Sprite

public class Boss1 extends Enemy
{

    public Boss1(String tempImage, int x, int y, int w, int h, int d, Movement m, 
            Collision c, AmmoHandler a, ExplosionHandler ex, int s, double velX, double velY, float angle, int score, int e, int nrImages, double animInterval)
    {
        super(tempImage, x, y, w, h, d, m, c, null, ex, s, velX, velY, angle, score, e, "1", nrImages, animInterval);
        animDestroyed = new Animation("boss_destroy", 6, 2.5D, 0, "png");
        animDiverse = new Animation((new StringBuilder(String.valueOf(tempImage))).append("_diverse").toString(), 2, 0.5D, -1, "png");
        destroyedSize = 200;
        if(tempImage.equals("enemy18"))
            destroyedPosYOffset = 5;
        states[0] = 1;
        energy = e;
        maxEnergy = e;
        ammoHandler = a;
        explosionHandler = ex;
        GameControl.bossLevel = true;
    }

    public void isDiverse()
    {
        if(explosionHandler.getExplCounter() == explosionHandler.getExplosions().size())
        {
            explosionHandler.setFinished(true);
            Pauser.setPausTime(50D);
            states[7] = 0;
            states[3] = 1;
            isDefeated = true;
            img = animDestroyed.getImage();
            Model.addSoundToList("/sounds/destroy3.wav");
        }
    }

    public void checkShooting(ArrayList ammos, Sprite hero)
    {
        if(states[3] == 0 && states[4] == 0 && ammoHandler != null && states[7] == 0 && Model.inGameArea(this, Hero.scrollPrevX, Hero.scrollPrevY) && (!GameControl.bossMode || energy > 0))
            ammoHandler.checkAmmoTime(ammos, this, hero);
    }

    public static int energy;
    public static int maxEnergy;
    public static int moveStage;
    public static boolean movementFinished;
    public static boolean isDefeated;
    private AmmoHandler ammoHandler;
}
