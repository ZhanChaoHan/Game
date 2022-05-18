// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtlDwEf.java

package com.crocro.wrp.utl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.wrp.WrpDw;
import java.util.Random;

public class UtlDwEf
    implements F
{

    public UtlDwEf()
    {
    }

    public static void dwFireWrks(AppLoop al, long bsTm)
    {
        int scrW = al.mMC.getScrW();
        int scrH = al.mMC.getScrH();
        WrpDw wd = al.mWD;
        int elps = (int)(bsTm % 1750L);
        AppLoop.RNDM.setSeed(bsTm / 1750L);
        int bsX = 30 + AppLoop.RNDM.nextInt(scrW - 60);
        int bsY = AppLoop.RNDM.nextInt(scrH / 2) + 30;
        int bsH = scrH - bsY;
        int col[] = FIRE_COL[AppLoop.RNDM.nextInt(FIRE_COL.length)];
        if(elps < 1000)
        {
            int FF53z = 8;
            int x = bsX;
            int y = (int)((float)bsY + ((float)bsH - (float)bsH * SIN[((90 * elps) / 1000) % 360]));
            wd.useCntr(true);
            wd.setCol(255, 255, 255);
            wd.setTrns(25);
            wd.dwOvl(x, y, FF53z + 2, FF53z + 2);
            wd.setCol(col[0], col[1], col[2]);
            wd.setTrns(65);
            wd.flOvl(x, y, FF53z, FF53z);
            wd.setTrns(100);
            wd.useCntr(false);
        } else
        {
            int elpsNow = elps - 1000;
            int elpsOld = (elpsNow * 4) / 5 - 75;
            double expBsSzNow = SIN[((90 * elpsNow) / 750 + 3600) % 360] * (float)(bsH + 25);
            double expBsSzOld = SIN[((90 * elpsOld) / 750 + 3600) % 360] * (float)(bsH + 25);
            int FF53z = (int)(COS[(90 * (elpsNow / 750)) % 360] * 10F);
            int grvNow = (int)((float)(elpsNow * elpsNow) * 9.8E-005F * 1.4F);
            int grvOld = (int)((float)(elpsOld * elpsOld) * 9.8E-005F * 1.4F);
            wd.useCntr(true);
            wd.setCol(255, 255, 255);
            wd.setTrns(25);
            int szCntrMax = (FF53z * 8 * (750 - elpsNow)) / 750;
            for(int i = 0; i < 4; i++)
                wd.flOvl(bsX, bsY, szCntrMax * i, szCntrMax * i);

            wd.setCol(col[0], col[1], col[2]);
            wd.setTrns(75);
            wd.setStrk(4);
            for(int i = 0; i < 75; i++)
            {
                int deg = AppLoop.RNDM.nextInt(360);
                double sin = SIN[deg];
                double cos = COS[deg];
                double expUnqSz = AppLoop.RNDM.nextDouble();
                int dstNow = (int)(expBsSzNow * expUnqSz);
                int x = (int)((double)bsX + sin * (double)dstNow);
                int y = (int)((double)bsY + cos * (double)dstNow + (double)grvNow);
                int dstOld = (int)(expBsSzOld * expUnqSz);
                int xOld = (int)((double)bsX + sin * (double)dstOld);
                int yOld = (int)((double)bsY + cos * (double)dstOld + (double)grvOld);
                wd.dwLn(x, y, xOld, yOld);
            }

            wd.setTrns(100);
            wd.setStrk(1);
            wd.useCntr(false);
        }
    }

    public static void dwDrpOfWtr(AppLoop al, long bsTm)
    {
        int scrW = al.mMC.getScrW();
        int scrH = al.mMC.getScrH();
        int scrBsSz = ((scrW + scrH) * 3) / 4;
        WrpDw wd = al.mWD;
        int elps = (int)(bsTm % 2000L);
        AppLoop.RNDM.setSeed(bsTm / 2000L);
        int bsX = -30 + AppLoop.RNDM.nextInt(scrW + 60);
        int bsY = -30 + AppLoop.RNDM.nextInt(scrH + 60);
        int bsSz = (int)((SIN[((180 * elps) / 2000) % 360] * (float)scrBsSz) / 3F);
        int alph = (int)(64F - 64F * SIN[((90 * elps) / 2000) % 360]);
        wd.useCntr(true);
        wd.setCol(0, 0, 0);
        wd.setTrns(alph);
        wd.flOvl(bsX, bsY, bsSz, bsSz);
        wd.setTrns(100);
        wd.useCntr(false);
    }

    public static void dwCnftt(AppLoop al, int dwSz)
    {
        int scrW = al.mMC.getScrW();
        int scrH = al.mMC.getScrH();
        WrpDw wd = al.mWD;
        int bsSz = 8;
        AppLoop.RNDM.setSeed(al.mTmNow);
        wd.useCntr(true);
        for(int i = 0; i < dwSz; i++)
        {
            int x = AppLoop.RNDM.nextInt(scrW);
            int y = AppLoop.RNDM.nextInt(scrH);
            int col[] = FIRE_COL[AppLoop.RNDM.nextInt(FIRE_COL.length)];
            wd.setCol(col[0], col[1], col[2]);
            wd.flOvl(x, y, bsSz, bsSz);
        }

        wd.useCntr(false);
    }

    public static final int BS_SQ_SZ = 60;
    private static final int FIRE_TM_OPEN = 1000;
    private static final int FIRE_TM_CLOSE = 750;
    public static final int FIRE_TM_MAX = 1750;
    private static final int FIRE_COL_DARK = 80;
    private static final int FIRE_COL_MDDL = 168;
    private static final int FIRE_COL_LGHT = 255;
    private static final int FIRE_COL[][] = {
        {
            255, 80, 80
        }, {
            80, 255, 80
        }, {
            80, 80, 255
        }, {
            255, 255, 80
        }, {
            255, 80, 255
        }, {
            80, 255, 255
        }, {
            255, 168, 80
        }, {
            255, 80, 168
        }, {
            168, 255, 80
        }, {
            80, 255, 168
        }, {
            168, 80, 255
        }, {
            80, 168, 255
        }, {
            255, 255, 255
        }
    };
    private static final int FIRE_CNTR_RGB = 255;
    private static final int WTR_TM_MAX = 2000;
    private static final int WTR_RGB = 0;
    public static final int WTR_DW_NO = 17;

}
