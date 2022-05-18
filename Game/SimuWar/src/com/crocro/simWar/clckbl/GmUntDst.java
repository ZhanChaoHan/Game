// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmUntDst.java

package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.simWar.gm.*;
import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.clckbl.Drg;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.wrp.WrpDw;

public class GmUntDst extends Drg
    implements GmDtUntF
{

    public GmUntDst(AppLoopSimWar al, ScnGm sg, int id, int imgId)
    {
        super(al, 0, 0, 0, 0, 1);
        mAL = (AppLoopSimWar)super.mAL;
        mSG = sg;
        mGDUnt = mSG.mGDUnt;
        mUId = id;
        mNrmlImgId = imgId;
        mDrgImgId = imgId;
        mW = mWD.getBufW(imgId);
        mH = mWD.getBufH(imgId);
        if(mAL.egV.mGmRstrt)
        {
            int xNew = mGDUnt.getUS(mUId, US_MV_X_NOW) - mW / 2;
            int yNew = mGDUnt.getUS(mUId, US_MV_Y_NOW) - mH / 2;
            setPos(xNew, yNew);
        } else
        {
            int xNew = (mGDUnt.getUD(mUId, 2) * 20 + 10) - mW / 2;
            int yNew = (mGDUnt.getUD(mUId, 3) * 20 + 10) - mH / 2;
            setPos(xNew, yNew);
        }
    }

    public int chkClckIn(int x, int y)
    {
        if(mSG.mUSelTyp != 0)
        {
            return 0;
        } else
        {
            int escpX = mX;
            int escpY = mY;
            int escpW = mW;
            int escpH = mH;
            int w = (mW * 8) / 10;
            int h = (mH * 8) / 10;
            mX += (mW - w) / 2;
            mY += (mH - h) / 2;
            mW = w;
            mH = h;
            int res = super.chkClckIn(x, y);
            mX = escpX;
            mY = escpY;
            mW = escpW;
            mH = escpH;
            return res;
        }
    }

    public void doAlwys()
    {
        if(!chkEnbl())
        {
            return;
        } else
        {
            setPos(mGDUnt.getUS(mUId, US_DST_X_NOW) - mW / 2, mGDUnt.getUS(mUId, US_DST_Y_NOW) - mH / 2);
            super.doAlwys();
            return;
        }
    }

    public void dwNrml()
    {
        if(!chkEnbl())
            return;
        if(mX + mW * 2 >= 0 && mX - mW < mAL.mMC.getScrW() && mY + mH * 2 >= 0 && mY - mY < mAL.mMC.getScrH())
        {
            dwPre();
            if(mUId < 32)
                super.dwNrml();
            dwPst();
        }
        dwCnctLn();
    }

    public void dwDrg()
    {
        if(!chkEnbl())
            return;
        dwPre();
        if(mUId < 32)
            super.dwDrg();
        dwPst();
        dwCnctLn();
        mSG.setSelUnt(mUId);
    }

    public void dwPre()
    {
        int anmUnt = 12;
        int deg = (int)((mSG.mTmGm / (long)anmUnt) % 360L);
        mWD.setRtt(deg, mW / 2, mH / 2);
    }

    public void dwPst()
    {
        mWD.setRtt(0);
    }

    public void dwCnctLn()
    {
        GmDtUnt lGDUnt = mGDUnt;
        int uId = mUId;
        int orgnX = mAL.mMC.mOrgnX;
        int orgnY = mAL.mMC.mOrgnY;
        int lW = mW;
        int lH = mH;
        int scrW = mAL.mMC.getScrW();
        int scrH = mAL.mMC.getScrH();
        WrpDw lWD = mWD;
        int xStrt = lGDUnt.getUS(uId, US_MV_X_NOW) + orgnX;
        int yStrt = lGDUnt.getUS(uId, US_MV_Y_NOW) + orgnY;
        int xGoal = lGDUnt.getUS(uId, US_DST_X_NOW) + orgnX;
        int yGoal = lGDUnt.getUS(uId, US_DST_Y_NOW) + orgnY;
        if(mClckNow)
        {
            xGoal += mDrgX - mClckX;
            yGoal += mDrgY - mClckY;
        }
        if((xStrt + lW * 2 < 0 || xStrt - lW >= scrW || yStrt + lH * 2 < 0 || yStrt - lH >= scrH) && (xGoal + lW * 2 < 0 || xGoal - lW >= scrW || yGoal + lH * 2 < 0 || yGoal - lH >= scrH))
            return;
        if(uId < 32)
        {
            lWD.useCol(WrpDw.COL_RED);
            lWD.setStrk(4);
        } else
        {
            lWD.useCol(WrpDw.COL_YELLOW);
            lWD.setStrk(4);
        }
        lWD.dwLn(xStrt, yStrt, xGoal, yGoal);
        lWD.setStrk(1);
        if(uId >= 32)
            lWD.flOvl(xGoal - 6, yGoal - 6, 12, 12);
    }

    public void addActDrp()
    {
        int dstX = mXIn + mW / 2;
        int dstY = mYIn + mH / 2;
        if(dstX < 0)
            dstX = 0;
        if(dstY < 0)
            dstY = 0;
        if(dstX >= mSG.mGDMap.mMapW)
            dstX = mSG.mGDMap.mMapW - 1;
        if(dstY >= mSG.mGDMap.mMapH)
            dstY = mSG.mGDMap.mMapH - 1;
        mGDUnt.setIdMy(mUId);
        mGDUnt.setMyUS(US_DST_X_OLD, mGDUnt.getMyUS(US_DST_X_NOW));
        mGDUnt.setMyUS(US_DST_Y_OLD, mGDUnt.getMyUS(US_DST_Y_NOW));
        mGDUnt.setMyUS(US_DST_X_NOW, dstX);
        mGDUnt.setMyUS(US_DST_Y_NOW, dstY);
    }

    public boolean chkEnbl()
    {
        boolean res = true;
        if(mGDUnt.getEnbl(mUId) == 0)
        {
            res = false;
            unSelDrg();
            rsrvRmv();
        }
        return res;
    }

    public void finish()
    {
        super.finish();
        mSG = null;
        mGDUnt = null;
    }

    private AppLoopSimWar mAL;
    private ScnGm mSG;
    private GmDtUnt mGDUnt;
    public int mUId;
}
