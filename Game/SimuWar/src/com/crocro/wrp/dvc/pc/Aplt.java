// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Aplt.java

package com.crocro.wrp.dvc.pc;

import com.crocro.wrp.app.AppLoop;
import java.applet.Applet;

// Referenced classes of package com.crocro.wrp.dvc.pc:
//            Frm

public class Aplt extends Applet
{

    public Aplt()
    {
    }

    public void init()
    {
        Frm mainFrm = new Frm();
        mainFrm.initApp(mAL);
        mainFrm.setVisible(true);
        Thread thrd = new Thread(mainFrm);
        thrd.start();
    }

    public void start()
    {
    }

    public void stop()
    {
    }

    public void destroy()
    {
        mMainFrm.dispose();
        mMainFrm = null;
    }

    private static final long serialVersionUID = 1L;
    public AppLoop mAL;
    public Frm mMainFrm;
    public static boolean mDbg = true;

}
