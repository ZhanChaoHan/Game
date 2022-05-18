// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorFilters.java

package gameEngine;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class ColorFilters
{

    public ColorFilters()
    {
    }

    public static Image setTransparentColor(Image image,final Color color)
    {
        RGBImageFilter rgbimagefilter = new RGBImageFilter() {

            public int filterRGB(int i, int j, int k)
            {
                if((k | 0xff000000) == testColor)
                    return k & 0xffffff;
                else
                    return k;
            }

            public int testColor;
            {
 
                testColor = color.getRGB() | 0xff000000;
            }
        }
;
        FilteredImageSource filteredimagesource = new FilteredImageSource(image.getSource(), rgbimagefilter);
        Image image1 = Toolkit.getDefaultToolkit().createImage(filteredimagesource);
        return image1;
    }

    public static Image setSemiTransparency(Image image, double d)
    {
        if(d > 1.0D)
            d = 1.0D;
        if(d < 0.0D)
            d = 0.0D;
       final int alpha = (int)(255D * (1.0D - d));
        
        RGBImageFilter rgbimagefilter = new RGBImageFilter() {

            public int filterRGB(int j, int k, int l)
            {
                if((l & 0xff000000) != 0)
                    return l & 0xffffff | alpha << 24;
                else
                    return l;
            }

        }
;
        FilteredImageSource filteredimagesource = new FilteredImageSource(image.getSource(), rgbimagefilter);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.sync();
        Image image1 = toolkit.createImage(filteredimagesource);
        return image1;
    }
}
