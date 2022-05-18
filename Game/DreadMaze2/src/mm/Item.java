// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java

package mm;


// Referenced classes of package mm:
//            CombatEntity, Maze, ItemEntity, Room

public class Item
{

    public Item(int typ, int difficulty)
    {
        type = typ;
        level = difficulty;
        if(type == 2)
        {
            maxHitPoints = difficulty / 3;
            hitPoints = 0;
            armor = difficulty / 2 + 1;
            power = difficulty / 5;
            dodge = 0.005F * (float)difficulty;
        } else
        if(type == 1)
        {
            maxHitPoints = difficulty / 5;
            hitPoints = 0;
            armor = difficulty / 5;
            power = difficulty;
            dodge = 0.001F * (float)difficulty;
        } else
        {
            maxHitPoints = difficulty / 3;
            hitPoints = difficulty * 2;
            armor = difficulty / 5;
            power = difficulty / 5;
            dodge = 0.0025F * (float)difficulty;
        }
    }

    public Item(int typ, int lev, int maxHP, int HP, int arm, int pow, float doge)
    {
        type = typ;
        level = lev;
        maxHitPoints = maxHP;
        hitPoints = HP;
        armor = arm;
        power = pow;
        dodge = doge;
    }

    public void pickUp(CombatEntity e)
    {
        e.modifyStats(maxHitPoints, hitPoints, armor, power, dodge);
    }

    public void drop(CombatEntity e)
    {
        e.modifyStats(-maxHitPoints, -hitPoints, -armor, -power, -dodge);
        Maze.getRoom(e.getX(), e.getY()).setItemEntity(new ItemEntity(e.getX(), e.getY(), this));
    }

    public int getType()
    {
        return type;
    }

    public int getLevel()
    {
        return level;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public int getMaxHitPoints()
    {
        return maxHitPoints;
    }

    public int getArmor()
    {
        return armor;
    }

    public int getPower()
    {
        return power;
    }

    public float getDodge()
    {
        return dodge;
    }

    public static final int TYPE_CONSUMABLE = 0;
    public static final int TYPE_WEAPON = 1;
    public static final int TYPE_ARMOR = 2;
    private int type;
    private int level;
    private int maxHitPoints;
    private int hitPoints;
    private int armor;
    private int power;
    private float dodge;
}
