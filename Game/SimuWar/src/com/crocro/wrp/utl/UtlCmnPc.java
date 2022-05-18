// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtlCmnPc.java

package com.crocro.wrp.utl;

import java.io.PrintStream;
import java.util.Calendar;

public class UtlCmnPc
{

    public UtlCmnPc()
    {
    }

    public static void prntExcp(Exception e, String mesStr)
    {
        System.err.println(mkExcp(e, mesStr));
    }

    public static String mkExcp(Exception e, String mesStr)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("-----<").append(getDtStr()).append(">-----\n");
        if(mesStr.length() > 0)
            sb.append(mesStr).append("\n");
        sb.append(getStePrms(Thread.currentThread().getStackTrace()[3])).append("\n");
        if(e != null)
        {
            sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
            StackTraceElement steE[] = e.getStackTrace();
            for(int i = 0; i < steE.length; i++)
                sb.append("\tat ").append(steE[i].toString()).append("\n");

        }
        
        return sb.toString();
    }

    private static String getStePrms(StackTraceElement ste)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("From : ").append(ste.getClassName()).append(" ").append(ste.getMethodName()).append(" (").append(ste.getFileName()).append(":").append(ste.getLineNumber()).append(")");
        return sb.toString();
    }

    public static String getDtStr()
    {
        String resStr = "";
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(1);
        int month = cal1.get(2) + 1;
        int day = cal1.get(5);
        int hour = cal1.get(11);
        int minute = cal1.get(12);
        int second = cal1.get(13);
        resStr = String.format("%04d/%02d/%02d %02d:%02d:%02d", new Object[] {
            Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)
        });
        return resStr;
    }
}
