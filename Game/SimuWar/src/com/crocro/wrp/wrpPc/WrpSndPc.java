package com.crocro.wrp.wrpPc;

import com.crocro.wrp.app.AppLoop;
import com.crocro.wrp.utl.UtlCmnPc;
import com.crocro.wrp.wrp.WrpFile;
import com.crocro.wrp.wrp.WrpSnd;
import com.crocro.wrp.wrp.WrpSnd.Prms;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.SysexMessage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class WrpSndPc
  implements WrpSnd
{
  public AppLoop mAL = null;
  private static final String PTH_SND = "res/snd/";
  private WrpFile mWF = null;

  private ArrayList<SndVrtl> mSndArrLst = new ArrayList();
  private ArrayList<WrpSnd.Prms> mPrmArrLst = new ArrayList();

  private int mVol = 100;

  LineListener gLnLstnr = new LineListener()
  {
    public void update(LineEvent evnt) {
      if (evnt.getType() == LineEvent.Type.STOP)
        try
        {
          Clip clp = (Clip)evnt.getSource();
          clp.stop();
          clp.setFramePosition(0);

          int id = -1;
          for (int i = 0; i < WrpSndPc.this.mSndArrLst.size(); i++) {
            if ((WrpSndPc.this.mSndArrLst.get(i) == null) || 
              (((WrpSndPc.SndVrtl)WrpSndPc.this.mSndArrLst.get(i)).mClp == null) || 
              (!((WrpSndPc.SndVrtl)WrpSndPc.this.mSndArrLst.get(i)).mClp.equals(clp))) continue;
            id = i; break;
          }
          if (id == -1) return;

          if ((((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(id)).typ != 3) && 
            (((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(id)).loop == 1) && 
            (((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(id)).wrnWt != 1))
          {
            WrpSndPc.this.play(id);
          }

          if (((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(id)).typ == 2)
            for (int i = 0; i < WrpSndPc.this.mSndArrLst.size(); i++) {
              if ((WrpSndPc.this.mSndArrLst.get(i) == null) || 
                (((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(i)).wrnWt != 1)) continue;
              ((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(i)).wrnWt = 0;
              ((WrpSndPc.SndVrtl)WrpSndPc.this.mSndArrLst.get(i)).start();
            }
        }
        catch (Exception e)
        {
          UtlCmnPc.prntExcp(e, "LineListener err");
        }
    }
  };

  public void setAppLoop(AppLoop al)
  {
    this.mAL = al;
    this.mWF = this.mAL.mWF;
  }

  public void freeCash()
  {
  }

  private void chngMidiVol(int vol)
  {
    try
    {
      Receiver receiver = MidiSystem.getReceiver();
      SysexMessage sysMsg = new SysexMessage();
      byte[] data = { -16, 127, 127, 4, 1, 0, (byte)(127 * vol / 100), -9 };

      sysMsg.setMessage(data, data.length);
      receiver.send(sysMsg, -1L);
    }
    catch (Exception localException)
    {
    }
  }

  public void ldSnd(int id, String fNm)
  {
    if (id < 0) return;
    if ((fNm == null) || (fNm.length() == 0)) return;

    if (id >= this.mSndArrLst.size()) {
      for (int i = this.mSndArrLst.size(); i < id + 1; i++) this.mSndArrLst.add(i, null);
    }
    this.mSndArrLst.set(id, new SndVrtl(id, fNm));

    if (id >= this.mPrmArrLst.size()) {
      for (int i = this.mPrmArrLst.size(); i < id + 1; i++) this.mPrmArrLst.add(i, null);
    }
    this.mPrmArrLst.set(id, new WrpSnd.Prms());

    if (this.mVol != 100)
      setPrm(id, 2, this.mVol);
  }

  public void delSnd(int id)
  {
    try
    {
      ((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ = 3;
      ((SndVrtl)this.mSndArrLst.get(id)).stop();
      this.mSndArrLst.set(id, null);
      this.mPrmArrLst.set(id, null);
    }
    catch (Exception localException)
    {
    }
  }

  public void setPrm(int id, int typ, int prm)
  {
    if ((id < 0) || (id >= this.mPrmArrLst.size())) return;
    if ((typ == 2) && ((prm < 0) || (100 < prm))) return;
    if (this.mPrmArrLst.get(id) == null) return;

    ((WrpSnd.Prms)this.mPrmArrLst.get(id)).set(typ, prm);

    if (typ == 2)
      ((SndVrtl)this.mSndArrLst.get(id)).volume(prm);
  }

  public int getSz()
  {
    return this.mPrmArrLst.size();
  }

  public void chngVol(int vol)
  {
    if ((this.mPrmArrLst != null) && (this.mPrmArrLst.size() > 0))
    {
      for (int i = 0; i < this.mPrmArrLst.size(); i++) {
        setPrm(i, 2, vol);
      }

      chngMidiVol(vol);
    }

    this.mVol = vol;
  }

  public int getVol()
  {
    return this.mVol;
  }

  public void play(int id)
  {
    if ((id < 0) || (id >= this.mPrmArrLst.size())) return;
    if (this.mSndArrLst.get(id) == null) return;

    if (((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ == 2) {
      for (int i = 0; i < this.mSndArrLst.size(); i++) {
        if ((this.mSndArrLst.get(id) == null) || 
          (((WrpSnd.Prms)this.mPrmArrLst.get(i)).typ != 2) || 
          (!((SndVrtl)this.mSndArrLst.get(id)).isRunning())) {
          continue;
        }
        if (((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ == 0) {
          ((WrpSnd.Prms)this.mPrmArrLst.get(id)).wrnWt = 1;
        }

        return;
      }

    }

    ((SndVrtl)this.mSndArrLst.get(id)).start();
    if (((SndVrtl)this.mSndArrLst.get(id)).mTyp == 1)
      try {
        Thread.sleep(100L);
        chngMidiVol(this.mVol);
      }
      catch (Exception localException)
      {
      }
    if (((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ == 2)
      for (int i = 0; i < this.mSndArrLst.size(); i++) {
        if ((i == id) || 
          (this.mSndArrLst.get(id) == null)) continue;
        if (((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ == 1) {
          stop(i);
        }
        else if (((WrpSnd.Prms)this.mPrmArrLst.get(id)).typ == 0) {
          ((WrpSnd.Prms)this.mPrmArrLst.get(id)).wrnWt = 1;
          stop(i);
        }
      }
  }

  public void stop(int id)
  {
    ((SndVrtl)this.mSndArrLst.get(id)).stop();
  }

  public void chng(int id, String fNm)
  {
    if (id < this.mSndArrLst.size())
    {
      if (this.mSndArrLst.get(id) == null) {
        ldSnd(id, fNm);
      }
      else if (!((SndVrtl)this.mSndArrLst.get(id)).mFNm.equals(fNm))
      {
        delSnd(id);
        ldSnd(id, fNm);
      }
    }
    else
    {
      ldSnd(id, fNm);
    }
  }

  public boolean isRunning(int id)
  {
    if ((id < 0) || (id >= this.mSndArrLst.size())) {
      return false;
    }
    boolean res = ((SndVrtl)this.mSndArrLst.get(id)).isRunning();
    return res;
  }

  public boolean enblAutoHrdVol()
  {
    return false;
  }

  public void doAutoHrdVol()
  {
  }

  public class SndVrtl
  {
    public int mId;
    public static final int TYP_WAV = 0;
    public static final int TYP_MIDI = 1;
    public int mTyp = 0;
    String mFNm;
    Clip mClp = null;
    Sequencer mMidi = null;

    public SndVrtl(int id, String fNm)
    {
      this.mId = id;
      load(fNm);
    }

    public void load(String fNm)
    {
      if ((fNm == null) || (fNm.length() == 0)) return;
      this.mFNm = fNm;

      InputStream is = WrpSndPc.this.mWF.getRIS("res/snd/" + fNm);

      if ((this.mFNm.endsWith(".wav")) || (this.mFNm.endsWith(".WAV")))
      {
        this.mTyp = 0;
        try
        {
          AudioInputStream strm = AudioSystem.getAudioInputStream(
            is);

          DataLine.Info inf = new DataLine.Info(Clip.class, strm.getFormat());

          this.mClp = ((Clip)AudioSystem.getLine(inf));
          this.mClp.addLineListener(WrpSndPc.this.gLnLstnr);
          this.mClp.open(strm);
          strm.close();
        } catch (Exception e) {
          UtlCmnPc.prntExcp(e, "load wav : " + fNm);

          if (is == null) return; try { is.close(); is = null; } catch (Exception localException1) {
          } } finally { if (is != null) try { is.close(); is = null; } catch (Exception localException2) {
            }  } try {
          is.close(); is = null;
        } catch (Exception localException3) {
        }
      } else {
        this.mTyp = 1;
        try {
          this.mMidi = MidiSystem.getSequencer();
          this.mMidi.open();
          Sequence sq = MidiSystem.getSequence(
            is);

          this.mMidi.setSequence(sq);
        } catch (Exception e) {
          UtlCmnPc.prntExcp(e, "load midi: " + fNm);

          if (is != null) try { is.close(); is = null; } catch (Exception localException4) {
            }  } finally {
          if (is != null) try { is.close(); is = null;
            } catch (Exception localException5) {
            } 
        }
      }
    }

    public boolean isRunning() {
      boolean res = false;
      if (this.mTyp == 0) {
        res = this.mClp.isRunning();
      }
      else if (this.mTyp == 1) {
        res = this.mMidi.isRunning();
      }
      return res;
    }

    public void start()
    {
      if (this.mTyp == 0)
      {
        this.mClp.start();
      }
      else if (this.mTyp == 1)
      {
        if (((WrpSnd.Prms)WrpSndPc.this.mPrmArrLst.get(this.mId)).loop == 1)
        {
          this.mMidi.addMetaEventListener(new MetaEventListener()
          {
            public void meta(MetaMessage arg) {
              if (arg.getType() == 47)
              {
                Thread thrd = new Thread() {
                  public void run() {
                    try { sleep(200L); } catch (Exception localException) {
                    }WrpSndPc.SndVrtl.this.mMidi.setMicrosecondPosition(0L);
                    WrpSndPc.SndVrtl.this.mMidi.start();
                    try { sleep(100L); } catch (Exception localException1) {
                    }WrpSndPc.this.chngMidiVol(WrpSndPc.this.mVol);
                  }
                };
                thrd.start();
              }
            } } );
        }
        this.mMidi.start();
      }
    }

    public void stop()
    {
      if (this.mTyp == 0) {
        this.mClp.stop();
      }
      else if (this.mTyp == 1)
        this.mMidi.stop();
    }

    public void volume(int prm)
    {
      if (this.mTyp == 0) {
        FloatControl volume = (FloatControl)this.mClp.getControl(FloatControl.Type.MASTER_GAIN);
        float logPrm = (float)(100.0D * Math.pow(Math.log10(prm) / Math.log10(100.0D), 1.5D));
        volume.setValue((volume.getMaximum() - volume.getMinimum()) * (logPrm / 100.0F) + volume.getMinimum());
      }
    }
  }
}