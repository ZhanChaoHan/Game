// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Drg.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Clckbl

public class Drg extends Clckbl
{

    public Drg(AppLoop al, int x, int y, int w, int h, int crdntTyp)
    {
        super(al, x, y, w, h, crdntTyp, 2);
        mNrmlImgId = 1;
        mDrgImgId = 1;
    }

    public void setNrmlImg(int imgId)
    {
        mNrmlImgId = imgId;
    }

    public void setDrgImg(int imgId)
    {
        mDrgImgId = imgId;
    }

    public void dwAlwys()
    {
        if(!mClckNow)
            dwNrml();
        else
            dwDrg();
    }

    public void dwNrml()
    {
        mWD.dwImg(mNrmlImgId, mX, mY);
    }

    public void dwDrg()
    {
        mWD.dwImg(mDrgImgId, mX + (mDrgX - mClckX), mY + (mDrgY - mClckY));
    }

    public void dwAlwysEf()
    {
        dwClckEf();
    }

    public void actDrp()
    {
        mvPos(mDrpX - mClckX, mDrpY - mClckY);
        mTmEf = mAL.mTmNow;
        addActDrp();
    }

    public void addActDrp()
    {
    }

    public int mNrmlImgId;
    public int mDrgImgId;
}
