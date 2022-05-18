// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Btn.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Clckbl

public class Btn extends Clckbl
{

    public Btn(AppLoop al, int x, int y, int w, int h, int crdntTyp)
    {
        super(al, x, y, w, h, crdntTyp, 1);
        mBfrImgId = 1;
        mAftrImgId = 1;
        mStatBtn = 0;
    }

    public void setBfrClckImg(int imgId)
    {
        mBfrImgId = imgId;
    }

    public void setAftrClckImg(int imgId)
    {
        mAftrImgId = imgId;
    }

    public void dwAlwys()
    {
        if(mStatBtn == 0)
            dwBfr();
        else
            dwAftr();
    }

    public void dwAlwysEf()
    {
        if(mStatBtn == 0)
            dwBfrEf();
        else
            dwAftrEf();
    }

    public void dwBfr()
    {
        mWD.dwImg(mBfrImgId, mX, mY);
    }

    public void dwAftr()
    {
        mWD.dwImg(mAftrImgId, mX, mY);
    }

    public void dwBfrEf()
    {
    }

    public void dwAftrEf()
    {
        dwClckEf();
    }

    public void actClck()
    {
        mStatBtn = 1;
        mTmEf = mAL.mTmNow;
        addActClck();
    }

    public void addActClck()
    {
    }

    public int mBfrImgId;
    public int mAftrImgId;
    public static final int CLCK_BFR = 0;
    public static final int CLCK_AFTR = 1;
    public int mStatBtn;
}
