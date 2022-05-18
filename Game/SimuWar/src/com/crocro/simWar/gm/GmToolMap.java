// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmToolMap.java

package com.crocro.simWar.gm;

import com.crocro.simWar.scn.ScnGm;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.utl.UtlTool;
import java.io.PrintStream;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDtUntF, GmDtMap, GmDtUnt, GmDt

public class GmToolMap
    implements F, GmDtUntF
{

    public GmToolMap(ScnGm sg, GmDt gd, GmDtUnt gdUnt, GmDtMap gdMap)
    {
        mRcyclPos = new int[2];
        mSG = sg;
        mGD = gd;
        mGDUnt = gdUnt;
        mGDMap = gdMap;
        mRtW = mGDMap.mRtW;
        mRtH = mGDMap.mRtH;
        mStckPos = new int[mRtW * mRtH + 1][2];
        mRcyclShrtPos = new int[mRtW * mRtH + 2][2];
        mMapRt = new int[mRtW * mRtH];
    }

    public boolean updtRt(int uId, int rtPos[][])
    {
        int xDst = mGDUnt.getUS(uId, US_DST_X_NOW);
        int yDst = mGDUnt.getUS(uId, US_DST_Y_NOW);
        int xNow = mGDUnt.getUS(uId, US_MV_X_NOW);
        int yNow = mGDUnt.getUS(uId, US_MV_Y_NOW);
        clcEneDst(mRcyclPos, uId, xNow, yNow, xDst, yDst);
        xDst = mRcyclPos[0];
        yDst = mRcyclPos[1];
        getMapRt(mMapRt, uId);
        getShrtRout(mRcyclShrtPos, mMapRt, xNow, yNow, xDst, yDst);
        if(mRcyclShrtPos[mRcyclShrtPos.length - 1][0] <= 1)
        {
            getMinDstncPos(mRcyclPos, mMapRt, xNow, yNow, xDst, yDst);
            if(mRcyclPos[0] == -1)
            {
                mGDUnt.doRst(uId, true);
                return false;
            }
            xDst = mRcyclPos[0];
            yDst = mRcyclPos[1];
            getShrtRout(mRcyclShrtPos, mMapRt, xNow, yNow, xDst, yDst);
        }
        mkMvDots(rtPos, mRcyclShrtPos);
        mGDUnt.doRst(uId, false);
        return true;
    }

    public void clcEneDst(int resPos[], int uId, int xNow, int yNow, int xDst, int yDst)
    {
        resPos[0] = xDst;
        resPos[1] = yDst;
        if(uId < 32)
            return;
        GmDtUnt lGDUnt = mGDUnt;
        boolean dstFixPtrl = false;
        lGDUnt.setIdMy(uId);
        int fllwId = lGDUnt.getMyUD(16);
        if(fllwId != -1 && lGDUnt.getUS(fllwId, US_ENBL) == 1)
        {
            int xOrzn = lGDUnt.getUS(fllwId, US_MV_X_NOW);
            int yOrzn = lGDUnt.getUS(fllwId, US_MV_Y_NOW);
            int angl = UtlTool.clcDegTop(xOrzn, yOrzn, xNow, yNow);
            int xFllw = xOrzn + (int)(COS[(angl + 270) % 360] * 20F * 2.0F);
            int yFllw = yOrzn + (int)(SIN[(angl + 270) % 360] * 20F * 2.0F);
            if(xFllw < 0)
                xFllw = 20;
            if(yFllw < 0)
                yFllw = 20;
            if(xFllw >= mGDMap.mMapW)
                xFllw = mGDMap.mMapW - 20;
            if(yFllw >= mGDMap.mMapH)
                yFllw = mGDMap.mMapH - 20;
            if((xFllw <= xNow ? xNow - xFllw : xFllw - xNow) + (yFllw <= yNow ? yNow - yFllw : yFllw - yNow) >= 20)
            {
                lGDUnt.setMyUS(US_DST_X_OLD, xNow);
                lGDUnt.setMyUS(US_DST_Y_OLD, yNow);
                xDst = xFllw;
                yDst = yFllw;
                lGDUnt.setMyUS(US_DST_X_NOW, xDst);
                lGDUnt.setMyUS(US_DST_Y_NOW, yDst);
            } else
            {
                xDst += (int)(120D * Math.cos((6.2831853071795862D * (double)(mSG.mTmGm % 360L)) / 360D));
                yDst += (int)(120D * Math.sin((6.2831853071795862D * (double)(mSG.mTmGm % 360L)) / 360D));
                if(xDst < 0)
                    xDst = 20;
                if(yDst < 0)
                    yDst = 20;
                if(xDst >= mGDMap.mMapW)
                    xDst = mGDMap.mMapW - 20;
                if(yDst >= mGDMap.mMapH)
                    yDst = mGDMap.mMapH - 20;
            }
            resPos[0] = xDst;
            resPos[1] = yDst;
            return;
        }
        if(lGDUnt.getUD(uId, 6) != -1)
        {
            dstFixPtrl = true;
            int ptrlTgt = lGDUnt.getMyUS(US_PTRL_TGT);
            int xPtrl = lGDUnt.getMyUD(6 + ptrlTgt * 2);
            int yPtrl = lGDUnt.getMyUD(7 + ptrlTgt * 2);
            if(xPtrl < 0 || xPtrl >= mRtW || yPtrl < 0 || yPtrl >= mRtH)
            {
                System.out.println((new StringBuilder("!!! Data is illegal !!! xPtrl ")).append(xPtrl).append(" yPtrl ").append(yPtrl).toString());
                if(xPtrl < 0)
                    xPtrl = 0;
                if(xPtrl >= mRtW)
                    xPtrl = mRtW - 1;
                if(yPtrl < 0)
                    yPtrl = 0;
                if(yPtrl >= mRtH)
                    yPtrl = mRtH - 1;
            }
            if(xNow == xPtrl * 20 + 10 && yNow == yPtrl * 20 + 10)
            {
                if(++ptrlTgt >= 4 || lGDUnt.getMyUD(6 + ptrlTgt * 2) == -1)
                    ptrlTgt = 0;
                xPtrl = lGDUnt.getMyUD(6 + ptrlTgt * 2);
                yPtrl = lGDUnt.getMyUD(7 + ptrlTgt * 2);
                lGDUnt.setMyUS(US_PTRL_TGT, ptrlTgt);
                lGDUnt.setMyUS(US_MV_RNG_OVR, 0);
            }
            lGDUnt.setMyUS(US_DST_X_OLD, lGDUnt.getMyUS(US_DST_X_NOW));
            lGDUnt.setMyUS(US_DST_Y_OLD, lGDUnt.getMyUS(US_DST_Y_NOW));
            xDst = xPtrl * 20 + 10;
            yDst = yPtrl * 20 + 10;
            lGDUnt.setMyUS(US_DST_X_NOW, xDst);
            lGDUnt.setMyUS(US_DST_Y_NOW, yDst);
        }
        int tmpNo;
        int dstncRngMv1 = (xNow <= (tmpNo = lGDUnt.getMyUS(US_DST_X_NOW)) ? tmpNo - xNow : xNow - tmpNo) + (yNow <= (tmpNo = lGDUnt.getMyUS(US_DST_Y_NOW)) ? tmpNo - yNow : yNow - tmpNo);
        int dstncRngMv2 = (xNow <= (tmpNo = lGDUnt.getMyUS(US_DST_X_OLD)) ? tmpNo - xNow : xNow - tmpNo) + (yNow <= (tmpNo = lGDUnt.getMyUS(US_DST_Y_OLD)) ? tmpNo - yNow : yNow - tmpNo);
        int rngMv = lGDUnt.getUD(uId, 5) * 20;
        if(dstFixPtrl && dstncRngMv1 > rngMv && dstncRngMv2 > rngMv)
            lGDUnt.setMyUS(US_MV_RNG_OVR, 1);
        if(dstFixPtrl && lGDUnt.getMyUS(US_MV_RNG_OVR) == 1)
        {
            resPos[0] = xDst;
            resPos[1] = yDst;
            return;
        }
        int xRngSrch = -1;
        int yRngSrch = -1;
        int idRngSrch = -1;
        int minHpRngSrch = 1000;
        int rngSrch = lGDUnt.getMyUD(4) * 20;
        for(int i = 0; i < 32; i++)
            if(lGDUnt.getUS(i, US_ENBL) == 1)
            {
                int xTmp = lGDUnt.getUS(i, US_MV_X_NOW);
                int yTmp = lGDUnt.getUS(i, US_MV_Y_NOW);
                int dstncRngSrch = (xTmp <= xNow ? xNow - xTmp : xTmp - xNow) + (yTmp <= yNow ? yNow - yTmp : yTmp - yNow);
                int hp = lGDUnt.getUS(i, US_HP);
                if(dstncRngSrch <= rngSrch && hp < minHpRngSrch)
                {
                    minHpRngSrch = hp;
                    xRngSrch = xTmp;
                    yRngSrch = yTmp;
                    idRngSrch = i;
                }
            }

        if(idRngSrch != -1)
        {
            int dst = ((tmpNo = lGDUnt.getMyUS(US_MV_X_NOW)) <= xRngSrch ? xRngSrch - tmpNo : tmpNo - xRngSrch) + ((tmpNo = lGDUnt.getMyUS(US_MV_Y_NOW)) <= yRngSrch ? yRngSrch - tmpNo : tmpNo - yRngSrch);
            int atRng = mGD.mSklI[lGDUnt.getMySkl()][GmDt.SKL_I_RNG];
            if(dst >= atRng)
            {
                lGDUnt.setMyUS(US_DST_X_OLD, lGDUnt.getMyUS(US_DST_X_NOW));
                lGDUnt.setMyUS(US_DST_Y_OLD, lGDUnt.getMyUS(US_DST_Y_NOW));
                xDst = xRngSrch;
                yDst = yRngSrch;
                lGDUnt.setMyUS(US_DST_X_NOW, xDst);
                lGDUnt.setMyUS(US_DST_Y_NOW, yDst);
            }
        }
        resPos[0] = xDst;
        resPos[1] = yDst;
    }

    public void getMapRt(int mapRt[], int uId)
    {
        boolean fly = mGD.mUntI[mGDUnt.getTyp(uId)][GmDt.UNT_I_FLY] == 1;
        boolean pss = mGD.mUntI[mGDUnt.getTyp(uId)][GmDt.UNT_I_PSS] == 1;
        int mapNowUse[] = (int[])null;
        int xMy = mGDUnt.getUS(uId, US_MV_X_NOW) / 20;
        int yMy = mGDUnt.getUS(uId, US_MV_Y_NOW) / 20;
        int exFly = mGDUnt.getUS(uId, US_EX_FLY_PW);
        int exPss = mGDUnt.getUS(uId, US_EX_PSS_PW);
        if(exFly > 0)
            fly = true;
        if(exFly < 0)
            fly = false;
        if(exPss > 0)
            pss = true;
        if(exPss < 0)
            pss = false;
        if(fly && !pss)
        {
            if(uId < 32)
                mapNowUse = mGDMap.mMapRtMyFly;
            else
                mapNowUse = mGDMap.mMapRtEneFly;
        } else
        if(!fly && pss)
        {
            if(uId < 32)
                mapNowUse = mGDMap.mMapRtMyPss;
            else
                mapNowUse = mGDMap.mMapRtEnePss;
        } else
        if(fly && pss)
        {
            if(uId < 32)
                mapNowUse = mGDMap.mMapRtMyFlyPss;
            else
                mapNowUse = mGDMap.mMapRtEneFlyPss;
        } else
        if(uId < 32)
            mapNowUse = mGDMap.mMapRtMyWalk;
        else
            mapNowUse = mGDMap.mMapRtEneWalk;
        System.arraycopy(mapNowUse, 0, mapRt, 0, mapRt.length);
        for(int y = -1; y <= 1; y++)
            if(yMy + y >= 0 && yMy + y < mRtH)
            {
                for(int x = -1; x <= 1; x++)
                    if(xMy + x >= 0 && xMy + x < mRtW && mapRt[(yMy + y) * mRtW + (xMy + x)] != -133)
                        mapRt[(yMy + y) * mRtW + (xMy + x)] = 0;

            }

    }

    public void getShrtRout(int shrtPos[][], int map[], int xStrt, int yStrt, int xGoal, int yGoal)
    {
        int arrSz = 0;
        shrtPos[arrSz][0] = xStrt;
        shrtPos[arrSz][1] = yStrt;
        arrSz++;
        int xStrtS = xStrt / 20;
        int yStrtS = yStrt / 20;
        int xGoalS = xGoal / 20;
        int yGoalS = yGoal / 20;
        if(xStrtS == xGoalS && yStrtS == yGoalS)
        {
            if(xStrt != xGoal || yStrt != yGoal)
            {
                shrtPos[arrSz][0] = xGoal;
                shrtPos[arrSz][1] = yGoal;
                arrSz++;
            }
            shrtPos[shrtPos.length - 1][0] = arrSz;
            return;
        }
        clcMvStp(map, xStrtS, yStrtS, xGoal, yGoal);
        if(map[yGoalS * mRtW + xGoalS] <= 0)
        {
            shrtPos[shrtPos.length - 1][0] = arrSz;
            return;
        }
        int x;
        int xTmp = x = xGoalS;
        int y;
        int yTmp = y = yGoalS;
        do
        {
            int pnt = map[y * mRtW + x];
            if(x > 0)
            {
                int pos = y * mRtW + (x - 1);
                if(map[pos] > 0 && map[pos] < pnt)
                {
                    pnt = map[pos];
                    xTmp = x - 1;
                    yTmp = y;
                }
            }
            if(x + 1 < mRtW)
            {
                int pos = y * mRtW + (x + 1);
                if(map[pos] > 0 && map[pos] < pnt)
                {
                    pnt = map[pos];
                    xTmp = x + 1;
                    yTmp = y;
                }
            }
            if(y > 0)
            {
                int pos = (y - 1) * mRtW + x;
                if(map[pos] > 0 && map[pos] < pnt)
                {
                    pnt = map[pos];
                    xTmp = x;
                    yTmp = y - 1;
                }
            }
            if(y + 1 < mRtH)
            {
                int pos = (y + 1) * mRtW + x;
                if(map[pos] > 0 && map[pos] < pnt)
                {
                    pnt = map[pos];
                    xTmp = x;
                    yTmp = y + 1;
                }
            }
            if(pnt == 1)
                break;
            shrtPos[arrSz][0] = xTmp * 20 + 10;
            shrtPos[arrSz][1] = yTmp * 20 + 10;
            arrSz++;
            x = xTmp;
            y = yTmp;
        } while(true);
        for(int i = 1; i < arrSz; i++)
        {
            int rvrs = arrSz - i;
            if(rvrs <= i)
                break;
            xTmp = shrtPos[i][0];
            yTmp = shrtPos[i][1];
            shrtPos[i][0] = shrtPos[rvrs][0];
            shrtPos[i][1] = shrtPos[rvrs][1];
            shrtPos[rvrs][0] = xTmp;
            shrtPos[rvrs][1] = yTmp;
        }

        shrtPos[arrSz][0] = xGoal;
        shrtPos[arrSz][1] = yGoal;
        arrSz++;
        shrtPos[shrtPos.length - 1][0] = arrSz;
    }

    public void clcMvStp(int map[], int xStrtS, int yStrtS, int xGoal, int yGoal)
    {
        int rtW = mRtW;
        int rtH = mRtH;
        int stckArr[][] = mStckPos;
        map[yStrtS * rtW + xStrtS] = 1;
        int stckSz = 0;
        int stckP = 0;
        stckArr[stckSz][0] = xStrtS;
        stckArr[stckSz][1] = yStrtS;
        for(stckSz++; stckP < stckSz;)
        {
            int x = stckArr[stckP][0];
            int y = stckArr[stckP][1];
            stckP++;
            int pnt = map[y * rtW + x] + 1;
            if(x == xGoal && y == yGoal)
                break;
            if(x > 0)
            {
                int pos = y * rtW + (x - 1);
                if(map[pos] == 0)
                {
                    map[pos] = pnt;
                    stckArr[stckSz][0] = x - 1;
                    stckArr[stckSz][1] = y;
                    stckSz++;
                }
            }
            if(x + 1 < rtW)
            {
                int pos = y * rtW + (x + 1);
                if(map[pos] == 0)
                {
                    map[pos] = pnt;
                    stckArr[stckSz][0] = x + 1;
                    stckArr[stckSz][1] = y;
                    stckSz++;
                }
            }
            if(y > 0)
            {
                int pos = (y - 1) * rtW + x;
                if(map[pos] == 0)
                {
                    map[pos] = pnt;
                    stckArr[stckSz][0] = x;
                    stckArr[stckSz][1] = y - 1;
                    stckSz++;
                }
            }
            if(y + 1 < rtH)
            {
                int pos = (y + 1) * rtW + x;
                if(map[pos] == 0)
                {
                    map[pos] = pnt;
                    stckArr[stckSz][0] = x;
                    stckArr[stckSz][1] = y + 1;
                    stckSz++;
                }
            }
        }

    }

    public void getMinDstncPos(int resPos[], int map[], int xStrt, int yStrt, int xGoal, int yGoal)
    {
        int mapLen = map.length;
        int dstncMin = mapLen * mRtW * 2;
        int xDstMinS = -1;
        int yDstMinS = -1;
        int xStrtS = xStrt / 20;
        int yStrtS = yStrt / 20;
        int xGoalS = xGoal / 20;
        int yGoalS = yGoal / 20;
        for(int i = 0; i < mapLen; i++)
            if(map[i] > 0)
            {
                int xS = i % mRtW;
                int yS = i / mRtW;
                int xDif = xGoalS - xS;
                int yDif = yGoalS - yS;
                int dstncNow = xDif * xDif + yDif * yDif;
                if(dstncNow < dstncMin)
                {
                    dstncMin = dstncNow;
                    xDstMinS = xS;
                    yDstMinS = yS;
                }
            }

        if(xDstMinS == -1 || xDstMinS == xStrtS && yDstMinS == yStrtS)
        {
            resPos[0] = -1;
            resPos[1] = -1;
        } else
        {
            resPos[0] = xDstMinS * 20 + 10;
            resPos[1] = yDstMinS * 20 + 10;
        }
    }

    public static void mkMvDots(int rtPos[][], int shrtRt[][])
    {
        int rtPosCnt = 0;
        int shrtRtSz = shrtRt[shrtRt.length - 1][0];
label0:
        for(int i = 1; i < shrtRtSz; i++)
        {
            int xStrt = shrtRt[i - 1][0];
            int yStrt = shrtRt[i - 1][1];
            int xGoal = shrtRt[i][0];
            int yGoal = shrtRt[i][1];
            int stpSz = (xStrt <= xGoal ? xGoal - xStrt : xStrt - xGoal) + (yStrt <= yGoal ? yGoal - yStrt : yStrt - yGoal) + 1;
            int stpSzBs = stpSz - 1;
            for(int j = i != 0 ? 1 : 0; j < stpSzBs; j++)
            {
                if(rtPosCnt >= rtPos.length)
                    break label0;
                rtPos[rtPosCnt][0] = xStrt + ((xGoal - xStrt) * j) / stpSzBs;
                rtPos[rtPosCnt][1] = yStrt + ((yGoal - yStrt) * j) / stpSzBs;
                rtPosCnt++;
            }

            if(rtPosCnt >= rtPos.length)
                break;
            rtPos[rtPosCnt][0] = xGoal;
            rtPos[rtPosCnt][1] = yGoal;
            rtPosCnt++;
        }

        if(rtPosCnt < rtPos.length)
            for(; rtPosCnt < rtPos.length; rtPosCnt++)
            {
                rtPos[rtPosCnt][0] = shrtRt[shrtRtSz - 1][0];
                rtPos[rtPosCnt][1] = shrtRt[shrtRtSz - 1][1];
            }

    }

    public boolean chkLnOfFireAt(int map[], int x1, int y1, int x2, int y2)
    {
        System.arraycopy(map, 0, mGDMap.mMapTmpLnOfFire, 0, mGDMap.mMapTmpLnOfFire.length);
        map = mGDMap.mMapTmpLnOfFire;
        int rtW = mRtW;
        int rtH = mRtH;
        for(int y = -1; y <= 1; y++)
            if(y2 + y >= 0 && y2 + y < rtH)
            {
                for(int x = -1; x <= 1; x++)
                    if(x2 + x >= 0 && x2 + x < rtW)
                        map[(y2 + y) * rtW + (x2 + x)] = 0;

            }

        return chkLnOfFire(map, x1, y1, x2, y2);
    }

    public boolean chkLnOfFire(int map[], int x1, int y1, int x2, int y2)
    {
        boolean res = false;
        int counter = 0;
        int dstX = x2 - x1;
        int addX;
        if(dstX < 0)
        {
            dstX *= -1;
            addX = -1;
        } else
        {
            addX = 1;
        }
        int dstY = y2 - y1;
        int addY;
        if(dstY < 0)
        {
            dstY *= -1;
            addY = -1;
        } else
        {
            addY = 1;
        }
        if(dstX + dstY <= 1)
            return true;
        int px = x1;
        int py = y1;
        int w = mRtW;
        int h = mRtH;
        if(dstX > dstY)
        {
            for(int i = 0; i < dstX; i++)
            {
                if(px > 0 && px < w && py > 0 && py < h)
                {
                    if((px != x1 || py != y1) && (px != x2 || py != y2) && map[py * w + px] != 0)
                        return res;
                    counter += dstY;
                    if(counter > dstX)
                    {
                        counter -= dstX;
                        py += addY;
                    }
                }
                px += addX;
            }

        } else
        {
            for(int i = 0; i < dstY; i++)
            {
                if(px > 0 && px < w && py > 0 && py < h)
                {
                    if((px != x1 || py != y1) && (px != x2 || py != y2) && map[py * w + px] != 0)
                        return res;
                    counter += dstX;
                    if(counter > dstY)
                    {
                        counter -= dstY;
                        px += addX;
                    }
                }
                py += addY;
            }

        }
        res = true;
        return res;
    }

    public void finish()
    {
        mSG = null;
        mGD = null;
        mGDUnt = null;
        mGDMap = null;
        mStckPos = null;
        mRcyclPos = null;
        mRcyclShrtPos = null;
        mMapRt = null;
    }

    private ScnGm mSG;
    private GmDt mGD;
    private GmDtUnt mGDUnt;
    private GmDtMap mGDMap;
    private int mRtW;
    private int mRtH;
    private int mStckPos[][];
    private int mRcyclPos[];
    private int mRcyclShrtPos[][];
    private int mMapRt[];
}
