// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockShape.java

package fab;

import java.awt.Graphics;
import java.awt.Point;

// Referenced classes of package fab:
//            Rect, BlockBoard

public class BlockShape
{
    public enum State
    {

       
            IN ,
            STAND,
            OUT
    }

    public enum Direction 
    {

        RIGHT,
        DOWN,
        LEFT,
        ROTATE
    }

    public enum ShapeType{

        LINE,SQUARE,STYPE,ZTYPE,TTYPE,LTYPE,RTYPE,EMPTY
 
    }


    public BlockShape(int boardx, int boardy, int posx, int posy, int squaresize)
    {
        state = State.STAND;
        shapetype = ShapeType.SQUARE;
        dim = 0;
        my = 0;
        mx = 0;
        sy = 0;
        first = true;
        this.posx = posx;
        this.posy = posy;
        this.boardx = boardx;
        this.boardy = boardy;
        this.squaresize = squaresize;
    }

    public void Draw(Graphics g, boolean checky)
    {
        for(int i = 0; i < MaxI(); i++)
            if(Y(i) >= 0 || !checky)
            {
                Rect r = new Rect(X(i) * squaresize + boardx + mx, Y(i) * squaresize + boardy + my, squaresize - 1, squaresize - 1, shapetype);
                r.Draw(g);
            }

    }

    // IN ,
   // STAND,
  //  OUT
    public void DrawState(Graphics g, boolean checky)
    {
        java.awt.Shape clip = g.getClip();
        g.setClip(boardx + -40, boardy + -50, 100, 100);
       
        switch(state)
        {
        case STAND: // '\002'
        default:
            break;

        case IN: // '\001'
            speed = speed - (speed / 100F) * 20F;
            sy -= speed;
            if(sy < 0)
            {
                sy = 0;
                state = State.STAND;
            }
            for(int i = 0; i < MaxI(); i++)
                if(Y(i) >= 0 || !checky)
                {
                    Rect r = new Rect(X(i) * squaresize + boardx + mx, Y(i) * squaresize + boardy + my + sy, squaresize - 1, squaresize - 1, shapetype);
                    r.Draw(g);
                }

            break;

        case OUT: // '\003'
            speed = speed + (speed / 100F) * 20F;
            sy -= speed;
            if(sy < -100)
                In();
            for(int i = 0; i < MaxI(); i++)
                if(oldY(i) >= 0 || !checky)
                {
                    Rect r = new Rect(oldX(i) * squaresize + boardx + oldmx, oldY(i) * squaresize + boardy + my + sy, squaresize - 1, squaresize - 1, oldshape);
                    r.Draw(g);
                }

            break;
        }
        g.setClip(clip);
    }

    public boolean Move(BlockBoard board)
    {
        return MoveDirection(board, Direction.DOWN);
    }

