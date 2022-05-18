// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   App.java

package com.crocro.wrp.dvc.pc;

import com.crocro.wrp.app.AppLoop;

// Referenced classes of package com.crocro.wrp.dvc.pc:
//            Frm

public class App
{

    public static void main()
    {
        main(null);
    }

    public static void main(String args[])
    {
        new App(args);
    }

    public App(String args[])
    {
        Frm mainFrm = null;
        if(args != null && args.length >= 1)
            mainFrm = new Frm(args[0]);
        else
            mainFrm = new Frm();
        init();
        mainFrm.initApp(mAL);
        Thread thrd = new Thread(mainFrm);
        thrd.start();
    }

    public void init()
    {
    }

    public AppLoop mAL;
}
