// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package mm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Referenced classes of package mm:
//            MazeGenerator, Room, BacktrackAI, IterativeBacktrackAI

public class Main extends JFrame
    implements ActionListener
{
    private class MMPanel extends JPanel
    {

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            float size = Math.min((float)getHeight() / ((float)maze.length + 0.0F), (float)getWidth() / ((float)maze[0].length + 0.0F));
            for(int x = 0; x < maze.length; x++)
            {
                for(int y = 0; y < maze[0].length; y++)
                    if(maze[x][y] != null)
                    {
                        if(!visitedSpaces[x][y] && !path[x][y])
                            g.setColor(Color.WHITE);
                        else
                        if(!path[x][y])
                            g.setColor(Color.YELLOW);
                        else
                            g.setColor(Color.ORANGE);
                        g.fillRect(Math.round((float)x * size), Math.round((float)y * size), Math.round(size), Math.round(size));
                        g.setColor(Color.BLACK);
                        if(!maze[x][y].isOpen(0))
                            g.drawLine(Math.round((float)x * size), Math.round((float)y * size), Math.round((float)(x + 1) * size), Math.round((float)y * size));
                        if(!maze[x][y].isOpen(3))
                            g.drawLine(Math.round((float)x * size), Math.round((float)(y + 1) * size), Math.round((float)(x + 1) * size), Math.round((float)(y + 1) * size));
                        if(!maze[x][y].isOpen(1))
                            g.drawLine(Math.round((float)x * size), Math.round((float)y * size), Math.round((float)x * size), Math.round((float)(y + 1) * size));
                        if(!maze[x][y].isOpen(2))
                            g.drawLine(Math.round((float)(x + 1) * size), Math.round((float)y * size), Math.round((float)(x + 1) * size), Math.round((float)(y + 1) * size));
                    } else
                    {
                        g.setColor(Color.GRAY);
                        g.fillRect(Math.round((float)x * size + 1.0F), Math.round((float)y * size + 1.0F), Math.round(size - 2.0F), Math.round(size - 2.0F));
                    }

            }

            g.setColor(Color.RED);
            g.fillOval(0, 0, (int)size, (int)size);
            g.setColor(Color.BLUE);
            g.fillOval((int)((float)goalX * size), (int)((float)goalY * size), (int)size, (int)size);
            g.setColor(Color.CYAN);
            g.fillOval((int)((float)currentX * size), (int)((float)currentY * size), (int)size, (int)size);
        }

        private static final long serialVersionUID = 1L;


        public MMPanel()
        {

            addMouseMotionListener(new MouseTracker(null));
        }
    }

    private class MazeThread
        implements Runnable
    {

        public void run()
        {
            int w = getValue(widthField);
            int h = getValue(heightField);
            visitedSpaces = new boolean[w][h];
            path = new boolean[w][h];
            if(type == 0)
            {
                generatingMaze = true;
                maze = MazeGenerator.generateMaze(w, h, frame);
                generatingMaze = false;
                goalX = maze.length - 1;
                goalY = maze[0].length - 1;
                currentX = -1;
                currentY = -1;
                repaint();
            } else
            if(type == 4)
            {
                try
                {
                    path = BacktrackAI.solveMaze(maze, 0, 0, goalX, goalY, frame);
                }
                catch(StackOverflowError e)
                {
                    JOptionPane.showMessageDialog(frame, "Oh no! There were too many backtracks\nfor a recursive algorithm!\nTry the iterative one or A*!");
                }
                currentX = -1;
                currentY = -1;
                repaint();
            } else
            if(type == 1)
            {
                path = IterativeBacktrackAI.solveMaze(maze, 0, 0, goalX, goalY, frame);
                currentX = -1;
                currentY = -1;
                repaint();
            } else
            if(type == 2)
            {
                currentX = -1;
                currentY = -1;
                repaint();
            }
        }

        Main frame;
        int type;
        public static final int TYPE_MAZE = 0;
        public static final int TYPE_BACKTRACK = 1;
        public static final int TYPE_ASTAR = 2;
        public static final int TYPE_RECURSIVE_BACKTRACK = 4;

        public MazeThread(Main f, int t)
        {
     
            type = 0;
            frame = f;
            type = t;
        }
    }

    private class MouseTracker extends MouseMotionAdapter
    {

        public void mouseMoved(MouseEvent e)
        {
            if(generatingMaze || !goalAtMouse.isSelected())
                return;
            double size = Math.min((double)panel.getHeight() / ((double)maze.length + 0.0D), (double)panel.getWidth() / ((double)maze[0].length + 0.0D));
            int gx = (int)((double)e.getX() / size);
            int gy = (int)((double)e.getY() / size - 1.0D);
            if(gx >= 0 && gx < maze.length && gy >= 0 && gy < maze[0].length)
            {
                goalX = gx;
                goalY = gy;
            }
            repaint();
        }


        private MouseTracker()
        {
  
        }

        MouseTracker(MouseTracker mousetracker)
        {
            this();
        }
    }


    public Main()
    {
        generatingMaze = false;
        generate = new JButton("New Maze");
        solveAStar = new JButton("A*");
        solveTrace = new JButton("Iterative BT");
        solveRTrace = new JButton("Recursive BT");
        widthField = new JTextField("20");
        heightField = new JTextField("20");
        speedField = new JTextField("50");
        goalAtMouse = new JCheckBox("Goal At Mouse");
        maze = MazeGenerator.generateMaze(getValue(widthField), getValue(heightField));
        path = new boolean[maze.length][maze[0].length];
        visitedSpaces = new boolean[maze.length][maze[0].length];
        currentX = -1;
        currentY = -1;
        goalX = maze.length - 1;
        goalY = maze[0].length - 1;
        panel = new MMPanel();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(panel, "Center");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 4));
        p.add(new JLabel("Solve With:"));
        p.add(solveAStar);
        p.add(solveTrace);
        p.add(solveRTrace);
        p.add(new JLabel("Width:"));
        p.add(widthField);
        p.add(new JLabel("Height:"));
        p.add(heightField);
        p.add(new JLabel("Draw Delay:"));
        p.add(speedField);
        p.add(goalAtMouse);
        p.add(generate);
        cp.add(p, "South");
        generate.addActionListener(this);
        solveAStar.addActionListener(this);
        solveTrace.addActionListener(this);
        solveRTrace.addActionListener(this);
        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String args[])
    {
        new Main();
    }

    private int getValue(JTextField field)
    {
        try
        {
            return Integer.parseInt(field.getText());
        }
        catch(Exception e)
        {
            return 20;
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        Object s = e.getSource();
        if(s == generate)
            (new Thread(new MazeThread(this, 0))).start();
        else
        if(s == solveAStar)
            (new Thread(new MazeThread(this, 2))).start();
        else
        if(s == solveTrace)
            (new Thread(new MazeThread(this, 1))).start();
        else
        if(s == solveRTrace)
            (new Thread(new MazeThread(this, 4))).start();
    }

    public void setMaze(Room newMaze[][])
    {
        maze = newMaze;
    }

    public void setSpacesVisited(boolean newSpaces[][])
    {
        visitedSpaces = newSpaces;
    }

    public void setCurrentPos(int i, int j)
    {
        currentX = i;
        currentY = j;
    }

    public int getDrawDelay()
    {
        return getValue(speedField);
    }

    private static final long serialVersionUID = 1L;
    private Room maze[][];
    private int goalX;
    private int goalY;
    private boolean generatingMaze;
    private MMPanel panel;
    private JButton generate;
    private JButton solveAStar;
    private JButton solveTrace;
    private JButton solveRTrace;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField speedField;
    private JCheckBox goalAtMouse;
    private boolean path[][];
    private boolean visitedSpaces[][];
    private int currentX;
    private int currentY;





















}
