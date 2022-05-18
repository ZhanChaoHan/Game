// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtlJson.java

package com.crocro.wrp.utl;

import java.io.PrintStream;
import java.util.*;

public class UtlJson
{

    public UtlJson()
    {
        mDbg = false;
        mOSLen = 8;
    }

    public Object prs(String srcStr)
    {
        if(srcStr == null || srcStr.length() == 0)
            return null;
        Object resObj = null;
        mSrcStr = srcStr;
        mPos = 0;
        for(mLen = srcStr.length(); mPos < mLen; mPos++)
        {
            char c = srcStr.charAt(mPos);
            if(c == '[')
            {
                dbgOut("prs", "Array", true);
                resObj = prsArr();
            } else
            if(c == '{')
            {
                dbgOut("prs", "Hash", true);
                resObj = prsHsh();
            }
        }

        return resObj;
    }

    public Object prsArr()
    {
        ArrayList al = new ArrayList();
        for(mPos++; mPos < mLen; mPos++)
        {
            char c = mSrcStr.charAt(mPos);
            if(c == ' ' || c == '\t' || c == '\r' || c == '\n')
                continue;
            if(c == ']')
                break;
            if(c == '[')
            {
                dbgOut("prsArr", "Array", true);
                Object obj = prsArr();
                al.add(obj);
            } else
            if(c == '{')
            {
                dbgOut("prsArr", "Hash", true);
                Object obj = prsHsh();
                al.add(obj);
            } else
            if(c == ',')
                dbgOut("prsArr", "Comma", true);
            else
            if(c == '"' || c == '\'')
            {
                dbgOut("prsArr", "String Dash", true);
                String str = prsStrDsh();
                dbgOut("prsArr", (new StringBuilder("String Dash * set String / ")).append(str).toString(), false);
                al.add(str);
            } else
            {
                dbgOut("prsArr", "String", true);
                String str = prsStr();
                dbgOut("prsArr", (new StringBuilder("String * set String / ")).append(str).toString(), false);
                al.add(str);
            }
        }

        return al;
    }

    public String prsStr()
    {
        int strtPos = mPos;
        for(mPos++; mPos < mLen; mPos++)
        {
            char c = mSrcStr.charAt(mPos);
            if(c != ',' && c != ':' && c != '}' && c != ']')
                continue;
            mPos--;
            break;
        }

        if(mPos >= mLen)
            return mSrcStr.substring(strtPos);
        else
            return mSrcStr.substring(strtPos, mPos + 1);
    }

    public String prsStrDsh()
    {
        int strtPos = mPos;
        char strtChr = mSrcStr.charAt(mPos);
        boolean wDsh = strtChr == '"';
        boolean sDsh = strtChr == '\'';
        for(mPos++; mPos < mLen; mPos++)
        {
            char c = mSrcStr.charAt(mPos);
            if(wDsh && c == '"' && mSrcStr.charAt(mPos - 1) != '\\' || sDsh && c == '\'' && mSrcStr.charAt(mPos - 1) != '\\')
                break;
        }

        if(mPos >= mLen)
            return mSrcStr.substring(strtPos + 1);
        else
            return mSrcStr.substring(strtPos + 1, mPos);
    }

