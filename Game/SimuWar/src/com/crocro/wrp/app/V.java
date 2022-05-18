package com.crocro.wrp.app;

import com.crocro.wrp.bs.F;
import com.crocro.wrp.bs.SF;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpFile;
import java.lang.reflect.Field;

public class V
  implements F
{
  private AppLoop mAL = null;
  public static final int GLBL_DT = 0;
  public static final int LCL_DT = 1;
  public static final int SCN_DT = 2;
  public static final String GLBL_DT_FNM = "glblDt.dat.msk";
  public static final String GLBL_DT_ARR_FNM = "glblDtArr.dat.msk";
  public static final String LCL_DT_FNM = "lclDt.dat.msk";
  public static final String LCL_DT_ARR_FNM = "lclDtArr.dat.msk";
  public static final String SCN_DT_FNM = "scnDt.dat.msk";
  public static final String SCN_DT_ARR_FNM = "scnDtArr.dat.msk";
  public GD gd;
  public GDA gda;
  public LD ld;
  public LDA lda;
  public SD sd;
  public SDA sda;
  public static final int SCN_NULL = SF.seq(0);
  public static final int SCN_TTL = SF.seq();
  public static final int SCN_USR_STRT = SF.seq();

  public static final int FLW_INIT = SF.seq(0);
  public static final int FLW_WAIT = SF.seq();
  public static final int FLW_USR_STRT = SF.seq();
  public static final int LYR_BTM = 0;
  public static final int LYR_TOP = 7;
  public static final int LYR_TOP_OPT = 8;
  public static final int LYR_TOP_LBOX = 9;
  public static final int LYR_MAX = 10;

  public V(AppLoop al)
  {
    this.mAL = al;
  }

  public void init()
  {
    this.gd = new GD();
    this.gda = new GDA();
    this.ld = new LD();
    this.lda = new LDA();
    this.sd = new SD();
    this.sda = new SDA();
  }

  public void svDt(int typ)
  {
    if (typ == 0) {
      dat2F(this.gd, "glblDt.dat.msk");
      dat2F(this.gda, "glblDtArr.dat.msk");
    }
    else if (typ == 1) {
      dat2F(this.ld, "lclDt.dat.msk");
      dat2F(this.lda, "lclDtArr.dat.msk");
    }
    else if (typ == 2) {
      dat2F(this.sd, "scnDt.dat.msk");
      dat2F(this.sda, "scnDtArr.dat.msk");
    }
  }

  public boolean ldDt(int typ)
  {
    boolean res = false;

    if (typ == 0) {
      res = (f2Dat(this.gd, "glblDt.dat.msk")) && 
        (f2Dat(this.gda, "glblDtArr.dat.msk"));
    }
    else if (typ == 1) {
      res = (f2Dat(this.ld, "lclDt.dat.msk")) && 
        (f2Dat(this.lda, "lclDtArr.dat.msk"));
    }
    else if (typ == 2) {
      res = (f2Dat(this.sd, "scnDt.dat.msk")) && 
        (f2Dat(this.sda, "scnDtArr.dat.msk"));
    }

    return res;
  }

  public void dat2F(Object obj, String pth)
  {
    Field[] flds = obj.getClass().getFields();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < flds.length; i++)
    {
      try
      {
        if (flds[i].getType().getName().equals("java.lang.String"))
        {
          String nm = flds[i].getName();
          String str = (String)flds[i].get(obj);
          sb.append(nm).append("=").append(str).append("\n");
        }
        else if ((flds[i].getType().isArray()) && 
          (flds[i].getType().getSimpleName().equals("int[]")))
        {
          int[] arr = (int[])flds[i].get(obj);

          String nm = flds[i].getName() + ".sz";
          int prm = arr.length;
          sb.append(nm).append("=").append(prm).append("\n");

          for (int j = 0; j < arr.length; j++)
          {
            nm = flds[i].getName() + "." + j;
            prm = arr[j];
            sb.append(nm).append("=").append(prm).append("\n");
          }
        }
        else
        {
          if (!flds[i].getType().getName().equals("int"))
            continue;
          String nm = flds[i].getName();
          int prm = flds[i].getInt(obj);
          sb.append(nm).append("=").append(prm).append("\n");
        }
      } catch (Exception e) {
    	  e.printStackTrace();
        this.mAL.mWE.dbgErr(e, "dat2F Fields");
      }
    }

    try
    {
      this.mAL.mWF.setFBArr(pth, sb.toString().getBytes("UTF-8"));
    } catch (Exception e) {
    	e.printStackTrace();
      this.mAL.mWE.dbgErr(e, "dat2F getBytes");
    }
  }

  public boolean f2Dat(Object obj, String pth)
  {
    MngOpt mngOpt = new MngOpt(this.mAL, pth, true);
    if (!mngOpt.mLdSccs) return false;
    return f2Dat(obj, mngOpt);
  }

  public boolean f2Dat(Object obj, MngOpt mngOpt)
  {
    Field[] flds = obj.getClass().getFields();

    for (int i = 0; i < flds.length; i++)
    {
      try
      {
        if (flds[i].getType().getName().equals("java.lang.String")) {
          String nm = flds[i].getName();
          if (mngOpt.exist(nm)) {
            String str = mngOpt.getS(nm);
            flds[i].set(obj, str);
          }

        }
        else if ((flds[i].getType().isArray()) && 
          (flds[i].getType().getSimpleName().equals("int[]")))
        {
          String nm = flds[i].getName();
          if (mngOpt.exist(nm + ".sz")) {
            int sz = mngOpt.getI(nm + ".sz");
            int[] arr = new int[sz];

            for (int j = 0; j < sz; j++) {
              if (mngOpt.exist(nm + "." + j)) {
                arr[j] = mngOpt.getI(nm + "." + j);
              }
            }

            flds[i].set(obj, arr);
          }
        }
        else {
          if (!flds[i].getType().getName().equals("int"))
            continue;
          String nm = flds[i].getName();
          if (mngOpt.exist(nm))
            flds[i].setInt(obj, mngOpt.getI(nm));
        }
      } catch (Exception e) {
    	  e.printStackTrace();
        this.mAL.mWE.dbgErr(e, "f2Dat");
      }

    }

    mngOpt.finish();

    return true;
  }

  public void svQck()
  {
    svDt(0);
    svDt(1);
    svDt(2);
  }

  public void ldQck()
  {
    ldDt(0);
    ldDt(1);
    ldDt(2);
  }

  public class GD
  {
    public int qckSv;
    public int sndVol = -1;
    public int autoHrdVol = 1;
    public int chkNws = 0;

    public GD()
    {
    }
  }

  public class GDA
  {
    public GDA()
    {
    }
  }

  public class LD
  {
    public LD()
    {
    }
  }

  public class LDA
  {
    public LDA()
    {
    }
  }

  public class SD
  {
    public int arrPntrScn;
    public int arrPntrFlw;

    public SD()
    {
    }
  }

  public class SDA
  {
    public int[] scn;
    public int[] flw;

    public SDA()
    {
    }
  }
}