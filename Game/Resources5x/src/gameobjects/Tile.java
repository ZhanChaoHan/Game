// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tile.java

package gameobjects;


// Referenced classes of package gameobjects:
//            GameObject, Side

public class Tile extends GameObject
{

    public Tile(String tempImage, int x, int y, int w, int h, int order)
    {
        super(x, y, w, h);
        this.order = order;
        image = (new StringBuilder("/images/")).append(tempImage).append(".png").toString();
        sides = new Side[4];
        sides[0] = new Side(x, y, x + w, y, 0);
        sides[1] = new Side(x + w, y, x + w, y + h, 1);
        sides[2] = new Side(x + w, y + h, x, y + h, 2);
        sides[3] = new Side(x, y + h, x, y, 3);
    }

    public String getImage()
    {
        return image;
    }

    public Side[] getTileSide()
    {
        return sides;
    }

    private String image;
    private Side sides[];
}
