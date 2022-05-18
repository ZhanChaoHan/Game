// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InfoPanel.java

package mm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package mm:
//            Globals, PlayerEntity, ImageManager, CombatEntity, 
//            AnimationSet, EnemyEntity, Maze, SinuosityPanel

public class InfoPanel extends JPanel
    implements ChangeListener, ActionListener
{

    public InfoPanel(SinuosityPanel p)
    {
        image = new JLabel();
        name = new JLabel();
        level = new JLabel();
        hitPoints = new JLabel();
        power = new JLabel();
        defense = new JLabel();
        dodge = new JLabel();
        gridResize = new JSlider(25, 100, 50);
        turnLength = new JSlider(50, 500, 100);
        tutorialsOn = new JCheckBox("Tutorials On");
        panel = p;
        setLayout(new GridLayout(3, 1));
        add(image);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 2));
        p1.add(new JLabel("Enemy:"));
        p1.add(name);
        p1.add(new JLabel("Level:"));
        p1.add(level);
        p1.add(new JLabel("Life:"));
        p1.add(hitPoints);
        p1.add(new JLabel("Power:"));
        p1.add(power);
        p1.add(new JLabel("Defense:"));
        p1.add(defense);
        p1.add(new JLabel("Dodge:"));
        p1.add(dodge);
        add(p1);
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1));
        p2.add(new JLabel("Zoom"));
        JPanel p2a = new JPanel();
        p2a.setLayout(new BorderLayout());
        p2a.add(new JLabel("-"), "West");
        p2a.add(gridResize, "Center");
        p2a.add(new JLabel("+"), "East");
        p2.add(p2a);
        p2.add(new JLabel("Turn Length"));
        JPanel p2b = new JPanel();
        p2b.setLayout(new BorderLayout());
        p2b.add(new JLabel("-"), "West");
        p2b.add(turnLength, "Center");
        p2b.add(new JLabel("+"), "East");
        p2.add(p2b);
        p2.add(tutorialsOn);
        add(p2);
        tutorialsOn.addActionListener(this);
        tutorialsOn.setSelected(true);
        gridResize.addChangeListener(this);
        turnLength.addChangeListener(this);
        setPreferredSize(new Dimension(150, 500));
    }

    public void redraw()
    {
        PlayerEntity p = Globals.getPlayers()[player];
        CombatEntity e = p.getTarget();
        if(e == null)
        {
            image.setIcon(new ImageIcon(ImageManager.getImage("Monster/notarget.png")));
            name.setText("None");
            level.setText("0");
            hitPoints.setText("0/0");
            power.setText("0");
            defense.setText("0");
            dodge.setText("0%");
        } else
        {
            image.setIcon(new ImageIcon(e.getAnimationSet().getCurrentImage()));
            name.setText(((EnemyEntity)e).getName());
            level.setText((new StringBuilder(String.valueOf(1 + e.getLevel()))).toString());
            hitPoints.setText((new StringBuilder(String.valueOf(e.getHitPoints()))).append("/").append(e.getMaxHitPoints()).toString());
            power.setText((new StringBuilder(String.valueOf(e.getPower()))).toString());
            defense.setText((new StringBuilder(String.valueOf(e.getDefense()))).toString());
            dodge.setText((new StringBuilder(String.valueOf((int)e.getDodge() * 100))).append("%").toString());
        }
    }

    public void setPlayer(int p)
    {
        player = p;
    }

    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource() == gridResize)
        {
            Maze.setGridSize(gridResize.getValue());
            panel.resize();
        } else
        {
            Globals.setTurnLength(turnLength.getValue());
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        Globals.setTutorialMode(tutorialsOn.isSelected());
    }

    private static final long serialVersionUID = 1L;
    private int player;
    private JLabel image;
    private JLabel name;
    private JLabel level;
    private JLabel hitPoints;
    private JLabel power;
    private JLabel defense;
    private JLabel dodge;
    private JSlider gridResize;
    private JSlider turnLength;
    private SinuosityPanel panel;
    private JCheckBox tutorialsOn;
}
