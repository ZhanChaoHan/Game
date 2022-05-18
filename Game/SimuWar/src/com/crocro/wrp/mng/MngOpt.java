// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MngOpt.java

package com.crocro.wrp.mng;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;
import java.util.*;

public class MngOpt
{

    public MngOpt(AppLoop al, String pthStr)
    {
        this(al, pthStr, false);
    }

    public MngOpt(AppLoop al, String pthStr, boolean lclFlg)
    {
        mAL = null;
        mLclFlg = false;
        mLdSccs = false;
        mStrHashMap = null;
        mPthStr = pthStr;
        mLclFlg = lclFlg;
        if(!mLclFlg)
        {
            mAL = al;
            mLdSccs = initR(mPthStr);
        } else
        {
            mAL = al;
            mLdSccs = initLcl(mPthStr);
        }
    }

    public boolean initR(String pthStr)
    {
        if(pthStr == null)
            return false;
        if(mStrHashMap == null)
            mStrHashMap = new HashMap();
        String ldStr = UtlTool.bToS(mAL.mWF.getR(pthStr));
        return initHash(ldStr);
    }

    public boolean initLcl(String pthStr)
    {
        if(pthStr == null)
            return false;
        if(mStrHashMap == null)
            mStrHashMap = new HashMap();
        String ldStr = UtlTool.bToS(mAL.mWF.getFBArr(pthStr));
        return initHash(ldStr);
    }

    private boolean initHash(String ldStr)
    {
        if(ldStr == null || ldStr.length() <= 0)
            return false;
        String sArr[] = UtlTool.repAll(ldStr, "\r", "").split("\n");
        if(sArr == null || sArr.length <= 0)
            return false;
        for(int i = 0; i < sArr.length; i++)
            if(!sArr[i].startsWith("//"))
            {
                int spltPos = sArr[i].indexOf("=");
                if(spltPos > 0)
                {
                    String keyStr = sArr[i].substring(0, spltPos).trim();
                    String bdyStr = sArr[i].substring(spltPos + 1);
                    mStrHashMap.put(keyStr, bdyStr);
                }
            }

        return true;
    }

    public String getS(String keyStr)
    {
        String resStr = (String)mStrHashMap.get(keyStr);
        if(resStr == null)
            resStr = "";
        else
            resStr = UtlTool.repAll(resStr, "\\n", "\n");
        return resStr;
    }

    public boolean exist(String keyStr)
    {
        return mStrHashMap.containsKey(keyStr);
    }

    public int getI(String keyStr)
    {
        return UtlTool.sToI(getS(keyStr));
    }

    public void setS(String keyStr, String prmStr)
    {
        mStrHashMap.put(keyStr, UtlTool.repAll(prmStr, "\n", "\\n"));
    }

    public void setI(String keyStr, int prmI)
    {
        mStrHashMap.put(keyStr, String.valueOf(prmI));
    }

    public String getSvStr()
    {
        ArrayList aLst = new ArrayList();
        StringBuilder sb = new StringBuilder();
        Set set = mStrHashMap.entrySet();
        java.util.Map.Entry entry;
        for(Iterator itr = set.iterator(); itr.hasNext(); aLst.add((new StringBuilder(String.valueOf((String)entry.getKey()))).append("=").append((String)entry.getValue()).toString()))
            entry = (java.util.Map.Entry)itr.next();

        Collections.sort(aLst);
        String s;
        for(Iterator iterator = aLst.iterator(); iterator.hasNext(); sb.append(s).append("\n"))
            s = (String)iterator.next();

        return sb.toString();
    }

    public boolean sv()
    {
        if(!mLclFlg)
            return false;
        boolean res = false;
        String svStr = getSvStr();
        if(svStr == null)
            return false;
        try
        {
            res = mAL.mWF.setFBArr(mPthStr, svStr.getBytes("UTF-8"));
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            mAL.mWE.dbgErr(e, "MngOpt sv getBytes");
        }
        return res;
    }

    public  void f(StringBuilder sb, String key, Object args[])
    {
        String frmt = getS(key);
        try
        {
            sb.append(UtlTool.smplFormat(frmt, args));
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            mAL.mWE.dbgErr(e, (new StringBuilder("MngOpt f : key : ")).append(key).append(" frmt ").append(frmt).toString());
        }
    }

    public  String f(String key, Object args[])
    {
        String frmt = getS(key);
        String res = null;
        try
        {
            res = UtlTool.smplFormat(frmt, args);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            mAL.mWE.dbgErr(e, (new StringBuilder("MngOpt f : key : ")).append(key).append(" frmt ").append(frmt).toString());
        }
        return res;
    }

    public void finish()
    {
        mAL = null;
        mStrHashMap.clear();
        mStrHashMap = null;
    }

    private AppLoop mAL;
    public boolean mLclFlg;
    public boolean mLdSccs;
    public String mPthStr;
    private HashMap mStrHashMap;
}
