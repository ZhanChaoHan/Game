package com.crocro.wrp.utl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

public class UtlMsk
{
  public static final String MSK_EXT = ".msk";
  public static final String LCNC_EXT = ".lcnc";

  public static int getLcncNo(String lcncKey)
  {
    int resNo = 1;
    try
    {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(lcncKey.getBytes("UTF-8"));
      byte[] hash = md5.digest();

      for (int i = 0; i < hash.length; i++) {
        resNo += hash[0];
      }

      if (resNo < 0) resNo *= -1;
      resNo %= 256;
      if (resNo == 0) resNo = 1;
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }
    return resNo;
  }

  public static void mskBArr(byte[] bArr)
  {
    ByteArrayInputStream bi = null;
    MskStream ms = null;
    try
    {
      bi = new ByteArrayInputStream(bArr);
      ms = new MskStream(bi, bArr.length);
      byte[] bArr2 = new byte[bArr.length];

      ms.read(bArr2);

      System.arraycopy(bArr2, 0, bArr, 0, bArr.length);
    } catch (Exception e) {
    	e.printStackTrace();
    } finally {
      if (bi != null) {
        try { bi.close(); } catch (Exception e) {
        	e.printStackTrace();
        }bi = null;
      }
      if (ms != null) {
        try { ms.close(); } catch (Exception e) {
        	e.printStackTrace();
        }ms = null;
      }
    }
  }

  public static class MskOutputStream extends OutputStream
  {
    private OutputStream mOS;
    private long mSz;
    private long mCnt = 0L;
    private int mLcncNo = 0;

    public MskOutputStream(OutputStream os, long sz) { this.mOS = os;
      this.mSz = sz; }

    public MskOutputStream(OutputStream os, long sz, String lcncKey) {
      this.mOS = os;
      this.mSz = sz;
      if ((lcncKey != null) && (lcncKey.length() > 0)) this.mLcncNo = UtlMsk.getLcncNo(lcncKey); 
    }

    public void write(byte[] b) throws IOException
    {
      write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
      for (int i = 0; i < len; i++)
        write(b[(i + off)]);
    }

    public void write(int i)
      throws IOException
    {
      int i2 = (int)((i + this.mSz + this.mCnt / 256L + this.mLcncNo) % 256L);
      this.mCnt += 1L;
      this.mOS.write(i2);
    }

    public void flush() throws IOException {
      this.mOS.flush();
    }

    public void close() throws IOException
    {
      this.mOS.close();
      this.mOS = null;
    }
  }

  public static class MskStream extends InputStream
  {
    private InputStream mIS;
    private long mSz;
    private long mCnt = 0L;
    private int mLcncNo = 0;

    public MskStream(InputStream is, long sz) { this.mIS = is;
      this.mSz = sz; }

    public MskStream(InputStream is, long sz, String lcncKey) {
      this.mIS = is;
      this.mSz = sz;
      if ((lcncKey != null) && (lcncKey.length() > 0)) this.mLcncNo = UtlMsk.getLcncNo(lcncKey); 
    }

    public int read()
      throws IOException
    {
      int i = this.mIS.read();
      if (i == -1) return i;

      int i2 = (int)((i + this.mSz + this.mCnt / 256L + this.mLcncNo) % 256L);
      this.mCnt += 1L;
      return i2;
    }

    public void close() throws IOException
    {
      this.mIS.close();
      this.mIS = null;
    }
  }

  public static class UnMskStream extends InputStream
  {
    private InputStream mIS;
    private long mSz;
    private long mCnt = 0L;
    private int mLcncNo = 0;

    public UnMskStream(InputStream is, long sz) { this.mIS = is;
      this.mSz = sz; }

    public UnMskStream(InputStream is, long sz, String lcncKey) {
      this.mIS = is;
      this.mSz = sz;
      if ((lcncKey != null) && (lcncKey.length() > 0)) this.mLcncNo = UtlMsk.getLcncNo(lcncKey); 
    }

    public int read()
      throws IOException
    {
      int i = this.mIS.read();
      if (i == -1) return i;

      int i2 = (int)(256L + (i - this.mSz - this.mCnt / 256L - this.mLcncNo) % 256L);
      this.mCnt += 1L;
      return i2;
    }

    public void close() throws IOException
    {
      this.mIS.close();
      this.mIS = null;
    }
  }
}