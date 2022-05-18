// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpSnd.java

package com.crocro.wrp.wrp;

import com.crocro.wrp.app.AppLoop;

public interface WrpSnd
{
    public static class Prms
    {

        public void set(int prmTyp, int newPrm)
        {
            if(prmTyp == 0)
                typ = newPrm;
            if(prmTyp == 1)
                loop = newPrm;
            if(prmTyp == 2)
                vol = newPrm;
        }

        public int typ;
        public int loop;
        public int vol;
        public int wrnWt;

        public Prms()
        {
            typ = 0;
            loop = 0;
            vol = 100;
            wrnWt = 0;
        }

        public Prms(int t, int l, int v)
        {
            typ = 0;
            loop = 0;
            vol = 100;
            wrnWt = 0;
            typ = t;
            loop = l;
            vol = v;
        }
    }


    public abstract void setAppLoop(AppLoop apploop);

    public abstract void freeCash();

    public abstract void ldSnd(int i, String s);

    public abstract void delSnd(int i);

    public abstract void setPrm(int i, int j, int k);

    public abstract int getSz();

    public abstract void chngVol(int i);

    public abstract int getVol();

    public abstract void play(int i);

    public abstract void stop(int i);

    public abstract void chng(int i, String s);

    public abstract boolean isRunning(int i);

    public abstract boolean enblAutoHrdVol();

    public abstract void doAutoHrdVol();

    public static final int PRM_TYP = 0;
    public static final int PRM_LOOP = 1;
    public static final int PRM_VOL = 2;
    public static final int TYP_BGM = 0;
    public static final int TYP_SE = 1;
    public static final int TYP_WRN = 2;
    public static final int TYP_DEL = 3;
    public static final int LOOP_FALSE = 0;
    public static final int LOOP_TRUE = 1;
    public static final int VOL_MAX = 100;
    public static final int VOL_MIN = 0;
    public static final int VOL_DFLT = 50;
    public static final int WT_NO = 0;
    public static final int WT_USE = 1;
    public static final Prms PRMS_BGM = new Prms(0, 1, 100);
    public static final Prms PRMS_SE = new Prms(1, 0, 100);
    public static final Prms PRMS_WARN = new Prms(2, 0, 100);

}
