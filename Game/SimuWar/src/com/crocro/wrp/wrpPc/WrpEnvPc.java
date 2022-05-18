// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrpEnvPc.java

package com.crocro.wrp.wrpPc;

import com.crocro.wrp.utl.Actr;
import com.crocro.wrp.utl.UtlCmnPc;
import com.crocro.wrp.wrp.WrpEnv;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WrpEnvPc
    implements WrpEnv
{

    public WrpEnvPc()
    {
        mFrm = null;
        mWaitDlg = false;
        mWaitDlgNxt = false;
    }

    public WrpEnvPc(Frame frm)
    {
        mFrm = null;
        mWaitDlg = false;
        mWaitDlgNxt = false;
        mFrm = frm;
    }

    public void dbgOut(String s)
    {
        System.out.println(s);
    }

    public void dbgErr(String s)
    {
        System.err.println(s);
    }

    public void dbgErr(Exception e, String s)
    {
        System.err.println(UtlCmnPc.mkExcp(e, s));
    }

    public void setTtl(String strTtl)
    {
        if(mFrm != null)
            mFrm.setTitle(strTtl);
    }

    public boolean chkWaitDlg()
    {
        return mWaitDlg;
    }

    public void inputDlg(String mesStr, String dfltStr, Actr actr)
    {
        mWaitDlg = true;
        mWaitDlgNxt = true;
        mActr = actr;
        mPane = new JOptionPane(mesStr, 3);
        mPane.setWantsInput(true);
        mPane.setInputValue(dfltStr);
        JDialog dlg = mPane.createDialog(mFrm, "Input Dialog");
        dlg.setModal(false);
        dlg.addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent windowevent)
            {
            }

            public void windowClosed(WindowEvent windowevent)
            {
            }

            public void windowClosing(WindowEvent windowevent)
            {
            }

            public void windowDeactivated(WindowEvent arg0)
            {
                mWaitDlgNxt = false;
                mActr.act(new Object[] {
                    mPane.getInputValue()
                });
                mPane = null;
                if(!mWaitDlgNxt)
                    mWaitDlg = false;
            }

            public void windowDeiconified(WindowEvent windowevent)
            {
            }

            public void windowIconified(WindowEvent windowevent)
            {
            }

            public void windowOpened(WindowEvent windowevent)
            {
            }

      
        }
);
        dlg.setVisible(true);
    }

    public void messageDlg(String ttlStr, String mesStr, Actr actr)
    {
        mWaitDlg = true;
        mWaitDlgNxt = true;
        mActr = actr;
        mPane = new JOptionPane(mesStr, -1);
        JDialog dlg = mPane.createDialog(mFrm, "Message Dialog");
        dlg.setModal(false);
        dlg.addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent windowevent)
            {
            }

            public void windowClosed(WindowEvent windowevent)
            {
            }

            public void windowClosing(WindowEvent windowevent)
            {
            }

            public void windowDeactivated(WindowEvent arg0)
            {
                mWaitDlgNxt = false;
                mActr.act();
                mPane = null;
                if(!mWaitDlgNxt)
                    mWaitDlg = false;
            }

            public void windowDeiconified(WindowEvent windowevent)
            {
            }

            public void windowIconified(WindowEvent windowevent)
            {
            }

            public void windowOpened(WindowEvent windowevent)
            {
            }

   
        }
);
        dlg.setVisible(true);
    }

    public void rootBck()
    {
    }

    public void usrExit()
    {
        System.exit(0);
    }

    public Frame mFrm;
    private boolean mWaitDlg;
    private boolean mWaitDlgNxt;
    private JOptionPane mPane;
    private Actr mActr;






}
