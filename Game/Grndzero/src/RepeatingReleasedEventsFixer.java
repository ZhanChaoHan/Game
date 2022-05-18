// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RepeatingReleasedEventsFixer.java

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

public class RepeatingReleasedEventsFixer
    implements AWTEventListener
{
    private class ReleasedAction
        implements ActionListener
    {

        void cancel()
        {
            if(!$assertionsDisabled && !RepeatingReleasedEventsFixer.assertEDT())
            {
                throw new AssertionError();
            } else
            {
                _timer.stop();
                _timer = null;
                _map.remove(Integer.valueOf(_originalKeyEvent.getKeyCode()));
                return;
            }
        }

        public void actionPerformed(ActionEvent e)
        {
            if(!$assertionsDisabled && !RepeatingReleasedEventsFixer.assertEDT())
                throw new AssertionError();
            if(_timer == null)
            {
                return;
            } else
            {
                cancel();
                KeyEvent newEvent = new RepostedKeyEvent((Component)_originalKeyEvent.getSource(), _originalKeyEvent.getID(), _originalKeyEvent.getWhen(), _originalKeyEvent.getModifiers(), _originalKeyEvent.getKeyCode(), _originalKeyEvent.getKeyChar(), _originalKeyEvent.getKeyLocation());
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(newEvent);
                return;
            }
        }

        private final KeyEvent _originalKeyEvent;
        private Timer _timer;

        static final boolean $assertionsDisabled = true;


        ReleasedAction(KeyEvent originalReleased, Timer timer)
        {
     
            _timer = timer;
            _originalKeyEvent = originalReleased;
        }
    }

    public static interface Reposted
    {
    }

    public static class RepostedKeyEvent extends KeyEvent
        implements Reposted
    {

        private static final long serialVersionUID = 0x74600317e50e6d68L;

        public RepostedKeyEvent(Component source, int id, long when, int modifiers, int keyCode, char keyChar, 
                int keyLocation)
        {
            super(source, id, when, modifiers, keyCode, keyChar, keyLocation);
        }
    }


    public RepeatingReleasedEventsFixer()
    {
    }

    public void install()
    {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, 8L);
    }

    public void remove()
    {
        Toolkit.getDefaultToolkit().removeAWTEventListener(this);
    }

    public void eventDispatched(AWTEvent event)
    {
        if( !(event instanceof KeyEvent))
            throw new AssertionError("Shall only listen to KeyEvents, so no other events shall come here");
        if(!assertEDT())
            throw new AssertionError();
        if(event instanceof Reposted)
            return;
        if(event.getID() == 400)
            return;
        KeyEvent keyEvent = (KeyEvent)event;
        if(keyEvent.isConsumed())
            return;
        if(keyEvent.getID() == 402)
        {
            Timer timer = new Timer(2, null);
            ReleasedAction action = new ReleasedAction(keyEvent, timer);
            timer.addActionListener(action);
            timer.start();
            _map.put(Integer.valueOf(keyEvent.getKeyCode()), action);
            keyEvent.consume();
        } else
        if(keyEvent.getID() == 401)
        {
            ReleasedAction action = (ReleasedAction)_map.remove(Integer.valueOf(keyEvent.getKeyCode()));
            if(action != null)
                action.cancel();
        } else
        {
            throw new AssertionError("All IDs should be covered.");
        }
    }

    private static boolean assertEDT()
    {
        if(!EventQueue.isDispatchThread())
            throw new AssertionError((new StringBuilder("Not EDT, but [")).append(Thread.currentThread()).append("].").toString());
        else
            return true;
    }

    private final Map _map = new HashMap();




}
