// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DrwScrpt.java

package com.crocro.wrp.clckbl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngLayout;
import com.crocro.wrp.utl.UtlJson;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpDw;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.crocro.wrp.clckbl:
//            Btn

public class DrwScrpt extends Btn
{
    private class LdImg
    {

        public int bufId;
     

        private LdImg()
        {
          
        }

        LdImg(LdImg ldimg)
        {
            this();
        }
    }

    private class ScrptPrms
    {

        public String cmnd;
        public String pth;
        public String w;
        public String h;
        public String bsX;
        public String bsY;
        public String mvX;
        public String mvY;
        public String flp;
        public String tl;
        public String algn;
        public String mes;
        public String key;
        public String val;
        public int dX;
        public int dY;
        public int dW;
        public int dH;
        public int rX;
        public int rY;
        public int rW;
        public int rH;
        public String boxR;
        public String boxG;
        public String boxB;
        public int boxRInt;
        public int boxGInt;
        public int boxBInt;
    

        private ScrptPrms()
        {
           
            boxRInt = -1;
            boxGInt = -1;
            boxBInt = -1;
        }

        ScrptPrms(ScrptPrms scrptprms)
        {
            this();
        }
    }


    public DrwScrpt(AppLoop al, int x, int y, int w, int h, String scrptStr)
    {
        super(al, x, y, w, h, 0);
        mSPrmsArr = null;
        mSPrmsPos = 0;
        mWDIn = null;
        mBufIdSz = 1;
        mLdImgMap = new HashMap();
        mDwArr = new ArrayList();
        mBckSP = null;
        mVarMap = new HashMap();
        UtlJson jsn = new UtlJson();
        ArrayList jsnArr = (ArrayList)jsn.prs(scrptStr);
        mSPrmsArr = new ScrptPrms[jsnArr.size()];
        Field spFld[] = DrwScrpt.ScrptPrms.class.getFields();
        for(int i = 0; i < jsnArr.size(); i++)
        {
            mSPrmsArr[i] = new ScrptPrms(null);
            ScrptPrms nowSP = mSPrmsArr[i];
            HashMap jsnHsh = (HashMap)jsnArr.get(i);
            for(int j = 0; j < spFld.length; j++)
            {
                String fldNm = spFld[j].getName();
                String fldTyp = spFld[j].getType().getSimpleName();
                if(jsnHsh.containsKey(fldNm))
                    if(fldTyp.equals("int"))
                    {
                        int prm = 0;
                        prm = UtlTool.sToI(jsnHsh.get(fldNm).toString());
                        try
                        {
                            Field fld = nowSP.getClass().getField(fldNm);
                            fld.setInt(nowSP, prm);
                        }
                        catch(Exception e)
                        {
                            System.out.println((new StringBuilder("DrwScrpt int : ")).append(e).toString());
                        }
                    } else
                    {
                        String prm = "";
                        prm = jsnHsh.get(fldNm).toString();
                        try
                        {
                            Field fld = nowSP.getClass().getField(fldNm);
                            fld.set(nowSP, prm);
                        }
                        catch(Exception e)
                        {
                            System.out.println((new StringBuilder("DrwScrpt String : ")).append(e).toString());
                        }
                    }
            }

        }

        mWDIn = mWD.getNew();
        mNmSFO = mAL.mML.mStrFntDflt.clone();
        mNmSFO.r = 255;
        mNmSFO.g = 255;
        mNmSFO.b = 255;
        prgScrpt();
    }

