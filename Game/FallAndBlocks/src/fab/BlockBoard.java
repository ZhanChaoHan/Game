// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockBoard.java

package fab;

import java.awt.Graphics;

// Referenced classes of package fab:
//            Rect, BlockShape

public class BlockBoard
{
    private class RemovingBoard
    {

        BlockShape.ShapeType board;
        int state;
        boolean removing;

        RemovingBoard(BlockShape.ShapeType board)
        {
            this.board = board;
            state = 0;
            removing = false;
        }
    }


    public BlockBoard(int cols, int rows, int squaresize, int posx, int posy)
    {
        isremoving = false;
        this.cols = cols;
        this.rows = rows;
        this.posx = posx;
        this.posy = posy;
        this.squaresize = squaresize;
        board = new BlockShape.ShapeType[cols][rows];
        remboard = new RemovingBoard[cols][rows];
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
                board[i][j] = BlockShape.ShapeType.EMPTY;

        }

        width = cols * squaresize;
        height = rows * squaresize;
    }

    public void Draw(Graphics g)
    {
        boolean removedrect = false;
        for(int i = 0; i < cols; i++)
        {
            boolean removed = false;
            for(int j = 0; j < rows; j++)
            {
                Rect r;
                if(!isremoving)
                {
                    if(board[i][j] != BlockShape.ShapeType.EMPTY)
                    {
                        r = new Rect(posx + i * squaresize, posy + j * squaresize, squaresize - 1, squaresize - 1, board[i][j]);
                        r.Draw(g);
                    }
                    continue;
                }
                if(remboard[i][j].board == BlockShape.ShapeType.EMPTY)
                    continue;
                r = new Rect(posx + i * squaresize, posy + j * squaresize, squaresize - 1, squaresize - 1, remboard[i][j].board);
                r.Draw(g);
                if(!removedrect && remboard[i][j].removing)
                {
                    remboard[i][j].board = BlockShape.ShapeType.EMPTY;
                    removed = true;
                    if(i == cols - 1)
                        isremoving = false;
                }
                if(j == rows - 1 && removed)
                    removedrect = true;
            }

        }

    }

    public void AddShape(BlockShape shape)
    {
        for(int i = 0; i < shape.MaxI(); i++)
            if(shape.Y(i) >= 0)
                board[shape.X(i)][shape.Y(i)] = shape.shapetype;

    }

    public int CheckRows()
    {
        int c = 0;
        int rowsremoved = 0;
        for(int j = 0; j < rows; j++)
        {
            for(int i = 0; i < cols; i++)
                if(board[i][j] != BlockShape.ShapeType.EMPTY)
                    c++;

            if(c == cols)
            {
                if(!isremoving)
                {
                    for(int j1 = 0; j1 < rows; j1++)
                    {
                        for(int i1 = 0; i1 < cols; i1++)
                            remboard[i1][j1] = new RemovingBoard(board[i1][j1]);

                    }

                }
                RemoveRow(j);
                rowsremoved++;
            }
            c = 0;
        }

        return rowsremoved;
    }

    void RemoveRow(int row)
    {
        for(int i = 0; i < cols; i++)
            remboard[i][row].removing = true;

        for(int j = row; j > 0; j--)
        {
            for(int i = 0; i < cols; i++)
            {
                board[i][j] = board[i][j - 1];
                isremoving = true;
            }

        }

        for(int i = 0; i < cols; i++)
            board[i][0] = BlockShape.ShapeType.EMPTY;

    }

    public boolean CheckGameOver()
    {
        for(int i = 0; i < cols; i++)
            if(board[i][0] != BlockShape.ShapeType.EMPTY)
                return true;

        return false;
    }

    public void StopRemoving()
    {
        if(isremoving)
            isremoving = false;
    }

    int rows;
    int cols;
    int posx;
    int posy;
    int width;
    int height;
    int squaresize;
    boolean isremoving;
    BlockShape.ShapeType board[][];
    RemovingBoard remboard[][];
}
