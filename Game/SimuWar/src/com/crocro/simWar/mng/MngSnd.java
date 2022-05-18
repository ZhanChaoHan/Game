// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngSnd.java

package com.crocro.simWar.mng;

import com.crocro.wrp.app.AppLoop;

public class MngSnd extends com.crocro.wrp.mng.MngSnd
{

    public MngSnd(AppLoop al)
    {
        super(al);
        setSePrms(SE_PRMS);
        setSeNms(SE_NMS);
        setSeIdMax(3);
    }

    public static final int SE_ID_DMG = 0;
    public static final int SE_ID_HEAL = 1;
    public static final int SE_ID_EFCT = 2;
    private static final int SE_ID_MAX = 3;
    private final int SE_PRMS[][] = {
        {
            1, 3
        }, {
            4, 3
        }, {
            7, 3
        }
    };
    private final String SE_NMS[] = {
        "seDmg", "seHeal", "seEfct"
    };
}
