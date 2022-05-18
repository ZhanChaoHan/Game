// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SF.java

package com.crocro.wrp.bs;


// Referenced classes of package com.crocro.wrp.bs:
//            F

public class SF
    implements F
{

    public SF()
    {
    }

    public static int seq()
    {
        int i = seqNo;
        seqNo++;
        return i;
    }

    public static int seq(int newNo)
    {
        seqNo = newNo;
        return seq();
    }

    private static int seqNo = 0;

}
