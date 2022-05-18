// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmDtMap.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.wrp.WrpDw;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDtUntF, GmDt

public class GmDtMap
    implements F, GmDtUntF
{

    public GmDtMap(AppLoopSimWar al, int imgIdMapVw, int imgIdMapDtctn)
    {
        mAL = null;
        mAL = al;
        mImgIdMapVw = imgIdMapVw;
        mImgIdMapDtctn = imgIdMapDtctn;
        mMapW = mAL.mWD.getBufW(mImgIdMapVw);
        mMapH = mAL.mWD.getBufH(mImgIdMapVw);
        initMapDtctn();
    }

    public void initMapDtctn()
    {
        int mapPxls[][][] = mAL.mWD.getRGBs(mImgIdMapDtctn);
        int w = mAL.mWD.getBufW(mImgIdMapDtctn);
        int h = mAL.mWD.getBufH(mImgIdMapDtctn);
        mMapCanNot = new boolean[w][h];
        mMapCanAt = new boolean[w][h];
        mMapHole = new boolean[w][h];
        mMapWall = new boolean[w][h];
        for(int y = 0; y < h; y++)
        {
            for(int x = 0; x < w; x++)
            {
                int p[] = mapPxls[x][y];
                if(p[1] > 127)
                {
                    if(p[2] > 127)
                    {
                        int _tmp = p[3];
                    } else
                    if(p[3] > 127)
                        mMapCanAt[x][y] = true;
                    else
                        mMapCanNot[x][y] = true;
                } else
                if(p[2] > 127)
                {
                    if(p[3] <= 127)
                        mMapWall[x][y] = true;
                } else
                if(p[3] > 127)
                    mMapHole[x][y] = true;
            }

        }

        int mapRtUnt = 20;
        mRtW = mMapW / mapRtUnt;
        mRtH = mMapH / mapRtUnt;
        mMapRtBsWalk = new int[mRtW * mRtH];
        mMapRtBsFly = new int[mRtW * mRtH];
        mMapRtBsPss = new int[mRtW * mRtH];
        mMapRtBsFlyPss = new int[mRtW * mRtH];
        mMapLnOfFire = new int[mRtW * mRtH];
        mMapRtMyWalk = new int[mRtW * mRtH];
        mMapRtEneWalk = new int[mRtW * mRtH];
        mMapRtMyFly = new int[mRtW * mRtH];
        mMapRtEneFly = new int[mRtW * mRtH];
        mMapRtMyPss = new int[mRtW * mRtH];
        mMapRtEnePss = new int[mRtW * mRtH];
        mMapRtMyFlyPss = new int[mRtW * mRtH];
        mMapRtEneFlyPss = new int[mRtW * mRtH];
        mMapMyLnOfFire = new int[mRtW * mRtH];
        mMapEneLnOfFire = new int[mRtW * mRtH];
        mMapTmpLnOfFire = new int[mRtW * mRtH];
        w = mAL.mWD.getBufW(mImgIdMapDtctn);
        h = mAL.mWD.getBufH(mImgIdMapDtctn);
        int mapDtctnRtUnt = w / mRtW;
        for(int y = 0; y < h; y++)
        {
            for(int x = 0; x < w; x++)
            {
                int xRt = x / mapDtctnRtUnt;
                int yRt = y / mapDtctnRtUnt;
                if(mMapCanNot[x][y])
                {
                    mMapRtBsWalk[yRt * mRtW + xRt] = -133;
                    mMapRtBsFly[yRt * mRtW + xRt] = -133;
                    mMapLnOfFire[yRt * mRtW + xRt] = -133;
                }
                if(mMapCanAt[x][y])
                    mMapRtBsWalk[yRt * mRtW + xRt] = -133;
                if(mMapHole[x][y])
                {
                    mMapRtBsWalk[yRt * mRtW + xRt] = -133;
                    mMapRtBsPss[yRt * mRtW + xRt] = -133;
                }
                if(mMapWall[x][y])
                {
                    mMapRtBsWalk[yRt * mRtW + xRt] = -133;
                    mMapLnOfFire[yRt * mRtW + xRt] = -133;
                }
            }

        }

        mAL.mWD.delBuf(mImgIdMapDtctn);
    }

    public void clcMapRt()
    {
        int mapRtBsWalk[] = mMapRtBsWalk;
        int mapRtBsFly[] = mMapRtBsFly;
        int mapRtBsPss[] = mMapRtBsPss;
        int mapRtBsFlyPss[] = mMapRtBsFlyPss;
        int mapLnOfFire[] = mMapLnOfFire;
        int mapRtMyWalk[] = mMapRtMyWalk;
        int mapRtEneWalk[] = mMapRtEneWalk;
        int mapRtMyFly[] = mMapRtMyFly;
        int mapRtEneFly[] = mMapRtEneFly;
        int mapRtMyPss[] = mMapRtMyPss;
        int mapRtEnePss[] = mMapRtEnePss;
        int mapRtMyFlyPss[] = mMapRtMyFlyPss;
        int mapRtEneFlyPss[] = mMapRtEneFlyPss;
        int mapMyLnOfFire[] = mMapMyLnOfFire;
        int mapEneLnOfFire[] = mMapEneLnOfFire;
        int arrLen = mapRtBsWalk.length;
        System.arraycopy(mapRtBsWalk, 0, mapRtMyWalk, 0, arrLen);
        System.arraycopy(mapRtBsWalk, 0, mapRtEneWalk, 0, arrLen);
        System.arraycopy(mapRtBsFly, 0, mapRtMyFly, 0, arrLen);
        System.arraycopy(mapRtBsFly, 0, mapRtEneFly, 0, arrLen);
        System.arraycopy(mapRtBsPss, 0, mapRtMyPss, 0, arrLen);
        System.arraycopy(mapRtBsPss, 0, mapRtEnePss, 0, arrLen);
        System.arraycopy(mapRtBsFlyPss, 0, mapRtMyFlyPss, 0, arrLen);
        System.arraycopy(mapRtBsFlyPss, 0, mapRtEneFlyPss, 0, arrLen);
        System.arraycopy(mapLnOfFire, 0, mapMyLnOfFire, 0, arrLen);
        System.arraycopy(mapLnOfFire, 0, mapEneLnOfFire, 0, arrLen);
        int rtW = mRtW;
        int untStts[] = mAL.egV.lda.untStts;
        int untDat[] = mAL.egV.lda.untDat;
        int untI[][] = mAL.egV.mGD.mUntI;
        for(int i = 0; i < 132; i++)
            if(untStts[i * US_MAX + US_ENBL] != 0)
            {
                int xU = untStts[i * US_MAX + US_MV_X_NOW] / 20;
                int yU = untStts[i * US_MAX + US_MV_Y_NOW] / 20;
                int typ = untDat[i * 17 + 1];
                int mvPow = untI[typ][GmDt.UNT_I_MV];
                for(int y = -1; y <= 1; y++)
                {
                    int yRnd = yU + y;
                    if(yRnd >= 0 && yRnd < mRtH)
                    {
                        for(int x = -1; x <= 1; x++)
                        {
                            int xRnd = xU + x;
                            if(xRnd >= 0 && xRnd < mRtW)
                            {
                                int pos = (yU + y) * rtW + (xU + x);
                                if(mvPow == 0)
                                {
                                    mapRtMyWalk[pos] = -133;
                                    mapRtEneWalk[pos] = -133;
                                    mapRtMyFly[pos] = -133;
                                    mapRtEneFly[pos] = -133;
                                    mapRtMyPss[pos] = -133;
                                    mapRtEnePss[pos] = -133;
                                    mapRtMyFlyPss[pos] = -133;
                                    mapRtEneFlyPss[pos] = -133;
                                } else
                                if(i < 32)
                                {
                                    if(mapRtEneWalk[pos] != -133)
                                        mapRtEneWalk[pos] = -1;
                                    if(mapRtEneFly[pos] != -133)
                                        mapRtEneFly[pos] = -1;
                                    if(mapRtEnePss[pos] != -133)
                                        mapRtEnePss[pos] = -1;
                                    if(mapRtEneFlyPss[pos] != -133)
                                        mapRtEneFlyPss[pos] = -1;
                                } else
                                {
                                    if(mapRtMyWalk[pos] != -133)
                                        mapRtMyWalk[pos] = -1;
                                    if(mapRtMyFly[pos] != -133)
                                        mapRtMyFly[pos] = -1;
                                    if(mapRtMyPss[pos] != -133)
                                        mapRtMyPss[pos] = -1;
                                    if(mapRtMyFlyPss[pos] != -133)
                                        mapRtMyFlyPss[pos] = -1;
                                }
                                if(i < 32)
                                {
                                    if(mapEneLnOfFire[pos] != -133)
                                        mapEneLnOfFire[pos] = -1;
                                } else
                                if(mapMyLnOfFire[pos] != -133)
                                    mapMyLnOfFire[pos] = -1;
                            }
                        }

                    }
                }

            }

    }

    public void finish()
    {
        mAL = null;
        mMapCanNot = null;
        mMapCanAt = null;
        mMapHole = null;
        mMapWall = null;
        mMapRtBsWalk = null;
        mMapRtBsFly = null;
        mMapRtBsPss = null;
        mMapRtBsFlyPss = null;
        mMapLnOfFire = null;
        mMapRtMyWalk = null;
        mMapRtEneWalk = null;
        mMapRtMyFly = null;
        mMapRtEneFly = null;
        mMapRtMyPss = null;
        mMapRtEnePss = null;
        mMapRtMyFlyPss = null;
        mMapRtEneFlyPss = null;
        mMapMyLnOfFire = null;
        mMapEneLnOfFire = null;
    }

    private AppLoopSimWar mAL;
    private int mImgIdMapVw;
    private int mImgIdMapDtctn;
    public int mMapW;
    public int mMapH;
    public boolean mMapCanNot[][];
    public boolean mMapCanAt[][];
    public boolean mMapHole[][];
    public boolean mMapWall[][];
    public int mRtW;
    public int mRtH;
    public int mMapRtBsWalk[];
    public int mMapRtBsFly[];
    public int mMapRtBsPss[];
    public int mMapRtBsFlyPss[];
    public int mMapLnOfFire[];
    public int mMapRtMyWalk[];
    public int mMapRtEneWalk[];
    public int mMapRtMyFly[];
    public int mMapRtEneFly[];
    public int mMapRtMyPss[];
    public int mMapRtEnePss[];
    public int mMapRtMyFlyPss[];
    public int mMapRtEneFlyPss[];
    public int mMapMyLnOfFire[];
    public int mMapEneLnOfFire[];
    public int mMapTmpLnOfFire[];
    public static final int MAP_RT_BLOCK = -133;
    public static final int MAP_RT_LOF_UNT = -1;
    public static final int MAP_RT_DOT_SZ = 20;
}