    public boolean prgScrpt()
    {
        if(mSPrmsArr == null || mSPrmsPos >= mSPrmsArr.length)
            return true;
        for(int i = 0; i < mDwArr.size(); i++)
        {
            ScrptPrms sp = (ScrptPrms)mDwArr.get(i);
            if(sp != null && sp.cmnd.equals("setMes"))
                mDwArr.remove(sp);
        }

        for(; mSPrmsPos < mSPrmsArr.length; mSPrmsPos++)
        {
            ScrptPrms sp = mSPrmsArr[mSPrmsPos];
            if(sp.cmnd != null && sp.cmnd.length() != 0)
                if(sp.cmnd.equals("setVar"))
                    mVarMap.put((new StringBuilder("v(")).append(sp.key).append(")").toString(), sp.val);
                else
                if(sp.cmnd.equals("setBck"))
                {
                    repVar(sp);
                    setImg(sp, 0);
                    setDPrm(sp);
                    mBckSP = sp;
                    mDwArr.add(0, mBckSP);
                } else
                if(sp.cmnd.equals("addImg"))
                {
                    repVar(sp);
                    setImg(sp, 1);
                    setDPrm(sp);
                    mDwArr.add(sp);
                } else
                if(sp.cmnd.equals("rstImg"))
                {
                    repVar(sp);
                    mDwArr = new ArrayList();
                    mDwArr.add(0, mBckSP);
                } else
                if(sp.cmnd.equals("setMes"))
                {
                    repVar(sp);
                    sp.mes = UtlTool.repAll(sp.mes, "\\n", "\n");
                    int wMax = mW - 60;
                    com.crocro.wrp.wrp.WrpDw.StrFntOpt sfo = mAL.mML.mStrFntDflt;
                    sp.dW = mWD.getStrW(sp.mes, sfo, wMax);
                    int tmpLnH = sfo.lnH;
                    sfo.lnH = (tmpLnH * 140) / 100;
                    sp.dH = mWD.getStrH(sp.mes, sfo, wMax);
                    sfo.lnH = tmpLnH;
                    sp.dX = (mW - sp.dW) / 2;
                    if(sp.bsY != null && sp.bsY.equals("t"))
                        sp.dY = mH / 2 + 10 + 3;
                    else
                    if(sp.bsY != null && sp.bsY.equals("b"))
                        sp.dY = mH / 2 - sp.dH - 10 - 3;
                    else
                        sp.dY = (mH - sp.dH) / 2;
                    if(UtlTool.sToI(sp.mvX) != 0)
                        sp.dX += UtlTool.sToI(sp.mvX);
                    if(UtlTool.sToI(sp.mvY) != 0)
                        sp.dY += UtlTool.sToI(sp.mvY);
                    if(sp.boxR != null)
                        sp.boxRInt = UtlTool.sToI(sp.boxR);
                    if(sp.boxG != null)
                        sp.boxGInt = UtlTool.sToI(sp.boxG);
                    if(sp.boxB != null)
                        sp.boxBInt = UtlTool.sToI(sp.boxB);
                    mDwArr.add(sp);
                } else
                if(sp.cmnd.equals("setNm"))
                {
                    if(sp.mes.length() == 0)
                    {
                        for(int i = 0; i < mDwArr.size(); i++)
                        {
                            ScrptPrms spTmp = (ScrptPrms)mDwArr.get(i);
                            if(spTmp.cmnd.equals("setNm"))
                                mDwArr.remove(spTmp);
                        }

                    } else
                    {
                        repVar(sp);
                        sp.mes = UtlTool.repAll(sp.mes, "\\n", "\n");
                        int wMax = mW - 60;
                        com.crocro.wrp.wrp.WrpDw.StrFntOpt sfo = mAL.mML.mStrFntDflt;
                        sp.dW = mWD.getStrW(sp.mes, sfo, wMax);
                        int tmpLnH = sfo.lnH;
                        sfo.lnH = (tmpLnH * 140) / 100;
                        sp.dH = mWD.getStrH(sp.mes, sfo, wMax);
                        sfo.lnH = tmpLnH;
                        sp.dX = (mW - sp.dW) / 2;
                        sp.dY = (mH - sp.dH) / 2;
                        if(UtlTool.sToI(sp.mvX) != 0)
                            sp.dX += UtlTool.sToI(sp.mvX);
                        if(UtlTool.sToI(sp.mvY) != 0)
                            sp.dY += UtlTool.sToI(sp.mvY);
                        mDwArr.add(sp);
                    }
                } else
                if(sp.cmnd.equals("wait"))
                {
                    mSPrmsPos++;
                    return false;
                }
        }

        return true;
    }

    public void bckStrtScrpt()
    {
        mSPrmsPos = 0;
        prgScrpt();
    }

