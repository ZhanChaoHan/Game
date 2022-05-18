// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Board.java

package fab;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Referenced classes of package fab:
//            Text, Level, Version, Score, 
//            MessageText, Message, ScoreModeSelector, BlockShape, 
//            BlockBoard, HighScores

public class Board extends JPanel
    implements ActionListener, Runnable
{
    private class TAdapter extends KeyAdapter
    {
     /*   RUNNING,GAME_OVER,PAUSED,INTRO,INPUT_NAME,SHOW_LEVEL,END_LEVEL,TUTORIAL*/
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
          

label0:
            switch(gamestate)
            {
            case SHOW_LEVEL: // '\006'
            case END_LEVEL: // '\007'
            case TUTORIAL: // '\b'
            default:
                break;

            case RUNNING: // '\001'
                switch(key)
                {
                case 37: // '%'
                    shape.MoveDirection(blockboard, BlockShape.Direction.LEFT);
                    break;

                case 38: // '&'
                    shape.Rotate(blockboard);
                    break;

                case 39: // '\''
                    shape.MoveDirection(blockboard, BlockShape.Direction.RIGHT);
                    break;

                case 40: // '('
                    shape.MoveDirection(blockboard, BlockShape.Direction.DOWN);
                    break;

                case 32: // ' '
                    blockboard.StopRemoving();
                    shape.MoveFullDown(blockboard);
                    break;

                case 80: // 'P'
                    gamestate = GameState.PAUSED;
                    break;
                }
                break;

            case GAME_OVER: // '\002'
                if(key == 10)
                    gameovermsg2.MessageOut();
                switch(key)
                {
                case 40: // '('
                    score.Reload(sms.Next());
                    break;

                case 38: // '&'
                    score.Reload(sms.Prev());
                    break;
                }
                break;

            case PAUSED: // '\003'
                if(key == 80)
                    gamestate = GameState.RUNNING;
                break;

            case INTRO: // '\004'
                switch(key)
                {
                default:
                    break;

                case 10: // '\n'
                    intromsg.MessageOut();
                    SetNextVisible();
                    SetBoardSize();
                    mode = (new StringBuilder()).append(boardmode).append(nextmode).toString();
                    break label0;

                case 40: // '('
                    if(activeselection == boardsizeselection)
                    {
                        if(activeselection++ > 4)
                            activeselection = 2;
                        SetIntroSelectionBoard();
                        break label0;
                    }
                    if(activeselection++ > 9)
                        activeselection = 8;
                    SetIntroSelectionNext();
                    break label0;

                case 38: // '&'
                    if(activeselection == boardsizeselection)
                    {
                        if(activeselection-- < 3)
                            activeselection = 5;
                        SetIntroSelectionBoard();
                        break label0;
                    }
                    if(activeselection-- < 9)
                        activeselection = 10;
                    SetIntroSelectionNext();
                    break label0;

                case 9: // '\t'
                    if(activeselection == boardsizeselection)
                    {
                        intromsg.t[boardsizeselection].selectionactive = false;
                        intromsg.t[nextselection].selectionactive = true;
                        activeselection = nextselection;
                    } else
                    {
                        intromsg.t[nextselection].selectionactive = false;
                        intromsg.t[boardsizeselection].selectionactive = true;
                        activeselection = boardsizeselection;
                    }
                    break;
                }
                break;

            case INPUT_NAME: // '\005'
                if(key == 10)
                {
                    if(playername.isEmpty())
                        playername = "noname";
                    score.SetHigh(playername, mode);
                    gameovermsg.MessageOut();
                    break;
                }
                if(playername.length() <= 20)
                    if(key >= 65 && key <= 122 || key == 32)
                        playername = (new StringBuilder()).append(playername).append(e.getKeyChar()).toString();
                    else
                    if(key == 8)
                    {
                        int l = playername.length();
                        if(l > 0)
                            playername = playername.substring(0, l - 1);
                    }
                gameovermsg.t[3].text = playername;
                break;
            }
        }


        private TAdapter()
        {

        }

    }

    public static enum GameState 
    {

          RUNNING,GAME_OVER,PAUSED,INTRO,INPUT_NAME,SHOW_LEVEL,END_LEVEL,TUTORIAL
    }


    public Board()
    {
    	
        gamestate = GameState.INTRO;
        text = new Text();
        level = new Level(0);
        playername = "";
        tutorialdone = false;
        messagedelay = 35;
        columns = 9;
        rows = 15;
        squaresize = 25;
        blockboardx = 20;
        blockboardy = 10;
        delay = 7;
        activeselection = 3;
        boardsizeselection = 3;
        nextselection = 9;
        version = new Version("0.10", 25, 442, 462);
        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.setDefaultFocusTraversalKeys(0, Collections.EMPTY_SET);
        kfm.setDefaultFocusTraversalKeys(1, Collections.EMPTY_SET);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        score = new Score();
        ImageIcon iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("board.png"));
        background = iid.getImage();
        iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("blockboard.png"));
        blockboardbackground = iid.getImage();
        iid = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("message.png"));
        message = iid.getImage();
        nextbackground = blockboardbackground.getScaledInstance(100, 100, 0);
        introbackground = blockboardbackground.getScaledInstance(462, 420, 0);
        gameoverbackground = blockboardbackground.getScaledInstance(260, 130, 0);
        MessageText mt[] = {
            new MessageText((new StringBuilder()).append("Level ").append(level.level).toString()), new MessageText(""), new MessageText("Get ready")
        };
        levelmsg = new Message(126, 192, 260, 200, message, mt, 20);
        levelmsg.InitMessage();
        mt = (new MessageText[] {
            new MessageText("Game Over"), new MessageText((new StringBuilder()).append("Your score is: ").append(score.score).toString()), new MessageText("Please input your name:"), new MessageText("", false, true), new MessageText("and hit Enter to continue...")
        });
        gameovermsg = new Message(126, 60, 260, 130, gameoverbackground, mt, 0);
        gameovermsg.InitMessage();
        mt = (new MessageText[] {
            new MessageText("Game Over"), new MessageText(""), new MessageText(""), new MessageText("Press Enter to restart")
        });
        gameovermsg2 = new Message(126, 60, 260, 130, gameoverbackground, mt, 0);
        gameovermsg2.InitMessage();
        mt = (new MessageText[] {
            new MessageText("Use arrow keys and Tab to navigate menu."), new MessageText("Please select board size:"), new MessageText("small"), new MessageText("normal", true, false, true), new MessageText("big"), new MessageText("hardcore"), new MessageText(""), new MessageText("How many next shapes do you want to see:"), new MessageText("1 next shape"), new MessageText("2 next shapes", true, false), 
            new MessageText("0 next shapes"), new MessageText(""), new MessageText("Use arrow keys to move and turn shapes"), new MessageText("and space key drops the shape"), new MessageText(""), new MessageText("anytime during the play press p to pause"), new MessageText("and now hit Enter to start!")
        });
        intromsg = new Message(25, 7, 462, 420, introbackground, mt, 0);
        intromsg.InitMessage();
        currdelay = delay;
        sms = new ScoreModeSelector();
    }

    public void addNotify()
    {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    /**
     *     RUNNING,GAME_OVER,PAUSED,INTRO,INPUT_NAME,SHOW_LEVEL,END_LEVEL,TUTORIAL
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        switch(gamestate)
        {
        case RUNNING: // '\001'
            DrawRunning(g);
            break;

        case GAME_OVER: // '\002'
            DrawRunning(g);
            DrawGameOver(g);
            break;

        case PAUSED: // '\003'
            DrawRunning(g);
            break;

        case INTRO: // '\004'
            DrawIntro(g);
            break;

        case INPUT_NAME: // '\005'
            DrawRunning(g);
            DrawInputName(g);
            break;

        case SHOW_LEVEL: // '\006'
            DrawRunning(g);
            DrawLevel(g);
            break;
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void run()
    {
        long beforeTime = System.currentTimeMillis();
        do
        {
            UpdateGame();
            repaint();
            long timeDiff = System.currentTimeMillis() - beforeTime;
            long sleep = 50L - timeDiff;
            if(sleep < 0L)
                sleep = 2L;
            try
            {
                Thread.sleep(sleep);
            }
            catch(InterruptedException e)
            {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        } while(true);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
    }

    void DrawMainBackGround(Graphics g)
    {
        g.drawImage(background, -2, 0, this);
    }

    void DrawBorder(Graphics g)
    {
        java.awt.Color c = g.getColor();
        DrawMainBackGround(g);
        g.drawImage(blockboardbackground, blockboardx, blockboardy, this);
        DrawNext(g);
        g.setColor(c);
    }

    
    
    /**
     *           RUNNING,GAME_OVER,PAUSED,INTRO,INPUT_NAME,SHOW_LEVEL,END_LEVEL,TUTORIAL
     */
    void UpdateGame()
    {
        switch(gamestate)
        {
        case PAUSED: // '\003'
        case END_LEVEL: // '\007'
        case TUTORIAL: // '\b'
        default:
            break;

        case RUNNING: // '\001'
            if(currdelay-- != 0)
                break;
            if(!shape.Move(blockboard))
            {
                blockboard.AddShape(shape);
                int rowsremoved = blockboard.CheckRows();
                if(rowsremoved > 0)
                {
                    score.AddRow(rowsremoved);
                    if(level.IsLevelFinished(rowsremoved))
                        NextLevel();
                }
                score.Add();
                if(blockboard.CheckGameOver())
                {
                    gameovermsg.t[1].text = (new StringBuilder()).append("Your score is: ").append(score.score).toString();
                    gameovermsg.InitMessage();
                    gamestate = GameState.INPUT_NAME;
                } else
                {
                    shape = new BlockShape(blockboardx, blockboardy, columns / 2, -2, squaresize);
                    shape.SetShape(nextshapetype1);
                    shape.OnStart();
                    nextshapetype1 = nextshapetype2;
                    nextshapetype2 = BlockShape.NextRandomShape();
                    SetNextShapes();
                }
            }
            currdelay = delay;
            break;

        case INTRO: // '\004'
            if(!intromsg.ShowMessage())
                Restart();
            break;

        case GAME_OVER: // '\002'
            sms.message.ShowMessage();
            if(!gameovermsg2.ShowMessage())
            {
                intromsg.InitMessage();
                gamestate = GameState.INTRO;
            }
            break;

        case INPUT_NAME: // '\005'
            if(!gameovermsg.ShowMessage())
            {
                gameovermsg2.InitMessage();
                gamestate = GameState.GAME_OVER;
            }
            break;

        case SHOW_LEVEL: // '\006'
            if(!levelmsg.ShowMessage())
                gamestate = GameState.RUNNING;
            break;
        }
    }

    void DrawGameOver(Graphics g)
    {
        gameovermsg2.Draw(g, this);
        sms.message.Draw(g, this);
        score.hs.Draw(g, this);
    }

    void DrawInputName(Graphics g)
    {
        gameovermsg.Draw(g, this);
        score.hs.Draw(g, this);
    }

    void DrawIntro(Graphics g)
    {
        DrawMainBackGround(g);
        intromsg.Draw(g, this);
        version.Draw(g, this);
    }

    void DrawLevel(Graphics g)
    {
        levelmsg.Draw(g, this);
    }

    void DrawRunning(Graphics g)
    {
    	if(blockboard==null){
    		return;
    	}
        DrawBorder(g);
        blockboard.Draw(g);
        shape.Draw(g, true);
        level.Draw(g);
        score.Draw(g);
    }

    void Restart()
    {
        score.ResetScore();
        blockboardy = 464 - rows * squaresize - 20;
        blockboardbackground = blockboardbackground.getScaledInstance(columns * squaresize, rows * squaresize, 0);
        blockboard = new BlockBoard(columns, rows, squaresize, blockboardx, blockboardy);
        shape = new BlockShape(blockboardx, blockboardy, columns / 2, -2, squaresize);
        nextshape1 = new BlockShape(420, 244, 0, 0, 20);
        nextshape2 = new BlockShape(420, 394, 0, 0, 20);
        nextshapetype1 = BlockShape.NextRandomShape();
        nextshapetype2 = BlockShape.NextRandomShape();
        SetNextShapes();
        shape.SetShape(BlockShape.NextRandomShape());
        shape.OnStart();
        gamestate = GameState.SHOW_LEVEL;
        level.SetLevel(1);
        SetLevelMsg();
        shape.getClass();
        delay = 7;
        currdelay = delay;
    }

    void NextLevel()
    {
        level.NextLevel();
        SetLevelMsg();
        gamestate = GameState.SHOW_LEVEL;
        levelmsg.InitMessage();
        delay--;
    }

    private void SetNextShapes()
    {
        nextshape1.SetShape(nextshapetype1);
        nextshape1.OnMiddle();
        nextshape2.SetShape(nextshapetype2);
        nextshape2.OnMiddle();
    }

    private void SetIntroSelectionBoard()
    {
        intromsg.t[boardsizeselection].selected = false;
        intromsg.t[boardsizeselection].selectionactive = false;
        intromsg.t[activeselection].selected = true;
        intromsg.t[activeselection].selectionactive = true;
        boardsizeselection = activeselection;
    }

    private void SetIntroSelectionNext()
    {
        intromsg.t[nextselection].selected = false;
        intromsg.t[nextselection].selectionactive = false;
        intromsg.t[activeselection].selected = true;
        intromsg.t[activeselection].selectionactive = true;
        nextselection = activeselection;
    }

    private void SetBoardSize()
    {
        int d = 2;
        columns = boardsizes[boardsizeselection - d][0];
        rows = boardsizes[boardsizeselection - d][1];
        squaresize = boardsizes[boardsizeselection - d][2];
        switch(boardsizeselection)
        {
        case 2: // '\002'
            boardmode = "s";
            break;

        case 3: // '\003'
            boardmode = "n";
            break;

        case 4: // '\004'
            boardmode = "b";
            break;

        case 5: // '\005'
            boardmode = "h";
            break;
        }
    }

    private void SetNextVisible()
    {
        switch(nextselection)
        {
        case 8: // '\b'
            shownext = 1;
            nextmode = "1";
            break;

        case 9: // '\t'
            shownext = 2;
            nextmode = "2";
            break;

        case 10: // '\n'
            shownext = 0;
            nextmode = "0";
            break;
        }
    }

    private void DrawNext(Graphics g)
    {
        if(shownext > 0)
        {
            Text text = new Text();
            String msg = "Next 1";
            g.drawImage(nextbackground, 380, 194, this);
            text.Draw(msg, 380, 184, g, Text.Position.LEFT);
            if(nextshape1.state == BlockShape.State.STAND)
                nextshape1.Draw(g, false);
            else
                nextshape1.DrawState(g, false);
            if(shownext > 1)
            {
                msg = "Next 2";
                text.Draw(msg, 380, 334, g, Text.Position.LEFT);
                g.drawImage(nextbackground, 380, 344, this);
                if(nextshape2.state == BlockShape.State.STAND)
                    nextshape2.Draw(g, false);
                else
                    nextshape2.DrawState(g, false);
            }
        }
    }

    private void SetLevelMsg()
    {
        levelmsg.t[0].text = (new StringBuilder()).append("Level ").append(level.level).toString();
    }

    private final String VERSION = "0.10";
    GameState gamestate;
    final int FONT_SIZE = 21;
    public static final int DELAY = 50;
    static final int BLOCKBOARD_X = 10;
    static final int BLOCKBOARD_Y = 10;
    static final int SQUARESIZE = 20;
    static final int BORDER_HEIGHT_ALTERED = 36;
    static final int BORDER_WIDTH_ALTERED = 20;
    static final int NEXTAREA = 100;
    static final int NEXTAREAPOSX = -40;
    static final int NEXTAREAPOSY = -50;
    static final int NEXTSHAPE1_X = 420;
    static final int NEXTSHAPE1_Y = 244;
    static final int NEXTSHAPE2_X = 420;
    static final int NEXTSHAPE2_Y = 394;
    static final int NEXTAREALABEL = -10;
    static final int MESSAGE_WIDTH = 260;
    static final int MESSAGE_HEIGHT = 200;
    static final int MESSAGE_X = 126;
    static final int MESSAGE_Y = 60;
    final int GAMEOVER_MESSAGE_HEIGHT = 130;
    static final int INTRO_MESSAGE_WIDTH = 462;
    static final int INTRO_MESSAGE_HEIGHT = 420;
    static final int INTRO_MESSAGE_X = 25;
    static final int INTRO_MESSAGE_Y = 7;
    static final int BORDER_SHIFT = 2;
    static final int BORDER_SCORE_HEIGHT = 36;
    static final int MESSAGE_DELAY = 35;
    static final int MESSAGE_DELAY_LINE = 160;
    static final int APPLE_DELAY = 200;
    public static final int BOARD_X = 2;
    public static final int BOARD_Y = 38;
    public static final int BOARD_X_TO = 490;
    public static final int BOARD_Y_TO = 462;
    public static final int TOP_X = 126;
    public static final int TOP_Y = 210;
    final int STARTING_LEVEL = 1;
    private Thread animator;
    BlockBoard blockboard;
    BlockShape shape;
    BlockShape nextshape1;
    BlockShape nextshape2;
    Score score;
    BlockShape.ShapeType nextshapetype1;
    BlockShape.ShapeType nextshapetype2;
    Message levelmsg;
    Message intromsg;
    Message gameovermsg;
    Message gameovermsg2;
    Text text;
    Level level;
    Image background;
    Image message;
    Image blockboardbackground;
    Image nextbackground;
    Image introbackground;
    Image gameoverbackground;
    Image versionbackground;
    Image topscorebackground;
    String playername;
    boolean scoresubmitted;
    boolean tutorialdone;
    int messagedelay;
    int columns;
    int rows;
    int squaresize;
    int blockboardx;
    int blockboardy;
    int currdelay;
    int delay;
    private int activeselection;
    private int boardsizeselection;
    private int nextselection;
    private int boardsizes[][] = {
        {
            9, 15, 25
        }, {
            11, 20, 20
        }, {
            15, 25, 15
        }, {
            21, 40, 10
        }
    };
    private int shownext;
    Version version;
    String mode;
    String boardmode;
    String nextmode;
    ScoreModeSelector sms;










}
