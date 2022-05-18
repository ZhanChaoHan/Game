// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Globals.java

package mm;


// Referenced classes of package mm:
//            PlayerEntity

public class Globals
{

    public Globals()
    {
    }

    public static void initialize()
    {
        createPlayers(1);
        currentTurn = 0;
        lastTurnUpdate = System.currentTimeMillis();
        turnLength = 100;
        sinLookups16 = new float[32];
        cosLookups16 = new float[32];
        for(int i = 0; i < sinLookups16.length; i++)
        {
            sinLookups16[i] = (float)Math.sin(0.19634954084936207D * (double)i);
            cosLookups16[i] = (float)Math.cos(0.19634954084936207D * (double)i);
        }

    }

    public static void createPlayers(int number)
    {
        players = new PlayerEntity[number];
        for(int i = 0; i < players.length; i++)
            players[i] = new PlayerEntity(i, 0);

    }

    public static PlayerEntity[] getPlayers()
    {
        return players;
    }

    public static void tick()
    {
        long now = System.currentTimeMillis();
        if(now - lastTurnUpdate >= (long)turnLength)
        {
            lastTurnUpdate = now;
            currentTurn++;
        }
    }

    public static int getCurrentTurn()
    {
        return currentTurn;
    }

    public static void setTurnLength(int length)
    {
        turnLength = length;
    }

    public static int getTurnLength()
    {
        return turnLength;
    }

    public static boolean tutorialModeIsOn()
    {
        return tutorialMode;
    }

    public static void setTutorialMode(boolean b)
    {
        tutorialMode = b;
    }

    public static float sinPiBy16(int i)
    {
        return sinLookups16[i];
    }

    public static float cosPiBy16(int i)
    {
        return cosLookups16[i];
    }

    public static void setScreenSize(int w, int h)
    {
        screenWidth = w;
        screenHeight = h;
    }

    public static int getScreenWidth()
    {
        return screenWidth;
    }

    public static int getScreenHeight()
    {
        return screenHeight;
    }

    private static PlayerEntity players[];
    private static int currentTurn;
    private static long lastTurnUpdate;
    private static int turnLength;
    private static boolean tutorialMode = true;
    private static float sinLookups16[];
    private static float cosLookups16[];
    private static int screenWidth;
    private static int screenHeight;

}