    public boolean MoveDirection(BlockBoard board, Direction d)
    {
        if(CanMove(board, d, shapes[dim][shapetype.ordinal()]))
        {
            posx += dx;
            posy += dy;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean CanMove(BlockBoard board, Direction m, Point p[])
    {
        Setdxdy(m);
        for(int i = 0; i < MaxI(); i++)
        {
            int tx = p[i].x + posx + dx;
            int ty = p[i].y + posy + dy;
            if(ty >= board.rows)
                return false;
            if(tx > board.cols - 1 || tx < 0)
                return false;
            if(ty > 0 && board.board[tx][ty] != ShapeType.EMPTY)
                return false;
        }

        return true;
    }

    public int MaxI()
    {
        return maxi[dim];
    }

    public int X(int i)
    {
        return shapes[dim][shapetype.ordinal()][i].x + posx;
    }

    public int oldX(int i)
    {
        return shapes[dim][oldshape.ordinal()][i].x + posx;
    }

    public int Y(int i)
    {
        return shapes[dim][shapetype.ordinal()][i].y + posy;
    }

    public int oldY(int i)
    {
        return shapes[dim][oldshape.ordinal()][i].y + posy;
    }

    /**
     *        RIGHT,
        DOWN,
        LEFT,
        ROTATE
     * @param m
     */
    void Setdxdy(Direction m)
    {
        int dx1 = 0;
        int dy1 = 0;
        switch(m)
        {
        case RIGHT: // '\001'
            dx1 = 1;
            break;

        case DOWN: // '\002'
            dx1 = -1;
            break;

        case LEFT: // '\003'
            dy1 = 1;
            break;
        }
        dx = dx1;
        dy = dy1;
    }

    public void Rotate(BlockBoard board)
    {
        if(shapetype != ShapeType.SQUARE)
        {
            Point p[] = new Point[MaxI()];
            for(int i = 0; i < MaxI(); i++)
            {
                p[i] = new Point(0, 0);
                p[i].y = shapes[dim][shapetype.ordinal()][i].x;
                p[i].x = -shapes[dim][shapetype.ordinal()][i].y;
            }

            if(CanMove(board, Direction.ROTATE, p))
            {
                for(int i = 0; i < MaxI(); i++)
                {
                    shapes[dim][shapetype.ordinal()][i].x = p[i].x;
                    shapes[dim][shapetype.ordinal()][i].y = p[i].y;
                }

            }
        }
    }

    public static ShapeType NextRandomShape()
    {
        ShapeType stv[] = ShapeType.values();
        int s = (int)(Math.random() * (double)stv.length - 1.0D);
        return stv[s];
    }

    public void SetShape(ShapeType st)
    {
        oldshape = shapetype;
        oldmx = mx;
        shapetype = st;
        if(first)
        {
            In();
            first = false;
        } else
        {
            Out();
        }
    }

    public void MoveFullDown(BlockBoard board)
    {
        while(MoveDirection(board, Direction.DOWN)) ;
    }

    public void OnStart()
    {
        int m = 0;
        for(int i = 0; i < MaxI(); i++)
            if(shapes[dim][shapetype.ordinal()][i].y > m)
                m = shapes[dim][shapetype.ordinal()][i].y;

        posy = -m - 1;
    }

    public void OnMiddle()
    {
        int min = 0;
        int max = 0;
        int minx = 0;
        int maxx = 0;
        for(int i = 0; i < MaxI(); i++)
        {
            if(shapes[dim][shapetype.ordinal()][i].y > max)
                max = shapes[dim][shapetype.ordinal()][i].y;
            if(shapes[dim][shapetype.ordinal()][i].y < min)
                min = shapes[dim][shapetype.ordinal()][i].y;
            if(shapes[dim][shapetype.ordinal()][i].x > maxx)
                maxx = shapes[dim][shapetype.ordinal()][i].x;
            if(shapes[dim][shapetype.ordinal()][i].x < minx)
                minx = shapes[dim][shapetype.ordinal()][i].x;
        }

        float t = (float)(min + max) / 2.0F;
        my = (int)(-t * (float)squaresize) - 10;
        t = (float)(minx + maxx) / 2.0F;
        mx = (int)(-t * (float)squaresize);
    }

    public void Out()
    {
        state = State.OUT;
        speed = 12F;
        sy = 0;
    }

    public void In()
    {
        speed = 25F;
        state = State.IN;
        sy = 100;
    }

    State state;
    ShapeType shapetype;
    ShapeType oldshape;
    private int dim;
    private int dx;
    private int dy;
    private int my;
    private int mx;
    private int oldmx;
    private int sy;
    private final int maxi[] = {
        4
    };
    final int DELAY = 7;
    final int SPEED = 25;
    final int SY = 100;
    float speed;
    private boolean first;
    int posx;
    int posy;
    int squaresize;
    int currdelay;
    int boardx;
    int boardy;
    Point shapes[][][] = {
        {
            {
                new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1)
            }, {
                new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(1, 0)
            }, {
                new Point(0, 0), new Point(-1, 0), new Point(0, -1), new Point(1, -1)
            }, {
                new Point(0, -1), new Point(-1, -1), new Point(0, 0), new Point(1, 0)
            }, {
                new Point(0, 0), new Point(-1, 0), new Point(0, 1), new Point(1, 0)
            }, {
                new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 1)
            }, {
                new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, -1)
            }
        }
    };
}
