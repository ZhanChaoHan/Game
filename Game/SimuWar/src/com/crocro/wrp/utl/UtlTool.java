package com.crocro.wrp.utl;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.wrp.WrpEnv;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;

public class UtlTool
{
  private static final NumberFormat mNF = NumberFormat.getInstance();
  private static final NumberFormat mNFDgt = NumberFormat.getInstance();

  private static StringBuilder gSB = new StringBuilder();
  private static long gMemTtl = 0L;
  private static long gMemUseOld = 0L;

  private static long gTmOld = System.currentTimeMillis();

  public static int getLoopNo(int srcNo, int loopMax)
  {
    int resNo = srcNo;

    if (resNo >= 0)
    {
      resNo %= loopMax;
    }
    else {
      int rvNo = resNo * -1;
      int upNo = (rvNo + loopMax) % loopMax * loopMax;
      resNo = (resNo + upNo) % loopMax;
    }

    return resNo;
  }

  public static int sToI(String srcStr)
  {
    int resNo = 0;
    try {
      resNo = Integer.parseInt(srcStr); } catch (Exception ex) {
    	
    }
    return resNo;
  }

  public static int[] sToIAr(String srcStr)
  {
    int[] resArr = (int[])null;
    if ((srcStr == null) || (srcStr.length() == 0)) return resArr;
    try
    {
      String[] strPrms = srcStr.split(",");
      resArr = new int[strPrms.length];

      for (int i = 0; i < strPrms.length; i++)
      {
        resArr[i] = sToI(strPrms[i]);
      }
    } catch (Exception ex) {
    	ex.printStackTrace();
    }
    return resArr;
  }

  public static int[][] sToI2dAr(String srcStr)
  {
    int[][] resArr = (int[][])null;
    if ((srcStr == null) || (srcStr.length() == 0)) return resArr;
    try
    {
      String[] strLns = srcStr.split("\n");
      resArr = new int[strLns.length][];

      for (int i = 0; i < strLns.length; i++)
      {
        String[] strPrms = strLns[i].split(",");
        resArr[i] = new int[strPrms.length];

        for (int j = 0; j < strPrms.length; j++)
          resArr[i][j] = sToI(strPrms[j]);
      }
    } catch (Exception ex) {
    	ex.printStackTrace();
    }
    return resArr;
  }

