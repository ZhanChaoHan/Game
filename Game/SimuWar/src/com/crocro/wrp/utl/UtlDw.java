// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtlDw.java

package com.crocro.wrp.utl;

import com.crocro.wrp.wrp.WrpDw;
import java.util.ArrayList;

// Referenced classes of package com.crocro.wrp.utl:
//            UtlTool

public class UtlDw
{
    public static class StringPrm
    {

        public String s;
        public int thisW;
        public int maxW;

        public StringPrm()
        {
            s = "";
            thisW = 0;
            maxW = 0;
        }
    }


    public UtlDw()
    {
    }

    public static StringPrm[] getStrWrp(String srcStr, int chrW, int wChrW, int maxW)
    {
        if(maxW < wChrW)
            return (new StringPrm[] {
                new StringPrm()
            });
        StringPrm resArr[] = {
            new StringPrm()
        };
        ArrayList lstArr = new ArrayList();
        if(srcStr == null || srcStr.length() <= 0)
            return resArr;
        String spltArr[] = srcStr.split("\n", -1);
        int sPos = 0;
        int sLen = 0;
        int sW = 0;
        for(int i = 0; i < spltArr.length; i++)
        {
            for(int j = 0; j < spltArr[i].length(); j++)
            {
                int tmpW = 0;
                char c = spltArr[i].charAt(j);
                if(c <= '~' || c == '\245' || c == '\u203E' || c >= '\uFF61' && c <= '\uFF9F')
                    tmpW = chrW;
                else
                    tmpW = wChrW;
                if(sW + tmpW > maxW)
                {
                    StringPrm sdp = new StringPrm();
                    sdp.s = spltArr[i].substring(sPos, sPos + sLen);
                    sdp.thisW = sW;
                    lstArr.add(sdp);
                    sPos = j;
                    j--;
                    sLen = 0;
                    sW = 0;
                } else
                {
                    sLen++;
                    sW += tmpW;
                }
            }

            StringPrm sdp = new StringPrm();
            sdp.s = spltArr[i].substring(sPos, sPos + sLen);
            sdp.thisW = sW;
            lstArr.add(sdp);
            sPos = 0;
            sLen = 0;
            sW = 0;
        }

        int sz = lstArr.size();
        int maxStrW = 0;
        if(lstArr.size() > 0)
        {
            resArr = new StringPrm[lstArr.size()];
            for(int i = 0; i < sz; i++)
            {
                resArr[i] = (StringPrm)lstArr.get(i);
                if(resArr[i].thisW > maxStrW)
                    maxStrW = resArr[i].thisW;
            }

        }
        for(int i = 0; i < sz; i++)
            resArr[i].maxW = maxStrW;

        return resArr;
    }

    public static void dwTile(WrpDw wd, int dwImgId, int dX, int dY, int dW, int dH)
    {
        dwTile(wd, dwImgId, dX, dY, 0, 0, dW, dH);
    }

    public static void dwTile(WrpDw wd, int dwImgId, int dX, int dY, int rX, int rY, int dW, int dH)
    {
        int iW = wd.getBufW(dwImgId);
        int iH = wd.getBufH(dwImgId);
        int maxX = dX + dW;
        int maxY = dY + dH;
        int rOnceX = UtlTool.getLoopNo(rX, iW);
        int rOnceY = UtlTool.getLoopNo(rY, iH);
        for(int posY = dY; posY < maxY;)
        {
            int h = iH - rOnceY;
            for(int posX = dX; posX < maxX;)
            {
                int w = iW - rOnceX;
                if(posX + w > maxX)
                    w = maxX - posX;
                if(posY + h > maxY)
                    h = maxY - posY;
                wd.dwImg(dwImgId, posX, posY, rOnceX, rOnceY, w, h);
                posX += w;
                rOnceX = 0;
            }

            posY += h;
            rOnceY = 0;
        }

    }

    public static void dwBtn(WrpDw wd, int x, int y, int w, int h, int colBs, int colLght, int colDrk)
    {
        wd.useCol(colDrk);
        wd.flRRct(x, y, w, h, 12, 12);
        wd.useCol(colLght);
        wd.flRRct(x, y, w - 4, h - 4, 12, 12);
        wd.useCol(colBs);
        wd.flRRct(x + 4, y + 4, w - 8, h - 8, 10, 10);
        wd.useCol(WrpDw.COL_BLACK);
        wd.setStrk(3);
        wd.dwRRct(x, y, w, h, 12, 12);
        wd.setStrk(1);
    }

    public static void dwBfrBtnBs(WrpDw wd, int x, int y, int w, int h)
    {
        dwBtn(wd, x, y, w, h, WrpDw.COL_BTN_BFR, WrpDw.COL_BTN_BFR_LGHT, WrpDw.COL_BTN_BFR_DARK);
    }

    public static void dwAftrBtnBs(WrpDw wd, int x, int y, int w, int h)
    {
        dwBtn(wd, x, y, w, h, WrpDw.COL_BTN_AFTR, WrpDw.COL_BTN_AFTR_LGHT, WrpDw.COL_BTN_AFTR_DARK);
    }

    public static void dwChkOn(WrpDw wd, int x, int y, int w, int h)
    {
        dwBtn(wd, x, y, w, h, WrpDw.COL_GREEN, WrpDw.COL_LGHT_GREEN, WrpDw.COL_DARK_GREEN);
    }

    public static void dwChkOff(WrpDw wd, int x, int y, int w, int h)
    {
        dwBtn(wd, x, y, w, h, WrpDw.COL_GRAY, WrpDw.COL_LGHT_GRAY, WrpDw.COL_DARK_GRAY);
    }

