// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemEntity.java

package mm;


// Referenced classes of package mm:
//            Entity, Item, AnimationSet

public class ItemEntity extends Entity
{

    public ItemEntity(int x, int y, Item i)
    {
        super(x, y);
        item = i;
        createItemAnimationSet();
    }

    protected void createAnimationSet()
    {
    }

    private void createItemAnimationSet()
    {
        if(item.getType() == 0)
            animationSet = new AnimationSet("Stand", new AnimationSet.Animation("Item/Potion/", "", 1, 0, 0, 0));
        else
        if(item.getType() == 2)
            animationSet = new AnimationSet("Stand", new AnimationSet.Animation("Item/Armor/", "", 1, 0, 0, 0));
        else
        if(item.getType() == 1)
            animationSet = new AnimationSet("Stand", new AnimationSet.Animation("Item/Weapon/", "", 1, 0, 0, 0));
    }

    public Item getItem()
    {
        return item;
    }

    public void act(boolean flag)
    {
    }

    private Item item;
}
