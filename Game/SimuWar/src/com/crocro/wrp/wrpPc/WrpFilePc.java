// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpFilePc.java

package com.crocro.wrp.wrpPc;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.utl.UtlMsk;
import com.crocro.wrp.utl.UtlTool;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;
import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class WrpFilePc
    implements WrpFile
{

    public WrpFilePc()
    {
        mAL = null;
        mDirBs = "dat";
        mLcncKey = null;
        mLngKey = null;
    }

    public void setAppLoop(AppLoop al)
    {
        mAL = al;
    }

    public File getDatF(String pth)
    {
        String usePth = null;
        if(pth.startsWith("./"))
            usePth = pth.substring(2);
        else
            usePth = (new StringBuilder(String.valueOf(mDirBs))).append(File.separator).append(pth).toString();
        File f = new File(usePth);
        File pf = f.getParentFile();
        if(!pf.exists())
            try
            {
                pf.mkdirs();
            }
            catch(Exception e)
            {
                mAL.mWE.dbgErr(e, (new StringBuilder(String.valueOf(pth))).append(" / Dir : ").append(pf).toString());
            }
        return f;
    }

    public byte[] getR(String pth)
    {
        return UtlTool.isToBArr(getRIS(pth), (new StringBuilder("getR ")).append(pth).toString());
    }

    public InputStream getRIS(String pth)
    {
        InputStream is = null;
        if(mLngKey != null && mLngKey.length() > 0)
        {
            String lngPth = pth.replaceFirst("^(res/[^/]+)", (new StringBuilder("$1-")).append(mLngKey).toString());
            is = getRIS_in(lngPth);
            if(is != null)
                return is;
        }
        is = getRIS_in(pth);
        return is;
    }

    private InputStream getRIS_in(String pth)
    {
        InputStream is = null;
        for(int i = 0; i < mResPck.length && i < mResRt.length; i++)
        {
            String resRt = mResRt[i];
            String resPck = mResPck[i];
            String entryPth = pth;
            if(!"res/".equals(resRt))
                entryPth = entryPth.replaceFirst("^res/", resRt);
            is = getZipIS(resPck, entryPth);
            if(is != null)
                return is;
        }

        try
        {
            URL url = getClass().getClassLoader().getResource(pth);
            is = url.openStream();
        }
        catch(Exception e)
        {
            if(is != null)
            {
                try
                {
                    is.close();
                }
                catch(Exception exception) { }
                is = null;
            }
        }
        return is;
    }

    private InputStream getZipIS(String zipPth, String entryPth)
    {
        InputStream is = null;
        File fZip = new File(zipPth);
        if(fZip.exists() && fZip.isFile())
            try
            {
                ZipFile zf = new ZipFile(fZip);
                ZipEntry ze = zf.getEntry(entryPth);
                if(ze != null)
                {
                    is = zf.getInputStream(ze);
                } else
                {
                    ze = zf.getEntry((new StringBuilder(String.valueOf(entryPth))).append(".msk").toString());
                    if(ze != null)
                    {
                        is = zf.getInputStream(ze);
                        long sz = ze.getSize();
                        is = new com.crocro.wrp.utl.UtlMsk.UnMskStream(is, sz);
                    } else
                    {
                        ze = zf.getEntry((new StringBuilder(String.valueOf(entryPth))).append(".lcnc").toString());
                        if(ze != null)
                        {
                            is = zf.getInputStream(ze);
                            long sz = ze.getSize();
                            is = new com.crocro.wrp.utl.UtlMsk.UnMskStream(is, sz, mLcncKey);
                        }
                    }
                }
                return is;
            }
            catch(Exception e)
            {
                mAL.mWE.dbgErr(e, (new StringBuilder("Zip Err : ")).append(entryPth).toString());
            }
        return is;
    }

    public byte[] getFBArr(String pth)
    {
        return UtlTool.isToBArr(getFIS(pth), (new StringBuilder("getF ")).append(pth).toString());
    }

    public InputStream getFIS(String pth)
    {
        InputStream is = null;
        File f = getDatF(pth);
        if(!f.exists())
            return is;
        try
        {
            is = new FileInputStream(f);
        }
        catch(Exception e)
        {
            mAL.mWE.dbgErr(e, pth);
        }
        if(pth.endsWith(".msk"))
            is = new com.crocro.wrp.utl.UtlMsk.UnMskStream(is, f.length());
        if(pth.endsWith(".lcnc"))
            is = new com.crocro.wrp.utl.UtlMsk.UnMskStream(is, f.length(), mLcncKey);
        return is;
    }

    public boolean setFBArr(String pth, byte[] bArr)
    {
      boolean res = true;
      FileOutputStream fOS = null;
      OutputStream oS = null;
      try
      {
        fOS = new FileOutputStream(getDatF(pth));
        oS = new BufferedOutputStream(fOS);

        if (pth.endsWith(".msk")) {
          oS = new UtlMsk.MskOutputStream(oS, bArr.length);
        }

        oS.write(bArr);
      } catch (Exception e) {
        this.mAL.mWE.dbgErr(e, "Rsc Err : " + pth);
        res = false;
      } finally {
        if (oS != null) {
          try { oS.flush(); oS.close(); } catch (Exception localException3) {
          }oS = null;
        }
        if (fOS != null) {
          try { fOS.close(); } catch (Exception localException4) {
          }fOS = null;
        }

      }

      return res;
    }

    public void setLngKey(String lngKey)
    {
        mLngKey = lngKey;
    }

    public String getLngKey()
    {
        return mLngKey;
    }

    public void setDirBs(String dirBs)
    {
        mDirBs = dirBs;
    }

    public void setLcncKey(String lcncKey)
    {
        mLcncKey = lcncKey;
    }

    public AppLoop mAL;
    private String mDirBs;
    private String mLcncKey;
    private final String RES_PCK = "res.zip";
    private final String RES_RT = "res/";
    public String mResPck[] = {
        "res.zip"
    };
    public String mResRt[] = {
        "res/"
    };
    private String mLngKey;
}