    public static void dwTblCellBs(WrpDw wd, int x, int y, int w, int h)
    {
        dwTblCellBs(wd, x, y, w, h, WrpDw.COL_BTN_BFR);
    }

    public static void dwTblCellBs(WrpDw wd, int x, int y, int w, int h, int colId)
    {
        wd.useCol(WrpDw.COL_BTN_BFR_DARK);
        wd.flRct(x, y, w, h);
        for(int i = 1; i >= 0; i--)
        {
            int x2 = (x + 3) - i;
            int y2 = (y + 3) - i;
            int w2 = (w - 6) + i * 2;
            int h2 = (h - 6) + i * 2;
            if(i == 1)
                wd.useCol(WrpDw.COL_BTN_BFR_LGHT);
            else
                wd.useCol(colId);
            wd.flRRct(x2, y2, w2, h2, 12, 12);
        }

    }

    public static void dwCntrStr(WrpDw wd, int x, int y, int w, int h, String srcStr, com.crocro.wrp.wrp.WrpDw.StrFntOpt strFntOpt)
    {
        dwCntrStr(wd, x, y, w, h, srcStr, strFntOpt, null);
    }

    public static void dwCntrStr(WrpDw wd, int x, int y, int w, int h, String srcStr, com.crocro.wrp.wrp.WrpDw.StrFntOpt strFntOpt, com.crocro.wrp.wrp.WrpDw.StrFrmOpt frmOpt)
    {
        int wStr = wd.getStrW(srcStr, strFntOpt, 999);
        int hStr = wd.getStrH(srcStr, strFntOpt, 999);
        int xDw = (x + w / 2) - wStr / 2;
        int yDw = (y + h / 2) - hStr / 2;
        wd.dwStr(srcStr, xDw, yDw, 999, strFntOpt, frmOpt);
    }

    public static void dwNmbr(WrpDw wd, int imgId, double no, int x, int y, int algnmnt)
    {
        String sFrmt;
        if(no > 0.0D)
        {
            int fig3 = (int)(no * 1000D);
            char noChrs[] = Integer.toString(fig3).toCharArray();
            int minLen = 4;
            int srcLen = noChrs.length;
            int newLen = (srcLen <= minLen ? minLen : srcLen) + 1;
            char noChrs2[] = new char[newLen];
            noChrs2[0] = noChrs2[1] = noChrs2[2] = noChrs2[3] = noChrs2[4] = '0';
            for(int i = 0; i < srcLen + 1; i++)
            {
                int newPos = newLen - 1 - i;
                int srcPos;
                if(i < 4)
                {
                    srcPos = srcLen - 1 - i;
                    if(srcPos < 0)
                        break;
                } else
                {
                    if(i == 3)
                        continue;
                    srcPos = srcLen - i;
                }
                noChrs2[newPos] = noChrs[srcPos];
            }

            noChrs2[newLen - 4] = '.';
            sFrmt = new String(noChrs2);
        } else
        {
            sFrmt = "0.000";
        }
        dwNmbr(wd, imgId, sFrmt, x, y, algnmnt);
    }

    public static void dwNmbr(WrpDw wd, int imgId, int no, int x, int y, int algnmnt)
    {
        dwNmbr(wd, imgId, String.valueOf(no), x, y, algnmnt);
    }

    public static void dwNmbr(WrpDw wd, int imgId, String noStr, int x, int y, int algnmnt)
    {
        if(noStr == null || noStr.length() == 0)
            return;
        int w = wd.getBufW(imgId) / 11;
        int h = wd.getBufH(imgId);
        if(algnmnt == 0)
            x -= w * noStr.length();
        else
        if(algnmnt == 2)
            x -= (w * noStr.length()) / 2;
        for(int i = 0; i < noStr.length(); i++)
        {
            char c = noStr.charAt(i);
            int pos = 0;
            switch(c)
            {
            case 48: // '0'
                pos = 0;
                break;

            case 49: // '1'
                pos = 1;
                break;

            case 50: // '2'
                pos = 2;
                break;

            case 51: // '3'
                pos = 3;
                break;

            case 52: // '4'
                pos = 4;
                break;

            case 53: // '5'
                pos = 5;
                break;

            case 54: // '6'
                pos = 6;
                break;

            case 55: // '7'
                pos = 7;
                break;

            case 56: // '8'
                pos = 8;
                break;

            case 57: // '9'
                pos = 9;
                break;

            case 46: // '.'
                pos = 10;
                break;
            }
            wd.dwImg(imgId, x + i * w, y, pos * w, 0, w, h);
        }

    }

    public static void prcBufFromS(WrpDw wd, int imgId, String fncStr)
    {
        String fnc[] = fncStr.split(",");
        for(int i = 0; i < fnc.length; i++)
        {
            int prcTyp = 0;
            if(fnc[i].equals("rtt90"))
                prcTyp = 1;
            else
            if(fnc[i].equals("rtt180"))
                prcTyp = 2;
            else
            if(fnc[i].equals("rtt270"))
                prcTyp = 3;
            else
            if(fnc[i].equals("mrrH"))
                prcTyp = 5;
            else
            if(fnc[i].equals("mrrV"))
                prcTyp = 4;
            wd.prcBuf(imgId, prcTyp);
        }

    }

    private static final int BTN_FRM_SZ = 3;
    private static final int BTN_3D_H = 4;
    public static final int BTN_R = 12;
    private static final int CELL_R = 12;
    private static final int CELL_PDDNG = 3;
    public static final int ALGNMNT_L = 0;
    public static final int ALGNMNT_R = 1;
    public static final int ALGNMNT_C = 2;
    public static final int NMBR_SPLT = 11;
}
