// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScoreFrame.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ScoreFrame extends Frame
    implements ActionListener
{

    ScoreFrame(String s)
    {
        setTitle("\u30E0\u30B5\u30B7\u306E\u5192\u967A \u30B9\u30B3\u30A2\u767B\u9332");
        setSize(280, 220);
        setLayout(new FlowLayout(0, 5, 15));
        expLabel = new Label("\u3042\u306A\u305F\u306E\u30B9\u30B3\u30A2\u306F " + calScore() + "\u70B9 \u3067\u3059\u3002");
        nameLabel = new Label("\u30B9\u30B3\u30A2\u30CD\u30FC\u30E0");
        commentLabel = new Label("\u30B3\u30E1\u30F3\u30C8\u3000\u3000");
        scoreName = new TextField(20);
        scoreComment = new TextField(20);
        scoreReg = new Button("\u30B9\u30B3\u30A2\u767B\u9332");
        scoreCancel = new Button("\u767B\u9332\u3057\u306A\u3044");
        scoreReg.addActionListener(this);
        scoreCancel.addActionListener(this);
        add(expLabel);
        add(nameLabel);
        add(scoreName);
        add(commentLabel);
        add(scoreComment);
        add(scoreReg);
        add(scoreCancel);
        push = false;
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        if(actionevent.getActionCommand() == "\u30B9\u30B3\u30A2\u767B\u9332")
        {
            if(!push)
            {
                push = true;
                String s = scoreName.getText();
                String s1 = scoreComment.getText();
                int i = calScore();
                sendData(i, s, s1);
            } else
            {
                dispose();
            }
        } else
        if(actionevent.getActionCommand() == "\u767B\u9332\u3057\u306A\u3044")
            dispose();
        if(push)
            dispose();
    }

    int sendData(int i, String s, String s1)
    {
        try
        {
            String s2 = "http://recchy.hp.infoseek.co.jp/cgi-bin/msssendscore.cgi";
            URL url = new URL(s2);
            URLConnection urlconnection = url.openConnection();
            urlconnection.setUseCaches(false);
            urlconnection.setDoOutput(true);
            PrintWriter printwriter = new PrintWriter(urlconnection.getOutputStream());
            printwriter.print(i);
            printwriter.print(",");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(s1);
            printwriter.close();
            java.io.InputStream inputstream = urlconnection.getInputStream();
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            String s3;
            while((s3 = bufferedreader.readLine()) != null) 
                System.out.println(s3);
            bufferedreader.close();
        }
        catch(IOException ioexception) { }
        return 0;
    }

    int calScore()
    {
        int i = app.musashi.ep * 10 + app.returnDistance() * 50;
        return i;
    }

    private Button scoreReg;
    private Button scoreCancel;
    private TextField scoreName;
    private TextField scoreComment;
    private Label nameLabel;
    private Label commentLabel;
    private Label expLabel;
    protected static Musashi app;
    private boolean push;
    private URL jumpUrl;
    private StringBuffer buf;
}
