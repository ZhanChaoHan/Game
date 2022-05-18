// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Chara.java

import java.awt.Component;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.HashMap;

public class Chara extends Component
    implements Viewer
{

    public Chara()
    {
        charaType = 1;
        sprite = new HashMap();
        aimAngle = 90;
        face = 1;
        storeHash = "f";
        storeCount = 0;
        alt = null;
    }

    public Chara(Image spriteSheet)
    {
        charaType = 1;
        sprite = new HashMap();
        aimAngle = 90;
        face = 1;
        storeHash = "f";
        storeCount = 0;
        alt = null;
        loadSprite(spriteSheet);
    }

    public void loadSprite(Image spriteSheet)
    {
        sprite.put("fr", new Sprite());
        ((Sprite)sprite.get("fr")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(0, 0, 32, 32)));
        sprite.put("w1r", new Sprite());
        ((Sprite)sprite.get("w1r")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(32, 0, 32, 32)));
        sprite.put("w2r", new Sprite());
        ((Sprite)sprite.get("w2r")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(64, 0, 32, 32)));
        sprite.put("fl", new Sprite());
        ((Sprite)sprite.get("fl")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(0, 32, 32, 32)));
        sprite.put("w1l", new Sprite());
        ((Sprite)sprite.get("w1l")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(32, 32, 32, 32)));
        sprite.put("w2l", new Sprite());
        ((Sprite)sprite.get("w2l")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(64, 32, 32, 32)));
        sprite.put("ar", new Sprite());
        ((Sprite)sprite.get("ar")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(0, 64, 32, 16)));
        ((Sprite)sprite.get("ar")).hingeX = -15;
        ((Sprite)sprite.get("ar")).hingeY = -8;
        sprite.put("al", new Sprite());
        ((Sprite)sprite.get("al")).img = createImage(new FilteredImageSource(spriteSheet.getSource(), new CropImageFilter(0, 80, 32, 16)));
        ((Sprite)sprite.get("al")).hingeX = -15;
        ((Sprite)sprite.get("al")).hingeY = -8;
    }

    public Image getSprite(String desc)
    {
        return ((Sprite)sprite.get(desc)).img;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isCamera()
    {
        return false;
    }

    private static final long serialVersionUID = 1L;
    int charaType;
    Image spriteSheet;
    Sprite currentSprite;
    HashMap sprite;
    int aimAngle;
    int lastAimAngle;
    int x;
    int y;
    float gx;
    float gy;
    int face;
    String storeHash;
    int storeCount;
    Projectile alt;
}
