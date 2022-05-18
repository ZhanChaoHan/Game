// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Scn.java

package com.crocro.wrp.scn;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.bs.F;
import com.crocro.wrp.clckbl.Clckbl;
import com.crocro.wrp.mng.MngCnvs;
import com.crocro.wrp.utl.UtlSort;
import com.crocro.wrp.wrp.WrpDw;
import com.crocro.wrp.wrp.WrpEnv;
import java.util.ArrayList;
import java.util.Comparator;

public class Scn
    implements F
{
    public static final class ClckblComparator
        implements Comparator
    {

        public int compare(Object o1_, Object o2_)
        {
            Clckbl o1 = (Clckbl)o1_;
            Clckbl o2 = (Clckbl)o2_;
            if(o2.mY != o1.mY)
                return o1.mY - o2.mY;
            else
                return o1.mX - o2.mX;
        }

        public ClckblComparator()
        {
        }
    }


    public Scn(AppLoop al)
    {
        mClckEnbl = true;
        mClckblSrtLst = new ArrayList();
        mmAL = al;
    }

    public void init()
    {
        mClckEnbl = true;
        mClckblLst = new ArrayList();
        for(int i = 0; i < 10; i++)
            mClckblLst.add(new ArrayList());

    }

    public void doFlw(int i)
    {
    }

    public void doAlwys(int i)
    {
    }

    public void doAlwysCmn()
    {
        initAlwys();
        preAlwys();
        doClckbl();
        dwClckbl();
        dwClckblEf();
        doClck();
        doBck();
        doMn();
        mmAL.doRsrv();
        pstAlwys();
        doRmv();
    }

    public void initAlwys()
    {
        ArrayList clckblLst = mClckblLst;
        ArrayList clckblSrtLst = mClckblSrtLst;
        int cLstSz = clckblLst.size();
        if(cLstSz != clckblSrtLst.size())
        {
            for(int i = 0; i < cLstSz; i++)
                clckblSrtLst.add(i, null);

        }
        for(int j = 0; j < 10; j++)
        {
            ArrayList cl = (ArrayList)clckblLst.get(j);
            int clSz = cl.size();
            Clckbl ca[] = (Clckbl[])clckblSrtLst.get(j);
            if(ca == null || ca.length != clSz)
            {
                ca = new Clckbl[clSz];
                clckblSrtLst.set(j, ca);
            }
            for(int i = 0; i < clSz; i++)
                ca[i] = (Clckbl)cl.get(i);

            UtlSort.clckblSort(ca);
        }

        mmAL.mWD.tgtBuf(0);
    }

    public void doClckbl()
    {
        for(int j = 0; j < 10; j++)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = 0; i < ca.length; i++)
                ca[i].doAlwys();

        }

    }

    public void dwClckbl()
    {
        for(int j = 0; j < 10; j++)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = 0; i < ca.length; i++)
                ca[i].dwAlwys();

        }

    }

    public void dwClckblEf()
    {
        for(int j = 0; j < 10; j++)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = 0; i < ca.length; i++)
                ca[i].dwAlwysEf();

        }

    }

    public void doClck()
    {
        if(!mClckEnbl)
            return;
        mmAL.mMC.update();
        com.crocro.wrp.mng.MngCnvs.Clck clck = mmAL.mMC.getTopClck();
        boolean keepClck = false;
        if(clck == null)
            return;
label0:
        for(int j = 9; j >= 0; j--)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = ca.length - 1; i >= 0; i--)
            {
                Clckbl clckbl = ca[i];
                if(clckbl == null)
                    continue;
                int resClck = clckbl.chkClckIn(clck.inX, clck.inY);
                if(resClck == 1)
                    clckbl.actClck();
                else
                if(resClck != 4)
                {
                    if(!clck.chkFnsh())
                    {
                        if(clckbl.mClckNow)
                        {
                            keepClck = true;
                            clckbl.setDrgPos(mmAL.mMC.getMX(), mmAL.mMC.getMY());
                        }
                        continue;
                    }
                    resClck = clckbl.chkClckOut(clck.outX, clck.outY);
                    if(resClck != 3)
                        continue;
                    clckbl.actDrp();
                }
                break label0;
            }

        }

        if(!keepClck)
            mmAL.mMC.rmvTopClck();
    }

    public void doBck()
    {
        if(!mClckEnbl)
            return;
        if(!mmAL.mMC.mBck)
            return;
        for(int j = 9; j >= 0; j--)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = ca.length - 1; i >= 0; i--)
            {
                Clckbl clckbl = ca[i];
                if(clckbl != null && clckbl.doBck())
                {
                    mmAL.mMC.mBck = false;
                    return;
                }
            }

        }

        if(addDoBck())
        {
            mmAL.mMC.mBck = false;
            return;
        } else
        {
            mmAL.mWE.rootBck();
            mmAL.mMC.mBck = false;
            return;
        }
    }

    public boolean addDoBck()
    {
        return false;
    }

    public void doMn()
    {
        if(!mClckEnbl)
            return;
        if(!mmAL.mMC.mMn)
            return;
        for(int j = 9; j >= 0; j--)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = ca.length - 1; i >= 0; i--)
            {
                Clckbl clckbl = ca[i];
                if(clckbl != null && clckbl.doMn())
                {
                    mmAL.mMC.mMn = false;
                    return;
                }
            }

        }

        if(addDoMn())
        {
            mmAL.mMC.mMn = false;
            return;
        } else
        {
            mmAL.mMC.mMn = false;
            return;
        }
    }

    public boolean addDoMn()
    {
        return false;
    }

    public void preAlwys()
    {
    }

    public void pstAlwys()
    {
    }

    public void doRmv()
    {
        for(int j = 0; j < 10; j++)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = 0; i < ca.length; i++)
                if(ca[i].mNeedRmv)
                {
                    ca[i].finish();
                    ((ArrayList)mClckblLst.get(j)).remove(ca[i]);
                }

        }

    }

    public void addClckbl(int lyId, Clckbl clckbl)
    {
        ((ArrayList)mClckblLst.get(lyId)).add(clckbl);
    }

    public void clrClckblLst()
    {
        if(mClckblSrtLst != null)
        {
            for(int j = 0; j < mClckblSrtLst.size(); j++)
            {
                Clckbl clckArr[] = (Clckbl[])mClckblSrtLst.get(j);
                if(clckArr != null)
                {
                    for(int i = 0; i < clckArr.length; i++)
                        clckArr[i].finish();

                }
            }

        }
        if(mClckblLst != null)
        {
            for(int j = 0; j < mClckblLst.size(); j++)
            {
                ArrayList arrLstClckbl = (ArrayList)mClckblLst.get(j);
                if(arrLstClckbl != null)
                {
                    for(int i = 0; i < arrLstClckbl.size(); i++)
                    {
                        Clckbl clckbl = (Clckbl)arrLstClckbl.get(i);
                        if(clckbl != null)
                            clckbl.finish();
                    }

                    arrLstClckbl.clear();
                }
            }

        }
    }

    public void clrClckblLyr(int lyr)
    {
        if(lyr < 0)
            return;
        if(mClckblSrtLst != null && lyr < mClckblSrtLst.size())
        {
            Clckbl clckArr[] = (Clckbl[])mClckblSrtLst.get(lyr);
            if(clckArr != null)
            {
                for(int i = 0; i < clckArr.length; i++)
                    clckArr[i].finish();

            }
        }
        if(mClckblLst != null && lyr < mClckblLst.size())
        {
            ArrayList arrLstClckbl = (ArrayList)mClckblLst.get(lyr);
            if(arrLstClckbl != null)
            {
                for(int i = 0; i < arrLstClckbl.size(); i++)
                {
                    Clckbl clckbl = (Clckbl)arrLstClckbl.get(i);
                    if(clckbl != null)
                        clckbl.finish();
                }

                arrLstClckbl.clear();
            }
        }
    }

    public void cnslClck()
    {
        for(int j = 0; j < 10; j++)
        {
            Clckbl ca[] = (Clckbl[])mClckblSrtLst.get(j);
            for(int i = 0; i < ca.length; i++)
                ca[i].mClckNow = false;

        }

        Clckbl.mClckExclsn = false;
    }

    public void finish()
    {
        clrClckblLst();
        if(mClckblSrtLst != null)
            mClckblSrtLst.clear();
        mClckblSrtLst = null;
        if(mClckblLst != null)
            mClckblLst.clear();
        mClckblLst = null;
        mmAL = null;
    }

    public AppLoop mmAL;
    public boolean mClckEnbl;
    public ArrayList mClckblLst;
    public ArrayList mClckblSrtLst;
    public static final ClckblComparator CLCKBL_CMPRTR = new ClckblComparator();

}
