// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Drw.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Clckbl

public class Drw extends Clckbl
{

    public Drw(AppLoop al, int x, int y, int w, int h, int crdntTyp)
    {
        super(al, x, y, w, h, crdntTyp, 0);
        mImgId = 1;
    }

    public void setImg(int imgId)
    {
        mImgId = imgId;
    }

    public void dwAlwys()
    {
        mWD.zmImg(mImgId, mX, mY, mW, mH);
    }

    public int mImgId;
}
