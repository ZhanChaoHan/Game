// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageText.java

package fab;


public class MessageText
{

    public MessageText(String text, boolean selected, boolean input)
    {
        this.text = text;
        this.selected = selected;
        this.input = input;
    }

    public MessageText(String text)
    {
        this.text = text;
        selected = false;
        input = false;
    }

    public MessageText(String text, boolean selected, boolean input, boolean selectionactive)
    {
        this.text = text;
        this.selected = selected;
        this.input = input;
        this.selectionactive = selectionactive;
    }

    public MessageText(String text, boolean selected, boolean input, boolean selectionactive, int group)
    {
        this.text = text;
        this.selected = selected;
        this.input = input;
        this.selectionactive = selectionactive;
        this.group = group;
    }

    String text;
    boolean selected;
    boolean input;
    boolean selectionactive;
    int group;
}
