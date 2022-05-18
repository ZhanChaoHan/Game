package com.crocro.simWar.clckbl;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.wrp.clckbl.Drg;

public class GmMap extends Drg
{

    public GmMap(AppLoopSimWar al, int x, int y, int w, int h, int imgId, int mrgnTop, 
            int mrgnBtm, int mrgnX)
    {
        super(al, x, y, w, h, 0);
        mMapX = 0;
        mMapY = 0;
        mMapW = 0;
        mMapH = 0;
        mDX = 0;
        mDY = 0;
        mRW = 0;
        mRH = 0;
        mRngXMin = 0;
        mRngXMax = 0;
        mRngYMin = 0;
        mRngYMax = 0;
        mAL = (AppLoopSimWar)super.mAL;
        mNrmlImgId = imgId;
        mMrgnTop = mrgnTop;
        mMrgnBtm = mrgnBtm;
        mMrgnX = mrgnX;
        mMapW = mWD.getBufW(mNrmlImgId);
        mMapH = mWD.getBufH(mNrmlImgId);
        mRngXMin = 0;
        mRngXMax = mMapW - mW;
        mDX = 0;
        mRW = mW;
        if(mMapW < mW)
        {
            mRngXMin = 0;
            mRngXMax = 0;
            mDX = (mW - mMapW) / 2;
            mRW = mMapW;
        }
        mRngYMin = 0;
        mRngYMax = mMapH - mH;
        mDY = 0;
        mRH = mH;
        if(mMapH < mH)
        {
            mRngYMin = 0;
            mRngYMax = 0;
            mDY = (mH - mMapH) / 2;
            mRH = mMapH;
        }
    }

    public void dwAlwys()
    {
        mWD.dwImg(mNrmlImgId, mX + mDX, mY + mDY, mMapX, mMapY, mRW, mRH);
    }

    public void setDrgPos(int x, int y)
    {
        mDrgX = x;
        mDrgY = y;
        mvMap();
    }

    public void actDrp()
    {
    }

    public void mvMap()
    {
        int mvX = mClckX - mDrgX;
        int mvY = mClckY - mDrgY;
        setMapPos(mMapX + mvX, mMapY + mvY);
    }

    public void setMapPos(int xNew, int yNew)
    {
        mMapX = xNew;
        mMapY = yNew;
        if(mMapX < mRngXMin - mMrgnX)
            mMapX = mRngXMin - mMrgnX;
        if(mMapX > mRngXMax + mMrgnX)
            mMapX = mRngXMax + mMrgnX;
        if(mMapY < mRngYMin - mMrgnTop)
            mMapY = mRngYMin - mMrgnTop;
        if(mMapY > mRngYMax + mMrgnBtm)
            mMapY = mRngYMax + mMrgnBtm;
        mAL.mMC.mOrgnX = mMapX * -1 + mDX;
        mAL.mMC.mOrgnY = mMapY * -1 + mDY;
        mClckX = mDrgX;
        mClckY = mDrgY;
    }

    public void finish()
    {
        super.finish();
        mAL = null;
    }

    protected AppLoopSimWar mAL;
    public int mMapX;
    public int mMapY;
    public int mMapW;
    public int mMapH;
    public int mDX;
    public int mDY;
    public int mRW;
    public int mRH;
    public int mRngXMin;
    public int mRngXMax;
    public int mRngYMin;
    public int mRngYMax;
    protected int mMrgnTop;
    protected int mMrgnBtm;
    protected int mMrgnX;
}
