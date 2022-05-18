// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlayerEntity.java

package mm;


// Referenced classes of package mm:
//            CombatEntity, AnimationSet, Maze, SinuosityPanel, 
//            Room, EnemyEntity, Item

public class PlayerEntity extends CombatEntity
{

    public PlayerEntity(int number, int xp)
    {
        super(0, 0, xp / 1000, 15, 0, 3, 0.0F);
        for(int i = 0; i < level; i++)
            levelUpWithNoEffects();

        playerNumber = number;
        experience = xp;
        isDead = false;
    }

    protected void createAnimationSet()
    {
        animationSet = new AnimationSet("Stand", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/Stand/").toString(), "", 1, 0, 0, 0));
        animationSet.addAnimation("Move", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/Walk/").toString(), "Player/Walk/", 2, 1, 0, 1));
        animationSet.addAnimation("GetItem", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/GetItem/").toString(), "Player/GetItem/", 1, 0, 5, 5));
        animationSet.addAnimation("TakeDamage", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/TakeDamage/").toString(), "Player/TakeDamage/", 1, 0, 2, 15));
        animationSet.addAnimation("Attack", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/Attack/").toString(), "Player/Attack/", 1, 0, 3, 10));
        animationSet.addAnimation("Die", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/Die/").toString(), "Player/Die/", 1, 0, 0x7fffffff, 99));
        animationSet.addAnimation("LevelUp", new AnimationSet.Animation((new StringBuilder("Player/Player")).append(playerNumber).append("/GetItem/").toString(), "Player/LevelUp/", 1, 0, 5, 99));
    }

    public void levelUp()
    {
        levelUpWithNoEffects();
        animationSet.setAnimation("LevelUp");
        int f = Maze.getGridSizeWithWalls();
        SinuosityPanel.createOutput(0, 255, 0, "LEVEL UP!", "", "", x * f, y * f, 10, 20, 20);
    }

    private void levelUpWithNoEffects()
    {
        super.levelUp();
        maxHitPoints += 10;
        hitPoints = maxHitPoints;
        defense++;
        power += 2;
        dodge += 0.01F;
    }

    public void addExperience(int change)
    {
        experience += change;
        if(experience / 1000 > level)
            levelUp();
        int f = Maze.getGridSizeWithWalls();
        int xPos = (x * f + f / 2) - (int)(Math.random() * 10D);
        int yPos = (y * f + f / 2) - (int)(Math.random() * 10D);
        SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(change).append("XP").toString(), "", "", xPos, yPos, 10, 50, 16);
    }

    public int getExperience()
    {
        return experience;
    }

    public void die()
    {
        isDead = true;
        Maze.getRoom(x, y).setCombatEntity(null);
        animationSet.setAnimation("Die");
    }

    public void attack(CombatEntity defender)
    {
        if(!(defender instanceof EnemyEntity))
            return;
        super.attack(defender);
        target = defender;
        if(defender.getHitPoints() <= 0)
        {
            addExperience((int)((double)(50 * defender.getLevel()) / ((double)level + 1.0D)));
            target = null;
        }
    }

    public void act(boolean newTurn)
    {
        super.act(newTurn);
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void revive()
    {
        isDead = false;
        animationSet.finishAnimation();
    }

    public CombatEntity getTarget()
    {
        return target;
    }

    public void pickUp(Item item)
    {
        if(item == null)
            return;
        int f = Maze.getGridSizeWithWalls();
        int xPos = (x * f + f / 2) - (int)(Math.random() * 10D);
        int yPos = (y * f + f / 2) - (int)(Math.random() * 10D);
        if(item.getType() == 0)
        {
            if(item.getHitPoints() > 0)
            {
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(item.getHitPoints()).append(" Life").toString(), "", "", xPos, yPos, 20, 50, 14);
                yPos += 15;
            }
            if(item.getMaxHitPoints() > 0)
            {
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(item.getMaxHitPoints()).append(" MaxLife").toString(), "", "", xPos, yPos, 20, 50, 14);
                yPos += 15;
            }
            if(item.getPower() > 0)
            {
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(item.getPower()).append(" Power").toString(), "", "", xPos, yPos, 20, 50, 14);
                yPos += 15;
            }
            if(item.getArmor() > 0)
            {
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(item.getArmor()).append(" Defense").toString(), "", "", xPos, yPos, 20, 50, 14);
                yPos += 15;
            }
            if(item.getDodge() > 0.0F)
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("+")).append(100F * item.getDodge()).append("% Dodge").toString(), "", "", xPos, yPos, 20, 50, 14);
        } else
        if(item.getType() == 1)
        {
            if(weapon == null || weapon != null && item.getLevel() > weapon.getLevel())
            {
                SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("Level ")).append(item.getLevel()).toString(), "", "", xPos, yPos, 15, 50, 14);
                SinuosityPanel.createOutput(0, 255, 0, "Sword", "", "", xPos, yPos + 15, 15, 50, 14);
            }
        } else
        if(item.getType() == 2 && (armor == null || armor != null && item.getLevel() > armor.getLevel()))
        {
            SinuosityPanel.createOutput(0, 255, 0, (new StringBuilder("Level ")).append(item.getLevel()).toString(), "", "", xPos, yPos, 15, 50, 14);
            SinuosityPanel.createOutput(0, 255, 0, "Shield", "", "", xPos, yPos + 15, 15, 50, 14);
        }
        super.pickUp(item);
    }

    private int experience;
    private int playerNumber;
    private boolean isDead;
    private CombatEntity target;
    public static final String IMAGE = "player0walk.png";
}
