// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GmImgWpn.java

package com.crocro.simWar.gm;

import com.crocro.simWar.app.AppLoopSimWar;
import com.crocro.simWar.app.V;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpFile;

// Referenced classes of package com.crocro.simWar.gm:
//            GmDt

public class GmImgWpn
    implements F
{

    public GmImgWpn(AppLoopSimWar al)
    {
        mAL = al;
        mWDIn = mAL.mWD.getNew();
        String tmpSArr2[][] = UtlTool.sToS2dAr(UtlTool.bToS(mAL.mWF.getR("res/dat/wpnS.dat")));
        int tmpIArr2[][] = UtlTool.sToI2dAr(UtlTool.bToS(mAL.mWF.getR("res/dat/wpnI.dat")));
        int bufCnt = 0;
        mImgIds = new int[tmpSArr2.length][];
        mImgNms = new String[tmpSArr2.length];
        mImgLdEnd = new boolean[tmpSArr2.length];
        for(int i = 0; i < tmpSArr2.length; i++)
        {
            int bufSz = tmpIArr2[i][0];
            mImgIds[i] = new int[bufSz];
            mImgNms[i] = tmpSArr2[i][0];
            for(int j = 0; j < bufSz; j++)
            {
                mImgIds[i][j] = bufCnt;
                bufCnt++;
            }

        }

        mSkl2Id = new int[mAL.egV.mGD.mSklS.length][2];
        for(int i = 0; i < mSkl2Id.length; i++)
        {
            String nm = mAL.egV.mGD.mSklS[i][0];
            mSkl2Id[i][0] = nm2Id(nm);
            nm = mAL.egV.mGD.mSklS[i][1];
            mSkl2Id[i][1] = nm2Id(nm);
        }

    }

    public int nm2Id(String srchNm)
    {
        if(srchNm == null || srchNm.length() == 0)
            return -1;
        for(int i = 0; i < mImgNms.length; i++)
            if(srchNm.equals(mImgNms[i]))
                return i;

        return -1;
    }

    public int getId(int sklTyp, int useTyp)
    {
        if(sklTyp < 0 || sklTyp >= mSkl2Id.length)
            return -1;
        if(useTyp < 0 || useTyp >= 2)
        {
            return -1;
        } else
        {
            int resId = mSkl2Id[sklTyp][useTyp];
            return resId;
        }
    }

    public void ldImg(int imgTyp)
    {
        if(imgTyp < 0 || imgTyp >= mImgIds.length)
            return;
        if(!mImgLdEnd[imgTyp])
        {
            mImgLdEnd[imgTyp] = true;
            int bufs[] = mImgIds[imgTyp];
            String nm = mImgNms[imgTyp];
            if(bufs.length == 1)
            {
                int bufId = bufs[0];
                String pth = UtlTool.smplFormat("w/%s.png", new Object[] {
                    nm
                });
                mWDIn.mkBuf(bufId, pth);
            } else
            {
                for(int j = 0; j < bufs.length; j++)
                {
                    int bufId = bufs[j];
                    String pth = UtlTool.smplFormat("w/%s.png", new Object[] {
                        (new StringBuilder(String.valueOf(nm))).append(j).toString()
                    });
                    mWDIn.mkBuf(bufId, pth);
                }

            }
        }
    }

    public void finish()
    {
        mAL = null;
        if(mWDIn != null)
            mWDIn.flshBuf(0);
        mWDIn = null;
        mImgIds = null;
        for(int i = 0; i < mImgNms.length; i++)
            mImgNms[i] = null;

        mImgNms = null;
        mImgLdEnd = null;
        mSkl2Id = null;
    }

    private AppLoopSimWar mAL;
    public WrpDw mWDIn;
    private static final String IMG_PTH_WPN = "w/%s.png";
    private static final String GD_WPN_I_PTH = "res/dat/wpnI.dat";
    private static final String GD_WPN_S_PTH = "res/dat/wpnS.dat";
    public int mImgIds[][];
    public String mImgNms[];
    public boolean mImgLdEnd[];
    public static final int USE_FLY = 0;
    public static final int USE_HIT = 1;
    public static final int USE_MAX = 2;
    public int mSkl2Id[][];
}