    public Object prsHsh()
    {
        HashMap hsh = new HashMap();
        String nowKey = "";
        boolean flgKey = true;
        for(mPos++; mPos < mLen; mPos++)
        {
            char c = mSrcStr.charAt(mPos);
            if(c == ' ' || c == '\t' || c == '\r' || c == '\n')
                continue;
            if(c == '}')
                break;
            if(c == '[')
            {
                dbgOut("prsHsh", "Array", true);
                Object obj = prsArr();
                hsh.put(nowKey, obj);
                dbgOut("prsHsh", (new StringBuilder("Array * set hash / ")).append(nowKey).append(" : ").append(obj).toString(), false);
            } else
            if(c == '{')
            {
                dbgOut("prsHsh", "Hash", true);
                Object obj = prsHsh();
                hsh.put(nowKey, obj);
                dbgOut("prsHsh", (new StringBuilder("Hash * set hash / ")).append(nowKey).append(" : ").append(obj).toString(), false);
            } else
            if(c == ':' || c == ',')
                dbgOut("prsHsh", "Hash or Comma", true);
            else
            if(c == '"' || c == '\'')
            {
                dbgOut("prsHsh", "String Dash", true);
                String str = prsStrDsh();
                if(!flgKey)
                {
                    hsh.put(nowKey, str);
                    dbgOut("prsHsh", (new StringBuilder("String Dash * set hash / ")).append(nowKey).append(" : ").append(str).toString(), false);
                    nowKey = "";
                } else
                {
                    nowKey = str;
                    dbgOut("prsHsh", (new StringBuilder("String Dash * set key / ")).append(nowKey).toString(), false);
                }
                flgKey = !flgKey;
            } else
            {
                dbgOut("prsHsh", "String", true);
                String str = prsStr();
                if(!flgKey)
                {
                    hsh.put(nowKey, str);
                    dbgOut("prsHsh", (new StringBuilder("String * set hash / ")).append(nowKey).append(" : ").append(str).toString(), false);
                    nowKey = "";
                } else
                {
                    nowKey = str;
                    dbgOut("prsHsh", (new StringBuilder("String * set key / ")).append(nowKey).toString(), false);
                }
                flgKey = !flgKey;
            }
        }

        return hsh;
    }

    private void dbgOut(String mthdStr, String prmStr, boolean outNowPos)
    {
        if(!mDbg)
            return;
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(mthdStr).append("] : ").append(prmStr);
        if(outNowPos)
            sb.append(" > ").append(mSrcStr.substring(mPos, mPos + mOSLen >= mLen ? mLen : mPos + mOSLen));
        System.out.println(sb.toString());
    }

    public static void dump(Object srcObj)
    {
        dump(srcObj, 0);
    }

    private static void dump(Object srcObj, int nst)
    {
        String nstStr = "";
        for(int i = 0; i < nst; i++)
            nstStr = (new StringBuilder(String.valueOf(nstStr))).append(NST_TB).toString();

        if(srcObj.getClass().getSimpleName().equals("ArrayList"))
        {
            System.out.println((new StringBuilder(String.valueOf(nstStr))).append("<Array>").toString());
            ArrayList al = (ArrayList)srcObj;
            for(int i = 0; i < al.size(); i++)
            {
                Object obj = al.get(i);
                if(obj.getClass().getSimpleName().equals("String"))
                    System.out.println((new StringBuilder(String.valueOf(nstStr))).append(NST_TB).append(obj.toString()).toString());
                else
                    dump(obj, nst + 1);
            }

            System.out.println((new StringBuilder(String.valueOf(nstStr))).append("</Array>").toString());
        } else
        if(srcObj.getClass().getSimpleName().equals("HashMap"))
        {
            System.out.println((new StringBuilder(String.valueOf(nstStr))).append("<Hash>").toString());
            HashMap hsh = (HashMap)srcObj;
            for(Iterator it = hsh.keySet().iterator(); it.hasNext();)
            {
                String key = (String)it.next();
                Object obj = hsh.get(key);
                if(obj.getClass().getSimpleName().equals("String"))
                {
                    System.out.println((new StringBuilder(String.valueOf(nstStr))).append(NST_TB).append("[").append(key).append("] : ").append(obj.toString()).toString());
                } else
                {
                    System.out.println((new StringBuilder(String.valueOf(nstStr))).append(NST_TB).append("[").append(key).append("]").toString());
                    dump(obj, nst + 1);
                }
            }

            System.out.println((new StringBuilder(String.valueOf(nstStr))).append("</Hash>").toString());
        }
    }

    private String mSrcStr;
    private int mPos;
    private int mLen;
    public boolean mDbg;
    public int mOSLen;
    private static String NST_TB = "  ";

}