    public void repVar(ScrptPrms sp)
    {
        Set keySet = mVarMap.keySet();
        Iterator itr = keySet.iterator();
        Field fields[] = sp.getClass().getFields();
        try
        {
            while(itr.hasNext()) 
            {
                String prm = (String)itr.next();
                for(int i = 0; i < fields.length; i++)
                    if(fields[i].getType() == String.class && fields[i].get(sp) != null)
                    {
                        String s = (String)fields[i].get(sp);
                        s = UtlTool.repAll(s, prm, (String)mVarMap.get(prm));
                        fields[i].set(sp, s);
                    }

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void setImg(ScrptPrms sp, int typ)
    {
        if(mLdImgMap.get(sp.pth) != null)
            return;
        LdImg ldImg = new LdImg(null);
        if(typ == 0)
        {
            mWDIn.mkBuf(0, sp.pth);
            ldImg.bufId = 0;
        } else
        {
            int sz = mLdImgMap.size();
            mWDIn.mkBuf(sz, sp.pth);
            ldImg.bufId = sz;
        }
        mLdImgMap.put(sp.pth, ldImg);
    }

    public void setDPrm(ScrptPrms sp)
    {
        int bufId = ((LdImg)mLdImgMap.get(sp.pth)).bufId;
        int bsW = mWDIn.getBufW(bufId);
        int bsH = mWDIn.getBufH(bufId);
        sp.dX = sp.rX = 0;
        sp.dY = sp.rY = 0;
        sp.dW = sp.rW = bsW;
        sp.dH = sp.rH = bsH;
        if(sp.w.equals("fit"))
            sp.dW = mW;
        if(sp.h.equals("fit"))
            sp.dH = mH;
        if(UtlTool.sToI(sp.w) > 0)
            sp.dW = UtlTool.sToI(sp.w);
        if(UtlTool.sToI(sp.h) > 0)
            sp.dH = UtlTool.sToI(sp.h);
        if(sp.bsX.equals("r"))
            sp.dX = mW - sp.dW;
        if(sp.bsX.equals("c"))
            sp.dX = (mW - sp.dW) / 2;
        if(sp.bsX.equals("cR"))
            sp.dX = (mW - sp.dW) / 2 + mW / 3;
        if(sp.bsX.equals("cL"))
            sp.dX = (mW - sp.dW) / 2 - mW / 3;
        if(sp.bsY.equals("b"))
            sp.dY = mH - sp.dH;
        if(sp.bsY.equals("c"))
            sp.dY = (mH - sp.dH) / 2;
        if(UtlTool.sToI(sp.mvX) != 0)
            sp.dX += UtlTool.sToI(sp.mvX);
        if(UtlTool.sToI(sp.mvY) != 0)
            sp.dY += UtlTool.sToI(sp.mvY);
        if(sp.flp != null && sp.flp.indexOf("h") >= 0)
        {
            sp.rX = sp.rW;
            sp.rW *= -1;
        }
        if(sp.flp != null && sp.flp.indexOf("v") >= 0)
        {
            sp.rY = sp.rH;
            sp.rH *= -1;
        }
    }

    public void dwBfr()
    {
        for(int lyr = 0; lyr < 3; lyr++)
        {
            for(int i = 0; i < mDwArr.size(); i++)
            {
                ScrptPrms sp = (ScrptPrms)mDwArr.get(i);
                if(sp != null)
                    if(lyr == 0)
                    {
                        if(sp.cmnd.equals("setBck"))
                            dwImg(sp);
                    } else
                    if(lyr == 1)
                    {
                        if(sp.cmnd.equals("addImg"))
                            dwImg(sp);
                    } else
                    if(lyr == 2 && (sp.cmnd.equals("setMes") || sp.cmnd.equals("setNm")))
                        dwStr(sp);
            }

        }

    }

    public void dwImg(ScrptPrms sp)
    {
        mWD.setClip(mX, mY, mW, mH);
        mWD.zmImg(mWDIn, ((LdImg)mLdImgMap.get(sp.pth)).bufId, mX + sp.dX, mY + sp.dY, sp.dW, sp.dH, sp.rX, sp.rY, sp.rW, sp.rH);
        mWD.clrClip();
    }

    public void dwStr(ScrptPrms sp)
    {
        int bsX;
        int boxX = bsX = mX + sp.dX;
        int bsY;
        int boxY = bsY = mY + sp.dY;
        int bsW;
        int boxW = bsW = sp.dW;
        int boxH = sp.dH;
        boxX -= 10;
        boxY -= 10;
        boxW += 20;
        boxH += 20;
        if(sp.cmnd.equals("setMes"))
            mWD.useCol(WrpDw.COL_BLACK);
        else
        if(sp.cmnd.equals("setNm"))
            mWD.setCol(10, 119, 213);
        mWD.flRRct(boxX - 3, boxY - 3, boxW + 6, boxH + 6, 20, 20);
        if(sp.cmnd.equals("setMes") && sp.tl != null)
        {
            int xTlBs = boxX + boxW / 2;
            int yTlBs = (boxY + boxH) - 3;
            int xTlTop = 0;
            if(sp.tl.equals("r"))
            {
                xTlBs += ((boxW / 2 - 18) * 3) / 4;
                xTlTop += 9;
            } else
            if(sp.tl.equals("l"))
            {
                xTlBs -= ((boxW / 2 - 18) * 3) / 4;
                xTlTop -= 9;
            }
            int xPosArr[] = {
                xTlBs - 9, xTlBs + xTlTop, xTlBs + 9
            };
            int yPosArr[] = {
                yTlBs, yTlBs + 24, yTlBs
            };
            if(sp.boxRInt != -1)
                mWD.setCol(sp.boxRInt, sp.boxGInt, sp.boxBInt);
            else
                mWD.useCol(WrpDw.COL_WHITE);
            mWD.flPly(xPosArr, yPosArr);
            mWD.useCol(WrpDw.COL_BLACK);
            mWD.setStrk(3);
            mWD.dwPly(xPosArr, yPosArr);
            mWD.setStrk(1);
        }
        if(sp.boxRInt != -1)
            mWD.setCol(sp.boxRInt, sp.boxGInt, sp.boxBInt);
        else
        if(sp.cmnd.equals("setMes"))
            mWD.useCol(WrpDw.COL_WHITE);
        else
        if(sp.cmnd.equals("setNm"))
            mWD.setCol(119, 182, 233);
        mWD.flRRct(boxX, boxY, boxW, boxH, 14, 14);
        int algn = 0;
        if(sp.algn != null)
            if(sp.algn.equals("c"))
                algn = 1;
            else
            if(sp.algn.equals("r"))
                algn = 2;
        com.crocro.wrp.wrp.WrpDw.StrFntOpt sfo = mAL.mML.mStrFntDflt;
        if(sp.cmnd.equals("setNm"))
            sfo = mNmSFO;
        int tmpLnH = sfo.lnH;
        sfo.lnH = (tmpLnH * 140) / 100;
        mWD.dwStr(sp.mes, bsX, bsY, bsW, sfo, null, algn);
        sfo.lnH = tmpLnH;
    }

    public void dwAftr()
    {
        dwBfr();
    }

    public void finish()
    {
        super.finish();
        mSPrmsArr = null;
        if(mWDIn != null)
            mWDIn.flshBuf(0);
        mWDIn = null;
        if(mLdImgMap != null)
            mLdImgMap.clear();
        mLdImgMap = null;
        if(mDwArr != null)
            mDwArr.clear();
        mDwArr = null;
        if(mVarMap != null)
            mVarMap.clear();
        mVarMap = null;
    }

    private ScrptPrms mSPrmsArr[];
    private int mSPrmsPos;
    public static final int BUF_BCK = 0;
    public static final int BUF_ADD_IMG = 1;
    private WrpDw mWDIn;
    int mBufIdSz;
    private HashMap mLdImgMap;
    private ArrayList mDwArr;
    ScrptPrms mBckSP;
    private static final int BOX_MRGN = 30;
    private static final int BOX_PDNG = 10;
    private static final int BOX_STRK = 3;
    private static final int BOX_RND = 20;
    private static final int TL_W = 18;
    private static final int TL_H = 24;
    private static final int STR_LNH_PER = 140;
    private HashMap mVarMap;
    private static com.crocro.wrp.wrp.WrpDw.StrFntOpt mNmSFO;
    private static final int COL_NM_R = 119;
    private static final int COL_NM_G = 182;
    private static final int COL_NM_B = 233;
}
