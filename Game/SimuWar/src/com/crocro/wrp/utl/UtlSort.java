// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UtlSort.java

package com.crocro.wrp.utl;

import com.crocro.wrp.clckbl.Clckbl;
import java.util.Comparator;

public class UtlSort
{

    public UtlSort()
    {
    }

    public static void sort(Object srcArray[], Comparator cmprtr)
    {
        mCmprtr = cmprtr;
        int len = srcArray.length;
        if(mHeap == null || mHeap.length != len)
            mHeap = new Object[len];
        mNum = 0;
        for(int trgt = 0; trgt < len; trgt++)
            insert(srcArray[trgt]);

        for(int trgt = 0; mNum > 0; trgt++)
            srcArray[trgt] = deletemin();

        mCmprtr = null;
    }

    public static void insert(Object obj)
    {
        Object heap[] = mHeap;
        Comparator cmprtr = mCmprtr;
        heap[mNum++] = obj;
        int i = mNum;
        for(int j = i / 2; i > 1; j = i / 2)
        {
            Object tgtI = heap[i - 1];
            Object tgtJ = heap[j - 1];
            if(cmprtr.compare(tgtI, tgtJ) >= 0)
                break;
            heap[i - 1] = tgtJ;
            heap[j - 1] = tgtI;
            i = j;
        }

    }

    public static Object deletemin()
    {
        Object heap[] = mHeap;
        Comparator cmprtr = mCmprtr;
        Object res = heap[0];
        heap[0] = heap[--mNum];
        int i = 1;
        for(int j = i * 2; j <= mNum; j = i * 2)
        {
            if(j + 1 <= mNum && cmprtr.compare(heap[j - 1], heap[j]) > 0)
                j++;
            Object tgtI = heap[i - 1];
            Object tgtJ = heap[j - 1];
            if(cmprtr.compare(tgtI, tgtJ) > 0)
            {
                heap[i - 1] = tgtJ;
                heap[j - 1] = tgtI;
            }
            i = j;
        }

        return res;
    }

    public static void clckblSort(Clckbl srcArray[])
    {
        int len = srcArray.length;
        if(mClckblHeap == null || mClckblHeap.length != len)
            mClckblHeap = new Clckbl[len];
        mNum = 0;
        for(int trgt = 0; trgt < len; trgt++)
            clckblInsert(srcArray[trgt]);

        for(int trgt = 0; mNum > 0; trgt++)
            srcArray[trgt] = clckblDeletemin();

        mCmprtr = null;
    }

    public static void clckblInsert(Clckbl clckbl)
    {
        Clckbl heap[] = mClckblHeap;
        heap[mNum++] = clckbl;
        int i = mNum;
        for(int j = i / 2; i > 1; j = i / 2)
        {
            Clckbl tgtI = heap[i - 1];
            Clckbl tgtJ = heap[j - 1];
            int cmpRes = tgtI.mY - tgtJ.mY;
            if(cmpRes == 0)
                cmpRes = tgtI.mX - tgtJ.mX;
            if(cmpRes >= 0)
                break;
            heap[i - 1] = tgtJ;
            heap[j - 1] = tgtI;
            i = j;
        }

    }

    public static Clckbl clckblDeletemin()
    {
        Clckbl heap[] = mClckblHeap;
        Clckbl res = heap[0];
        heap[0] = heap[--mNum];
        int i = 1;
        for(int j = i * 2; j <= mNum; j = i * 2)
        {
            int cmpRes = heap[j - 1].mY - heap[j].mY;
            if(cmpRes == 0)
                cmpRes = heap[j - 1].mX - heap[j].mX;
            if(j + 1 <= mNum && cmpRes > 0)
                j++;
            Clckbl tgtI = heap[i - 1];
            Clckbl tgtJ = heap[j - 1];
            cmpRes = tgtI.mY - tgtJ.mY;
            if(cmpRes == 0)
                cmpRes = tgtI.mX - tgtJ.mX;
            if(cmpRes > 0)
            {
                heap[i - 1] = tgtJ;
                heap[j - 1] = tgtI;
            }
            i = j;
        }

        return res;
    }

    private static Comparator mCmprtr;
    private static Object mHeap[] = null;
    private static int mNum;
    private static Clckbl mClckblHeap[] = null;

}