  public static String joinI(int[] iArr, String spltr)
  {
    if ((iArr == null) || (iArr.length == 0)) return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < iArr.length; i++) {
      if (i > 0) sb.append(spltr);
      sb.append(iArr[i]);
    }
    return sb.toString();
  }

  public static String[][] sToS2dAr(String srcStr)
  {
    String[][] resArr = (String[][])null;
    if ((srcStr == null) || (srcStr.length() == 0)) return resArr;
    try
    {
      String[] strLns = srcStr.split("\n");
      resArr = new String[strLns.length][];

      for (int i = 0; i < strLns.length; i++)
      {
        String[] strPrms = strLns[i].split(",");
        resArr[i] = new String[strPrms.length];

        for (int j = 0; j < strPrms.length; j++)
          resArr[i][j] = strPrms[j];
      }
    } catch (Exception ex) {
    }
    return resArr;
  }

  public static String bToS(byte[] bArr)
  {
    String resStr = "";
    try
    {
      resStr = new String(bArr, "UTF-8");
    }
    catch (Exception ex)
    {
    	ex.printStackTrace();
    }

    return resStr;
  }

  public static byte[] isToBArr(InputStream is, String dbgFrm)
  {
    if (is == null) return new byte[0];

    byte[] buf = new byte[1024];
    byte[] resArr = new byte[0];
    ByteArrayOutputStream bos = null;
    try
    {
      bos = new ByteArrayOutputStream();
      int n;
      while ((n = is.read(buf, 0, buf.length)) != -1)
      {
      
        bos.write(buf, 0, n);
      }
      resArr = bos.toByteArray();
    }
    catch (Exception ex)
    {
      if (is != null) try { is.close(); } catch (Exception localException1) {
        } if (bos != null) try { bos.close();
        }
        catch (Exception localException2)
        {
        }
    }
    finally
    {
      if (is != null) try { is.close(); } catch (Exception localException3) {
        } if (bos != null) try { bos.close();
        } catch (Exception localException4)
        {
        } 
    }
    return resArr;
  }

  public static String repAll(String srcStr, String srchStr, String repStr)
  {
    if ((srcStr == null) || (srchStr == null) || (repStr == null)) return srcStr;
    if ((srcStr.length() == 0) || (srchStr.length() == 0)) return srcStr;

    int strt = 0;
    int end = srcStr.indexOf(srchStr, strt);
    if (end == -1) return srcStr;

    int repLen = srchStr.length();
    int increase = repStr.length() - repLen;
    increase = increase >= 0 ? increase * 16 : 0;
    StringBuilder buf = new StringBuilder(srcStr.length() + increase);

    while (end != -1) {
      buf.append(srcStr.substring(strt, end)).append(repStr);
      strt = end + repLen;
      end = srcStr.indexOf(srchStr, strt);
    }

    buf.append(srcStr.substring(strt));

    return buf.toString();
  }

  public static String replOne(String srcStr, String srchStr, String repStr)
  {
    if ((srcStr == null) || (srchStr == null) || (repStr == null)) return srcStr;
    if ((srcStr.length() == 0) || (srchStr.length() == 0)) return srcStr;

    int strt = 0;
    int end = srcStr.indexOf(srchStr, strt);
    if (end == -1) return srcStr;

    int repLen = srchStr.length();
    int increase = repStr.length() - repLen;
    increase = increase >= 0 ? increase : 0;
    StringBuilder buf = new StringBuilder(srcStr.length() + increase);

    buf.append(srcStr.substring(strt, end)).append(repStr);
    strt = end + repLen;
    buf.append(srcStr.substring(strt));

    return buf.toString();
  }

  public static String smplFormat(String srcStr, Object[] args)
  {
    if ((srcStr == null) || (args == null)) return srcStr;
    int srcLen = srcStr.length();
    int argLen = args.length;
    if ((srcLen == 0) || (args.length == 0)) return srcStr;

    int pos = 0;
    String srchStr = "%";

    int strt = 0;
    int end = srcStr.indexOf(srchStr, strt);
    if (end == -1) return srcStr;

    int repLen = 1;
    int increase = args.length * 32;
    StringBuilder buf = new StringBuilder(srcStr.length() + increase);

    while ((end != -1) && 
      (pos < argLen))
    {
      if (end + 1 < srcLen) {
        if ((end + 1 < srcLen) && 
          (srcStr.charAt(end + 1) == '%'))
        {
          repLen = 2;
          buf.append(srcStr.substring(strt, end)).append("%");
          pos--;
        }
        else if ((end + 1 < srcLen) && (
          (srcStr.charAt(end + 1) == 's') || 
          (srcStr.charAt(end + 1) == 'd')))
        {
          repLen = 2;
          try {
            String repStr = args[pos].getClass().isPrimitive() ? String.valueOf(args[pos]) : args[pos].toString();
            buf.append(srcStr.substring(strt, end)).append(repStr);
          } catch (Exception localException1) {
          }
        } else if ((end + 2 < srcLen) && 
          (srcStr.charAt(end + 1) == '+') && 
          (srcStr.charAt(end + 2) == 'd'))
        {
          repLen = 3;
          try {
            int no = ((Integer)args[pos]).intValue();
            buf.append(srcStr.substring(strt, end));
            if (no >= 0) buf.append('+');
            buf.append(no);
          } catch (Exception localException2) {
          }
        } else if ((end + 2 < srcLen) && 
          (srcStr.charAt(end + 1) >= '2') && 
          (srcStr.charAt(end + 1) <= '9') && 
          (srcStr.charAt(end + 2) == 'd'))
        {
          repLen = 3;
          try {
            int fig = srcStr.charAt(end + 1) - '0';
            int no = ((Integer)args[pos]).intValue();
            int figClc = no;

            buf.append(srcStr.substring(strt, end));
            for (int i = 1; i < fig; i++) {
              figClc /= 10;
              if (figClc != 0) continue; buf.append(' ');
            }
            buf.append(no);
          } catch (Exception localException3) {
          }
        } else if ((end + 3 < srcLen) && 
          (srcStr.charAt(end + 1) >= '.') && 
          (srcStr.charAt(end + 2) >= '1') && 
          (srcStr.charAt(end + 2) <= '9') && 
          (srcStr.charAt(end + 3) == 'f'))
        {
          repLen = 4;
          try
          {
            int fig = srcStr.charAt(end + 2) - '0';
            int figUp = 1;
            for (int i = 0; i < fig; i++) figUp *= 10;

            double noF = 0.0D;
            if (args[pos].getClass().getSimpleName().indexOf("Double") >= 0) {
              noF = ((Double)args[pos]).floatValue();
            }
            else if (args[pos].getClass().getSimpleName().indexOf("Float") >= 0) {
              noF = ((Float)args[pos]).floatValue();
            }

            int noHd = (int)noF;
            int noTl = (int)((noF - noHd) * figUp);
            int figClc = noTl;

            buf.append(srcStr.substring(strt, end));
            buf.append(noHd).append('.');
            for (int i = 1; i < fig; i++) {
              figClc /= 10;
              if (figClc != 0) continue; buf.append('0');
            }
            buf.append(noTl);
          } catch (Exception e) {
            System.out.println(e);
          }
        }
        pos++;
      }

      strt = end + repLen;

      end = srcStr.indexOf(srchStr, strt);
    }

    buf.append(srcStr.substring(strt));

    return buf.toString();
  }

  public static int clcDegTop(int xOrzn, int yOrzn, int xPos, int yPos)
  {
    int deg = 0;

    int x = xPos - xOrzn;
    int y = yPos - yOrzn;

    double s = Math.acos(x / Math.sqrt(x * x + y * y));

    s = s / 3.141592653589793D * 180.0D;

    if (y < 0) s = 360.0D - s;

    deg = (int)Math.floor(s);
    if (s - deg >= 0.5D) deg++;

    deg = (deg + 90) % 360;

    return deg;
  }

  public static int getDegX(int deg, int xSrc)
  {
    int xRes = 0;
    double rad = deg * 3.141592653589793D / 180.0D;

    xRes = (int)(xSrc * Math.cos(rad));

    return xRes;
  }

  public static int getDegY(int deg, int ySrc)
  {
    int yRes = 0;
    double rad = deg * 3.141592653589793D / 180.0D;

    yRes = (int)(ySrc * Math.sin(rad));

    return yRes;
  }

  public static void dbgOutMemChng(double thrshldMb, AppLoop al)
  {
    if (gMemTtl == 0L) gMemTtl = Runtime.getRuntime().totalMemory();
    long memFree = Runtime.getRuntime().freeMemory();
    long memUse = gMemTtl - memFree;

    if ((gMemUseOld == 0L) || 
      (memUse - gMemUseOld < thrshldMb * 1024.0D * 1024.0D)) {
      gMemUseOld = memUse;
      return;
    }

    long defMemUse = (memUse - gMemUseOld) / 1024L / 1024L;

    gSB.setLength(0);
    gSB
      .append("Mem Over : ").append(defMemUse).append("\n")
      .append(getStePrms(java.lang.Thread.currentThread().getStackTrace()[2])).append("\n");

    al.mWE.dbgErr(gSB.toString());
    gMemUseOld = memUse;
  }

  public static long dbgGetTmElpse()
  {
    long tmNow = System.currentTimeMillis();
    long tmElps = tmNow - gTmOld;
    gTmOld = tmNow;
    return tmElps;
  }

  public static String nf(int i)
  {
    return mNF.format(i);
  }

  public static String dgt(double d, int fig)
  {
    mNFDgt.setMaximumFractionDigits(fig);
    return mNFDgt.format(d);
  }

  public static String getPrmFrmHtml(String keyStr, String htmlStr)
  {
    String resPrm = "";

    if ((keyStr == null) || (keyStr.length() == 0)) return resPrm;
    if ((htmlStr == null) || (htmlStr.length() == 0)) return resPrm;

    String srchStr = "<!-- " + keyStr + "=";
    int strtPos = htmlStr.indexOf(srchStr);
    if (strtPos < 0) return resPrm;
    strtPos += srchStr.length();

    srchStr = " -->";
    int endPos = htmlStr.indexOf(srchStr, strtPos);
    if (endPos < 0) return resPrm;

    resPrm = htmlStr.substring(strtPos, endPos);

    return resPrm;
  }

  private static String getStePrms(StackTraceElement ste)
  {
    StringBuilder sb = new StringBuilder();

    sb
      .append("From : ").append(ste.getClassName())
      .append(" ").append(ste.getMethodName())
      .append(" (").append(ste.getFileName())
      .append(":").append(ste.getLineNumber())
      .append(")");

    return sb.toString();
  }

  public static void dump(Object obj, AppLoop al)
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      Field[] flds = obj.getClass().getFields();
      al.mWE.dbgOut("-----< dump (" + flds.length + ") >-----");
      for (int i = 0; i < flds.length; i++) {
        sb.setLength(0);
        sb
          .append(flds[i].getName())
          .append(" : ");
        try {
          sb.append(flds[i].getInt(obj)); } catch (Exception e) {
          sb.append("-");
        }al.mWE.dbgOut(sb.toString());
      }
    }
    catch (Exception localException1)
    {
    }
  }

  public static void dump(Object obj)
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      Field[] flds = obj.getClass().getFields();
      System.out.println("-----< dump (" + flds.length + ") >-----");
      for (int i = 0; i < flds.length; i++) {
        sb.setLength(0);
        sb
          .append(flds[i].getName())
          .append(" : ");
        try {
          sb.append(flds[i].getInt(obj)); } catch (Exception e) {
          sb.append("-");
        }sb
          .append(" : ")
          .append(flds[i].toString());
        System.out.println(sb.toString());
      }
    }
    catch (Exception ex)
    {
    	ex.printStackTrace();
    }
  }
}