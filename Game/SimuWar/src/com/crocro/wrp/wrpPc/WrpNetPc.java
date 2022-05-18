package com.crocro.wrp.wrpPc;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.mng.MngOpt;
import com.crocro.wrp.wrp.WrpEnv;
import com.crocro.wrp.wrp.WrpNet;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class WrpNetPc
  implements WrpNet
{
  private AppLoop mAL = null;
  private static final int CNNCT_TM_OUT = 1000;
  private static final int READ_TM_OUT = 1000;

  public void setAppLoop(AppLoop al)
  {
    this.mAL = al;
  }

  public String getHttp(String urlStr)
  {
    return getHttp(urlStr, 1000, 1000);
  }

  public String getHttp(String urlStr, int cnnctTmOut_, int rdTmOut_)
  {
    StringBuilder sb = new StringBuilder();
    URL url = null;
    HttpURLConnection http = null;
    BufferedReader reader = null;
    int cnnctTmOut = cnnctTmOut_;
    int rdTmOut = rdTmOut_;

    MngOpt mo = new MngOpt(this.mAL, "netOpt.dat", true);
    if (!mo.mLdSccs)
    {
      mo.setS("http.proxyHost", "");
      mo.setS("http.proxyPort", "");
      mo.setI("cnnctTmOut", 1000);
      mo.setI("readTmOut", 1000);
      mo.sv();
    }
    if (mo.getS("http.proxyHost").length() > 0) {
      System.setProperty("http.proxyHost", mo.getS("http.proxyHost"));
    }
    if (mo.getS("http.proxyPort").length() > 0) {
      System.setProperty("http.proxyPort", mo.getS("http.proxyPort"));
    }
    if (mo.getI("cnnctTmOut") > 0) {
      cnnctTmOut = mo.getI("cnnctTmOut");
    }
    if (mo.getI("readTmOut") > 0) {
      rdTmOut = mo.getI("readTmOut");
    }

    try
    {
      url = new URL(urlStr);
      http = (HttpURLConnection)url.openConnection();
      http.setConnectTimeout(cnnctTmOut);
      http.setReadTimeout(rdTmOut);
      http.connect();

      reader = new BufferedReader(
        new InputStreamReader(http.getInputStream(), "UTF-8"));

      String buffer = reader.readLine();
      sb.append(buffer).append("\n");

      while (buffer != null) {
        buffer = reader.readLine();
        if (buffer == null) continue; sb.append(buffer).append("\n");
      }
    } catch (Exception e) {
      this.mAL.mWE.dbgErr(e, "getHttp : urlStr : " + 
        urlStr + 
        " http.proxyHost " + System.getProperty("http.proxyHost") + 
        " http.proxyPort " + System.getProperty("http.proxyPort") + 
        " cnnctTmOut " + cnnctTmOut + 
        " readTmOut " + rdTmOut);

      if (reader != null)
        try {
          reader.close();
          reader = null;
        }
        catch (Exception localException1)
        {
        }
    }
    finally
    {
      if (reader != null)
        try {
          reader.close();
          reader = null;
        }
        catch (Exception localException2)
        {
        }
    }
    return sb.toString();
  }

  public void openBrwsr(String urlStr)
  {
    String openUrlStr = urlStr;

    if (openUrlStr.startsWith("./")) {
      openUrlStr = 
        "file:///" + 
        System.getProperty("user.dir")
        .replace(File.separatorChar, '/') + 
        "/assets/" + 
        openUrlStr.substring(2);
    }

    try
    {
      Desktop desktop = Desktop.getDesktop();
      desktop.browse(new URI(openUrlStr));
    } catch (Exception e) {
      this.mAL.mWE.dbgErr(e, "openBrwsr : " + urlStr);
    }
  }
}