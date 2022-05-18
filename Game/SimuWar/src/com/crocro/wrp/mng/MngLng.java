// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngLng.java

package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;

// Referenced classes of package com.crocro.wrp.mng:
//            MngOpt

public class MngLng extends MngOpt
{

    public MngLng(AppLoop al, String pthPrts)
    {
        super(al, null);
        initPth(pthPrts);
    }

    public void initPth(String pthPrts)
    {
        String optPth = null;
        if(pthPrts == null || pthPrts.length() == 0)
            optPth = "res/lng/string.txt";
        else
            optPth = (new StringBuilder("res/lng/string_")).append(pthPrts).append(".txt").toString();
        initR(optPth);
    }

    static final String DIR = "res/lng/";
    static final String FNM_DFLT = "string";
    static final String FNM_EXT = ".txt";
}
