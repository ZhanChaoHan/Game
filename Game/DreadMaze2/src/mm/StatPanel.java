// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StatPanel.java

package mm;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

// Referenced classes of package mm:
//            Globals, PlayerEntity, AnimationSet, Item

public class StatPanel extends JPanel
{

    public StatPanel()
    {
        image = new JLabel();
        playerName = new JLabel();
        playerLevel = new JLabel();
        playerHP = new JLabel();
        playerPow = new JLabel();
        playerDef = new JLabel();
        playerDodge = new JLabel();
        weaponName = new JLabel();
        weaponLevel = new JLabel();
        weaponHP = new JLabel();
        weaponPow = new JLabel();
        weaponDef = new JLabel();
        weaponDodge = new JLabel();
        armorName = new JLabel();
        armorLevel = new JLabel();
        armorHP = new JLabel();
        armorPow = new JLabel();
        armorDef = new JLabel();
        armorDodge = new JLabel();
        setLayout(new GridLayout(1, 4));
        add(image);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 2));
        p1.add(new JLabel("Name:"));
        p1.add(playerName);
        p1.add(new JLabel("Level:"));
        p1.add(playerLevel);
        p1.add(new JLabel("Life:"));
        p1.add(playerHP);
        p1.add(new JLabel("Power:"));
        p1.add(playerPow);
        p1.add(new JLabel("Defense:"));
        p1.add(playerDef);
        p1.add(new JLabel("Dodge:"));
        p1.add(playerDodge);
        add(p1);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 2));
        p2.add(new JLabel("Weapon:"));
        p2.add(weaponName);
        p2.add(new JLabel("Level:"));
        p2.add(weaponLevel);
        p2.add(new JLabel("+Life:"));
        p2.add(weaponHP);
        p2.add(new JLabel("+Power:"));
        p2.add(weaponPow);
        p2.add(new JLabel("+Defense:"));
        p2.add(weaponDef);
        p2.add(new JLabel("+Dodge:"));
        p2.add(weaponDodge);
        add(p2);
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(6, 2));
        p3.add(new JLabel("Armor:"));
        p3.add(armorName);
        p3.add(new JLabel("Level:"));
        p3.add(armorLevel);
        p3.add(new JLabel("+Life:"));
        p3.add(armorHP);
        p3.add(new JLabel("+Power:"));
        p3.add(armorPow);
        p3.add(new JLabel("+Defense:"));
        p3.add(armorDef);
        p3.add(new JLabel("+Dodge:"));
        p3.add(armorDodge);
        add(p3);
        setPreferredSize(new Dimension(500, 100));
    }

    public void redraw()
    {
        PlayerEntity p = Globals.getPlayers()[player];
        image.setIcon(new ImageIcon(p.getAnimationSet().getCurrentImage()));
        playerName.setText((new StringBuilder("#")).append(player).toString());
        playerLevel.setText((new StringBuilder(String.valueOf(1 + p.getLevel()))).toString());
        playerHP.setText((new StringBuilder(String.valueOf(p.getHitPoints()))).append("/").append(p.getMaxHitPoints()).toString());
        playerPow.setText((new StringBuilder(String.valueOf(p.getPower()))).toString());
        playerDef.setText((new StringBuilder(String.valueOf(p.getDefense()))).toString());
        playerDodge.setText((new StringBuilder(String.valueOf((int)(p.getDodge() * 100F)))).append("%").toString());
        Item w = p.getWeapon();
        if(w == null)
        {
            weaponName.setText("None");
            weaponLevel.setText("0");
            weaponHP.setText("0/0");
            weaponPow.setText("0");
            weaponDef.setText("0");
            weaponDodge.setText("0%");
        } else
        {
            weaponName.setText("Sword");
            weaponLevel.setText((new StringBuilder(String.valueOf(w.getLevel()))).toString());
            weaponHP.setText((new StringBuilder(String.valueOf(w.getHitPoints()))).append("/").append(w.getMaxHitPoints()).toString());
            weaponPow.setText((new StringBuilder(String.valueOf(w.getPower()))).toString());
            weaponDef.setText((new StringBuilder(String.valueOf(w.getArmor()))).toString());
            weaponDodge.setText((new StringBuilder(String.valueOf((int)w.getDodge() * 100))).append("%").toString());
        }
        Item a = p.getArmor();
        if(a == null)
        {
            armorName.setText("None");
            armorLevel.setText("0");
            armorHP.setText("0/0");
            armorPow.setText("0");
            armorDef.setText("0");
            armorDodge.setText("0%");
        } else
        {
            armorName.setText("Shield");
            armorLevel.setText((new StringBuilder(String.valueOf(a.getLevel()))).toString());
            armorHP.setText((new StringBuilder(String.valueOf(a.getHitPoints()))).append("/").append(a.getMaxHitPoints()).toString());
            armorPow.setText((new StringBuilder(String.valueOf(a.getPower()))).toString());
            armorDef.setText((new StringBuilder(String.valueOf(a.getArmor()))).toString());
            armorDodge.setText((new StringBuilder(String.valueOf((int)a.getDodge() * 100))).append("%").toString());
        }
    }

    public void setPlayer(int p)
    {
        player = p;
    }

    private static final long serialVersionUID = 1L;
    private int player;
    private JLabel image;
    private JLabel playerName;
    private JLabel playerLevel;
    private JLabel playerHP;
    private JLabel playerPow;
    private JLabel playerDef;
    private JLabel playerDodge;
    private JLabel weaponName;
    private JLabel weaponLevel;
    private JLabel weaponHP;
    private JLabel weaponPow;
    private JLabel weaponDef;
    private JLabel weaponDodge;
    private JLabel armorName;
    private JLabel armorLevel;
    private JLabel armorHP;
    private JLabel armorPow;
    private JLabel armorDef;
    private JLabel armorDodge;
}
