// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageBlitter.java

package gameEngine;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class ImageBlitter
{

    public ImageBlitter()
    {
    }

    public static Image crop(Image image, int i, int j, int k, int l)
    {
        java.awt.image.ImageProducer imageproducer = image.getSource();
        FilteredImageSource filteredimagesource = new FilteredImageSource(imageproducer, new CropImageFilter(i, j, k, l));
        Image image1 = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
        return image1;
    }

    public static Image cropTiled(Image image, int i, int j, int k, int l)
    {
        java.awt.image.ImageProducer imageproducer = image.getSource();
        int i1 = 1;
        FilteredImageSource filteredimagesource = new FilteredImageSource(imageproducer, new CropImageFilter(i1 + i1 * 2 * i + i * k, i1 + i1 * 2 * j + j * l, k, l));
        Image image1 = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
        return image1;
    }
}
